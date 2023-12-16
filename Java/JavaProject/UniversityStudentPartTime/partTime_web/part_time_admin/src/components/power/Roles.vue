<template>
  <div>
    <!-- 顶部面包屑区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 表格主体内容区域 -->

    <el-card class="box-card">
      <!-- 添加角色按钮 -->
      <el-row>
        <el-col>
          <el-button type="primary" @click="roleDialogVisible = true"
            >添加角色</el-button
          >
        </el-col>
      </el-row>
      <!-- 表格内容区域 -->
      <el-table :data="rolesList" border stripe style="width: 100%" >
        <!-- 表格最左侧展开区域 -->
        <el-table-column type="expand">
          <template slot-scope="scope">
            <el-row :class="['btbottom', i1===0 ? 'btbottom':'']" v-for="(item1,i1) in scope.row.children" :key="item1.id">
              <!-- 一级权限 -->
              <el-col :span="3" class="elcol">
                <el-tag closable @close="open(scope.row,item1.id)">{{item1.authName}}</el-tag><i class="el-icon-caret-right"></i>

              </el-col>

              <!-- 二级和三级权限 -->
              <el-col :span="21">
                <!-- 二级和三级还要分成两列 故还需要el-row -->
                <el-row  v-for="item2 in item1.children" :key="item2.id">
                  <el-col :span="6">
                    <el-tag type='success' closable @close="open(scope.row,item2.id)" >{{item1.authName}}</el-tag><i class="el-icon-caret-right"></i>
                  </el-col>
                  <el-col :span="18">
                    <el-tag v-for="item3 in item2.children" type='warning' closable :key="item3.id" @close="open(scope.row,item3.id)">{{item3.authName}}</el-tag>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </template>
        </el-table-column>
        <el-table-column type="index"> </el-table-column>
        <el-table-column prop="roleName" label="角色名称"> </el-table-column>
        <el-table-column prop="roleDesc" label="角色描述"> </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
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
              @click="deleteRole(scope.row)"
              >删除</el-button
            >
            <el-button type="warning" icon="el-icon-setting" size="mini"
              @click="showRightsDialog()" >分配权限</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!--添加用户对话框 -->
    <el-dialog
      title="添加角色"
      :visible.sync="roleDialogVisible"
      width="50%"
      @close="resetAddRoleForm"
    >
      <el-form
        :model="addRoleInfo"
        :rules="addRoleInfoRules"
        ref="addRoleInfoRef"
        label-width="100px"
      >
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="addRoleInfo.roleName"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="roleDesc">
          <el-input v-model="addRoleInfo.roleDesc"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="roleDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addRole">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 编辑角色对话框 -->
    <el-dialog title="编辑角色" :visible.sync="editDialogVisible" width="50%">
      <el-form
        :model="editUserInfo"
        :rules="editUserInfoRules"
        ref="editUserInfo"
        label-width="100px"
      >
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="editUserInfo.roleName"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="roleDesc">
          <el-input v-model="editUserInfo.roleDesc"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editUser">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 分配权限对话框 -->
    <el-dialog
  title="分配权限"
  :visible.sync="rightsDialogVisible"
  width="50%">
  <!-- 显示所有权限数据的树形控件 -->
  <el-tree :data="rightsList" :props="rightsListProps" show-checkbox node-key="id" default-expand-all  :default-checked-keys="defKeys"></el-tree>
  <span slot="footer" class="dialog-footer">
    <el-button @click="rightsDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="rightsDialogVisible = false">确 定</el-button>
  </span>
</el-dialog>
  </div>
</template>

