package servlets;

import beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns="/private")
public class PrivatePage extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute( "user" );
        if(session.getAttribute("user") == null)
        {
            request.setAttribute("noConnexion", "Vous devez être connecté");
            response.sendRedirect("/");
            return;
        }
        this.getServletContext().getRequestDispatcher("/private.jsp").forward(request, response);
    }

}
