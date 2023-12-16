package eg13_Collection;

/*
一、集合框架的概述：
    1、集合、数组都是对多个数据进行存储(主要指内存层面的存储)操作的结构，简称Java容器
    2.1、数组在存储多个数据方面的特点：
        --> 一旦初始化以后，其长度就确定了
        --> 数组一旦定义好，其元素的类型也就确定了，也就只能操作指定类型的数据
    2.2、数组在存储多个数据方面的缺点：
        --> 一旦初始化之后，其长度不可修改
        --> 数组中提供的方法非常有限，对于添加、删除、插入数据等操作，非常不便，同时效率不高
        --> 获取数组中实际元素的个数的需求，数组没有现成的属性或者方法可以使用
        --> 数组储存数据的特点：有序、可重复。对于无序、不可重复的需求，不能满足。

二、集合框架：
    --> Collection接口：单列集合，用来储存一个一个的对象
        --> List接口：储存有序的、可重复的的数据(动态数组，替换原有的数组)
            --> ArrayList：作为List接口的主要实现类；线程不安全，效率高
            --> LinkedList：对于频繁的插入、删除操作，使用此类效率比ArrayList高；底层使用双向链表储存
            --> Vector：作为List接口的古老实现类；线程安全，效率低

        --> Set接口：储存无序的、不可重复的数据
            --> HashSet：作为Set接口的主要实现类；线程不安全的；可以储存null值
                --> LinkedHashSet：作为HashSet的子类；遍历其内部数据时，可以按照添加的顺序遍历；由于双向链表的存在，
                                   所以对于频繁的遍历操作，LinkedHashSet效率高于HashSet
            --> TreeSet：可以按照添加对象的指定属性，进行排序
        要求：1、向Set中添加数据，其所在的类一定要重写hashCode()和equals()
             2、重写的hashCode()和equals()尽可能保持一致性：相等的对象必须具有相等的散列码

    -->　Map接口：双列集合，用来储存一对一对(key - value)的对象
        --> HashMap：作为Map的主要实现类；线程不安全的，效率高；可以储存null的key和value
            --> LinkedHashMap：保证在遍历map元素时，可以按照添加的顺序实现遍历。对于频繁的遍历操作，此类执行效率高于HashMap
                          原因：在原有的HashMap底层结构上，添加了一对指针，指向前一个和后一个元素
        --> TreeMap：保证按照添加的key-value对进行排序，实现排序遍历。此时考虑key的自然排序或定制排序
        --> HashTable：作为古老的实现类；线程安全的，效率低；不能储存null的key和value
            --> Properties：常用来处理配置文件。key和value都是String类型
 */

public class Application {
    public static void main(String[] args) {
        //CollectionUser coll = new CollectionUser();
        //coll.methods();

        //ListUser list = new ListUser();
        //list.methods();

        //SetUser setUser = new SetUser();
        //setUser.Linked_Hash_Set();
        //setUser.Tree_Set();

        MapThreadUser mtu = new MapThreadUser();
        mtu.methods3();
    }
}