package service;

import dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by MrLi on 2022/02/11/15:35
 */

@Repository
public class BookService {
    @Value(value = "《三体》")
    private String name;

    /*
    //定义dao类型属性
    //**不需要添加set方法
    @Autowired                          //根据属性进行注入
    @Qualifier(value = "bookDaoImpl1")  //根据名称进行注入
    private BookDao bookDao;
    */

//    @Resource                       //默认根据类型进行注入
    @Resource(name = "bookDaoImpl1")  //根据名称进行注入
    BookDao bookDao;

    public void method() {
        bookDao.method();
        System.out.println("这里是BookService类");
    }
}
