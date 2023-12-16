package eg8_MyThread;

/*
 *创建多线程的方式二：实现Runnable接口
 * 1、创建一个实现了Runnable接口的类
 * 2、实现类去实现Runnable中的抽象方法：Run()
 * 3、创建实现类的对象
 * 4、将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
 * 5、通过Thread类的对象调用start()：①启动线程 ②调用当前线程的run() --> 调用了Runnable类型的target的run()
 */
// 1、创建一个实现了Runnable接口的类
public class MyThread_RunnableThread implements Runnable{
    // 2、实现类去实现Runnable中的抽象方法：Run()
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i%2 != 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
