using appraisal_system.model;

namespace appraisal_system {
    public partial class SetBases : Form {
        private readonly DelegateLoadAppraisalBase _loadAppraisalBase;
        private AppraiseBases? _appraiseBases;

        public SetBases(DelegateLoadAppraisalBase loadAppraisalBase) {
            InitializeComponent();
            _loadAppraisalBase = loadAppraisalBase;
        }

        public SetBases(DelegateLoadAppraisalBase loadAppraisalBase,
            int baseId) : this(loadAppraisalBase) {
            if (baseId >= 0) {
                _appraiseBases = new AppraiseBases().ListAll().Find(
                    item => item.Id == baseId
                );
            }
        }

        private void SetBases_Load(object sender, EventArgs e) {
            if (_appraiseBases != null) {
                //设置页面数据
                sb_BaseType.Text = _appraiseBases.BaseType;
                sb_AppraisalBase.Text = _appraiseBases.AppraisalBase.ToString();
                sb_IsDel.Checked = _appraiseBases.IsDel;
            }
        }

        private void sb_saveButton_Click(object sender, EventArgs e) {
            //获取输入框内容
            var baseType = sb_BaseType.Text;
            var appraisalBase = Convert.ToInt32(sb_AppraisalBase.Text);
            var isDel = sb_IsDel.Checked;

            //判断用户是想修改数据还是添加数据
            int row;
            if (_appraiseBases == null) {   //添加数据
                AppraiseBases appraiseBases = new AppraiseBases {
                    BaseType = baseType,
                    AppraisalBase = appraisalBase,
                    IsDel = isDel
                };

                //添加到数据库
                row = appraiseBases.InsertAppraiseBases(appraiseBases);

                if (row <= 0) {
                    MessageBox.Show(@"添加数据失败");
                } else {
                    MessageBox.Show(@"添加数据成功");
                    //刷新数据，关闭窗口
                    _loadAppraisalBase();
                    this.Close();
                }
            } else {    //修改数据
                _appraiseBases = new AppraiseBases {
                    Id = _appraiseBases.Id,
                    BaseType = baseType,
                    AppraisalBase = appraisalBase,
                    IsDel = isDel
                };

                //修改数据库数据
                row = _appraiseBases.UpdateAppraiseBases(_appraiseBases);

                if (row <= 0) {
                    MessageBox.Show(@"修改数据失败");
                } else {
                    MessageBox.Show(@"修改数据成功");
                    //刷新数据，关闭窗口
                    _loadAppraisalBase();
                    this.Close();
                }
            }
        }
    }
}