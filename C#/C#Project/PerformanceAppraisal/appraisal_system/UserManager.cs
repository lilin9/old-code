using appraisal_system.model;

namespace appraisal_system {
    //声明委托关键字
    public delegate void DelegateLoadUserList();

    public partial class UserManager : Form {
        //委托变量
        private DelegateLoadUserList _loadUserList;

        public UserManager() {
            InitializeComponent();
        }

        //页面加载函数
        private void UserManager_Load(object sender, EventArgs e) {
            LoadAppraisalList();
            LoadUserList();
            //给委托赋值
            _loadUserList = LoadUserList;
        }

        //搜索按钮点击事件
        private void buttonSearch_Click(object sender, EventArgs e) {
            LoadUserList();
        }

        //鼠标点击事件
        private void userDataGirdView_MouseDown(object sender, MouseEventArgs e) {
            //判断是否是鼠标右键
            if (e.Button == MouseButtons.Right) {
                //只让新建按钮显示
                createDialogItem.Visible = true;
                editDialogItem.Visible = false;
                startDialogItem.Visible = false;
                stopDialogItem.Visible = false;
            }
        }

        //表格的鼠标点击事件
        private void userDataGirdView_CellMouseDown(object sender, DataGridViewCellMouseEventArgs e) {
            //判断是否是鼠标右键，且是否选中了表格数据行
            if (e is { Button: MouseButtons.Left, RowIndex: <= -1 })
                return;
            //让当前选中行高亮
            userDataGirdView.Rows[e.RowIndex].Selected = true;
            //让新建按钮和编辑按钮显示
            createDialogItem.Visible = true;
            editDialogItem.Visible = true;

            //当前行是否停用，停用显示启用选项，反之显示停用按钮
            var isDel = (bool)userDataGirdView.Rows[0].Cells["IsDel"].Value;
            if (isDel) {
                startDialogItem.Visible = true;
            } else {
                stopDialogItem.Visible = true;
            }
        }

        //右键弹出框新建选项点击事件
        private void createDialogItem_Click(object sender, EventArgs e) {
            //加载出新建弹出框 setUser.cs
            SetUser setUser = new SetUser(_loadUserList);
            setUser.ShowDialog();
        }

        //编辑按钮点击事件
        private void editDialogItem_Click(object sender, EventArgs e) {
            //获取用户点击的行的用户信息的id
            int userId = (int)userDataGirdView.SelectedRows[0].Cells["id"].Value;
            //显示编辑框
            SetUser setUser = new SetUser(_loadUserList, userId);
            setUser.ShowDialog();
        }

        /**
         * 获取用户数据并渲染到表格中
         */
        private void LoadUserList() {
            //关闭自动数据填充
            userDataGirdView.AutoGenerateColumns = false;

            //获取用户输入框数据
            var username = textUsername.Text.Trim();
            var baseTypeId = (int)appraiseBox.SelectedValue;
            var isDel = isDelBox.Checked;

            //设置数据
            var data = new UsersAppraiseBases().GetListJoinAppraisal();
            //根据用户输入框数据对后台数据进行清洗
            if (baseTypeId == 0) {
                userDataGirdView.DataSource = data.FindAll(item =>
                    item.Username.Contains(username) &&
                    item.IsDel == isDel
                );
            } else {
                userDataGirdView.DataSource = data.FindAll(item =>
                    item.Username.Contains(username) &&
                    item.IsDel == isDel &&
                    item.BaseTypeId == baseTypeId
                );
            }
        }

        /**
         * 获取身份列表数据并渲染到组件上
         */
        private void LoadAppraisalList() {
            //查询数据库数据
            List<AppraiseBases> appraiseBasesList = new AppraiseBases().ListAll();
            appraiseBasesList.Insert(0, new AppraiseBases(0, "-查询所有-", 0, false));

            //设置控件
            appraiseBox.DataSource = appraiseBasesList;
            appraiseBox.DisplayMember = "BaseType";
            appraiseBox.ValueMember = "id";
        }
    }
}