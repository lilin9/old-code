using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data;
using System.Data.SqlClient;
using System.Configuration;

namespace OnlineBook
{
    public static class DataBase
    {
        private static SqlConnection connection;
        public static SqlConnection Connection
        {
            get
            {
                string connectString = ConfigurationManager.ConnectionStrings["BookShop"].ConnectionString;
                if (connection == null || connection.State == ConnectionState.Closed)
                {
                    connection = new SqlConnection(connectString);
                    connection.Open();
                }
                return connection;
            }
        }

        public static DataTable GetDataSet(string sql)
        {
            SqlCommand command = new SqlCommand(sql, Connection);
            DataSet ds = new DataSet();
            SqlDataAdapter da = new SqlDataAdapter(command);
            da.Fill(ds);
            return ds.Tables[0];
        }

        public static DataTable GetDataSet(string sql, SqlParameter[] sqlParameter)
        {
            SqlCommand command = new SqlCommand(sql, Connection);
            foreach (SqlParameter parameter in sqlParameter)
            {
                command.Parameters.Add(parameter);
            }
            DataSet ds = new DataSet();
            SqlDataAdapter da = new SqlDataAdapter(command);
            da.Fill(ds);
            return ds.Tables[0];
        }

        public static int ExecuteSql(string sql)
        {
            SqlCommand command = new SqlCommand(sql, Connection);
            return command.ExecuteNonQuery();
        }

        public static int ExecuteSql(string sql, SqlParameter[] sqlParameter)
        {
            SqlCommand command = new SqlCommand(sql, Connection);
            foreach (SqlParameter parameter in sqlParameter)
            {
                command.Parameters.Add(parameter);
            }
            return command.ExecuteNonQuery();
        }

    }
}