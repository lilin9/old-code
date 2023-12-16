<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>

    <script type="text/javascript">
        $(function () {
            //为 删除按钮 添加单击事件，以提示用户是否确认删除商品
            $("a.deleteItem").click(function () {
                return confirm("确定是否删除《" + $(this).parent().parent().find("td:first").text() + "》?");
            })
            //为 清空购物车按钮 添加单击事件，以提示用户是否确认清空购物车
            $("a.clearCart").click(function () {
                return confirm("确定是否清空购物车?");
            })
            //为 修改输入框 绑定 onchange内容发生改变 事件
            $(".updateItem").change(function () {
                const id = $(this).attr("bookId");
                const name = $(this).parent().parent().find("td:first").text();
                const count = $(this).value;

                if (confirm("确定是否要修改《" + name +"》的数量为" + count + "？")) {
                    //向服务器发起请求，保存修改
                    location.href = "http://localhost:8080/28_BookCity/cartServlet?action=updateCount&count=" + count + "&id=" + id;
                } else {
                    //defaultValue 属性是表单项 Dom 对象属性，表示默认的 value 属性值
                    $(this).value = $(this).defaultValue;
                }
            })
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>

    <%--静态包含，登录 成功之后的菜单 --%>
    <%@ include file="/pages/common/login_success_menu.jsp" %>


</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>

        <%-- 如果购物车为空的情况 --%>
        <c:if test="${empty sessionScope.get('cart').get('items')}">
            <tr>
                <td colspan="5"><a href="index.jsp"> 当前购物车为空！</a></td>
            </tr>
        </c:if>
        <%-- 如果购物车为非空的情况 --%>
        <c:if test="${not empty sessionScope.get('cart').get('items')}">
            <c:forEach items="${sessionScope.cart.items}" var="entry">
                <tr>
                    <td>${entry.value.name}</td>
                    <td>
                        <label><input bookId="${entry.value.id}" class="updateCount" style="width: 40px" type="text" value="${entry.value.count}"></label>
                    </td>
                    <td>${entry.value.price}</td>
                    <td>${entry.value.totalPrice}</td>
                    <td><a href="cartServlet?action=updateItem&id=${entry.value.id}" class="deleteItem">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>

    </table>

    <%-- 如果购物车非空，输出页面的内容 --%>
    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a href="cartServlet?action=clear" id="clearCart">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>

</div>


<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>


</body>
</html>