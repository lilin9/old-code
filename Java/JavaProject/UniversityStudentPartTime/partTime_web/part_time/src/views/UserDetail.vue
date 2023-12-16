<template>
  <div class="user-detail">
      <Retreat />
      <div class="user-top">
        <div class="user-collect">
          <van-icon :name="followed ? 'like' : 'like-o'"
            size=".5rem" @click="followControl()"/>
        </div>
        <div class="user-content">
          <div class="user-name">{{userName}}</div>
          <div class="user-img" :style="'background-image:url('+imgUrl+')'"></div>
          <div class="user-gender">
            <i :class="[(gender === 'male') ? 'icon-nan' : 'icon-nv', 'iconfont']"></i>
          </div>
          <!-- <div class="user-location">
            <van-icon name="location-o" />广东省珠海市
          </div> -->
          <div class="user-publish">
            <div class="publish-title">发布职位</div>
            <div class="publish-content"
              v-for="item of publishPositions"
              :key="item.id"
              @click="jobDetails(item.id)">
              <div class="position-title">{{item.jobTitle}}</div>
              <div class="position-location">
                <van-icon name="location-o"/>{{item.jobPlace}}
              </div>
              <div class="position-salary">{{item.jobTreat}}元/{{item.treatMethod}} {{item.payMethod}}</div>
            </div>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
const tokenObj = JSON.parse(window.localStorage.getItem('token'))

export default {
  name: 'userDetail',
  data () {
    return {
      userId: '',
      publishPositions: [],
      userName: '',
      gender: '',
      imgUrl: '',
      headline: '',
      followed: false,
      following: []
    }
  },
  methods: {
    async init (userId) {
      await this.$http({
        method: 'post',
        url: `/user/userDetail/${userId}`,
        params: { fields: 'publishPositions' }
      }).then(res => {
        console.log(res)
        this.publishPositions = res.data.data.positionsVos
        this.userName = res.data.data.nickName === null ? res.data.data.nickName : res.data.data.userName
        this.gender = res.data.data.sex
        this.imgUrl = res.data.data.avatar
        this.headline = res.data.data.status
      })
    },
    // 获取用户所关注列表
    async followingList () {
      await this.$http({
        method: 'post',
        url: `/userCare/care`,
        data: {
          token: tokenObj.value
        }
      }).then(res => {
        this.following = res.data.data
        // 通过判断following数组的id是否与userId相等进行关注与未关注的区分
        for (let item of this.following) {
          if (item == this.userId) {
            this.followed = true
            return
          }
        }
      })
    },
    // 关注某用户
    async follow (id) {
      await this.$authHttp({
        method: 'put',
        url: `/userCare/toCare/${id}`,
        data: {
          token: tokenObj.value
        }
      }).then(res => {
        if (res.data.code === 200) {
          console.log(res)
          this.followed = true
          this.$toast.success('关注成功！')
        } else {
          this.followed = false
          this.$toast.fail('关注失败！')
        }
      }).catch(err => {
        console.log(err)
        this.followed = false
        this.$toast.fail('关注失败！')
      })
    },
    // 取消关注某用户
    async unfollow (id) {
      await this.$authHttp({
        method: 'delete',
        url: `/userCare/cancelCare/${id}`,
        data: {
          token: tokenObj.value
        }
      }).then(res => {
        if (res.data.code === 200) {
          console.log(res)
          this.followed = false
          this.$toast.success('取消关注成功！')
        } else {
          this.followed = true
          this.$toast.success('取消关注失败！')
        }
      }).catch(err => {
        console.log(err)
        this.followed = true
        this.$toast.success('取消关注失败！')
      })
    },
    jobDetails (id) {
      this.$router.push({
        path: 'jobDetail',
        query: {
          id: id
        }
      })
    },
    followControl () {
      if (!this.followed) {
        this.follow(this.userId)
      } else {
        this.unfollow(this.userId)
      }
    }
  },
  components: {
  },
  created () {
    let userId = this.$route.query.userId
    this.userId = userId
    this.init(userId)
    this.followingList()
  },
  watch: {
    // '$route.query.userId': function (newval, oldval) {
    //   if (newval !== undefined) {
    //     this.init(newval)
    //   }
    // },
    // '$store.state.loginId': function (newval, oldval) {
    //   this.followingList(newval)
    // }
  }
}
</script>

<style lang="less" scoped>
.user-top {
  position: relative;
  .user-collect {
    position: absolute;
    right: .5rem;
    top: 0.05rem;
  }
}
.user-content {
  position: absolute;
  width: 100%;
  top: 1rem;
  .user-name {
    position: absolute;
    left: .2rem;
    font-size: .35rem;
    font-weight: 500;
  }
  .user-img {
    position: absolute;
    width: 2.3rem;
    height: 2.3rem;
    right: .4rem;
    background-size:cover;
    background-repeat: no-repeat;
    border-radius: 2.3rem;
  }
  .user-gender {
    position: absolute;
    top: .7rem;
    left: .25rem;
  }
  .user-location {
    position: absolute;
    top: 1.2rem;
    left: .2rem;
  }
}
.user-publish {
  position: absolute;
  top: 3rem;
  width: 100%;
  left: 50%;
  transform: translateX(-50%);
  border-radius: .2rem;
  background: #e9ecef;
  .publish-title {
    height: 1rem;
    text-align: center;
    font-size: .3rem;
    font-weight: 500;
    margin-top: .35rem;
    border-bottom: 0.01rem solid #fff;
  }
  .publish-content {
    position: relative;
    height: 1.5rem;
    border-bottom: 0.01rem solid #fff;
    .position-title {
      position: absolute;
      top: .2rem;
    }
    .position-location {
      position: absolute;
      bottom: .2rem;
    }
    .position-salary {
      position: absolute;
      top: .5rem;
      color: red;
      right: .2rem;
    }
  }
}
.iconfont {
  font-size: .2rem!important;
}
</style>
