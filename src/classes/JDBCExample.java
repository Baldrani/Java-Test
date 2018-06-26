package classes;

import java.sql.*;

public class JDBCExample {

    public static void main(String[] argv) {

        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        java.sql.Connection connection = null;


        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://db:3306/firstdb","root", "simple");
                    //.getConnection("jdbc:mysql://localhost/java" + "user=root&password=azerty123");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }


        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}