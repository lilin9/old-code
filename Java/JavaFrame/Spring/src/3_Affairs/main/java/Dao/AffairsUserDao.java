package Dao;

import java.math.BigDecimal;

/**
 * Created by MrLi on 2022/02/14/14:37
 */
public interface AffairsUserDao {
    void addMoney(String username, BigDecimal money);
    void reduceMoney(String username, BigDecimal money);
}
