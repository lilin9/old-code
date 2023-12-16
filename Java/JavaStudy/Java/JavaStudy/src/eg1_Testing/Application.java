package eg1_Testing;

public class Application {
    public static void main(String[] args){
        Student student = new Student();
        Teacher teacher = new Teacher();

        student.information();
        student.print();

        System.out.print("\n======我是分割线======\n\n");

        teacher.information();
        teacher.print();
    }
}
