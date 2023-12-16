package eg6_Customer_Information_Management;

// 实体对象，用来封装用户信息
public class Customer {
    private String name;
    private String gender;
    private int age;
    private int phone;
    private String email;

    // 提供 get() 和 set() 方法
    public String getName(){
        return this.name;
    }
    public String setName(String name){
        return this.name = name;
    }

    public String getGender(){
        return this.gender;
    }
    public String setGender(String gender){
        return this.gender = gender;
    }

    public int getAge(){
        return this.age;
    }
    public int setAge(int age){
        return this.age = age;
    }

    public int getPhone(){
        return phone;
    }
    public int setPhone(int phone){
        return this.phone = phone;
    }

    public String getEmail(){
        return email;
    }
    public String seteamil(String eamil){
        return this.email = eamil;
    }

    // 设置无参构造器和有参构造器
    public Customer(){
    }
    public Customer(String name, String gender, int age, int phone, String email){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }
}
