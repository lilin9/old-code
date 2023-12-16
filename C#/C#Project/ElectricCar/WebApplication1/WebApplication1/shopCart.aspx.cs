using bookstore;
using System;
using System.Collections.Generic;
using System.Data;
using MySql.Data.MySqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class home : System.Web.UI.Page {
    protected void Page_Load(object sender, EventArgs e) {
        if (!IsPostBack) {
            if (this.Session["UserId"] != null) {
                string sql = "SELECT `car`.`CarId`,`Title`,`CarImage`,`SalePrice`,`ShoppingCart`.`quantity`,`SalePrice`*`ShoppingCart`.`quantity` AS Money FROM `ShoppingCart`,`Car` WHERE `ShoppingCart`.`carId`=`car`.`CarId` AND `ShoppingCart`.`userId`=@UserId" + ";";
                MySqlParameter[] sqlParameter = new MySqlParameter[]
                {
                    new MySqlParameter("@UserId",Convert.ToInt32(this.Session["UserId"]))
                };

                DataTable dt = DB.GetDataSet(sql, sqlParameter);
                this.GridView1.DataSource = dt;
                this.GridView1.DataBind();

            } else {
                Response.Write("<script>alert('请先登录账号')</script >");
            }

        }
    }

    protected void chkSelectAll_CheckedChanged(object sender, EventArgs e) {
        bool checkBox = ((CheckBox)this.GridView1.HeaderRow.FindControl("chkSelectAll")).Checked;
        for (int i = 0; i < this.GridView1.Rows.Count; i++) {
            ((CheckBox)this.GridView1.Rows[i].FindControl("chkSelect")).Checked = checkBox;
        }

        decimal total = 0;
        for (int i = 0; i < this.GridView1.Rows.Count; i++) {
            if (((CheckBox)this.GridView1.Rows[i].FindControl("chkSelect")).Checked == true) {
                total = total + Convert.ToDecimal(this.GridView1.Rows[i].Cells[5].Text);
            }
        }
        this.lblMoney.Text = total.ToString();

    }

    protected void GridView1_RowDeleting(object sender, GridViewDeleteEventArgs e) {
        string CarId = this.GridView1.DataKeys[e.RowIndex].Value.ToString();
        string sql = "DELETE FROM `ShoppingCart` WHERE `CarId`=@CarId AND `UserId`=@UserId" + ";";
        MySqlParameter[] sqlParameter = new MySqlParameter[]
        {
                new MySqlParameter("@CarId",Convert.ToInt32(CarId)),
                new MySqlParameter("@UserId",Convert.ToInt32(this.Session["UserId"]))
        };
        DB.ExecuteSql(sql, sqlParameter);
        this.Response.Redirect("shopCart.aspx");

    }

    protected void btnContinue_Click(object sender, EventArgs e) {
        this.Response.Redirect("cars.aspx?classId=0");
    }

    protected void chkSelect_CheckedChanged(object sender, EventArgs e) {
        decimal total = 0;
        for (int i = 0; i < this.GridView1.Rows.Count; i++) {
            if (((CheckBox)this.GridView1.Rows[i].FindControl("chkSelect")).Checked == true) {
                total = total + Convert.ToDecimal(this.GridView1.Rows[i].Cells[5].Text);
            }
        }
        this.lblMoney.Text = total.ToString();
    }

    protected void btnTotal_Click(object sender, EventArgs e) {
        OrderInfo orderInfo = new OrderInfo();
        Car car;
        orderInfo.UserId = Convert.ToInt32(this.Session["UserId"]);
        orderInfo.TotalPrice = Convert.ToDecimal(this.lblMoney.Text);
        for (int i = 0; i < this.GridView1.Rows.Count; i++) {
            if (((CheckBox)this.GridView1.Rows[i].FindControl("chkSelect")).Checked == true) {
                car = new Car();
                car.SalePrice = Convert.ToDecimal(this.GridView1.Rows[i].Cells[3].Text);
                car.Quantity = Convert.ToInt32(this.GridView1.Rows[i].Cells[4].Text);
                car.CarId = Convert.ToInt32(this.GridView1.DataKeys[i].Value.ToString());
                orderInfo.OrderDetails.Add(car);
            }
        }
        this.Session["Order"] = orderInfo;
        Response.Redirect("pay.aspx");
    }
}