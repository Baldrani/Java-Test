package dao;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.Url;
import beans.User;

public class UrlDaoImpl implements UrlDao {
    private DaoFactory daoFactory;

    UrlDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void add(Url url) throws DaoException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = daoFactory.getConnection();
            preparedStatement = conn.prepareStatement("INSERT INTO url (base, shortcut, starting_date, password, user_id) VALUES (?, ?, ?, ?, ?);");
            preparedStatement.setString(1, url.getBase());
            preparedStatement.setString(2, url.getShortcut());
            preparedStatement.setString(3, url.getStartingDate());
            preparedStatement.setString(4, url.getPassword());
            preparedStatement.setInt(5, url.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Url find(String shortcut) throws DaoException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Url url2return = new Url();
        try {
            conn = daoFactory.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM url WHERE (shortcut = ?)");
            preparedStatement.setString(1, shortcut);
            System.out.println(shortcut);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst() ) {
                System.out.println("No data");
                return null;
            } else {
                if(resultSet.next()) {
                    do {
                        url2return.setId(resultSet.getInt("id"));
                        url2return.setUserId(resultSet.getInt("user_id"));
                        url2return.setBase(resultSet.getString("base"));
                        url2return.setShortcut(resultSet.getString("shortcut"));
                        url2return.setPassword(resultSet.getString("password"));
                        url2return.setStartingDate(resultSet.getString("starting_date"));
                        //url2return.setStartingDate(resultSet.getString("starting_date")); //TODO ending_date
                    } while (resultSet.next());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  url2return;
    }
}