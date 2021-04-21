package dataBaseManager;

import java.sql.*;

public class DBManager {
    public static ResultSet connectToDB(String column) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/TrelloTesting", "postgres", "******");
        con.setAutoCommit(false);
        Statement stmt = con.createStatement();


        return stmt.executeQuery("SELECT " + column + " FROM trellotwo;");
    }

    public static String getLogin() {
        String login = "";
        try {
          ResultSet rs = connectToDB("login");
            while (rs.next()){
                login = rs.getString("login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return login;
    }

    public static String getPassword() {
        String password = "";
        try {
            ResultSet rs = connectToDB("password");
            while (rs.next()){
                password = rs.getString("password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    public static String getKey() {
        String key = "";
        try {
            ResultSet rs = connectToDB("key");
            while (rs.next()){
                key = rs.getString("key");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    public static String getToken() {
        String token = "";
        try {
            ResultSet rs = connectToDB("token");
            while (rs.next()){
                token = rs.getString("token");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

}
