package NO6_ApacheDbUtils;

import NO0_JDBCUtils.JDBCUtils;
import NO4_BaseDAO.Customer;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Handler;

/*
commons-dbutils 是Apache组织提供的一个开源JDBC工具类库，封装了针对于数据库的增删改查操作
 */
public class QueryRunning {
    @Test
    public void insert() {
        Connection con = null;
        int update = 0;
        try {
            QueryRunner runner = new QueryRunner();
            con = JDBCUtils.getConnection();
            String sql = "insert into customer(name, email, birth) values(?,?,?)";

            update = runner.update(con, sql, "龙葵", "longyang@qq.com", "1111-11-12");

            System.out.println(update);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(con, null);
        }
    }

    //BeanHandler：是ResultSetHandler接口的实现类，用于封装表中的一条记录
    @Test
    public void select1(){
        Connection con = null;
        try {
            QueryRunner runner = new QueryRunner();
            con = JDBCUtils.getConnection();
            String sql = "select * from customer where id=?";

            BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);

            Customer customer = runner.query(con, sql, handler, 3);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(con, null);
        }
    }

    //BeanListHandler：是ResultSetHandler接口的实现类，用于封装表中的多条记录
    @Test
    public void select2(){
        Connection con = null;
        try {
            QueryRunner runner = new QueryRunner();
            con = JDBCUtils.getConnection();
            String sql = "select * from customer where id<=?";

            BeanListHandler<Customer> handler = new BeanListHandler<>(Customer.class);

            List<Customer> list = runner.query(con, sql, handler, 3);
            list.forEach(System.out :: println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(con, null);
        }
    }

    //ScalarHandler：用于查询特殊值
    @Test
    public void select3(){
        Connection con = null;
        try {
            QueryRunner runner = new QueryRunner();
            con = JDBCUtils.getConnection();
            String sql = "select count(*) from customer";

            ScalarHandler handler = new ScalarHandler();

            Long count = (Long) runner.query(con, sql, handler);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(con, null);
        }
    }

    //自定义ResultSetHandler的实现类
    @Test
    public void select4(){
        Connection con = null;
        try {
            QueryRunner runner = new QueryRunner();
            con = JDBCUtils.getConnection();
            String sql = "select id,name,email,birth from customer where id = ?";

            ResultSetHandler<Customer> handler = new ScalarHandler<>(){
                @Override
                public Customer handle(ResultSet rs) throws SQLException {
                    if (rs.next()){
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String email = rs.getString("email");
                        Date birth = rs.getDate("birth");

                        return new Customer(id, name, email, birth);
                    }
                    return null;
                }
            };

            Customer customer = runner.query(con, sql, handler, 7);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.close(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
