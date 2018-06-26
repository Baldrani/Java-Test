package classes;

import beans.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDb {
    private String dbhost;
    private String dbport;
    private String dbname;
    private String dbuser;
    private String dbpwd;
    private java.sql.Connection conn = null;

    public ConnectionDb() {

            try {
                Context env = (Context)new InitialContext().lookup("java:comp/env");
                this.dbhost = (String)env.lookup("BDD-HOST");
                this.dbport = (String)env.lookup("BDD-PORT");
                this.dbname = (String)env.lookup("BDD-NAME");
                this.dbuser = (String)env.lookup("BDD-USER");
                this.dbpwd = (String)env.lookup("BDD-PWD");

                String url = "jdbc:mysql://"+this.dbhost+":"+this.dbport+"/"+this.dbname+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                String user = this.dbuser;
                String pwd = this.dbpwd;

                System.out.println(url);

                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                this.conn = DriverManager.getConnection(url, user, pwd);

            } catch (ClassNotFoundException | SQLException | NamingException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    try {
                        conn.close();
                    } catch (SQLException ignore) {

                    }
            }
    }
    /**
     * endConnection
     * @param statement
     * @param result
     */
    private void endConnection(Statement statement, ResultSet result) {
        try {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (this.conn != null) {
                this.conn.close();
            }
        } catch (SQLException ignore) {
            ignore.printStackTrace();
        }
    }

    //User Part

    /**
     * addUser
     * @param user
     */
    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement("INSERT INTO users (login, password) VALUES(?, ?);");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * verifUser
     * @param user
     * @return
     */
    public boolean verifUser(User user) {
        boolean foundUser = false;
        Statement statement = null;
        ResultSet result = null;

        try {
            statement = this.conn.createStatement();

            result = statement.executeQuery("SELECT * FROM users WHERE login = '" + user.getLogin() + "' AND password = '" + user.getPassword() + "';");

            while (result.next()) {
                foundUser = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            endConnection(statement, result);
        }

        return foundUser;
    }

    public List<User> listUsers() {
        List<User> users = new ArrayList<>();

        Statement statement = null;
        ResultSet result = null;

        try {
            statement = this.conn.createStatement();

            result = statement.executeQuery("SELECT login, password FROM users");

            while (result.next()) {
                String login = result.getString("login");
                String password = result.getString("password");

                User user = new User();
                user.setLogin(login);
                user.setPassword(password);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            endConnection(statement, result);
        }

        return users;
    }

    public void deleteUser(User user, int id) {
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement("DELETE FROM users WHERE id=?;");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
