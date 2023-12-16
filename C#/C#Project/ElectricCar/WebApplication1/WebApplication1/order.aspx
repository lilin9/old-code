<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" 
    CodeFile="order.aspx.cs" Inherits="bookstore.order"%>

<asp:Content ID="order" ContentPlaceHolderID="ContentPlaceHolder" Runat="Server">
    <div id="order-body">
        <div id="order-content">
           <%-- <table>
                <thead>
                    <tr>
                        <td>订单号:</td>
                        <td>635455127095468750 </td>
                        <td>日期:</td>
                        <td>2014/9/5 11:18:29 </td>
                    </tr>
                    <tr>
                        <td>收货人</td><td>王丽</td>
                        <td>金额</td><td>￥72.00</td>
                    </tr>
                    <tr>
                        <td>订单状态</td>
                        <td>“交易中”</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="2">书名</td><td>单价</td><td>数量</td><td>金额</td>
                    </tr>
                    <tr>
                        <td colspan="2">没有梦想何必远方</td>
                        <td>36.00</td><td>2</td><td>72.00</td>
                    </tr>
                </tbody>
            </table>--%>
                <asp:Repeater ID="rptOrder" runat="server">
                  <ItemTemplate>
                     <table>
                        <thead>
                            <tr>
                                <td>订单号:</td>
                                <td><%#Eval("OrderId") %></td>
                                <td>日期:</td>
                                <td><%#Eval("OrderDate") %></td>
                            </tr>
                            <tr>
                                <td>收货人</td><td><%#Eval("RealName") %></td>
                                <td>金额</td><td>￥<%#Eval("TotalPrice") %></td>
                            </tr>
                            <tr>
                                <td>订单状态</td>
                                <td>“<%#Eval("Status") %>”</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td colspan="2">书名</td><td>单价</td><td>数量</td><td>金额</td>
                            </tr>
                      <asp:Repeater ID="rptOrderDetials" runat="server" DataSource='<%#Eval("OrderDetails") %>'>
                        <ItemTemplate>
                           <tr>
                                <td colspan="2"><%#Eval("Title")%></td>
                                <td><%#Eval("SalePrice")%></td><td><%#Eval("Quantity")%></td><td><%#Eval("SumOfMoney")%></td>
                            </tr>
                        </ItemTemplate>
                       </asp:Repeater>
                            </tbody>
            </table>
          </ItemTemplate>
        </asp:Repeater>
      </div>
    </div>
</asp:Content>

