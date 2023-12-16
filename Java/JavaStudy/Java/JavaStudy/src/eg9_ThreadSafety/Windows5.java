package eg9_ThreadSafety;

//方式四：使用线程池

public class Windows5 implements Runnable{
    @Override
    public void run() {
        for (int i =1; i<=100; i++){
            if (i%2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
