<template>
  <div>
    <!-- 顶部导航栏面包蟹区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- card布局区域 -->
    <el-card class="box-card">
      <!-- card中输入文本框区域 -->

      <el-row :gutter="20">
        <el-col :span="8">
          <!-- 输入框 -->
          <el-input
            placeholder="请输入内容" v-model="queryInfo.query">
            <el-button slot="append" icon="el-icon-search" @click="getUsersList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="6">
            <el-button type="primary" @click="dialogVisible = true">添加用户</el-button>
        </el-col>
      </el-row>

      <!-- 表格区域 -->
          <el-table
      :data="usersList"
      stripe
      :border = "true"
      style="width: 100%">
      <el-table-column
        type="index"
        label="#">
      </el-table-column>
      <el-table-column
        prop="userName"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="nickName"
        label="昵称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="sex"
        label="性别">
        <template v-slot="scope">
          <span v-if="scope.row.sex == 0">
            男
          </span>
          <span v-if="scope.row.sex == 1">
            女
          </span>
          <span v-if="scope.row.sex == 2">
            未知
          </span>
        </template>
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮箱"
        width="180">
      </el-table-column>
      <el-table-column
        prop="phonenumber"
        label="电话"
        width="180">
      </el-table-column>
      <el-table-column
        prop="type"
        label="角色"
        width="180">
        <template v-slot="scope">
          <span v-if="scope.row.type == 0">
            普通用户
          </span>
          <span v-if="scope.row.type == 1">
            企业联系人
          </span>
          <span v-if="scope.row.type == 2">
            超级管理员
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="状态">
        <!-- 根据插值放置一个按钮 -->
        <template v-slot="scope">
          <!-- {{scope}} -->
          <!-- <p>{{ scope.row }}</p> -->

           <el-switch v-model="scope.row.status"
                      @change="usersStateChange(scope.row)"
                      active-value="0"
                      inactive-value="1">

        </el-switch>

        </template>

      </el-table-column>
      <el-table-column
        prop="updateTime"
        label="修改时间"
        width="180">
      </el-table-column>
      <el-table-column

        label="操作"
        width="180">
         <template slot-scope="scope">

            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showReworkUser(scope.row)"></el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeUser(scope.row.id)"></el-button>
            <el-tooltip class="item" effect="dark" content="搜索" placement="top" :enterable="false">
              <el-button type="warning" icon="el-icon-setting" size="mini" @click="allowRoleDialog(scope.row)"></el-button>
            </el-tooltip>
          </template>

      </el-table-column>
    </el-table>

    <!-- 底部分页区域 -->
     <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="queryInfo.pagenum"
      :page-sizes="[1, 2, 3, 5,10]"
      :page-size="queryInfo.pagesize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="400">
    </el-pagination>

    </el-card>
   <!-- //点击添加用户按钮弹出添加用户对话框 -->
    <el-dialog
  title="添加用户"
  :visible.sync="dialogVisible"
  width="30%"
  @close="addDialogClose"
 >
  <!-- 添加用户内容主体区域 -->
  <el-form :model="addForm" :rules="addFormRulers" ref="addFormRef" label-width="90px" >

  <el-form-item label="昵称" prop="nickName">
    <el-input v-model.trim="addForm.nickName"></el-input>
  </el-form-item>
  <el-form-item label="用户名" prop="userName">
    <el-input v-model.trim="addForm.userName"></el-input>
  </el-form-item>
    <el-form-item label="密码" prop="password">
    <el-input v-model.trim="addForm.password"></el-input>
  </el-form-item>
    <el-form-item label="邮箱" prop="email">
    <el-input v-model.trim="addForm.email"></el-input>
  </el-form-item>
    <el-form-item label="手机号码" prop="phonenumber">
    <el-input v-model.trim="addForm.phonenumber"></el-input>
  </el-form-item>
    <el-form-item label="性别" prop="sex">
      <el-select value="" v-model.trim="addForm.sex" placeholder="选择性别">
        <el-option
          v-for="item in sexOptions"
          :key="item.label"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
  </el-form-item>
    <el-form-item label="类型" prop="type">
      <el-select value="" v-model.trim="addForm.type" placeholder="选择类型">
        <el-option
          v-for="item in typeOptions"
          :key="item.label"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
  </el-form-item>

  </el-form>
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addUser">确 定</el-button>
  </span>
