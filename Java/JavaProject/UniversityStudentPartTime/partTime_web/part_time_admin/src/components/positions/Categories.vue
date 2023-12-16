<template>
  <div>
    <!-- 商品分类面包屑区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>商品分类</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-row>
        <el-col>
          <el-button type="primary" @click="addCateDialog">添加分类</el-button>
        </el-col>
      </el-row>

      <!-- 表格区 -->
      <tree-table
        class="trees"
        :data="cateList"
        :columns="columns"
        show-index
        index-text="#"
        border
        :show-row-hover="false"
        :expand-type="false"
        :selection-type="false"
      >
        <!-- 是否有效 -->
        <template slot="isok" slot-scope="scope">
          <i
            class="el-icon-check"
            v-if="scope.row.cat_deleted === false"
            style="color: green"
          ></i>
          <i class="el-icon-close" v-else style="color: red"></i>
        </template>
        <!-- 分类排序 -->
        <template slot="order" slot-scope="scope">
          <el-tag size="mini" v-if="scope.row.cat_level === 0">一级</el-tag>
          <el-tag
            type="success"
            size="mini"
            v-else-if="scope.row.cat_level === 1"
            >二级</el-tag
          >
          <el-tag type="warning" size="mini" v-else>三级</el-tag>
        </template>
        <!-- 操作 -->
        <template slot="operate" slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="small" @click="showEditFormDialog(scope.row)"
            >编辑</el-button
          >
          <el-button type="danger" icon="el-icon-delete" size="small"
            @click="deleteCate(scope.row)">删除</el-button
          >
        </template>
      </tree-table>

      <!-- 分页区 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum"
        :page-sizes="[1, 5, 10, 15]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </el-card>

    <!-- 添加分类对话框 -->
    <el-dialog
      title="添加分类"
      :visible.sync="addCateDialogVisible"
      width="50%"
      @closed = "resetAddCateForm"
    >
      <el-form
        :model="addCateForm"
        :rules="addCateFormRules"
        ref="addCateFormRef"
        label-width="100px"
      >
        <el-form-item label="分类名称: " prop="cat_name">
          <el-input v-model="addCateForm.cat_name"></el-input>
        </el-form-item>
        <el-form-item label="父级分类: ">
          <el-cascader
          expandTrigger='hover'
            v-model="selectedKeys"
            :options="parentCateList"
            :props="cascaderProps"
            clearable

            @change="parentCateChange"
          ></el-cascader>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addCateDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addCate"
          >确 定</el-button
        >
      </span>
    </el-dialog>
    <!-- 编辑分类对话框 -->
    <el-dialog
  title="修改分类"
  :visible.sync="editFormDialogVisible"
  width="50%">
  <el-form :model="editCateForm" :rules="editCateFormRules" ref="editCateFormRef" label-width="100px" >
  <el-form-item label="分类名称" prop="cat_name">
    <el-input v-model="editCateForm.cat_name"></el-input>
  </el-form-item>
  </el-form>
  <span slot="footer" class="dialog-footer">
    <el-button @click="editFormDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="aditCate">确 定</el-button>
  </span>
</el-dialog>
  </div>
</template>

