<%--
  Created by IntelliJ IDEA.
  User: MrLi
  Date: 2021/12/18
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>upload</title>
</head>
<body>
<form action="http://localhost:8080/36_UploadAndDownloadOfFile/uploadServlet" method="post" enctype="multipart/form-data">
    用户名：<label><input type="text" name="username" /></label> <br>
    头像：<input type="file" name="photo"> <br>
    <input type="submit" value="上传">
</form>
</body>
</html>
