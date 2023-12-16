<template>
  <div class="school">
    <h1>{{ msg }}</h1>
    <h2>学生名字：{{ name }}</h2>
    <h2>学生性别：{{ sex }}</h2>
    <h2>学生年龄：{{ age + 1 }}</h2>
  </div>
</template>

<script>
export default {
  name: "Student",
  data() {
    return {
      msg: "I am a Loli",
    };
  },
  //简单方式接收
  // props: ["name", "sex", "age"],

  //限制类型式接收
  /* props: {
    name: String,
    sex: Number,
    age: String
  } */

//接收数据时，进行类型限制和默认值限制以及必要性限制
  props: {
    name:{
      type:String,
      //name必须有值
      required:true,
    },
    sex:{
      type:String,
      //name必须有值
      required:true,
    },
    age:{
      type:Number,
      //设置默认值
      default:1,
    },
  }
};
</script>