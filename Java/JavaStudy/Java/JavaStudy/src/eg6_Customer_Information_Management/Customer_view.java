package eg6_Customer_Information_Management;

import java.util.Scanner;

// 主模块，负责菜单的显示和用户的操作
public class Customer_view {
    Customer_list customer_list = new Customer_list(10);
    Scanner input = new Scanner(System.in);

    // 设置客户界面
    public  void IU(){
        boolean isFalg = true;

        while(isFalg){
            System.out.println("--------------------客户信息管理软件--------------------\n");
            System.out.println("\t\t1 添加客户\n\t\t2 修改客户\n\t\t3 删除客户\n\t\t4 客户列表\n\t\t5 退出\n");

            System.out.print("\t请选择(1-5)：");
            int count = input.nextInt();

            switch (count) {
                case 1:
                    add_new_customer();
                    break;
                case 2:
                    remove_customer();
                    break;
                case 3:
                    delete_customer();
                    break;
                case 4:
                    get_all_customer();
                    break;
                case 5:
                    System.out.print("确认是否退出(Y/N)：");
                    char chars = input.next().charAt(0);
                    if (chars == 'Y' || chars == 'y') {
                        isFalg = false;
                    }
                    break;
            }
        }

    }

    // 添加客户信息
    public void add_new_customer(){
        System.out.println("---------------------添加客户----------------------");

        System.out.print("姓名：");
        String name = input.next();

        System.out.print("性别：");
        String gender = input.next();

        System.out.print("年龄：");
        int age = input.nextInt();

        System.out.print("电话：");
        int phone = input.nextInt();

        System.out.print("邮箱：");
        String email = input.next();

        // 将上述变量封装到对象中
        Customer customer = new Customer(name, gender, age, phone, email);

        // 判断数组是否已满
        boolean isSuccess = customer_list.add(customer);
        if(isSuccess){
            System.out.println("---------------------添加成功----------------------");
        }
        else{
            System.out.println("------------------目录已满添加失败-------------------");
        }
    }

    // 修改客户信息
    public void remove_customer(){
        System.out.println("---------------------修改客户----------------------");

        Customer cust;
        int number;
        while(true){
            System.out.print("请输入待修改客户编号(-1退出)：");

            number = input.nextInt();
            if(number == -1){
                return;
            }

            // 判断客户输入的编号是否在列表中存在
            cust = customer_list.get(number-1);
            if(cust == null){
                System.out.println("无法找到指定客户！");
            }
            else{
                // 找到相应编号的客户
                break;
            }
        }

        // 找到相应客户信息后，修改客户信息
        System.out.print("姓名(" + cust.getName() + ")：");
        String name = input.next();

        System.out.print("性别(" + cust.getGender() + ")：");
        String gender = input.next();
        if(gender == null){
            gender = cust.getGender();
        }

        System.out.print("年龄(" + cust.getAge() + ")：");
        int age = input.nextInt();

        System.out.print("电话(" + cust.getPhone() + ")：");
        int phone = input.nextInt();

        System.out.print("邮箱(" + cust.getEmail() + ")：");
        String email = input.next();

        // 将修改完成的客户信息添加到数组中
        Customer new_cust = new Customer(name, gender, age, phone, email);

        boolean isRemove = customer_list.remove(number-1, new_cust);
        if(isRemove){
            System.out.println("---------------------修改完成----------------------");
        }
        else{
            System.out.println("---------------------修改失败----------------------");
        }
    }

    // 删除客户信息
    public void delete_customer(){
        System.out.println("---------------------输出客户----------------------");

        Customer cust;
        int number;
        while(true){
            System.out.print("请输入待删除客户编号(-1退出)：");

            number = input.nextInt();
            if(number == -1){
                return;
            }

            cust = customer_list.get(number-1);
            if(cust == null){
                System.out.println("无法找到指定客户！");
            }
            else{
                // 找到相应编号的客户
                // 找到相应客户信息后，删除客户信息
                System.out.print("确认是否删除(Y/N)：");

                char isDelete = input.next().charAt(0);

                if(isDelete == 'y' || isDelete == 'Y'){
                    boolean deleteSuccess = customer_list.delete(number-1);
                    if(deleteSuccess){
                        System.out.println("---------------------删除成功----------------------");
                    }
                    else{
                        System.out.println("---------------------删除失败----------------------");
                    }
                }
                else{
                    System.out.println("输入错误！");
                    return;
                }
            }
        }
    }

    // 获取客户列表信息
    public void get_all_customer(){
        System.out.println("---------------------客户列表----------------------");

        int count = customer_list.get_count();
        if(count == 0) {
            System.out.println("没有客户信息！");
        }
        else{
            System.out.println("编号\t\t姓名\t\t性别\t\t年龄\t\t电话\t\t邮箱");
            Customer[] custs = customer_list.get_all();

            for(int i=0; i<custs.length; i++){
                Customer cust = custs[i];
                System.out.println((i+1) + "\t\t" + cust.getName() + "\t\t" + cust.getGender() + "\t\t" + cust.getAge() + "\t\t" + cust.getPhone() + "\t\t" + cust.getEmail());
            }
        }

        System.out.println("--------------------客户列表完成--------------------");
    }

    public static void main(String[] args) {
        Customer_view customer_view = new Customer_view();
        customer_view.IU();
    }
}
