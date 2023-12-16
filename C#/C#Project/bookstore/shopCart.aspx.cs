using bookstore;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class home : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        if (!IsPostBack)
        {
            if (this.Session["UserId"] != null)
            {

                //string sql = "select Book.BookId,BookName,BookImage,SalePrice,Cart.Quantity,SalePrice*Cart.Quantity as Money from Cart,Book where Cart.BookId=Book.BookId and Cart.UserId=@UserId";
                //SqlParameter[] sqlParameter = new SqlParameter[]
                //{
                //    new SqlParameter("@UserId",Convert.ToInt32(this.Session["UserId"]))
                //};

                //DataTable dt = DB.GetDataSet(sql, sqlParameter);
                //this.RepeaterBook.DataSource = dt;
                //this.RepeaterBook.DataBind();
                string sql = "select Book.BookId,BookName,BookImage,SalePrice,Cart.Quantity,SalePrice*Cart.Quantity as Money from Cart,Book where Cart.BookId=Book.BookId and Cart.UserId=@UserId";
                SqlParameter[] sqlParameter = new SqlParameter[]
                {
                    new SqlParameter("@UserId",Convert.ToInt32(this.Session["UserId"]))
                };

                DataTable dt = DB.GetDataSet(sql, sqlParameter);
                this.GridView1.DataSource = dt;
                this.GridView1.DataBind();

            }
            else
            {
                Response.Write("<script>alert('请先登录账号')</script >");
            }
            
        }
    }

    protected void chkSelectAll_CheckedChanged(object sender, EventArgs e)
    {
        bool checkBox = ((CheckBox)this.GridView1.HeaderRow.FindControl("chkSelectAll")).Checked;
        for (int i = 0; i < this.GridView1.Rows.Count; i++)
        {
            ((CheckBox)this.GridView1.Rows[i].FindControl("chkSelect")).Checked = checkBox;
        }

        decimal total = 0;
        for (int i = 0; i < this.GridView1.Rows.Count; i++)
        {
            if (((CheckBox)this.GridView1.Rows[i].FindControl("chkSelect")).Checked == true)
            {
                total = total + Convert.ToDecimal(this.GridView1.Rows[i].Cells[5].Text);
            }
        }
        this.lblMoney.Text = total.ToString();

    }

    protected void GridView1_RowDeleting(object sender, GridViewDeleteEventArgs e)
    {
        string BookId = this.GridView1.DataKeys[e.RowIndex].Value.ToString();
        string sql = "delete from Cart where BookId=@BookId and UserId=@UserId";
        SqlParameter[] sqlParameter = new SqlParameter[]
        {
                new SqlParameter("@BookId",Convert.ToInt32(BookId)),
                new SqlParameter("@UserId",Convert.ToInt32(this.Session["UserId"]))
        };
        DB.ExecuteSql(sql, sqlParameter);
        this.Response.Redirect("shopCart.aspx");

    }

    protected void btnContinue_Click(object sender, EventArgs e)
    {
        this.Response.Redirect("books.aspx?classId=0");
    }

    protected void chkSelect_CheckedChanged(object sender, EventArgs e)
    {
        decimal total = 0;
        for (int i = 0; i < this.GridView1.Rows.Count; i++)
        {
            if (((CheckBox)this.GridView1.Rows[i].FindControl("chkSelect")).Checked == true)
            {
                total = total + Convert.ToDecimal(this.GridView1.Rows[i].Cells[5].Text);
            }
        }
        this.lblMoney.Text = total.ToString();
    }

    protected void btnTotal_Click(object sender, EventArgs e)
    {
        OrderInfo orderInfo = new OrderInfo();
        Book book;
        orderInfo.UserId = Convert.ToInt32(this.Session["UserId"]);
        orderInfo.TotalPrice = Convert.ToDecimal(this.lblMoney.Text);
        for (int i = 0; i < this.GridView1.Rows.Count; i++)
        {
            if (((CheckBox)this.GridView1.Rows[i].FindControl("chkSelect")).Checked == true)
            {
                book = new Book();
                book.SalePrice = Convert.ToDecimal(this.GridView1.Rows[i].Cells[3].Text);
                book.Quantity = Convert.ToInt32(this.GridView1.Rows[i].Cells[4].Text);
                book.BookId = Convert.ToInt32(this.GridView1.DataKeys[i].Value.ToString());
                orderInfo.OrderDetails.Add(book);
            }
        }
        this.Session["Order"] = orderInfo;
        Response.Redirect("pay.aspx");
    }
}