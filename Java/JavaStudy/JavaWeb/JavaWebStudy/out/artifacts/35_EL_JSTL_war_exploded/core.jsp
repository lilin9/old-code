<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Student" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MrLi
  Date: 2021/12/16
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- set标签可以往域中保存数据 -->
保存之前：${ requestScope.abc }

<c:set scope="request" var="abc" value="abc"/>
保存之后：${ requestScope.abc }

<hr>

<!--
if标签用来做if判断
test属性表示判断的条件（使用EL表达式输出）
-->
<c:if test="${12 == 12}">
    <h1>12 等于 12 成立</h1>
</c:if>

<c:if test="${12 == 12}">
    <h1>12 不等于 12 成立</h1>
</c:if>

<hr>

<%
request.setAttribute("height", 178);
%>
<c:choose>
    <c:when test="${requestScope.height > 190}">
        <h1>好大</h1>
    </c:when>

    <c:when test="${requestScope.height > 170}">
        <h1>还行</h1>
    </c:when>

    <c:otherwise>
        <h1>好小</h1>
    </c:otherwise>
</c:choose>

<hr>

<c:forEach var="i" begin="1" end="10">
    foreach遍历：${ i }
</c:forEach>

<hr>

<!-- 遍历数组 -->
<%
    request.setAttribute("arr", new String[]{"胡歌","霍建华","吴磊","刘亦菲","尊龙"});
%>
<c:forEach items="${requestScope.arr}" var="item">
    遍历数组：${item}
</c:forEach>

<hr>

<!-- 遍历List集合 -->
<%
    List<Student> list = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
        list.add(new Student(i+1, "name"+i, "abc"+1,15+i, 12345+i));
    }

    request.setAttribute("list", list);
%>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>password</th>
        <th>age</th>
        <th>phone</th>
    </tr>
    <c:forEach items="${requestScope.list}" var="each">
        <tr>
            <td>${each.id}</td>
            <td>${each.name}</td>
            <td>${each.password}</td>
            <td>${each.age}</td>
            <td>${each.phone}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
