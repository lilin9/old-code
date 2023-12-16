package NO1_UserJDBC;

/*
ORM编程思想（Object Relational Mapping）：
    一个数据表对应一个Java类
    表中的一条记录对应Java类的一个对象
    表中的一个字段对应Java类的一个属性
 */
public class Customer {
    private int id;
    private String name;
    private int userCP;

    public Customer(){
        super();
    }

    public Customer(int id, String name, int userCP) {
        this.id = id;
        this.name = name;
        this.userCP = userCP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserCP() {
        return userCP;
    }

    public void setUserCP(int userCP) {
        this.userCP = userCP;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userCP='" + userCP + '\'' +
                '}';
    }
}