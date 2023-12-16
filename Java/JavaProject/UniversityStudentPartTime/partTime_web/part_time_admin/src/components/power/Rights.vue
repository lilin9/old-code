<template>
  <div>
    <!-- 用户列表面包蟹区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>权限列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 内容区 -->
    <el-card class="box-card">
      <el-table :data="rightsList" border stripe style="width: 100%">
        <el-table-column type="index"> </el-table-column>
        <el-table-column prop="authName" label="权限名称"> </el-table-column>
        <el-table-column prop="path" label="路径"> </el-table-column>
        <el-table-column label="权限等级">
          <template slot-scope="scope">
            <el-tag type="success" v-if="scope.row.level==='0'">一级</el-tag>
            <el-tag type="info" v-if="scope.row.level==='1'">二级</el-tag>
            <el-tag type="warning" v-if="scope.row.level==='2'">三级</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'MyRights',
  created () {
    this.getRightsList()
  },
  data () {
    return {
      rightsList: []
    }
  },
  methods: {
    // 封装请求权限列表数据
    async getRightsList () {
      const { data: res } = await this.$http.get('rights/list')
      console.log(res)
      if (res.meta.status !== 200) { return this.$message.error('获取用户列表数据失败') }
      this.rightsList = res.data
    }
  }
}
</script>

<style lang="less" scoped></style>
