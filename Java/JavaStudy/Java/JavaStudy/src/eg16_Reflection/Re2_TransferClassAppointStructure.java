package eg16_Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

/*
调用运行时类中指定的结构：属性、方法、构造器
 */

public class Re2_TransferClassAppointStructure {
    //如何操作运行时类中的指定的属性
    @Test
    public void test() throws Exception {
        Class<Person> clazz = Person.class;

        //创建运行时类的对象
        Person p = clazz.newInstance();

        //获取指定的属性：要求运行时类的属性声明为public
        //通常不用此方式
//        Field id = clazz.getField("id");

        //设置当前属性的值
        //set(): 参数1 --> 指明设置哪个对象的值     参数2 -->　将此属性值设置为多少
//        id.set(p, 1002);

        //获取当前属性的值
        //get(): 参数1 --> 获取对象的当前属性值
//        int pId = (int)id.get(p);
//        System.out.println(pId);


        //1、getDeclaredField(String fileName): 获取运行时类中指定变量名的属性
        //常用方式
        Field name = clazz.getDeclaredField("name");

        //2、保证当前属性是可访问的
        name.setAccessible(true);

        //3、获取、设置指定对象的此属性值
        name.set(p, "Tom");

        System.out.println(name.get(p));
    }

    //如何操作运行时类中的指定的属性
    @Test
    public void test1() throws Exception{
        Class<Person> clazz = Person.class;

        //创建运行时类的对象
        Person p = clazz.newInstance();

        //1、获取指定的某个方法：getDeclaredMethod()   参数1 --> 指明获取的方法的名称  参数2 --> 指明获取的方法的形成列表
        Method show = clazz.getDeclaredMethod("show", String.class);

        //2、保证当前属性是可访问的
        show.setAccessible(true);

        //3、调用invoke()：参数1 --> 方法的调用者     参数2 --> 给方法形参赋值的实参
        //invoke()的返回值即为对应类中调用的方法的返回值
        Object invoke = show.invoke(p, "CHN");
        System.out.println(invoke);

        System.out.println("==========静态方法的调用==========");

        Method staticShow = clazz.getDeclaredMethod("staticShow");
        staticShow.setAccessible(true);

        //如果调用的运行时类的方法没有返回值，则invoke()返回值是null
        Object o = staticShow.invoke(Person.class);
        System.out.println(o); //null
    }

    //如何操作运行时类中的指定的构造器
    @Test
    public void test2() throws Exception {
        Class<Person> clazz = Person.class;

        //1、获取指定的构造器
        Constructor<Person> constructor = clazz.getDeclaredConstructor(String.class);

        //2、保证此构造器是可访问的
        constructor.setAccessible(true);

        //3、调用此构造器创建运行时类的对象
        Person person = constructor.newInstance("Tom");
        System.out.println(person);
    }
}
