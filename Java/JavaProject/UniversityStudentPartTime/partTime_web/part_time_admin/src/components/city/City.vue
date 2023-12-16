<template>
  <div>
    <!-- 顶部面包屑区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>城市管理</el-breadcrumb-item>
      <el-breadcrumb-item>城市列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片区域 -->
    <el-card>
      <el-row :gutter="20">
        <!-- 输入文本框区域 -->
        <el-col :span="8">
          <el-input placeholder="请输入内容" v-model.trim="queryInfo.query" clearable @clear="getGoodsList">
            <el-button slot="append" icon="el-icon-search" @click="getGoodsList"></el-button>
          </el-input>
        </el-col>
        <!-- 添加商品按钮 -->
        <el-col :span="6">
          <el-button
            type="primary"
            @click="dialogVisible = true">添加城市</el-button>
        </el-col>
      </el-row>

      <!-- 卡片中表格 -->
      <el-table :data="goodsList" style="width: 100%" border stripe>
        <el-table-column type="index" label="#"> </el-table-column>
        <el-table-column width="300px" prop="cityId" label="城市ID">
        </el-table-column>
        <el-table-column prop="city" label="城市">
        </el-table-column>
        <el-table-column  label="更新时间">
          <template slot-scope="scope">
            {{scope.row.updateTime | global_filter}}
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!-- <pre>{{ scope.row }}</pre> -->
            <!-- 删除操作 -->
            <el-button
              type="danger"
              size="mini"
              icon="el-icon-delete"
              @click="deleteGoods(scope.row)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 添加城市 -->
      <el-dialog
        title="添加用户"
        :visible.sync="dialogVisible"
        width="30%"
        @close="addCityClose"
      >
      <!-- 添加城市主体内容 -->
      <el-form :model="addCityData" :rules="addFormRulers" ref="addFormRef" label-width="90px" >

        <el-form-item label="城市邮编" prop="cityId">
          <el-input v-model.trim="addCityData.cityId"></el-input>
        </el-form-item>
        <el-form-item label="城市名" prop="city">
          <el-input v-model.trim="addCityData.city"></el-input>
        </el-form-item>

      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addCity()">确 定</el-button>
      </span>
      </el-dialog>

      <!-- 分页区域 -->
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
  </div>
</template>

<script>
export default {
  name: 'City',
  created () {
    this.getGoodsList()
  },
  data () {
    return {
      dialogVisible: false,
      //添加城市数据
      addCityData: {
        city: '',
        cityId: ''
      },
      // 存放商品数据列表请求回来的数据
      goodsList: [],
      // 存放获取发起商品数据列表请求的请求参数
      queryInfo: {
        query: '',
        pageNum: 1,
        pageSize: 5
      },
      total: 0
    }
  },
  methods: {
    // 添加用户的表单验证规则
    addFormRulers: {
      city: [
        { required: true, message: '请输入城市名', trigger: 'blur' },
        { min: 3, max: 9, message: '长度在 3 到 9 个字符', trigger: 'blur' }
      ]
    },
    // 重置添加城市表单验证信息
    async addCityClose() {
      this.$refs.addFormRef.resetFields()
    },
    //添加城市
    async addCity() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return

        //对城市邮编进行校验
        if (isNaN(this.addCityData.cityId))
          return this.$message.error('城市邮编必须是数字')

        //如果没有 return，则调用后端接口请求添加城市信息
        const {data: res} = await this.$http.post('city/', this.addCityData)

        if (res.code === 200) {
          this.$message.success('添加城市成功')
          //隐藏对话框
          this.dialogVisible = false
          //添加数据后再次刷新页面
          this.getGoodsList()
          return
        }

        if (res.code === 418)
          return this.$message.error('城市名重复了')
        if (res.code === 417)
          return this.$message.error('城市邮编重复')

        return this.$message.error('添加城市失败')
      })
    },
    // 获取商品列表数据
    async getGoodsList () {
      const { data: res } = await this.$http.get('city/', {
        params: this.queryInfo
      })
      if (res.code !== 200) {
        return this.$message.error('获取城市数据列表失败')
      }
      console.log(res.data)
      this.goodsList = res.data.cityVos
      this.total = res.data.total
    },
    // 分页区条数发生改变促发此函数
    handleSizeChange (newVal) {
      this.queryInfo.pagesize = newVal
      this.getGoodsList()
    },
    // 分页去页码发生改变促发此函数
    handleCurrentChange (newVal) {
      this.queryInfo.pagenum = newVal
      this.getGoodsList()
    },
    // 点击删除按钮删除商品
    async deleteGoods (row) {
      const confirmInfo = await this.$confirm('此操作将永久删除该城市, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      // console.log(confirmInfo)
      if (confirmInfo !== 'confirm') {
        return this.$message.error('已取消删除')
      }
      // 发起删除商品请求
      const { data: res } = await this.$http.delete(`city/${row.id}`)
      if (res.code !== 200) {
        return this.$message.error('删除商品数据失败')
      }
      this.$message.success('删除商品成功')
      this.getGoodsList()
    },
    // 点击添加商品跳转到添加商品页面
    goAddPage () {
      this.$refs.addFormRef.resetFields()
    }
  }
}
</script>

<style lang="less" scoped>

</style>
