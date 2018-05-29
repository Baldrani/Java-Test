package classes;

import models.User;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDb {
    private Connection connection;

    /**
     * loadDatabase
     */
    public void loadDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java2", "root", "azerty123");
        } catch (SQLException e) {
            e.printStackTrace();
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
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ignore) {
            ignore.printStackTrace();
        }
    }

    //User part

    /**
     * addUser
     * @param user
     */
    public void addUser(User user) {
        loadDatabase();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (login, password) VALUES(?, ?);");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();
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
            statement = this.connection.createStatement();

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
            statement = this.connection.createStatement();

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

        loadDatabase();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?;");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
