<template>
  <div class="retreat">
      <van-icon name="arrow-left" @click="retreatControl()"/>
  </div>
</template>

<script>
export default {
  name: 'retreat',
  methods: {
    retreatControl () {
      this.$router.go(-1)
    }
  }
}
</script>

<style lang="less" scoped>
.retreat {
    position: fixed;
    top: .1rem;
    left: 0;
}
.van-icon {
    font-size: .5rem;
}
</style>
