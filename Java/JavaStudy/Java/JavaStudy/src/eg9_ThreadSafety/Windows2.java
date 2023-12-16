package eg9_ThreadSafety;

// 方式一：同步方法

class Windows2 implements Runnable{
    private static int ticket = 100;

    @Override
    public void run() {
        if(ticket > 0){
            show();
        }
    }

    public static synchronized void show(){
        while (true){
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "：买票，票号为：" + ticket);
                ticket--;
            }
            else break;
        }
    }
}
