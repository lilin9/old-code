namespace ElectricCarManagement {
    partial class Form1 {
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
            this.leftContainer = new System.Windows.Forms.Panel();
            this.leftTitle = new System.Windows.Forms.Label();
            this.orderItemButton = new System.Windows.Forms.Button();
            this.goodsItemButton = new System.Windows.Forms.Button();
            this.userItemButton = new System.Windows.Forms.Button();
            this.homeItemButton = new System.Windows.Forms.Button();
            this.rightContainer = new System.Windows.Forms.Panel();
            this.leftContainer.SuspendLayout();
            this.SuspendLayout();
            // 
            // leftContainer
            // 
            this.leftContainer.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(32)))), ((int)(((byte)(34)))), ((int)(((byte)(42)))));
            this.leftContainer.Controls.Add(this.leftTitle);
            this.leftContainer.Controls.Add(this.orderItemButton);
            this.leftContainer.Controls.Add(this.goodsItemButton);
            this.leftContainer.Controls.Add(this.userItemButton);
            this.leftContainer.Controls.Add(this.homeItemButton);
            this.leftContainer.Font = new System.Drawing.Font("微软雅黑", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.leftContainer.Location = new System.Drawing.Point(0, 0);
            this.leftContainer.Name = "leftContainer";
            this.leftContainer.Size = new System.Drawing.Size(187, 592);
            this.leftContainer.TabIndex = 0;
            // 
            // leftTitle
            // 
            this.leftTitle.BackColor = System.Drawing.Color.Black;
            this.leftTitle.ForeColor = System.Drawing.Color.White;
            this.leftTitle.Location = new System.Drawing.Point(0, 0);
            this.leftTitle.Name = "leftTitle";
            this.leftTitle.Size = new System.Drawing.Size(187, 82);
            this.leftTitle.TabIndex = 4;
            this.leftTitle.Text = "ElectricCar";
            this.leftTitle.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // orderItemButton
            // 
            this.orderItemButton.FlatAppearance.BorderSize = 0;
            this.orderItemButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.orderItemButton.ForeColor = System.Drawing.Color.White;
            this.orderItemButton.Location = new System.Drawing.Point(0, 335);
            this.orderItemButton.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            this.orderItemButton.Name = "orderItemButton";
            this.orderItemButton.Size = new System.Drawing.Size(187, 82);
            this.orderItemButton.TabIndex = 3;
            this.orderItemButton.Text = "订单";
            this.orderItemButton.UseVisualStyleBackColor = true;
            this.orderItemButton.MouseClick += new System.Windows.Forms.MouseEventHandler(this.orderItemButton_Click);
            this.orderItemButton.MouseEnter += new System.EventHandler(this.menuItemButton_MouseEnter);
            this.orderItemButton.MouseLeave += new System.EventHandler(this.menuItemButton_MouseLevel);
            // 
            // goodsItemButton
            // 
            this.goodsItemButton.FlatAppearance.BorderSize = 0;
            this.goodsItemButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.goodsItemButton.ForeColor = System.Drawing.Color.White;
            this.goodsItemButton.Location = new System.Drawing.Point(0, 252);
            this.goodsItemButton.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            this.goodsItemButton.Name = "goodsItemButton";
            this.goodsItemButton.Size = new System.Drawing.Size(187, 82);
            this.goodsItemButton.TabIndex = 2;
            this.goodsItemButton.Text = "商品";
            this.goodsItemButton.UseVisualStyleBackColor = true;
            this.goodsItemButton.MouseClick += new System.Windows.Forms.MouseEventHandler(this.goodsItemButton_Click);
            this.goodsItemButton.MouseEnter += new System.EventHandler(this.menuItemButton_MouseEnter);
            this.goodsItemButton.MouseLeave += new System.EventHandler(this.menuItemButton_MouseLevel);
            // 
            // userItemButton
            // 
            this.userItemButton.FlatAppearance.BorderSize = 0;
            this.userItemButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.userItemButton.ForeColor = System.Drawing.Color.White;
            this.userItemButton.Location = new System.Drawing.Point(0, 170);
            this.userItemButton.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            this.userItemButton.Name = "userItemButton";
            this.userItemButton.Size = new System.Drawing.Size(187, 82);
            this.userItemButton.TabIndex = 1;
            this.userItemButton.Text = "用户";
            this.userItemButton.UseVisualStyleBackColor = true;
            this.userItemButton.MouseClick += new System.Windows.Forms.MouseEventHandler(this.userItemButton_Click);
            this.userItemButton.MouseEnter += new System.EventHandler(this.menuItemButton_MouseEnter);
            this.userItemButton.MouseLeave += new System.EventHandler(this.menuItemButton_MouseLevel);
            // 
            // homeItemButton
            // 
            this.homeItemButton.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.homeItemButton.FlatAppearance.BorderSize = 0;
            this.homeItemButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.homeItemButton.ForeColor = System.Drawing.Color.White;
            this.homeItemButton.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.homeItemButton.Location = new System.Drawing.Point(0, 82);
            this.homeItemButton.Name = "homeItemButton";
            this.homeItemButton.Size = new System.Drawing.Size(187, 82);
            this.homeItemButton.TabIndex = 0;
            this.homeItemButton.Text = "首页";
            this.homeItemButton.UseVisualStyleBackColor = true;
            this.homeItemButton.MouseClick += new System.Windows.Forms.MouseEventHandler(this.homeItemButton_Click);
            this.homeItemButton.MouseEnter += new System.EventHandler(this.menuItemButton_MouseEnter);
            this.homeItemButton.MouseLeave += new System.EventHandler(this.menuItemButton_MouseLevel);
            // 
            // rightContainer
            // 
            this.rightContainer.Font = new System.Drawing.Font("微软雅黑", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.rightContainer.Location = new System.Drawing.Point(217, 0);
            this.rightContainer.Margin = new System.Windows.Forms.Padding(0);
            this.rightContainer.Name = "rightContainer";
            this.rightContainer.Size = new System.Drawing.Size(917, 592);
            this.rightContainer.TabIndex = 1;
            // 
            // Form1
            // 
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.None;
            this.ClientSize = new System.Drawing.Size(1139, 589);
            this.Controls.Add(this.rightContainer);
            this.Controls.Add(this.leftContainer);
            this.Font = new System.Drawing.Font("宋体", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Name = "Form1";
            this.Text = "Home";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.leftContainer.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel leftContainer;
        private System.Windows.Forms.Panel rightContainer;
        private System.Windows.Forms.Button homeItemButton;
        private System.Windows.Forms.Button orderItemButton;
        private System.Windows.Forms.Button goodsItemButton;
        private System.Windows.Forms.Button userItemButton;
        private System.Windows.Forms.Label leftTitle;
    }
}