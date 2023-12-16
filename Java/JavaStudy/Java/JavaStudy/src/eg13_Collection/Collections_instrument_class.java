package eg13_Collection;

import java.util.ArrayList;
import java.util.Collections;

/*
Collections：操作Collection和Map的工具类

面试题：Collection和Collections的区别？
    Collection：含有List、Map的接口
    Collections：操作Collection接口的工具类
 */
public class Collections_instrument_class {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(12);
        list.add(32);
        list.add(91);
        list.add(42);
        list.add(43);
        list.add(56);
        list.add(10);

        // 排序操作
        // 1、reverse(List)：反转List中元素的顺序
        System.out.println(list);
        Collections.reverse(list);
        System.out.println("1 -> " + list);

        // 2、sort(List)：根据元素的自然顺序对指定 List 集合元素按升序排序
        Collections.sort(list);
        System.out.println("2 -> " + list);

        // 3、shuffle(List)：对List集合元素进行随机排序
        Collections.shuffle(list);
        System.out.println("3 -> " + list);

        // 4、sort(List, Comparator)：根据指定的 Comparator 产生的顺序对 List 集合元素进行排序
        System.out.println("…………略…………");

        // 5、swap(List, int i, int j)：将指定 List 集合中的 i 处元素和 j 处元素进行交换
        Collections.swap(list, 2, 3);
        System.out.println("5 -> " + list);


        //查找、替换
        // 1、Object max(Collection)：根据元素的自然顺序，返回给定集合中的最大元素
        Collections.sort(list);
        Comparable max = Collections.max(list);
        System.out.println("1 -> " + max);

        // 2、Object max(Collection, Comparator)：根据 Comparator 指定的顺序，返回给定集合中的最大值
        System.out.println("…………略…………");

        // 3、Object min(Collection)
        System.out.println("…………略…………");

        // 4、Object min(Collection， Comparator)
        System.out.println("…………略…………");

        // 5、int frequency(Collection, Object)：返回指定集合中指定元素出现的次数
        int i = Collections.frequency(list, 3);
        System.out.println("5 -> " + i);

        // 6、void copy(List dest, List src)：将src中的内容复制到dest中
        // 错误写法
        ArrayList list1 = new ArrayList();
        Collections.copy(list, list1);
        System.out.println("6 -> " + list);

        // 7、boolean replaceAll(List list, Object oldVal, Object newVal)：使用新值替换list中所有旧值
        boolean b = Collections.replaceAll(list1, 1, 3);
        System.out.println(b);
        System.out.println(list);
    }
}
