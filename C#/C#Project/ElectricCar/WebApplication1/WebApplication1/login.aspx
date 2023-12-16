<%@ Page Language="C#" AutoEventWireup="true" CodeFile="login.aspx.cs" Inherits="bookstore.login" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>登陆特斯拉电动摩托之家</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <form id="form1" runat="server">
        <div class="model">

            <header class="bg-blue">特斯拉电动摩托之家</header>
            <div class="login-body">
              <div>
                <label for="username">账号</label>
                  <asp:TextBox ID="txtusername" runat="server"></asp:TextBox>
              </div>
              <div>
                <label for="password">密码</label>
                  <asp:TextBox ID="txtpassword" runat="server" TextMode="Password"></asp:TextBox>
              </div>
                <asp:Label ID="tip" runat="server"></asp:Label>
               <p style="text-align:right;color:#808080;font-size:13px;margin-right:28px;margin-top:-7px;"><a href="register.aspx">没有账号？前去注册-> </a></p>
        
              <div>
                  <asp:Button ID="loginBtn" runat="server" Text="登录" OnClick="login_click"/>
                  <input type="reset"/>
              </div>
            </div>

        </div>
    </form>
</body>
</html>
