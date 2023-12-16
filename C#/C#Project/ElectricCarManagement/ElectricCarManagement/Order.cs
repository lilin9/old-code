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
    public partial class Order : UserControl {
        private Common common;
        private string selectSQL = "SELECT o.`OrderId`,od.`OdId`,`CarId`,`SalePrice`,`Quantity`,`TotalPrice`,`Status`,`OrderDate`,`Username`,`Telephone`,`Address`,`RealName` FROM `Order` o INNER JOIN `OrderDetail` od ON o.`OrderId`=od.`OrderId`;";
        private int columnCount = 12;
        public Order() {
            InitializeComponent();
            common = new Common();
        }

        private void Order_Load(object sender, EventArgs e) {
            //设置背景颜色
            this.BackColor = Color.FromArgb(243, 243, 244);
            //设置home1窗口长宽
            Panel panel = new Panel();
            panel.Size = new Size(830, 510);
            this.Controls.Add(panel);
            //设置表格数据
            common.executeSQL(this.OrderDataTable, selectSQL);
        }

        //给表格添加删除和修改按钮
        private void OrderDataTable_CellPainting(object sender, DataGridViewCellPaintingEventArgs e) {
            common.paintingTableButton(e, this.OrderDataTable);
        }

        //删除和修改按钮的点击事件
        private void OrderDataTable_CellMouseClick(object sender, DataGridViewCellMouseEventArgs e) {
            //判断是不是按钮的点击事件
            if (e.ColumnIndex != 0) {
                return;
            }

            //获取点击行的数据
            string[] dataList = new string[columnCount];
            for (int i = 0; i < columnCount; i++) {
                dataList[i] = this.OrderDataTable.Rows[e.RowIndex].Cells[i + 1].Value.ToString();
            }
            //判断点击的是删除还是修改按钮
            int clickFlag = common.operation(e, this.OrderDataTable);
            if (1 == clickFlag) {
                //删除操作
                if (common.showMessageBox("是否确认删除？", true) == DialogResult.Yes)
                    common.executeSQL("DELETE `Order`,`OrderDetail` FROM `Order` LEFT JOIN `OrderDetail` ON `Order`.`OrderId`=`OrderDetail`.`OrderId` WHERE `Order`.`OrderId`=" + dataList[0] + ";");
            } else if (2 == clickFlag) {
                //编辑操作
                if (common.showMessageBox("是否确认修改？", false) == DialogResult.Yes) {
                    common.executeSQL("UPDATE `Order` SET `Username`='" + dataList[8] + "',`OrderDate`='" + dataList[7] + "',`Telephone`='" + dataList[9] + "',`Address`='" + dataList[10] + "',`RealName`='" + dataList[11] + "',`TotalPrice`='" + dataList[5] + "' WHERE `OrderId`=" + dataList[0] + ";");
                    common.executeSQL("UPDATE `OrderDetail` SET `SalePrice`='" + dataList[3] + "',`Quantity`='" + dataList[4] + "',`Status`='" + dataList[6] + "' WHERE `OdId`=" + dataList[1] + ";");
                }
            }

            //刷新数据
            common.flushPage(this.OrderDataTable, selectSQL);
        }
    }
}
