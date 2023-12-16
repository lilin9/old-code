<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" 
    CodeFile="cars.aspx.cs" Inherits="bookstore.home"%>

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
            <%--<input id="books-search-inpput" type="text" placeholder="请输入关键字搜索电动车"/>--%>
            <asp:TextBox ID="KeywordsTextField" runat="server" placeholder="请输入关键字搜索电动车"></asp:TextBox>
            <asp:Button ID="Button1" runat="server" Text="查找" OnClick="Button1_Click1"/>
       </div>
       <div id="books-class-conetnt">
           <ul id="books-class-ul">
               <a href="cars.aspx?classId=0"><li>全部</li></a>
               <asp:Repeater ID="RepeaterClass" runat="server">
                <ItemTemplate>
                    <a href="<%#"cars.aspx?classId="+ Eval("CategoryId") %>"><li ><%# Eval("CategoryName") %></li></a>
                </ItemTemplate>
            </asp:Repeater>
           </ul>
           <div class="clear"></div>
       </div>

       <div id="books-main-content">
            <asp:Repeater ID="RepeaterBook" runat="server">
                <ItemTemplate>
                    <div class="books-main-item">
                       <a href="<%#"carDetail.aspx?CarId="+ Eval("CarId") %>"><img src="./images2/<%# Eval("CarImage") %>"/></a>
                       <h6><%# Eval("Title") %></h6>
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

