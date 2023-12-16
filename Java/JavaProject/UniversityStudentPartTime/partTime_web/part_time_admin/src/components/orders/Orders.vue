<template>
  <div>
    <!-- 订单管理面包屑区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
  <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
  <el-breadcrumb-item>订单管理</el-breadcrumb-item>
  <el-breadcrumb-item>订单列表</el-breadcrumb-item>
</el-breadcrumb>

 <!-- 卡片区域 -->
 <el-card>
   <!-- 搜索区域 -->
    <el-input placeholder="请输入内容" v-model.trim="queryInfo.query">
    <el-button slot="append" icon="el-icon-search" @click="getOrdersList"></el-button>
  </el-input>

  <!-- 表格区域 -->
   <el-table
      :data="ordersList"
      border
      stripe
      style="width: 100%">
      <el-table-column
        type="index"
        align = "center"
        label="#">
      </el-table-column>
      <el-table-column
        prop="order_number"
        align = "center"
        label="订单编号">
      </el-table-column>
      <el-table-column
        width="90px"
        prop="order_price"
        align = "center"
        label="订单价格">
      </el-table-column>
      <el-table-column
      align = "center"
      width="110px"
        label="是否付款">
        <template slot-scope="scope">
          <!-- {{ scope.row}} -->
          <el-tag type="success" v-if="scope.row.pay_status === '1'">已付款</el-tag>
          <el-tag type="danger" v-else>未付款</el-tag>
        </template>

      </el-table-column>
         <el-table-column
        prop="is_send"
        width="90px"
        align = "center"
        label="是否发货">
      </el-table-column>
         <el-table-column
         align = "center"
         width="300px"
        label="下单时间">
        <template slot-scope="scope">
          {{scope.row.create_time | global_filter}}

        </template>
      </el-table-column>
         <el-table-column
        label="操作"
        width="200px"
         align = "center">
        <template>
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="showBox"></el-button>
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
  @close = "resetAddressForm">
  <el-form :model="addressForm" :rules="addressFormRules" ref="addressFormRef" label-width="100px" class="demo-ruleForm">
  <el-form-item label="省市区/县" prop="address1" >
      <el-cascader
      expandTrigger="hover"
    v-model="addressForm.address1"
    :options="cityDate"
    @change="handleChange"></el-cascader>
  </el-form-item>
   <el-form-item label="详细地址" prop="address2">
    <el-input v-model="addressForm.address2" class="address2Input"></el-input>
  </el-form-item>
  </el-form>
  <span slot="footer" class="dialog-footer">
    <el-button @click="addressDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addressDialogVisible = false">确 定</el-button>
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
import cityDate from '@/components/orders/citydata.js'
export default {
  name: 'MyOrders',
  created () {
    // 页面一加载就请求所有的订单数据列表
    this.getOrdersList()
  },
  data () {
    return {
      // 存放所有的订单数据列表
      ordersList: [],
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
      addressForm: {
        address1: [],
        address2: ''

      },
      // 修改地址对话框中表单验证规则
      addressFormRules: {
        address1: [
          { required: true, message: '请选择省市县', trigger: 'blur' }
        ],
        address2: [
          { required: true, message: '请填写详细地址', trigger: 'blur' }
        ]
      },
      // 省市县数据
      cityDate,
      // 控制物流信息对话框的显示与隐藏
      progressDialogVisible: false,
      // 物流信息假数据
      progressInfo: []

    }
  },
  methods: {
    // 获取所有订单管理数据
    async getOrdersList () {
      const { data: res } = await this.$http.get('orders', { params: this.queryInfo })
      if (res.meta.status !== 200) {
        this.$message.error('获取订单管理数据失败')
      }
      console.log(res)
      this.ordersList = res.data.goods
      this.total = res.data.total
    },
    // 分页区显示数据条数发生改变促发此函数
    handleSizeChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getOrdersList()
    },
    // 分页区切换页面促发此函数
    handleCurrentChange (newPage) {
      // console.log(newPage)
      this.queryInfo.pagenum = newPage
      this.getOrdersList()
    },
    // 点击编辑按钮弹出修改地址对话框
    async showBox () {
      this.addressDialogVisible = true
    },
    // 级联选择器状态发生改变促发此函数
    handleChange () {
      console.log(this.addressForm.address1)
    },
    // 对话框关闭前回调函数清空表单内容
    resetAddressForm () {
      this.addressForm.address1 = []
      this.$refs.addressFormRef.resetFields()
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
