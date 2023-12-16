package eg15_IO.Program;

/*
一、网络编程的两个主要问题：
    1、如何准确地定位网络上一台或多台主机；定位主机上的特定的应用
    2、找到主机后如何可靠高效地进行数据传输

二、网络编程中的两个要素：
    1、对应问题一：IP和端口号
    2、对应问题二：提供网络通信协议；TCP/IP参考模型（应用层、传输层、网络层、物理+数据连接层）

三、通信要素一：IP和端口号
    1、IP：唯一的标识 Internet 上的计算机（通信实体）
    2、在Java上使用InetAddress类代表IP
    3、如何实例化InetAddress：
        getByName(String host)、getLocalHost()、getHostName() / getHostAddress
    4、端口号：正在计算机上运行的进程
        要求：不同的进程有不同的端口号
        范围：被规定为一个 16 位的整数 0 ~ 65535
    5、端口号与IP地址的组合得出一个网络套接字：Socket
 */

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class IO6_IOInternetProgramming {
    public static void main(String[] args) {
        try {
            InetAddress inet = InetAddress.getByName("www.baidu.com");
            System.out.println(inet);

            // 获取本机地址
            InetAddress inet1 = InetAddress.getLocalHost();
            System.out.println(inet1);

            System.out.println(inet.getHostName());
            System.out.println(inet.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    // 从网络链接中下载资源(图片)
    @Test
    public void testUrl(){
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            URL url = new URL("http://img.pconline.com.cn/images/upload/upc/tx/itbbs/1509/07/c37/12291697_1441612877625.jpg");
            URLConnection openConnection = url.openConnection();

            openConnection.connect();

            is = openConnection.getInputStream();
            fos = new FileOutputStream("src\\eg15_IO\\Data\\5.jpg");

            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer)) != -1){
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
