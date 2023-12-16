using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.Entity;
using MySql.Data.MySqlClient;
using System.Configuration;
using System.Data;
using System.Windows.Forms;
using System.Drawing;

namespace ElectricCarManagement.Db {
    internal class Common : DbContext {
        //连接数据库
        public DataSet connectDB(string sql) {
            //获取配置文件数据库连接信息
            string connectString = "server=localhost;user id=root;password=123abc;database=electricVehicle; pooling=true;";
            //连接mysql数据库
            MySqlConnection mySqlConnection = new MySqlConnection(connectString);
            //打开数据库连接
            mySqlConnection.Open();
            //创建命令对象
            MySqlCommand cmd = new MySqlCommand(sql, mySqlConnection);
            //查询数据库，返回DataSet对象
            MySqlDataAdapter adapter = new MySqlDataAdapter(sql, mySqlConnection);
            DataSet set = new DataSet();
            adapter.Fill(set);
            //返回值
            return set;
        }

        //执行sql语句
        public void executeSQL(DataGridView view, string sql) {
            DataSet set = connectDB(sql);
            view.DataSource = set.Tables[0];
        }
        public void executeSQL(string sql) {
            connectDB(sql);
        }

        //绘制表格按钮
        public void paintingTableButton(DataGridViewCellPaintingEventArgs e, DataGridView dgView) {
            if (e.ColumnIndex >= 0 && e.RowIndex >= 0) {
                //判断是不是按钮列
                    if (dgView.Columns[e.ColumnIndex].HeaderText == "操作") {
                        StringFormat sf = StringFormat.GenericDefault.Clone() as StringFormat;//设置重绘入单元格的字体样式
                        sf.FormatFlags = StringFormatFlags.DisplayFormatControl;
                        sf.Alignment = StringAlignment.Center;
                        sf.LineAlignment = StringAlignment.Center;
                        sf.Trimming = StringTrimming.EllipsisCharacter;

                        e.PaintBackground(e.CellBounds, false);//重绘边框

                        //设置要写入字体的大小
                        System.Drawing.Font myFont = new System.Drawing.Font("微软雅黑", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
                        SizeF sizeDel = e.Graphics.MeasureString("删除", myFont);
                        SizeF sizeMod = e.Graphics.MeasureString("编辑", myFont);

                        float fDel = sizeDel.Width / (sizeDel.Width + sizeMod.Width); //
                        float fMod = sizeMod.Width / (sizeDel.Width + sizeMod.Width);

                        //设置每个“按钮的边界”
                        RectangleF rectDel = new RectangleF(e.CellBounds.Left, e.CellBounds.Top, e.CellBounds.Width * fDel, e.CellBounds.Height);
                        RectangleF rectMod = new RectangleF(rectDel.Right, e.CellBounds.Top, e.CellBounds.Width * fMod, e.CellBounds.Height);
                        e.Graphics.DrawString("编辑", myFont, Brushes.Blue, rectMod, sf); //绘制“按钮”
                        e.Graphics.DrawString("删除", myFont, Brushes.Red, rectDel, sf);
                        e.Handled = true;

                        e.Handled = true;
                }
            }
        }

        //表格按钮的点击处理逻辑
        public int operation(DataGridViewCellMouseEventArgs e, DataGridView dgView) {
            if (e.ColumnIndex >= 0 && e.RowIndex >= 0) {
                Point curPosition = e.Location;//当前鼠标在当前单元格中的坐标
                if (dgView.Columns[e.ColumnIndex].HeaderText == "操作") {
                    Graphics g = dgView.CreateGraphics(); 
                    System.Drawing.Font myFont = new System.Drawing.Font("微软雅黑", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
                    SizeF sizeDel = g.MeasureString("删除", myFont);
                    SizeF sizeMod = g.MeasureString("编辑", myFont);
                    float fDel = sizeDel.Width / (sizeDel.Width + sizeMod.Width);
                    float fMod = sizeMod.Width / (sizeDel.Width + sizeMod.Width);

                    Rectangle rectTotal = new Rectangle(0, 0, dgView.Columns[e.ColumnIndex].Width, dgView.Rows[e.RowIndex].Height);
                    RectangleF rectDel = new RectangleF(rectTotal.Left, rectTotal.Top, rectTotal.Width * fDel, rectTotal.Height);
                    RectangleF rectMod = new RectangleF(rectDel.Right, rectTotal.Top, rectTotal.Width * fMod, rectTotal.Height);


                    if (rectDel.Contains(curPosition)) { //删除
                        return 1;
                    } else if (rectMod.Contains(curPosition)) {//编辑
                        return 2;
                    }
                }
            }
            return 0;
        }

        //弹窗
        public DialogResult showMessageBox(string msg, bool isWarning) {
            DialogResult dr;
            if (!isWarning) {
                dr = MessageBox.Show(msg, "提示", MessageBoxButtons.YesNoCancel, MessageBoxIcon.Question);
            } else {
                dr = MessageBox.Show(msg, "警告", MessageBoxButtons.YesNoCancel, MessageBoxIcon.Exclamation);
            }
            return dr;
        }

        //刷新页面用
        public void flushPage(DataGridView view, string sql) {
            //清除表格数据
            DataTable table = (DataTable)view.DataSource;
            table.Rows.Clear();
            view.DataSource = table;
            //重新读取数据
            executeSQL(view, sql);
        }
    }
}
