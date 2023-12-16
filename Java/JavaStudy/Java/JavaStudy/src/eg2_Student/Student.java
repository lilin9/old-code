package eg2_Student;

public class Student {
    public Object test;
    String name;
    int grade;

    public void setting(String name, int grade){
        this.name = name;
        this.grade = grade;
    }

    public void get(){
        System.out.println("学生的名字是" + "\t" + this.name);
        System.out.println("学生的成绩是" + "\t" + this.grade);
    }

    public Student(){

    }

    public Student(String name, int grade){
        this.name = name;
        this.grade = grade;
    }
}