using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ElectricCarManagement {
    internal static class Program {
        /// <summary>
        /// 应用程序的主入口点。
        /// </summary>
        [STAThread]
        static void Main() {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            //先进行登录验证，才能进入系统
            Login login = new Login();
            login.ShowDialog();
            //判断是否登录成功
            if (login.DialogResult == DialogResult.OK) {
                Application.Run(new Form1());
            }
        }
    }
}
