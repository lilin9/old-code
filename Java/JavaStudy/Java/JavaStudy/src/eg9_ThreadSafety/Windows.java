package eg9_ThreadSafety;

// 原代码：原始方法实现

class Windows implements Runnable{
    private int ticket = 100;

    @Override
    public void run() {
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
