package eg12_SeasonClass;

/*
一、枚举类的使用
    1、枚举类的理解：类的对象只有有限个、确定的。我们称此类为枚举类
    2、当需要定义一组常量时，强烈建议使用枚举类
    3、如果枚举类中只有一个对象，则可以作为单例模式的实现方式

二、如何定义枚举类
    1、jdk5.0之前，自定义枚举类
    2、jdk5.0之后，可以使用enum关键字定义枚举类
        说明：定义的枚举类默认继承于java.lang.Enum类

三、Enum类中的常用方法
    1、values()方法：返回枚举类型的对象数组。该方法可以很方便的遍历所有的枚举值
    2、valueOf(String str)：可以把一个字符串转化为对应的枚举对象。要求字符串必须是枚举类对象
    3、toString()：返回当前枚举类对象常量的名称

四、使用enum关键字定义的枚举类实现接口的情况
    情况1：实现接口，在enum类中实现抽象方法
    情况2：让枚举类的对象分别实现接口中的抽象方法
 */

public class Application {
    public static void main(String[] args) {
        Custom_Season spring = Custom_Season.SPRING;
//        System.out.println(spring);

        enum_Season summer = enum_Season.SUMMER;
//        System.out.println("\n" + summer);

        // values()
        enum_Season[] values = enum_Season.values();
        for (enum_Season value : values) {
            System.out.println(value);
            value.show();
        }

        // valueOf(String objName)：返回枚举类中对象名是objName的对象
        enum_Season winter = enum_Season.valueOf("WINTER");
        System.out.println(winter);
    }
}
