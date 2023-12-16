<%--
  Created by IntelliJ IDEA.
  User: MrLi
  Date: 2022/1/12
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="http://localhost:8080/37_cookie_session/loginServlet" method="get">
    用户名：<label><input type="text" name="userName" id="username" value="${cookie.get("username").value}"></label> <br>
    密码：<label><input type="password" name="password" id="password"></label> <br>
    <input type="submit" value="提交">
</form>
</body>
</html>
