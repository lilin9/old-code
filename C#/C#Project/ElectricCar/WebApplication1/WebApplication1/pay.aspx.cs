using System;
using System.Collections.Generic;
using System.Data;
using MySql.Data.MySqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Activities.Expressions;
using System.Activities.Statements;

namespace bookstore{
    public partial class pay : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!this.IsPostBack)
            {
                string sql = "SELECT `RealName`, `Telephone`, `Address` FROM `User` WHERE `UserId`=@UserId" + ";";
                MySqlParameter[] parameter = new MySqlParameter[]
                {
                    new MySqlParameter("@UserId",Convert.ToInt32(this.Session["UserId"]))
                };
                DataTable dt = DB.GetDataSet(sql, parameter);
                if (dt.Rows.Count > 0)
                {
                    this.txtName.Text = dt.Rows[0]["RealName"].ToString();
                    this.txtTelphone.Text = dt.Rows[0]["Telephone"].ToString();
                    this.txtAddress.Text = dt.Rows[0]["Address"].ToString();
                }

            }
        }

        protected void btnCheckOut_Click(object sender, EventArgs e)
        {
            OrderInfo orderInfo = (OrderInfo)this.Session["Order"];
            //把订单信息插入Order表
            string sql = "INSERT INTO `Order`(`UserId`,`OrderDate`,`Telephone`,`Address`,`RealName`,`TotalPrice`)  VALUES(@UserId, @OrderDate, @Telephone, @Address, @RealName, @TotalPrice);";
            MySqlParameter[] parameter = new MySqlParameter[]
            {
               new MySqlParameter("@UserId",orderInfo.UserId),
               new MySqlParameter("@OrderDate",orderInfo.OrderDate),
               new MySqlParameter("@Telephone",this.txtTelphone.Text),
               new MySqlParameter("@Address",this.txtAddress.Text),
               new MySqlParameter("@RealName",this.txtName.Text),
               new MySqlParameter("@TotalPrice",orderInfo.TotalPrice)
            };
            DB.ExecuteSql(sql, parameter);

            //把订单明细插入OrderDetail表
            for (int i = 0; i < orderInfo.OrderDetails.Count; i++)
            {
                sql = "INSERT INTO `OrderDetail`(`OrderId`, `CarId`, `SalePrice`, `Quantity`) VALUES(@OrderId, @CarId, @SalePrice, @Quantity);";
                parameter = new MySqlParameter[]
                {
                    new MySqlParameter("@OrderId",orderInfo.OrderId),
                    new MySqlParameter("@CarId",orderInfo.OrderDetails[i].CarId),
                    new MySqlParameter("@SalePrice",orderInfo.OrderDetails[i].SalePrice),
                    new MySqlParameter("@Quantity",orderInfo.OrderDetails[i].Quantity)
                };
                DB.ExecuteSql(sql, parameter);
            }

            //把购物车中的对应项目删除
            for (int i = 0; i < orderInfo.OrderDetails.Count; i++)
            {
                sql = "DELETE FROM `ShoppingCart` WHERE `UserId`= @UserId AND `CarId`= @CarId;";
                parameter = new MySqlParameter[]
                {
                    new MySqlParameter("@UserId",orderInfo.UserId),
                    new MySqlParameter("@CarId",orderInfo.OrderDetails[i].CarId)
                };
                DB.ExecuteSql(sql, parameter);
            }

            //把库存中的书的数量减去已经售出的书的数量
            for (int i = 0; i < orderInfo.OrderDetails.Count; i++)
            {
                sql = "UPDATE `car` SET `Quantity`=`Quantity`-@Quantity WHERE `CarId`=@CarId;";
                parameter = new MySqlParameter[]
                {
                    new MySqlParameter("@Quantity",orderInfo.OrderDetails[i].Quantity),
                    new MySqlParameter("@CarId",orderInfo.OrderDetails[i].CarId)
                };
                DB.ExecuteSql(sql, parameter);
            }
            this.Session["Order"] = null;
            Response.Redirect("cars.aspx?classId=0");
        }
    }
}
