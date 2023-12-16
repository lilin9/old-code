package com.MrLi.demo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created by MrLi on 2022/03/29/14:04
 */
@DisplayName("Junit5功能测试类")
public class Junit5Test {

    static Stream<String> stringProvider() {
        return Stream.of("A", "B", "C", "D");
    }

    @ParameterizedTest
    @DisplayName("参数化测试")
    @ValueSource(ints = {1,2,3,4,5,6,7})
    void testParameterized(int i) {
        System.out.println(i);
    }

    @ParameterizedTest
    @DisplayName("参数化测试2")
    @MethodSource("stringProvider")
    void testParameterized2(String i) {
        System.out.println(i);
    }

    @DisplayName("前置条件")
    @Test
    void testAssumptions() {
        Assumptions.assumeTrue(true, "结果是true");
        System.out.println("这里是testAssumptions");
    }

    @DisplayName("快速失败")
    @Test
    void testFail() {
        Assertions.fail("This should fail.");
    }

    //如果测试方法超过1s就会异常
    @DisplayName("超时断言")
    @Test
    void testAssertTimeout() {
        Assertions.assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(500));
    }

    @DisplayName("异常断言")
    @Test
    void testAssertThrows() {
        //断定业务逻辑一定会出现异常
        Assertions.assertThrows(
                //扔出断言异常
                ArithmeticException.class, () -> System.out.println(1 % 0)
        );
    }

    //所有的断言都需要成功
    @DisplayName("组合断言")
    @Test
    void testAssertAll() {
        Assertions.assertAll("test",
                () -> Assertions.assertTrue(true && true),
                () -> Assertions.assertEquals(2, 1+1));
    }

    @DisplayName("数组断言")
    @Test
    void testArray() {
        Assertions.assertArrayEquals(new int[]{1,2}, new int[]{1,2});
    }

    //断言：前面的断言失败，后面的代码都不会执行
    @DisplayName("测试简单断言")
    @Test
    void testSimpleAssertions() {
        Assertions.assertEquals(2, 1+1, "业务逻辑计算失败");

        Assertions.assertSame(new Object(), new Object(), "两个对象不一样");
    }

    @DisplayName("测试DisplayName注解")
    @Test
    void testDisplayName() {
        System.out.println(1);
    }

    @Disabled
    @DisplayName("测试方法2")
    @Test
    void test2() {
        System.out.println(2);
    }

    //当方法超出规定时间后，会抛出超时异常
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @DisplayName("测试方法3")
    @Test
    void test3() {
        try {
            Thread.sleep(2000);
            System.out.println(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void testBeforeEach() {
        System.out.println("测试即将开始……");
    }

    @AfterEach
    void testAfterEach() {
        System.out.println("测试即将结束……");
    }

    @BeforeAll
    static void testBeforeAll() {
        System.out.println("所有测试即将开始……");
    }

    @AfterAll
    static void testAfterAll() {
        System.out.println("所有测试即将结束……");
    }
}
