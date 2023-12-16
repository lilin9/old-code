package org.soft.base;

import org.junit.jupiter.api.Test;
import org.soft.base.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SsmDemApplicationTests {
    @Autowired
    private CommonUtils commonUtils;

    @Test
    void contextLoads() {
    }
}
