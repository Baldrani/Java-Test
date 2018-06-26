package dao;

import java.util.List;
import beans.User;

public interface UserDao {
    void add( User user ) throws DaoException;
    User find( String email, String password) throws DaoException;
    List<User> lister() throws DaoException;
}

/*
public class UserDAO extends DAO<User>{



    public boolean create(User obj) {
        return false;
    }

    public boolean delete(User obj) {
        return false;
    }

    public boolean update(User obj) {
        return false;
    }

    public void create(String login, String password){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
    }

    public User find(int id) {
        User user = new User();

        try {
            ResultSet result = this.connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM eleve WHERE elv_id = " + id);

            /*
            if(result.first())
                 user = new User(
                        result.getString("login"),
                        result.getString("password"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
*/
