<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>

<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">

    <title>工作台时间</title>

    <link rel="stylesheet" href="jquery/shizhong/index.css" media="screen" type="text/css" />

    <style>
        input {
            width: 40px;
            height: 20px;
        }
        #remainTime{
            background-color: yellow;
            margin-top: -9px;
            font-size: 23px;
            text-align: center;


        }
        #remainTime span {
            color: red;
            font-weight: bold;
            margin-right: 10px;
        }
    </style>

</head>

<body>



<div class="fill">

    <div class="reference"></div>



    <div id="remainTime" >
       北京时间
    </div>

    <div class="clock" id="utility-clock">
        <div class="centre">
            <div class="dynamic"></div>
            <div class="expand round circle-1"></div>
            <div class="anchor hour">
                <div class="element thin-hand"></div>
                <div class="element fat-hand"></div>
            </div>
            <div class="anchor minute">
                <div class="element thin-hand"></div>
                <div class="element fat-hand minute-hand"></div>
            </div>
            <div class="anchor second">
                <div class="element second-hand"></div>
            </div>
            <div class="expand round circle-2"></div>
            <div class="expand round circle-3"></div>
        </div>
    </div>
</div>

<script src="jquery/shizhong/index.js"></script>
<%--<script>
    (function show() {
        //1.设置目的时间
        var oYear = 2022
        var oMonth = 0
        var oDay = 0
        var oHour = 0
        var oMinute = 0
        var oSecond = 0
        var funtureDate = new Date(oYear, oMonth, oDay, oHour, oMinute, oSecond);
        // console.log(dateFormat(funtureDate))
        //2.设置定时器
        setInterval(function () {
            //3.获取现在的时间
            var presentDate = new Date();
            //4.获取时间戳
            var funtureTime = funtureDate.getTime();
            var presenTime = presentDate.getTime();
            //5.获取剩余的时间戳
            var allTime = funtureTime - presenTime;
            //6.把毫秒转换为秒
            var allSecond = parseInt(allTime / 1000);
            //7.获取剩余多少天
            var day = size(parseInt(allSecond / 3600 / 24));
            //8.获取剩余多少小时
            var hour = size(parseInt(allSecond / 3600 % 24));
            //9.获取剩余多少分钟
            var minute = size(parseInt(allSecond / 60 % 60));
            //10.获取剩余多少秒
            var second = size(parseInt(allSecond % 60));
            //11.注入：
            document.getElementById('one').innerHTML = day;
            document.getElementById('two').innerHTML = hour;
            document.getElementById('three').innerHTML = minute;
            document.getElementById('four').innerHTML = second;
        }, 1000);
        //如果数>=10,则在前面补0
        function size(num) {
            return num < 10 & num >= 0 ? '0' + num : num;
        }
    })()

</script>--%>

</body>

</html>
