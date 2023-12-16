<template>
  <div>
    <!-- 订单管理面包屑区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
  <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
  <el-breadcrumb-item>用户管理</el-breadcrumb-item>
  <el-breadcrumb-item>关注列表</el-breadcrumb-item>
</el-breadcrumb>

 <!-- 卡片区域 -->
 <el-card>
   <!-- 搜索区域 -->
    <el-input placeholder="请输入内容" v-model.trim="queryInfo.query">
    <el-button slot="append" icon="el-icon-search" @click="getCareList"></el-button>
  </el-input>

  <!-- 表格区域 -->
   <el-table
      :data="careList"
      border
      stripe
      style="width: 100%">
      <el-table-column
        type="index"
        align = "center"
        label="#">
      </el-table-column>
      <el-table-column
        width="90px"
        prop="userName"
        align = "center"
        label="关注人">
      </el-table-column>
      <el-table-column
        width="90px"
        prop="careUserName"
        align = "center"
        label="被关注人">
      </el-table-column>
     <el-table-column
       width="150px"
       prop="collectUser"
       align = "center"
       label="关注人类型">
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
        width="90px"
        align = "center"
        label="是否删除">
      </el-table-column>
     <el-table-column
       prop="updateTime"
       width="200px"
       align = "center"
       label="修改时间">
     </el-table-column>
         <el-table-column
        label="操作"
        width="200px"
         align = "center">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="showBox(scope.row)"></el-button>
          <el-button type="success" icon="el-icon-location-outline" size="mini" @click="showProgressBox" :disabled="true"></el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页区域 -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="queryInfo.pagenum"
      :page-sizes="[1, 5, 10, 15]"
      :page-size="queryInfo.pagesize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
 </el-card>
  <!-- 修改地址对话框 -->
 <el-dialog
  title="修改地址"
  :visible.sync="addressDialogVisible"
  width="50%"
  @close = "resetupdateCare">
  <el-form :model="updateCare" :rules="updateCareRules" ref="updateCareRef" label-width="100px" class="demo-ruleForm">
    <el-form-item label="关注人" prop="userName">
      <el-input v-model.trim="updateCare.userName" readonly></el-input>
    </el-form-item>
    <el-form-item label="被关注人" prop="careUserName"  :disabled="true">
      <el-input v-model.trim="updateCare.careUserName" readonly></el-input>
    </el-form-item>
    <el-form-item label="是否删除" prop="isDelete">
      <el-select value="" v-model.trim="updateCare.isDelete" placeholder="是否删除">
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
    <el-button @click="addressDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="reworkCare">确 定</el-button>
  </span>
</el-dialog>
   <!-- 物流信息对话框 -->
   <el-dialog
  title="物流进度"
  :visible.sync="progressDialogVisible"
  width="50%">
   <!-- 物流信息时间线 -->
    <el-timeline>
    <el-timeline-item
      v-for="(activity, index) in progressInfo"
      :key="index"
      :timestamp="activity.time">
      {{activity.context}}
    </el-timeline-item>
  </el-timeline>
</el-dialog>
  </div>
</template>

<script>
import careData from '@/components/users/careData.js'
export default {
  name: 'Care',
  created () {
    // 页面一加载就请求所有的订单数据列表
    this.getCareList()
  },
  data () {
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
      // 存放所有的订单数据列表
      careList: [],
      // 所有订单数据总条数
      total: 0,
      // 存放请求的参数对象
      queryInfo: {
        query: '',
        pagenum: 1,
        pagesize: 5
      },
      // 控制修改地址对话框显示与隐藏
      addressDialogVisible: false,
      // 修改地址对话框中表单信息
      updateCare: {},
      // 修改地址对话框中表单验证规则
      updateCareRules: {
        address1: [
          { required: true, message: '请选择省市县', trigger: 'blur' }
        ],
        address2: [
          { required: true, message: '请填写详细地址', trigger: 'blur' }
        ]
      },
      // 省市县数据
      careData,
      // 控制物流信息对话框的显示与隐藏
      progressDialogVisible: false,
      // 物流信息假数据
      progressInfo: []

    }
  },
  methods: {
    //修改用户关注数据
    async reworkCare() {
      const { data: res } = await this.$http.put(`userCare/${this.updateCare.id}/${this.updateCare.isDelete}`)
      this.getCareList()
      this.addressDialogVisible = false
      if (res.code !== 200) return
      this.$message.error('数据更新失败')
      this.$message({
        message: '数据更新成功',
        type: 'success'
      })
    },
    // 获取所有订单管理数据
    async getCareList () {
      const { data: res } = await this.$http.get('userCare/')
      if (res.code !== 200) {
        this.$message.error('获取订单管理数据失败')
      }
      console.log(res)
      this.careList = res.data
    },
    // 分页区显示数据条数发生改变促发此函数
    handleSizeChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getCareList()
    },
    // 分页区切换页面促发此函数
    handleCurrentChange (newPage) {
      // console.log(newPage)
      this.queryInfo.pagenum = newPage
      this.getCareList()
    },
    // 点击编辑按钮弹出修改地址对话框
    async showBox (val) {
      this.updateCare = val
      this.addressDialogVisible = true
    },
    // 级联选择器状态发生改变促发此函数
    handleChange () {
      console.log(this.updateCare.address1)
    },
    // 对话框关闭前回调函数清空表单内容
    resetupdateCare () {
      this.updateCare.address1 = []
      this.$refs.updateCareRef.resetFields()
    },
    // 点击按钮弹出物流信息对话框
    async showProgressBox () {
      const { data: res } = await this.$http.get('/kuaidi/1106975712662')
      console.log(res)
      if (res.meta.status !== 200) {
        return this.$message.error('获取物流数据失败')
      }
      this.progressInfo = res.data
      this.progressDialogVisible = true
    }
  }

}
</script>

<style lang="less" scoped>
.el-input {
  width: 500px;
}
.address2Input {
  width: 100%;
}
.el-cascader {
  width: 100%;
}

</style>
