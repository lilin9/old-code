using bookstore;
using System;
using System.Collections.Generic;
using System.Data;
using MySql.Data.MySqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class update : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        if (!this.IsPostBack)
        {
            string sql = "select * from Category";
            DataTable dt = DataBase.GetDataSet(sql);
            this.DropDownList1.DataTextField = "CategoryName";
            this.DropDownList1.DataValueField = "CategoryId";
            this.DropDownList1.DataSource = dt;
            this.DropDownList1.DataBind();

        }
    }
    protected void btnUpdate_Click(object sender, EventArgs e)
    {
        string sql = "update `Car` set SalePrice=@SalePrice,Quantity=@Quantity,CategoryId=@CategoryId where `CarId`=@CarId";
        MySqlParameter[] parameter = new MySqlParameter[]
        {
                new MySqlParameter("@SalePrice",Convert.ToDecimal(this.txtSalePrice.Text)),
                new MySqlParameter("@Quantity",Convert.ToInt32(this.txtQuantity.Text)),
                new MySqlParameter("@CategoryId",Convert.ToInt32(this.DropDownList1.SelectedValue)),
                new MySqlParameter("@CarId",this.txtCarId.Text)
        };
        DataBase.ExecuteSql(sql, parameter);
    }

    protected void btnQuery_Click(object sender, EventArgs e)
    {
        string sql = "select Title,Description,SalePrice,Quantity,CategoryId from `Car` where `CarId`=@CarId";
        MySqlParameter[] parameter = new MySqlParameter[]
        {
                new MySqlParameter("@CarId",this.txtCarId.Text)
        };
        DataTable dt = DataBase.GetDataSet(sql, parameter);
        if (dt.Rows.Count > 0)
        {
            this.lblTitle.Text = dt.Rows[0]["Title"].ToString();
            this.lblDescription.Text = dt.Rows[0]["Description"].ToString();
            this.txtQuantity.Text = dt.Rows[0]["Quantity"].ToString();
            this.txtSalePrice.Text = dt.Rows[0]["SalePrice"].ToString();
            this.DropDownList1.SelectedValue = dt.Rows[0]["CategoryId"].ToString();
        }


    }
}