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
    public partial class Goods : UserControl {
        private Common common;
        private string selectSQL = "SELECT `CarId`,`CategoryName`,`Title`,`Car`.`Description`,`CarImage`,`SalePrice`,`Quantity` FROM `Car` INNER JOIN `Category` ON `Car`.`CategoryId`=`Category`.`CategoryId`;";
        private int columnCount = 7;
        public Goods() {
            InitializeComponent();
            common = new Common();
        }

        private void Goods_Load(object sender, EventArgs e) {
            //设置背景颜色
            this.BackColor = Color.FromArgb(243, 243, 244);
            //设置home1窗口长宽
            Panel panel = new Panel();
            panel.Size = new Size(830, 510);
            this.Controls.Add(panel);
            //设置表格数据
            common.executeSQL(this.GoodsDataTable, selectSQL);
        }

        //给表格添加删除和修改按钮
        private void GoodsDataTable_CellPainting(object sender, DataGridViewCellPaintingEventArgs e) {
            common.paintingTableButton(e, this.GoodsDataTable);
        }

        //删除和修改按钮的点击事件
        private void GoodsDataTable_CellMouseClick(object sender, DataGridViewCellMouseEventArgs e) {
            //判断是不是按钮的点击事件
            if (e.ColumnIndex != 0) {
                return;
            }

            //获取点击行的数据
            string[] dataList = new string[columnCount];
            for (int i = 0; i < columnCount; i++) {
                dataList[i] = this.GoodsDataTable.Rows[e.RowIndex].Cells[i + 1].Value.ToString();
            }
            //判断点击的是删除还是修改按钮
            int clickFlag = common.operation(e, this.GoodsDataTable);
            if (1 == clickFlag) {
                //删除操作
                if (common.showMessageBox("是否确认删除？", true) == DialogResult.Yes)
                    common.executeSQL("DELETE FROM `Car` WHERE `CarId`=" + dataList[0] + ";");
            } else if (2 == clickFlag) {
                //编辑操作
                if (common.showMessageBox("是否确认修改？", false) == DialogResult.Yes) {
                    common.executeSQL("UPDATE `Car` SET `Title`='" + dataList[2] + "',`Description`='" + dataList[3] + "',`CarImage`='" + dataList[4] + "',`SalePrice`='" + dataList[5] + "',`Quantity`='" + dataList[6] + "' WHERE `CarId`=" + dataList[0] + ";");
                }
            }

            //刷新数据
            common.flushPage(this.GoodsDataTable, selectSQL);
        }
    }
}
