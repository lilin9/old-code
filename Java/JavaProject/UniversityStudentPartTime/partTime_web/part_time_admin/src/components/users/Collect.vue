<template>
  <div>
    <!-- 顶部导航栏面包蟹区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户收藏</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- card布局区域 -->
    <el-card class="box-card">
      <!-- card中输入文本框区域 -->

      <el-row :gutter="20">
        <el-col :span="8">
          <!-- 输入框 -->
          <el-input
            placeholder="请输入内容" v-model="queryInfo.query">
            <el-button slot="append" icon="el-icon-search" @click="getCollectsList"></el-button>
          </el-input>
        </el-col>
      </el-row>

      <!-- 表格区域 -->
          <el-table
      :data="collectList"
      stripe
      :border = "true"
      style="width: 100%">
      <el-table-column
        type="index"
        label="#">
      </el-table-column>
      <el-table-column
        prop="userName"
        label="收藏人"
        width="180">
      </el-table-column>
      <el-table-column
        prop="jobTitle"
        label="职位名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="collectUser"
        label="发起人类型">
        <template v-slot="scope">
          <span v-if="scope.row.collectUser == 0">
            学生发起
          </span>
          <span v-if="scope.row.collectUser == 1">
            企业发起
          </span>
          <span v-if="scope.row.collectUser == 2">
            管理员发起
          </span>
        </template>
      </el-table-column>
      <el-table-column
              prop="isDelete"
              label="是否删除">
        <template v-slot="scope">
          <span v-if="scope.row.isDelete == 0">
            未删除
          </span>
          <span v-if="scope.row.isDelete == 1">
            已删除
          </span>
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

   <!-- 点击修改按钮弹出修改用户对话框 -->
   <el-dialog
  title="修改用户"
  :visible.sync="userDialogVisible"
  width="50%">
  <!-- // 修改用户内容主题区域 -->
  <el-form :model="updateCollect" ref="updateCollectRef" label-width="70px" @close="resetOldUserForm">
    <el-form-item label="收藏人" prop="userName">
      <el-input v-model.trim="updateCollect.userName" readonly></el-input>
    </el-form-item>
    <el-form-item label="职位名" prop="jobTitle"  :disabled="true">
      <el-input v-model.trim="updateCollect.jobTitle" readonly></el-input>
    </el-form-item>
    <el-form-item label="是否删除" prop="isDelete">
      <el-select value="" v-model.trim="updateCollect.isDelete" placeholder="是否删除">
        <el-option
          v-for="item in isDelete_"
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
  </div>
</template>

<script>
export default {
  name: 'Collect',
  created () {
    // 封装页面一打开users就向服务器请求数据
    this.getCollectsList()
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
      isDelete_: [
        {
          value: 0,
          label: '0 未删除'
        },
        {
          value: 1,
          label: '1 已删除'
        }
      ],
      // 用来存储请求回来的users的数据
      collectList: [],
      queryInfo: {
        query: '',
        pagenum: 1,
        pagesize: 6
      },
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
      updateCollect: {},
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

      }
    }
  },

  methods: {
    // 封装collectsAPI接口请求数据函数
    async getCollectsList () {
      const { data: res } = await this.$http.get('collect/', {
        params: {
          query: this.queryInfo.query,
          pageNum: this.queryInfo.pagenum,
          pageSize: this.queryInfo.pagesize

        }

      })
      console.log(res)
      // 请求获取来的数据转存到usersList
      this.collectList = res.data
    },
    handleSizeChange (newVal) {
      // console.log(newVal)
      this.queryInfo.pagesize = newVal
      // 重新发起axios
      this.getCollectsList()
    },
    handleCurrentChange (newVal) {
      // console.log(newVal)
      this.queryInfo.pagenum = newVal
      this.getCollectsList()
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
      this.$refs.updateCollectRef.resetFields()
    },
    async removeUser (id) {
      const removeInfo = await this.$confirm('此操作将永久删除该收藏, 是否继续?', '提示', {
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
      const { data: res } = await this.$http.delete(`collect/${id}`, { id: id })
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
      this.getCollectsList()
    },
    // 点击修改按钮弹出对话框

    showReworkUser (val) {
      // console.log(val)
      this.updateCollect = val
      this.userDialogVisible = true
    },
    // 点击对话框里面的确定按钮发起修改用户请求
    async reworkUser () {
      const { data: res } = await this.$http.put(`collect/${this.updateCollect.id}`, {
        updateCollectData: this.updateCollect
      })
      // console.log(res)
      this.userDialogVisible = false
      this.getCollectsList()
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

      // const { data: res } = await this.$http.get('roles')
      // if (res.meta.status !== 200) {
      //   return this.$message.error('获取角色列表成功')
      // }
      // this.roleList = res.data
      // this.allowUserDialogVisible = true
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
      this.getCollectsList()
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
