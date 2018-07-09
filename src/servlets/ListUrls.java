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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns="/list-urls")
public class ListUrls  extends  HttpServlet{
    private UrlDao urlDao;
    private List<Url> urls;

    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactory();
        this.urlDao = daoFactory.getUrlDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute( "user" );
        if(session.getAttribute("user") == null)
        {
            request.setAttribute("erreur", "Vous devez ête connecté");
            response.sendRedirect("/");
            return;
        }
        try {
            this.urls = this.urlDao.lister(user);
            request.getSession().setAttribute("urls", urls);
            //this.urls.forEach((i)->System.out.println(i.getBase() + i.getShortcut() + i.getCreateAt()));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        };
        this.getServletContext().getRequestDispatcher("/list-urls.jsp").forward(request, response);
    }

}
