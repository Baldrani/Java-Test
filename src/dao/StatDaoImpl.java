package dao;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.Stat;
import beans.Url;
import beans.User;

public class StatDaoImpl implements StatDao {
    private DaoFactory daoFactory;

    StatDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void add(Stat stat) throws DaoException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = daoFactory.getConnection();
            preparedStatement = conn.prepareStatement("INSERT INTO stat (clicked_at, id_url) VALUES (?, ?);");
            preparedStatement.setString(1, stat.getClickedAt());
            preparedStatement.setInt(2, stat.getIdUrl());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int lister(Url url) throws DaoException {
        Connection conn = null;
        ResultSet resultat = null;
        PreparedStatement preparedStatement = null;
        int i = 0;

        try {
            conn = daoFactory.getConnection();
            preparedStatement = conn.prepareStatement("SELECT id, clicked_at FROM stat WHERE id_url = ?;");
            preparedStatement.setInt(1, url.getId());
            resultat = preparedStatement.executeQuery();
            while (resultat.next()) {
                i = i + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<Stat> reallister(Url url) throws DaoException {

        List<Stat> stats = new ArrayList<Stat>();
        Connection conn = null;
        ResultSet resultat = null;
        PreparedStatement preparedStatement = null;
        int i = 0;

        try {
            conn = daoFactory.getConnection();
            preparedStatement = conn.prepareStatement("SELECT id, clicked_at FROM stat WHERE id_url = ?;");
            preparedStatement.setInt(1, url.getId());
            resultat = preparedStatement.executeQuery();
            while (resultat.next()) {
                String clicked_at = resultat.getString("clicked_at");

                Stat stat = new Stat();
                stat.setClickedAt(clicked_at);
                stats.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }

}