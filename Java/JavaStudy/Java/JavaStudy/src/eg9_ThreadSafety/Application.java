package eg9_ThreadSafety;
/*
例子：创建三个窗口买票，总票数为100张，使用实现Runnable接口的方式

1、问题：买票过程中，出现了重票、错票 --> 出现线程安全问题
2、问题出现的原因：当某个线程操作车票的过程中，尚未操作完成时，其他线程参与进来，也同时操作车票
3、解决方法：当一个线程a在操作ticket的时候，其他线程不能参与进来，直到线程a操作完成ticket时，其他线程才可以开始操作ticket.
           这种情况即使线程a出现了阻塞，也不能被改变。
4、在Java中，通过同步机制，来解决线程安全问题。

方式一：同步代码块
    synchronized(同步监视器(锁)){
        // 需要被同步的代码
        ……
    }
    说明：
        1、同步监视器，俗称：锁。任何一个类的对象，都可以充当锁
        2、多个线程必须要共同使用同一把锁

方式一：同步方法
   1、如果操作共享数据的代码完整的声明在一个方法中，我们不妨将此方法声明为同步的
   2、同步监视器(锁)：this

方式二：Lock锁
    步骤：
        1、实例化ReentrantLock
        2、调用锁定方法：lock()
        3、调用解锁方法：unlock()

方式三：实现callable接口 --> JDK5.0新增
    步骤：
        1、创建一个实现Callable的实现类
        2、实现call方法，将此线程需要执行的操作声明在call()中
        3、创建Callable接口实现类的对象
        4、将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
        5、将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()

方式四：使用线程池
    步骤：
        1、提供指定线程数量的线程池
        2、执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        3、关闭连接池
        
    优点：
        1、提高响应速度（减少了创建新线程的时间）
        2、降低资源消耗（重复利用线程池中线程，不需要每次都创建）
        3、便于线程管理
            corePoolSize：核心池的大小
            maximumPooSize：最大线程数
            keepAliveTime：线程没有任务时最多保持多长时间会终止
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);

        service.execute(new Windows5()); // 适用于Runnable
//        service.submit(Callable callable); // 适用于Callable

        service.shutdown(); // 关闭连接池
    }

}






