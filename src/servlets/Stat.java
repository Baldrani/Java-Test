package servlets;

import beans.Url;
import beans.User;
import dao.DaoFactory;
import dao.StatDao;
import dao.UrlDao;
import nl.captcha.Captcha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(urlPatterns = "/stat/*")
public class Stat extends HttpServlet{

    private UrlDao urlDao;
    private StatDao statDao;
    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactory();
        this.urlDao = daoFactory.getUrlDao();
        this.statDao = daoFactory.getStatDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Url url = urlDao.find("localhost:"+request.getLocalPort()+"/u"+request.getPathInfo());

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute( "user" );
            if (session.getAttribute("user") == null)
            {
                request.setAttribute("erreur", "Vous devez ête connecté");
                response.sendRedirect("/");
                return;
            }
            request.getSession().setAttribute("url", url);

            List<beans.Stat> stats = new ArrayList<beans.Stat>();
            stats = this.statDao.reallister(url);

            beans.Stat temp = new beans.Stat();
            ArrayList<String> dates = new ArrayList<String>();
            for (int i = 0; i < stats.size(); i++)
            {
                temp = stats.get(i);
                String[] waiting = temp.getClickedAt().split(" ");
                String [] format = waiting[0].split("-");
                String last = format[2] + '/' + format[1] + '/' + format[0];
                dates.add(waiting[0]);
            }
            HashMap<String, Integer> chartData = new HashMap<String, Integer>();

            for (int i = 0; i < dates.size(); i++)
            {
                String date = dates.get(i);
                if (chartData.containsKey(date) == true) {
                    int nbDate = chartData.get(date) + 1;
                    chartData.remove(date);
                    chartData.put(date, nbDate);
                }
                else
                {
                    chartData.put(date, 1);
                }
            }
           Map<String, Integer> sortedMap = new TreeMap<String, Integer>(chartData);

            request.getSession().setAttribute("chartData", sortedMap);

        } catch (Exception e) {
            System.out.println("Url not found");
            request.getSession().setAttribute("message", "Cette url n'existe pas");
        }
        this.getServletContext().getRequestDispatcher("/stat.jsp").forward(request, response);
    }
}
