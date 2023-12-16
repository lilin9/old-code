using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;

namespace bookstore
{
    public partial class register : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void txtRegister_Click(object sender, EventArgs e)
        {
            string sql = "select * from [User] where UserName=@Username";
            SqlParameter[] sqlParamete = new SqlParameter[]
            {
                new SqlParameter("@Username",this.txtUserName.Text)
            };
            DataTable dt = DB.GetDataSet(sql, sqlParamete);
            if (dt.Rows.Count > 0)
            {
                this.txtUserName.Text = "已经存在这个用户名，请重新取名";
            }
            else
            {
                sql = "insert into [User](Username,Password,Sex,RealName,Telephone,Address) values(@Username,@Password,@Sex,@RealName,@Telephone,@Address)";
                sqlParamete = new SqlParameter[]
                {
                    new SqlParameter("@Username",this.txtUserName.Text),
                    new SqlParameter("@Password",this.txtPassword.Text),
                    new SqlParameter("@Sex",this.rdoFemale.Checked?"女":"男"),
                    new SqlParameter("@RealName",this.txtName.Text),
                    new SqlParameter("@Telephone",this.txtTelephone.Text),
                    new SqlParameter("@Address",this.txtAddress.Text)
                };
                DB.ExecuteSql(sql, sqlParamete);
                this.Response.Redirect("login.aspx");
            }

        }
    }
}