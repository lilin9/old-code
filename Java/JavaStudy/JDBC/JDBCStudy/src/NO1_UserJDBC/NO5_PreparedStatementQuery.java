package NO1_UserJDBC;

import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author 云梦
 * @create 17:46 2021/11/8
 * @Discription 使用PreparedStatement实现针对于不同表的通用的查询操作
 */
public class NO5_PreparedStatementQuery {
    @Test
    public void test(){
        String sql = "select id,boyName name, userCP from boys where id=?";
        Customer boy = getInstance(Customer.class, sql, 3);
        System.out.println(boy);

        String sql1 = "select id,name,sex,borndate,phone,boyfriend_id from beauty where id=?";
        Beauty beauty = getInstance(Beauty.class, sql1, 3);
        System.out.println(beauty);
    }

    public <T>T getInstance(Class<T> clazz, String sql, Object... args) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            con = NO3_JDBCUtils.getConnection();
            ps = con.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            resultSet = ps.executeQuery();
            //获取结果集的元数据: ResultSetMetaData
            ResultSetMetaData rsmd = resultSet.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int count = rsmd.getColumnCount();

            if (resultSet.next()) {
                T t = clazz.newInstance();
                //处理结果集一行数据中的每一个列
                for (int i = 0; i < count; i++) {
                    //获取每个列的值
                    Object columnValue = resultSet.getObject(i + 1);
                    //获取每个列的列名
                    String columnName = rsmd.getColumnLabel(i + 1);

                    //给customer对象指定的columnName属性，赋值为columnValue：通过反射
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            NO3_JDBCUtils.closeResource(con, ps, resultSet);
        }
        return null;
    }
}
