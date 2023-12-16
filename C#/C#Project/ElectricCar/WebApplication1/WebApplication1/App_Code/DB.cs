using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data;
using System.Configuration;
using MySql.Data.MySqlClient;
using System.Web.Configuration;

namespace bookstore
{
    public class DB
    {
        private static MySqlConnection connection;
        public static MySqlConnection Connection
        {
            get
            {
                string connectString = WebConfigurationManager.ConnectionStrings["ElectricVehicle"].ConnectionString;
                if (connection == null || connection.State == ConnectionState.Closed)
                {
                    connection = new MySqlConnection(connectString);
                    connection.Open();
                }
                return connection;
            }
        }

        public static DataTable GetDataSet(string sql)
        {
            MySqlCommand command = new MySqlCommand(sql, Connection);
            DataSet ds = new DataSet();
            MySqlDataAdapter da = new MySqlDataAdapter(command);
            da.Fill(ds);
            return ds.Tables[0];
        }

        public static DataTable GetDataSet(string sql, MySqlParameter[] sqlParameter)
        {
            MySqlCommand command = new MySqlCommand(sql, Connection);
            foreach (MySqlParameter parameter in sqlParameter)
            {
                command.Parameters.Add(parameter);
            }
            DataSet ds = new DataSet();
            MySqlDataAdapter da = new MySqlDataAdapter(command);
            da.Fill(ds);
            return ds.Tables[0];
        }

        public static int ExecuteSql(string sql)
        {
            MySqlCommand command = new MySqlCommand(sql, Connection);
            return command.ExecuteNonQuery();
        }

        public static int ExecuteSql(string sql, MySqlParameter[] sqlParameter)
        {
            MySqlCommand command = new MySqlCommand(sql, Connection);
            foreach (MySqlParameter parameter in sqlParameter)
            {
                command.Parameters.Add(parameter);
            }
            return command.ExecuteNonQuery();
        }

    }
}