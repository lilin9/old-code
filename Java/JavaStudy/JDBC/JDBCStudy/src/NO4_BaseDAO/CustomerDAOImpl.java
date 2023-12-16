package NO4_BaseDAO;

import java.sql.Connection;
import java.util.List;

public class CustomerDAOImpl extends BaseDAO implements CustomerDAO {
    @Override
    public void insert(Connection con, Customer cus) {
        String sql = "insert into customer(id,name,email,birth) values(?,?,?,?)";
        upDate(con, sql, cus.getName(), cus.getEmail(), cus.getBirth());
    }

    @Override
    public void deleteById(Connection con, int id) {
        String sql = "delete from customer where id=?";
        upDate(con, sql, id);
    }

    @Override
    public void upDate(Connection con, Customer cus) {
        String sql = "update customer set name=?,email=?,birth=? where id=?";
        upDate(con, sql, cus.getName(), cus.getEmail(), cus.getBirth(), cus.getId());
    }

    @Override
    public Customer getCustomerById(Connection con, int id) {
        String sql = "select id,name,email,birth from customer where id=?";
        return getInstance(con, Customer.class, sql, id);
    }

    @Override
    public List<Customer> getAll(Connection con) {
        String sql = "select id,name,email,birth from customer";
        return getForList(con, Customer.class, sql);
    }

    @Override
    public int getCount(Connection con) {
        String sql = "select count(*) from customer";
        return getValue(con, sql);
    }
}
