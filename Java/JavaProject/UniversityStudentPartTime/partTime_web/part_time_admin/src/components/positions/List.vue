<template>
  <div>
    <!-- 顶部面包屑区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>职位管理</el-breadcrumb-item>
      <el-breadcrumb-item>职位列表</el-breadcrumb-item>
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
          <el-button type="primary" @click="goAddPage">添加职位</el-button>
        </el-col>
      </el-row>

      <!-- 卡片中表格 -->
      <el-table :data="goodsList" style="width: 100%" border stripe>
        <el-table-column type="index" label="#"> </el-table-column>
        <el-table-column prop="jobCate" label="兼职类别">
        </el-table-column>
        <el-table-column width="150px" prop="jobTitle" label="兼职标题">
        </el-table-column>
        <el-table-column prop="jobPlace" label="工作地点">
        </el-table-column>
        <el-table-column width="300px" prop="jobDetailPlace" label="详细工作地点"></el-table-column>
        <el-table-column width="150px" label="工作待遇">
          <template slot-scope="scope">
            {{ scope.row.jobTreat }}元 / {{ scope.row.treatMethod }} {{ scope.row.payMethod }}
          </template>
        </el-table-column>
        <el-table-column width="300px" prop="jobContent" label="工作内容">
        </el-table-column>
        <el-table-column prop="isAvailable" label="是否启用">
          <template v-slot="scope">
          <span v-if="scope.row.isAvailable == 0">
            启用
          </span>
          <span v-if="scope.row.isAvailable == 1">
            不启用
          </span>
          <span v-if="scope.row.isAvailable == 2">
            删除
          </span>
          </template>
        </el-table-column>
        <el-table-column prop="publisher.userName" label="联系人">
        </el-table-column>
        <el-table-column  label="更新时间">
          <template slot-scope="scope">
            {{scope.row.updateTime | global_filter}}
          </template>
        </el-table-column>
        <el-table-column width="120px" label="操作">
          <template slot-scope="scope">
            <!-- <pre>{{ scope.row }}</pre> -->
            <!-- 编辑操作 -->
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-edit"
              @click="showEditDialog(scope.row)"
            ></el-button>
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

      <!-- 修改对话框 -->
      <el-dialog
        title="修改"
        :visible.sync="editFormVisible"
        width="50%">
        <!-- 修改职位内容区域 -->
        <el-form :model="isAvailable" ref="updateCollectRef" label-width="70px" @close="closeEditForm">
          <el-form-item label="是否启用" prop="isAvailable">
            <el-select value="" v-model.trim="isAvailable" placeholder="是否启用">
              <el-option key="0" label="0 启用" value="0"></el-option>
              <el-option key="1" label="1 不启用" value="1"></el-option>
              <el-option key="2" label="2 删除" value="2"></el-option>
            </el-select>
          </el-form-item>

        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="editFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="editParams" >确 定</el-button>
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
  name: 'MyList',
  created () {
    this.getGoodsList()
  },
  data () {
    return {
      //储存需要修改的 职位id
      positionsId: '',
      //储存是否启用 职位 的数据
      isAvailable: '',
      // 控制编辑框的隐藏和显示
      editFormVisible: false,
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
    //点击修改发送请求修改数据
    async editParams() {
      // //发送请求
      // console.log('发送请求 -> ', this.positionsId)
      // console.log('发送请求 -> ', this.isAvailable)
      const { data: res } = await this.$http.put(`positions/${this.positionsId}/${this.isAvailable}`)
      this.editFormVisible = false
      this.getGoodsList()

      if (res.code !== 200)
        return this.$message.error('数据更新失败')

      this.$message({
        message: '数据更新成功',
        type: 'success'
      })
    },
    // 编辑对话框关闭前清空清晰
    closeEditForm() {
      //将修改框隐藏
      this.editFormVisible = false
      //清空修改栏各项数据
      this.isAvailable = ''
      this.positionsId = ''
    },
    //点击修改
    showEditDialog (val) {
      //当点击修改时，将修改框从隐藏改为显示
      this.editFormVisible = true
      console.log('点击修改 val -> ', val.id)
      //设置 isAvailable 的初始值
      this.isAvailable = val.isAvailable
      //设置 positionsId 的初始值
      this.positionsId = val.id
    },
    // 获取商品列表数据
    async getGoodsList () {
      const { data: res } = await this.$http.get('positions/', {
        params: this.queryInfo
      })
      if (res.code !== 200) {
        return this.$message.error('获取职位数据列表失败')
      }
      console.log(res.data)
      this.goodsList = res.data.positionsDetailVos
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
      const confirmInfo = await this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      // console.log(confirmInfo)
      if (confirmInfo !== 'confirm') {
        return this.$message.error('已取消删除')
      }
      // 发起删除商品请求
      const { data: res } = await this.$http.delete(`positions/${row.id}`)
      if (res.code !== 200) {
        return this.$message.error('删除职位数据失败')
      }
      this.$message.success('删除职位成功')
      this.getGoodsList()
    },
    // 点击添加商品跳转到添加商品页面
    goAddPage () {
      this.$router.push('/positions/add')
    }
  }
}
</script>

<style lang="less" scoped>

</style>
