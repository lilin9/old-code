<%--
  Created by IntelliJ IDEA.
  User: MrLi
  Date: 2021/12/15
  Time: 8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setAttribute("key", "值");
%>
表达式脚本输出key的值是：<%= request.getAttribute("key") %><br>

EL表达式输出key的值是：${key}
</body>
</html>
