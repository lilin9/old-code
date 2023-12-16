import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by MrLi on 2022/02/11/12:55
 */
public class beanTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("../../main/resources/beanTest.xml");
        Person person = context.getBean("person", Person.class);
        System.out.println("第四步，获取bean实例对象");
        System.out.println(person);
        //手动销毁bean实例
        context.close();
    }
}
