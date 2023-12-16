using System;
using System.Collections.Generic;
using System.Data;
using MySql.Data.MySqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace bookstore {
    public partial class bookDetail : System.Web.UI.Page {
        protected void Page_Load(object sender, EventArgs e) {
            if (!IsPostBack) {
                int carId = int.Parse(Request["CarId"]);
                string sql;

                sql = "select * from `Car` where `carId`=" + carId + ";";

                DataTable dt = DB.GetDataSet(sql);
                RepeaterBookDetail.DataSource = dt;
                RepeaterBookDetail.DataBind();

            }
        }

        protected void btnbookDeatilgoCart_Click(object sender, EventArgs e) {
            if (this.Session["UserId"] != null) {
                try {
                    foreach (RepeaterItem item in this.RepeaterBookDetail.Items) {
                        TextBox txt = item.FindControl("txtQuantity") as TextBox;
                        if (txt != null) {
                            //string sql = "insert into Cart(UserId,BookId,Quantity) values(@UserId,@BookId,@Quantity)";
                            string sql = "INSERT INTO `ShoppingCart`(`userId`, `carId`, `quantity`) VALUES(@UserId, @CarId ,@Quantity);";
                            MySqlParameter[] sqlParameter = new MySqlParameter[]
                            {
                            new MySqlParameter("@UserId", Convert.ToInt32(Session["UserId"])),
                            new MySqlParameter("@CarId",Convert.ToInt32(Request["CarId"])),
                            new MySqlParameter("@Quantity",Convert.ToInt32(txt.Text))
                            };
                            DB.ExecuteSql(sql, sqlParameter);
                            Response.Redirect("shopCart.aspx");

                        }
                    }
                } catch (MySqlException) {
                    Response.Write("<script>alert('商品已在购物车')</script>");

                }

            }

        }



        protected void Unnamed_Click(object sender, EventArgs e) {

        }
    }
}
