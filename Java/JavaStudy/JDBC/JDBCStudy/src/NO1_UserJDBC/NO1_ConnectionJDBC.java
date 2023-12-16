package NO1_UserJDBC;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

public class NO1_ConnectionJDBC {
    //方式一:
    @Test
    public void test1() throws SQLServerException {
        //1、注册驱动
        SQLServerDriver driver = new SQLServerDriver();

        //2、得到数据库连接
        //jdbc:sqlserver:   SQL server协议
        //localhost:    IP地址
        //192.168.0.8   默认SQL serve端口号
        //Myemployees   数据库
        String url = "jdbc:sqlserver://localhost;user=root;password=31415926535li";
        Properties info = new Properties();
        info.getProperty("user", "root");
        info.getProperty("password", "31415926535li");
        Connection connect = driver.connect(url, info);

        System.out.println(connect);
    }
    //方式二: 对方式一的迭代
    @Test
    public void test2() throws Exception {
        //1、获取drive实现类的对象：使用反射
        Class<?> clazz = Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Driver driver = (Driver) clazz.newInstance();

        //2、提供要连接的数据库
        String url = "jdbc:sqlserver://localhost:1433/girls";

        //3、提供需要的用户和密码
        Properties info = new Properties();
        info.getProperty("user", "root");
        info.getProperty("password", "31415926535li");

        //3、获取连接
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    //方式三：使用DriverManager替换Driver
    @Test
    public void test3() throws Exception {
        //1、获取Driver实现类对象
        Class<?> clazz = Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Driver driver = (Driver) clazz.newInstance();

        //2、提供url、user、password
        String url = "jdbc:sqlserver://localhost:1433/girls";
        String user = "NT Service\\MSSQLSERVER";
        String password = "31415926535li";

        //3、注册驱动
        DriverManager.deregisterDriver(driver);

        //4、获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    //方式四：对方式三的优化；可以只是加载驱动，不用显示的注册驱动
    /*
    相较于方式三，方式四的优化点：
        static {
            try {
                java.sal.DriverManager.registerDriver(new Driver());
            } catch(SQLException E) {
                throw new RuntimeException("Can't register driver!");
            }
        }
     */
    @Test
    public void test4() throws Exception{
        //1、提供url、user、password
        String url = "jdbc:sqlserver://localhost:1433/girls";
        String user = "NT Service\\MSSQLSERVER";
        String password = "31415926535li";

        //2、加载的Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        //4、获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    //方式五：将数据库连接需要的4个基本信息信息声明在配置文件中，通过读取配置文件的方式，获取连接
    /*
    方式5的好处：
        1、实现了数据与代码的分离，实现了解耦
        2、如果需要修改配置文件信息，可以避免程序重新打包
     */
    @Test
    public void test5() throws Exception {
        //1、获取四个基本信息
        InputStream is = NO1_ConnectionJDBC.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties pro = new Properties();
        pro.load(is);

        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        String url = pro.getProperty("url");
        String driverClass = pro.getProperty("driverClass");

        String urlserver = "jdbc:sqlserver://127.0.0.1:1433;databaseName=test;user=sa;password=123abc"; //sqlserver身份连接
        String urlwindows = "jdbc:sqlserver://127.0.0.1:1433;databaseName=girls;integratedSecurity=true;"; //Windows集成模式连接

        //2、加载驱动
        Class.forName(driverClass);

        //3、获取连接
        //Connection con = DriverManager.getConnection(urlwindows);
        Connection con = DriverManager.getConnection(urlserver);
        System.out.println(con);
    }
}
