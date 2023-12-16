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
    public partial class bookDetail : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                int bookId = int.Parse(Request["BookId"]);
                string sql;

                sql = "select * from Book where BookId=" + bookId;

                DataTable dt = DB.GetDataSet(sql);
                RepeaterBookDetail.DataSource = dt;
                RepeaterBookDetail.DataBind();

            }
        }

        protected void btnbookDeatilgoCart_Click(object sender, EventArgs e)
        {
            if (this.Session["UserId"] != null)
            {
                try {
                    foreach (RepeaterItem item in this.RepeaterBookDetail.Items)
                    {
                        TextBox txt = item.FindControl("txtQuantity") as TextBox;
                        if (txt != null)
                        {
                            string sql = "insert into Cart(UserId,BookId,Quantity) values(@UserId,@BookId,@Quantity)";
                            SqlParameter[] sqlParameter = new SqlParameter[]
                            {
                            new SqlParameter("@UserId", Convert.ToInt32(Session["UserId"])),
                            new SqlParameter("@BookId",Convert.ToInt32(Request["BookId"])),
                            new SqlParameter("@Quantity",Convert.ToInt32(txt.Text))
                            };
                            DB.ExecuteSql(sql, sqlParameter);
                            Response.Redirect("shopCart.aspx");

                        }
                    }
                }
                catch (SqlException)
                {
                    Response.Write("<script>alert('此书已在购物车')</script>");

                }
              
            }

        }



        protected void Unnamed_Click(object sender, EventArgs e)
        {

        }
    }
}
