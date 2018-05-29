package classes;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class Test
 */
@WebServlet(urlPatterns="/create-user")
public class CreateUserPage extends HttpServlet {

    private static final long serialVersionUID = 1L; //?

    public CreateUserPage() { // ?
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/create_user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("password").trim().compareTo("") == 0 && request.getParameter("email").trim().compareTo("") == 0 && request.getParameter("login").trim().compareTo("") == 0) {
            if (request.getParameter("password").equals(request.getParameter("password_confirm"))) {
                User user = new User();
                user.setLogin(request.getParameter("login"));
                user.setPassword(request.getParameter("password"));

                ConnectionDb connectionDb = new ConnectionDb();
                connectionDb.addUser(user);

                HttpSession session = request.getSession();
                session.setAttribute( "connected", true );
            }
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/create_user.jsp").forward(request, response);

    }

}
