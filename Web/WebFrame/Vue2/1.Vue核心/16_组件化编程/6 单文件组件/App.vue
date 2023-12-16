<template>
  <div>
      <School/>
      <Student/>
  </div>
</template>

<script>
    //引入组件
    import Student from "./Student.vue"
    import School from "./School.vue"

    export default {
        name: "App",
        components: {Student,School}
    }
</script>

<style>

</style>