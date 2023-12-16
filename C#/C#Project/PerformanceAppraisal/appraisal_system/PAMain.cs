using appraisal_system.common;

namespace appraisal_system {
    public partial class PAMain : Form {
        public PAMain() {
            InitializeComponent();
        }

        private void PAMain_Load(object sender, EventArgs e) {
            //���� UserManger ����
            var form = FormFactory.CreateForm("UserManager");
            form.MdiParent = this;
            form.Parent = splitContainer1.Panel2;
            form.Show();
        }

        private void menuTreeView_AfterSelect(object sender, TreeViewEventArgs e) {
            //�������а�ť��ʽ
            foreach (TreeNode node in menuTreeView.Nodes) {
                node.BackColor = Color.White;
                node.ForeColor = Color.Black;
            }

            //null ֵ���
            if (e.Node == null)
                return;

            //���ð�ť�����ʱ����ʽ
            e.Node.BackColor = SystemColors.Highlight;
            e.Node.ForeColor = Color.White;


            //���������˵���ť����ʾ��Ӧ�Ĵ��壨ʹ���˹���ģʽ��
            var form = FormFactory.CreateForm(e.Node.Tag?.ToString());
            form.MdiParent = this;
            form.Parent = splitContainer1.Panel2;
            form.Show();
        }
    }
}