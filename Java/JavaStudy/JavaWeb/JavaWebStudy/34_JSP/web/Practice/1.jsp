<%--
  Created by IntelliJ IDEA.
  User: MrLi
  Date: 2021/12/12
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>九九乘法表</title>

    <style>
        table {
            width: 650px;
            height: 200px;
        }
    </style>
</head>
<body>
<table>
    <caption><b>九九乘法表</b></caption>
    <% for (int i = 1; i <= 9; i++) { %>
        <tr>
        <% for (int j = 1; j <= i; j++) { %>
           <td><%= j + "*" + i + "=" + i*j %></td>
        <% } %>
        </tr>
    <% } %>
</table>
</body>
</html>
