package com.tianmao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TianMaoApplicationTests {

    @Test
    void contextLoads() {
        String str = "pid=10000019&pid=10000013";
        String[] result = str.split("&");
        for (String item : result) {
            System.out.println(item);
        }
    }

}
