package Dao;

import org.springframework.stereotype.Component;

/**
 * Created by MrLi on 2022/02/13/13:42
 *
 * 被增强类
 */
@Component
public class Clothes {
    public void clothesFactory() {
        System.out.println("衣服工厂生产衣服");
    }
}
