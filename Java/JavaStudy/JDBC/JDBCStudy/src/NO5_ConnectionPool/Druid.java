package NO5_ConnectionPool;

import NO0_JDBCUtils.JDBCUtils;
import NO4_BaseDAO.Customer;
import NO4_BaseDAO.CustomerDAOImpl;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class Druid {
    /**
     * @author 云梦‘
     * @create 12:58 2021/11/13
     * @Discription 使用Druid数据库连接池技术
     */
    private static DataSource source;
    static {
        try {
            Properties pros = new Properties();

            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            pros.load(is);


            source = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection druidGetConnection() throws SQLException {
        return source.getConnection();
    }

    //测试
    @org.junit.jupiter.api.Test
    void insert() {
        CustomerDAOImpl dao = new CustomerDAOImpl();
        Connection con = null;
        try {
            con = Druid.druidGetConnection();
            Customer cus = new Customer(7, "紫萱", "zixuan@qq.com", new Date(1234567L));
            dao.insert(con, cus);

            System.out.println("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(con, null);
        }
    }
}