<script>
export default {
  name: 'MyCategories',
  created () {
    this.getCateList()
  },
  data () {
    return {
      queryInfo: {
        type: 3,
        pagenum: 1,
        pagesize: 5
      },
      // 存储商品分类列表数据
      cateList: [],
      // 存储父级商品列表数据
      parentCateList: [],
      addCateForm: {
        cat_pid: 0,
        cat_name: '',
        cat_level: 0
      },
      columns: [
        {
          label: '分类名称',
          prop: 'cat_name'
        },
        {
          label: '是否有效',
          type: 'template',
          template: 'isok'
        },
        {
          label: '分类排序',
          type: 'template',
          template: 'order'
        },
        {
          label: '操作',
          type: 'template',
          template: 'operate'
        }
      ],
      // 存储商品分类列表数据总条数
      total: 0,
      // 控制添加分类弹出框
      addCateDialogVisible: false,
      // 控制编辑分类对话框
      editFormDialogVisible: false,
      // 添加分类表单验证规则
      addCateFormRules: {
        cat_name: [
          { required: true, message: '请输入分类名称', trigger: 'blur' }
        ]
      },
      // 编辑分类表单验证规则
      editCateFormRules: {
        cat_name: [
          { required: true, message: '请输入分类名称', trigger: 'blur' }
        ]

      },
      // 级联选择器props配置选项
      cascaderProps: {
        value: 'cat_id',
        label: 'cat_name',
        children: 'children',
        checkStrictly: true

      },
      // 级联选择器中v-model
      selectedKeys: [],
      // 编辑分类存储的数据
      editCateForm: {

      }

    }
  },
  methods: {
    // 获取商品分类数据列表的函数
    async getCateList () {
      const { data: res } = await this.$http.get('categories', {
        params: this.queryInfo
      })
      console.log(res)
      if (res.meta.status !== 200) {
        return this.$message.error('获取商品数据列表失败')
      }
      this.cateList = res.data.result
      this.total = res.data.total
    },
    // 分页去底部每页条数变化促发函数
    handleSizeChange (newSize) {
      this.queryInfo.pagesize = newSize
      this.getCateList()
    },
    // 分页区底部页码值发生改变促发函数
    handleCurrentChange (newPage) {
      this.queryInfo.pagenum = newPage
      this.getCateList()
    },
    // 获取父级分类数据
    async getParentCateList () {
      const { data: res } = await this.$http.get('categories', {
        params: {
          type: 2
        }
      })
      // console.log(res)
      if (res.meta.status !== 200) {
        return this.$message.error('获取父级数据列表失败')
      }
      this.parentCateList = res.data
    },
    // 添加按钮弹出添加分类对话框
    addCateDialog () {
      // 获取父级节点数据
      this.getParentCateList()

      this.addCateDialogVisible = true
    },
    // 级联选择器选项发生改变促发此函数
    parentCateChange () {
      console.log(this.selectedKeys)
      // 判断级联选择器发生改变是选择其他新的商品还是选择为空 选择为空则说明添加
      // 的商品分类为一级分类
      if (this.selectedKeys.length > 0) {
        // 说明添加的不是一级分类 是二级分类或者三级分类
        this.addCateForm.cat_pid = this.selectedKeys[this.selectedKeys.length - 1]
        this.addCateForm.cat_level = this.selectedKeys.length
      } else {
        // else 说明添加的是一级分类
        this.addCateForm.cat_pid = 0
        this.cat_level = 0
      }
    },
    // 添加确定按钮添加分类
    addCate () {
      // console.log(this.addCateForm)
      // 点击确定按钮发起请求添加分类，在添加分类之前需要对表单进行与验证
      this.$refs.addCateFormRef.validate(async valid => {
        if (!valid) return
        const { data: res } = await this.$http.post('categories', this.addCateForm)
        if (res.meta.status !== 201) {
          return this.$message.error('添加分类失败')
        }
        this.$message.success('添加分类成功')
        this.getCateList()
        this.addCateDialogVisible = false
      })
    },
    // 对话框关闭前清空表单内容
    resetAddCateForm () {
      this.$refs.addCateFormRef.resetFields()
      this.addCateForm.cat_pid = 0
      this.addCateForm.cat_level = 0
      this.selectedKeys = []
    },

    // 点击删除按钮删除分类
    async deleteCate (val) {
      const deleteInfo = await this.$confirm('此操作将永久删除该商品分类, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      console.log(deleteInfo)
      // 根据deleteInfo的值来确定按下按钮是确定还是取消从而发起请求
      if (deleteInfo !== 'confirm') {
        return this.$message.error('已取消删除')
      }
      // 如果deleteInfo值为confirm那么说明用户点击的是确定按钮,则发起删除分类的请求
      const { data: res } = await this.$http.delete(`categories/${val.cat_id}`)
      if (res.meta.status !== 200) {
        return this.$message.error('删除分类失败')
      }
      this.getCateList()
      this.$message.success('删除分类成功')
    },
    // 添加编辑按钮弹出编辑对话框
    showEditFormDialog (val) {
      this.editCateForm = val
      this.editFormDialogVisible = true
    },
    // 点击确定按钮发起编辑提交请求
    aditCate () {
      // 发起请求前必须先对表单进行预验证
      this.$refs.editCateFormRef.validate(async valid => {
        if (!valid) return
        const { data: res } = await this.$http.put(`categories/${this.editCateForm.cat_id}`, {
          cat_name: this.editCateForm.cat_name
        })
        if (res.meta.status !== 200) {
          return this.$message.error('商品分类更新失败')
        }
        this.$message.success('更新成功')
        this.getCateList()
        this.editFormDialogVisible = false
      })
    }
  }
}
</script>

<style lang="less" scoped>
.trees {
  margin-top: 15px;
}
.el-cascader {
  width: 100%;
}
</style>
