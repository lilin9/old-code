package eg16_Reflection;

public class Person {
    private String name;
    int age;
    public int id;

    public Person() {
    }

    private Person(String name) {
        this.name = name;
    }

    public Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    private String show(String nation){
        System.out.println("我的国籍是" + nation);
        return nation;
    }

    private static void staticShow(){
        System.out.println("我是一个static的show()方法！");
    }
}
