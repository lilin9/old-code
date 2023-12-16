using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Admin_MasterPage : System.Web.UI.MasterPage
{
    protected void Page_Load(object sender, EventArgs e)
    {
        if (!IsPostBack)
        {
            if (this.Session["UserName"] != null)
            {
                label1.Text = this.Session["UserName"].ToString();

            }
            else
            {
                Response.Redirect("../login.aspx");
            }
        }
    }
}
