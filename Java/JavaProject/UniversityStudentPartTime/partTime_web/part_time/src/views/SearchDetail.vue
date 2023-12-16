<template>
  <div class="search-detail">
      <retreat />
      <div class="search-top">{{$route.query.q}}</div>
      <div v-if="searchResult.length === 0">
          <van-empty description="查不到您的内容，请重新查询" />
      </div>
      <div v-else>
        <div class="job-detail"
        v-for="item of searchResult"
        :key="item.id"
        @click="jobDetails(item.id)">
        <div class="job-top">
            <div class="job-title">{{item.jobTitle}}</div>
            <div class="job-salary">{{item.jobTreat}}/{{item.treatMethod}} {{item.payMethod}}</div>
        </div>
        <div class="job-description">描述: {{item.jobContent}}</div>
        <div class="job-publisher">
            <div class="publisher-img" :style="'background-image:url('+item.publisher.avatar+')'"></div>
            <div class="publisher-name">{{item.publisher.nickName === null? item.publisher.nickName : item.publisher.userName}}</div>
        </div>
      </div>
      </div>
  </div>
</template>

<script>
import Retreat from '../components/Retreat.vue'
export default {
  name: 'searchDetail',
  data () {
    return {
      searchResult: []
    }
  },
  methods: {
    async init (searchContent) {
      await this.$http({
        method: 'get',
        url: '/positions/search',
        params: { searchContent: searchContent }
      }).then(res => {
        console.log(res.data)
        this.searchResult = res.data.data
      })
    },
    async jobDetails (id) {
      this.$router.push({
        path: 'jobDetail',
        query: {
          id: id
        }
      })
    }
  },
  components: {
    Retreat
  },
  created () {
    this.init(this.$route.query.q)
  },
  watch: {
    '$route.query.q': function (newval, oldval) {
      if (newval !== undefined) {
        this.init(newval)
      }
    }
  }
}
</script>

<style lang="less" scoped>
.search-top {
    text-align-last: center;
    margin-top: .1rem;
    font-size: .35rem;
    font-weight: 500;
}
.job-detail {
  margin-bottom: 24px;
  padding: 24px;
  background-color: #fff;
  border-radius: 12px;
  box-shadow: 0 8px 12px #ebedf0;
  .job-title {
    font-size: .35rem;
    font-weight: 500;
  }
  .job-salary {
    color: red;
  }
  .publisher-img {
    width:1rem;
    height:1rem;
    margin: .5rem 0;
    background-size:cover;
    background-repeat: no-repeat;
    background-position: top center ;
  }
  .publisher-name {
    margin-left: 1.5rem;
    margin-top: -1rem;
  }
}
.position-publishing {
  position: fixed;
  top: 5rem;
  right: .5rem;
}
.van-icon-plus::before {
  color: red;
  border: 1px solid red;
  border-radius: .5rem;
}
</style>
