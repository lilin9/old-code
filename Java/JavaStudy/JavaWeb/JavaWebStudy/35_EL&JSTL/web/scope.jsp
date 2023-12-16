<%--
  Created by IntelliJ IDEA.
  User: MrLi
  Date: 2021/12/15
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    pageContext.setAttribute("key1", "pageContext");
    request.setAttribute("key1", "request");
    session.setAttribute("key1", "session");
    application.setAttribute("key1", "application");
%>

${applicationScope.key1}
</body>
</html>
