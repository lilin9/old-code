package eg10_ThreadCommunication;

import java.util.concurrent.locks.ReentrantLock;

public class Number implements Runnable{
    private int num = 1;
    private ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true){
                synchronized (this){
                    notify();

                    if (num <= 100){
                        System.out.println(Thread.currentThread().getName() + ":" + num);
                        num++;

                        try {
                            // 使得调用如下wait()方法的线程陷入阻塞状态
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else break;
                }
        }
    }
}
