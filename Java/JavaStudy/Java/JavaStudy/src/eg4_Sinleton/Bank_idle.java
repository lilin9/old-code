package eg4_Sinleton;

// 懒汉式单例设计模式
public class Bank_idle {
    // 1、私有化类的构造器
    private Bank_idle(){

    }

    // 2、内部创建类的对象
    // 4、要求此对象也必须声明为静态的
    private static Bank_idle instance = null;

    // 3、声明public、static的返回当前类对象的方法
    public static Bank_idle getInstance(){
        // 方式一：效率稍差
/*        synchronized (Bank_idle.class){
            if(instance == null){
                instance = new Bank_idle();
            }
        }
 */

        // 方式二：效率稍高
        if (instance == null){
            synchronized (Bank_idle.class){
                if(instance == null){
                    instance = new Bank_idle();
                }
            }
        }
        return instance;
    }
}