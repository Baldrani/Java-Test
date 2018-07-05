package dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.*;

public class DaoFactory {

    private String dbhost;
    private String dbport;
    private String dbname;
    private String dbuser;
    private String dbpwd;
    private java.sql.Connection conn = null;



    public DaoFactory() {
        try {
            Context env = (Context)new InitialContext().lookup("java:comp/env");
            this.dbhost = (String)env.lookup("BDD-HOST");
            this.dbport = (String)env.lookup("BDD-PORT");
            this.dbname = (String)env.lookup("BDD-NAME");
            this.dbuser = (String)env.lookup("BDD-USER");
            this.dbpwd = (String)env.lookup("BDD-PWD");
        } catch (NamingException e){
            e.printStackTrace();
        }
    }

    public DaoFactory getInstance() {
        try {
            String dburl = "jdbc:mysql://"+this.dbhost+":"+this.dbport+"/"+this.dbname+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = this.dbuser;
            String pwd = this.dbpwd;

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.conn = DriverManager.getConnection(dburl, user, pwd);

        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException ignore) {

                }
        }
        DaoFactory instance = new DaoFactory();
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
        e.printStackTrace();
        }
        String dburl = "jdbc:mysql://"+this.dbhost+":"+this.dbport+"/"+this.dbname+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        return DriverManager.getConnection(dburl, this.dbuser, this.dbpwd);
    }

    // Récupération du Dao
    public UserDao getUserDao() {
        return new UserDaoImpl(this);
    }
    public UrlDao getUrlDao() { return new UrlDaoImpl(this); }

}