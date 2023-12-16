namespace appraisal_system {
    partial class SetBases {
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
            groupBox2 = new GroupBox();
            sb_saveButton = new Button();
            groupBox1 = new GroupBox();
            sb_AppraisalBase = new TextBox();
            sb_IsDel = new CheckBox();
            label2 = new Label();
            sb_BaseType = new TextBox();
            username = new Label();
            groupBox2.SuspendLayout();
            groupBox1.SuspendLayout();
            SuspendLayout();
            // 
            // groupBox2
            // 
            groupBox2.Controls.Add(sb_saveButton);
            groupBox2.Location = new Point(10, 189);
            groupBox2.Name = "groupBox2";
            groupBox2.Size = new Size(637, 159);
            groupBox2.TabIndex = 3;
            groupBox2.TabStop = false;
            // 
            // sb_saveButton
            // 
            sb_saveButton.Location = new Point(519, 119);
            sb_saveButton.Name = "sb_saveButton";
            sb_saveButton.Size = new Size(112, 34);
            sb_saveButton.TabIndex = 0;
            sb_saveButton.Text = "保存";
            sb_saveButton.UseVisualStyleBackColor = true;
            sb_saveButton.Click += sb_saveButton_Click;
            // 
            // groupBox1
            // 
            groupBox1.Controls.Add(sb_AppraisalBase);
            groupBox1.Controls.Add(sb_IsDel);
            groupBox1.Controls.Add(label2);
            groupBox1.Controls.Add(sb_BaseType);
            groupBox1.Controls.Add(username);
            groupBox1.Location = new Point(10, 12);
            groupBox1.Name = "groupBox1";
            groupBox1.Size = new Size(637, 171);
            groupBox1.TabIndex = 2;
            groupBox1.TabStop = false;
            groupBox1.Text = "填写信息";
            // 
            // sb_AppraisalBase
            // 
            sb_AppraisalBase.Anchor = AnchorStyles.Left | AnchorStyles.Right;
            sb_AppraisalBase.Location = new Point(451, 44);
            sb_AppraisalBase.Name = "sb_AppraisalBase";
            sb_AppraisalBase.Size = new Size(150, 30);
            sb_AppraisalBase.TabIndex = 7;
            // 
            // sb_IsDel
            // 
            sb_IsDel.AutoSize = true;
            sb_IsDel.Location = new Point(31, 108);
            sb_IsDel.Name = "sb_IsDel";
            sb_IsDel.Size = new Size(72, 28);
            sb_IsDel.TabIndex = 6;
            sb_IsDel.Text = "停用";
            sb_IsDel.UseVisualStyleBackColor = true;
            // 
            // label2
            // 
            label2.Anchor = AnchorStyles.Left | AnchorStyles.Right;
            label2.AutoSize = true;
            label2.Location = new Point(345, 47);
            label2.Name = "label2";
            label2.Size = new Size(100, 24);
            label2.TabIndex = 2;
            label2.Text = "年终奖基数";
            // 
            // sb_BaseType
            // 
            sb_BaseType.Anchor = AnchorStyles.Left | AnchorStyles.Right;
            sb_BaseType.Location = new Point(119, 44);
            sb_BaseType.Name = "sb_BaseType";
            sb_BaseType.Size = new Size(150, 30);
            sb_BaseType.TabIndex = 1;
            // 
            // username
            // 
            username.Anchor = AnchorStyles.Right;
            username.AutoSize = true;
            username.Location = new Point(31, 47);
            username.Name = "username";
            username.Size = new Size(82, 24);
            username.TabIndex = 0;
            username.Text = "身份类型";
            // 
            // SetBases
            // 
            AutoScaleDimensions = new SizeF(11F, 24F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(657, 360);
            Controls.Add(groupBox2);
            Controls.Add(groupBox1);
            Name = "SetBases";
            Text = "SetBases";
            Load += SetBases_Load;
            groupBox2.ResumeLayout(false);
            groupBox1.ResumeLayout(false);
            groupBox1.PerformLayout();
            ResumeLayout(false);
        }

        #endregion

        private GroupBox groupBox2;
        private Button sb_saveButton;
        private GroupBox groupBox1;
        private CheckBox setIsDel;
        private ComboBox setSex;
        private Label label3;
        private Label label2;
        private TextBox sb_BaseType;
        private Label username;
        private CheckBox sb_IsDel;
        private TextBox sb_AppraisalBase;
    }
}