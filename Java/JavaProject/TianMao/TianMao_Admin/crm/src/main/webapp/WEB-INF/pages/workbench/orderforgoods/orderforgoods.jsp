<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">

    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="jquery/bs_pagination-master/css/jquery.bs_pagination.min.css">

    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript"
            src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="jquery/bs_pagination-master/js/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="jquery/bs_pagination-master/localization/en.js"></script>

    <script type="text/javascript">

        $(function () {


            //当容器加载完成之后，对容器调用工具函数
            $(".mydate").datetimepicker({
                language: 'zh-CN', //语言
                format: 'yyyy-mm-dd',//日期的格式
                minView: 'month', //可以选择的最小视图
                initialDate: new Date(),//初始化显示的日期
                autoclose: true,//设置选择完日期或者时间之后，日否自动关闭日历
                todayBtn: true,//设置是否显示"今天"按钮,默认是false
                clearBtn: true//设置是否显示"清空"按钮，默认是false
            });

            //当整个活动主页面加载完成，查询所有数据的第一页以及所有数据的总条数
            //收集参数
            queryActivityByConditionForPage(1,10);

            //给查询按钮添加单机事件
            $("#queryOrderBtn").click(function () {
                //查询所有符合条件数据的第一页以及所有符合条件数据的总条数;
                queryActivityByConditionForPage(1, $("#demo_pag1").bs_pagination('getOption', 'rowsPerPage'));
            })

            //给全选按钮添加单击事件
            $("#checkAll").click(function () {
                //如果全选按钮是选中状态 列表中所有的checkbox都选中

                $("#tBody input[type='checkbox']").prop("checked", this.checked);


            });


            $("#tBody").on("click", "input[type='checkbox']", function () {
                if ($("#tBody input[type='checkbox']").size() == $("#tBody input[type='checkbox']:checked").size()) {
                    $("#checkAll").prop("checked", true);
                } else {//如果列表中的所有checkbox至少有一个没选中，则"全选"按钮也取消
                    $("#checkAll").prop("checked", false);
                }
            })


            //给删除按钮添加单机事件
            $("#deleteOrderBtn").click(function () {
                //收集参数
                //获取列表中所有被选中的checkbox
                var checkkedIds = $("#tBody input[type='checkbox']:checked");
                if (checkkedIds.size() == 0) {
                    alert("请选择要删除的订单");
                    return;
                }
                if (window.confirm("确定删除吗?") == true) {
                    //遍历取得所有id值
                    var ids = "";
                    $.each(checkkedIds, function (index, obj) {
                        ids += "id=" + this.value + "&";
                    });
                    ids = ids.substr(0, ids.length - 1);
                    //发送异步请求

                    $.ajax({
                        url: 'workbench/order/deleteorderByIds.do',
                        data: ids,
                        type: 'post',
                        dataType: 'json',
                        success: function (data) {
                            if (data.code == "1") {
                                //刷新市场活动列表，显示第一页数据，保持每页显示条数不变
                                queryActivityByConditionForPage(1, $("#demo_pag1").bs_pagination('getOption', 'rowsPerPage'))
                            } else {
                                //提示信息
                                alert(data.message);
                            }

                        }
                    });
                }
            });

            //给修改按钮添加单机事件
            $("#editOrderBtn").click(function () {
                //收集参数
                //获取列表中获取的checkbox
                var checkIds = $("#tBody input[type='checkbox']:checked");
                if (checkIds.size() == 0) {
                    alert("请选择需要修改的订单");
                    return;
                }
                if (checkIds.size() > 1) {
                    alert("每次只能修改一条数据");
                    return;
                }
                var id = checkIds.val();
                //发送请求
                $.ajax({
                    url: 'workbench/order/queryOrderById.do',
                    data: {
                        id: id
                    },
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {
                        //把市场活动的信息显示在修改的模态窗口上
                        $("#edit-oid").val(data.oid);
                        $("#edit-marketOrderName").val(data.recvName);
                        $("#edit-marketOrderAdress").val(data.recvAddress);
                        $("#edit-phone").val(data.recvPhone);
                        $("#editOrderModal").modal("show");
                    }

                });

            });

            //给更新按钮添加单机事件
            $("#saveEditOrder").click(function () {
                var oid = $("#edit-oid").val();
                var recvName=$.trim($("#edit-marketOrderName").val());
                var recvAddress=$.trim($("#edit-marketOrderAdress").val());
                var recvPhone = $("#edit-phone").val();

                //表单验证
                if (recvName == "") {
                    alert("收货人姓名");
                    return;
                }

                if (recvAddress == "") {
                    alert("地址不能为空");
                    return;
                }
                if (recvPhone == "") {
                    alert("收货人电话号码不能为空");
                    return;
                }

                //发送请求
                $.ajax({
                   url:'workbench/order/saveCreateorder.do',
                    data:{
                        oid:oid,
                        recvName:recvName,
                        recvAddress:recvAddress,
                        recvPhone:recvPhone
                    },
                    type:'post',
                    dataType:'json',
                    success:function (data) {
                        if(data.code=="1"){
                            $("#editOrderModal").modal("hide");
                            //刷新市场活动列表,保持页号和每页显示条数都不变
                            queryActivityByConditionForPage( $("#demo_pag1").bs_pagination('getOption', 'currentPage'), $("#demo_pag1").bs_pagination('getOption', 'rowsPerPage'))
                        }else{
                            alert(data.message);
                            $("#editOrderModal").modal("show");
                        }

                    }
                });
            });


                $("#exportOrderAllBtn").click(function () {
                    //发送同步请求
                    window.location.href='workbench/order/exportorderAll.do';
                })
        });


        function queryActivityByConditionForPage(pageNo, pageSize) {
            //收集参数
            var recvName = $("#order-OwnerName").val();

            //var pageNo=1;
            //var pageSize=10;
            //发送请求
            $.ajax({
                url: 'workbench/order/queryCountOfOrderByCondition.do',
                data: {
                    recvName: recvName,
                    pageNo: pageNo,
                    pageSize: pageSize
                },
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    //显示总条数
                    //$("#totalRowsB").text(data.totalRows);
                    //显示市场活动的列表
                    //遍历activityList，拼接所有行数据
                    var htmlStr = "";
                    $.each(data.OrdersList, function (index, obj) {
                        htmlStr += "<tr class=\"active\">";
                        htmlStr += "	<td><input type=\"checkbox\"  value=\"" + obj.oid + "\"/></td>";
                        htmlStr += "     <td>" + obj.createdUser + "</td>";
                        htmlStr += "    <td>" + obj.totalPrice + "</td>";
                        htmlStr += "    <td>" + obj.createdTime + "</td>";
                        htmlStr += "    <td>" + obj.recvPhone + "</td>";
                        htmlStr += "    <td>" + obj.recvName+ "</td>";
                        htmlStr += "    <td>" + obj.recvAddress + "</td>";
                        htmlStr += "   </tr>";
                    });
                    $("#tBody").html(htmlStr);

                    //取消全选按钮
                    $("#checkAll").prop("checked", false);

                    //计算总页数
                    var totalPages = 1;
                    if (data.totalRows % pageSize == 0) {
                        totalPages = data.totalRows / pageSize;
                    } else {
                        totalPages = parseInt(data.totalRows / pageSize) + 1;
                    }

                    //对容器调用bs_pagination工具函数，显示翻页信息
                    $("#demo_pag1").bs_pagination({
                        currentPage: pageNo,//当前页号,相当于pageNo

                        rowsPerPage: pageSize,//每页显示条数,相当于pageSize
                        totalRows: data.totalRows,//总条数
                        totalPages: totalPages,  //总页数,必填参数.

                        visiblePageLinks: 5,//最多可以显示的卡片数

                        showGoToPage: true,//是否显示"跳转到"部分,默认true--显示
                        showRowsPerPage: true,//是否显示"每页显示条数"部分。默认true--显示
                        showRowsInfo: true,//是否显示记录的信息，默认true--显示

                        //用户每次切换页号，都自动触发本函数;
                        //每次返回切换页号之后的pageNo和pageSize
                        onChangePage: function (event, pageObj) { // returns page_num and rows_per_page after a link has clicked
                            //js代码
                            //alert(pageObj.currentPage);
                            //alert(pageObj.rowsPerPage);
                            queryActivityByConditionForPage(pageObj.currentPage, pageObj.rowsPerPage);
                        }
                    });
                }
            });
        }
    </script>
