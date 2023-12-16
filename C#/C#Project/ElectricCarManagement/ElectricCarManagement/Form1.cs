using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ElectricCarManagement {
    public partial class Form1 : Form {
        public Home home;
        public Order order;
        public Goods goods;
        public User user;
        private Size beforeResizeSize = Size.Empty;

        public Form1() {
            InitializeComponent();
            home = new Home();
            order = new Order();
            goods = new Goods();
            user = new User();
            //显示默认窗口
            this.showPage(home);
        }

        //让窗口控件大小可以动态变化 start
        protected override void OnResizeBegin(EventArgs e) {
            base.OnResizeBegin(e);
            beforeResizeSize = this.Size;
        }

        protected override void OnResizeEnd(EventArgs e) {
            base.OnResizeEnd(e);
            //窗口resize之后的大小
            Size endResizeSize = this.Size;
            //获得比例变化
            float percentWidth = (float)endResizeSize.Width / beforeResizeSize.Width;
            float percentHeight = (float)endResizeSize.Height / beforeResizeSize.Height; ;
            foreach (Control control in this.Controls) {
                if (control is DataGridView) continue;
                //按比例改变控件大小
                control.Width = (int)(control.Width * percentWidth);
                control.Height = (int)(control.Height * percentHeight);
                //为了不使控件之间覆盖，位置也要按比例变化
                control.Left = (int)(control.Left * percentWidth);
                control.Top = (int)(control.Top * percentHeight);
            }
        }
        //让窗口控件大小可以动态变化 end

        //给左侧菜单按钮设置鼠标事件
        private void menuItemButton_MouseEnter(object sender, EventArgs e) {
            //设置字体颜色
            ((Button)sender).ForeColor = Color.FromArgb(64, 158, 255);
        }

        private void menuItemButton_MouseLevel(object sender, EventArgs e) {
            //设置字体颜色
            ((Button)sender).ForeColor = Color.White;
        }

        //home 按钮点击事件
        private void homeItemButton_Click(object sender, MouseEventArgs e) {
            this.showPage(home);
        }

        private void userItemButton_Click(object sender, MouseEventArgs e) {
            this.showPage(user);
        }
        private void goodsItemButton_Click(object sender, MouseEventArgs e) {
            this.showPage(goods);
        }
        private void orderItemButton_Click(object sender, MouseEventArgs e) {
            this.showPage(order);
        }

        private void showPage(UserControl page) {
            page.Show();
            //清空以前加载的窗口控件
            rightContainer.Controls.Clear();
            //加载home页面
            rightContainer.Controls.Add(page);
        }

        private void Form1_Load(object sender, EventArgs e) {

        }
    }
}
