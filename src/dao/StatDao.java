package dao;

import beans.Stat;
import beans.Url;
import beans.User;

import java.util.List;

public interface StatDao {
    void add( Stat stat ) throws DaoException;
    int lister(Url url) throws DaoException;
    List<Stat> reallister(Url url) throws DaoException;
}