package eg18_Lambda;

/*
一、构造器引用
    和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致。
    抽象方法的返回值类型即为构造器所属的类的类型

二、数组引用
    可以把数组当成一个特殊的类，写法则与构造器引用一致
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

public class L3_ConstructorRef {
    //构造器引用
    //Supplier中的T get()
    @Test
    public void test1(){
        Supplier<Employee> su1 = () -> new Employee();
        System.out.println(su1.get());

        System.out.println("=============================");

        Supplier<Employee> su2 = Employee :: new;
        System.out.println(su2.get());
    }

    //数组引用
    //Function中的R apply()
    @Test
    public void test2(){
        Function<Integer, String[]> fun1 = length -> new String[length];
        String[] apply = fun1.apply(5);
        System.out.println(Arrays.toString(apply));

        System.out.println("=============================");

        Function<Integer, String[]> fun2 = String[] :: new;
        String[] apply1 = fun2.apply(10);
        System.out.println(Arrays.toString(apply1));
    }
}
