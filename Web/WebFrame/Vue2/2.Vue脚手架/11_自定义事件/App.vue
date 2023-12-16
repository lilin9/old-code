<template>
  <div>
    <h1>{{ msg }}</h1>
    <h1>name of student is 【{{ studentName }}】</h1>
    <!-- 通过父组件给子组件传递函数类型的props实现：子组件给父组件传递数据 -->
    <School :getSchoolName="getSchoolName" />

    <hr />

    <!-- 通过父组件给子组件绑定了一个自定义事件实现：子组件给父组件传递数据 -->
    <Student v-on:love="getStudentName" @demo="test" />
    <!-- <Student v-on:love.once="getStudentName" /> -->
    <!-- <Student ref="student" /> -->
  </div>
</template>

<script>
//引入 School.vue文件
import Student from "./components/Student.vue";
import School from "./components/School.vue";

export default {
  name: "App",
  data() {
    return {
      msg: "Welcome To Learning Vue",
      studentName: '',
    };
  },
  components: {
    Student,
    School,
  },
  methods: {
    getSchoolName(name) {
      console.log("name of School --> ", name);
    },
    getStudentName(name, ...params) {
      console.log("name of Student --> ", name, params);
      this.studentName = name;
    },
    test() {
      console.log("这是一个 student 的测试方法");
    }
  },
  mounted() {
    // this.$refs.student.$on("love", this.getStudentName);
    // this.$refs.student.$once("love", this.getStudentName);
  },
};
</script>

<style>
</style>