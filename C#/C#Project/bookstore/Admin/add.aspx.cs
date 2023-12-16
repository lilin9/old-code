using bookstore;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
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
            string fileName = DateTime.Now.Ticks.ToString() + ".jpg";
            this.FileUpload1.SaveAs(this.Server.MapPath(@"\Image\") + fileName);
            string sql = "insert into Book(CategoryId,BookName,Author,Publisher,PublishDate,Description,BookImage,ISBN,SalePrice,Quantity,IsHot) "
                       + "values(@CategoryId,@BookName,@Author,@Publisher,@PublishDate,@Description,@BookImage,@ISBN,@SalePrice,@Quantity,@IsHot)";
            SqlParameter[] parameter = new SqlParameter[]
            {
                new SqlParameter("@CategoryId",Convert.ToInt32(this.DropDownList1.SelectedValue)),
                new SqlParameter("@BookName",this.txtBookName.Text),
                new SqlParameter("@Author",this.txtAuthor.Text),
                new SqlParameter("@Publisher",this.txtPublisher.Text),
                new SqlParameter("@PublishDate",Convert.ToDateTime(this.txtPublishDate.Text)),
                new SqlParameter("@Description",this.txtDescription.Text),
                new SqlParameter("@BookImage",@"\Image\"+fileName),
                new SqlParameter("@ISBN",this.txtISBN.Text),
                new SqlParameter("@SalePrice",Convert.ToDecimal(this.txtSalePrice.Text)),
                new SqlParameter("@Quantity",Convert.ToInt32(this.txtQuantity.Text)),
                new SqlParameter("@IsHot",this.rdoNo.Checked?"否":"是")
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