<%@ Page Language="C#" AutoEventWireup="true" CodeFile="register.aspx.cs" Inherits="bookstore.register" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新世界书城--注册</title>
    <style>
        #register_page{
            width:350px;  
            height:auto;  
            position: absolute;  
            top: 50%;  
            left: 50%;  
            -webkit-transform:translate(-50%,-50%);  
            background: rgba(204, 204, 204, 0.14);
            text-align:center;
        }
        #register-head{
            text-align: center;
            display: block;
            height: 40px;
            line-height: 40px;
            background: rgba(231, 79, 77, 0.92);
            margin: 0;
            color: #fff;
        }
        input[type="submit"]{
            background: #e74f4d;
            color: #fff;
            width: 100px;
            line-height: 30px;
            border: none;
            margin-top: 12px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
    <div id="register_page">
              <p id="register-head" style="text-align:center">注册新世界书城账号</p>
        <p>昵称：<asp:TextBox ID="txtUserName" runat="server" Width="250px"></asp:TextBox></p>                
        <p>密码：<asp:TextBox ID="txtPassword" runat="server" TextMode="Password" Width="250px"></asp:TextBox></p>              
        <p>性别：<asp:RadioButton ID="rdoMale" runat="server" Text="男" GroupName="Sex" />
            <asp:RadioButton ID="rdoFemale" runat="server" Text="女" GroupName="Sex" /></p>
        <p>姓名：<asp:TextBox ID="txtName" runat="server" Width="250px"></asp:TextBox></p>              
        <p>电话：<asp:TextBox ID="txtTelephone" runat="server" Width="250px"></asp:TextBox></p>
        <p>地址：<asp:TextBox ID="txtAddress" runat="server" Width="250px"></asp:TextBox></p>
        <p style="text-align:center"><asp:Button ID="txtRegister" runat="server" Text="注册" 
                onclick="txtRegister_Click" /></p>  
    </div>
    </form>
</body>
</html>
