import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by MrLi on 2021/12/18/15:16
 */
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //（1）获取要下载的文件名
        String downloadFileName = "image.jpg";

		//（2）读取要下载的文件内容（通过ServletContext对象可以读取）
        ServletContext servletContext = getServletContext();

        //（3）在回传前，通过响应头告诉客户端返回的数据类型
        //获取要下载的文件类型
        String mimeType = servletContext.getMimeType("/file/" + downloadFileName);
        resp.setContentType(mimeType);

        //（4）还要告诉客户端收到的数据适用于下载使用的（通过响应头）。
        //Content-Disposition：响应头，表示收到的数据的处理方式
        //attachment：表示附件，用于下载
        resp.setHeader("Content-Disposition", "attachment;filename=" + downloadFileName);

        //（5）把下载的文件内容回传给客户端
        //获取文件路径
        InputStream resourceAsStream = servletContext.getResourceAsStream("/file/" + downloadFileName);
        //获取响应的输出流
        OutputStream outputStream = resp.getOutputStream();
        //获取输入流（resourceAsStream）中的全部数据，复制给输出流（outputStream），输出给客户端
        IOUtils.copy(resourceAsStream, outputStream);

    }
}
