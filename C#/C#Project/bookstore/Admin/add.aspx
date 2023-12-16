<%@ Page Title="" Language="C#" MasterPageFile="MasterPage.master" AutoEventWireup="true" 
    CodeFile="add.aspx.cs" Inherits="add"%>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder" runat="server">
   <div id="add-body">
       <div id="add-content">
           <div id="register_page">
               <table>
                   <thead>
                       <tr>
                           <td colspan="2">填写书籍详情</td>
                       </tr>
                   </thead>
                   <tbody>
                       <tr>
                           <td>书名：</td><td><asp:TextBox ID="txtBookName" runat="server" Width="184px"></asp:TextBox></td>
                       </tr>
                       <tr>
                           <td>作者：</td><td><asp:TextBox ID="txtAuthor" runat="server" Width="181px"></asp:TextBox></td>
                       </tr>
                       <tr>
                           <td>出版社：</td><td><asp:TextBox ID="txtPublisher" runat="server" Width="163px"></asp:TextBox></asp:TextBox></td>
                       </tr>
                       <tr>
                           <td>出版日期：</td><td><asp:TextBox ID="txtPublishDate" runat="server"></asp:TextBox></td>
                       </tr>
                       <tr>
                           <td>ISBN：</td><td><asp:TextBox ID="txtISBN" runat="server" Width="180px"></asp:TextBox></td>
                       </tr>
                       <tr>
                           <td>售价：</td><td><asp:TextBox ID="txtSalePrice"  runat="server" Width="180px"></asp:TextBox></td>
                       </tr>
                       <tr>
                           <td>数量：</td><td><asp:TextBox ID="txtQuantity" runat="server" Width="183px"></asp:TextBox></td>
                       </tr>
                       <tr>
                           <td>类别：</td><td><asp:DropDownList ID="DropDownList1" runat="server"> </asp:DropDownList></td>
                       </tr>
                       <tr>
                           <td>推荐：</td><td><asp:RadioButton ID="rdoYes" runat="server" GroupName="Recommend" Text="是" 
            Checked="True" />
        <asp:RadioButton ID="rdoNo" runat="server" GroupName="Recommend" Text="否" /></td>
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