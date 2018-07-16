package servlets;

import beans.Url;
import beans.User;
import dao.DaoFactory;
import dao.UrlDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@WebServlet(urlPatterns="/create-csv")
public class CreateCSVServlet extends HttpServlet {

    private UrlDao urlDao;
    private List<Url> urls;

    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactory();
        this.urlDao = daoFactory.getUrlDao();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute( "user" );

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"urls-export.csv\"");
        request.getSession().setAttribute("urls", urls);
        try {
            OutputStream outputStream = response.getOutputStream();
            String outputResult = "Shortcut, Url, Created at\n";
            this.urls = this.urlDao.lister(user);
            for(Url url : this.urls){
                outputResult += ""+url.getBase()+", "+url.getShortcut()+", "+url.getCreateAt()+"\n";
            }
            outputStream.write(outputResult.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}