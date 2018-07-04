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
        System.out.println(user);
        if(session.getAttribute("user") == null)
        {
            System.out.println("ICI");
            //TODO 404
        }
        this.getServletContext().getRequestDispatcher("/private.jsp").forward(request, response);
    }

}
