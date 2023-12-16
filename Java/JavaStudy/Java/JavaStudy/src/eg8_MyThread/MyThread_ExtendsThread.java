package eg8_MyThread;

/*
 *多线程的创建方式一：继承于Thread类
 *
 *1、创建一个继承于Thread类的子类
 *2、重写Thread类的run() --> 将此线程执行的操作声明在run()中
 *3、创建Thread类的子类的对象
 *4、通过此对象调用start()
 *
 *例子：遍历100以内的所有偶数
 */

// 1、创建一个继承于Thread类的子类
public class MyThread_ExtendsThread extends Thread{
    @Override
    // 2、重写Thread类的run()
    public void run() {
        for(int i=0; i<50; i++){
            if(i%2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
