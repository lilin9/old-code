package NO0_JDBCUtils;

import java.sql.*;

/**
 * @author 云梦
 * @create 13:53 2021/11/10
 * @Discription 操控数据库的工具类
 */

public class JDBCUtils {
    public static Connection getConnection() throws Exception{
        //1、获取基本信息
        String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String urlwindows = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Demo;integratedSecurity=true;"; //Windows集成模式连接
        //String urlserver = "jdbc:sqlserver://127.0.0.1:1433;databaseName=test;user=root;password=31415926535li"; //sqlserver身份连接

        //2、加载驱动
        Class.forName(driverClass);

        //3、获取连接
        return DriverManager.getConnection(urlwindows);
    }

    public static void closeResource(Connection con, Statement ps){
        try {
            if (con != null) con.close();
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResource(Connection con, Statement ps, ResultSet res){
        try {
            if (con != null) con.close();
            if (ps != null) ps.close();
            if (res != null) res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
