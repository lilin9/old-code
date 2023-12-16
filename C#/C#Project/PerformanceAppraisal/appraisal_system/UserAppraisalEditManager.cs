using System.Globalization;
using appraisal_system.model;

namespace appraisal_system {
    public partial class UserAppraisalEditManager : Form {
        private readonly int _userId;
        private readonly int _year;
        private readonly Action _bindDgv;

        public UserAppraisalEditManager() {
            InitializeComponent();
        }

        public UserAppraisalEditManager(int userId, int year, Action bindDgv) : this() {
            this._userId = userId;
            this._year = year;
            this._bindDgv = bindDgv;
        }

        private void UserAppraisalEditManager_Load(object sender, EventArgs e) {
            CreateWindows();
            BindingData();
        }

        /*
         * 绑定要显示的数据
         */
        private void BindingData() {
            var userAppraisalsList = UserAppraisals.ListByUserIdAndYear(_userId, _year);
            //获取 flp 容器下的所有控件
            var flpControls = flp.Controls;
            foreach (Control flpControl in flpControls) {
                //获取 flp 容器下的所有 panel 容器
                if (flpControl is Panel) {
                    var panelControls = flpControl.Controls;
                    //获取 panel 容器下的 textBox 控件
                    foreach (Control panelControl in panelControls) {
                        if (panelControl is TextBox) {
                            var textBox = (TextBox)panelControl;
                            //获取 coefficientsId
                            var coefficientsId = Convert.ToInt32(textBox.Name.Split('_')[1]);
                            //设置 textBx 要回显的值
                            var temp = userAppraisalsList.Find(
                                item => item.CoefficientsId == coefficientsId
                            )?.Count.ToString(CultureInfo.InvariantCulture);
                            textBox.Text = temp;
                        }
                    }
                }
            }
        }

        /*
         * 创建窗体列表
         */
        private void CreateWindows() {
            //加载所有系数表数据
            var coefficientsList = AppraisalCoefficients.ListAll();
            coefficientsList.ForEach(coefficients => {
                //循环渲染生成列表
                var panel = new Panel();
                var label = new Label {
                    Location = new Point(0, 4),
                    Text = coefficients.AppraisalType,
                    Width = 60
                };
                var textBox = new TextBox {
                    Location = new Point(65, 0),
                    Width = 120,
                    Height = 26,
                    Name = "txtAppraisal_" + coefficients.Id
                };

                panel.Controls.Add(label);
                panel.Controls.Add(textBox);
                flp.Controls.Add(panel);
            });
        }

        private void btnSave_Click(object sender, EventArgs e) {
            //获取 flp 容器下的所有控件
            var flpControls = flp.Controls;
            foreach (Control flpControl in flpControls) {
                //获取 flp 容器下的所有 panel 容器
                if (flpControl is Panel) {
                    var panelControls = flpControl.Controls;
                    //获取 panel 容器下的 textBox 控件
                    foreach (Control panelControl in panelControls) {
                        if (panelControl is TextBox) {
                            var textBox = (TextBox)panelControl;
                            if (textBox.Text == "") {
                                continue;
                            }
                            //获取 coefficientsId
                            var coefficientsId = Convert.ToInt32(textBox.Name.Split('_')[1]);
                            var count = Convert.ToDouble(textBox.Text);
                            //先删除数据
                            UserAppraisals.Delete(_userId, _year.ToString(), coefficientsId);
                            //然后添加数据
                            var userAppraisals = new UserAppraisals {
                                UserId = _userId,
                                AssessmentYear = _year,
                                CoefficientsId = coefficientsId,
                                Count = count,
                                IsDel = false
                            };
                            UserAppraisals.Insert(userAppraisals);
                        }
                    }
                }
            }
            //刷新页面数据
            MessageBox.Show("数据更新成功");
            _bindDgv();
            //关闭窗口
            this.Close();
        }

        private void btnCancel_Click(object sender, EventArgs e) {
            this.Close();
        }
    }
}