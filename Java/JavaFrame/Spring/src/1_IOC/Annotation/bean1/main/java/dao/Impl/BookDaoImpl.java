package dao.Impl;

import dao.BookDao;
import org.springframework.stereotype.Repository;

/**
 * Created by MrLi on 2022/02/11/16:13
 */
@Repository(value = "bookDaoImpl1")
public class BookDaoImpl implements BookDao {
    @Override
    public void method() {
        System.out.println("这里是 BookDao 的实现类");
    }
}
