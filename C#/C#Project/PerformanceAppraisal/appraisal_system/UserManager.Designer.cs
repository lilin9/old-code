namespace appraisal_system {
    partial class UserManager {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
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
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent() {
            components = new System.ComponentModel.Container();
            groupBox1 = new GroupBox();
            appraiseBox = new ComboBox();
            label2 = new Label();
            textUsername = new TextBox();
            label1 = new Label();
            buttonSearch = new Button();
            isDelBox = new CheckBox();
            userDataGirdView = new DataGridView();
            Id = new DataGridViewTextBoxColumn();
            Username = new DataGridViewTextBoxColumn();
            Sex = new DataGridViewTextBoxColumn();
            BaseType = new DataGridViewTextBoxColumn();
            AppraisalBase = new DataGridViewTextBoxColumn();
            IsDel = new DataGridViewCheckBoxColumn();
            userManagerDialog = new ContextMenuStrip(components);
            createDialogItem = new ToolStripMenuItem();
            editDialogItem = new ToolStripMenuItem();
            startDialogItem = new ToolStripMenuItem();
            stopDialogItem = new ToolStripMenuItem();
            groupBox1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)userDataGirdView).BeginInit();
            userManagerDialog.SuspendLayout();
            SuspendLayout();
            // 
            // groupBox1
            // 
            groupBox1.Anchor = AnchorStyles.Top | AnchorStyles.Left | AnchorStyles.Right;
            groupBox1.Controls.Add(appraiseBox);
            groupBox1.Controls.Add(label2);
            groupBox1.Controls.Add(textUsername);
            groupBox1.Controls.Add(label1);
            groupBox1.Controls.Add(buttonSearch);
            groupBox1.Controls.Add(isDelBox);
            groupBox1.Location = new Point(12, 12);
            groupBox1.Name = "groupBox1";
            groupBox1.Size = new Size(921, 115);
            groupBox1.TabIndex = 0;
            groupBox1.TabStop = false;
            groupBox1.Text = "筛选";
            // 
            // appraiseBox
            // 
            appraiseBox.FormattingEnabled = true;
            appraiseBox.Location = new Point(408, 43);
            appraiseBox.Name = "appraiseBox";
            appraiseBox.Size = new Size(150, 32);
            appraiseBox.TabIndex = 13;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(356, 47);
            label2.Name = "label2";
            label2.Size = new Size(46, 24);
            label2.TabIndex = 12;
            label2.Text = "身份";
            // 
            // textUsername
            // 
            textUsername.Location = new Point(105, 44);
            textUsername.Name = "textUsername";
            textUsername.Size = new Size(150, 30);
            textUsername.TabIndex = 11;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(35, 47);
            label1.Name = "label1";
            label1.Size = new Size(64, 24);
            label1.TabIndex = 10;
            label1.Text = "用户名";
            // 
            // buttonSearch
            // 
            buttonSearch.Anchor = AnchorStyles.Left;
            buttonSearch.Location = new Point(773, 40);
            buttonSearch.Name = "buttonSearch";
            buttonSearch.Size = new Size(113, 34);
            buttonSearch.TabIndex = 9;
            buttonSearch.Text = "搜索";
            buttonSearch.UseVisualStyleBackColor = true;
            buttonSearch.Click += buttonSearch_Click;
            // 
            // isDelBox
            // 
            isDelBox.AutoSize = true;
            isDelBox.Location = new Point(638, 44);
            isDelBox.Name = "isDelBox";
            isDelBox.Size = new Size(90, 28);
            isDelBox.TabIndex = 8;
            isDelBox.Text = "已停职";
            isDelBox.UseVisualStyleBackColor = true;
            // 
            // userDataGirdView
            // 
            userDataGirdView.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            userDataGirdView.BackgroundColor = Color.White;
            userDataGirdView.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            userDataGirdView.Columns.AddRange(new DataGridViewColumn[] { Id, Username, Sex, BaseType, AppraisalBase, IsDel });
            userDataGirdView.ContextMenuStrip = userManagerDialog;
            userDataGirdView.Location = new Point(12, 133);
            userDataGirdView.MultiSelect = false;
            userDataGirdView.Name = "userDataGirdView";
            userDataGirdView.RowHeadersWidth = 62;
            userDataGirdView.RowTemplate.Height = 32;
            userDataGirdView.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            userDataGirdView.Size = new Size(921, 475);
            userDataGirdView.TabIndex = 1;
            userDataGirdView.CellMouseDown += userDataGirdView_CellMouseDown;
            userDataGirdView.MouseDown += userDataGirdView_MouseDown;
            // 
            // Id
            // 
            Id.DataPropertyName = "Id";
            Id.HeaderText = "用户编号";
            Id.MinimumWidth = 8;
            Id.Name = "Id";
            Id.Width = 150;
            // 
            // Username
            // 
            Username.DataPropertyName = "Username";
            Username.HeaderText = "用户名";
            Username.MinimumWidth = 8;
            Username.Name = "Username";
            Username.Width = 150;
            // 
            // Sex
            // 
            Sex.DataPropertyName = "Sex";
            Sex.HeaderText = "性别";
            Sex.MinimumWidth = 8;
            Sex.Name = "Sex";
            Sex.Width = 150;
            // 
            // BaseType
            // 
            BaseType.DataPropertyName = "BaseType";
            BaseType.HeaderText = "基数类型";
            BaseType.MinimumWidth = 8;
            BaseType.Name = "BaseType";
            BaseType.Width = 150;
            // 
            // AppraisalBase
            // 
            AppraisalBase.DataPropertyName = "AppraisalBase";
            AppraisalBase.HeaderText = "基数";
            AppraisalBase.MinimumWidth = 8;
            AppraisalBase.Name = "AppraisalBase";
            AppraisalBase.Width = 150;
            // 
            // IsDel
            // 
            IsDel.DataPropertyName = "IsDel";
            IsDel.HeaderText = "是否停职";
            IsDel.MinimumWidth = 8;
            IsDel.Name = "IsDel";
            IsDel.Resizable = DataGridViewTriState.True;
            IsDel.SortMode = DataGridViewColumnSortMode.Automatic;
            IsDel.Width = 150;
            // 
            // userManagerDialog
            // 
            userManagerDialog.ImageScalingSize = new Size(24, 24);
            userManagerDialog.Items.AddRange(new ToolStripItem[] { createDialogItem, editDialogItem, startDialogItem, stopDialogItem });
            userManagerDialog.Name = "userManagerDialog";
            userManagerDialog.Size = new Size(241, 157);
            // 
            // createDialogItem
            // 
            createDialogItem.Name = "createDialogItem";
            createDialogItem.Size = new Size(240, 30);
            createDialogItem.Text = "新建";
            createDialogItem.Click += createDialogItem_Click;
            // 
            // editDialogItem
            // 
            editDialogItem.Name = "editDialogItem";
            editDialogItem.Size = new Size(240, 30);
            editDialogItem.Text = "编辑";
            editDialogItem.Click += editDialogItem_Click;
            // 
            // startDialogItem
            // 
            startDialogItem.Name = "startDialogItem";
            startDialogItem.Size = new Size(240, 30);
            startDialogItem.Text = "启用";
            // 
            // stopDialogItem
            // 
            stopDialogItem.Name = "stopDialogItem";
            stopDialogItem.Size = new Size(240, 30);
            stopDialogItem.Text = "停用";
            // 
            // UserManager
            // 
            AutoScaleDimensions = new SizeF(11F, 24F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(945, 620);
            Controls.Add(groupBox1);
            Controls.Add(userDataGirdView);
            FormBorderStyle = FormBorderStyle.None;
            Name = "UserManager";
            Text = "UserManager";
            WindowState = FormWindowState.Maximized;
            Load += UserManager_Load;
            groupBox1.ResumeLayout(false);
            groupBox1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)userDataGirdView).EndInit();
            userManagerDialog.ResumeLayout(false);
            ResumeLayout(false);
        }

        #endregion

        private GroupBox groupBox1;
        private DataGridView userDataGirdView;
        private ContextMenuStrip userManagerDialog;
        private ToolStripMenuItem createDialogItem;
        private ToolStripMenuItem editDialogItem;
        private ToolStripMenuItem startDialogItem;
        private ToolStripMenuItem stopDialogItem;
        private DataGridViewTextBoxColumn Id;
        private DataGridViewTextBoxColumn Username;
        private DataGridViewTextBoxColumn Sex;
        private DataGridViewTextBoxColumn BaseType;
        private DataGridViewTextBoxColumn AppraisalBase;
        private DataGridViewCheckBoxColumn IsDel;
        private ComboBox appraiseBox;
        private Label label2;
        private TextBox textUsername;
        private Label label1;
        private Button buttonSearch;
        private CheckBox isDelBox;
    }
}