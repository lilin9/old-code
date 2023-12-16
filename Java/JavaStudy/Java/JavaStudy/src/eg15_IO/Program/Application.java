package eg15_IO.Program;

/*
一、流的分类：
1、操作数据单位：字节流、字符流
2、数据的流向：输入流、输出流
3、流的角色：节点流、处理流

二、流的体系结构
抽象基类                 节点流(或文件流)                                      缓冲流(处理流的一种)
InputStream             FileInputStream (read(byte[] buffer))              BufferedInputStream (read(byte[] buffer))
OutputStream            FileOutputStream (write(byte[] buffer,0,len))      BufferedOutputStream (write(byte[] buffer,0,len))
Reader                  FileReader (read(char[] cbuf))                     BufferedReader (read(char[] cbuf))
Writer                  FileWriter (write(char[] cbuf,0,len))              BufferedWriter (write(char[] cbuf,0,len))

结论：1、对于文本文件(.txt, .java, .c, .cpp...)，使用字符流处理
     2、对于非文本文件(.jpg, .png, .mp3, .mp4, .doc, .avi, ..ppt...)，使用字节流处理

转换流的使用：
    1、转换流：属于字符流
        InputStreamReader：将一个字节的输入流转换成字符的输入流
        OutputStreamWriter：将一个字符的输出流转换成字节的输出流
    2、作用：提供字节流与字符流之间的转换
    3、解码：字节、字节数组 --> 字符数组、字符串
       编码：字符数组、字符串 --> 字节、字节数组

    4、字符集
        ASCII：美国标准信息交换表。用一个字节的7位表示
        ISO08859-1：拉丁码表。欧洲码表。用一个字节的8位表示
        GB2312：中文编码表。最多两个字节编码所有字节
        GBK：中文编码表升级，融合了更多的中文文字符号。最多两个字节编码
        Unicode：国际标准码，融合了目前人类使用的所有字符。为每个字符分配唯一的字符码。所有的文字都用两个字节来表示
        UTF-8：变长的编码方式，可用1-4个字节来表示一个字符
 */

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Application {
    // 使用缓冲流实现图片的复制

    // 1、作用：提高流的读取、写入速度
    // 2、原因：内部提供了一个缓存区
    @Test
    public void BufferTest(){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            File file1 = new File("src\\eg15_IO\\Data\\2.jpg");
            File file2 = new File("src\\eg15_IO\\Data\\3.jpg");

            FileInputStream fis = new FileInputStream(file1);
            FileOutputStream fos = new FileOutputStream(file2);

            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[10];
            int len;
            while ((len = bis.read(buffer)) != -1) bos.write(buffer, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // 字符集的使用
    @Test
    public void char_set_Test(){
        FileInputStream fis = null;
        InputStreamReader isr = null;
        try {
            fis = new FileInputStream("src\\eg15_IO\\Data\\Text.txt");

//        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8); //使用系统默认的字符集
            //参数2指明了字符集，具体使用哪个字符集，取决于文件Text.txt保存时使用的字符集是什么
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);

            char[] cbuf = new char[23];
            int len;
            while ((len = isr.read(cbuf)) != -1){
                String str = new String(cbuf, 0, len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isr != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
