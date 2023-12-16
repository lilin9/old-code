package service;

import dao.Impl.UserDaoImpl;
import dao.UserDao;

/**
 * Created by MrLi on 2022/02/10/13:21
 */
public class UserService {
    //spring中调用 UserDaoImpl 的方法
    //1、创建 UserDao 属性，生成 set() 方法
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    //2、调用方法
    public void test() {
        System.out.println("UserService");
        userDao.userDao();
    }
}
