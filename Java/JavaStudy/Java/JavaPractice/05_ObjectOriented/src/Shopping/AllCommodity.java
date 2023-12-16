package NO5_ObjectOriented_BigWork.Shopping;

import java.util.HashMap;

//对商品进行初始化，并保存在集合中
public class AllCommodity {
    public HashMap<String, Double> initialization(){
        Books books = new Books();
        Cellphones cellphone = new Cellphones();
        Crafts craft = new Crafts();
        HashMap<String, Double> map = new HashMap<>();

        books.setName("Java语言程序设计");
        books.setPrice(104.25);
        books.setNumbers(1);

        cellphone.setName("荣耀50 5G手机");
        cellphone.setPrice(2899.00);
        cellphone.setNumbers(1);

        craft.setName("印尼加里曼沉香手链");
        craft.setPrice(427.00);
        craft.setNumbers(1);

        map.put(craft.getName(), craft.getPrice());
        map.put(cellphone.getName(), cellphone.getPrice());
        map.put(books.getName(), books.getPrice());

        return map;
    }
}
