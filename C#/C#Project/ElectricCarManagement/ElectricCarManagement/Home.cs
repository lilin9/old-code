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
    public partial class Home : UserControl {
        public static string username = "Tony";
        public Home() {
            InitializeComponent();
        }

        private void Home_Load(object sender, EventArgs e) {
            //设置背景颜色
            this.BackColor = Color.FromArgb(243, 243, 244);
            //设置home1窗口长宽
            Panel panel = new Panel();
            panel.Size = new Size(830, 510);
            //设置欢迎标语
            this.welcomeLabel.Text = username + ", 欢迎登录";
            this.Controls.Add(panel);
        }

        private void avatarPanel_Paint(object sender, PaintEventArgs e) {
            this.avatarPanel.BackColor = Color.White;
        }
    }
}
