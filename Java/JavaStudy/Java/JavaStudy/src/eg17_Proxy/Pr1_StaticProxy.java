package eg17_Proxy;

/*
动态代理举例

特点：代理类和被代理类在编译期间就被确定下来
 */

interface ClothFactory{
    void produceCloth();
}

//被代理类
class NikeClothFactory implements ClothFactory{
    @Override
    public void produceCloth() {
        System.out.println("Nike工厂生产一些运动服");
    }
}

//代理类
class ProxyClothFactory implements ClothFactory{
    final private ClothFactory factory; //用被代理类对象进行实例化

    public ProxyClothFactory (ClothFactory factory){
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂的准备工作");

        factory.produceCloth();

        System.out.println("代理工厂的后续收尾工作");
    }
}

public class Pr1_StaticProxy {
    public static void main(String[] args) {
        //创建被代理类对象
        NikeClothFactory nike = new NikeClothFactory();
        //创建代理类对象
        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nike);

        proxyClothFactory.produceCloth();
    }
}
