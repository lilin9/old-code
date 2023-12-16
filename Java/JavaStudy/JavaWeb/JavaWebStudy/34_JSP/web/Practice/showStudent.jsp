<%@ page import="java.util.List" %>
<%@ page import="pojo.Student" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 云梦
  Date: 2021/12/12
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>jsp输出表格里面包含学生信息</title>

    <style>
        caption {
            padding-top: 20px;
            padding-bottom: 20px;
        }

        table {
            width: 650px;
            height: 650px;
            text-align: center;
            border-collapse: collapse;
        }

        tr td {
            border: 1px black solid;
        }

        tr th {
            border: 1px black solid;
        }
    </style>
</head>
<body>
    <%
        List<Student> studentList = (List<Student>) request.getAttribute("stuList");
    %>
    <table>
        <caption><b>遍历输出10个学生信息到表格中</b></caption>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Age</th>
            <th>Phone</th>
            <th>Change</th>
        </tr>
            <% for (Student student : studentList) { %>
                <tr>
                    <td><%= student.getId() %></td>
                    <td><%= student.getName() %></td>
                    <td><%= student.getAge() %></td>
                    <td><%= student.getPhone() %></td>
                    <td>delete, update</td>
                </tr>
            <% } %>
    </table>
</body>
</html>
