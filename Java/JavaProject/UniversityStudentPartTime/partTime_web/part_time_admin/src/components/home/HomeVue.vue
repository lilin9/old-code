<template>
  <el-container class="home-container">
    <!-- 头部区 -->
    <el-header>
      <div>
        <img src="@/assets/heima.png" alt="">
        <span>大学生兼职网站后台系统</span>

      </div>
      <el-button type="info" @click="logout">退出</el-button>
    </el-header>
    <!-- 内容主体区 -->
    <el-container>

      <!-- 侧边栏区 -->
      <el-aside :width="isCollapse ? '64px' : '200px'">
           <!-- 点击展开和收缩侧边栏 -->
      <div class="toggle-button" @click="changeCollapse">
        <span>|||</span>
      </div>
        <el-menu
      background-color="#cbc8eb"
      text-color="#fff"
      :unique-opened = "true"
      :collapse = "isCollapse"
      :collapse-transition="false"
      :router="true"
      >
      <el-submenu :index="item.id + ''" v-for="item in menuList" :key="item.id">
        <!-- 一级菜单模板区 -->
        <template slot="title">
          <i :class="iconsObject[item.id]"></i>
          <span>{{ item.name }}</span>
        </template>
        <!-- 二级菜单区域 -->
        <el-menu-item :index="'/'+ subItem.path " v-for="subItem in item.children" :key="subItem.id">
            <i class="el-icon-menu"></i>
          <span>{{ subItem.name }}</span>
        </el-menu-item>

      </el-submenu>

    </el-menu>

      </el-aside>
      <!-- 右内容显示区 -->
      <el-main>
        <!-- 路由占位符 -->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import params from "@/components/positions/Params";

export default {
  name: 'MyHome',
  data () {
    return {
      menuList: [],
      iconsObject: {
        125: 'iconfont icon-user',
        103: 'iconfont icon-lock_fill',
        101: 'iconfont icon-shangpin',
        102: 'iconfont icon-danju',
        145: 'iconfont icon-baobiao'
      },
      isCollapse: false
    }
  },
  created () {
    this.getMenuList()
  },
  methods: {
    async logout () {
      const { data: res } = await this.$http.delete('user/logout', {
        data: {
          token: window.sessionStorage.getItem('admin_token')
        }
      })
      // console.log('登录输出 -> ', res)
      if (res.code !== 200) return this.$message.error('退出登录失败')
      console.log(res)
      this.$message({
        message: '退出登录成功',
        type: 'success'
      })
      window.sessionStorage.removeItem('admin_token')
      this.$router.push('/login')
    },
    // 定义左侧侧边栏请求数据函数
    async getMenuList () {
      const { data: res } = await this.$http.get('menu/')
      console.log(res)
      if (res.code !== 200) return this.$message.error(res.msg)
      this.menuList = res.data
    },
    changeCollapse () {
      this.isCollapse = !this.isCollapse
    }
  }
}
</script>

<style lang="less" scoped>
.home-container {
  height: 100%;
}
.el-header {
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  // background-color: #373d41;
  background-color: #cbc8eb;
  align-items: center;
  color: #fff;
  font-size: 20px;
  div {
    display: flex;
    align-items: center;
    span {
      margin-left: 15px;
    }
  }
}
.el-aside {
  // height: 100vh;
  // background-color: #333744;
  background-color:#cbc8eb;
}
.el-menu {
  border-right: 0;
}
.el-main {
  background-color: el-main;
}
.iconfont {
  margin-right: 10px;
}
.toggle-button {
  // background-color: #4a5064;
  background-color: #e0e6f9;
  text-align: center;
  font-size: 10px;
  line-height: 24px;
  letter-spacing: .2em;
  cursor: pointer;
  color: #999;

}
</style>
