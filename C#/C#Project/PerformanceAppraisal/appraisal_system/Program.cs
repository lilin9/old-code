using System.Configuration;
using appraisal_system.Utility;

namespace appraisal_system {
    internal static class Program {
        /// <summary>
        ///  The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main() {
            //获取数据库连接字符串，并设置
            SqlHelper.ConnectionStr = ConfigurationManager.ConnectionStrings["ConnectionStr"].ConnectionString;
            // To customize application configuration such as set high DPI settings or default font,
            // see https://aka.ms/applicationconfiguration.
            ApplicationConfiguration.Initialize();
            Application.Run(new PAMain());
        }
    }
}