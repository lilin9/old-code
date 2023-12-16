<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" 
    CodeFile="pay.aspx.cs" Inherits="bookstore.pay"%>

<asp:Content ID="pay" ContentPlaceHolderID="ContentPlaceHolder" Runat="Server">
    <div id="pay-body">
        <div id="pay-content">
            <form runat="server">
                <div id="register_page">
                    <p id="pay-head" style="text-align:center">快递信息</p>
                    <p>姓名：<asp:TextBox ID="txtName" runat="server" Width="250px"></asp:TextBox></p>              
                    <p>电话：<asp:TextBox ID="txtTelphone" runat="server" Width="250px"></asp:TextBox></p>
                    <p>地址：<asp:TextBox ID="txtAddress" runat="server" Width="250px"></asp:TextBox></p>
                    <p style="text-align:center">
                        <asp:Button ID="btnCheckOut" runat="server" onclick="btnCheckOut_Click"
                            Text="订单生成" />
                    </p>  
                </div>
            </form>  
        </div>
    </div>
</asp:Content>

