package eg10_ThreadCommunication;
/*
线程通信的例子：使用两个线程打印1-100。线程1、线程2交替打印。

涉及到的三个方法：
    wait()：一旦执行此方法，当前线程就会进入阻塞状态，并释放同步监视器
    notify()：一旦执行此方法，就会唤醒被 wait 的一个线程。如果有多个线程被 wait ，就会唤醒优先级高的那个
    notifyAll()：一旦执行此方法，就会唤醒所有被 wait 的线程

注意点：
    1、wait()、notify()、notifyAll()三个方法必须使用在同步代码块或者同步方法中
    2、wait()、notify()、notifyAll()三个方法的调用者必须是同步代码块或者同步方法中的同步监视器。否则，会出现 IllegalMonitorStateException 异常。
    3、wait()、notify()、notifyAll()三个方法是定义在 Java.Lang.Object 类中

面试题：sleep()和wait()的异同？
1、相同点：一旦执行方法，都可以使当前的线程进入阻塞状态
2、不同点：①两个方法声明的位置不同：Thread类中声明sleep()，Object类中声明wait()
         ②调用的要求不同：sleep()可以在任何需要的场景下调用；wait()必须使用在同步代码块中
         ③关于是否释放同步监视器：如果两个方法都是用在同步代码块或同步方法中，sleep()不会释放锁，wait()会释放锁
 */
public class Application {
    public static void main(String[] args) {
        Number number = new Number();

        Thread n1 = new Thread(number);
        Thread n2 = new Thread(number);

        n1.setName("线程1：");
        n2.setName("线程2：");

        n1.start();
        n2.start();
    }
}
