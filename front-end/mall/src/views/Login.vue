<template>
  <div>
    <h1>用户登录</h1>
    <a-input style="width: 200px" placeholder="用户名" /><br />
    <a-input-password style="width: 200px" placeholder="密码" /><br />
    <a-button @click="buttonClick" type="primary"> 登录 </a-button>
  </div>
</template>

<script>
export default {
  methods: {
    buttonClick() {
      this.$router.push("/main");
    },
  },
};
</script>
