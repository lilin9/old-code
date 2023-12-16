using bookstore;
using System;
using System.Collections.Generic;
using System.Data;
using MySql.Data.MySqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class add : System.Web.UI.Page
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
    protected void btnAdd_Click(object sender, EventArgs e)
    {
        try {
            string fileName = "car.jpg";
            //this.FileUpload1.SaveAs(this.Server.MapPath(@"\image2\") + fileName);
            string sql = "insert into Car(`CategoryId`, `Title`, `Description`, `CarImage`, `SalePrice`, `Quantity`) "
                       + "values(@CategoryId,@Title,@Description,@CarImage,@SalePrice,@Quantity)";
            MySqlParameter[] parameter = new MySqlParameter[]
            {
                new MySqlParameter("@CategoryId",Convert.ToInt32(this.DropDownList1.SelectedValue)),
                new MySqlParameter("@Title",this.textTitle.Text),
                new MySqlParameter("@Description",this.txtDescription.Text),
                new MySqlParameter("@CarImage",@"/"+fileName),
                new MySqlParameter("@SalePrice",Convert.ToDecimal(this.txtSalePrice.Text)),
                new MySqlParameter("@Quantity",Convert.ToInt32(this.txtQuantity.Text))
            };
            DataBase.ExecuteSql(sql, parameter);
            Response.Write("<script>alert('添加成功')</script>");
        }
        catch (Exception)
        {
            Response.Write("<script>alert('请填写完整信息')</script>");
        }
        
    }
}