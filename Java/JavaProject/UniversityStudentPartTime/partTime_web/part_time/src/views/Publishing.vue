<template>
  <div class="publishing">
      <Retreat />
      <div class="publishing-text">职位发布</div>
      <div class="publishing-content">
          <van-cell-group>
            <van-field v-model="jobTitle" label="工作名" placeholder="请输入工作名" />
            <van-field v-model="jobPlace" label="城 市" placeholder="请输入城市" />
            <van-field v-model="jobTreat" label="薪 资" placeholder="请输入薪资" />
            <van-field v-model="treatMethod" label="待 遇" placeholder="请输入待遇方式：小时/天/月" />
            <van-field v-model="payMethod" label="结 算" placeholder="请输入结算方式：当日结，次日结，月结，完工结" />
            <van-field v-model="jobCate" label="类 别" placeholder="请输入工作类别" />
            <van-field v-model="jobDetailPlace" label="工作地点" placeholder="请输入详细工作地点" />
            <van-field
                v-model="jobContent"
                rows="2"
                autosize
                label="描 述"
                type="textarea"
                maxlength="50"
                placeholder="请输入描述"
                show-word-limit
            />
          </van-cell-group>
      </div>
      <div class="publishing-buttom">
          <van-button size="large" type="info"
           @click="publishing()"
          >发  布
          </van-button>
      </div>
  </div>
</template>

<script>
const tokenObj = JSON.parse(window.localStorage.getItem('token'))

export default {
  name: 'publishing',
  data () {
    return {
      // 兼职标题
      jobTitle: '',
      // 工作地点
      jobPlace: '',
      // 工作待遇
      jobTreat: '',
      // 待遇方式 小时/天/月
      treatMethod: '',
      // 结算方式 当日结，次日结，月结，完工结
      payMethod: '',
      // 兼职类别 类别名称
      jobCate: '',
      // 详细工作地点
      jobDetailPlace: '',
      // 工作内容
      jobContent: ''
    }
  },
  methods: {
    publishing () {
      if (this.jobTitle.length < 2) {
        this.$toast.fail('工作名长度不能小于2')
        return
      }
      if (this.jobPlace.length < 2) {
        this.$toast.fail('城市长度不能小于2')
        return
      }
      if (isNaN(this.jobTreat)) {
        this.$toast.fail('薪资必须是数字类型')
        return
      }
      if (this.treatMethod.length < 2) {
        this.$toast.fail('待遇方式长度不能小于2')
        return
      }
      if (this.payMethod.length < 2) {
        this.$toast.fail('结算方式长度不能小于2')
        return
      }
      if (this.jobCate.length < 2) {
        this.$toast.fail('类别长度不能小于2')
        return
      }
      if (this.jobDetailPlace.length < 2) {
        this.$toast.fail('详细工作地点不能小于2')
        return
      }
      if (this.jobContent.length < 10) {
        this.$toast.fail('描述长度不能小于10')
        return
      }
      let compJobInfo = {
        'jobTitle': this.jobTitle,
        'jobPlace': this.jobPlace,
        'jobTreat': this.jobTreat,
        'treatMethod': this.treatMethod,
        'payMethod': this.payMethod,
        'jobCate': this.jobCate,
        'jobDetailPlace': this.jobDetailPlace,
        'jobContent': this.jobContent
      }
      this.toPublishing(compJobInfo, tokenObj.value)
    },

    toPublishing (compJobInfo, token) {
      this.$authHttp({
        method: 'put',
        url: `/positions/publishing/${token}`,
        data: {
          compJobInfo
        }
      }).then(res => {
        console.log(res)
        if (res.data.code === 200) {
          this.$toast.success('发布成功')
          this.$router.go(-1)
        } else {
          this.$toast.fail('发布失败')
        }
      }).catch(err => {
        console.log(err)
        this.$toast.fail('发布失败')
      })
    }
  },
  components: {
  },
  created () {
  }
}
</script>

<style lang="less" scoped>
.publishing-text {
    text-align: center;
    font-size: .35rem;
    font-weight: 500;
}
.publishing-content {
    width: 99%;
    height: 5rem;
    margin: 1rem auto;
}
</style>
