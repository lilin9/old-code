package eg15_IO.Program;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IO1_Fileread {
    /*
    将文本文件内容读入程序中，并输出到控制台

    注意：1、read()的理解：返回读入的一个字符。如果达到文件末尾，返回-1
         2、异常的处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理
         3、读入的文件一定要存在，否则会报FileNotFoundException
    */
    @Test
    public void testFileRead(){
        FileReader reader = null;

        try {
            // 1、实例化File类的对象，指明要操作的文件
            File file = new File("src\\eg15_IO\\Data\\Text.txt");

            // 2、提供具体的流
            reader = new FileReader(file);
            // 3、数据的读取1  read()：返回读入的一个字符。如果到达文件末尾，返回-1
//            int data = reader.read();
//            while (data != -1){
//                System.out.print((char)data);
//                data = reader.read();

            // 3、数据的读取2  read(char[] cbuf)：返回每次读入cbuf数组中的字符的个数。如果达到文件末尾，返回-1
            char[] cbuf = new char[5];
            int len;
            while ((len = reader.read(cbuf)) != -1){
                // 错误写法
//                for (int i=0 ;i< cbuf.length; i++) System.out.print(cbuf[i]);

                // 正确写法
                for (int i=0; i<len; i++) System.out.print(cbuf[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // 4、流的关闭
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
