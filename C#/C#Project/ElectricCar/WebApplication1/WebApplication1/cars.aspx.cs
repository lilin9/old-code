﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Activities.Statements;
using System.Runtime.ConstrainedExecution;

namespace bookstore {
    public partial class home : System.Web.UI.Page {
        protected void Page_Load(object sender, EventArgs e) {
            if (!IsPostBack) {
                int classId = int.Parse(Request["classId"]);
                string sql;
                if (classId == 0) {
                    sql = "select * from `car`;";
                } else {
                    sql = "select  * from `car` where `CategoryId`=" + classId + ";";
                }

                DataTable dt = DB.GetDataSet(sql);
                RepeaterBook.DataSource = dt;
                RepeaterBook.DataBind();

                string sql2 = "select * from Category";
                DataTable dt2 = DB.GetDataSet(sql2);
                RepeaterClass.DataSource = dt2;
                RepeaterClass.DataBind();

            }
        }
        public void Button1_Click1(object sender, EventArgs e) {
            String text = KeywordsTextField.Text;
            String sql3 = "SELECT* FROM `Car` WHERE `title` LIKE '%" + text + "%'" + ";";
            DataTable dt = DB.GetDataSet(sql3);
            RepeaterBook.DataSource = dt;
            RepeaterBook.DataBind();
        }
    }
}