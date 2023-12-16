package Demo;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.util.Properties;

public class Demo {
    public static void main(String[] args) throws SQLServerException {
        //1、注册驱动
        SQLServerDriver driver = new SQLServerDriver();

        //2、得到数据库连接
        //jdbc:sqlserver:   SQL server协议
        //localhost:    IP地址
        //192.168.0.8   默认SQL serve端口号
        //Myemployees   数据库
        String url = "jdbc:sqlserver://localhost:192.168.0.8/Myemployees";
        Properties info = new Properties();
        info.getProperty("user", "root");
        info.getProperty("password", "31415926535li");
        Connection connect = driver.connect(url, info);

        System.out.println(connect);
    }
}
