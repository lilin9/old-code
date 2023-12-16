import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by MrLi on 2022/02/11/15:42
 */
public class Test {
/*    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("../../main/resources/beanTest1.xml");
        BookService bookService = context.getBean("bookService", BookService.class);

        bookService.method();
    }*/

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookService bookService = context.getBean("bookService", BookService.class);

        bookService.method();
    }
}
