package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by MrLi on 2022/01/09/13:14
 */
public class JDBCUtils {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/master";
            String user = "root";
            String password = "123abc";

            Class.forName("com.mysql.jdbc.Driver");

            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection con) {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}