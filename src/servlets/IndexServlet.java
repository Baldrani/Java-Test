package servlets;

import beans.Url;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import beans.User;
import dao.DaoFactory;
import dao.UrlDao;

import static java.lang.Integer.parseInt;

@WebServlet(name = "IndexServlet", urlPatterns="/")
public class IndexServlet extends HttpServlet {

    private UrlDao urlDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = new DaoFactory();
        this.urlDao = daoFactory.getUrlDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        request.getSession().removeAttribute("noConnexion");
        request.getSession().removeAttribute("message");
        request.getSession().removeAttribute("link");
        System.out.println(request.getSession().getAttribute("link"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("url").trim().compareTo("") != 0) {
            if ((request.getParameter("validate") == null)
                    || (request.getParameter("validate").compareTo("debut_fin") == 0 && request.getParameter("date_base").compareTo("") != 0  && request.getParameter("date_fin").compareTo("") != 0)
                    || (request.getParameter("validate").compareTo("duree") == 0 && request.getParameter("no_debut").compareTo("") != 0 )
                    || (request.getParameter("validate").compareTo("max_clic") == 0 && request.getParameter("clic").compareTo("") != 0 ))
                try {
                    Url url = new Url();
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String createAt = sdf.format(date);

                    HttpSession session = request.getSession();
                    User user = (User) session.getAttribute("user");

                    if (request.getParameter("captchaCheck") != null && request.getParameter("captchaCheck").compareTo("on") == 0) {
                        url.setCaptcha(1);
                    }
                    if (request.getParameter("validate") != null) {
                        if (request.getParameter("validate").compareTo("max_clic") == 0) {
                            url.setMaxClic(parseInt(request.getParameter("clic")));
                        }
                        if (request.getParameter("validate").compareTo("duree") == 0) {
                            SimpleDateFormat formatDuree = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                            Date dateDuree = formatDuree.parse(request.getParameter("no_debut"));
                            url.setEndingDate(formatDuree.format(dateDuree));
                            url.setStartingDate(createAt);
                        }
                        if (request.getParameter("validate").compareTo("debut_fin") == 0) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                            Date dateDebut = format.parse(request.getParameter("date_base"));
                            Date dateFin = format.parse(request.getParameter("date_fin"));
                            url.setEndingDate(format.format(dateFin));
                            url.setStartingDate(format.format(dateDebut));
                        }
                    }
                    if (session.getAttribute("user") != null) {
                        url.setUserId(user.getId());
                    }

                    byte[] encodedBytes = Base64.getEncoder().encode(String.valueOf(urlDao.count() + 1).getBytes());
                    url.setShortcut("localhost:"+request.getLocalPort()+"/u/"+new String(encodedBytes));

                    url.setBase(request.getParameter("url"));
                    url.setCreateAt(createAt);
                    if(request.getParameter("password").trim().compareTo("") != 0) {
                        url.setPassword(request.getParameter("password"));
                    }
                    urlDao.add(url);
                    request.getSession().setAttribute("link", url.getShortcut());
                } catch (Exception e) {
                    request.setAttribute("erreur", e.getMessage());
                }
        }
        response.sendRedirect("/");
    }
}