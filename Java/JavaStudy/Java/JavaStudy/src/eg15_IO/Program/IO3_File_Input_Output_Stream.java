package eg15_IO.Program;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
测试FileInputStream和FileOutputStream的使用
 */

public class IO3_File_Input_Output_Stream {
    // 通过 FileInputStream 和 FileOutputStream 实现对图片的复制
    @Test
    public void test(){
        FileInputStream iput = null;
        FileOutputStream oput = null;
        try {
            File file1 = new File("src\\eg15_IO\\Data\\1.jpg");
            File file2 = new File("src\\eg15_IO\\Data\\2.jpg");

            iput = new FileInputStream(file1);
            oput = new FileOutputStream(file2);

            byte[] buffer = new byte[3];
            int len;
            while ((len = iput.read(buffer)) != -1){
                oput.write(buffer, 0, len);
            }

            System.out.println("程序执行成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (iput != null) iput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (oput != null) oput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
