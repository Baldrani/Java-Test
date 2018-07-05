package dao;

import java.util.List;
import beans.User;

public interface UserDao {
    void add( User user ) throws DaoException;
    void modify(int userId, User user) throws DaoException;
    User find( String email, String password) throws DaoException;
    List<User> lister() throws DaoException;
}