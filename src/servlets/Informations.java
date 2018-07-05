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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns="/modify-user")
public class Informations extends HttpServlet {
    private UserDao userDao;
    private User userSession;

    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactory();
        this.userDao = daoFactory.getUserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        this.userSession = (User) session.getAttribute( "user" );;
        if(session.getAttribute("user") == null)
        {
            request.setAttribute("erreur", "Vous devez ête connecté");
            response.sendRedirect("/");
            return;
        }
        this.getServletContext().getRequestDispatcher("/modify-user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("password-confirm").trim().compareTo("") != 0 && request.getParameter("password").trim().compareTo("") != 0 && request.getParameter("email").trim().compareTo("") != 0 && request.getParameter("login").trim().compareTo("") != 0) {
            if (request.getParameter("password").equals(request.getParameter("password-confirm"))) {
                try {
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String modifiedAt = sdf.format(date);

                    User user = new User();
                    user.setLogin(request.getParameter("login"));
                    user.setEmail(request.getParameter("email"));
                    user.setPassword(request.getParameter("password"));
                    user.setModifiedAt(modifiedAt);
                    user.setType(request.getParameter("type"));
                    userDao.modify(this.userSession.getId(), user);

                    this.userSession.setLogin(user.getLogin());
                    this.userSession.setEmail(user.getEmail());
                    this.userSession.setType(user.getType());
                } catch (Exception e) {
                    request.setAttribute("erreur", e.getMessage());
                }
                response.sendRedirect("/my-account");
            }
        }
    }
}
