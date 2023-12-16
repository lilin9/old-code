using bookstore;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace bookstore {
    public partial class login : System.Web.UI.Page {
        protected void Page_Load(object sender, EventArgs e) {

        }
        protected void login_click(object sender, EventArgs e) {
            String username = this.txtusername.Text;
            String pwd = this.txtpassword.Text;
            string sql = "select * from `user` where `Username`='" + username + "' and `Password`='" + pwd + "'";
            DataTable dt = DB.GetDataSet(sql);
            if (dt.Rows.Count > 0) {
                this.Session["UserName"] = username;
                this.Session["UserId"] = dt.Rows[0]["UserId"].ToString();
                if (dt.Rows[0]["Rank"].ToString().Equals("2")) {
                    Response.Redirect("Admin/add.aspx");
                } else {
                    Response.Redirect("home.aspx");
                }

            } else {
                tip.Text = "账号密码错误，请重新输入";
                //tip.Text = username + " " + pwd;
            }
        }

    }
}
