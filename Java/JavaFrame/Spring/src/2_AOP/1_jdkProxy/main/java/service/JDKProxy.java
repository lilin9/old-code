package service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by MrLi on 2022/02/12/15:10
 *
 * 第三步：使用Proxy类创建接口代理对象
 */
public class JDKProxy {
    public static void main(String[] args) {
        //创建接口实现类的代理对象
        Class[] interfaces = {Temp.class};

        //temp：代理类的对象
        Temp temp = (Temp) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new TempDaoProxy(new Templmpl()));
        System.out.println(temp.add(2, 3));
    }
}

//创建代理对象代码
class TempDaoProxy implements InvocationHandler {
    private Object obj; //需要使用被代理类的对象进行赋值
    public TempDaoProxy(Object obj) {
        this.obj = obj;
    }

    //当我们通过代理类的对象，调用方法 a 时，就会自动调用如下的方法：invoke()
    //将被代理类要执行的方法 a 的功能就声明在 invoke() 中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前面加的额外的方法 -> " + method.getName() + "，传递的参数 -> " + Arrays.toString(args));

        //method；即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        //obj：被代理类的对象
        Object invoke = method.invoke(obj, args);

        System.out.println("后面加的额外的方法 -> " + obj);
        return invoke;
    }
}