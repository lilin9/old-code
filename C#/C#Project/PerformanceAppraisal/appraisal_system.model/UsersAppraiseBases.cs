using System.Collections.Generic;
using System.Data;
using appraisal_system.Utility;

namespace appraisal_system.model {
    public class UsersAppraiseBases {
        public int Id { get; set; }
        public string Username { get; set; }
        public string Sex { get; set; }
        public int BaseTypeId { get; set; }
        public bool IsDel { get; set; }
        public int AppraisalBase { get; set; }
        public string BaseType { get; set; }

        public DataTable GetDataTableJoinAppraisal() {
            //查询数据库数据
            DataTable dataTable = SqlHelper.ExecuteTable(
                "SELECT u.`id` AS Id,u.`password` AS `Password`,u.`username` AS Username,u.`sex` AS Sex,u.`base_type_id` AS BaseTypeId,u.`is_del` AS IsDel,a.`appraisal_base` AS AppraisalBase,a.`base_type` AS BaseType FROM `users` AS u LEFT JOIN `appraise_bases` AS a ON u.`base_type_id` = a.`id`;"
            );

            return dataTable;

        }

        /**
         * 查询所有用户数据
         */
        public List<UsersAppraiseBases> GetListJoinAppraisal() {
            //查询数据库数据
            DataTable dataTable = GetDataTableJoinAppraisal();

            //将数据库表类型数据转换成 list 并返回
            List<UsersAppraiseBases> usersAppraiseBasesList = new List<UsersAppraiseBases>();
            foreach (DataRow row in dataTable.Rows) {
                usersAppraiseBasesList.Add(ToModel(row));
            }

            return usersAppraiseBasesList;

        }

        /**
         * 将数据库表类型数据转换成 AppraiseBases 实体
         */
        private UsersAppraiseBases ToModel(DataRow row) {
            UsersAppraiseBases usersAppraiseBases = new UsersAppraiseBases();
            usersAppraiseBases.Id = (int)row["Id"];
            usersAppraiseBases.Username = row["Username"].ToString();
            usersAppraiseBases.Sex = row["Sex"].ToString();
            usersAppraiseBases.BaseTypeId = (int)row["BaseTypeId"];
            usersAppraiseBases.IsDel = (bool)row["IsDel"];
            usersAppraiseBases.AppraisalBase = (int)row["AppraisalBase"];
            usersAppraiseBases.BaseType = row["BaseType"].ToString();

            return usersAppraiseBases;
        }
    }
}