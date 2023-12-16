package eg1_Testing;
import java.util.Scanner;

public class Student {
    String name;
    int age;
    String major;
    String interests;

    public void information(){
        Scanner input = new Scanner(System.in);

        System.out.print("输入学生的姓名：" + "\t");
        this.name = input.next();

        System.out.print("输入学生的年龄：" + "\t");
        this.age = input.nextInt();

        System.out.print("输入学生的专业：" + "\t");
        this.major = input.next();

        System.out.print("输入学生的爱好：" + "\t");
        this.interests = input.next();
    }

    public void print(){
        System.out.println("学生" + this.name + "的年龄是" + this.age + "岁，《" + this.major +"》专业，喜欢" + this.interests);
    }
}