<script>
export default {
  name: 'MyRoles',
  created () {
    this.getRolesList()
  },
  data () {
    return {
      rolesList: [],
      rightsList: [],
      addRoleInfo: {
        roleName: '',
        roleDesc: ''
      },
      // 默认选中的节点id值数组
      defKeys: [105, 116],
      editUserInfo: {},
      // 添加角色表单验证规则
      addRoleInfoRules: {
        roleName: [
          { required: true, message: '请输入角色名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        roleDesc: [
          { min: 3, max: 9, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ]
      },
      // 编辑角色表单的验证规则
      editUserInfoRules: {
        roleName: [
          { required: true, message: '请输入角色名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        roleDesc: [
          { min: 3, max: 9, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ]
      },
      // 树形控件展示数据时的配置选项
      rightsListProps: {
        label: 'authName',
        children: 'children'

      },

      roleDialogVisible: false,
      editDialogVisible: false,
      rightsDialogVisible: false
    }
  },
  methods: {
    // 获取角色列表数据
    async getRolesList () {
      const { data: res } = await this.$http.get('roles')
      console.log(res)
      if (res.meta.status !== 200) { return this.$message.error('获取角色列表数据失败') }
      this.rolesList = res.data
    },
    // 对话框关闭前清空表单里面内容及其验证消息
    resetAddRoleForm () {
      this.$refs.addRoleInfoRef.resetFields()
    },
    // 点击对话框里面的确定按钮发起添加角色请求
    addRole () {
      // 在发起请求之前你对整个添加角色列表表单进行一次预验证
      this.$refs.addRoleInfoRef.validate(async (value) => {
        if (!value) return
        const { data: res } = await this.$http.post('roles', this.addRoleInfo)
        // console.log(res)
        if (res.meta.status !== 201) return this.$message.error('添加角色失败')
        this.getRolesList()
        this.roleDialogVisible = false
        this.$message.success('添加角色成功')
      })
    },
    // 点击删除按钮发起请求删除角色
    async deleteRole (val) {
      // console.log(val)
      const { data: res } = await this.$http.delete(`roles/${val.id}`)
      // console.log(res)
      if (res.meta.status !== 200) return this.$message.error('删除角色失败')
      this.getRolesList()
    },
    // 点击编辑按钮弹出编辑信息对话框
    showEditDialog (val) {
      this.editDialogVisible = true
      this.editUserInfo = val
    },
    // 点击弹出对话框的确定按钮发起修改角色信息请求
    editUser () {
      // 发起请求之前先对表单进行预验证
      this.$refs.editUserInfo.validate(async (value) => {
        if (!value) return
        const { data: res } = await this.$http.put(
          `roles/${this.editUserInfo.id}`,
          {
            roleName: this.editUserInfo.roleName,
            roleDesc: this.editUserInfo.roleDesc
          }
        )
        console.log(res)
        if (res.meta.status !== 200) { return this.$message.error('编辑角色数据失败') }
        this.editDialogVisible = false
        this.$message.success('编辑角色成功')
      })
    },
    // 点击第三级tag栏上面关闭按钮调用启动弹框函数
    async open (newV1, newV2) {
      const result = await this.$confirm('此操作将永久该角色权限, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if (result !== 'confirm') return this.$message.error('取消删除')
      // console.log('确认删除')
      // console.log(val.children)
      // 只要确认删除那就说明发起axios删除角色权限
      const { data: res } = await this.$http.delete(`roles/${newV1.id}/rights/${newV2}`)
      console.log(res)
      if (res.meta.status !== 200) return this.$message.error('删除角色权限失败')
      newV1.children = res.data
    },
    // 点击编辑权限弹出对话框
    async showRightsDialog () {
      this.rightsDialogVisible = true
      // 弹出对话框时获取所有权限数据并渲染到对话框span区域
      const { data: res } = await this.$http.get('rights/tree')
      console.log(res)
      if (res.meta.status !== 200) {
        return this.$message.error('获取权限数据失败')
      }
      // 没有被return 出去说明所有权限数据获取成功，把获取来的数据存到data中
      this.rightsList = res.data
    }

  }
}
</script>

<style lang="less" scoped>
.elcol {
  text-align: center;
}
.el-tag {
  margin: 7px;
}
// 顶边框
.bttop {
  border: 1px solid #eee;
}
// 底边框
.btbottom {
  border: 1px solid #eee;
}
</style>
