<template>
  <div class="home">
    <navigation/>
    <div class="home-search">
      <van-cell-group>
        <van-field
          v-model="searchContent"
          placeholder="搜兼职/城市"
        />
        <van-icon name="search"
                  class="search-icon"
                  size=".5rem"
                  @click="search()"/>
      </van-cell-group>
      <van-tabs animated swipeable>
        <van-tab v-for="index in hotCity" :title="index.city" :key="index.id">
          <div v-for="item of positions" :key="item._id">
            <div v-if="item.jobPlace === index.city"
                 class="job-detail"
                 @click="jobDetails(item.id)">
              <div class="job-top">
                <div class="job-title">{{ item.jobTitle }}</div>
                <div class="job-salary">{{ item.jobTreat }}元/{{ item.treatMethod }} {{ item.payMethod }}</div>
              </div>
              <div class="job-description">描述: {{ item.jobContent }}</div>
              <div class="job-publisher">
                <div class="publisher-img" :style="'background-image:url('+item.publisher.avatar+')'"></div>
                <div class="publisher-name">{{ item.publisher.nickName === null ? item.publisher.nickName : item.publisher.userName }}</div>
              </div>
            </div>
          </div>
        </van-tab>
      </van-tabs>
      <div class="position-publishing" @click="publishing()">
        <van-icon size=".5rem" name="plus"/>
        <div>发布</div>
      </div>
    </div>
  </div>
</template>

<script>
import Navigation from '../components/Navigation.vue'

export default {
  name: 'home',
  data () {
    return {
      searchContent: '',
      // 热门城市
      hotCity: [
        { city: '北京', id: 0 },
        { city: '上海', id: 1 },
        { city: '广州', id: 2 },
        { city: '深圳', id: 3 },
        { city: '杭州', id: 4 },
        { city: '成都', id: 5 },
        { city: '南京', id: 6 }
      ],
      positions: [],
      /* 城市 id */
      cityId: ''
    }
  },
  methods: {
    /* 预加载城市 */
    loadCity () {
      this.$http({
        method: 'get',
        url: '/city/'
      }).then(res => {
        console.log(res)
        this.hotCity = res.data.data
      })
    },
    /* 预加载职位信息 */
    loadPositions () {
      this.$http({
        method: 'get',
        url: '/positions/'
      }).then(res => {
        console.log(res)
        this.positions = res.data.data
      })
    },
    search () {
      if (this.searchContent.length > 0) {
        this.$router.push({
          path: 'searchDetail',
          query: {
            q: this.searchContent
          }
        })
      } else {
        this.$toast.fail('请输入搜索内容')
      }
    },
    jobDetails (id) {
      this.$router.push({
        path: 'jobDetail',
        query: {
          id: id
        }
      })
    },
    publishing () {
      this.$router.push({
        path: 'publishing'
      })
    }
  },

  created () {
    /* 加载城市信息 */
    this.loadCity()
    /* 加载职位信息 */
    this.loadPositions()
  },

  components: {
    Navigation
  }
}
</script>

<style lang="less" scoped>
  .search-icon {
    position: absolute;
    right: .2rem;
    top: .2rem;
  }

  .job-detail {
    margin-bottom: 24px;
    padding: 24px;
    background-color: #fff;
    border-radius: 12px;
    box-shadow: 0 8px 12px #ebedf0;
  }
  .job-title {
      font-size: .35rem;
      font-weight: 500;
    }

  .job-salary {
      color: red;
  }
  .job-description {
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;     //作为弹性伸缩盒子模型显示
    -webkit-box-orient: vertical;   //设置伸缩盒子的子元素排列方式：从上到下垂直排列
    -webkit-line-clamp: 3;    //显示的行数
  }

  .publisher-img {
    width: 1rem;
    height: 1rem;
    margin: .5rem 0;
    background-size: contain;
    background-repeat: no-repeat;
    background-position: top center;
  }

  .publisher-name {
    margin-left: 1.5rem;
    margin-top: -1rem;
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
