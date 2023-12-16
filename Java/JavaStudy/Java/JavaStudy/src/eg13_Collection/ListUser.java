package eg13_Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUser {
    // 三、Collection里的方法的使用
    public void methods(){
        ArrayList list = new ArrayList();

        list.add(123);
        list.add(345);
        list.add("Love");
        list.add(new Person("Amy", 12));
        list.add(new Person("Amy", 12));

        // 1、void add(int index, Object ele)：在index位置插入ele元素

        // 2、boolean addAll(int index, Collection ele)
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        list.addAll(list1);
        System.out.println(list.size());

        // 3、Object get(int index)
        System.out.println(list.get(1));

        // 4、int indexOf(Object obj)：返回obj在集合中首次出现的位置
        int index = list.indexOf(new Person("Amy", 12));
        System.out.println(index);

        // 5、int LastIndexOf(Object obj)：返回obj在当前集合中最后出现的位置

        // 6、Object remove(int index)：移除指定index位置的元素，并返回此元素
        Object obj = list.remove(0);
        System.out.println(obj);
        System.out.println(list);

        // 7、Object set(int index, Object ele)：设置指定index位置的元素为ele
        list.set(1, "music");
        System.out.println(list);

        // 8、List subList(int fromIndex, int toIndex)：返回从fromIndex到toIndex位置的左闭右开区间的子集合
        List subList = list.subList(2, 5);
        System.out.println(subList);
    }
}
