<%--
  Created by IntelliJ IDEA.
  User: MrLi
  Date: 2021/12/15
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
输出请求参数username的值：${ param.username } <br>
输出请求参数username的值：${ paramValues.username[0] } <br>

<hr>

获取请求头【User-Agent】的值：${ header['User-Agent'] } <br>
获取请求头【Cookie】的值：${ header['Cookie'] } <br>
获取请求头【User-Agent】的值：${ headerValues['User-Agent'][0] } <br>

<hr>

获取Cookie的名称：${ cookie.JSESSIONID.name } <br>
获取Cookie的值：${ cookie.JSESSIONID.value } <br>

<hr>

${ initParam.username } <br>
${ initParam.url } <br>
</body>
</html>
