using System;
using System.Data;
using MySql.Data.MySqlClient;

namespace appraisal_system.Utility {
    public class SqlHelper {
        public static string ConnectionStr { get; set; }

        public static DataTable ExecuteTable(string sql, params MySqlParameter[] sqlParameter) {
            using (MySqlConnection sqlConnection = new MySqlConnection(ConnectionStr)) {
                sqlConnection.Open();
                var sqlCommand = new MySqlCommand(sql, sqlConnection);
                
                sqlCommand.Parameters.AddRange(sqlParameter);
                var sqlDataAdapter = new MySqlDataAdapter(sqlCommand);
                var dataSet = new DataSet();
                sqlDataAdapter.Fill(dataSet);

                return dataSet.Tables[0];
            }
        }

        /**
         * 除了查询外都执行，返回数据库受影响的行数
         */
        public static int ExecuteNonQuery(string sql, params MySqlParameter[] mySqlParameters) {
            using (MySqlConnection sqlConnection = new MySqlConnection(ConnectionStr)) {
                sqlConnection.Open();
                var sqlCommand = new MySqlCommand(sql, sqlConnection);
                
                //传入参数
                sqlCommand.Parameters.AddRange(mySqlParameters);
                var rows = sqlCommand.ExecuteNonQuery();

                if (rows <= 0) {
                    throw new Exception("数据库操作失败!");
                }
                return rows;
            }
        }
    }
}
