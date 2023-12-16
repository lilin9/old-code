package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private final static ThreadLocal<Connection> cons = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(inputStream);
            // 创建 数据库连接 池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null,说明获取连接失败<br/>有值就是获取连接成功
     */
    public static Connection getConnection(){
        Connection conn = cons.get();

        if (conn == null) {
            try {
                conn = dataSource.getConnection();

                //保存到 ThreadLocal 对象中，供以后的JDBC操作使用
                cons.set(conn);

                //设置事务为手动管理
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * @author MrLi
     * @create 2022/1/18 17:12
     * @description 提交事务并且关闭释放连接
     */
    public static void commitAndClose() {
        Connection conn = cons.get();

        //如果不为空，说明之前使用过连接，操作过数据库
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        //因为Tomcat服务器底层使用了线程池技术，故一定要在最后执行 remove 操作，否则会报错
        cons.remove();
    }

    /**
     * @author MrLi
     * @create 2022/1/18 17:12
     * @description 回滚事务并且关闭释放连接
     */
    public static void rollbackAndClose() {
        Connection conn = cons.get();

        //如果不为空，说明之前使用过连接，操作过数据库
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        //因为Tomcat服务器底层使用了线程池技术，故一定要在最后执行 remove 操作，否则会报错
        cons.remove();
    }

    /*
      关闭连接，放回数据库连接池

    public static void close(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    */
}