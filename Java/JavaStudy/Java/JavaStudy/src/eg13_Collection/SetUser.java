package eg13_Collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class SetUser {
    /*
    一、Set：储存无序的、不可重复的数据（以HashSet为例说明）
        1、无序性：不等于随机性。储存的数据在底层数组中并非按照数组索引的顺序添加，而是根据数据的哈希值决定的
        2、不可重复性：保证添加的元素按照equals()判断时，不能返回true。即：相同的元素只能有一个

    二、添加元素的过程：以HashSet为例
        向HashSet中添加元素a，首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，此哈希值接着通过某种算法
        计算出在HashSet底层数组中的存放位置（即为：索引位置），判断数组此位置上是否已经有元素：
            如果此位置上没有其他元素，则a元素添加成功。 --> 情况1
            如果此位置上有其他元素b（或以链表形式存在多个元素），则比较元素a和元素b的hash值：
                如果hash值不相同，则元素a添加成功。 --> 情况2
                如果hash值相同，进而需要调用元素a所在类的equals()方法：
                    equals()返回true，元素a添加失败
                    equals()返回false，则元素a添加成功。 --> 情况3

        对于添加成功的情况2和情况3而言：元素a，与已经存在指定索引位置上的数据以链表方式储存。（七上八下）
        jdk7：元素a放到数组中，指向原来的元素
        jdk8：原来的元素在数组中，指向元素a
     */

    // LinkedHashSet的使用
    public void Linked_Hash_Set(){
        LinkedHashSet set = new LinkedHashSet();
        set.add(123);
        set.add(456);
        set.add(789);
        set.add("love");
        set.add("good");
        set.add(new Person("Tonny", 21));
        set.add(new Person("Tonny", 21));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) System.out.println(iterator.next());
    }

    // TreeSet的使用
    public void Tree_Set(){
        /*
        1、向TreeSet中添加数据，要求是相同类的对象提供
        2、两种排序方法：自然排序(实现Comparable接口) 和 定制排序(Comparator)

        3、自然排序中，比较两个对象是否相同的标准是：compareTo() 返回0，不再是equals()
        4、定制排序中，比较两个对象是否相同的标准是：compare() 返回0，不再是equals()
         */

        /*
        TreeSet set = new TreeSet();
        set.add(new Person("Tom", 12));
        set.add(new Person("Tonny", 21));
        set.add(new Person("Mike", 33));
        set.add(new Person("Amy", 12));
        set.add(new Person("Amy", 22));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) System.out.println(iterator.next());
         */

        Comparator com = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                // 按照年龄从小到大排序
                if (o1 instanceof Person && o2 instanceof Person){
                    Person p1 = (Person) o1;
                    Person p2 = (Person) o2;

                    return Integer.compare(((Person) o1).getAge(), ((Person) o2).getAge());
                }
                throw new RuntimeException("输入的类型不匹配！");
            }
        };
        TreeSet set = new TreeSet(com);
        set.add(new Person("Tom", 12));
        set.add(new Person("Tonny", 21));
        set.add(new Person("Mike", 33));
        set.add(new Person("Amy", 12));
        set.add(new Person("Amy", 22));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) System.out.println(iterator.next());
    }
}