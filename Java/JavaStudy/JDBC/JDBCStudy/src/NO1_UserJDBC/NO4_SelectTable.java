package NO1_UserJDBC;

import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author 云梦
 * @create 2021/11/7
 * @Discription 针对于表的查询操作
 */
public class NO4_SelectTable {
    /*
    针对于表的字段名与类的属性名不相同的情况：
        1、必须声明sql时，使用类的属性名来命名字段的别名
        2、使用ResultSetMetDate时，需要使用GetColumnLabel()来替换getColumnName()，来获取列的别名
        补充：如果sql中没有给字段起别名，getColumnName()获取的就是列名
     */
    @Test
    public void selectBoys() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            con = NO3_JDBCUtils.getConnection();
            String sql = "select * from boys where id=?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, 1);

            //执行并返回结果集
            resultSet = ps.executeQuery();
            //处理结果集
            //判断结果集的下一条是否有数据，如果有数据返回true，并且指针下移；如果返回false，指针不下移
            if (resultSet.next()) {

                //获取结果集的各个字段
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int userCP = resultSet.getInt(3);

                //将数据封装为一个对象
                Customer customer = new Customer(id, name, userCP);
                System.out.println(customer);
            }
            else System.out.println("没有找到需要的数据!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            NO3_JDBCUtils.closeResource(con, ps, resultSet);
        }
    }

    //针对于Customers表的通用的查询操作
    public Customer queryForCustomers(String sql, Object ...args) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            con = NO3_JDBCUtils.getConnection();
            ps = con.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }

            resultSet = ps.executeQuery();
            //获取结果集的元数据: ResultSetMetaData
            ResultSetMetaData rsmd = resultSet.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int count = rsmd.getColumnCount();

            if (resultSet.next()){
                Customer customer = new Customer();
                //处理结果集一行数据中的每一个列
                for (int i = 0; i < count; i++) {
                    //获取每个列的值
                    Object columnValue = resultSet.getObject(i + 1);
                    //获取每个列的列名
                    String columnName = rsmd.getColumnName(i + 1);

                    //给customer对象指定的columnName属性，赋值为columnValue：通过反射
                    Field field = Customer.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(customer, columnValue);
                }
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            NO3_JDBCUtils.closeResource(con, ps, resultSet);
        }
        return null;
    }
    @Test
    public void test(){
        NO4_SelectTable table = new NO4_SelectTable();

        String sql = "select id,name from beauty where id=?";
        Customer customer = table.queryForCustomers(sql, 11);
        System.out.println(customer);
    }
}
