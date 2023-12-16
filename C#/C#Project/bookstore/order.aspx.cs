using bookstore;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace bookstore
{
    public partial class order : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (this.Session["UserId"] != null)
                {
                        string sql = "select [Order].OrderId,OrderDate,Telephone,Address,RealName,TotalPrice,Status,BookName,OrderDetail.SalePrice,OrderDetail.Quantity from [Order],OrderDetail,Book where [Order].OrderId=OrderDetail.OrderId and OrderDetail.BookId=Book.BookId and UserId=@UserId";
                        SqlParameter[] parameter = new SqlParameter[]
                        {
                            new SqlParameter("@UserId",Convert.ToInt32(this.Session["UserId"]))
                        };
                        DataTable dt = DB.GetDataSet(sql, parameter);
                        IList<OrderInfo> orderInfoes = new List<OrderInfo>();
                        OrderInfo orderInfo = new OrderInfo();
                        string tempOrderId = "11111";
                        for (int i = 0; i < dt.Rows.Count; i++)
                        {
                            if (tempOrderId != dt.Rows[i]["OrderId"].ToString())
                            {
                                orderInfo = new OrderInfo();
                                orderInfoes.Add(orderInfo);
                                orderInfo.OrderId = dt.Rows[i]["OrderId"].ToString();
                                orderInfo.OrderDate = Convert.ToDateTime(dt.Rows[i]["OrderDate"]);
                                orderInfo.Telephone = dt.Rows[i]["Telephone"].ToString();
                                orderInfo.Address = dt.Rows[i]["Address"].ToString();
                                orderInfo.RealName = dt.Rows[i]["RealName"].ToString();
                                orderInfo.TotalPrice = Convert.ToDecimal(dt.Rows[i]["TotalPrice"]);
                                orderInfo.Status = dt.Rows[i]["Status"].ToString();
                                tempOrderId = orderInfo.OrderId;
                            }
                            Book book = new Book();
                            book.BookName = dt.Rows[i]["BookName"].ToString();
                            book.SalePrice = Convert.ToDecimal(dt.Rows[i]["SalePrice"]);
                            book.Quantity = Convert.ToInt32(dt.Rows[i]["Quantity"]);
                            orderInfo.OrderDetails.Add(book);
                        }
                        this.rptOrder.DataSource = orderInfoes;
                        this.rptOrder.DataBind();

                    }
                    else
                    {
                        Response.Write("<script>alert('请先登录账号')</script >");
                    }

            }
        }
    }
}
