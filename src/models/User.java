package models;

import classes.ConnectionDb;

public class User {
    private String login;
    private String password;

    private ConnectionDb connection = new ConnectionDb();

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
