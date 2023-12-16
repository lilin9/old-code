import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

public class Dom4jTest {

    public void test1() {
        //创建一个SaxReader输入流，去读取xml配置文件，生成Document对象
        SAXReader saxReader = new SAXReader();

        try {
            Document document = saxReader.read("../books.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void test2() throws Exception {
        //1、读取books.xml文件
        SAXReader reader = new SAXReader();
        Document document = reader.read("../books.xml");

        //2、通过Document对象获取根元素
        Element rootElement = document.getRootElement();

        //3、通过根元素获取book标签对象
        List<Element> books = (List<Element>) rootElement.element("book");

        //4、遍历，处理每个book标签转换为book类
        for (Element each: books) {
            Element nameElement = each.element("name");

            //getText() 可以获取标签中的文本内容
            String nameTest = nameElement.getText();

            //直接获取指定标签名的文本内容
            String priceText = each.elementText("price");
            String authorText = each.elementText("author");

            String snValue = each.attributeValue("sn");

            Book book = new Book(snValue, nameTest, Integer.parseInt(priceText), authorText);
            System.out.println(book);
        }
    }
}