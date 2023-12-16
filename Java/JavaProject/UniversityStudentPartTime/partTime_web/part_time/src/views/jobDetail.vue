<template>
  <div class="job-detail">
    <Retreat/>
    <div class="detail-top">
      职位详情
    </div>
    <div class="detail-content">
      <div class="job-title">{{ title }}</div>
      <div class="job-location">
        <van-icon name="location-o"/>
        {{ location }}
      </div>
      <div class="job-salary">
        <van-icon name="gold-coin-o"/>
        {{ salary }}
      </div>
      <div class="job-publisher">
        <div class="publisher-img" :style="'background-image:url('+imgUrl+')'"></div>
        <div class="publisher-name">
          <van-icon name="user-o"/>
          {{ name }}
        </div>
        <!-- <div class="publisher-mail">
            <van-icon name="comment-o" />
            {{1085550655}}
        </div> -->
        <div class="publisher-detail" @click="userDetails()">
          <van-icon size=".6rem" name="arrow"/>
        </div>
      </div>
      <div class="job-description">
        <div class="description-title">职位描述</div>
        <div class="description-content">{{ description }}</div>
      </div>
      <div class="job-place">
        <div class="place-title">工作地点</div>
        {{ place }}
      </div>
      <div class="job-others">
        <div class="job-collect" v-if="userId !== this.$store.state.loginId">
          <van-icon :name="collected ? 'like' : 'like-o'" @click="collectControl()"/>
          收藏
        </div>
        <div class="job-connection" @click="toContact(userId)">
          <van-button type="info">联系他/她</van-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const tokenObj = JSON.parse(window.localStorage.getItem('token'))

export default {
  name: 'jobDetail',
  data () {
    return {
      title: '',
      positionId: '',
      location: '',
      salary: '',
      imgUrl: '',
      name: '',
      place: '',
      description: '',
      collected: false,
      collecting: [],
      userId: ''
    }
  },
  methods: {
    async init (positionId) {
      await this.$http({
        methods: 'get',
        url: `/positions/${positionId}`
      }).then(res => {
        this.title = res.data.data.jobTitle
        this.location = res.data.data.jobPlace
        this.salary = res.data.data.jobTreat + '/' + res.data.data.treatMethod + ' ' + res.data.data.payMethod
        this.imgUrl = res.data.data.publisher.avatar
        this.name = res.data.data.publisher.nickName === null ? res.data.data.publisher.nickName : res.data.data.publisher.userName
        this.place = res.data.data.jobDetailPlace
        this.description = res.data.data.jobContent
        this.userId = res.data.data.publisher.id
      })
    },
    // 获取用户所收藏工作列表并检查是否包含此工作
    async collectingList (token) {
      await this.$http({
        method: 'post',
        url: `/collect/selectUserCollect`,
        data: {
          token
        }
      }).then(res => {
        this.collecting = res.data.data
        for (let item of this.collecting) {
          if (item.jobId == this.positionId) {
            this.collected = true
          }
        }
      })
    },
    // 收藏某工作
    async collect (id) {
      await this.$authHttp({
        method: 'put',
        url: `/collect/addCollect/${id}`,
        data: {
          token: tokenObj.value
        }
      }).then(res => {
        if (res.data.code === 200) {
          this.collected = true
          this.$toast.success('收藏成功！')
        } else if (res.data.code === 406) {
          this.collected = false
          this.$router.push('/profile')
          this.$toast.fail(res.data.msg)
        } else {
          this.collected = false
          this.$toast.fail('收藏失败！')
        }
      }).catch(err => {
        console.log('收藏某工作 err\n', err)
        this.collected = false
        this.$toast.fail('收藏失败！')
      })
    },
    // 取消收藏某工作
    async uncollect (id) {
      await this.$authHttp({
        method: 'delete',
        url: `/collect/cancelCollect/${id}`,
        data: {
          token: tokenObj.value
        }
      }).then(res => {
        console.log('收藏夹 --》 ', res)
        if (res.data.code === 200) {
          this.collected = false
          this.$toast.success('取消收藏成功！')
        } else {
          this.$toast.fail('取消收藏失败')
        }
      }).catch(err => {
        console.log(err)
        this.collected = true
        this.$toast.fail('取消收藏失败')
      })
    },
    async userDetails () {
      await this.$router.push({
        path: 'userDetail',
        query: {
          userId: this.userId
        }
      })
    },
    collectControl () {
      if (!this.collected) {
        this.collect(this.positionId)
      } else {
        this.uncollect(this.positionId)
      }
    },
    // 跳转到联系人页面
    toContact (userId) {
      this.$router.push({
        path: 'contact',
        query: {
          userId: userId
        }
      })
    }
  },
  components: {},
  // 在页面创建时
  created () {
    this.positionId = this.$route.query.id
    this.init(this.positionId)
    // 获取 token
    this.collectingList(tokenObj.value)
  }
  // watch: {
  //   '$route.query.id': function (newval, oldval) {
  //     if (newval !== undefined) {
  //       this.init(newval)
  //     }
  //   }
  // }
}
</script>

<style lang="less" scoped>
.job-detail {
  width: 100%;

  .detail-top {
    text-align: center;
    margin-top: .1rem;
  }
}

.detail-content {
  margin-top: .1rem;
  margin-left: .1rem;

  .job-title {
    font-size: .35rem;
    font-weight: 500;
  }

  .job-salary {
    color: red;
  }

  .job-publisher {
    position: relative;
    width: 90%;
    margin: 0 auto;
    padding: .2rem;
    height: 1.5rem;
    border-radius: .12rem;
    background: #e9ecef;

    .publisher-img {
      position: absolute;
      width: 1rem;
      height: 1rem;
      margin-top: .25rem;
      background-size: cover;
      background-repeat: no-repeat;
    }

    .publisher-name {
      position: absolute;
      left: 1.5rem;
      top: .75rem;
    }

    .publisher-mail {
      position: absolute;
      left: 1.5rem;
      top: 1rem;
    }

    .publisher-detail {
      position: absolute;
      right: 1rem;
      top: .7rem;
    }
  }

  .job-description {
    margin-top: .3rem;

    .description-title {
      font-size: .35rem;
      font-weight: 500;
    }

    .description-content {
      width: 90%;
      margin: 0 auto;
      padding: .2rem;
      border-radius: 12px;
      background: #e9ecef;
    }
  }

  .job-place {
    margin-top: .3rem;

    .place-title {
      font-size: .35rem;
      font-weight: 500;
    }
  }

  .job-others {
    position: fixed;
    width: 100%;
    bottom: 1rem;

    .job-collect {
      position: absolute;
      right: .4rem;
      bottom: 26em;
      color: red;
    }

    .job-connection {
      position: absolute;
      right: 0;
      bottom: 0.5em;
    }
  }
}
</style>
