package eg1_Testing;
import java.util.Scanner;

public class Teacher {
    String name;
    int age;
    int teachAge;
    String course;

    public void information(){
        Scanner input = new Scanner(System.in);

        System.out.print("这名老师的姓名是：");
        this.name = input.next();

        System.out.print("这名老师的年龄是：");
        this.age = input.nextInt();

        System.out.print("这名老师的教龄是：");
        this.teachAge = input.nextInt();

        System.out.print("这名老师的教的科目是：");
        this.course = input.next();
    }

    public void print(){
        System.out.println("老师" + this.name + "的年龄是" + this.age + "岁，教龄" + this.teachAge + "年，教的科目是《" + this.course + "》");
    }

    public static void main(String[] args){
        Teacher teacher = new Teacher();

        teacher.information();
        teacher.print();
    }
}