</el-dialog>

   <!-- 点击修改按钮弹出修改用户对话框 -->
   <el-dialog
  title="修改用户"
  :visible.sync="userDialogVisible"
  width="50%">
  <!-- // 修改用户内容主题区域 -->
  <el-form :model="oldUserInfo" :rules="oldUserInfoRulers" ref="oldUserInfoRef" label-width="70px" @close="resetOldUserForm">

    <el-form-item label="昵称" prop="nickName">
      <el-input v-model.trim="oldUserInfo.nickName"></el-input>
    </el-form-item>
    <el-form-item label="用户名" prop="userName"  :disabled="true">
      <el-input v-model.trim="oldUserInfo.userName" readonly></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model.trim="oldUserInfo.password"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model.trim="oldUserInfo.email"></el-input>
    </el-form-item>
    <el-form-item label="手机号码" prop="phonenumber">
      <el-input v-model.trim="oldUserInfo.phonenumber"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="sex">
      <el-select value="" v-model.trim="oldUserInfo.sex" placeholder="选择性别">
        <el-option
          v-for="item in sexOptions"
          :key="item.label"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="类型" prop="type">
      <el-select value="" v-model.trim="oldUserInfo.type" placeholder="选择类型">
        <el-option
          v-for="item in typeOptions"
          :key="item.label"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>

    </el-form>
  <span slot="footer" class="dialog-footer">
    <el-button @click="userDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="reworkUser" >确 定</el-button>
  </span>
</el-dialog>
    <!-- 点击分配角色按钮弹出分配角色对话框 -->
    <el-dialog
  title="分配角色"
  :visible.sync="allowUserDialogVisible"
  width="50%"
  @close="resetAllowSelect">
  <div>
    <p>当前用户 : {{userInfo.username}}</p>
    <p>分配角色 : {{userInfo.role_name}}</p>
    <p>分配新角色:
    <el-select v-model="selectValueId" placeholder="请选择">
      <el-option
        v-for="item in roleList"
        :key="item.id"
        :label="item.roleName"
        :value="item.id">
      </el-option>
    </el-select>
    </p>
  </div>
  <span slot="footer" class="dialog-footer">
    <el-button @click="allowUserDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="allowRole">确 定</el-button>
  </span>
</el-dialog>
  </div>
</template>

