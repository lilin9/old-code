<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: MrLi
  Date: 2021/12/11
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%-- jsp 声明脚本 --%>
    <%--练习：1、声明类属性--%>
    <%!
        private Integer id;
        private String name;
        private static Map<String, Object> map;
    %>
    <%--练习：2、声明static静态代码块--%>
    <%!
        static {
            map = new HashMap<>();
            map.put("key1", "value1");
            map.put("key2", "value2");
            map.put("key3", "value3");
            map.put("key4", "value4");
            map.put("key5", "value5");
        }
    %>
    <%--练习：3、声明类方法--%>
    <%!
        public int a() {
            return 123;
        }
    %>
    <%--练习：4、声明内部类--%>
    <%!
        public static class A {
            private Integer id;
            private String name;
        }
    %>


    <%-- jsp 表达式脚本 --%>
    <%--练习：1、输出整型--%>
    <%= 12 %>
    <%--练习：2、输出浮点型--%>
    <%= 12.3 %>
    <%--练习：3、输出字符串--%>
    <%= "love" %>
    <%--练习：3、输出对象--%>
    <%= new Date() %>


    <%-- jsp 代码脚本 --%>
    <%--练习：1、if 语句--%>
    <%--练习：2、for 循环语句--%>
    <%
        for (int temp = 0; temp <= 100; temp++) {
            if (temp % 2 == 0) System.out.println(temp + "是0到100以内的偶数！");
            else System.out.println(temp + "是0到100以内的奇数！");
        }
        %>

    <%--练习：3、翻译后java文件中 _jsService() 方法内的代码都可以写--%>
    <%
        String username = request.getParameter("username");
        System.out.println(username);
    %>
</body>
</html>