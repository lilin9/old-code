package eg6_Customer_Information_Management;

// 是Customer对象的管理模块，内部用数组管理一组Customer对象，
// 并提供相应的添加、修改、删除和遍历方法，供Customer_view调用
public class Customer_list {

    private Customer[] customers;   // 保存用户信息的数组
    private int count = 0;    // 记录用户数量

    // 用来初始化数组 customers 的构造器
    // countCustomer：指定数组长度
    public Customer_list(int countCustomer){
        customers = new Customer[countCustomer];
    }

    //添加客户信息
    public boolean add(Customer customer){
        this.customers[count] = customer;
        this.count += 1;

        return true;
    }

    // 修改客户信息
    public boolean remove(int index, Customer cust) {
        if (index < 0 || index > count) {
            return false;
        }
        else{
            customers[index] = cust;
            return true;
        }
    }

    // 删除客户信息
    public boolean delete(int index){
        if(index < 0 || index > count){
            System.out.println("客户信息不存在！");
            return false;
        }
        else{
            for(int i=index; i<count-1; i++){
                customers[i] = customers[i+1];
            }
            count--;
            customers[count-1] = null;
            System.out.println("删除成功！");
        }
        return true;
    }

    // 获取所有客户信息
    public Customer[] get_all(){
        Customer[] custs = new Customer[count];
        for(int i=0; i < customers.length; i++){
            custs[i] = customers[i];
        }

        return custs;
    }

    // 获取指定客户信息
    public Customer get(int index){
        if(index < 0 || index > count){
            System.out.println("客户信息不存在！");
            return null;
        }

        return customers[index];
    }

    // 获取客户数量
    public int get_count(){
        return count;
    }
}
