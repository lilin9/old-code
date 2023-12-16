package NO0_JDBCUtils;

import NO1_UserJDBC.NO1_ConnectionJDBC;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author 云梦
 * @create 13:53 2021/11/10
 * @Discription 操控数据库的工具类
 */
public class JDBCUtils {

    public static Connection getConnection() throws Exception {
        //1、获取基本信息
        String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String urlwindows = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Demo;integratedSecurity=true;"; //Windows集成模式连接
        String urlserver = "jdbc:sqlserver://127.0.0.1:1433;databaseName=test;user=root;password=123abc"; //sqlserver身份连接

        //2、加载驱动
        Class.forName(driverClass);

        //3、获取连接
        return DriverManager.getConnection(urlserver);
    }
    public static Connection getConnection1() throws Exception {
        //1、获取四个基本信息
        InputStream is = NO1_ConnectionJDBC.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pro = new Properties();
        pro.load(is);
        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        String url = pro.getProperty("url");
        String driverClass = pro.getProperty("driverClass");

        //2、加载驱动
        Class.forName(driverClass);

        //3、获取连接
        return DriverManager.getConnection(url, user, password);
    }

    public static void closeResource(Connection con, Statement ps) {
        try {
            if (con != null) con.close();
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResource(Connection con, Statement ps, ResultSet res) {
        try {
            if (con != null) con.close();
            if (ps != null) ps.close();
            if (res != null) res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
