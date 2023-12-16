import java.io.*;

/**
 * Created by MrLi on 2021/12/31/20:57
 *
 * 使用 FileInputStream 和 FileOutputStream (字节流)拷贝一个文本文件
 */
public class FileInputStream_ {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            String path1 = "06_CopyFile\\Data\\网易云音乐精彩评论.txt";
            String path2 = "06_CopyFile\\Data\\FileInputStreamCopy.txt";
            File file1 = new File(path1);
            File file2 = new File(path2);
            fileInputStream = new FileInputStream(file1);
            fileOutputStream = new FileOutputStream(file2);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
                System.out.println("程序执行成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) fileInputStream.close();
                if (fileOutputStream != null) fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
