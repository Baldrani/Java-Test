package dao;

import beans.Url;

public interface UrlDao {
    void add( Url url ) throws DaoException;
    Url find( String shortcut ) throws DaoException;
}