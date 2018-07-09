package dao;

import beans.Url;
import beans.User;

import java.util.List;

public interface UrlDao {
    void add( Url url ) throws DaoException;
    Url find( String shortcut ) throws DaoException;
    List<Url> lister(User user) throws DaoException;

}