package test;

import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by MrLi on 2022/01/14/16:08
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "三体", 1, new BigDecimal(50), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "朝闻道", 1, new BigDecimal(50), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "朝闻道", 1, new BigDecimal(50), new BigDecimal(1000)));

        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "三体", 1, new BigDecimal(50), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "朝闻道", 1, new BigDecimal(50), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "朝闻道", 1, new BigDecimal(50), new BigDecimal(1000)));

        cart.deleteItem(2);

        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "三体", 1, new BigDecimal(50), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "朝闻道", 1, new BigDecimal(50), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "朝闻道", 1, new BigDecimal(50), new BigDecimal(1000)));

        cart.deleteItem(2);

        cart.clear();

        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "三体", 1, new BigDecimal(50), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "朝闻道", 1, new BigDecimal(50), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "朝闻道", 1, new BigDecimal(50), new BigDecimal(1000)));

        cart.deleteItem(2);

        cart.clear();

        cart.addItem(new CartItem(1, "三体", 1, new BigDecimal(50), new BigDecimal(1000)));

        cart.updateCount(1, 20);

        System.out.println(cart);
    }
}