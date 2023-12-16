package eg13_Collection;

import java.util.*;

/*
    面试题：比较ArrayList、LinkedList、Vector三者的异同
            相同点：三者都实现了List接口，储存数据的特点相同；储存有序的、可重复的数据；底层都是使用Object[]储存
            不同点：↑
 */

public class CollectionUser {
    // 三、Collection里的方法的使用
    public void methods(){
        // 向Collection接口的实现类的对象中添加数据obj时，要求obj所在类要重写equals()

        Collection coll = new ArrayList();

        // 1、add(Object e)
        coll.add("AA");
        coll.add("BB");
        coll.add(123);
        coll.add(new Date());

        // 2、size()
        System.out.println(coll.size());

        // 3、addAll(Collection coll1)
        Collection coll1 = new ArrayList();
        coll1.add("C");
        coll1.add(12345);
        coll.addAll(coll1);
        System.out.println(coll.size());

        // 4、isEmpty()：判断当前集合是否为空
        System.out.println(coll.isEmpty());

        // 5、clear()：清空集合元素
        coll.clear();
        System.out.println(coll.size());

        // 6、contains(Object obj)：判断当前集合中是否包含obj
        // contains()在判断时会调用obj对象所在类的equals()
        coll1.add(new Person("Tom", 20));
        boolean contains = coll1.contains(12345);
        System.out.println(contains);
        System.out.println(coll1.contains(new Person("Tom", 20)));

        // 7、containsAll()：判断形参coll1中的所有元素是否都存在于当前集合中
        System.out.println(coll1.containsAll(coll));

        // 8、remove(Object obj)
        System.out.println(coll1);
        coll1.remove(12345);
        System.out.println(coll1);

        // 9、removeAll(Collection coll1)
        coll.add(new Person("Tom", 20));
        coll.add("B");
        coll1.add(new Person("Tonny", 12));
        System.out.println(coll1);
        coll1.removeAll(coll);
        System.out.println(coll1);

        // 10、retainAll(Collection coll1)：交集，获取当前集合和coll1集合的交集，并返回给当前集合
        coll.add("C");
        coll.add("M");
        coll.retainAll(coll1);
        System.out.println(coll);

        // 11、equals(Object obj)

        // 12、hashCode()：返回当前对象的hash值
        System.out.println(coll1.hashCode());

        // 13、toArray()：集合 -> 数组
        Object[] array = coll1.toArray();
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }

        //拓展：asList() 数组 -> 集合
        List<String> list = Arrays.asList(new String[]{"AA", "BB", "CC", "DD", "FF"});
        System.out.println("\n" + list);

        System.out.println("\n======================\n");

        // 14、iterator()：返回iterator接口的实例，用于遍历集合元素。
        Iterator iterator = coll1.iterator();
        // 迭代器方式一
        //System.out.println(iterator.next());
        // 迭代器方式二：推荐使用
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
