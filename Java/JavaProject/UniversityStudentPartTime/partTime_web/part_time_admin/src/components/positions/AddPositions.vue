<template>
  <div>
    <!-- 添加商品顶部面包屑区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>职位管理</el-breadcrumb-item>
      <el-breadcrumb-item>职位列表</el-breadcrumb-item>
      <el-breadcrumb-item>添加职位</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-alert title="添加职位信息" type="info" center show-icon> </el-alert>
      <el-steps
        :space="200"
        :active="actionIndex - 0"
        finish-status="success"
        align-center
      >
        <el-step title="基本信息"></el-step>
        <el-step title="联系人信息"></el-step>
        <el-step title="工作内容"></el-step>
        <el-step title="完成"></el-step>
      </el-steps>

      <!-- 左侧切换栏区域 -->
      <el-form
        :model="addForm"
        :rules="addFormRules"
        ref="addFormRef"
        label-width="100px"
        label-position="top"
      >
        <el-tabs v-model="actionIndex" tab-position="left" :before-leave="beforeTabLeave">
          <el-tab-pane label="基本信息" name="0">
            <el-form-item label="职位类别" prop="jobCate">
              <el-input v-model="addForm.jobCate"></el-input>
            </el-form-item>
            <el-form-item label="职位标题" prop="jobTitle">
              <el-input v-model="addForm.jobTitle"></el-input>
            </el-form-item>
            <el-form-item label="工作地点" prop="jobPlace">
              <el-input v-model="addForm.jobPlace"></el-input>
            </el-form-item>
            <el-form-item label="详细工作地点" prop="jobDetailPlace">
              <el-input v-model="addForm.jobDetailPlace"></el-input>
            </el-form-item>
            <el-form-item label="工作待遇(元)" prop="jobTreat">
              <el-input v-model="addForm.jobTreat" type="number"></el-input>
            </el-form-item>
            <el-form-item label="待遇方式" prop="treatMethod"  >
              <!-- 放置一个级联选择框 -->
              <el-select value="" v-model.trim="addForm.treatMethod" placeholder="选择待遇方式">
                <el-option
                  v-for="item in treatMethodList"
                  :key="item.label"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="结算方式" prop="payMethod"  >
              <!-- 放置一个级联选择框 -->
              <el-select value="" v-model.trim="addForm.payMethod" placeholder="选择结算方式">
                <el-option
                  v-for="item in payMethodList"
                  :key="item.label"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="联系人信息" name="2">
            <el-form-item label="用户名" prop="userName">
              <el-input v-model="publisher.userName" @blur="checkUsername"></el-input>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="工作内容" name="3">
            <quill-editor v-model="addForm.jobContent">
            </quill-editor>
            <el-button type="primary" class="addbtn" @click="add">添加职位</el-button>
          </el-tab-pane>
        </el-tabs>
      </el-form>
    </el-card>

  </div>
</template>

<script>
// import _ from 'lodash'
export default {
  name: 'MyAddGoods',
  created () {
    this.getCatesList()
  },
  data () {
    return {
      actionIndex: '0',
      // 添加商品表单存放数据
      addForm: {
        jobCate: '',
        jobTitle: '',
        jobPlace: '',
        jobDetailPlace: '',
        jobTreat: 0,
        payMethod: '',
        jobContent: ''

      },
      //联系人数据
      publisher: {
        userName: ''
      },
      // 存放级联选择框中商品分类的数据
      payMethodList: [
        {value: '当日结',label: '当日结'},
        {value: '次日结',label: '次日结'},
        {value: '月结',label: '月结'},
        {value: '完工结',label: '完工结'}
      ],
      treatMethodList: [
        {value: '小时',label: '小时'},
        {value: '天',label: '天'},
        {value: '月',label: '月'}
      ],
      // 级联选择框的配置对象
      cascaderProps: {
        value: 'value',
        label: 'label'
        // jobCate: []
      },
      // 添加商品表单验证规则
      addFormRules: {
        jobTitle: [
          { required: true, message: '请输入职位类别', trigger: 'blur' }
        ],
        jobPlace: [
          { required: true, message: '请输入工作地点', trigger: 'blur' }

        ],
        jobDetailPlace: [
          { required: true, message: '请输入详细工作地点', trigger: 'blur' }

        ],
        jobTreat: [
          { required: true, message: '请输入工作待遇', trigger: 'blur' }

        ],
        payMethod: [
          { required: true, message: '请输入结算方式', trigger: 'blur' }

        ],
        userName: [
          { required: true, message: '请输入用户名', trigger: 'blur' }

        ],
        jobContent: [
          { required: true, message: '请输入工作内容', trigger: 'blur' }

        ],
        jobCate: [
          { required: true, message: '请选择职位分类', trigger: 'blur' }

        ]

      }
    }
  },
  methods: {
    // 级联选择框只要一选择新的商品数据就促发此函数
    handleCateChange () {
      // 设置级联选择框只能选择三级
      if (this.addForm.jobCate.length !== 3) {
        this.addForm.jobCate = []
      }
      console.log(this.addForm.jobCate)
    },
    // tab栏切换促发此函数定义是否可以切换
    beforeTabLeave (activeName, oldActiveName) {
      // 判断如果是处于第一个tab栏并且长度如果不等于三 则不允许切换
      if (this.addForm.jobCate.length < 3) {
        this.$message({
          message: '职位分类长度不够',
          center: true,
          type: 'error'
        })
        return false
      }

      return oldActiveName
    },
    // 点击tab栏根据情况发起请求
    async checkUsername () {
      //当用户输入完用户名后，判断用户名是否存在
      if (this.publisher.userName) {
        //向后端发送请求
        const {data: res} = await this.$http.get('user/', {
          params: {
            query: this.publisher.userName,
            pageNum: 1,
            pageSize: 1
          }
        })

        console.log(res.data)
        //如果能请求不到数据，说明用户输入的用户名不存在
        if (res.code !== 200 || !res.data)
          return this.$message.error('用户不存在')
      }
    },
    // 点击，添加商品，添加之前需要对表单进行预验证
    add () {
      this.$refs.addFormRef.validate(async valid => {
        if (!this.addForm) {
          return this.$message.error('请填写相关信息')
        }
        // 添加商品
        // this.addForm.jobCate = this.addForm.jobCate.join(',')
        // console.log(this.addForm)
        // const form = _.cloneDeep(this.addForm)
        // form.jobCate = form.jobCate.join(',')
        // console.log(form)
        const positionsData = JSON.parse(JSON.stringify(this.addForm))
        // 添加商品的所有请求数据全处理完成发起请求
        const { data: res } = await this.$http.post(`positions/${this.publisher.userName}`, positionsData)
        if (res.code !== 200) {
          return this.$message.error('添加职位失败')
        }
        this.$message.success('添加职位成功')
        this.$router.push('/positions')
      })
    }

  },
  computed: {
    cateId () {
      return this.addForm.jobCate[2]
    }
  }
}
</script>

<style lang="less" scoped>
.el-checkbox {
  margin: 0 10px 0 0 !important;
}
.previewImg {
  width: 100%;
}
.addbtn {
  margin-top: 15px;
  float: right;
}

</style>
