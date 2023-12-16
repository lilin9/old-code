package NO4_BaseDAO;

import java.sql.Connection;
import java.util.List;

//用于规范针对于customers表的常用操作
public interface CustomerDAO {
    /**
     * @author 云梦
     * @create 15:14 2021/11/12
     * @Discription 将cus对象添加到数据库中
     */
    void insert(Connection con, Customer cus);
    /**
     * @author 云梦
     * @create 15:16 2021/11/12
     * @Discription 针对指定的id，删除表中的一条记录
     */
    void deleteById(Connection con, int id);
    /**
     * @author 云梦
     * @create 15:17 2021/11/12
     * @Discription 针对于内存中的cus对象，去修改数据表中指定的记录
     */
    void upDate(Connection con, Customer cus);
    /**
     * @author 云梦 
     * @create 15:19 2021/11/12
     * @Discription 针对指定的id查询客户cus
     */
    Customer getCustomerById(Connection con, int id);
    /**
     * @author 云梦
     * @create 15:20 2021/11/12
     * @Discription 查询表中的所有记录构成的集合
     */
    List<Customer> getAll(Connection con);
    /**
     * @author 云梦
     * @create 15:22 2021/11/12
     * @Discription 返回数据表中的所有条目数
     * @return
     */
    int getCount(Connection con);
}
