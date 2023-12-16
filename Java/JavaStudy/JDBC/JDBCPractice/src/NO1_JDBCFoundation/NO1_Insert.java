package NO1_JDBCFoundation;

import java.sql.*;
import java.util.Scanner;

/**
 * @author 云梦
 * @create 8:43 2021/11/9
 * @Discription 从控制台向数据库的表中插入一条数据
 */

public class NO1_Insert {//14,'丘淑贞','女','1968-05-16','18270055783'
    public static void main(String[] args) {
        NO1_Insert insert = new NO1_Insert();
        Connection con = null;
        PreparedStatement ps = null;

        try {
            //1、获取连接
            con = insert.getConnection();

            //2、预编译sql语句，返回PreparedStatement的实例
            String url = "insert into beauty(id,name,sex,borndate,phone) values(?,?,?,?,?)";
            ps = con.prepareStatement(url);

            //3、填充占位符，插入数据
            Scanner input = new Scanner(System.in);
            System.out.print("id=");
            int id = input.nextInt();
            System.out.print("name=");
            String name = input.next();
            System.out.print("sex=");
            String sex = input.next();
            System.out.print("birthday=");
            String time = input.next();
            System.out.print("phone=");
            String phone = input.next();
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, sex);
            ps.setDate(4, Date.valueOf(time));
            ps.setString(5,phone);

            //4、执行操作
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5、关闭资源
            insert.closeResource(con, ps);
        }
    }
    //一、获取与数据库的连接
    public Connection getConnection() throws Exception {
        //1、获取基本信息
        String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String urlWindows = "jdbc:sqlserver://127.0.0.1:1433;databaseName=girls;integratedSecurity=true;";
        //String urlSqlserver = "jdbc:sqlserver://127.0.0.1:1433;databaseName=girls;user=root;password=31415926535li";

        //2、加载驱动
        Class.forName(driverClass);

        //3、获取连接
        return DriverManager.getConnection(urlWindows);
    }

    //二、关闭资源
    public void closeResource(Connection con, Statement ps){
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
}
