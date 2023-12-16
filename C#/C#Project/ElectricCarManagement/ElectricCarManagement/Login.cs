using ElectricCarManagement.Db;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ElectricCarManagement {
    public partial class Login : Form {
        private Common common;
        public Login() {
            common = new Common();
            InitializeComponent();
        }

        private void buttonOfLogin_Click(object sender, EventArgs e) {
            //获取用户名和密码
            string username = this.usernameTextBox.Text;
            string password = this.passwordTextBox.Text;

            //判空
            if (username == null || password == null || username == "" || password == "") {
                MessageBox.Show("用户名或密码不能为空!", "登录提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }
            //查询用户数据
            DataTable table = common.connectDB("SELECT `userId`, `username`, `password`, `sex`, `realName`, `telephone`, `address`, `rank` FROM `User` WHERE `Username`='" + username + "' AND `Password`='" + password + "';").Tables[0];
            //密码是否错误
            if (table.Rows.Count <= 0) {
                MessageBox.Show("用户名或密码错误!", "登录提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            //是否是管理员
            string rank = table.Rows[0]["Rank"].ToString();
            if (!"2".Equals(rank)) {
                MessageBox.Show("需要管理员权限才能登录!", "登录提示", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            //将用户名传递给Home控件
            Home.username = table.Rows[0]["Username"].ToString();

            //登录成功，关闭登录窗口
            this.DialogResult = DialogResult.OK;
            this.Close();
        }
    }
}
