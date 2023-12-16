package Demo;

import org.junit.Test;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("id=");
        String id = input.next();
        System.out.print("exam=");
        String exam = input.next();
        //String id = "222224195263214584"; //18
        //String exam = "200523164754003"; //15

        System.out.println(id.length());
        System.out.println(exam.length());
        System.out.println();

        if (id.length() == 18) System.out.println("id");
        else System.out.println("exam");
    }

    @Test
    public void test1() {
        System.out.println("hello");
    }
}
