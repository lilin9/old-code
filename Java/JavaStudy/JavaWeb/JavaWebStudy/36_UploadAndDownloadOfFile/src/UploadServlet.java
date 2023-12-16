import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
/**
 * Created by MrLi on 2021/12/18/9:35
 */
public class UploadServlet extends HttpServlet {
/**
 * @author MrLi
 * @create 2021/12/18 9:37
 * @description 用来处理文件上传
 */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setCharacterEncoding("UTF-8");

        //1、判断上传的数据是否是多段数据（只有是多段数据，才可以文件上传）
        if (ServletFileUpload.isMultipartContent(req)) {
            //创建FileItemFactory工厂实现类
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据的工具类ServletFileUpload类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                //解析上传的数据，得到每一个表单项FileItem
                List<FileItem> list = servletFileUpload.parseRequest(req);
                //循环判断，每一个表单项，是普通类型还是需要上传的文件
                for (FileItem each : list) {
                    if (each.isFormField()) {
                        //普通表单项
                        System.out.println("表单项的name属性值：" + each.getFieldName());
                        System.out.println("表单项的value属性值：" + each.getString("UTF-8"));
                    } else {
                        //需要上传的文件
                        System.out.println("表单项的name属性值：" + each.getFieldName());
                        System.out.println("上传的文件名：" + each.getName());

                        each.write(new File("D:\\Java\\JavaWeb\\36_UploadAndDownloadOfFile\\Data\\" + each.getName()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
