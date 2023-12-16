package Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by MrLi on 2022/02/14/14:40
 */
@Repository
public class AffairsUserDaoImpl implements AffairsUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addMoney(String username, BigDecimal money) {
        String sql = "update `s_user` set `money`=`money`+? where username=?";
        jdbcTemplate.update(sql, money, username);
    }

    @Override
    public void reduceMoney(String username, BigDecimal money) {
        String sql = "update `s_user` set `money`=`money`-? where username=?";
        jdbcTemplate.update(sql, money, username);
    }
}
