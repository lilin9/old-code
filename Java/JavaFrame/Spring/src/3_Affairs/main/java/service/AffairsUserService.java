package service;

import Dao.AffairsUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by MrLi on 2022/02/14/14:39
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AffairsUserService {
    @Autowired
    private AffairsUserDao userDao;

    //转账
    public void accountMoney() {
            //lucy少100
            userDao.reduceMoney("Lucy", new BigDecimal(100));

            //异常情况
            System.out.println(3/0);

            //mary多100
            userDao.addMoney("Mary", new BigDecimal(100));
    }
}
