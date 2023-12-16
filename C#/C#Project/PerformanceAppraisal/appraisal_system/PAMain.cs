using appraisal_system.common;

namespace appraisal_system {
    public partial class PAMain : Form {
        public PAMain() {
            InitializeComponent();
        }

        private void PAMain_Load(object sender, EventArgs e) {
            //加载 UserManger 窗体
            var form = FormFactory.CreateForm("UserManager");
            form.MdiParent = this;
            form.Parent = splitContainer1.Panel2;
            form.Show();
        }

        private void menuTreeView_AfterSelect(object sender, TreeViewEventArgs e) {
            //重置所有按钮样式
            foreach (TreeNode node in menuTreeView.Nodes) {
                node.BackColor = Color.White;
                node.ForeColor = Color.Black;
            }

            //null 值检查
            if (e.Node == null)
                return;

            //设置按钮被点击时的样式
            e.Node.BackColor = SystemColors.Highlight;
            e.Node.ForeColor = Color.White;


            //点击侧边栏菜单按钮，显示对应的窗体（使用了工厂模式）
            var form = FormFactory.CreateForm(e.Node.Tag?.ToString());
            form.MdiParent = this;
            form.Parent = splitContainer1.Panel2;
            form.Show();
        }
    }
}