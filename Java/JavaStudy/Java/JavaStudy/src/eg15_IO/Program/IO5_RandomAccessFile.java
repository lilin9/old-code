package eg15_IO.Program;

/*
RandomAccessFile的使用：
1、RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
2、RandomAccessFile既可以作为一个输入流，又可以作为一个输出流

3、如果RandomAccessFIle作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建
   如果写出的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头覆盖）

4、可以通过相关的操作，实现RandomAccessFile"插入"数据的效果
 */

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class IO5_RandomAccessFile {
    @Test
    public void test(){
        RandomAccessFile rAf = null;
        RandomAccessFile rAf1 = null;
        try {
            rAf = new RandomAccessFile(new File("D:\\Java\\Practice\\src\\eg15_IO\\Data\\1.jpg"), "r");
            rAf1 = new RandomAccessFile(new File("D:\\Java\\Practice\\src\\eg15_IO\\Data\\4.jpg"), "rw");

            byte[] buffer = new byte[1024];
            int len;
            while((len = rAf.read(buffer)) != -1) rAf1.write(buffer, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rAf1 != null) rAf1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (rAf != null) rAf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    使用RandomAccessFile实现数据文本插入的效果
     */
    @Test
    public void test1(){
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(new File("src\\eg15_IO\\Data\\Text1.txt"), "rw");
            raf.seek(3); // 将指针位置调到角标为3的位置

            StringBuilder builder = new StringBuilder((int) new File("src\\eg15_IO\\Data\\Text1.txt").length());
            byte[] buffer = new byte[10];
            int len;
            while ((len = raf.read(buffer)) != -1){
                builder.append(new String(buffer, 0, len));
            }

            // 调回指针，写入"xyz"
            raf.seek(3);
            raf.write("xyz".getBytes());

            // 将StringBuilder中的数据写入到文件中
            raf.write(builder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (raf != null) raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
