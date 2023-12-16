package eg9_ThreadSafety;

import java.util.concurrent.locks.ReentrantLock;

//方式二：Lock锁

class Windows3 implements Runnable{
    private int ticket = 100;
    // 1、实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {

        try{
            // 2、调用锁定方法：lock()
            lock.lock();

            if(ticket > 0){
                while (true){
                    if (ticket > 0) {
                        System.out.println(Thread.currentThread().getName() + "：买票，票号为：" + ticket);
                        ticket--;
                    }
                    else break;
                }
            }
        }finally{
            // 3、调用解锁方法：unlock()
            lock.unlock();
        }

    }
}