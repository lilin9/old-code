import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AffairsUserService;

/**
 * Created by MrLi on 2022/02/14/15:14
 */
public class AffairsTest {
    @Test
    public void test1() {
        ApplicationContext context = new AnnotationConfigApplicationContext("../../main/resources/affairsBean.xml");
        AffairsUserService affairsUserService = context.getBean(AffairsUserService.class, "affairsUserService");

        affairsUserService.accountMoney();
    }
}
