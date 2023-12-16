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
using ElectricCarManagement.Entity;

namespace ElectricCarManagement {
    public partial class User : UserControl {
        private UserEntity userEntity;
        private Common common;
        private string selectSQL = "SELECT `userId`, `username`, `password`, `sex`, `realName`, `telephone`, `address`, `rank` FROM `user`;";
        //private int columnCount = 8;
        private int columnCount = 0;
        public User() {
            userEntity = new UserEntity();
            common = new Common();
            InitializeComponent();
        }

        private void User_Load(object sender, EventArgs e) {
            //设置背景颜色
            this.BackColor = Color.FromArgb(243, 243, 244);
            //设置home1窗口长宽
            Panel panel = new Panel();
            panel.Size = new Size(830, 510);
            this.Controls.Add(panel);
            //设置表格数据
            common.executeSQL(this.UserDataTable, selectSQL);
        }

        //给表格添加删除和修改按钮
        private void UserDataTable_CellPainting(object sender, DataGridViewCellPaintingEventArgs e) {
            common.paintingTableButton(e, this.UserDataTable);
        }

        //删除和修改按钮的点击事件
        private void UserDataTable_CellMouseClick(object sender, DataGridViewCellMouseEventArgs e) {
            //判断是不是按钮的点击事件
            if (e.ColumnIndex != 0) {
                return;
            }

            //获取点击行的数据
            //string[] dataList = new string[columnCount];
            //for (int i = 0; i < columnCount; i++) {
            //    dataList[i] = this.UserDataTable.Rows[e.RowIndex].Cells[i + 1].Value.ToString();
            //}
            userEntity.UserId = int.Parse(this.UserDataTable.Rows[e.RowIndex].Cells[++columnCount].Value.ToString());
            userEntity.Username = this.UserDataTable.Rows[e.RowIndex].Cells[++columnCount].Value.ToString();
            userEntity.Password = this.UserDataTable.Rows[e.RowIndex].Cells[++columnCount].Value.ToString();
            userEntity.Sex = this.UserDataTable.Rows[e.RowIndex].Cells[++columnCount].Value.ToString();
            userEntity.RealName = this.UserDataTable.Rows[e.RowIndex].Cells[++columnCount].Value.ToString();
            userEntity.Telephone = this.UserDataTable.Rows[e.RowIndex].Cells[++columnCount].Value.ToString();
            userEntity.Address = this.UserDataTable.Rows[e.RowIndex].Cells[++columnCount].Value.ToString();
            userEntity.Rank = this.UserDataTable.Rows[e.RowIndex].Cells[++columnCount].Value.ToString();
            //判断点击的是删除还是修改按钮
            int clickFlag = common.operation(e, this.UserDataTable);
            if (1 == clickFlag) {
                //删除操作
                if (common.showMessageBox("是否确认删除？", true) == DialogResult.Yes)
                    common.executeSQL("DELETE FROM `user` WHERE `userId`=" + userEntity.UserId + ";");
            } else if (2 == clickFlag) {
                //编辑操作
                if (common.showMessageBox("是否确认修改？", false) == DialogResult.Yes)
                    common.executeSQL("UPDATE `user` SET `Username`='" + userEntity.Username + "',`Password`='" + userEntity.Password + "',`Sex`='" + userEntity.Sex + "',`RealName`='" + userEntity.RealName + "',`Telephone`='" + userEntity.Telephone + "',`Address`='" + userEntity.Address + "',`Rank`='" + userEntity.Rank + "' WHERE `UserId`=" + userEntity.UserId + ";");
            }

            //刷新数据
            common.flushPage(this.UserDataTable, selectSQL);
        }
    }
}
