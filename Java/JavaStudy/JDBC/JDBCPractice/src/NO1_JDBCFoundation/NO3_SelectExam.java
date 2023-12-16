package NO1_JDBCFoundation;

import java.sql.*;
import java.util.Scanner;

/**
 * @author 云梦 
 * @create 19:42 2021/11/9
 * @Discription 输入身份证号或者准考证号可以查询到学生的基本信息
 */

public class NO3_SelectExam {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("请选择您要输入的类型:\na:准考证号\nb:身份证\n输入:");
        String temp = input.next();
        if (temp.equals("c")) {
            System.out.println("您的输入有误，请重新进入程序！");
            System.exit(0);
        }

        NO3_SelectExam selectExam = new NO3_SelectExam();
        String card;
        ExamStudent student = null;
        if (temp.equals("a")){
            System.out.print("请输入准考证号码：");
            card = input.next();
            if (card.length() != 15) {
                System.out.println("查无此人！请重新进入程序。");
                System.exit(0);
            }
            else student =  selectExam.selectIdCard(card);
        }
        if (temp.equals("b")){
            System.out.print("请输入身份证号码：：");
            card = input.next();
            if (card.length() != 18) {
                System.out.println("查无此人！请重新进入程序。");
                System.exit(0);
            }
            else student =  selectExam.selectIdCard(card);
        }

        if (student != null){
            System.out.println("==========查询结果==========");
            System.out.print("流水号:   " + student.getFlowID());
            System.out.print("\n四级/六级：" + student.getType());
            System.out.print("\n身份证号码:" + student.getIdCard());
            System.out.print("\n准考证号码:" + student.getExamCard());
            System.out.print("\n学生姓名： " + student.getStudentName());
            System.out.print("\n区域：    " + student.getLocation());
            System.out.print("\n成绩：    " + student.getGrade());
        }
        else System.out.println("查无此人！请重新进入程序。");
    }



    //根据身份证号码查询学生信息
    public ExamStudent selectIdCard(String Card) {
        ExamStudent student = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            //1、获取连接
            con = getConnection();

            //2、预编译sql语句，返回PreparedStatement的实例
            //判断Card是身份证号码还是准考证号码
            String sql;
            if (Card.length() == 18) sql = "select flowID,type,idCard,examCard,studentName,location,grade from examStudent where idCard=?";
            else sql = "select flowID,type,idCard,examCard,studentName,location,grade from examStudent where examCard=?";

            ps = con.prepareStatement(sql);

            //3、填充占位符
            ps.setObject(1, Card);

            //4、执行并返回结果集
            resultSet = ps.executeQuery();
            //处理结果集
            int flowID;
            if (resultSet.next()){
                //获取结果集的各个字段
                flowID = resultSet.getInt(1);
                int type = resultSet.getInt(2);
                String idCard = resultSet.getString(3);
                String examCard = resultSet.getString(4);
                String studentName = resultSet.getString(5);
                String location = resultSet.getString(6);
                int grade = resultSet.getInt(7);
                //将数据封装为一个对象
                student = new ExamStudent(flowID, type, idCard, examCard, studentName, location, grade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            closeResource(con, ps, resultSet);
        }
        return student;
    }

    //与数据库建立连接
    public static Connection getConnection() throws Exception {
        String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String urlWindows = "jdbc:sqlserver://127.0.0.1:1433;databaseName=XSCJ;integratedSecurity=true;";

        Class.forName(driverClass);
        return DriverManager.getConnection(urlWindows);
    }

    //关闭资源
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
