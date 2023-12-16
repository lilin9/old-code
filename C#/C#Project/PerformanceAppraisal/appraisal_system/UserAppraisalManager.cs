using System.Data;
using appraisal_system.model;

namespace appraisal_system {
    public partial class UserAppraisalManager : Form {
        private readonly string _appraisalType = "AppraisalType";
        private readonly string _appraisalCoefficient = "AppraisalCoefficient";
        private readonly string _calculationMethod = "CalculationMethod";
        private readonly string _yearBonus = "YearBonus";
        private readonly string _assessmentYear = "AssessmentYear";

        private Action _bindDgv;
        public UserAppraisalManager() {
            InitializeComponent();
        }

        private void UserAppraisalManager_Load(object sender, EventArgs e) {
            //给 dataGirdView 添加列
            AddColumn();
            //渲染数据
            RenderData();
            //给 _bindDgv 委托变量绑定方法
            _bindDgv = RenderData;
        }

        /**
         * 渲染数据到表格中
         */
        private void RenderData() {
            //获取用户数据
            var dataTaleUser = new UsersAppraiseBases().GetDataTableJoinAppraisal();
            //获取系数表集合
            var coefficientsList = AppraisalCoefficients.ListAll();
            //将 系数表集合数据 和 用户数据 合并
            coefficientsList.ForEach(item => {
                //添加系数名
                dataTaleUser.Columns.Add(new DataColumn() {
                    ColumnName = _appraisalType + item.Id
                });
                //添加系数值
                dataTaleUser.Columns.Add(new DataColumn() {
                    ColumnName = _appraisalCoefficient + item.Id
                });
                //添加计算方式
                dataTaleUser.Columns.Add(new DataColumn() {
                    ColumnName = _calculationMethod + item.Id
                });
            });
            //添加考核年度
            dataTaleUser.Columns.Add(new DataColumn() {
                ColumnName = _assessmentYear
            });
            //添加实发年终奖
            dataTaleUser.Columns.Add(new DataColumn() {
                ColumnName = _yearBonus
            });

            //补全 dataTaleUser 的数据
            var userCoefficientsList = UserAppraisalCoefficients.ListAll();
            for (int i = 0; i < dataTaleUser.Rows.Count; i++) {
                var filterUserCoefficientsList = userCoefficientsList.FindAll(
                    item => item.UserId == (int)dataTaleUser.Rows[i]["Id"] &&
                            item.AssessmentYear == Convert.ToInt32(yearComboBox.Text)
                );

                //系数计算的数组，用于存放每个考核类型的总系数
                double[] yeaBounusArr = new double[filterUserCoefficientsList.Count];
                for (int j = 0; j < filterUserCoefficientsList.Count; j++) {
                    //获取 AppraisalType 对应的 dataTableUser 的 ColumnName 值
                    //获取考核次数
                    var arraisalTypeKey = _appraisalType + filterUserCoefficientsList[j].CoefficientsId;
                    var arraisalTypeCount = filterUserCoefficientsList[j].Count;
                    //获取考核系数
                    var appraisalCoefficientKey = _appraisalCoefficient + filterUserCoefficientsList[j].CoefficientsId;
                    var appraisalCoefficientCount = filterUserCoefficientsList[j].AppraisalCoefficient;
                    //获取计算方式
                    var calculationMethodKey = _calculationMethod + filterUserCoefficientsList[j].CoefficientsId;
                    var calculationMethodValue = filterUserCoefficientsList[j].CalculationMethod;

                    //给 dataTableUser 绑定值
                    dataTaleUser.Rows[i][arraisalTypeKey] = arraisalTypeCount;
                    dataTaleUser.Rows[i][appraisalCoefficientKey] = appraisalCoefficientCount;
                    dataTaleUser.Rows[i][calculationMethodKey] = calculationMethodValue;

                    //计算考核系数："考核系数" x "次数" x "计算方式"
                    yeaBounusArr[j] = arraisalTypeCount * appraisalCoefficientCount * calculationMethodValue;
                }

                dataTaleUser.Rows[i][_assessmentYear] = yearComboBox.Text;
                //结算实发年终奖
                double yearBonusAll = 0;
                for (int j = 0; j < yeaBounusArr.Length; j++) {
                    yearBonusAll += yeaBounusArr[j];
                }
                //计算实发年终奖
                double yearBonus = (1 + yearBonusAll) * Convert.ToDouble(dataTaleUser.Rows[i]["AppraisalBase"]);
                //防止 yearBonus 为负
                dataTaleUser.Rows[i][_yearBonus] = yearBonus < 0 ? 0 : yearBonus;
            }

            //关闭自动生成列
            UserAppraisalGirdView.AutoGenerateColumns = false;
            //绑定数据源
            UserAppraisalGirdView.DataSource = dataTaleUser;
        }

