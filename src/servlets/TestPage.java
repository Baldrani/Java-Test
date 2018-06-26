package servlets;

import beans.User;
import dao.DaoException;
import dao.DaoFactory;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/test")
public class TestPage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactory();
        this.userDao = daoFactory.getUserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("user", userDao.lister());
        }
        catch (DaoException e) {
            //request.setAttribute("erreur", e.getMessage());
            //throw new DaoException("Il y a une erreur mais je ne sais où ", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/create_user.jsp").forward(request, response);
    }


    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        try {
            User user = new User();
            user.setLogin(request.getParameter("login"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            userDao.add(user);
            request.setAttribute("users", userDao.lister());
        }
        catch (Exception e) {
            request.setAttribute("erreur", e.getMessage());
        }
        //this.getServletContext().getRequestDispatcher("/WEB-INF/create_user.jsp").forward(request, response);
    }
}
