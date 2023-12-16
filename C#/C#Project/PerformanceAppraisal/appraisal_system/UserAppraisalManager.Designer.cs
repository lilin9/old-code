namespace appraisal_system {
    partial class UserAppraisalManager {
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
            panel1 = new Panel();
            yearComboBox = new ComboBox();
            label1 = new Label();
            UserAppraisalGirdView = new DataGridView();
            Id = new DataGridViewTextBoxColumn();
            Username = new DataGridViewTextBoxColumn();
            Sex = new DataGridViewTextBoxColumn();
            BaseType = new DataGridViewTextBoxColumn();
            AppraisalBase = new DataGridViewTextBoxColumn();
            cmsUserAppraisal = new ContextMenuStrip(components);
            tsmEdit = new ToolStripMenuItem();
            panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)UserAppraisalGirdView).BeginInit();
            cmsUserAppraisal.SuspendLayout();
            SuspendLayout();
            // 
            // panel1
            // 
            panel1.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            panel1.Controls.Add(yearComboBox);
            panel1.Controls.Add(label1);
            panel1.Location = new Point(12, 12);
            panel1.Name = "panel1";
            panel1.Size = new Size(965, 126);
            panel1.TabIndex = 0;
            // 
            // yearComboBox
            // 
            yearComboBox.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Right;
            yearComboBox.FormattingEnabled = true;
            yearComboBox.Location = new Point(804, 47);
            yearComboBox.Name = "yearComboBox";
            yearComboBox.Size = new Size(133, 32);
            yearComboBox.TabIndex = 1;
            yearComboBox.Text = "2018";
            // 
            // label1
            // 
            label1.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Right;
            label1.AutoSize = true;
            label1.Location = new Point(752, 50);
            label1.Name = "label1";
            label1.Size = new Size(46, 24);
            label1.TabIndex = 0;
            label1.Text = "身份";
            // 
            // UserAppraisalGirdView
            // 
            UserAppraisalGirdView.AllowUserToAddRows = false;
            UserAppraisalGirdView.AllowUserToDeleteRows = false;
            UserAppraisalGirdView.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            UserAppraisalGirdView.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            UserAppraisalGirdView.Columns.AddRange(new DataGridViewColumn[] { Id, Username, Sex, BaseType, AppraisalBase });
            UserAppraisalGirdView.ContextMenuStrip = cmsUserAppraisal;
            UserAppraisalGirdView.Location = new Point(12, 144);
            UserAppraisalGirdView.MultiSelect = false;
            UserAppraisalGirdView.Name = "UserAppraisalGirdView";
            UserAppraisalGirdView.ReadOnly = true;
            UserAppraisalGirdView.RowHeadersWidth = 62;
            UserAppraisalGirdView.RowTemplate.Height = 32;
            UserAppraisalGirdView.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            UserAppraisalGirdView.Size = new Size(965, 373);
            UserAppraisalGirdView.TabIndex = 1;
            UserAppraisalGirdView.CellMouseDown += UserAppraisalGirdView_CellMouseDown;
            UserAppraisalGirdView.MouseDown += UserAppraisalGirdView_MouseDown;
            // 
            // Id
            // 
            Id.DataPropertyName = "Id";
            Id.HeaderText = "编号";
            Id.MinimumWidth = 8;
            Id.Name = "Id";
            Id.ReadOnly = true;
            Id.Width = 150;
            // 
            // Username
            // 
            Username.DataPropertyName = "Username";
            Username.HeaderText = "用户名";
            Username.MinimumWidth = 8;
            Username.Name = "Username";
            Username.ReadOnly = true;
            Username.Width = 150;
            // 
            // Sex
            // 
            Sex.DataPropertyName = "Sex";
            Sex.HeaderText = "性别";
            Sex.MinimumWidth = 8;
            Sex.Name = "Sex";
            Sex.ReadOnly = true;
            Sex.Width = 150;
            // 
            // BaseType
            // 
            BaseType.DataPropertyName = "BaseType";
            BaseType.HeaderText = "身份";
            BaseType.MinimumWidth = 8;
            BaseType.Name = "BaseType";
            BaseType.ReadOnly = true;
            BaseType.Width = 150;
            // 
            // AppraisalBase
            // 
            AppraisalBase.DataPropertyName = "AppraisalBase";
            AppraisalBase.HeaderText = "年终奖基数";
            AppraisalBase.MinimumWidth = 8;
            AppraisalBase.Name = "AppraisalBase";
            AppraisalBase.ReadOnly = true;
            AppraisalBase.Width = 150;
            // 
            // cmsUserAppraisal
            // 
            cmsUserAppraisal.ImageScalingSize = new Size(24, 24);
            cmsUserAppraisal.Items.AddRange(new ToolStripItem[] { tsmEdit });
            cmsUserAppraisal.Name = "cmsUpdateUserAppraisal";
            cmsUserAppraisal.Size = new Size(171, 34);
            // 
            // tsmEdit
            // 
            tsmEdit.Name = "tsmEdit";
            tsmEdit.Size = new Size(170, 30);
            tsmEdit.Text = "编辑考核项";
            tsmEdit.Click += tsmEdit_Click;
            // 
            // UserAppraisalManager
            // 
            AutoScaleDimensions = new SizeF(11F, 24F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(989, 529);
            Controls.Add(UserAppraisalGirdView);
            Controls.Add(panel1);
            FormBorderStyle = FormBorderStyle.None;
            Name = "UserAppraisalManager";
            Text = "UserAppraisalManager";
            WindowState = FormWindowState.Maximized;
            Load += UserAppraisalManager_Load;
            panel1.ResumeLayout(false);
            panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)UserAppraisalGirdView).EndInit();
            cmsUserAppraisal.ResumeLayout(false);
            ResumeLayout(false);
        }

        #endregion

        private Panel panel1;
        private ComboBox yearComboBox;
        private Label label1;
        private DataGridView UserAppraisalGirdView;
        private DataGridViewTextBoxColumn Id;
        private DataGridViewTextBoxColumn Username;
        private DataGridViewTextBoxColumn Sex;
        private DataGridViewTextBoxColumn BaseType;
        private DataGridViewTextBoxColumn AppraisalBase;
        private ContextMenuStrip cmsUserAppraisal;
        private ToolStripMenuItem tsmEdit;
    }
}