<template>
  <div class="count">
    <h3>当前的求和是: {{sum}}</h3>
    <h4>当前的求和放大10倍后是: {{bigSum}}</h4>
    <select v-model.number="num">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
    </select>
    <button @click="add()">+</button>
    <button @click="reduce()">-</button>
    <button @click="odd()">当前求和为奇数再加</button>
    <button @click="wait()">等一等再加</button>
  </div>
</template>

<script>
export default {
  name: 'Count',
  data() {
    return {
      num: 1, //用户选择的数字
    }
  },
  methods: {
    add() {
      // this.$store.dispatch("add", this.num)
      this.$store.commit("ADD", this.num)
    },
    reduce() {
      // this.$store.dispatch("reduce", this.num)
      this.$store.commit("REDUCE", this.num)
    },
    odd() {
      this.$store.dispatch("odd", this.num)
    },
    wait() {
      this.$store.dispatch("wait", this.num)
    }
  },
}
</script>

<style scoped>
</style>