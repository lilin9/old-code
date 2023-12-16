package eg8_MyThread;

/*
 *比较两种多线程的创建方式：
 *      开发中：优先选择 --> 实现Runnable接口的方式
 *      原因：1、实现的方式没有类的单继承局限性
 *           2、实现的方式更适合来处理多个线程有共享数据的情况
 *      联系：public class Thread implements Runnable
 *      相同点：两种方式都需要重写run()，将线程要执行的逻辑声明在run()中
 */

public class Application {
    public static void main(String[] args) {
        RunnableThread();
    }


    public static void ExtendsThread(){
        // 3、创建Thread类的子类的对象
        MyThread_ExtendsThread e1 = new MyThread_ExtendsThread();
        MyThread_ExtendsThread e2 = new MyThread_ExtendsThread();

        // 4、通过此对象调用start()
        e1.start();
        e2.start();

        for(int i=0; i<50; i++){
            if(i%2 != 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }


    public static void RunnableThread(){
        // 3、创建实现类的对象
        MyThread_RunnableThread r1 = new MyThread_RunnableThread();
        // 4、将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
        Thread t1 = new Thread(r1);

        // 5、通过Thread类的对象调用start()
        t1.setName("RunnableThread：");
        t1.start();
    }
}
