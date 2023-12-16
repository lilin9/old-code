using System.Collections.Generic;
using System.Data;
using appraisal_system.utility;
using appraisal_system.Utility;

namespace appraisal_system.model {
    public class UserAppraisalCoefficients {
        public int Id { get; set; }
        public int UserId { get; set; }
        public int CoefficientsId { get; set; }
        public double Count { get; set; }
        public int AssessmentYear { get; set; }
        public string AppraisalType { get; set; }
        public double AppraisalCoefficient { get; set; }
        public int CalculationMethod { get; set; }
        public bool IsDel { get; set; }

        public static List<UserAppraisalCoefficients> ListAll() {
            var list = new List<UserAppraisalCoefficients>();

            //读取数据库数据
            var dataTable = SqlHelper.ExecuteTable(
                "SELECT ua.`id` AS Id,ua.`user_id` AS UserId, ua.`coefficients_id` AS CoefficientsId,ua.`count` AS `Count`,ua.`assessment_year` AS AssessmentYear,ac.`appraisal_type` AS AppraisalType,ac.`appraisal_coefficient` AS AppraisalCoefficient ,ac.`calculation_method` AS CalculationMethod,ua.`is_del` AS IsDel FROM `user_appraisal` AS ua LEFT JOIN `appraisal_coefficients` AS ac ON ac.`id` = ua.`coefficients_id`;"
            );

            //遍历转换类型
            foreach (DataRow row in dataTable.Rows) {
                list.Add(row.DataRowToModel<UserAppraisalCoefficients>());
            }

            return list;
        }
    }
}