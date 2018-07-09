package servlets;

import beans.Stat;
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
import java.util.Date;

@WebServlet(urlPatterns = "/u/*")
public class UrlServlet extends HttpServlet {

    private UrlDao urlDao;
    private StatDao statDao;
    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactory();
        this.urlDao = daoFactory.getUrlDao();
        this.statDao = daoFactory.getStatDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Url url = urlDao.find("localhost:"+request.getLocalPort()+request.getRequestURI());
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateNow = sdf.format(now);
            int count = statDao.lister(url);
            if ((url.getEndingDate() != null && url.getEndingDate().compareTo(dateNow) < 0)
                 || (url.getStartingDate() != null && url.getStartingDate().compareTo(dateNow) > 0)
                 || (url.getMaxClic() > 0 && count >= url.getMaxClic()))
            {
                url.setCaptcha(0);
                url.setPassword(null);
                request.getSession().setAttribute("link", url);
                request.getSession().setAttribute("uri", request.getRequestURI());
                request.getSession().setAttribute("messageVerif", "Désolé cette URL n'est plus disponible !");
                this.getServletContext().getRequestDispatcher("/u.jsp").forward(request, response);
                return;
            }
            if (url.getPassword() != null || url.getCaptcha() != 0) {
                request.getSession().setAttribute("link", url);
                request.getSession().setAttribute("uri", request.getRequestURI());
                this.getServletContext().getRequestDispatcher("/u.jsp").forward(request, response);
                return;
            }
            Stat stat = new Stat();
            stat.setClickedAt(dateNow);
            stat.setIdUrl(url.getId());
            statDao.add(stat);
            response.sendRedirect(url.getBase());
            return;
        } catch (Exception e) {
            System.out.println("Url not found");
            request.getSession().setAttribute("messageVerif", "Cette url n'existe pas");
        }
        this.getServletContext().getRequestDispatcher("/").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Url url = urlDao.find(request.getParameter("url"));
            Date now = new Date();
            int okCaptcha = 1;
            int okPassword = 1;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateNow = sdf.format(now);
            HttpSession session = request.getSession();

            Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
            request.setCharacterEncoding("UTF-8"); // Do this so we can capture non-Latin chars

            if (request.getParameter("answer_captcha") != null){
                String answer = request.getParameter("answer_captcha");
                if (!captcha.isCorrect(answer)) {
                    okCaptcha = 0;
                }
            }
            if (request.getParameter("password") != null) {
                if (request.getParameter("password").trim().compareTo("") != 0) {
                    if (request.getParameter("password").compareTo(url.getPassword()) != 0) {
                        okPassword = 0;
                    }
                }
            }
            if (okCaptcha == 1 && okPassword == 1)
            {
                Stat stat = new Stat();
                stat.setClickedAt(dateNow);
                stat.setIdUrl(url.getId());
                statDao.add(stat);
                response.sendRedirect(url.getBase());
            }
            else
            {
                request.getSession().setAttribute("link", url);
                request.getSession().setAttribute("uri", request.getRequestURI());
                request.getSession().setAttribute("badEnter", "Mauvais mot de passe ou erreur de captcha");
                this.getServletContext().getRequestDispatcher("/u.jsp").forward(request, response);
            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Url not found");
            request.getSession().setAttribute("messageVerif", "Cette url n'existe pas");
        }
        request.getSession().removeAttribute("messageVerif");
    }
}