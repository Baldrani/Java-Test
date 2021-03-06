package servlets;

import beans.User;
import classes.ConnectionDb;
import dao.DaoFactory;
import dao.UserDao;
import dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns="/create-user")
public class CreateUserPage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactory();
        this.userDao = daoFactory.getUserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO Redirect to private if connected
        this.getServletContext().getRequestDispatcher("/create_user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("password-confirm").trim().compareTo("") != 0 && request.getParameter("password").trim().compareTo("") != 0 && request.getParameter("email").trim().compareTo("") != 0 && request.getParameter("login").trim().compareTo("") != 0) {
            if (request.getParameter("password").equals(request.getParameter("password-confirm"))) {
                try {
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String createAt = sdf.format(date);

                    User user = new User();
                    user.setLogin(request.getParameter("login"));
                    user.setEmail(request.getParameter("email"));
                    user.setPassword(request.getParameter("password"));
                    user.setCreateAt(createAt);
                    System.out.println(request.getParameter("type"));
                    user.setType(request.getParameter("type"));
                    userDao.add(user);

                    user = userDao.find(request.getParameter("email"), request.getParameter("password"));
                    HttpSession session = request.getSession();
                    session.setAttribute("connected", true);
                    session.setAttribute("user", user);
                } catch (Exception e) {
                    request.setAttribute("erreur", e.getMessage());
                }

                response.sendRedirect("/private");
            }
        }
        //Redirect + One of the field was empty
        //TODO ERROR HANDLING
    }
}
