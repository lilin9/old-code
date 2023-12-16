package service;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by MrLi on 2022/02/13/13:43
 *
 * 增强类
 */
@Component
@Aspect     //表明生成代理对象
@Order(0)
public class ClothesProxy {
    //相同的切入点抽取：@Pointcut
    @Pointcut(value = "execution(* Dao.Clothes.clothesFactory(..))")
    public void point() {}

    //将以下方法配置为前置通知：@Before
    @Before(value = "point()")
    public void before() {
        System.out.println("在衣服工厂生产衣服之前的一些操作");
    }

    //将以下方法配置为后置通知：@AfterReturning
    @AfterReturning(value = "point()")
    public void afterReturning() {
        System.out.println("在衣服工厂生产衣服之前的一些操作");
    }
}
