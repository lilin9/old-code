<template>
  <div class="search">
    <navigation />
    <van-cell-group>
        <van-field
          v-model="searchUser"
          placeholder="搜索用户名"
        />
        <van-icon name="search"
          class="search-icon"
          size=".5rem"
          @click="search()"/>
    </van-cell-group>
    <div v-if="searchResult.length === 0">
        <van-empty description="查不到您的内容，请重新查询" />
    </div>
    <div v-else>
      <div class="search-content"
      v-for="item of searchResult"
      :key="item._id">
      <div class="user-img"
        :style="'background-image:url(' + item.avatar_url + ')'">
      </div>
      <div class="user-name">
        <van-icon name="user-o" />
        {{item.name}}
      </div>
      <div class="user-mail">
          <van-icon name="comment-o" />
          123456
      </div>
      <div class="user-detail" @click="userDetails(item._id)">
          <van-icon size=".6rem" name="arrow" />
      </div>
    </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'search',
  data () {
    return {
      searchUser: '',
      searchResult: []
    }
  },
  methods: {
    async search () {
      await this.$http({
        method: 'get',
        url: '/users',
        params: {
          q: this.searchUser
        }
      }).then(res => {
        console.log(res)
        this.searchResult = res.data
      })
    },
    async userDetails (userId) {
      await this.$router.push({
        path: 'userDetail',
        query: {
          userId: userId
        }
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
.search-icon {
  position: absolute;
  right: .2rem;
  top: .2rem;
}
.search-content {
  position: relative;
  width: 90%;
  margin: .1rem auto;
  padding: .2rem;
  height: 1.5rem;
  border-radius: .12rem;
  background: #e9ecef;
  .user-img {
    position: absolute;
    width: 1rem;
    height: 1rem;
    margin-top: 0.25rem;
    background-size: cover;
    background-repeat: no-repeat;
  }
  .user-name {
    position: absolute;
    left: 1.5rem;
    top: .35rem;
  }
  .user-mail {
    position: absolute;
    left: 1.5rem;
    top: 1rem;
  }
  .user-detail {
    position: absolute;
    right: 1rem;
    top: .7rem;
  }
}
</style>
