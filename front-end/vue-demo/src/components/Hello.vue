<template>
  <div>
    <h1>你好，Hello，{{ name }}</h1>
    <el-button type="warning">警告按钮</el-button>
    <el-radio v-model="radio" label="1">备选项</el-radio>
    <el-radio v-model="radio" label="2">备选项</el-radio>
  </div>
</template>
<script>
export default {
  data() {
    return {
      name: "张三",
      radio: 1,
    };
  },
};
</script>
<style>
</style>