package NO1_UserJDBC;

import java.sql.*;

/**
 * @author 云梦
 * @create 2021/11/7
 * @Description 操作数据库的工具类
 */
public class NO3_JDBCUtils {
    /**
     * @author 云梦
     * @create 2021/11/7
     * @Discription 获取数据库的连接
     */
    public static Connection getConnection() throws Exception {
        Connection con;

        //1、获取基本信息
        String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        //String urlserver = "jdbc:sqlserver://127.0.0.1:1433;databaseName=test;user=root;password=31415926535li"; //sqlserver身份连接
        String urlwindows = "jdbc:sqlserver://127.0.0.1:1433;databaseName=girls;integratedSecurity=true;"; //Windows集成模式连接

        //2、加载驱动
        Class.forName(driverClass);

        //3、获取连接
        con = DriverManager.getConnection(urlwindows);

        return con;
    }

    /**
     *
     * @author 云梦
     * @create 2021/11/7
     * @Discription 关闭数据库资源的操作
     */
    public static void closeResource(Connection con, Statement ps){
        //7、关闭资源
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeResource(Connection con, Statement ps, ResultSet resultSet){
        //7、关闭资源
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
