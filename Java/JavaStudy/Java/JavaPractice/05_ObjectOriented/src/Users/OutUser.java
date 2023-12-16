package NO5_ObjectOriented_BigWork.Users;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//海外用户类
public class OutUser extends User{
    //填写验证手机号的ui界面
    @Override
    public void cellphone_ui() {
        Scanner input = new Scanner(System.in);
        int yes;

        do {
            System.out.print("Please enter the phone number: ");
            this.setPhoneNumber(input.nextInt());

            System.out.println("***************The phone number is saved successfully!***************");

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print("Do you want to confirm the next step? Please enter 1 to confirm, and 0 if not: ");
            yes = input.nextInt();
        } while (yes != 1);
    }

    //设置填写账号信息的UI界面
    @Override
    public void account_ui() {
        Scanner input = new Scanner(System.in);
        int num1 = 0;
        int num2 = 0;

        System.out.print("\nEnter your user name: ");
        this.setUserName(input.next());

        System.out.print("Set your password: ");
        this.setPassword(input.next());

        System.out.print("Confirm your password: ");
        do {
            String pass_temp = input.next();
            if (super.getPassword().equals(pass_temp)) break;
            else System.out.print("Wrong password, please re-enter: ");

            //如果用户输入三次账号密码依旧错误，则退出程序
            num1++;
            if (num1 == 4) {
                System.out.println("The password is entered incorrectly, and the program will be exited soon!");

                //使得程序滞缓1秒，给用户反应时间
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.exit(0);
            }
        }while (true);

        System.out.print("Enter your email: ");
        this.setEmail(input.next());

        System.out.print("Confirm your email: ");
        do {
            String email_temp = input.next();
            if (super.getEmail().equals(email_temp)) break;
            else System.out.print("Incorrect email account, please re-enter: ");

            //如果用户输入三次邮箱账号依旧错误，则退出程序
            num2++;
            if (num2 == 4) {
                System.out.println("The email account is entered incorrectly, and the program will be exited soon!");

                //使程序滞缓1秒，给用户反应时间
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.exit(0);
            }
        }while (true);

    }
}
