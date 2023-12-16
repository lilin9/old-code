using appraisal_system.model;

namespace appraisal_system {
    public partial class SetUser : Form {
        private readonly DelegateLoadUserList _loadUserList;

        public SetUser(DelegateLoadUserList loadUserList) {
            InitializeComponent();
            _loadUserList = loadUserList;
        }

        private Users? _user;

        public SetUser(DelegateLoadUserList loadUserList, int userId) : this(loadUserList) {
            if (userId >= 0) {
                var usersList = Users.ListAll();
                _user = usersList.Find(item => userId == item.Id);
            }
        }

        //加载函数
        private void SetUser_Load(object sender, EventArgs e) {
            //查询所有身份信息
            List<AppraiseBases> appraiseBasesList = new AppraiseBases().ListAll();

            //设置多选框数据
            setBase.DataSource = appraiseBasesList;
            //设置下拉列表的显示成员为"BaseType"，表示下拉列表中每个项显示该项对应对象的"BaseType"属性的值
            setBase.DisplayMember = "BaseType";
            //设置下拉列表的值成员为"id"，表示下拉列表中每个项的值为该项对应对象的"id"属性的值
            setBase.ValueMember = "id";

            //设置表单回显值
            if (_user != null) {
                setUsername.Text = _user.Username;
                setBase.SelectedValue = _user.BaseTypeId;
                setSex.Text = _user.Sex;
                setIsDel.Checked = _user.IsDel;
            }
        }

        /**
         * 保存按钮点击事件
         */
        private void saveButton_Click(object sender, EventArgs e) {
            //获取用户输入的值
            string userName = setUsername.Text.Trim();
            int baseId = (int)setBase.SelectedValue;
            string sex = setSex.Text;
            bool isDel = setIsDel.Checked;

            //判断是修改用户信息还是添加用户信息
            int row;
            if (_user == null) {
                //初始化 user 对象
                Users users = new Users {
                    Username = userName,
                    BaseTypeId = baseId,
                    Sex = sex,
                    IsDel = isDel,
                    Password = "111"
                };
                //保存到数据库
                row = users.InsertUser(users);

                //给出提示信息
                if (row > 0) {
                    MessageBox.Show(@"用户添加成功");
                    //调用委托，刷新用户列表
                    _loadUserList();
                    this.Close();
                } else {
                    MessageBox.Show(@"用户添加失败");
                }
            } else {
                //重置 _user 对象
                _user = new Users {
                    Id = _user.Id,
                    Username = userName,
                    BaseTypeId = baseId,
                    Sex = sex,
                    IsDel = isDel
                };
                //修改用户信息
                row = _user.UpdateUser(_user);
                //提示信息
                if (row > 0) {
                    MessageBox.Show(@"修改用户成功");
                    //调用委托，刷新用户列表
                    _loadUserList();
                    this.Close();
                } else {
                    MessageBox.Show(@"修改用户失败");
                }
            }
        }
    }
}