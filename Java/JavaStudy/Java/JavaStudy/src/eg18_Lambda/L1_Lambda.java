package eg18_Lambda;

/*
一、Lambda表达式的使用：
    1、举例：(o1, o2) -> Integer.compare(o1, o2);
    2、格式：
        ->  :lambda操作符 或 箭头操作符
        ->左边：lambda形参列表（就是接口中的抽象方法的形参列表）
        ->右边：lambda体（就是重写的抽象方法的方法体）
    3、Lambda表达式的本质：作为函数式接口的实例

    4、如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接口

    5、以往用匿名实现类表示的现在都可以用Lambda表达式来写

二、java内置的4大核心函数式接口：
    消费型接口 Consumer<T>       void accept(T, t)       用途：对类型为T的对象应用操作，包含方法：void accept(T t)
    供给型接口 Supplier<T>       T get()                 用途：返回类型为T的对象，包含方法：T get()
    函数型接口 Function<T,R>     R apply(T t)            用途：对类型为T的对象应用操作，并返回结果。结果是R类型的对象。包含方法：boolean test(T t)
    断定型接口 Predicate<T>      boolean test(T t)       用途：确定类型为T的对象是否满足某约束，并返回boolean值。包含方法：boolean test(T t)
 */

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

public class L1_Lambda {
    //Lambda表达式使用一：无参，无返回值
    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱洗澡，皮肤好好！");
            }
        };
        r1.run();

        System.out.println("======================================");

        Runnable r2 = () -> System.out.println("我爱洗澡，皮肤好好！");
    }

    //Lambda表达式使用二：需要一个参数，但无返回值
    @Test
    public void test2(){
        Consumer<String> con1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con1.accept("我们都是小怪兽，有一天要被正义的奥特曼杀死");

        System.out.println("======================================");

        Consumer<String> con2 = (String s) -> System.out.println(s);
        con2.accept("我答应了做你的小怪兽，却没有做到");
    }

    //Lambda表达式使用三：数据类型可以省略，因为可由编译器推断得出，称为“类型推断”
    @Test
    public void test3(){
        Consumer<String> con = (String s) -> System.out.println(s);
        con.accept("我答应了做你的小怪兽，却没有做到");

        System.out.println("======================================");

        Consumer<String> con1 = s -> System.out.println(s);
        con1.accept("我答应了做你的小怪兽，却没有做到");
    }

    //Lambda表达式使用四：数据类型可以省略，因为可由编译器推断得出，称为“类型推断”
    @Test
    public void test4(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare(12, 21));

        System.out.println("======================================");

        Comparator<Integer> com2 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        System.out.println(com2.compare(31, 13));
    }
}
