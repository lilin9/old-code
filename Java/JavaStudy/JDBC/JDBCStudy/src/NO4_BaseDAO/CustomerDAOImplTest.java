package NO4_BaseDAO;

import NO0_JDBCUtils.JDBCUtils;
import NO5_ConnectionPool.Druid;

import java.sql.Connection;
import java.util.Date;

class CustomerDAOImplTest {

    CustomerDAOImpl dao = new CustomerDAOImpl();

    @org.junit.jupiter.api.Test
    void insert() {
        Connection con = null;
        try {
            con = Druid.druidGetConnection();
            Customer cus = new Customer(5, "紫萱", "zixuan@qq.com", new Date(1234567L));
            dao.insert(con, cus);

            System.out.println("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(con, null);
        }
    }

    @org.junit.jupiter.api.Test
    void deleteById() {
    }

    @org.junit.jupiter.api.Test
    void upDate() {
    }

    @org.junit.jupiter.api.Test
    void getCustomerById() {
        Connection con = null;
        try {
            con = JDBCUtils.getConnection();
            Customer cus = dao.getCustomerById(con, 1);
            System.out.println(cus);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(con, null);
        }
    }

    @org.junit.jupiter.api.Test
    void getAll() {
    }

    @org.junit.jupiter.api.Test
    void getCount() {
    }
}