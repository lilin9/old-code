namespace appraisal_system {
    partial class SetUser {
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
            groupBox1 = new GroupBox();
            setBase = new ComboBox();
            setIsDel = new CheckBox();
            setSex = new ComboBox();
            label3 = new Label();
            label2 = new Label();
            setUsername = new TextBox();
            username = new Label();
            groupBox2 = new GroupBox();
            saveButton = new Button();
            groupBox1.SuspendLayout();
            groupBox2.SuspendLayout();
            SuspendLayout();
            // 
            // groupBox1
            // 
            groupBox1.Controls.Add(setBase);
            groupBox1.Controls.Add(setIsDel);
            groupBox1.Controls.Add(setSex);
            groupBox1.Controls.Add(label3);
            groupBox1.Controls.Add(label2);
            groupBox1.Controls.Add(setUsername);
            groupBox1.Controls.Add(username);
            groupBox1.Location = new Point(12, 12);
            groupBox1.Name = "groupBox1";
            groupBox1.Size = new Size(637, 171);
            groupBox1.TabIndex = 0;
            groupBox1.TabStop = false;
            groupBox1.Text = "填写信息";
            // 
            // setBase
            // 
            setBase.FormattingEnabled = true;
            setBase.Location = new Point(451, 43);
            setBase.Name = "setBase";
            setBase.Size = new Size(150, 32);
            setBase.TabIndex = 7;
            // 
            // setIsDel
            // 
            setIsDel.AutoSize = true;
            setIsDel.Location = new Point(404, 109);
            setIsDel.Name = "setIsDel";
            setIsDel.Size = new Size(108, 28);
            setIsDel.TabIndex = 6;
            setIsDel.Text = "是否停用";
            setIsDel.UseVisualStyleBackColor = true;
            // 
            // setSex
            // 
            setSex.FormattingEnabled = true;
            setSex.Items.AddRange(new object[] { "男", "女" });
            setSex.Location = new Point(100, 109);
            setSex.Name = "setSex";
            setSex.Size = new Size(150, 32);
            setSex.TabIndex = 5;
            setSex.Text = "男";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(33, 113);
            label3.Name = "label3";
            label3.Size = new Size(61, 24);
            label3.TabIndex = 4;
            label3.Text = "性   别";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(399, 46);
            label2.Name = "label2";
            label2.Size = new Size(46, 24);
            label2.TabIndex = 2;
            label2.Text = "身份";
            // 
            // setUsername
            // 
            setUsername.Location = new Point(100, 43);
            setUsername.Name = "setUsername";
            setUsername.Size = new Size(150, 30);
            setUsername.TabIndex = 1;
            // 
            // username
            // 
            username.AutoSize = true;
            username.Location = new Point(31, 46);
            username.Name = "username";
            username.Size = new Size(64, 24);
            username.TabIndex = 0;
            username.Text = "用户名";
            // 
            // groupBox2
            // 
            groupBox2.Controls.Add(saveButton);
            groupBox2.Location = new Point(12, 189);
            groupBox2.Name = "groupBox2";
            groupBox2.Size = new Size(637, 159);
            groupBox2.TabIndex = 1;
            groupBox2.TabStop = false;
            // 
            // saveButton
            // 
            saveButton.Location = new Point(519, 119);
            saveButton.Name = "saveButton";
            saveButton.Size = new Size(112, 34);
            saveButton.TabIndex = 0;
            saveButton.Text = "保存";
            saveButton.UseVisualStyleBackColor = true;
            saveButton.Click += saveButton_Click;
            // 
            // SetUser
            // 
            AutoScaleDimensions = new SizeF(11F, 24F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(657, 360);
            Controls.Add(groupBox2);
            Controls.Add(groupBox1);
            FormBorderStyle = FormBorderStyle.FixedToolWindow;
            Name = "SetUser";
            Text = "SetUser";
            Load += SetUser_Load;
            groupBox1.ResumeLayout(false);
            groupBox1.PerformLayout();
            groupBox2.ResumeLayout(false);
            ResumeLayout(false);
        }

        #endregion

        private GroupBox groupBox1;
        private GroupBox groupBox2;
        private Button saveButton;
        private ComboBox setSex;
        private Label label3;
        private TextBox textBox2;
        private Label label2;
        private TextBox setUsername;
        private Label username;
        private CheckBox setIsDel;
        private ComboBox setBase;
    }
}