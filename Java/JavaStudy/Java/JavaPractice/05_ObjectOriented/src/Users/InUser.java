package NO5_ObjectOriented_BigWork.Users;

import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import NO5_ObjectOriented_BigWork.Shopping.*;

//海内用户类
public class InUser extends User {
    @Override
    //填写验证手机号的ui界面
    public void cellphone_ui(){
        Scanner input = new Scanner(System.in);
        int yes;

        do {
            System.out.print("输入手机号码：");
            this.setPhoneNumber(input.nextInt());

            System.out.println("***************手机号码保存成功!***************");

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print("是否确认进行下一步，确认请输入1，不确认请输入0：");
            yes = input.nextInt();
        } while (yes != 1);
    }

    @Override
    //设置填写账号信息的UI界面
    public void account_ui(){
        Scanner input = new Scanner(System.in);
        int num1 = 0;
        int num2 = 0;

        System.out.print("请输入用户名：");
        this.setEmail(input.next());

        System.out.print("请设置密码：");
        this.setPassword(input.next());

        System.out.print("请确认密码：");
        do {
            String pass_temp = input.next();
            if (super.getPassword().equals(pass_temp)) break;
            else System.out.print("密码错误，请重新输入：");

            //如果用户输入三次账号密码依旧错误，则退出程序
            num1++;
            if (num1 == 4) {
                System.out.println("密码输入错误，即将退出程序！");

                //使得程序滞缓1秒，给用户反应时间
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.exit(0);
            }
        }while (true);

        System.out.print("请输入邮箱：");
        this.setEmail(input.next());

        System.out.print("请确认邮箱：");
        do {
            String email_temp = input.next();
            if (super.getEmail().equals(email_temp)) break;
            else System.out.print("邮箱账号错误，请重新输入：");

            //如果用户输入三次邮箱账号依旧错误，则退出程序
            num2++;
            if (num2 == 4) {
                System.out.println("邮箱账号输入错误，即将退出程序！");

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

    //设置购物车的UI界面
    public void shoppUI(){
        Scanner input = new Scanner(System.in);
        AllCommodity all = new AllCommodity();
        Money money = new Money();
        Iterator<String> keys = all.initialization().keySet().iterator();
        Iterator<Double> values = all.initialization().values().iterator();

        System.out.println("***************您的商品信息如下所示***************");
        while (keys.hasNext() && values.hasNext()) System.out.println("产品是：" + keys.next() + " ,价钱为：" +
                values.next() + " 元，购买数量是：" + 1);

        System.out.println("\n***************下面进入结算界面***************");
        System.out.print("您是否是VIP会员?是请输入1，不是输入0：");
        int temp2 = input.nextInt();
        double sum = 0;
        if (temp2 == 1){
            for (Double aDouble : all.initialization().values()) sum += money.vip_get_Price(aDouble);

        }
        else{
            for (Double aDouble : all.initialization().values()) sum += money.common_get_Price(aDouble);
        }
        System.out.println("您需要支付的费用为" + (float)sum + "元\n\n下面将为您自动结算费用，请稍后……");

        try {
            TimeUnit.MILLISECONDS.sleep(3000); //滞缓3秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("***************结算成功，祝您购买愉快!***************");
    }
}
