package eg4_Sinleton;

/*
 *
 * 单例设计模式：
 *      1、所谓单例设计模式，就是采取一定的方法保证在整个的软件系统中，对某一个类只能存在一个对象实例
 *
 *      2、如何实现：
 *          饿汉式 VS 懒汉式
 *
 *      3、区分懒汉式和饿汉式：
 *          饿汉式：
 *              坏处 -> 对象加载时间过长
 *              好处 -> 饿汉式线程是安全的
 *          懒汉式：
 *              好处 -> 延迟对象的创建
 *              目前的写法坏处 -> 懒汉式线程是不安全的
 *
 */

public class Application {
    public static void main(String[] args) {
        Bank_hunger bank1 = Bank_hunger.getBank();
        Bank_hunger bank2 = Bank_hunger.getBank();

        System.out.println(bank1 == bank2);

        Bank_idle bank3 = Bank_idle.getInstance();
        Bank_idle bank4 = Bank_idle.getInstance();

        System.out.println(bank3 == bank4);
    }
}
