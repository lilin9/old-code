<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/5
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<div>
    <%-- manager/bookServlet?是要请求的资源的地址 --%>
    <%-- action=page是要调用服务器资源里的 page 方法 --%>
    <a href="manager/bookServlet?action=page">图书管理</a>
    <a href="order_manager.jsp">订单管理</a>
    <a href="index.jsp">返回商城</a>
</div>