</head>
<body>

<!-- 创建订单的模态窗口 -->
<div class="modal fade" id="createOrderModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel1">创建订单</h4>
            </div>
            <div class="modal-body">

                <form id="createActivityForm" class="form-horizontal" role="form">

                    <div class="form-group">
                        <label for="create-marketActivityName1" class="col-sm-2 control-label">名称<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-marketActivityName1">
                        </div>


                        <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-marketActivityName">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control mydate" id="create-startTime" readonly>
                        </div>
                        <label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control mydate" id="create-endTime" readonly>
                        </div>
                    </div>
                    <div class="form-group">

                        <label for="create-cost" class="col-sm-2 control-label">成本</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-cost">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create-describe" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10" style="width: 81%;">
                            <textarea class="form-control" rows="3" id="create-describe"></textarea>
                        </div>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="saveCreateActivityBtn">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- 修改市场活动的模态窗口 -->
<div class="modal fade" id="editOrderModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">修改订单</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" role="form">
                    <input type="hidden" id="edit-oid">
                    <div class="form-group">
                        <label for="edit-marketOrderName" class="col-sm-2 control-label">收货人<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-marketOrderName" value="发传单">
                        </div>

                        <label for="edit-marketOrderAdress" class="col-sm-2 control-label">收获地址<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-marketOrderAdress" value="发传单">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-phone" class="col-sm-2 control-label">收货人电话号码<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control " id="edit-phone" value="2020-10-10">
                        </div>

                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="saveEditOrder">更新</button>
            </div>
        </div>
    </div>
