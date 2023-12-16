package eg11_StringBufferBuilder;
/*
关于StringBuffer和StringBuilder的使用：
1、String、StringBuffer、StringBuilder的异同：
    String：不可变的字符序列     底层使用char[]数组存储
    StringBuffer：可变的字符序列    线程安全，效率低    底层使用char[]数组存储
    StringBuilder：可变的字符序列   线程不安全，效率高   底层使用char[]数组存储

2、源码分析：
    String str = new String(); //char[] value = new char[0];
    String str1 = new String("abc"); //char[] value = new char[]{'a', 'b', 'c'};

    StringBuffer sb1 = new StringBuffer; //char[] value = new char[16] 底层创建了一个长度是16的char[]数组
    sb1.append('a'); //value[0] = 'a';
    sb1.append('b'); //value[1] = 'b';

    StringBuffer sb2 = new StringBuffer("abc"); //char[] value = new char["abc".length() + 16];

问题1：System.out.println(sb2.length()); --> 3
问题2：扩容：如果要添加的数据底层数组装不下，就需要扩容底层数组。默认情况下，扩容为原来容量的2倍 + 2，同时
           将原有数组中的元素复制到新的数组中。

3、StringBuffer主要方法：
    增：append()
    删：delete(int start, int end)
    改：setCharAt(int n, char ch) / replace(int start, int end, String str)
    查：charAt(int n)
    插：insert(int offset, ×××)
    长度：length()
    遍历：for() + charAt() / toString()

4、三者运行效率比较：
    StringBuilder　＞　StringBuffer　＞　String
 */

import java.util.Locale;

public class Application {
    public static void main(String[] args) {
        String str = "HelloWorld";
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = str.toCharArray();

        for (int i = 0; i < str.length(); i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z'){
                stringBuffer.append((chars[i]+"").toLowerCase(Locale.ROOT));
            }
            if (chars[i] >= 'a' && chars[i] <= 'z'){
                stringBuffer.append((chars[i] + "").toUpperCase(Locale.ROOT));
            }
        }
        System.out.println(stringBuffer.reverse());
    }
}


















