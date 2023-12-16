using appraisal_system.model;

namespace appraisal_system {
    public delegate void DelegateLoadAppraisalBase();
    public partial class BaseManager : Form {
        private DelegateLoadAppraisalBase _loadAppraisalBase;
        public BaseManager() {
            InitializeComponent();
        }

        private void BaseManager_Load(object sender, EventArgs e) {
            //加载身份类型数据
            LoadAppraisalList();
            //加载身份基数数据
            LoadAppraisalBase();
            _loadAppraisalBase = LoadAppraisalBase;
        }

        private void bm_searchButton_Click(object sender, EventArgs e) {
            LoadAppraisalBase();
        }

        private void bm_dataGirdView_MouseDown(object sender, MouseEventArgs e) {
            //鼠标右键
            if (e.Button == MouseButtons.Right) {
                bm_cms_create.Visible = true;
                bm_cms_edit.Visible = false;
                bm_cms_start.Visible = false;
                bm_cms_stop.Visible = false;
            }
        }

        private void bm_dataGirdView_CellMouseDown(object sender, DataGridViewCellMouseEventArgs e) {
            //鼠标左键，没有选中表格行，退出
            if (e is { Button: MouseButtons.Left, RowIndex: <= -1 }) {
                return;
            }
            //当前选中行高亮
            bm_dataGirdView.ClearSelection();   //取消所有选中
            bm_dataGirdView.Rows[e.RowIndex].Selected = true;
            //让新建按钮和编辑按钮显示
            bm_cms_create.Visible = true;
            bm_cms_edit.Visible = true;

            //当前行是否停用，停用显示启用选项，反之显示停用按钮
            var isDel = (bool)bm_dataGirdView.Rows[0].Cells["IsDel"].Value;
            if (isDel) {
                bm_cms_start.Visible = true;
            } else {
                bm_cms_stop.Visible = true;
            }
        }
        private void bm_cms_create_Click(object sender, EventArgs e) {
            //显示编辑框
            var setBases = new SetBases(_loadAppraisalBase);
            setBases.ShowDialog();
        }

        private void bm_cms_edit_Click(object sender, EventArgs e) {
            //获取用户点击的行的用户信息的id
            var baseId = (int)bm_dataGirdView.SelectedRows[0].Cells["Id"].Value;
            //显示编辑框
            var setBases = new SetBases(_loadAppraisalBase, baseId);
            setBases.ShowDialog();
        }

        /**
         * 加载 身份基数 数据
         */
        private void LoadAppraisalBase() {
            //关闭自动数据填充
            bm_dataGirdView.AutoGenerateColumns = false;

            //获取筛选框数据
            var appraisalBaseInput = bm_AppraisalBase.Text.Trim();
            var appraisalBase = Convert.ToInt32(appraisalBaseInput == "" ? -1 : appraisalBaseInput);
            var baseTypeId = (int)(bm_BaseTypeBox.SelectedValue ?? -1);
            var isDel = bm_isDelBox.Checked;

            //获取所有数据
            var appraiseBasesList = new AppraiseBases().ListAll();
            //根据筛选框筛选数据
            if (baseTypeId == -1 && appraisalBase == -1) {
                bm_dataGirdView.DataSource = appraiseBasesList.FindAll(item =>
                    isDel == item.IsDel
                );
            } else if (baseTypeId == -1 && appraisalBase != -1) {
                //给 dataGirdView 绑定数据源
                bm_dataGirdView.DataSource = appraiseBasesList.FindAll(item =>
                    appraisalBase == item.AppraisalBase &&
                    isDel == item.IsDel
                );
            } else if (baseTypeId != -1 && appraisalBase == -1) {
                //给 dataGirdView 绑定数据源
                bm_dataGirdView.DataSource = appraiseBasesList.FindAll(item =>
                    baseTypeId == item.Id &&
                    isDel == item.IsDel
                );
            } else {
                //给 dataGirdView 绑定数据源
                var temp = appraiseBasesList.FindAll(item =>
                    appraisalBase == item.AppraisalBase &&
                    isDel == item.IsDel &&
                    baseTypeId == item.Id
                );
                bm_dataGirdView.DataSource = temp;
            }
        }

        /**
         * 获取身份类型列表数据并渲染到组件上
         */
        private void LoadAppraisalList() {
            //查询数据库数据
            List<AppraiseBases> appraiseBasesList = new AppraiseBases().ListAll();
            appraiseBasesList.Insert(0, new AppraiseBases(-1, "-查询所有-", 0, false));

            //设置控件
            bm_BaseTypeBox.DataSource = appraiseBasesList;
            bm_BaseTypeBox.DisplayMember = "BaseType";
            bm_BaseTypeBox.ValueMember = "id";
        }
    }
}