</div>

<!-- 导入市场活动的模态窗口 -->
<div class="modal fade" id="importActivityModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">导入市场活动</h4>
            </div>
            <div class="modal-body" style="height: 350px;">
                <div style="position: relative;top: 20px; left: 50px;">
                    请选择要上传的文件：<small style="color: gray;">[仅支持.xls]</small>
                </div>
                <div style="position: relative;top: 40px; left: 50px;">
                    <input type="file" id="activityFile">
                </div>
                <div style="position: relative; width: 400px; height: 320px; left: 45% ; top: -40px;">
                    <h3>重要提示</h3>
                    <ul>
                        <li>操作仅针对Excel，仅支持后缀名为XLS的文件。</li>
                        <li>给定文件的第一行将视为字段名。</li>
                        <li>请确认您的文件大小不超过5MB。</li>
                        <li>日期值以文本形式保存，必须符合yyyy-MM-dd格式。</li>
                        <li>日期时间以文本形式保存，必须符合yyyy-MM-dd HH:mm:ss的格式。</li>
                        <li>默认情况下，字符编码是UTF-8 (统一码)，请确保您导入的文件使用的是正确的字符编码方式。</li>
                        <li>建议您在导入真实数据之前用测试文件测试文件导入功能。</li>
                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="importActivityBtn" type="button" class="btn btn-primary">导入</button>
            </div>
        </div>
    </div>
</div>


<div>
    <div style="position: relative; left: 10px; top: -10px;">
        <div class="page-header">
            <h3>订单管理</h3>
        </div>
    </div>
</div>
<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
    <div style="width: 100%; position: absolute;top: 5px; left: 10px;">

        <div class="btn-toolbar" role="toolbar" style="height: 80px;">
            <form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">收货人名字</div>
                        <input class="form-control" type="text" id="order-OwnerName">
                    </div>
                </div>

                <button id="queryOrderBtn" type="button" class="btn btn-default">查询</button>

            </form>
        </div>
        <div class="btn-toolbar" role="toolbar"
             style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
            <div class="btn-group" style="position: relative; top: 18%;">

                <button type="button" class="btn btn-default" id="editOrderBtn" data-toggle="modal"
                <%--data-target="#editOrderModal">--%><span class="glyphicon glyphicon-pencil"></span> 修改订单</button>
                <button type="button" class="btn btn-danger" id="deleteOrderBtn"><span
                        class="glyphicon glyphicon-minus"></span> 删除订单
                </button>
            </div>
            <div class="btn-group" style="position: relative; top: 18%;">

                <button id="exportOrderAllBtn" type="button" class="btn btn-default"><span
                        class="glyphicon glyphicon-export"></span> 下载订单数据列表
                </button>

            </div>
        </div>
        <div style="position: relative;top: 10px;">
            <table class="table table-hover">
                <thead>
                <tr style="color: #B3B3B3;">
                    <td><input type="checkbox" id="checkAll"/></td>
                    <td>订单所有者</td>
                    <td>订单价格</td>
                    <td>订单创建时间</td>
                    <td>收获人电话</td>
                    <td>收货人姓名</td>
                    <td>详情地址</td>


                </tr>
                </thead>
                <tbody id="tBody">
                <%--<tr class="active">
                    <td><input type="checkbox" /></td>
                    <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.html';">发传单</a></td>
                    <td>zhangsan</td>
                    <td>2020-10-10</td>
                    <td>2020-10-20</td>
                </tr>
                <tr class="active">
                    <td><input type="checkbox" /></td>
                    <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.html';">发传单</a></td>
                    <td>zhangsan</td>
                    <td>2020-10-10</td>
                    <td>2020-10-20</td>
                </tr>--%>
                </tbody>
            </table>
            <div id="demo_pag1"></div>
        </div>

        <%--<div style="height: 50px; position: relative;top: 30px;">
            <div>
                <button type="button" class="btn btn-default" style="cursor: default;">共<b id="totalRows">50</b>条记录</button>
            </div>
            <div class="btn-group" style="position: relative;top: -34px; left: 110px;">
                <button type="button" class="btn btn-default" style="cursor: default;">显示</button>
                <div class="btn-group">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        10
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">20</a></li>
                        <li><a href="#">30</a></li>
                    </ul>
                </div>
                <button type="button" class="btn btn-default" style="cursor: default;">条/页</button>
            </div>
            <div style="position: relative;top: -88px; left: 285px;">
                <nav>
                    <ul class="pagination">
                        <li class="disabled"><a href="#">首页</a></li>
                        <li class="disabled"><a href="#">上一页</a></li>
                        <li class="active"><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">下一页</a></li>
                        <li class="disabled"><a href="#">末页</a></li>
                    </ul>
                </nav>
            </div>
        </div>--%>

    </div>

</div>
</body>
</html>
