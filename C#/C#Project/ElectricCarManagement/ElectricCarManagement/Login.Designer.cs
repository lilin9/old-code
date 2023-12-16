namespace ElectricCarManagement {
    partial class Login {
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
            this.LoginPanel = new System.Windows.Forms.Panel();
            this.LoginContainer = new System.Windows.Forms.Panel();
            this.ButtonPanel = new System.Windows.Forms.Panel();
            this.LoginButton = new System.Windows.Forms.Button();
            this.PasswordPanel = new System.Windows.Forms.Panel();
            this.passwordTextBox = new System.Windows.Forms.TextBox();
            this.PasswordLabel = new System.Windows.Forms.Label();
            this.UsernamePanel = new System.Windows.Forms.Panel();
            this.usernameTextBox = new System.Windows.Forms.TextBox();
            this.UsernameLabel = new System.Windows.Forms.Label();
            this.buttonOfLogin = new System.Windows.Forms.Button();
            this.LoginPanel.SuspendLayout();
            this.LoginContainer.SuspendLayout();
            this.ButtonPanel.SuspendLayout();
            this.PasswordPanel.SuspendLayout();
            this.UsernamePanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // LoginPanel
            // 
            this.LoginPanel.Controls.Add(this.LoginContainer);
            this.LoginPanel.Location = new System.Drawing.Point(0, 0);
            this.LoginPanel.Name = "LoginPanel";
            this.LoginPanel.Size = new System.Drawing.Size(1139, 589);
            this.LoginPanel.TabIndex = 0;
            // 
            // LoginContainer
            // 
            this.LoginContainer.BackColor = System.Drawing.Color.White;
            this.LoginContainer.Controls.Add(this.ButtonPanel);
            this.LoginContainer.Controls.Add(this.PasswordPanel);
            this.LoginContainer.Controls.Add(this.UsernamePanel);
            this.LoginContainer.Location = new System.Drawing.Point(370, 145);
            this.LoginContainer.Name = "LoginContainer";
            this.LoginContainer.Size = new System.Drawing.Size(400, 300);
            this.LoginContainer.TabIndex = 0;
            // 
            // ButtonPanel
            // 
            this.ButtonPanel.Controls.Add(this.buttonOfLogin);
            this.ButtonPanel.Controls.Add(this.LoginButton);
            this.ButtonPanel.Location = new System.Drawing.Point(65, 213);
            this.ButtonPanel.Name = "ButtonPanel";
            this.ButtonPanel.Size = new System.Drawing.Size(270, 39);
            this.ButtonPanel.TabIndex = 3;
            // 
            // LoginButton
            // 
            this.LoginButton.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(64)))), ((int)(((byte)(158)))), ((int)(((byte)(255)))));
            this.LoginButton.Font = new System.Drawing.Font("微软雅黑", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.LoginButton.ForeColor = System.Drawing.Color.White;
            this.LoginButton.Location = new System.Drawing.Point(83, 3);
            this.LoginButton.Name = "LoginButton";
            this.LoginButton.Size = new System.Drawing.Size(113, 37);
            this.LoginButton.TabIndex = 2;
            this.LoginButton.Text = "登录";
            this.LoginButton.UseVisualStyleBackColor = false;
            // 
            // PasswordPanel
            // 
            this.PasswordPanel.Controls.Add(this.passwordTextBox);
            this.PasswordPanel.Controls.Add(this.PasswordLabel);
            this.PasswordPanel.Location = new System.Drawing.Point(65, 129);
            this.PasswordPanel.Name = "PasswordPanel";
            this.PasswordPanel.Size = new System.Drawing.Size(270, 39);
            this.PasswordPanel.TabIndex = 1;
            // 
            // passwordTextBox
            // 
            this.passwordTextBox.Font = new System.Drawing.Font("微软雅黑", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.passwordTextBox.Location = new System.Drawing.Point(100, 3);
            this.passwordTextBox.Multiline = true;
            this.passwordTextBox.Name = "passwordTextBox";
            this.passwordTextBox.PasswordChar = '*';
            this.passwordTextBox.Size = new System.Drawing.Size(149, 34);
            this.passwordTextBox.TabIndex = 2;
            // 
            // PasswordLabel
            // 
            this.PasswordLabel.AutoSize = true;
            this.PasswordLabel.Font = new System.Drawing.Font("微软雅黑", 14.25F);
            this.PasswordLabel.Location = new System.Drawing.Point(19, 8);
            this.PasswordLabel.Name = "PasswordLabel";
            this.PasswordLabel.Size = new System.Drawing.Size(50, 25);
            this.PasswordLabel.TabIndex = 1;
            this.PasswordLabel.Text = "密码";
            // 
            // UsernamePanel
            // 
            this.UsernamePanel.Controls.Add(this.usernameTextBox);
            this.UsernamePanel.Controls.Add(this.UsernameLabel);
            this.UsernamePanel.Location = new System.Drawing.Point(65, 45);
            this.UsernamePanel.Name = "UsernamePanel";
            this.UsernamePanel.Size = new System.Drawing.Size(270, 39);
            this.UsernamePanel.TabIndex = 0;
            // 
            // usernameTextBox
            // 
            this.usernameTextBox.Font = new System.Drawing.Font("微软雅黑", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.usernameTextBox.Location = new System.Drawing.Point(100, 3);
            this.usernameTextBox.Multiline = true;
            this.usernameTextBox.Name = "usernameTextBox";
            this.usernameTextBox.Size = new System.Drawing.Size(149, 34);
            this.usernameTextBox.TabIndex = 1;
            // 
            // UsernameLabel
            // 
            this.UsernameLabel.AutoSize = true;
            this.UsernameLabel.Font = new System.Drawing.Font("微软雅黑", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.UsernameLabel.Location = new System.Drawing.Point(10, 8);
            this.UsernameLabel.Margin = new System.Windows.Forms.Padding(0);
            this.UsernameLabel.Name = "UsernameLabel";
            this.UsernameLabel.Size = new System.Drawing.Size(69, 25);
            this.UsernameLabel.TabIndex = 0;
            this.UsernameLabel.Text = "用户名";
            // 
            // buttonOfLogin
            // 
            this.buttonOfLogin.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(64)))), ((int)(((byte)(158)))), ((int)(((byte)(255)))));
            this.buttonOfLogin.Font = new System.Drawing.Font("微软雅黑", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.buttonOfLogin.ForeColor = System.Drawing.Color.White;
            this.buttonOfLogin.Location = new System.Drawing.Point(83, 0);
            this.buttonOfLogin.Name = "buttonOfLogin";
            this.buttonOfLogin.Size = new System.Drawing.Size(113, 37);
            this.buttonOfLogin.TabIndex = 2;
            this.buttonOfLogin.Text = "登录";
            this.buttonOfLogin.UseVisualStyleBackColor = false;
            this.buttonOfLogin.Click += new System.EventHandler(this.buttonOfLogin_Click);
            // 
            // Login
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1139, 589);
            this.Controls.Add(this.LoginPanel);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Name = "Login";
            this.Text = "Login";
            this.LoginPanel.ResumeLayout(false);
            this.LoginContainer.ResumeLayout(false);
            this.ButtonPanel.ResumeLayout(false);
            this.PasswordPanel.ResumeLayout(false);
            this.PasswordPanel.PerformLayout();
            this.UsernamePanel.ResumeLayout(false);
            this.UsernamePanel.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel LoginPanel;
        private System.Windows.Forms.Panel LoginContainer;
        private System.Windows.Forms.Button LoginButton;
        private System.Windows.Forms.Panel PasswordPanel;
        private System.Windows.Forms.TextBox passwordTextBox;
        private System.Windows.Forms.Label PasswordLabel;
        private System.Windows.Forms.Panel UsernamePanel;
        private System.Windows.Forms.TextBox usernameTextBox;
        private System.Windows.Forms.Label UsernameLabel;
        private System.Windows.Forms.Panel ButtonPanel;
        private System.Windows.Forms.Button buttonOfLogin;
    }
}