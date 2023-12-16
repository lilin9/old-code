package eg15_IO.Program;

import org.junit.Test;

import java.io.*;

/*
对象流的使用
1、ObjectInputStream和ObjectOutputStream
2、作用：用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可以把Java中的对象写入到数据流中，
        也可以把对象从数据源中还原回来

3、序列化机制：
    对象序列化机制允许把内存中的Java对象转换成与平台无关的二进制流，从而允许把这种二进制流持久地保存在磁盘上，
    或者通过网络将这种二进制流传输到另一个网络节点。当其他程序获取了这种二进制流，就可以恢复成原来的Java对象。

4、如果一个Java对象需要序列化，需要满足的要求：
    (1)、需要实现接口：Serializable(标识接口)
    (2)、当前类提供一个全局常量：Static final long serialVersionUID = 123456789L;
    (3)、除了当前Person类需要实现Serializable接口之外，还必须保证其内部属性也必须是可序列化的(默认情况下，基本数据类型可序列化)
    (4)、ObjectOutputStream和ObjectInputStream不能序列化static和transient修饰的成员变量
 */

public class IO4_ObjectInputOutputStream {
    /*
    序列化过程：将内存中的Java对象保存到磁盘或者通过网络传输出去
    通过ObjectOutputStream实现
     */
    @Test
    public void testOutput(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("src\\eg15_IO\\DAta\\Object.dat"));

            oos.writeObject("我爱洗澡，皮肤好好！");
            oos.flush(); // 显示的刷新操作
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
    反序列化：将磁盘文件或网络中的对象还原为内存中的一个Java对象
    使用ObjectInputStream实现
     */
    @Test
    public void testInput(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("src\\eg15_IO\\DAta\\Object.dat"));

            Object obj = ois.readObject();
            String str = (String)obj;

            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
