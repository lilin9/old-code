package eg9_ThreadSafety;

class Windows1 implements Runnable{
    private int ticket = 100;
    final Object obj = new Object();

    @Override
    public void run() {
        // synchronized (this)  (this慎用)
        // synchronized (Windows1.class)
        synchronized (obj){
            if(ticket > 0){
                while (true){
                    if (ticket > 0) {
                        System.out.println(Thread.currentThread().getName() + "：买票，票号为：" + ticket);
                        ticket--;
                    }
                    else break;
                }
            }
        }
    }
}
