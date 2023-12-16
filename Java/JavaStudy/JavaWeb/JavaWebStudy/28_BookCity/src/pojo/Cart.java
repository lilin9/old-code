package pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MrLi on 2022/01/14/15:39
 *
 * 购物车对象
 */
public class Cart {
    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    /**
     * @author MrLi
     * @create 2022/1/14 15:46
     * @description 添加商品项
     */
    public void addItem(CartItem cartItem){
        //先查看购物车中是否已经添加过此商品。如果已添加，则数量累加，总金额更新；如果没有添加过，则添加入集合中
        CartItem item = items.get(cartItem.getId());
        if (item != null){
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        } else {
            items.put(cartItem.getId(), cartItem);
        }
    }

    /**
     * @author MrLi
     * @create 2022/1/14 15:46
     * @description 删除商品项
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * @author MrLi
     * @create 2022/1/14 15:46
     * @description 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * @author MrLi
     * @create 2022/1/14 15:47
     * @description 修改商品数量
     */
    public void updateCount(Integer id, int count){
        //先查看购物车中是否有此商品。如果有，则修改商品数量，更新总金额
        CartItem item = items.get(id);

        if (item != null) {
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;

        for (Map.Entry<Integer, CartItem> entry: items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        for (Map.Entry<Integer, CartItem> entry: items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
