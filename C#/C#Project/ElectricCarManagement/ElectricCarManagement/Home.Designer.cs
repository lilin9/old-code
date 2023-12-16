namespace ElectricCarManagement {
    partial class Home {
        

        /// <summary> 
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary> 
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing) {
            if (disposing && (components != null)) {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region 组件设计器生成的代码

        /// <summary> 
        /// 设计器支持所需的方法 - 不要修改
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent() {
            this.avatar = new System.Windows.Forms.PictureBox();
            this.avatarPanel = new System.Windows.Forms.Panel();
            this.welcomeLabel = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.avatar)).BeginInit();
            this.avatarPanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // avatar
            // 
            this.avatar.Image = global::ElectricCarManagement.Properties.Resources.avatar;
            this.avatar.Location = new System.Drawing.Point(68, 22);
            this.avatar.Margin = new System.Windows.Forms.Padding(0);
            this.avatar.Name = "avatar";
            this.avatar.Size = new System.Drawing.Size(33, 33);
            this.avatar.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.avatar.TabIndex = 0;
            this.avatar.TabStop = false;
            // 
            // avatarPanel
            // 
            this.avatarPanel.Controls.Add(this.welcomeLabel);
            this.avatarPanel.Controls.Add(this.avatar);
            this.avatarPanel.Location = new System.Drawing.Point(181, 119);
            this.avatarPanel.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.avatarPanel.Name = "avatarPanel";
            this.avatarPanel.Size = new System.Drawing.Size(171, 107);
            this.avatarPanel.TabIndex = 1;
            this.avatarPanel.Paint += new System.Windows.Forms.PaintEventHandler(this.avatarPanel_Paint);
            // 
            // welcomeLabel
            // 
            this.welcomeLabel.Font = new System.Drawing.Font("微软雅黑", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.welcomeLabel.Location = new System.Drawing.Point(40, 63);
            this.welcomeLabel.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.welcomeLabel.Name = "welcomeLabel";
            this.welcomeLabel.Size = new System.Drawing.Size(90, 19);
            this.welcomeLabel.TabIndex = 1;
            this.welcomeLabel.Text = "Tom, 欢迎登录";
            this.welcomeLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // Home
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.Controls.Add(this.avatarPanel);
            this.Margin = new System.Windows.Forms.Padding(0);
            this.Name = "Home";
            this.Size = new System.Drawing.Size(585, 430);
            this.Load += new System.EventHandler(this.Home_Load);
            ((System.ComponentModel.ISupportInitialize)(this.avatar)).EndInit();
            this.avatarPanel.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.PictureBox avatar;
        private System.Windows.Forms.Panel avatarPanel;
        private System.Windows.Forms.Label welcomeLabel;
    }
}
