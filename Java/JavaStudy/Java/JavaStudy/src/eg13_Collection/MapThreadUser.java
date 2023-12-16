package eg13_Collection;

/*
一、Map结构的理解：
    Map中的key：无序的、可重复的，使用Set储存所有的key --> key所在的类要重写equals()和hashCode() (以HashMap为例)
    Map中的value：无序的、可重复的，使用Collection储存所有的value --> value所在的类要重写equals()
    一个键值对：key-value构成了一个entry对象
    Map中的entry：无序的、不可重复的，使用Set储存所有的entry

二、HashMap的底层实现原理（以jdk7为例）：
    1、HashMap map = new HashMap():
        在实例化以后，底层创建了长度是16的一维数组Entry[] table.

    2、map.put(key1, value1):
        首先，调用key1所在类的hashCode()计算key1哈希值，此哈希值经过某种算法计算以后，得到Entry数组中的存放位置。
            如果此位置上的数据为空，此时的key1-value1添加成功。 --> 情况1
            如果此位置上的数据不为空(意味着此位置上存在一个或多个数据(以链表形式存在))，比较key1和已经存在的一个或者多个数据的哈希值：
                如果key1的哈希值与已经存在的数据的哈希值都不相同，此时key1-value1添加成功。 --> 情况2
                如果key1的哈希值和已经存在的某一个数据(key2-value2)的哈希值相同，继续比较：调用key1所在类的equals(key2)，比较：
                    如果equals()返回false：此时key1-value1添加成功 --> 情况3
                    如果equals()返回true：使用value1替换value2

        补充：关于情况2和情况3 -> 此时key1-value1和原来的数据以链表的方式储存

    3、jdk8 相较于 jdk7 在底层实现方面的不同：
        ①、new HashMap()：底层没有创建一个长度为16的数组
        ②、jdk8底层数组是：Node[]，而非Entry[]
        ③、首次调用put(0方法时，底层才开始创建长度为16的数组
        ④、jdk7底层结构只有：数组 + 链表；jdk8中底层结构：数组 + 链表 + 红黑树
            当数组的某一个索引位置上的元素以链表形式存在的数据个数 大于 8，且当数组的长度 大于 16时，此时
            此索引位置上的所有数据改为使用红黑树储存

三、LinkedHashMap()的底层实现原理

 */

import java.util.*;

// 四、Map接口中定义的方法
public class MapThreadUser {
    // 添加、删除、修改操作
    public void methods1(){
        HashMap map = new HashMap();

        // 1、Object put(Object key, Object value)：将指定key-value添加到(或修改)当前map对象中
        map.put("aa", 12);  //添加
        map.put("bb", 13);
        map.put("cc", 15);
        map.put("dd", 17);
        map.put("ee", 10);
        map.put("aa", 10);  //修改

        // 2、void putAll(Map m)：将m中的所有key-value对存放到当前map中
        HashMap map1 = new HashMap();
        map1.put("ff", 34);
        map1.put("gg", 89);
        map1.put("ss", 90);
        map1.putAll(map);

        // 3、Object remove(Object key)：移除指定key的key-value对，并返回value
        Object value = map.remove("cc");
        System.out.println(value);

        // 4、void clear()：清空当前map中所有数据
        map.clear();
        System.out.println(map.size());
    }

    // 元素查询操作
    public void methods2(){
        HashMap map = new HashMap();
        map.put("aa", 12);  //添加
        map.put("bb", 13);
        map.put("cc", 15);
        map.put("dd", 17);
        map.put("ee", 10);
        map.put("aa", 10);  //修改

        // 1、Object get(Object key)：获取指定key对应的value
        System.out.println(map.get("aa"));

        // 2、boolean containsKey(Object key)：是否包含指定的key
        System.out.println(map.containsKey("bb"));

        // 3、boolean containsValue(Object value)：是否包含指定的value
        System.out.println(map.containsValue(45));

        // 4、int size()：返回map中key-value对的个数
        System.out.println(map.size());

        // 5、boolean isEmpty()：判断当前map是否为空
        System.out.println(map.isEmpty());

        // 6、boolean equals(Object obj)：判断当前map和参数对象obj是否相等
        HashMap map1 = new HashMap();
        map1.put("aa", 12);
        map1.put("bb", 13);
        map1.put("cc", 15);

        System.out.println(map.equals(map1));
    }

    // 元视图操作的方法
    public void methods3(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("aa", 12);  //添加
        map.put("bb", 13);
        map.put("cc", 15);
        map.put("dd", 17);
        map.put("ee", 10);
        map.put("ff", 10);  //修改

        // 1、Set keySet()：返回所有key构成的Set集合
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) System.out.println(iterator.next());

        System.out.println();

        // 2、Collection values()：返回所有value构成的Collection集合
        Collection values = map.values();
        Iterator iterator1 = values.iterator();
        while (iterator1.hasNext()) System.out.println(iterator1.next());

        System.out.println();

        // 3、Set entrySet()：返回所有的key-value对构成的Set集合
        Set entrySet = map.entrySet();
        Iterator iterator2 = entrySet.iterator();
        while (iterator2.hasNext()){
            Object obj = iterator2.next();
            // entrySet集合中的元素都是entry
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry);
        }
    }
}
