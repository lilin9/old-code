package eg2_Student;

public class Application {
    public static void main(String[] args){
        Student student1 = new Student();
        Student student2 = new Student("小美", 90);

        student1.setting("小明", 100);
        student1.get();

        System.out.println();

        System.out.println("学生的名字是" + "\t" + student2.name);
        System.out.println("学生的成绩是" + "\t" + student2.grade);
    }
}
