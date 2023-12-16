package NO5_ObjectOriented_BigWork.Shopping;

//商品主类
public class Commodity {
    private String name;
    private double price;
    private int numbers;

    public Commodity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", numbers=" + numbers +
                '}';
    }
}
