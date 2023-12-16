<%--
  Created by IntelliJ IDEA.
  User: MrLi
  Date: 2022/1/16
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
这是 login.jsp 登录页面<br>
<form action="http://8080:localhost/38_Filter/loginServlet" method="get">
    用户名：<label><input type="text" name="username"></label> <br>
    密&nbsp&nbsp&nbsp码：<label><input type="password" name="password"></label> <br>
    <input type="submit" value="登录">
</form>
</body>
</html>
