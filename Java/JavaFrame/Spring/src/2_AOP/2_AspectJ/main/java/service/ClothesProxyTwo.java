package service;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by MrLi on 2022/02/13/15:00
 *
 * 增强类第2个类
 */
@Component
@Aspect     //表明生成代理对象
@Order(1)
public class ClothesProxyTwo {
    //相同的切入点抽取：@Pointcut
    @Pointcut(value = "execution(* Dao.Clothes.clothesFactory(..))")
    public void point() {}

    //将以下方法配置为前置通知：@Before
    @Before(value = "point()")
    public void before() {
        System.out.println("在衣服工厂生产衣服之前的一些操作，这是第2个增强类");
    }
}
