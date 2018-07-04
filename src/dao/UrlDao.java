package dao;

import java.util.List;
import beans.Url;

public interface UrlDao {
    void add( Url url ) throws DaoException;
}