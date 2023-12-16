<template>
  <div class="contact">
    <Retreat />
    <div class="contact-title">马云</div>
    <div class="contact-content">
        <!-- <div class="contentA">
            <div class="contentA-img" :style="'background-image:url('+imgUrl+')'"></div>
            <div class="contentA-message">
                {{message}}
            </div>
        </div> -->
        <div class="message">你好</div>
    </div>
    <div class="contact-message">
        <van-field
            v-model="message"
            center
            clearable
            placeholder="请输入想说的话"
        >
        <template #button >
            <van-button size="small" type="primary"
                @click="sendMessage(message)"
            >
            发送
            </van-button>
        </template>
</van-field>
    </div>
  </div>
</template>

<script>
export default {
  name: 'contact',
  data () {
    return {
      userName: '',
      userId: '',
      imgUrl: '',
      myImgUrl: '',
      message: ''
    }
  },
  methods: {
    async init (userId) {
      await this.$http({
        methods: 'get',
        url: `/users/${userId}`
      }).then(res => {
        console.log(res)
        this.userName = res.data.name
        this.imgUrl = res.data.avatar_url
      })
      console.log(this.userName)
    },
    sendMessage (message) {
      if (message === '') {
        this.$toast.fail('请不要发送空消息')
        return false
      }
    }
  },
  mounted () {
    this.userId = this.$route.query.userId
    this.init(this.userId)
  }
}
</script>

<style lang="less" scoped>
.contact-title {
  width: 100%;
  height: .25rem;
  margin-top: .1rem;
  text-align: center;
}
.contact-content {
    margin-top: .2rem;
    .contentA {
        position: relative;
    }
    .contentA-img {
        position: absolute;
        width:1rem;
        height:1rem;
        background-size: cover;
        background-repeat: no-repeat;
        border-radius: .3rem;
    }
    .contentA-message {
        position: absolute;
        padding: .5rem;
        left: 1.5rem;
        background: #ccc;
    }
}
.contact-message {
    position: absolute;
    width: 95%;
    bottom: 0;
}
.message {
  width: 95%;
  height: .5rem;
  border-radius: 0.12rem;
  background: #e9ecef;
  padding: .1rem;
}
</style>
