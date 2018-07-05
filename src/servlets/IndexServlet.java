package servlets;

import beans.Url;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import beans.User;
import dao.DaoFactory;
import dao.UrlDao;

@WebServlet(name = "IndexServlet", urlPatterns="/")
public class IndexServlet extends HttpServlet {

    private UrlDao urlDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactory();
        this.urlDao = daoFactory.getUrlDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("url").trim().compareTo("") != 0) {
                try {
                    Url url = new Url();
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String startingDate = sdf.format(date);

                    HttpSession session = request.getSession();
                    User user = (User) session.getAttribute("user");
                    if (session.getAttribute("user") != null)
                    {
                        url.setUserId(user.getId());
                    }

                    String uniqueID = UUID.randomUUID().toString();
                    url.setShortcut("localhost:"+request.getLocalPort()+"/u/"+uniqueID);
                    //TODO Rethink uniqueness
                    url.setBase(request.getParameter("url"));
                    url.setStartingDate(startingDate);
                    if(request.getParameter("password").trim().compareTo("") != 0) {
                        url.setPassword(request.getParameter("password"));
                    }
                    urlDao.add(url);
                } catch (Exception e) {
                    request.setAttribute("erreur", e.getMessage());
                }

                response.sendRedirect("/private")   ;
        }
    }
}