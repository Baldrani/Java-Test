package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.User;

public class UserDaoImpl implements UserDao {
    private DaoFactory daoFactory;

    UserDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void add(User user) throws DaoException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = daoFactory.getConnection();
            preparedStatement = conn.prepareStatement("INSERT INTO user (login, email, password, type, create_at) VALUES (?, ?, ?, ?, ?);");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getType());
            preparedStatement.setString(5, user.getCreateAt());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void modify(int userId, User user) throws DaoException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = daoFactory.getConnection();
            preparedStatement = conn.prepareStatement("UPDATE user SET login = ?, email = ?, password = ?, type = ?, modified_at = ? WHERE id = ?;");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getType());
            preparedStatement.setString(5, user.getModifiedAt());
            preparedStatement.setInt(6, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User find(String email, String password) throws DaoException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        User user2return = new User();
        try {
            conn = daoFactory.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM user WHERE (email = ?) AND (password = ?);");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst() ) {
                System.out.println("No data");
                return null;
            } else {
                if(resultSet.next()) {
                    do {
                        user2return.setLogin(resultSet.getString("login"));
                        user2return.setEmail(resultSet.getString("email"));
                        user2return.setId(resultSet.getInt("id"));
                        user2return.setType(resultSet.getString("type"));
                    } while (resultSet.next());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  user2return;
    }

    @Override
    public List<User> lister() throws DaoException {
        List<User> users = new ArrayList<User>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT login, email FROM user;");

            while (resultat.next()) {
                String login = resultat.getString("login");
                String email = resultat.getString("email");

                User user = new User();
                user.setLogin(login);
                user.setEmail(email);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}