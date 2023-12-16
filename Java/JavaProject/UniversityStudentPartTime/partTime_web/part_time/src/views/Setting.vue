<template>
  <div class="setting">
      <div class="setting-top">
          <Retreat />
          <div class="setting-title">编辑资料</div>
      </div>
      <div class="setting-img">
          <div class="setting-img-text">头像</div>
          <van-uploader
           :after-read="afterRead"
           v-model="fileList"
           :max-count="1"
           accept="image/*"/>
      </div>
      <div class="setting-info">
          <van-field v-model="userName" left-icon="smile-o" label="用户名" placeholder="请输入用户名"/>
          <van-radio-group class="radio-gender" v-model="radio" direction="horizontal">
            <van-radio name="male">男</van-radio>
            <van-radio name="female">女</van-radio>
         </van-radio-group>
         <van-field
            v-model="headline"
            rows="2"
            autosize
            label="签名"
            type="textarea"
            maxlength="50"
            placeholder="请输入留言"
            show-word-limit
         />
         <div class="setting-save" @click="saveChange()">
             <van-button square type="primary" size="normal">保存</van-button>
         </div>
      </div>
  </div>
</template>

<script>
export default {
  name: 'setting',
  data () {
    return {
      userId: '',
      imgUrl: '',
      userName: '',
      radio: '',
      fileList: [
        {
          url: ''
        }
      ],
      headline: ''
    }
  },
  created () {
    this.userId = this.$route.params.id
    this.getUser(this.userId)
  },
  methods: {
    getUser (userId) {
      this.$http({
        method: 'get',
        url: `/users/${userId}`,
        params: { fields: 'publishPositions' }
      }).then(res => {
        console.log(res.data)
        this.imgUrl = res.data.avatar_url
        this.userName = res.data.name
        this.radio = res.data.gender
        this.fileList[0].url = this.imgUrl
        this.headline = res.data.headline
      })
    },
    async afterRead (file) {
      // 此时可以自行将文件上传至服务器
      const formData = new FormData()
      formData.append('file', file.file)
      await this.$http({
        method: 'post',
        url: 'upload',
        data: formData
      }).then(res => {
        this.imgUrl = res.data.url
      })
    },
    async saveChange () {
      const saveData = {
        'name': this.userName,
        'avatar_url': this.imgUrl,
        'gender': this.radio,
        'headline': this.headline
      }
      await this.$authHttp({
        method: 'patch',
        url: `/users/${this.userId}`,
        data: saveData
      }).then(res => {
        console.log(res)
      })
    }
  }
}
</script>

<style lang="less" scoped>
.setting-title {
    position: fixed;
    top: .1rem;
    left: 50%;
    transform: translateX(-50%);
    font-size: .4rem;
}

.setting-img {
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    top: 1rem;
    &-text {
        text-align: center;
        color: orange;
    }
}
.setting-info {
    position: absolute;
    width: 100%;
    top: 3.5rem;
    .van-cell {
        font-size: .3rem;
    }
    .radio-gender {
        margin: .25rem;
    }
}

.setting-save {
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
}

</style>
