package eg14_Generic;

import java.util.*;

/*
泛型的使用：
1、jdk5.0新增的特性

2、在集合中使用泛型：
    总结：
        ①、集合接口或集合类在jdk5.0时都修改为带泛型的结构
        ②、在实例化集合类时，可以指明具体的泛型类型
        ③、指明完之后，在集合类或接口中凡是定义类或接口时，内部结构(比如：方法、构造器、属性等)使用到的类的泛型的位置，都指定为实例化的泛型类型
        ④、注意点：泛型的类型必须是类，不能是基本数据类型。需要用到基本数据类型的位置，拿包装类替换
        ⑤、如果实例化时，没有指明泛型的类型。默认类型为java.lang.Object类型

3、如何自定义泛型结构：泛型类、泛型接口；泛型方法

4、通配符的使用
    通配符：？
    类A和类B的父类，G<A>和G<B>是没有关系的，二者共同的父类是：G<?>
    注意：1、对于List<？>，不能向其内部添加数据
         2、对于List<?>，可以向其内部添加唯一值null

    有限制条件的通配符的使用：
        ？ extends A:
                G<? extends A> --> 可以作为 G<A> 和 G<B> 的父类，其中B是A的子类(extends: <=)
                ? super A --> 可以作为 G<A> 和 G<B> 的父类的，其中B是A的父类(super: >=)
 */

public class Application {
    public static void main(String[] args) {
        // 在集合中使用泛型的情况

        // 以ArrayList为例
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        for (String each: list) System.out.println(each);

        System.out.println();

        // 以HashMap为例
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Tom", 12);
        map.put("Tony", 21);
        map.put("Any", 13);

        Set<Map.Entry<String, Integer>> entries = map.entrySet();

        for (Map.Entry<String, Integer> entry : entries) System.out.println(entry);
    }

    public void user(){
        Custom_Generic<String> cg = new Custom_Generic<String>("orderAA", 1001, "order:AA");
        cg.setOrderT("AABBSS");
    }

    public void test1(){
        List<Object> list1 = null;
        List<String> list2 = null;

        List<?> list = null;

        print(list1);
        print(list2);
    }

    public void print(List<?> list){
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }
}
