using System.Collections.Generic;
using System.Data;
using appraisal_system.utility;
using appraisal_system.Utility;
using MySql.Data.MySqlClient;

namespace appraisal_system.model {
    public class Users {
        public int Id { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public string Sex { get; set; }
        public int BaseTypeId { get; set; }
        public bool IsDel { get; set; }

        /**
         * 获取所有用户数据
         */
        public static List<Users> ListAll() {
            //获取数据库数据
            DataTable dataTable = SqlHelper.ExecuteTable(
                "SELECT `id` AS `Id`,`username` AS `Username`,`sex` AS `Sex` ,`password` AS `Password`,`base_type_id` AS BaseTypeId,`is_del` AS IsDel FROM `users`;"
            );

            List<Users> usersList = new List<Users>();
            //循环遍历 dataTable，转换成 user 类型并存入列表
            foreach (DataRow dataRow in dataTable.Rows) {
                usersList.Add(dataRow.DataRowToModel<Users>());
            }

            return usersList;
        }

        /**
         * 插入 用户 数据，返回影响的行数
         */

        public int InsertUser(Users user) {
            return SqlHelper.ExecuteNonQuery(
                "INSERT INTO `users`(`username`, `sex`, `password`, `base_type_id`, `is_del`) VALUES(?Username, ?Sex, ?Password, ?BaseTypeId, ?IsDel);",
                new MySqlParameter("?Username", user.Username),
                new MySqlParameter("?Sex", user.Sex),
                new MySqlParameter("?Password", user.Password),
                new MySqlParameter("?BaseTypeId", user.BaseTypeId),
                new MySqlParameter("?IsDel", user.IsDel)

            );
        }

        /**
         * 修改 用户 数据，返回影响的行数
         */
        public int UpdateUser(Users user) {
            return SqlHelper.ExecuteNonQuery(
                "UPDATE `users` SET `username`=?Username, `sex`=?Sex, `password`=?Password, `base_type_id`=?BaseTypeId, `is_del`=?IsDel WHERE `id`=?Id;",
                new MySqlParameter("?Username", user.Username),
                new MySqlParameter("?Sex", user.Sex),
                new MySqlParameter("?Password", user.Password),
                new MySqlParameter("?BaseTypeId", user.BaseTypeId),
                new MySqlParameter("?IsDel", user.IsDel),
                new MySqlParameter("?Id", user.Id)
            );
        }
    }
}