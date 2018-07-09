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


@WebServlet(urlPatterns="/my-account")
public class Account extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute( "user" );
        if(session.getAttribute("user") == null)
        {
            request.setAttribute("noConnexion", "Vous devez être connecté");
            response.sendRedirect("/");
            //this.getServletContext().getRequestDispatcher("/").forward(request, response);
            return;
        }
        this.getServletContext().getRequestDispatcher("/my-account.jsp").forward(request, response);
    }

}
