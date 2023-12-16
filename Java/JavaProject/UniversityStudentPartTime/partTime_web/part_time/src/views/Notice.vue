<template>
  <div class="notice">
    <Retreat />
    <div class="notice-title">我的消息</div>
    <!-- {{$store.state.loginId}} -->
    <div class="notice-message" @click="toContact(userId)">
      <div class="message-p">马云</div>
      <div class="message-c">你好</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'notice',
  data () {
    return {
      userId: '5fe3806c52640849f82cdf4a'
    }
  },
  methods: {
    toContact (userId) {
      this.$router.push({
        path: 'contact',
        query: {
          userId: userId
        }
      })
    }
  },
  mounted () {
    this.$socket.connect()
  },
  sockets: {
    disconnect () {
      console.log('Socket 断开')
    },
    connect_failed () {
      console.log('连接失败')
    },
    connect () {
      console.log('socket connected')
      this.$socket.emit('test2', { 'abc': 123 })
    },
    test1 () {
      console.log('test1')
    }
  }
}
</script>

<style lang="less" scoped>
.notice-title {
  width: 100%;
  height: .25rem;
  margin-top: .1rem;
  text-align: center;
}
.notice-message {
  position: relative;
  margin-top: .2rem;
  width: 95%;
  left: 50%;
  transform: translateX(-50%);
  height: 1rem;
  border-radius: 0.12rem;
  background: #e9ecef;
  padding: .1rem;
  .message-p {
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
  }
  .message-c {
      position: absolute;
      bottom: .1rem;
      font-size: .1rem;
      color: gray;
  }
}
</style>
