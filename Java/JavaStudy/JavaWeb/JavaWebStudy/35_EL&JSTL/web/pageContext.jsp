<%--
  Created by IntelliJ IDEA.
  User: MrLi
  Date: 2021/12/15
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%= request.getScheme() %><br>  <!-- 获取请求的协议 -->
<%= request.getServerName() %><br>  <!-- 获取服务器ip -->
<%= request.getServerPort() %><br>  <!-- 获取服务器端口 -->
<%= request.getContextPath() %><br>  <!-- 获取工程路径 -->
<%= request.getMethod() %><br>  <!-- 获取请求方法 -->
<%= request.getRemoteHost() %><br>  <!-- 获取客户端ip地址 -->
<%= session.getId() %><br>  <!-- 获取会话的id编号 -->

①、获取协议：${ pageContext.request.scheme }<br>
②、获取服务器ip：${pageContext.request.serverName}<br>
③、获取服务器端口：${pageContext.request.serverPort}<br>
④、获取工程路径：${pageContext.request.contextPath}<br>
⑤、获取请求方法：${pageContext.request.method}<br>
⑥、获取客户端ip地址：${pageContext.request.remoteHost}<br>
⑦、获取会话的id编号：${pageContext.session.id}<br>
</body>
</html>
