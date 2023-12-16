using System.Collections.Generic;
using System.Data;
using appraisal_system.utility;
using appraisal_system.Utility;

namespace appraisal_system.model {
    /**
     * 系数表
     */
    public class AppraisalCoefficients {
        public int Id { get; set; }
        public string AppraisalType { get; set; }
        public double AppraisalCoefficient { get; set; }
        public int CalculationMethod { get; set; }

        public bool IsDel { get; set; }

        /**
         * 查询所有
         */
        public static List<AppraisalCoefficients> ListAll() {
            var dataTable = SqlHelper.ExecuteTable("SELECT `id` as Id,`appraisal_type` AS AppraisalType,`appraisal_coefficient` AS AppraisalCoefficient,`calculation_method` AS CalculationMethod,`is_del` AS IsDel FROM `appraisal_coefficients`;");
            
            var list = new List<AppraisalCoefficients>();
            ;
            foreach (DataRow row in dataTable.Rows) {
             list.Add(row.DataRowToModel<AppraisalCoefficients>());   
            }
            return list;
        }
    }
}