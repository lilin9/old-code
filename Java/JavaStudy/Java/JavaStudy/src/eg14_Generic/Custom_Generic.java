package eg14_Generic;

//自定义泛型类

public class Custom_Generic<T> { // 类的内部结构就可以使用类的泛型
    String orderName;
    int orderId;

    T orderT;

    public Custom_Generic(){};

    public Custom_Generic(String orderName, int orderId, T orderT){
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    public T getOrderT(){
        return this.orderT;
    }

    public void setOrderT(T orderT){
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Custom_Generic{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }
}
