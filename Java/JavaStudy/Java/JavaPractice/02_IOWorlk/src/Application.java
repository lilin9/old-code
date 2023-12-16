package No1_IOWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// skskk

public class Application {
    public static void main(String[] args){
        int count = 5;
        BufferedReader bis = null;

        try {
            System.out.print("请输入密码：");
            InputStreamReader fis = new InputStreamReader(System.in);
            bis = new BufferedReader(fis);

            while (true){
                String input = bis.readLine();
                String password = "123456";

                if (password.equals(input)){
                    System.out.println("恭喜你进入游戏！");
                    break;
                }
                count--;

                if (count == 0){
                    System.out.println("密码错误，结束游戏！");
                    System.exit(0);
                }
                else System.out.print("密码输入错误，你还有" + count + "次机会。请重新输入密码：");

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
