namespace appraisal_system {
    partial class BaseManager {
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
            bm_dataGirdView = new DataGridView();
            Id = new DataGridViewTextBoxColumn();
            BaseType = new DataGridViewTextBoxColumn();
            AppraisalBase = new DataGridViewTextBoxColumn();
            IsDel = new DataGridViewTextBoxColumn();
            bm_ContextMenuStrip = new ContextMenuStrip(components);
            bm_cms_create = new ToolStripMenuItem();
            bm_cms_edit = new ToolStripMenuItem();
            bm_cms_start = new ToolStripMenuItem();
            bm_cms_stop = new ToolStripMenuItem();
            groupBox1 = new GroupBox();
            bm_BaseTypeBox = new ComboBox();
            label2 = new Label();
            bm_AppraisalBase = new TextBox();
            label1 = new Label();
            bm_searchButton = new Button();
            bm_isDelBox = new CheckBox();
            ((System.ComponentModel.ISupportInitialize)bm_dataGirdView).BeginInit();
            bm_ContextMenuStrip.SuspendLayout();
            groupBox1.SuspendLayout();
            SuspendLayout();
            // 
            // bm_dataGirdView
            // 
            bm_dataGirdView.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            bm_dataGirdView.BackgroundColor = Color.White;
            bm_dataGirdView.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            bm_dataGirdView.Columns.AddRange(new DataGridViewColumn[] { Id, BaseType, AppraisalBase, IsDel });
            bm_dataGirdView.ContextMenuStrip = bm_ContextMenuStrip;
            bm_dataGirdView.Location = new Point(12, 144);
            bm_dataGirdView.Name = "bm_dataGirdView";
            bm_dataGirdView.RowHeadersWidth = 62;
            bm_dataGirdView.RowTemplate.Height = 32;
            bm_dataGirdView.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            bm_dataGirdView.Size = new Size(869, 363);
            bm_dataGirdView.TabIndex = 1;
            bm_dataGirdView.CellMouseDown += bm_dataGirdView_CellMouseDown;
            bm_dataGirdView.MouseDown += bm_dataGirdView_MouseDown;
            // 
            // Id
            // 
            Id.DataPropertyName = "Id";
            Id.HeaderText = "编号";
            Id.MinimumWidth = 8;
            Id.Name = "Id";
            Id.Width = 150;
            // 
            // BaseType
            // 
            BaseType.DataPropertyName = "BaseType";
            BaseType.HeaderText = "身份类型";
            BaseType.MinimumWidth = 8;
            BaseType.Name = "BaseType";
            BaseType.Width = 150;
            // 
            // AppraisalBase
            // 
            AppraisalBase.DataPropertyName = "AppraisalBase";
            AppraisalBase.HeaderText = "年终奖基数";
            AppraisalBase.MinimumWidth = 8;
            AppraisalBase.Name = "AppraisalBase";
            AppraisalBase.Width = 150;
            // 
            // IsDel
            // 
            IsDel.DataPropertyName = "IsDel";
            IsDel.HeaderText = "是否删除";
            IsDel.MinimumWidth = 8;
            IsDel.Name = "IsDel";
            IsDel.Width = 150;
            // 
            // bm_ContextMenuStrip
            // 
            bm_ContextMenuStrip.ImageScalingSize = new Size(24, 24);
            bm_ContextMenuStrip.Items.AddRange(new ToolStripItem[] { bm_cms_create, bm_cms_edit, bm_cms_start, bm_cms_stop });
            bm_ContextMenuStrip.Name = "bm_ContextMenuStrip";
            bm_ContextMenuStrip.Size = new Size(241, 157);
            // 
            // bm_cms_create
            // 
            bm_cms_create.Name = "bm_cms_create";
            bm_cms_create.Size = new Size(240, 30);
            bm_cms_create.Text = "新建";
            bm_cms_create.Click += bm_cms_create_Click;
            // 
            // bm_cms_edit
            // 
            bm_cms_edit.Name = "bm_cms_edit";
            bm_cms_edit.Size = new Size(240, 30);
            bm_cms_edit.Text = "编辑";
            bm_cms_edit.Click += bm_cms_edit_Click;
            // 
            // bm_cms_start
            // 
            bm_cms_start.Name = "bm_cms_start";
            bm_cms_start.Size = new Size(240, 30);
            bm_cms_start.Text = "启用";
            // 
            // bm_cms_stop
            // 
            bm_cms_stop.Name = "bm_cms_stop";
            bm_cms_stop.Size = new Size(240, 30);
            bm_cms_stop.Text = "停用";
            // 
            // groupBox1
            // 
            groupBox1.Anchor = AnchorStyles.Top | AnchorStyles.Left | AnchorStyles.Right;
            groupBox1.Controls.Add(bm_BaseTypeBox);
            groupBox1.Controls.Add(label2);
            groupBox1.Controls.Add(bm_AppraisalBase);
            groupBox1.Controls.Add(label1);
            groupBox1.Controls.Add(bm_searchButton);
            groupBox1.Controls.Add(bm_isDelBox);
            groupBox1.Location = new Point(12, 12);
            groupBox1.Name = "groupBox1";
            groupBox1.Size = new Size(869, 126);
            groupBox1.TabIndex = 1;
            groupBox1.TabStop = false;
            groupBox1.Text = "筛选";
            // 
            // bm_BaseTypeBox
            // 
            bm_BaseTypeBox.FormattingEnabled = true;
            bm_BaseTypeBox.Location = new Point(385, 50);
            bm_BaseTypeBox.Name = "bm_BaseTypeBox";
            bm_BaseTypeBox.Size = new Size(150, 32);
            bm_BaseTypeBox.TabIndex = 7;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(333, 54);
            label2.Name = "label2";
            label2.Size = new Size(46, 24);
            label2.TabIndex = 6;
            label2.Text = "身份";
            // 
            // bm_AppraisalBase
            // 
            bm_AppraisalBase.Location = new Point(82, 51);
            bm_AppraisalBase.Name = "bm_AppraisalBase";
            bm_AppraisalBase.Size = new Size(150, 30);
            bm_AppraisalBase.TabIndex = 5;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(12, 54);
            label1.Name = "label1";
            label1.Size = new Size(64, 24);
            label1.TabIndex = 4;
            label1.Text = "年终奖";
            // 
            // bm_searchButton
            // 
            bm_searchButton.Anchor = AnchorStyles.Left;
            bm_searchButton.Location = new Point(750, 47);
            bm_searchButton.Name = "bm_searchButton";
            bm_searchButton.Size = new Size(113, 34);
            bm_searchButton.TabIndex = 3;
            bm_searchButton.Text = "搜索";
            bm_searchButton.UseVisualStyleBackColor = true;
            bm_searchButton.Click += bm_searchButton_Click;
            // 
            // bm_isDelBox
            // 
            bm_isDelBox.AutoSize = true;
            bm_isDelBox.Location = new Point(615, 51);
            bm_isDelBox.Name = "bm_isDelBox";
            bm_isDelBox.Size = new Size(90, 28);
            bm_isDelBox.TabIndex = 2;
            bm_isDelBox.Text = "已停职";
            bm_isDelBox.UseVisualStyleBackColor = true;
            // 
            // BaseManager
            // 
            AutoScaleDimensions = new SizeF(11F, 24F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(893, 519);
            Controls.Add(groupBox1);
            Controls.Add(bm_dataGirdView);
            FormBorderStyle = FormBorderStyle.None;
            Name = "BaseManager";
            Text = "BaseManager";
            WindowState = FormWindowState.Maximized;
            Load += BaseManager_Load;
            ((System.ComponentModel.ISupportInitialize)bm_dataGirdView).EndInit();
            bm_ContextMenuStrip.ResumeLayout(false);
            groupBox1.ResumeLayout(false);
            groupBox1.PerformLayout();
            ResumeLayout(false);
        }

        #endregion

        private DataGridView bm_dataGirdView;
        private GroupBox groupBox1;
        private ComboBox bm_BaseTypeBox;
        private Label label2;
        private Label label1;
        private Button bm_searchButton;
        private CheckBox bm_isDelBox;
        private DataGridViewTextBoxColumn Id;
        private DataGridViewTextBoxColumn BaseType;
        private DataGridViewTextBoxColumn AppraisalBase;
        private DataGridViewTextBoxColumn IsDel;
        private TextBox bm_AppraisalBase;
        private ContextMenuStrip bm_ContextMenuStrip;
        private ToolStripMenuItem bm_cms_create;
        private ToolStripMenuItem bm_cms_edit;
        private ToolStripMenuItem bm_cms_start;
        private ToolStripMenuItem bm_cms_stop;
    }
}