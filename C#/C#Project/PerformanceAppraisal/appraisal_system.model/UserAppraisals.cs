using System.Collections.Generic;
using System.Data;
using appraisal_system.utility;
using appraisal_system.Utility;
using MySql.Data.MySqlClient;

namespace appraisal_system.model {
    public class UserAppraisals {
        public int Id { get; set; }
        public int UserId { get; set; }
        public int CoefficientsId { get; set; }
        public double Count { get; set; }
        public int AssessmentYear { get; set; }
        public bool IsDel { get; set; }

        public static List<UserAppraisals> ListByUserIdAndYear(int userId, int year) {
            var dataTable = SqlHelper.ExecuteTable(
                "SELECT `id` AS Id,`user_id` AS UserId,`coefficients_id` AS CoefficientsId,`count` AS COUNT,`assessment_year` AS AssessmentYear,`is_del` AS IsDel FROM `user_appraisal` WHERE user_id = ?UserId AND assessment_year = ?AssessmentYear;",
                new MySqlParameter("?UserId", userId),
                new MySqlParameter("?AssessmentYear", year)
            );

            var userAppraisalsList = new List<UserAppraisals>();
            foreach (DataRow dataTableRow in dataTable.Rows) {
                userAppraisalsList.Add(dataTableRow.DataRowToModel<UserAppraisals>());
            }

            return userAppraisalsList;
        }

        public static void Insert(UserAppraisals userAppraisals) {
            SqlHelper.ExecuteNonQuery(
                "INSERT INTO `user_appraisal`(`user_id`,`coefficients_id`,`count`,`assessment_year`,`is_del`) VALUES(?UserId,?CoefficientsId,?Count,?AssessmentYear,?IsDel);",
                new MySqlParameter("?UserId", userAppraisals.UserId),
                new MySqlParameter("?CoefficientsId", userAppraisals.CoefficientsId),
                new MySqlParameter("?Count", userAppraisals.Count),
                new MySqlParameter("?AssessmentYear", userAppraisals.AssessmentYear),
                new MySqlParameter("?IsDel", userAppraisals.IsDel)
            );
        }

        public static void Delete(int userId, string assessmentYear, int coefficientsId) {
            SqlHelper.ExecuteNonQuery(
                "DELETE FROM `user_appraisal` WHERE `user_id`=?UserId AND `coefficients_id`=?CoefficientsId AND `assessment_year`=?AssessmentYear;",
                new MySqlParameter("?UserId", userId),
                new MySqlParameter("?CoefficientsId", coefficientsId),
                new MySqlParameter("?AssessmentYear", assessmentYear)
            );
        }
    }
}