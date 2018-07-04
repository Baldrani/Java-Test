package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.Url;

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
}