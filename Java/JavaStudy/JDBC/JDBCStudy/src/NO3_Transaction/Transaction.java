package NO3_Transaction;

import NO0_JDBCUtils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 云梦
 * @create 12:17 2021/11/11
 * @Discription
 *
 * 1、数据库事务：一组逻辑操作单元，使数据从一种状态变换到另一种状态
 *      ->一组逻辑操作单元：一个或多个DML操作
 * 2、事务处理的原则：保证所有事务都作为一个工作单元来执行，即使出现了故障，都不能改变这种执行方式。当在一个事务中
 *    执行多个操作时，要么所有的事务都被提交(commit)，那么这些修改就永久的保存下来；要么数据库管理系统将放弃所做
 *    的所有修改，整个事务回滚(rollback)到最初状态.
 *
 * 3、数据一旦提交，就不可回滚
 * 4、哪些操作会导致数据的自动提交？
 *      ->DDL操作一旦执行，都会自动提交 (set autocommit = false 对DDL操作失效)
 *      ->DML默认情况下，一旦执行，就会自动提交 (可以通过 set autocommit = false的方式取消DML操作的自动提交)
 *      ->默认在关闭连接时，会自动的提交数据
 *
 * 5、事务的ACID属性：
 *      原子性(Atomicity)
 *      一致性(Consistency)
 *      隔离性(Isolation)
 *      持久性(Durability)
 */

public class Transaction {
    //使用事务后的转账操作
    @Test
    public void upDataTest(){
        Connection con = null;
        try {
            con = JDBCUtils.getConnection();

            //1、取消数据的自动提交
            con.setAutoCommit(false);

            String sql1 = "update user_table set money = money-10 where name=?";
            upDate(con,sql1, "Jerry");

            //模拟网络故障
            System.out.println(10/0);

            String sql2 = "update user_table set money = money + 10 where name = ?";
            upDate(con,sql2, "Tom");

            //2、提交数据
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //3、回滚数据
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            //4、关闭外面getConnection()
            JDBCUtils.closeResource(con, null);
        }
    }

    public void upDate(Connection con, String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            //1、预编译sql语句，返回PreparedStatement的实例
            ps = con.prepareStatement(sql);

            //2、填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }

            //3、执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //4、资源的关闭
            JDBCUtils.closeResource(null, ps);
        }
    }
}