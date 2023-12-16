<template>
    <div class="publishing-detail">
        <Retreat />
        <div class="publishing-title">我的发布</div>
        <div v-if="publishPositions.length === 0">
            <van-empty description="您还没有发布任何职位，赶紧去发布吧！" />
        </div>
        <div v-else>
          <div class="publish-content"
              v-for="item of publishPositions"
              :key="item.id"
              @click="jobDetails(item.id)">
            <div class="position-title">{{item.jobTitle}}</div>
            <div class="position-location">
              <van-icon name="location-o"/>{{item.jobPlace}}
            </div>
            <div class="position-salary">{{item.jobTreat}}/{{item.treatMethod}} {{item.payMethod}}</div>
          </div>
        </div>
    </div>
</template>

<script>
export default {
  name: 'PublishingDetail',
  data () {
    return {
      publishPositions: []
    }
  },
  methods: {
    async init (userId) {
      await this.$http({
        method: 'get',
        url: `/positions/publishPositions/${userId}`,
        params: { fields: 'publishPositions' }
      }).then(res => {
        console.log(res)
        if (res.data.code === 200) {
          this.publishPositions = res.data.data
        } else {
          this.$toast.fail('发生错误，请重试')
        }
      }).catch(err => {
        console.log(err)
        this.$toast.fail('发生错误，请重试')
      })
    },
    jobDetails (id) {
      this.$router.push({
        path: 'jobDetail',
        query: {
          id: id
        }
      })
    }
  },
  created () {
    this.init(this.$route.query.userId)
  }
}
</script>

<style lang="less" scoped>
.publishing-title {
    width: 100%;
    text-align: center;
    font-size: .35rem;
    font-weight: 500;
}
.publish-content {
    position: relative;
    height: 1.5rem;
    border-bottom: 0.01rem solid #fff;
    background: #e9ecef;
    border-radius: .2rem;
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
</style>
