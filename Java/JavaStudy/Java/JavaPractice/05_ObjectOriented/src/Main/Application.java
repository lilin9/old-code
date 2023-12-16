package NO5_ObjectOriented_BigWork.Main;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import NO5_ObjectOriented_BigWork.Users.*;

public class Application {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        InUser inUser = new InUser();
        OutUser outUser = new OutUser();

        System.out.print("***************1.企业用户注册\t2.海外用户注册***************");
        System.out.print("\n输入1或2：");
        int temp = input.nextInt();
        if (temp ==1) {
            inUser.cellphone_ui();
            inUser.account_ui();

            System.out.println("***************账号注册成功!***************\n");

            try {
                TimeUnit.MILLISECONDS.sleep(2000); //滞缓1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            inUser.shoppUI();
        }
        else {
            outUser.cellphone_ui();
            outUser.account_ui();
            System.out.println("***************Account registration is successful!***************");
        }
    }
}
