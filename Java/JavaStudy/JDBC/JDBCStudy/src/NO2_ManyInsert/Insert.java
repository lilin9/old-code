package NO2_ManyInsert;

import NO0_JDBCUtils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author 云梦 
 * @create 15:45 2021/11/10
 * @Discription 向goods表中插入20000条数据
 *
 * create table goods(
 * id int primary key,
 * name varchar(30)
 * )
 *
 *方式一：使用Statement
 * Connection con = JDBCUtils.getConnection();
 * Statement st = con.createStatement();
 * for (int i=1; i<=10000; i++){
 *     String sql = "insert into goods(name) values('name_" + i +"')";
 *     se.execute(sql);
 * }
 */

public class Insert {
    //方式二：使用prepareStatement
    @Test
    public void insert1() {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();

            con = JDBCUtils.getConnection();
            String sql = "insert into goods(name) values(?)";
            ps = con.prepareStatement(sql);

            for (int i = 1; i <= 10000; i++) {
                ps.setObject(1, "name_" + i);

                ps.execute();
            }

            long end = System.currentTimeMillis();
            System.out.println("time = " + (end-start)); //2505
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(con, ps);
        }
    }

    //方式三：addBatch()、executeBatch()、clearBatch()
    @Test
    public void insert2(){
        Connection con = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();

            con = JDBCUtils.getConnection();
            String sql = "insert into goods(name) values(?)";
            ps = con.prepareStatement(sql);

            for (int i = 1; i <= 1000000; i++) {
                ps.setObject(1, "name_" + i);

                //1、”攒“sql
                ps.addBatch();

                if (i % 500 == 0){
                    //2、执行batch
                    ps.executeBatch();

                    //3、清空batch
                    ps.clearBatch();
                }
            }

            long end = System.currentTimeMillis();
            System.out.println("time = " + (end - start)); //85232
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(con, ps);
        }
    }

    //方式四(最终版)：设置连接不允许自动提交数据
    @Test
    public void insert3(){
        Connection con = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();

            con = JDBCUtils.getConnection();

            //设置不允许自动提交数据
            con.setAutoCommit(false);

            String sql = "insert into goods(name) values(?)";
            ps = con.prepareStatement(sql);

            for (int i = 1; i <= 1000000; i++) {
                ps.setObject(1, "name_" + i);

                //1、”攒“sql
                ps.addBatch();

                if (i % 500 == 0){
                    //2、执行batch
                    ps.executeBatch();

                    //3、清空batch
                    ps.clearBatch();
                }
            }

            //允许提交数据
            con.commit();

            long end = System.currentTimeMillis();
            System.out.println("time = " + (end - start)); //16791
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(con, ps);
        }
    }
}
