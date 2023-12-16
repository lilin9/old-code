using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace bookstore{
    public partial class pay : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!this.IsPostBack)
            {
                string sql = "select RealName,Telephone,Address from [User] where UserId=@UserId";
                SqlParameter[] parameter = new SqlParameter[]
                {
                    new SqlParameter("@UserId",Convert.ToInt32(this.Session["UserId"]))
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
            string sql = "insert into [Order](OrderId,UserId,OrderDate,Telephone,Address,RealName,TotalPrice) values(@OrderId,@UserId,@OrderDate,@Telephone,@Address,@RealName,@TotalPrice)";
            SqlParameter[] parameter = new SqlParameter[]
            {
               new SqlParameter("@OrderId",orderInfo.OrderId),
               new SqlParameter("@UserId",orderInfo.UserId),
               new SqlParameter("@OrderDate",orderInfo.OrderDate),
               new SqlParameter("@Telephone",this.txtTelphone.Text),
               new SqlParameter("@Address",this.txtAddress.Text),
               new SqlParameter("@RealName",this.txtName.Text),
               new SqlParameter("@TotalPrice",orderInfo.TotalPrice)
            };
            DB.ExecuteSql(sql, parameter);

            //把订单明细插入OrderDetail表
            for (int i = 0; i < orderInfo.OrderDetails.Count; i++)
            {
                sql = "insert into OrderDetail(OrderId,BookId,SalePrice,Quantity) values(@OrderId,@BookId,@SalePrice,@Quantity)";
                parameter = new SqlParameter[]
                {
                    new SqlParameter("@OrderId",orderInfo.OrderId),
                    new SqlParameter("@BookId",orderInfo.OrderDetails[i].BookId),
                    new SqlParameter("@SalePrice",orderInfo.OrderDetails[i].SalePrice),
                    new SqlParameter("@Quantity",orderInfo.OrderDetails[i].Quantity)
                };
                DB.ExecuteSql(sql, parameter);
            }

            //把购物车中的对应项目删除
            for (int i = 0; i < orderInfo.OrderDetails.Count; i++)
            {
                sql = "delete from Cart where UserId=@UserId and BookId=@BookId";
                parameter = new SqlParameter[]
                {
                    new SqlParameter("@UserId",orderInfo.UserId),
                    new SqlParameter("@BookId",orderInfo.OrderDetails[i].BookId)
                };
                DB.ExecuteSql(sql, parameter);
            }

            //把库存中的书的数量减去已经售出的书的数量
            for (int i = 0; i < orderInfo.OrderDetails.Count; i++)
            {
                sql = "update Book set Quantity=Quantity-@Quantity where BookId=@BookId";
                parameter = new SqlParameter[]
                {
                    new SqlParameter("@Quantity",orderInfo.OrderDetails[i].Quantity),
                    new SqlParameter("@BookId",orderInfo.OrderDetails[i].BookId)
                };
                DB.ExecuteSql(sql, parameter);
            }
            this.Session["Order"] = null;
            Response.Redirect("books.aspx?classId=0.aspx");
        }
    }
}