<script>
export default {
  name: 'MyUsers',
  created () {
    // 封装页面一打开users就向服务器请求数据
    this.getUsersList()
  },
  data () {
    // 自定义表单验证规则

    // 自定义邮箱的验证规则
    const checkEmail = (rule, value, callback) => {
      const regEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
      if (regEmail.test(value)) {
        return callback()
      }
      callback(new Error('邮箱格式不正确'))
    }
    // 自定义手机号码的验证规则
    const checkPhone = (rule, value, callback) => {
      const regMobile = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
      if (regMobile.test(value)) {
        return callback()
      }
      callback(new Error('手机号码格式不正确'))
    }

    return {
      // 下拉框数据
      sexOptions: [
        {
          value: 0,
          label: '男'
        },
        {
          value: 1,
          label: '女'
        }
      ],
      typeOptions: [
        {
          value: 0,
          label: '普通用户'
        },
        {
          value: 1,
          label: '企业联系人'
        },
        {
          value: 2,
          label: '管理员'
        }
      ],
      // 用来存储请求回来的users的数据
      usersList: [],
      queryInfo: {
        query: '',
        pagenum: 1,
        pagesize: 6
      },
      dialogVisible: false,
      userDialogVisible: false,
      allowUserDialogVisible: false,
      // 添加用户的表单数据对象
      addForm: {
        userName: '',
        nickName: '',
        password: '',
        type: 0,
        email: '',
        sex: 0,
        phonenumber: ''

      },
      userInfo: {

      },
      roleList: [],
      oldUserInfo: {},
      selectValueId: '',
      // 添加用户的表单验证规则
      addFormRulers: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 9, message: '长度在 3 到 9 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 9, message: '长度在 3 到 9 个字符', trigger: 'blur' }

        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }

        ],
        mobile: [
          { required: true, message: '请输入手机号码', trigger: 'blur' },
          { validator: checkPhone, trigger: 'blur' }

        ]

      },
      oldUserInfoRulers: {
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }

        ],
        mobile: [
          { required: true, message: '请输入手机号码', trigger: 'blur' },
          { validator: checkPhone, trigger: 'blur' }

        ]

      }
    }
  },

  methods: {
    // 封装usersAPI接口请求数据函数
    async getUsersList () {
      const { data: res } = await this.$http.get('user/', {
        params: {
          query: this.queryInfo.query,
          pageNum: this.queryInfo.pagenum,
          pageSize: this.queryInfo.pagesize

        }

      })
      console.log(res)
      // 请求获取来的数据转存到usersList
      this.usersList = res.data
    },
    handleSizeChange (newVal) {
      // console.log(newVal)
      this.queryInfo.pagesize = newVal
      // 重新发起axios
      this.getUsersList()
    },
    handleCurrentChange (newVal) {
      // console.log(newVal)
      this.queryInfo.pagenum = newVal
      this.getUsersList()
    },
    async usersStateChange (userInfo) {
      // 只要swich按钮状态发生改变，就调用api修改服务器对于用户的state状态值
      const { data: res } = await this.$http.put(`user/status/${userInfo.id}/${userInfo.status}`)
      if (res.code !== 200) {
        userInfo.status = !userInfo.status
        return this.$message.error('更新用户失败')
      }
      this.$message.success('更新用户成功')
    },
    // 重置添加用户表单验证信息
    addDialogClose () {
      this.$refs.addFormRef.resetFields()
    },
    // 重置修改用户表单验证信息
    resetOldUserForm () {
      this.$refs.oldUserInfoRef.resetFields()
    },
    // 只要用户点击弹出框的确定按钮,就请求服务器添加用户信息，但在请求添加用户信息之前需要对表单进行预先处理
    async addUser () {
      this.$refs.addFormRef.validate(async valid => {
        // console.log(valid)
        if (!valid) return
        // 如果没有被return  则调用接口发起请求添加用户信息
        const { data: res } = await this.$http.post('user/', this.addForm)
        // console.log(res)
        if (res.code === 200) {
          this.$message.success('添加用户成功')
          // 隐藏对话框
          this.dialogVisible = false
          // 添加用户数据后重新获取用户数据刷新页面
          this.getUsersList()
        } else if (res.code === 501) {
          return this.$message.error('用户名不能为空')
        } else if (res.code === 502) {
          return this.$message.error('密码不能为空')
        } else if (res.code === 400) {
          return this.$message.error('用户名已存在')
        } else {
          return this.$message.error('添加用户失败')
        }
      })
    },
    async removeUser (id) {
      const removeInfo = await this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      console.log(removeInfo)
      if (removeInfo !== 'confirm') {
        return this.$message({
          type: 'info',
          message: '已取消删除!'
        })
      }
      // 发起请求删除数据
      const { data: res } = await this.$http.delete(`user/${id}`, { id: id })
      // console.log(res)
      // 删除数据之后调用获取数据函数更新界面
      if (res.code !== 200) {
        return this.$message({
          type: 'info',
          message: '删除数据失败!'
        })
      }
      this.$message({
        type: 'success',
        message: '删除成功!'
      })
      this.getUsersList()
    },
    // 点击修改按钮弹出对话框

    showReworkUser (val) {
      // console.log(val)
      this.oldUserInfo = val
      this.userDialogVisible = true
    },
    // 点击对话框里面的确定按钮发起修改用户请求
    async reworkUser () {
      const { data: res } = await this.$http.put(`user/updateUser/${this.oldUserInfo.id}`, {
        updateUserData: this.oldUserInfo
      })
      // console.log(res)
      this.userDialogVisible = false
      this.getUsersList()
      if (res.code !== 200) return
      this.$message.error('数据更新失败')
      this.$message({
        message: '数据更新成功',
        type: 'success'
      })
    },
    // 点击搜索按钮弹出对话框
    async allowRoleDialog (userInfo) {
      this.userInfo = userInfo

      //  在展示对话框之前获取所有角色列表数据

      const { data: res } = await this.$http.get('roles')
      if (res.meta.status !== 200) {
        return this.$message.error('获取角色列表成功')
      }
      this.roleList = res.data
      this.allowUserDialogVisible = true
    },
    // 点击分配角色对话框中的确定按钮发起请求分配角色
    async allowRole () {
      const { data: res } = await this.$http.put(`users/${this.userInfo.id}/role`, {
        rid: this.selectValueId
      })
      if (res.meta.status !== 200) {
        return this.$message.error('分配用户角色失败')
      }
      this.$message.success('分配角色成功')
      this.getUsersList()
      this.allowUserDialogVisible = false
    },
    // 关闭对话框时触发close清空select里面的内容
    resetAllowSelect () {
      this.selectValueId = ''
    }
  }
}
</script>

<style lang="less" scoped>

</style>
