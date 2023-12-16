package Dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import utils.JDBCUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by MrLi on 2022/01/09/13:56
 */
public abstract class BaseDao {
    private final QueryRunner queryRunner = new QueryRunner();

    public int update(String sql, Object ...args) {
        Connection con = JDBCUtils.getConnection();

        try {
            return queryRunner.update(con, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con);
        }
        return -1;
    }

    public <T>T queryForOne(Class<T> type, String sql, Object ...args) {
        Connection con = JDBCUtils.getConnection();

        try {
            return  queryRunner.query(con, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con);
        }
        return null;
    }

    public <T>List<T> queryForList(Class<T> type, String sql, Object ...args) {
        Connection con = JDBCUtils.getConnection();
        try {
            return (List<T>) queryRunner.query(con, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con);
        }
        return null;
    }
}
