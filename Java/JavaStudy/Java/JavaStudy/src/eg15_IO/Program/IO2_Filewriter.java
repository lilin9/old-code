package eg15_IO.Program;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IO2_Filewriter {
    /*
    从内存中写出数据到硬盘文件里

    注意：1、输出操作，对应的File可以不存在。不会报异常
         2、File对应的硬盘中的文件如果不存在，在输出过程中，会自动创建文件；
            File对应的硬盘中的文件如果存在：
                    如果流使用的构造器是：FileWriter(file, false) / FileWriter(file)：对原有文件覆盖
                    如果流使用的构造器是：FileWriter(file, true)：不会对原有文件覆盖，而是在原有文件基础上追加内容

    */
    @Test
    public void testFileWriter(){
        FileWriter fileWriter = null;
        try {
            // 1、提供File类的对象，指明要写出的文件
            File file = new File("src\\eg15_IO\\Data\\Text.txt");

            // 2、提供FileWriter的对象，用于数据的写出
            fileWriter = new FileWriter(file);

            // 3、写出操作
            fileWriter.write("I have a dream!\n");
            fileWriter.write("I will change the world!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4、流资源的关闭
            try {
                if (fileWriter != null) fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test(){
        FileReader reader = null;
        try {
            File file = new File("src\\eg15_IO\\Data\\Text.txt");

            reader = new FileReader(file);

            char[] cbuf = new char[5];
            int len;

            while ((len = reader.read(cbuf)) != -1){
                for (int i = 0; i < len; i++) {
                    System.out.print(cbuf[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
