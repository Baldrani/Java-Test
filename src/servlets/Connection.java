package servlets;

import beans.User;
import dao.DaoFactory;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns="/connection")
public class Connection extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactory();
        this.userDao = daoFactory.getUserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("password").trim().compareTo("") != 0 && request.getParameter("email").trim().compareTo("") != 0) {
            try {
                User user = userDao.find(request.getParameter("email"), request.getParameter("password"));
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("connected", true);
            } catch (Exception e) {
                System.out.println("User not found");
                request.getSession().setAttribute("message", "Ce compte n'existe pas");
                //request.setAttribute("erreur", e.getMessage());
            }
            response.sendRedirect("/private");
            return;
        }
        System.out.println("Field not filled");
    }
}
