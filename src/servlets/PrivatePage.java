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
        System.out.println(user.toString());
        if(session.getAttribute("login") == null)
        {
            //TODO 404
        }
        this.getServletContext().getRequestDispatcher("/private.jsp").forward(request, response);
    }

}
