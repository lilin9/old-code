<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" 
    CodeFile="carDetail.aspx.cs" Inherits="bookstore.bookDetail"%>

<asp:Content ID="bookDetail" ContentPlaceHolderID="ContentPlaceHolder" Runat="Server">
    <form runat="server">
   <div id="bookDetail-body">
       <div id="bookDetail-content">
           <asp:Repeater ID="RepeaterBookDetail" runat="server">
                <ItemTemplate>
                    <div id="bookDetail-img"><img  src="./images2/<%# Eval("CarImage") %>"/></div>
                    <div id="bookDetail-descript">
                        <h3><%# Eval("Title") %></h3>
                        <p id="bookDetail-descript-price-block">售价：<span id="bookDetail-descript-price">￥<%# Eval("SalePrice") %></span></p>
                        <table>
                            <tr>
                                <td class="bookDetail-desc">详情</td><td><%# Eval("Description") %></td>
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

