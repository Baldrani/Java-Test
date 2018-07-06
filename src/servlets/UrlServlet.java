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

@WebServlet(urlPatterns = "/u/*")
public class UrlServlet extends HttpServlet {

    private UrlDao urlDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactory();
        this.urlDao = daoFactory.getUrlDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Url url = urlDao.find("localhost:"+request.getLocalPort()+request.getRequestURI());
            if (url.getPassword() != null) {
                request.getSession().setAttribute("link", url);
                request.getSession().setAttribute("uri", request.getRequestURI());

                this.getServletContext().getRequestDispatcher("/u.jsp").forward(request, response);
                return;
            }
            response.sendRedirect(url.getBase());
            return;
        } catch (Exception e) {
            System.out.println("Url not found");
            request.getSession().setAttribute("message", "Cette url n'existe pas");
        }
        this.getServletContext().getRequestDispatcher("/").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("password").trim().compareTo("") != 0) {
            try {
                Url url = urlDao.find(request.getParameter("url"));
                if (request.getParameter("password") == url.getPassword()) {
                    System.out.println("OK");
                    response.sendRedirect(url.getBase());
                }
                System.out.println("."+request.getParameter("password")+".");
                System.out.println("."+url.getPassword()+".");
            } catch (Exception e) {
                System.out.println("Url not found");
                request.getSession().setAttribute("message", "Cette url n'existe pas");
            }
            return;
        }
    }
}