package com.lilin.springsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringSecurityApplicationTests {
    @Test
    void contextLoads() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String lockPassword = passwordEncoder.encode("123abc");
        boolean result = passwordEncoder.matches("123abc", lockPassword);

        System.out.println("lockPassword --> " + lockPassword);
        System.out.println("result --> " + result);
    }
}
