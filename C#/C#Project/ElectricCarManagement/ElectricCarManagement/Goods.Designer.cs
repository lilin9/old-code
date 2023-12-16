namespace ElectricCarManagement {
    partial class Goods {
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
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle1 = new System.Windows.Forms.DataGridViewCellStyle();
            this.GoodsDataTable = new System.Windows.Forms.DataGridView();
            this.CarId = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.CategoryName = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Title = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Description = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.CarImage = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.SalePrice = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Quantity = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.operate = new System.Windows.Forms.DataGridViewButtonColumn();
            ((System.ComponentModel.ISupportInitialize)(this.GoodsDataTable)).BeginInit();
            this.SuspendLayout();
            // 
            // GoodsDataTable
            // 
            this.GoodsDataTable.AllowUserToAddRows = false;
            this.GoodsDataTable.BackgroundColor = System.Drawing.Color.White;
            dataGridViewCellStyle1.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle1.BackColor = System.Drawing.SystemColors.Control;
            dataGridViewCellStyle1.Font = new System.Drawing.Font("微软雅黑", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            dataGridViewCellStyle1.ForeColor = System.Drawing.SystemColors.WindowText;
            dataGridViewCellStyle1.SelectionBackColor = System.Drawing.SystemColors.Highlight;
            dataGridViewCellStyle1.SelectionForeColor = System.Drawing.SystemColors.HighlightText;
            dataGridViewCellStyle1.WrapMode = System.Windows.Forms.DataGridViewTriState.True;
            this.GoodsDataTable.ColumnHeadersDefaultCellStyle = dataGridViewCellStyle1;
            this.GoodsDataTable.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.GoodsDataTable.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.CarId,
            this.CategoryName,
            this.Title,
            this.Description,
            this.CarImage,
            this.SalePrice,
            this.Quantity,
            this.operate});
            this.GoodsDataTable.Location = new System.Drawing.Point(5, 10);
            this.GoodsDataTable.Margin = new System.Windows.Forms.Padding(0);
            this.GoodsDataTable.Name = "GoodsDataTable";
            this.GoodsDataTable.RowHeadersWidth = 32;
            this.GoodsDataTable.RowTemplate.Height = 30;
            this.GoodsDataTable.Size = new System.Drawing.Size(509, 303);
            this.GoodsDataTable.TabIndex = 1;
            this.GoodsDataTable.CellMouseClick += new System.Windows.Forms.DataGridViewCellMouseEventHandler(this.GoodsDataTable_CellMouseClick);
            this.GoodsDataTable.CellPainting += new System.Windows.Forms.DataGridViewCellPaintingEventHandler(this.GoodsDataTable_CellPainting);
            // 
            // CarId
            // 
            this.CarId.DataPropertyName = "CarId";
            this.CarId.HeaderText = "Id";
            this.CarId.MinimumWidth = 8;
            this.CarId.Name = "CarId";
            this.CarId.ReadOnly = true;
            this.CarId.Width = 150;
            // 
            // CategoryName
            // 
            this.CategoryName.DataPropertyName = "CategoryName";
            this.CategoryName.HeaderText = "类别";
            this.CategoryName.Name = "CategoryName";
            this.CategoryName.ReadOnly = true;
            this.CategoryName.Width = 200;
            // 
            // Title
            // 
            this.Title.DataPropertyName = "Title";
            this.Title.HeaderText = "标题";
            this.Title.Name = "Title";
            this.Title.Width = 250;
            // 
            // Description
            // 
            this.Description.DataPropertyName = "Description";
            this.Description.HeaderText = "描述";
            this.Description.Name = "Description";
            this.Description.Width = 250;
            // 
            // CarImage
            // 
            this.CarImage.DataPropertyName = "CarImage";
            this.CarImage.HeaderText = "图片";
            this.CarImage.Name = "CarImage";
            // 
            // SalePrice
            // 
            this.SalePrice.DataPropertyName = "SalePrice";
            this.SalePrice.HeaderText = "单价";
            this.SalePrice.Name = "SalePrice";
            // 
            // Quantity
            // 
            this.Quantity.DataPropertyName = "Quantity";
            this.Quantity.HeaderText = "库存";
            this.Quantity.Name = "Quantity";
            // 
            // operate
            // 
            this.operate.HeaderText = "操作";
            this.operate.Name = "operate";
            this.operate.Width = 120;
            // 
            // Goods
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.Control;
            this.Controls.Add(this.GoodsDataTable);
            this.Margin = new System.Windows.Forms.Padding(0);
            this.Name = "Goods";
            this.Size = new System.Drawing.Size(519, 323);
            this.Load += new System.EventHandler(this.Goods_Load);
            ((System.ComponentModel.ISupportInitialize)(this.GoodsDataTable)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView GoodsDataTable;
        private System.Windows.Forms.DataGridViewTextBoxColumn CarId;
        private System.Windows.Forms.DataGridViewTextBoxColumn CategoryName;
        private System.Windows.Forms.DataGridViewTextBoxColumn Title;
        private System.Windows.Forms.DataGridViewTextBoxColumn Description;
        private System.Windows.Forms.DataGridViewTextBoxColumn CarImage;
        private System.Windows.Forms.DataGridViewTextBoxColumn SalePrice;
        private System.Windows.Forms.DataGridViewTextBoxColumn Quantity;
        private System.Windows.Forms.DataGridViewButtonColumn operate;
    }
}
