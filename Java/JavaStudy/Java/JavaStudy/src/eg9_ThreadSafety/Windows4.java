package eg9_ThreadSafety;

import java.util.concurrent.Callable;

// 方式三：实现callable接口 --> JDK5.0新增

public class Windows4 implements Callable {
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i=0; i<=100; i++){
            if (i%2 == 0 ){
                System.out.println(i);
                sum += i;
            }
        }
        return "\n"+ sum;
    }
}
