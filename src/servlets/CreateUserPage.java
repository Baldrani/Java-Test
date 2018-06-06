package servlets;

import beans.User;
import classes.ConnectionDb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns="/create-user")
public class CreateUserPage extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/create_user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("password-confirm").trim().compareTo("") == 0 && request.getParameter("password").trim().compareTo("") == 0 && request.getParameter("email").trim().compareTo("") == 0 && request.getParameter("login").trim().compareTo("") == 0) {
            if (request.getParameter("password").equals(request.getParameter("password-confirm"))) {
                User user = new User();
                user.setLogin(request.getParameter("login"));
                user.setPassword(request.getParameter("password"));

                ConnectionDb connectionDb = new ConnectionDb();
                connectionDb.addUser(user);

                HttpSession session = request.getSession();
                session.setAttribute( "connected", true );
                session.setAttribute("user", user);
            }
        }

        //this.getServletContext().getRequestDispatcher("/WEB-INF/create_user.jsp").forward(request, response);
        response.sendRedirect("/private");
    }

}
