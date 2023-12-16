<template>
  <div>
    <!-- 面包蟹区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>分类参数</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <!-- 卡片顶部警示信息 -->
      <el-alert
        title="
注意：只允许为第三级分类设置相关参数！"
        type="warning"
        :closable="false"
        show-icon
      >
      </el-alert>
      <!-- 卡片级联选择框 -->
      <el-row>
        <el-col>
          <span>选择商品分类: </span>
          <el-cascader
            expandTrigger="hover"
            v-model="selectedCateKeys"
            :options="cateList"
            :props="cateProps"
            @change="handleChange"
          ></el-cascader>
        </el-col>
      </el-row>
      <!-- tabs切换栏区域 -->
      <el-tabs v-model="activeName" @tab-click="handleTabClick">
        <el-tab-pane label="动态参数" name="many">
          <el-button
            type="primary"
            :disabled="isBtnDisable"
            @click="showAddDialog"
            >添加属性</el-button
          >
          <!-- 动态参数表格 -->
          <el-table :data="manyTableDate" border stripe style="width: 100%">
            <!-- 展开行区域 -->
            <el-table-column type="expand">
              <template slot-scope="scope">
                <el-tag
                  closable
                  v-for="(item, i) in scope.row.attr_vals"
                  :key="i"
                  @close="handleClose(i,scope.row)"
                  >{{ item }}</el-tag
                >
                <!-- 输入框 -->
                <el-input
                  class="input-new-tag"
                  v-if="scope.row.inputVisible"
                  v-model.trim="scope.row.inputValue"
                  ref="saveTagInput"
                  size="small"
                  @keyup.enter.native="handleInputConfirm(scope.row)"
                  @blur="handleInputConfirm(scope.row)"
                >
                </el-input>
                <!-- 按钮 -->
                <el-button
                  v-else
                  class="button-new-tag"
                  size="small"
                  @click="showInput(scope.row)"
                  >+ New Tag</el-button
                >
              </template>
            </el-table-column>
            <!-- 索引类区域 -->
            <el-table-column type="index" label="#"></el-table-column>
            <el-table-column prop="attr_name" label="参数名称">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <!-- <pre> {{ scope.row}}</pre> -->
                <el-button
                  type="primary"
                  icon="el-icon-edit"
                  size="mini"
                  @click="showEditDialog(scope.row)"
                  >编辑</el-button
                >
                <el-button
                  type="danger"
                  icon="el-icon-delete"
                  size="mini"
                  @click="deleteParams(scope.row)"
                  >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="静态属性" name="only">
          <el-button
            type="primary"
            :disabled="isBtnDisable"
            @click="showAddDialog"
            >添加属性</el-button
          >
          <!-- 静态参数表格 -->
          <el-table :data="onlyTableDate" border stripe style="width: 100%">
            <!-- 静态参数表格展开行 -->
                <el-table-column type="expand">
              <template slot-scope="scope">
                <el-tag
                  closable
                  v-for="(item, i) in scope.row.attr_vals"
                  :key="i"
                  @close="handleClose(i,scope.row)"
                  >{{ item }}</el-tag
                >
                <!-- 输入框 -->
                <el-input
                  class="input-new-tag"
                  v-if="scope.row.inputVisible"
                  v-model.trim="scope.row.inputValue"
                  ref="saveTagInput"
                  size="small"
                  @keyup.enter.native="handleInputConfirm(scope.row)"
                  @blur="handleInputConfirm(scope.row)"
                >
                </el-input>
                <!-- 按钮 -->
                <el-button
                  v-else
                  class="button-new-tag"
                  size="small"
                  @click="showInput(scope.row)"
                  >+ New Tag</el-button
                >
              </template>
            </el-table-column>
            <el-table-column label="#" type="index"> </el-table-column>
            <el-table-column prop="attr_name" label="属性名称">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <!-- <pre>{{ scope.row }}</pre> -->
                <el-button
                  type="primary"
                  icon="el-icon-edit"
                  size="mini"
                  @click="showEditDialog(scope.row)"
                  >编辑</el-button
                >
                <el-button
                  type="danger"
                  icon="el-icon-delete"
                  size="mini"
                  @click="deleteParams(scope.row)"
                  >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-card>

    <!-- 弹出添加对话框 -->
    <el-dialog
      :title="'添加' + titleText"
      :visible.sync="addFormVisible"
      width="50%"
      @close="resetForm"
    >
      <el-form
        :model="addForm"
        :rules="addFormRules"
        ref="addFormRef"
        label-width="100px"
      >
        <el-form-item :label="labelText" prop="attr_name">
          <el-input v-model="addForm.attr_name"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addParams">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 弹出修改对话框 -->
    <el-dialog
      :title="'修改' + titleText"
      :visible.sync="editFormVisible"
      width="50%"
      @close="resetEditForm"
    >
      <el-form
        :model="editForm"
        :rules="editFormRules"
        ref="editFormRef"
        label-width="100px"
      >
        <el-form-item :label="labelText" prop="attr_name">
          <el-input v-model="editForm.attr_name"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editParams">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'MyParams',
  created () {
    this.getCateList()
  },
  data () {
    return {
      // 存放商品分类数据
      cateList: [],
      // 双向绑定存放级联选择框选中的值id
      selectedCateKeys: [],
      // 级联选择框的配置对象
      cateProps: {
        value: 'cat_id',
        label: 'cat_name',
        children: 'children'
      },
      // tabs切换栏
      activeName: 'many',
      // 存放动态参数数据
      manyTableDate: [],
      // 存放静态参数数据
      onlyTableDate: [],
      // 存放编修修改前的数据
      editForm: {},
      // 控制添加对话框显示
      addFormVisible: false,
      // 控制编辑对话框
      editFormVisible: false,
      // 添加属性表单存放的数据
      addForm: {
        attr_name: ''
      },
      // 添加属性表单里面的验证规则
      addFormRules: {
        attr_name: [
          { required: true, message: '请输入参数名称', trigger: 'blur' }
        ]
      },
      // 编辑表单的验证规则
      editFormRules: {
        attr_name: [
          { required: true, message: '请输入参数名称', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    // 获取商品分类数据列表用来渲染级联选择框的数据
    async getCateList () {
      const { data: res } = await this.$http.get('categories')
      if (res.meta.status !== 200) {
        return this.$message.error('获取商品分类数据列表失败')
      }
      this.cateList = res.data
      console.log(this.cateList)
    },
    // 级联选择框状态发生改变，促发此函数
    async handleChange () {
      // console.log(this.selectedCateKeys)
      this.getParamsDate()
    },
    // tabs栏发生切换
    handleTabClick () {
      this.getParamsDate()
    },
    // 获取参数列表数九
    async getParamsDate () {
      // 说明选中的不是三级分类
      if (this.selectedCateKeys.length !== 3) {
        this.selectedCateKeys = []
        this.manyTableDate = []
        this.onlyTableDate = []
        return
      }
      // 选中的是三级分类
      // console.log(this.activeName)
      const { data: res } = await this.$http.get(
        `categories/${this.cateId}/attributes`,
        {
          params: {
            sel: this.activeName
          }
        }
      )
      if (res.meta.status !== 200) {
        return this.$message.error('获取参数列表数据失败')
      }
      // 把res.data.attr_vals分割成一个数组 原来是个字符串无法渲染里面的内容
      res.data.forEach((item) => {
        item.attr_vals = item.attr_vals ? item.attr_vals.split(' ') : []
        item.inputVisible = false
        item.inputValue = ''
      })
      console.log(res.data)

      // 判断获取数据是动态参数数据还是静态参数数据
      if (this.activeName === 'many') {
        this.manyTableDate = res.data
      } else {
        this.onlyTableDate = res.data
      }
    },
    // 点击添加属性弹出对话框
    showAddDialog () {
      this.addFormVisible = true
    },
    // 对话框关闭时促发函数清空表单信息
    resetForm () {
      this.$refs.addFormRef.resetFields()
    },
    // 点击按钮添加参数或者属性
    addParams () {
      // 添加之前先对表单进行预验证
      this.$refs.addFormRef.validate(async (valid) => {
        if (!valid) return
        const { data: res } = await this.$http.post(
          `categories/${this.cateId}/attributes`,
          {
            attr_name: this.addForm.attr_name,
            attr_sel: this.activeName
          }
        )
        if (res.meta.status !== 201) {
          return this.$message.error('添加失败')
        }
        this.$message.success('添加成功')
        this.getParamsDate()
        this.addFormVisible = false
      })
    },
    // 点击编辑按钮弹出对话框
    showEditDialog (val) {
      this.editForm = val
      this.editFormVisible = true
    },
    // 编辑对话框关闭前清空清晰
    resetEditForm () {
      this.$refs.editFormRef.resetFields()
    },
    // 点击确定按钮发起编辑请求修改数据
    editParams () {
      // 对表单进行与验证
      this.$refs.editFormRef.validate(async (valid) => {
        if (!valid) return
        const { data: res } = await this.$http.put(
          `categories/${this.cateId}/attributes/${this.editForm.attr_id}`,
          {
            attr_name: this.editForm.attr_name,
            attr_sel: this.editForm.attr_sel
          }
        )
        if (res.meta.status !== 200) return this.$message.error('更新失败')
        this.getParamsDate()
        this.editFormVisible = false
        this.$message.success('更新成功')
      })
    },
    // 点击删除按钮删除参数数据
    async deleteParams (val) {
      const deleteParamsInfo = await this.$confirm(
        '此操作将永久删除该参数数据, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch((err) => err)
      if (deleteParamsInfo !== 'confirm') {
        return this.$message.error('取消删除')
      }
      // 发起删除参数请求
      const { data: res } = await this.$http.delete(
        `categories/${this.cateId}/attributes/${val.attr_id}`
      )
      if (res.meta.status !== 200) {
        return this.$message.error('删除参数失败')
      }
      this.getParamsDate()
      this.$message.success('删除成功')
    },
    // tag动态编辑文本框状态变化
    async handleInputConfirm (row) {
      // 把文本框输入的内容push到attr_vals中
      row.attr_vals.push(row.inputValue)
      // 发起请求提交修改参数
      this.saveAttr_vals(row)
    },
    // 点击动态tag实现文本框与按钮切换
    showInput (row) {
      row.inputVisible = true
      // 让文本框自动获得焦点
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus()
      })
    },
    // 点击tag标签上面的删除按钮执行删除attr_vals的数据
    handleClose (i, row) {
      row.attr_vals.splice(i, 1)
      // 发起请求删除参数属性数据
      this.saveAttr_vals(row)
    },
    // 定义编辑提交参数的函数
    async saveAttr_vals (row) {
      const { data: res } = await this.$http.put(`categories/${this.cateId}/attributes/${row.attr_id}`, {
        attr_name: row.attr_name,
        attr_sel: row.attr_sel,
        attr_vals: row.attr_vals.join(' ')
      })
      if (res.meta.status !== 200) {
        return this.$message.error('参数更新失败')
      }
      row.inputValue = ''
      row.inputVisible = false
      this.$message.success('参数更新成功')
    }

  },

  computed: {
    // 计算属性来返回一个布尔值确定按钮是否禁用
    isBtnDisable () {
      if (this.selectedCateKeys.length !== 3) {
        return true
      }
      return false
    },
    // 计算属性来确定参数列表请求路径中分类id值
    cateId () {
      if (this.selectedCateKeys.length === 3) {
        return this.selectedCateKeys[2]
      }
      return null
    },
    // 计算属性显示文本标题内容
    titleText () {
      if (this.activeName === 'many') {
        return '动态参数'
      } else {
        return '静态属性'
      }
    },
    labelText () {
      if (this.activeName === 'many') {
        return '动态参数'
      } else {
        return '静态属性'
      }
    }
  }
}
</script>

<style lang="less" scoped>
.el-alert {
  margin-bottom: 15px;
}
.el-table__cell {
  padding-left: 30px;
}
.el-input {
  margin-left: 10px;
  width: 120px;
}
</style>
