package classes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class Private extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        //if(session.getAttribute("login") == null)
        //{
        //    response.sendRedirect("/test1/connexion");
        //}
        //else
        //{
            ConnexionBdd logins = new ConnexionBdd();


            List listusers = logins.listUsers();
            System.out.println(listusers);

            request.setAttribute("users", listusers);

            this.getServletContext().getRequestDispatcher("/WEB-INF/Private.jsp").forward(request, response);
        //}
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
