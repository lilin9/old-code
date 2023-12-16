<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" 
    CodeFile="bookDetail.aspx.cs" Inherits="bookstore.bookDetail"%>

<asp:Content ID="bookDetail" ContentPlaceHolderID="ContentPlaceHolder" Runat="Server">
    <form runat="server">
   <div id="bookDetail-body">
       <div id="bookDetail-content">
           <asp:Repeater ID="RepeaterBookDetail" runat="server">
                <ItemTemplate>
                    <div id="bookDetail-img"><img  src="<%# Eval("BookImage") %>"/></div>
                    <div id="bookDetail-descript">
                        <h3><%# Eval("BookName") %></h3>
                        <p id="bookDetail-descript-price-block">售价：<span id="bookDetail-descript-price">￥<%# Eval("SalePrice") %></span></p>
                        <table>
                            <tr>
                                <td class="bookDetail-desc">作者</td><td><%# Eval("Author") %></td>
                            </tr>
                            <tr>
                                <td class="bookDetail-desc">出版社</td><td><%# Eval("Publisher") %></td>
                            </tr>
                            <tr>
                                <td class="bookDetail-desc">出版日期</td><td><%# Eval("PublishDate") %></td>
                            </tr>
                            <tr>
                                <td class="bookDetail-desc">ISBN</td><td><%# Eval("ISBN") %></td>
                            </tr>
                            <tr>
                                <td class="bookDetail-desc">数量</td>
                                <td>
                                    <asp:TextBox ID="txtQuantity" runat="server" Width="40px">1</asp:TextBox>
                                    <span id="bookDeatil-yu">件(库存<%# Eval("Quantity") %>件)</span></td>
                            </tr>
                        </table>
                        <div id="bookDatail-btn">
                            <a id="bookDeatil-pay" href="pay.aspx">     
                                <%--<asp:Button id="btnPay" runat="server" Text="立即购买" OnClick="Unnamed_Click"/>--%>
                                立即购买
                            </a>
                            <%--<input id="bookDeatil-goCart" type="button" value="加入购物车"/>--%>
                            <asp:Button  runat="server" id="btnbookDeatilgoCart" Text="加入购物车" OnClick="btnbookDeatilgoCart_Click"/>
                   
                        </div>
                    </div>
                </ItemTemplate>
           </asp:Repeater>
           <div class="clear"></div>
       </div>
   </div>
    </form>
    <script>
        var pay = document.getElementById("bookDeatil-pay");
        var goCart = document.getElementById("ContentPlaceHolder_RepeaterBookDetail_btnbookDeatilgoCart_0");
        pay.onclick = function () {
            tishi();
        }
        goCart.onclick = function () {
            tishi();
        }
        function tishi() {
            if (document.getElementById("login-out").style.display == "none") {
                alert("请先登录账号！");
            }
        }
    </script>
</asp:Content>

