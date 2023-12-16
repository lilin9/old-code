<template>
  <div class="login-container">
    <!-- 登录界面展示框 -->
    <div class="login_box">
      <!-- 登录界面展框中的头像盒子 -->
      <div class="avatar_box">
        <img src="@/assets/lg.png" alt="" />
      </div>
      <!-- 底部登录信息框 -->
      <el-form :model="loginForm" :rules="loginFormRulers" class="login_from" ref="formItemRef">
        <!-- 用户名区 -->
        <el-form-item  label-width="0px" prop="username">
          <el-input   prefix-icon="iconfont icon-user" v-model="loginForm.username" ></el-input>
        </el-form-item>
        <!-- 密码区 -->
        <el-form-item  label-width="0px" prop="password" >
          <el-input   prefix-icon="iconfont icon-lock_fill" type="password" v-model="loginForm.password"></el-input>
        </el-form-item>
        <!-- 底部按钮区 -->
        <el-form-item  label-width="0px" class="btns">
            <el-button type="primary" @click="login">登录</el-button>
            <el-button type="info" @click="resetLoginForm">重置</el-button>
        </el-form-item>

      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyLogin',
  data () {
    return {
      // 这是登录表单的数据绑定对象
      loginForm: {
        userName: '',
        password: ''
      },
      loginFormRulers: {
        userName: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 10, message: '用户名长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 3, max: 9, message: '密码长度在 3 到 9 个字符', trigger: 'blur' }

        ]

      }
    }
  },
  methods: {
    // 点击重置按钮重置输入框里面的内容和验证规则
    resetLoginForm () {
      this.$refs.formItemRef.resetFields()
    },
    login () {
      // console.log(this)
      this.$refs.formItemRef.validate(async valid => {
        if (!valid) return
        const { data: res } = await this.$http.post('user/login', this.loginForm)
        // console.log('登录输出 -> ', res)
        if (res.code !== 200) return this.$message.error('用户名和密码错误，登录失败')
        console.log(res)
        this.$message({
          message: '恭喜你，这是一条成功消息',
          type: 'success'
        })
        window.sessionStorage.setItem('admin_token', res.data.token)
        this.$router.push('/home')
      })
    }
  }
}
</script>

<style lang="less" scoped>

.login-container {

  // background-color: skyblue;
  height: 100%;
  .login_box {
    width: 450px;
    height: 300px;
    background-color: #fff;
    border-radius: 3px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    .avatar_box {
      position: absolute;
      height: 130px;
      width: 130px;
      padding: 10px;
      border-radius: 50%;
      border: 1px solid #eee;
      box-shadow: 0 0 10px #ddd;
      background-color: #fff;
      left: 50%;
      transform: translate(-50%, -50%);
      img {
        width: 100%;
        height: 100%;
        background-color: #eee;
        border-radius: 50%;
      }
    }
    .login_from {
      position: absolute;
      bottom: 0 ;
      width: 100%;
      padding: 0 20px;
      box-sizing: border-box;

    }
    .btns {
      display: flex;
      justify-content: flex-end;

    }
  }

}
</style>
