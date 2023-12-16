namespace appraisal_system {
    partial class PAMain {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing) {
            if (disposing && (components != null)) {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent() {
            TreeNode treeNode1 = new TreeNode("用户管理");
            TreeNode treeNode2 = new TreeNode("基数管理");
            TreeNode treeNode3 = new TreeNode("系数管理");
            TreeNode treeNode4 = new TreeNode("人员绩效");
            splitContainer1 = new SplitContainer();
            menuTreeView = new TreeView();
            ((System.ComponentModel.ISupportInitialize)splitContainer1).BeginInit();
            splitContainer1.Panel1.SuspendLayout();
            splitContainer1.SuspendLayout();
            SuspendLayout();
            // 
            // splitContainer1
            // 
            splitContainer1.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            splitContainer1.Location = new Point(0, 0);
            splitContainer1.Name = "splitContainer1";
            // 
            // splitContainer1.Panel1
            // 
            splitContainer1.Panel1.Controls.Add(menuTreeView);
            // 
            // splitContainer1.Panel2
            // 
            splitContainer1.Panel2.AutoScroll = true;
            splitContainer1.Size = new Size(1124, 645);
            splitContainer1.SplitterDistance = 203;
            splitContainer1.TabIndex = 0;
            // 
            // menuTreeView
            // 
            menuTreeView.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            menuTreeView.Font = new Font("微软雅黑", 12F, FontStyle.Regular, GraphicsUnit.Point);
            menuTreeView.FullRowSelect = true;
            menuTreeView.ItemHeight = 60;
            menuTreeView.Location = new Point(3, 0);
            menuTreeView.Name = "menuTreeView";
            treeNode1.Name = "_userManager";
            treeNode1.Tag = "UserManager";
            treeNode1.Text = "用户管理";
            treeNode2.Name = "_baseManager";
            treeNode2.Tag = "BaseManager";
            treeNode2.Text = "基数管理";
            treeNode3.Name = "coefficientManager";
            treeNode3.Text = "系数管理";
            treeNode4.Name = "userAppraisal";
            treeNode4.Tag = "UserAppraisalManager";
            treeNode4.Text = "人员绩效";
            menuTreeView.Nodes.AddRange(new TreeNode[] { treeNode1, treeNode2, treeNode3, treeNode4 });
            menuTreeView.ShowLines = false;
            menuTreeView.Size = new Size(200, 645);
            menuTreeView.TabIndex = 0;
            menuTreeView.AfterSelect += menuTreeView_AfterSelect;
            // 
            // PAMain
            // 
            AutoScaleMode = AutoScaleMode.None;
            ClientSize = new Size(1124, 645);
            Controls.Add(splitContainer1);
            IsMdiContainer = true;
            MaximizeBox = false;
            Name = "PAMain";
            Text = "Form1";
            Load += PAMain_Load;
            splitContainer1.Panel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)splitContainer1).EndInit();
            splitContainer1.ResumeLayout(false);
            ResumeLayout(false);
        }

        #endregion

        private SplitContainer splitContainer1;
        private TreeView menuTreeView;
    }
}