<%@ page import="Person" pageEncoding="utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: MrLi
  Date: 2021/12/15
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Person person = new Person();
    person.setName("Tony");
    person.setPhones(new String[]{"123", "456", "789"});

    List<String> cities = new ArrayList<>();
    cities.add("北京");
    cities.add("上海");
    cities.add("香港");
    cities.add("杭州");
    person.setCities(cities);

    Map<String, Object> map = new HashMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");
    map.put("key4", "value4");
    person.setMap(map);

    pageContext.setAttribute("person", person);
%>

输出：${person}
输出Person的phones数组:${person.phones} <br>
输出Person的phones数组属性值:${person.phones[0]} <br>
输出Person的Map集合：${person.map} <br>
输出Person的Map集合中某个key的值：${person.map.key1} <br>
</body>
</html>