        /**
         * 给 dataGirdView 添加列
         */
        private void AddColumn() {
            //查询所有系数表数据
            List<AppraisalCoefficients> acList = AppraisalCoefficients.ListAll();
            //获取 dataGirdView 所有的列
            List<DataGridViewTextBoxColumn> columnList = new List<DataGridViewTextBoxColumn>();
            //给 dataGirdView 动态添加列
            acList.ForEach(item => {
                //考核项次数
                columnList.Add(new DataGridViewTextBoxColumn() {
                    HeaderText = item.AppraisalType,
                    Name = _appraisalType + item.Id,
                    DataPropertyName = "AppraisalType" + item.Id,
                    ReadOnly = true,
                    Width = 120
                });
                //考核项系数
                columnList.Add(new DataGridViewTextBoxColumn() {
                    HeaderText = "系数",
                    Name = _appraisalCoefficient + item.Id,
                    DataPropertyName = _appraisalCoefficient + item.Id,
                    ReadOnly = true,
                    Visible = false,
                    Width = 120
                });
                //考核项是递增还是递减
                columnList.Add(new DataGridViewTextBoxColumn() {
                    HeaderText = "计算方式",
                    Name = _calculationMethod + item.Id,
                    DataPropertyName = _calculationMethod + item.Id,
                    ReadOnly = true,
                    Visible = false,
                    Width = 120
                });
            });
            //将 columnList 加载到窗体中
            UserAppraisalGirdView.Columns.AddRange(columnList.ToArray());
            //添加 考核年度 和 实发年终奖 列
            UserAppraisalGirdView.Columns.Add(new DataGridViewTextBoxColumn() {
                HeaderText = "考核年度",
                Name = _assessmentYear,
                DataPropertyName = _assessmentYear,
                ReadOnly = true,
                Width = 120
            });
            UserAppraisalGirdView.Columns.Add(new DataGridViewTextBoxColumn() {
                HeaderText = "实发年终奖",
                Name = _yearBonus,
                DataPropertyName = _yearBonus,
                ReadOnly = true,
                Width = 125
            });
        }

        private void UserAppraisalGirdView_MouseDown(object sender, MouseEventArgs e) {
            tsmEdit.Visible = false;
        }

        private void UserAppraisalGirdView_CellMouseDown(object sender, DataGridViewCellMouseEventArgs e) {
            //高亮选中的行
            if (e.RowIndex > -1) {
                UserAppraisalGirdView.ClearSelection();
                UserAppraisalGirdView.Rows[e.RowIndex].Selected = true;
                tsmEdit.Visible = true;
            }
        }

        private void tsmEdit_Click(object sender, EventArgs e) {
            var year = yearComboBox.Text;
            var userId = (int)UserAppraisalGirdView.SelectedRows[0].Cells["id"].Value;
            //显示 数据录入 窗体
            var userAppraisalEditManager = new UserAppraisalEditManager(userId, Convert.ToInt32(year), _bindDgv);
            userAppraisalEditManager.ShowDialog();
        }
    }
}
