using System.Collections.Generic;
using System.Data;
using appraisal_system.Utility;
using MySql.Data.MySqlClient;

namespace appraisal_system.model {
    public class AppraiseBases {
        public int Id { get; set; }
        public string BaseType { get; set; }
        public int AppraisalBase { get; set; }
        public bool IsDel { get; set; }

        public AppraiseBases() {
        }

        public AppraiseBases(int id, string baseType, int appraisalBase, bool isDel) {
            Id = id;
            BaseType = baseType;
            AppraisalBase = appraisalBase;
            IsDel = isDel;
        }

        /**
         * 查询所有身份数据
         */
        public List<AppraiseBases> ListAll() {
            //查询数据库数据
            var dataTable =
                SqlHelper.ExecuteTable("SELECT `id`,`base_type`,`appraisal_base`,`is_del` FROM `appraise_bases`;");

            var list = new List<AppraiseBases>();
            //遍历数据库数据，将其转换成 AppraiseBases 实体并添加进数组
            foreach (DataRow item in dataTable.Rows) {
                list.Add(ToModel(item));
            }

            return list;
        }

        /**
         * 将数据库表类型数据转换成 AppraiseBases 实体
         */
        private AppraiseBases ToModel(DataRow rows) {
            AppraiseBases appraiseBases = new AppraiseBases();
            appraiseBases.Id = (int)rows["id"];
            appraiseBases.BaseType = rows["base_type"].ToString();
            appraiseBases.AppraisalBase = (int)rows["appraisal_base"];
            appraiseBases.IsDel = (bool)rows["is_del"];

            return appraiseBases;
        }

        public int InsertAppraiseBases(AppraiseBases appraiseBases) {
            return SqlHelper.ExecuteNonQuery(
                    "INSERT INTO `appraise_bases`(`base_type`, `appraisal_base`, `is_del`) VALUES(?BaseType, ?AppraisalBase, ?IsDel);",
                    new MySqlParameter("?BaseType", appraiseBases.BaseType),
                    new MySqlParameter("?AppraisalBase", appraiseBases.AppraisalBase),
                    new MySqlParameter("?IsDel", appraiseBases.IsDel)
                );
        }

        public int UpdateAppraiseBases(AppraiseBases appraiseBases) {
            return SqlHelper.ExecuteNonQuery(
                "UPDATE `appraise_bases` SET `base_type`=?BaseType, `appraisal_base`=?AppraisalBase, `is_del`=?IsDel WHERE `id`=?Id;",
                new MySqlParameter("?BaseType", appraiseBases.BaseType),
                new MySqlParameter("?AppraisalBase", appraiseBases.AppraisalBase),
                new MySqlParameter("?IsDel", appraiseBases.IsDel),
                new MySqlParameter("?Id", appraiseBases.Id)
            );
        }
    }
}