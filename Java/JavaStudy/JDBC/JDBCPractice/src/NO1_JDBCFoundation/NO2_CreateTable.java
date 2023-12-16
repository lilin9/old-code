package NO1_JDBCFoundation;

import java.sql.*;

/**
 * @author 云梦
 * @create 8:44 2021/11/9
 * @Discription 创立数据库表examStudent，并向数据表中添加数据
 */

public class NO2_CreateTable {
    public static void main(String[] args) {
        NO2_CreateTable create = new NO2_CreateTable();
        create.createTable();
        create.insertData();
    }

    //建立数据库表examStudent
    public void createTable() {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            //1、获取连接
            con = getConnection();

            //2、预编译sql语句，返回PreparedStatement的实例
            String sql = "create table examStudent(flowID int,type int,idCard varchar(18) primary key,examCard varchar(15) unique,studentName varchar(20),location varchar(20),grade int)";
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(con, ps);
        }
    }

    //往数据库examStudent里添加数据
    public void insertData() {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            //1、获取连接
            con = getConnection();

            //2、预编译sql语句
            String[] sql = new String[]{"insert into examStudent(flowID,type,idCard,examCard,studentName,location,grade) values(1,4,412824195263214584,200523164754000,'张峰','郑州',85)",
                    "insert into examStudent(flowID,type,idCard,examCard,studentName,location,grade) values(2,4,222224195263214584,200523164754001,'孙朋','大连',56)",
                    "insert into examStudent(flowID,type,idCard,examCard,studentName,location,grade) values(3,6,342824195263214584,200523164754002,'刘明','沈阳',72)",
                    "insert into examStudent(flowID,type,idCard,examCard,studentName,location,grade) values(4,6,100824195263214584,200523164754003,'赵虎','哈尔滨',95)",
                    "insert into examStudent(flowID,type,idCard,examCard,studentName,location,grade) values(5,4,454524195263214584,200523164754004,'杨丽','北京',64)",
                    "insert into examStudent(flowID,type,idCard,examCard,studentName,location,grade) values(6,4,854524195263214584,200523164754005,'王小红','太原',60)"};
            for (String temp: sql) {
                ps = con.prepareStatement(temp);
                ps.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //3、关闭资源
            closeResource(con, ps);
        }
    }


    //与数据库建立连接
    public static Connection getConnection() throws Exception {
        String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String urlWindows = "jdbc:sqlserver://127.0.0.1:1433;databaseName=XSCJ;integratedSecurity=true;";

        Class.forName(driverClass);
        return DriverManager.getConnection(urlWindows);
    }
    //关闭资源
    public static void closeResource(Connection con, Statement ps){
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
