package pojo;

/**
 * Created by MrLi on 2022/02/11/13:31
 */
public class Employee1 {
    private Dept1 dept1;

    public void setDept1(Dept1 dept1) {
        this.dept1 = dept1;
    }
    public void printf() {
        System.out.println(dept1);
    }
    @Override
    public String toString() {
        return "Employee1{" +
                "dept1=" + dept1 +
                '}';
    }
}
