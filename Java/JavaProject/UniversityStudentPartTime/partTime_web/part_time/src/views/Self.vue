<template>
  <div class="self">
    <div class="self-name">{{userName}}</div>
    <div class="self-img" :style="'background-image:url('+imgUrl+')'"></div>
    <Retreat />
    <router-link :to=selfUrl>
        <van-icon name="setting-o"/>
    </router-link>
    <div class="self-headline">
      <van-icon name="gem-o" size=".5rem" />
      <span class="headline-title">个性签名</span>
      <div class="headline-content">{{headline}}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'self',
  components: {
  },
  data () {
    return {
      userId: '',
      imgUrl: '',
      userName: '',
      gender: '',
      selfUrl: '',
      headline: ''
    }
  },
  created () {
    this.userId = this.$route.params.id
    this.getUser(this.userId)
    this.selfUrl = `/setting/${this.userId}`
    console.log(this.selfUrl)
  },
  methods: {
    getUser (userId) {
      this.$http({
        method: 'get',
        url: `/users/${userId}`,
        params: { fields: 'publishPositions' }
      }).then(res => {
        this.imgUrl = res.data.avatar_url
        this.userName = res.data.name
        this.gender = res.data.gender
        this.headline = res.data.headline
        console.log(res)
      })
    }
  }
}
</script>

<style lang="less" scoped>
.self-name {
  text-align: center;
  font-size: .35rem;
  font-weight: 500;
}
.self-img {
    width: 90%;
    margin: 0 auto;
    height: 4rem;
    margin-top: 0.5rem;
    background-size: 100% 100%;
    border-radius: 0.4rem;
    background-repeat: no-repeat;
}
.van-icon-setting-o {
    position: fixed;
    top: 0;
    right: 0;
    font-size: .5rem;
}
.van-icon-gem-o {
  margin-left: .4rem;
  margin-top: .5rem;
}
.headline-content {
  width: 90%;
  margin: 0 auto;
  padding: 0.2rem;
  height: 1rem;
  border-radius: 0.12rem;
  background: #e9ecef;
}
</style>
