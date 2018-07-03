package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = new HttpSession();
        session.invalidate();
        response.sendRedirect("restanes.jsp");
        return; // <--- Here.
    }
}
