package eg18_Lambda;

/*
方法引用的使用：
    1、使用情景：当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用
    2、方法引用，本质上是Lambda表达式；而Lambda表达式本质上是函数式接口的实例；所以，方法引用也是函数式接口的实例
    3、使用格式： 类(或对象) :: 方法名

    4、具体分为以下三种情况：
        情况1     对象 :: 非静态方法
        情况2     类 :: 静态方法

        情况3     类 :: 非静态方法

    5、方法引用使用的要求：要求接口中的抽象方法的形参列表和返回值类型与方法引用的方法的形参列表和返回值类型相同(针对情况1和情况2)
 */

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class L2_MethodRef {
    //情况1   对象 :: 实例方法
    //Consumer中的void accept(T t)
    //PrintStream中的void println(T t)
    @Test
    public void test1(){
        Consumer<String> con1 = str -> System.out.println(str);
        con1.accept("Sakura");

        System.out.println("=============================");

        Consumer<String> con2 = System.out::println;
        con2.accept("上杉绘梨衣");
    }

    //Supplier中的T get()
    //Employee中的String getName()
    @Test
    public void test2(){
        Employee em = new Employee(1001, "Tom", 23, 5600);

        Supplier<String> sup1 = () -> em.getName();
        System.out.println(sup1.get());

        System.out.println("=============================");

        Supplier<String> sup2 = em :: getName;
        System.out.println(sup2.get());
    }

    //情况2   类 :: 静态方法
    //Comparator中的compare(T t1, T t2)
    //Integer中的int compare(T t1, T t2)
    @Test
    public void test3(){
        Comparator<Integer> com1 = (t1, t2) -> Integer.compare(t1, t2);
        System.out.println(com1.compare(12, 21));

        System.out.println("=============================");

        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(21, 12));
    }

    //Function中的R apply(T t)
    //Math中的Long round(Double d)
    @Test
    public void test4(){
        Function<Double, Long> fun1 = d -> Math.round(d);
        System.out.println(fun1.apply(3.1415));

        System.out.println("=============================");

        Function<Double, Long> fun2 = Math ::round;
        System.out.println(fun2.apply(2.728));
    }

    //情况3   类 :: 实例方法
    //Comparator中的int compare(T t1, T t2)
    //String中的int t1.compareTo(t2)
    @Test
    public void test5(){
        Comparator<String> com1 = (s1, s2) -> s1.compareTo(s2);
        System.out.println(com1.compare("abc", "abe"));

        System.out.println("=============================");

        Comparator<String> com2 = String :: compareTo;
        System.out.println(com2.compare("bcq", "bcd"));
    }

    //BiPredicate中的boolean test(T t1, T t2)
    //String中的boolean t1.equals(t2)
    @Test
    public void test6(){
        BiPredicate<String, String> bp1 = (t1, t2) -> t1.equals(t2);
        System.out.println(bp1.test("abc", "abc"));

        System.out.println("=============================");

        BiPredicate<String, String> bp2 = String :: equals;
        System.out.println(bp2.test("abc", "def"));
    }

    //Function中的R apply(T t)
    //Employee中的String getName()
    @Test
    public void test7(){
        Employee em = new Employee(1001, "Tom", 23, 5600);

        Function<Employee, String> fun1 = e -> e.getName();
        System.out.println(fun1.apply(em));

        System.out.println("=============================");

        Function<Employee, String> fun2 = Employee :: getName;
        System.out.println(fun2.apply(em));
    }
}