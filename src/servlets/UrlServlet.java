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
            response.sendRedirect(url.getBase());
            return;
        } catch (Exception e) {
            System.out.println("Url not found");
            request.getSession().setAttribute("message", "Cette url n'existe pas");
        }
        this.getServletContext().getRequestDispatcher("/").forward(request, response);
    }
}