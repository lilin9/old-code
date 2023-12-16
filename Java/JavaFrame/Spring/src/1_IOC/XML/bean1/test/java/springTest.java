import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Employee;

/**
 * Created by MrLi on 2022/02/10/13:54
 */
public class springTest {
    @Test
    public void test() {
        //1、加载 Spring 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("../../main/resources/beanTest.xml");
        //2、获取配置文件中的对象
        BookService userService = context.getBean("userService", BookService.class);
        //3、调用方法
        userService.test();
    }

    @Test
    public void test1() {
        //1、加载 Spring 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("../../main/resources/beanTest.xml");
        //2、获取配置文件中的对象
        Employee employee = context.getBean("employee", Employee.class);
        //3、调用方法
        System.out.println(employee);
    }
}
