<template>
  <div>
    <div class="profile-login" v-if="this.$store.state.logined === false">
        <div class="profile-title">
          {{msg}}
        </div>
        <div class="profile-login">
          <van-field v-model="userName" label="用户名" placeholder="请输入用户名"/>
          <van-field v-model="password" type="password" label="密 码" placeholder="请输入密码"/>
        </div>
        <div class="profile-buttom">
          <van-button v-if="msg =='注册'" type="primary" size="large" class="buttom-register" @click="register">{{msg}}</van-button>
          <van-button v-if="msg =='登录'" type="primary" size="large" class="buttom-login" @click="login">{{msg}}</van-button>
        </div>
        <div v-if="msg =='登录'" class="register-info" @click="toRegister">
          用户未注册，点击注册
        </div>
        <div v-if="msg =='注册'" class="login-info" @click="toLogin">
          用户已注册，点击登录
        </div>
    </div>
    <div class="profile-logined" v-if="this.$store.state.logined === true">
      <navigation />
      <div class="profile-top">
          <van-image round width="1.2rem" height="1.2rem" :src=imgUrl />
          <div class="profile-name">{{userName}}</div>
          <div class="profile-id">id   {{userId}}</div>
          <router-link :to= selfUrl>
            <div class="profile-self">个人主页<van-icon name="arrow" /></div>
          </router-link>
      </div>
      <div class="profile-link">
        <div class="link-title">我的相关</div>
        <div class="link-grid">
          <van-grid :column-num="3">
            <van-grid-item icon="like-o" text="我的关注" @click="userFollowing(userId)"/>
            <van-grid-item icon="comment-circle-o" text="我的发布" @click="userPublishing(userId)" />
            <van-grid-item icon="bag-o" text="我的收藏" @click="positionCollecting(userId)" />
          </van-grid>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'profile',
  data () {
    return {
      msg: '登录',
      userName: '',
      password: '',
      imgUrl: '',
      userId: '',
      selfUrl: ''
    }
  },
  methods: {
    // 去用户收藏页
    positionCollecting (userId) {
      this.$router.push({
        path: '/collectingDetail',
        query: {
          userId: userId
        }
      })
    },
    // 去用户发布页
    userPublishing (userId) {
      this.$router.push({
        path: '/publishingDetail',
        query: {
          userId: userId
        }
      })
    },
    // 去用户关注页
    userFollowing (userId) {
      this.$router.push({
        path: '/followingDetail',
        query: {
          userId: userId
        }
      })
    },
    toRegister () {
      this.msg = '注册'
    },
    toLogin () {
      this.msg = '登录'
    },
    register () {
      if (this.userName.length < 2) {
        this.$toast.fail('用户名长度不能小于2')
        return
      }
      if (this.password.length < 6) {
        this.$toast.fail('密码长度不能小于6')
        return
      }
      if (this.userName === this.password) {
        this.$toast.fail('用户名与密码不能相同')
        return
      }
      this.$http({
        method: 'post',
        url: '/user/register',
        data: {
          'userName': this.userName,
          'password': this.password
        }
      }).then(res => {
        if (res.data.code === 200) {
          this.$toast.success('注册成功')
          this.toLogin()
        } else if (res.data.code === 400) {
          this.$toast.fail('用户名已存在')
        } else {
          this.$toast.fail('出现未知错误')
        }
      }).catch(err => {
        this.$toast.fail('用户名已被占用')
        console.log('err --> ', err)
      })
    },
    async login () {
      await this.$http({
        method: 'post',
        url: '/user/login',
        data: {
          'userName': this.userName,
          'password': this.password
        }
      }).then(res => {
        if (res.data.code === 200) {
          const token = { 'value': res.data.data.token, 'startTime': new Date().getTime() }
          window.localStorage.setItem('token', JSON.stringify(token))
          this.$toast.success('登录成功！')
          this.$store.commit('changeLogined')
          this.$store.commit('saveLoginId', res.data.data.id)
          this.getUser(res.data.data)
          this.$router.push('home')
        } else if (res.data.code === 401) {
          this.$toast.fail(res.data.msg)
        } else {
          this.$toast.fail('用户名或密码错误')
        }
      }).catch(err => {
        this.$toast.fail('用户名或密码错误！')
        console.log(err)
      })
    },
    async getUser (loginUserData) {
      this.userName = loginUserData.nickName == null ? loginUserData.userName : loginUserData.nickName
      this.imgUrl = loginUserData.avatar
      this.userId = loginUserData.id
    }
  }
}
</script>

<style lang="less" scoped>
.profile-title {
  margin-top: .5rem;
  width: 100%;
  font-size: .5rem;
  text-align: center;
  color: green;
}

.profile-login {
  margin-top: 1rem;
}

.profile-buttom {
  margin-top: 1rem;
}

.register-info,
.login-info {
  text-align: center;
  margin-top: 1rem;
  font-size: .5rem;
  outline: khaki;
}

.register-error-info {
  text-align: center;
}

.profile-logined {
  width: 100%;
  height: 10rem;
  background-size: 100% 100%;
  background-repeat: no-repeat;
}
.profile-top {
  position: absolute;
  top: .3rem;
  width: 100%;
  height: 1.5rem;
  background: aliceblue;
}
.profile-name {
  position: absolute;
  top: .1rem;
  left: 1.5rem;
  font-size: 0.3rem;
  font-weight: bolder;
  color: orange;
}
.profile-id {
  position: absolute;
  bottom: .1rem;
  left: 1.5rem;
  color: orange;
}
.profile-self {
  position: absolute;
  top: .2rem;
  right: .2rem;
}
.profile-link {
  position: absolute;
  width: 100%;
  top: 2rem;
  .link-title {
    margin-left: .5rem;
    margin-top: .5rem;
    font-size: .35rem;
    font-weight: 500;
  }
  .link-grid {
    margin-top: .5rem;
  }
}
</style>
