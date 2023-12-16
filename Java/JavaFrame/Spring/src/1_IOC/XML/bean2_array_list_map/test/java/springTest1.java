import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.FactoryBean1;
import pojo.Student;

/**
 * Created by MrLi on 2022/02/10/13:54
 */
public class springTest1 {
    //普通Bean
    @Test
    public void test() {
        //1、加载 Spring 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("../../main/resources/beanTest.xml");
        //2、获取配置文件中的对象
        Student student = context.getBean("student", Student.class);
        //3、调用方法
        System.out.println(student);
    }

    //工厂Bean
    @Test
    public void test1() {
        //1、加载 Spring 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("../../main/resources/beanTest.xml");
        //2、获取配置文件中的对象
        FactoryBean1 factoryBean1 = context.getBean("factoryBean1", FactoryBean1.class);
        //3、调用方法
        System.out.println(factoryBean1);
    }
}
