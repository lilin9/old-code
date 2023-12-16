package eg3_Proof_of_polymorphism;

import java.util.Random;

class Animal{
    protected  void eat(){
        System.out.println("我是动物，我要进食！！！");
    }
}

class Dog extends Animal{
    public void eat() {
        System.out.println("我是狗狗，我要吃骨头！！！");
    }
}

class Cat extends Animal{
    public void eat(){
        System.out.println("我是喵喵，我要吃鱼！！！");
    }
}

class Sheep extends Animal{
    public void eat(){
        System.out.println("我是绵绵，我要吃草！！！");
    }
}


public class Proof_of_polymorphism {
    public static Animal getInstance(int count){
        switch (count){
            case 0:
                return new Dog();
            case 1:
                return new Cat();
            default:
                return new Sheep();
        }
    }

    public static void main(String[] args) {
        int count = new Random().nextInt(3);
        System.out.println("随机数是：" + count);

        Animal animal = getInstance(count);
        animal.eat();
    }
}


