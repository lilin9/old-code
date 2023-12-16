<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" 
    CodeFile="shopCart.aspx.cs" Inherits="home"%>

<asp:Content ID="shopCart" ContentPlaceHolderID="ContentPlaceHolder" Runat="Server">
   <div id="shopCart-body">
       <div id="shopCart-content">
           <form runat="server">
          <%-- <table>
               <thead>
                 
                   <tr>
                       <td>
                           <input type="checkbox" id="selectAll" name="selectAll"/>
                          
                           全选</td>
                       <td>书名</td>
                       <td>单价</td>
                       <td>数量</td>
                       <td>金额</td>
                   </tr>
               </thead>
               <tbody>
                   <asp:Repeater ID="RepeaterBook" runat="server">
                        <ItemTemplate>
                            <tr>
                                <td>
                                    <input type="checkbox" name="selectBook" value="<%# Eval("BookId") %>"/>
                                    
                                </td>
                                <td><img src="<%# Eval("BookImage") %>"/><span><%# Eval("BookName") %></span></td>
                                <td><%# Eval("SalePrice") %></td>
                                <td><input type="number" value="<%# Eval("Quantity") %>" name="Quantity"/></td>
                                <td class="shopCart-money">
                                    <%# Eval("Money") %>
                                   
                                </td>
                           </tr>
                        </ItemTemplate>
                   </asp:Repeater>
                 
               </tbody>
               <tfoot>
                   <tr>
                       <td>合计</td>
                       <td id="shopCart-content-sum" colspan="4">￥
                           <span id="shopCart-sum">0</span>
                           
                       </td>
                   </tr>
                   <tr>
                       <td colspan="5">
                           <button id="shopCart-content-select"><a href="cars.aspx?classId=0"></a>继续挑选</button>
                           <asp:Button ID="btnTotal" runat="server" Text="结算" onclick="btnTotal_Click" 
        style="height: 21px" />
                       </td>
                   </tr>
               </tfoot>
           </table>--%>
                <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" 
                        DataKeyNames="CarId" onrowdeleting="GridView1_RowDeleting">
                    <Columns>
                        <asp:TemplateField HeaderText="全选">
                        <HeaderTemplate>
                           <asp:CheckBox ID="chkSelectAll" runat="server" AutoPostBack="True" 
                                oncheckedchanged="chkSelectAll_CheckedChanged" Text="全选" />
                        </HeaderTemplate>
                            <ItemTemplate>
                                <asp:CheckBox ID="chkSelect" runat="server" AutoPostBack="True" 
                                    oncheckedchanged="chkSelect_CheckedChanged" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </ItemTemplate>           
                        </asp:TemplateField>
                        <asp:ImageField DataImageUrlField="CarImage" DataImageUrlFormatString="/images2{0}">
                            <ControlStyle />
                        </asp:ImageField>
                        <asp:BoundField HeaderText="名称" DataField="Title" />
                        <asp:BoundField HeaderText="单价" DataField="SalePrice" />
                        <asp:BoundField HeaderText="数量" DataField="Quantity" />
                        <asp:BoundField DataField="Money" HeaderText="金额" />
                        <asp:CommandField ShowDeleteButton="True" />
                    </Columns>
                  </asp:GridView>
                    <div id="shopCart-content-sum">
                        <asp:Label ID="Label5" runat="server" Text="合计："></asp:Label>
                        <span id="shopCart-sum">￥<asp:Label ID="lblMoney" runat="server" Text="0"></asp:Label></span>
                    </div>
                    <asp:Button ID="btnTotal" runat="server" Text="前往结算" onclick="btnTotal_Click" 
                        />   
                    <asp:Button ID="btnContinue" runat="server" onclick="btnContinue_Click" 
                        Text="继续挑选" />
                    
           </form>
       </div>
   </div>
   <%-- <script>
        var all = document.getElementById("selectAll");
       
        var checkbox = document.getElementsByTagName('tbody')[0];//获取div
        var checked = checkbox.getElementsByTagName('input');//获取div下的input
        var money = checkbox.getElementsByClassName('shopCart-money');//获取div下的input

        var sum = document.getElementById("shopCart-sum");
        //全选
        all.onclick = function(){
            
            if (all.checked) {
                var total = 0;
                for (j = 0; j < money.length; j++) {
                    var num = parseFloat(money[j].innerText);
                    total = total+num;
                }
                sum.innerText = total;
                for (i = 0; i < checked.length; i++) {
                    checked[i].checked = true;
                }
            }
            else {
                sum.innerText = 0;
                for (i = 0; i < checked.length; i++) {
                    checked[i].checked = false;
                }
            }
        }
        for (var i = 0; i < checked.length; i++) {
            checked[i].onclick = function(){
                var total = 0;
                for (var j = 0; j < checked.length; j++) {
                    if (checked[j].checked) {
                        var num = parseFloat(money[j].innerText);
                        total = total + num;
                    }
                }
                sum.innerText = total;
            }
        }
    </script>--%>
</asp:Content>

