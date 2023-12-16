import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by MrLi on 2021/12/31/21:22
 *
 * 编写10个线程，第一个线程从1加到10，第二个线程从11加到20……第十个线程从91加到100，最后再把十个线程结果相加
 */
public class OneToTen extends Thread {
    public static int sum;
    private final int stratNum;
    ReentrantLock lock = new ReentrantLock();

    public OneToTen(int startNum) {
        this.stratNum = startNum;
    }

    public static synchronized void add(int num) {
        sum += num;
    }

    public void run() {
        lock.lock();
        int sum = 0;

        for (int i=0; i<10; i++) {
            sum += (stratNum + i);
        }

        add(sum);
        lock.unlock();
    }

    public static void main(String[] args) {
        Thread[] threadList = new Thread[10];

        for (int i=0; i<10; i++) {
            threadList[i] = new OneToTen((10 * i) + 1);
            threadList[i].start();
        }

        try {
            for (int i=0; i<10; i++) {
                threadList[i].join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Sum is : " + sum);
    }
}
