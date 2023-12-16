<template>
  <div class="count">
    <h3>当前的求和是: {{sum}}</h3>
    <h4>当前的求和放大10倍后是: {{bigSum}}</h4>
    <h4>我在{{school}}学习{{subject}}</h4>
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
import {mapState, mapGetters} from "vuex"

export default {
  name: 'Count',
  data() {
    return {
      num: 1, //用户选择的数字
    }
  },
  computed: {
    //由开发者自己写的计算属性
/*     sum() {
      return $store.state.sum
    },
    school() {
      return $store.state.school
    },
    subject() {
      return $store.state.subject
    }, */

    //借助mapState生成计算属性，从state中读取数据（对象写法）
    // ...mapState({sum:'sum', school:'school', subject:'subject'})

    //借助mapState生成计算属性，从state中读取数据（数组写法）
    ...mapState(['sum','school','subject']),

    /* ================================================================== */
    /* bigSum() {
      return $store.getters.bigSum
    } */

    // 借助mapState生成计算属性，从getters中读取数据（对象写法）
    // ...mapGetters({bigSum:'bigSum'})

    //借助mapState生成计算属性，从state中读取数据（数组写法）
    ...mapGetters(['bigSum']),
  },
  mounted() {
    mapState({sum:'sum', school:'school', subject:'subject'});
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