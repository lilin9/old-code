<%@ Page Title="" Language="C#" MasterPageFile="MasterPage.master" AutoEventWireup="true" 
    CodeFile="add.aspx.cs" Inherits="add"%>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder" runat="server">
   <div id="add-body">
       <div id="add-content">
           <div id="register_page">
               <table>
                   <thead>
                       <tr>
                           <td colspan="2">填写电动车详情</td>
                       </tr>
                   </thead>
                   <tbody>
                       <tr>
                           <td>标题：</td><td><asp:TextBox ID="textTitle" runat="server" Width="184px"></asp:TextBox></td>
                       </tr>
                       <tr>
                           <td>售价：</td><td><asp:TextBox ID="txtSalePrice"  runat="server" Width="180px"></asp:TextBox></td>
                       </tr>
                       <tr>
                           <td>库存：</td><td><asp:TextBox ID="txtQuantity" runat="server" Width="183px"></asp:TextBox></td>
                       </tr>
                       <tr>
                           <td>类别：</td><td><asp:DropDownList ID="DropDownList1" runat="server"> </asp:DropDownList></td>
                       </tr>
                       <tr>
                           <td>图片：</td><td><asp:FileUpload ID="FileUpload1" runat="server" />250*360px,jpg</td>
                       </tr>
                       <tr>
                           <td>描述：</td><td><asp:TextBox ID="txtDescription" runat="server" Height="40px" TextMode="MultiLine"></asp:TextBox></td>
                       </tr>
                       <tr>
                           <td colspan="2"> <asp:Button ID="btnAdd" runat="server" Text="添加" onclick="btnAdd_Click" /></td>
                       </tr>
                   </tbody>
               </table>
      
       </div>
   </div>   
</asp:Content>