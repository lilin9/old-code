import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Employee1;

/**
 * Created by MrLi on 2022/02/11/12:55
 */
public class beanTest5 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("../../main/resources/beanTest.xml");
        Employee1 employee1 = context.getBean("employee1", Employee1.class);
        System.out.println(employee1);
    }
}
