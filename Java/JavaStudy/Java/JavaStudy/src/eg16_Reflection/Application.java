package eg16_Reflection;

import org.junit.Test;

import java.io.InputStream;
import java.lang.annotation.ElementType;
import java.util.Properties;

/*
关于反射(Reflection)的理解：
    反射被视为动态语言的关键，反射机制允许程序在执行期间借助于Reflection API取得任何类的内部消息，
    并能操作任意对象的内部属性以及方法。

框架 = 反射 + 注解 + 设计模式

关于java.lang.Class类的理解
1、类的加载过程：
    程序经过javac.exe命令后，会生成一个或多个字节码文件(.class结尾).
    接着我们使用java.exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件加载到内存中，这个过程就称为类的加载。
    加载到内存中的类，就称为运行时类，此运行时类，就作为Class的一个实例。(或者说，Class的实例就对应着一个运行时类)

2、加载到内存中的运行时类，会在内存中缓存一定的时间。在此时间内，可以通过不同的方式来获取此运行时类
 */

// 获取Class类的实例的方式
public class Application {
    @Test
    public void test1() throws ClassNotFoundException {
        // 方式一：调用运行时类的属性(.class)
        Class<Person> clazz1 = Person.class;
        System.out.println(clazz1);

        // 方式二：通过运行时类的对象，调用getClass()
        Person person1 = new Person();
        Class<? extends Person> clazz2 = person1.getClass();
        System.out.println(clazz2);

        // 方式三：调用Class的静态方法：forName(String classPath)
        Class<?> clazz3 = Class.forName("eg16_Reflection.Person");
        System.out.println(clazz3);

        // 方式四：使用类的加载器(ClassLoader)(了解)
        ClassLoader loader = Application.class.getClassLoader();
        Class<?> clazz4 = loader.loadClass("eg16_Reflection.Person");
        System.out.println(clazz4);
    }

    // Class实例可以是哪些结构的说明
    @Test
    public void test2(){
        Class<Object> c1 = Object.class;
        Class<Comparable> c2 = Comparable.class;
        Class<String> c3 = String.class;
        Class<int[][]> c4 = int[][].class;
        Class<ElementType> c5 = ElementType.class;
        Class<Override> c6 = Override.class;
        Class<Integer> c7 = int.class;
        Class<Void> c8 = void.class;
        Class<Class> c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class<? extends int[]> c10 = a.getClass();
        Class<? extends int[]> c11 = b.getClass();
        // 只要数组的元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);
    }

    // 使用类的加载器加载配置文件
    @Test
    public void test3() throws Exception{
        Properties pro = new Properties();
        // 此时的配置文件默认在当前module下
        // 读取配置文件的方式一：
//        FileInputStream fis = new FileInputStream("src\\eg16_Reflection\\jdbc.properties");
//        pro.load(fis);

        // 读取配置文件的方式二：使用ClassLoader
        // 配置文件默认识别为：当前module的src下
        ClassLoader classLoader = ClassLoader.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("src\\eg16_Reflection\\jdbc.properties");

        pro.load(is);

        String user = pro.getProperty("user");
        String password = pro.getProperty("password");

        System.out.println("user=" + user + ", password=" + password);
    }
}
