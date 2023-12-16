<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" 
    CodeFile="books.aspx.cs" Inherits="bookstore.home"%>

<asp:Content ID="books" ContentPlaceHolderID="ContentPlaceHolder" Runat="Server">
    <script type="text/javascript">   
        function keyDown()   
        {          
            if(event.keyCode==13)   
            {   
                document.getElementById("Button1").click();
            }   
        }   
     </script>
    <form id="form1" runat="server">
   <div id="books-body">
       <div id="books-search-content">
            <%--<input id="books-search-inpput" type="text" placeholder="请输入关键字搜索书籍"/>--%>
            <asp:TextBox ID="KeywordsTextField" runat="server" placeholder="请输入关键字搜索书籍"></asp:TextBox>
            <asp:Button ID="Button1" runat="server" Text="查找" OnClick="Button1_Click1"/>
       </div>
       <div id="books-class-conetnt">
           <ul id="books-class-ul">
               <a href="books.aspx?classId=0"><li>全部</li></a>
               <asp:Repeater ID="RepeaterClass" runat="server">
                <ItemTemplate>
                    <a href="<%#"books.aspx?classId="+ Eval("CategoryId") %>"><li ><%# Eval("CategoryName") %></li></a>
                </ItemTemplate>
            </asp:Repeater>
           </ul>
           <div class="clear"></div>
       </div>

       <div id="books-main-content">
            <asp:Repeater ID="RepeaterBook" runat="server">
                <ItemTemplate>
                    <div class="books-main-item">
                       <a href="<%#"bookDetail.aspx?BookId="+ Eval("BookId") %>"><img src="<%# Eval("BookImage") %>"/></a>
                       <h6><%# Eval("BookName") %></h6>
                       <p>单价：<span class="price">￥<%# Eval("SalePrice") %></span></p>
                       <button>点击购买</button>
                   </div>
                </ItemTemplate>
            </asp:Repeater>
       
           <div class="clear"></div>
       </div>
   </div>
    </form>
</asp:Content>

