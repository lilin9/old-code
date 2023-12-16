package eg4_Sinleton;

// 饿汉式单例设计模式
public class Bank_hunger {
    // 1、私有化类的构造器
    private Bank_hunger(){

    }

    // 2、内部创建类的对象
    // 4、要求此对象也必须声明为静态的
    private static Bank_hunger instance = new Bank_hunger();

    // 3、提供公共的静态方法，调用类的对象
    public static Bank_hunger getBank(){
        return instance;
    }
}
