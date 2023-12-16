using bookstore;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
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
        string sql = "update Book set SalePrice=@SalePrice,Quantity=@Quantity,CategoryId=@CategoryId,IsHot=@IsHot where ISBN=@ISBN";
        SqlParameter[] parameter = new SqlParameter[]
        {
                new SqlParameter("@SalePrice",Convert.ToDecimal(this.txtSalePrice.Text)),
                new SqlParameter("@Quantity",Convert.ToInt32(this.txtQuantity.Text)),
                new SqlParameter("@CategoryId",Convert.ToInt32(this.DropDownList1.SelectedValue)),
                new SqlParameter("@IsHot",this.rdoYes.Checked?"是":"否"),
                new SqlParameter("@ISBN",this.txtISBN.Text)
        };
        DataBase.ExecuteSql(sql, parameter);
    }

    protected void btnQuery_Click(object sender, EventArgs e)
    {
        string sql = "select BookName,Author,Publisher,SalePrice,Quantity,CategoryId,IsHot from Book where ISBN=@ISBN";
        SqlParameter[] parameter = new SqlParameter[]
        {
                new SqlParameter("@ISBN",this.txtISBN.Text)
        };
        DataTable dt = DataBase.GetDataSet(sql, parameter);
        if (dt.Rows.Count > 0)
        {
            this.lblBookName.Text = dt.Rows[0]["BookName"].ToString();
            this.lblAuthor.Text = dt.Rows[0]["Author"].ToString();
            this.lblPublisher.Text = dt.Rows[0]["Publisher"].ToString();
            this.txtQuantity.Text = dt.Rows[0]["Quantity"].ToString();
            this.txtSalePrice.Text = dt.Rows[0]["SalePrice"].ToString();
            this.DropDownList1.SelectedValue = dt.Rows[0]["CategoryId"].ToString();
            if (dt.Rows[0]["IsHot"].ToString() == "是")
            {
                this.rdoYes.Checked = true;
                this.rdoNo.Checked = false;
            }
            else
            {
                this.rdoNo.Checked = true;
                this.rdoYes.Checked = false;
            }
        }


    }
}