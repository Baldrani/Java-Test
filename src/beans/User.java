package beans;

import classes.ConnectionDb;
//import dao.UserDAO;

public class User {
    private String login;
    private String password;

    public User() {
    }

    public void create(String login, String password) {
        //UserDAO userDao = new UserDAO(this);
        //userDao.create();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
