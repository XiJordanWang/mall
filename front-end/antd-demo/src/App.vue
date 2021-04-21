<template>
  <div id="app">
    <h1>{{ name }}</h1>
    <img alt="Vue logo" src="./assets/logo.png" />
    <router-link to="main"
      ><a-button type="primary">Button></a-button></router-link
    >
  </div>
</template>

<script>
import Main from "./components/Main.vue";

export default {
  name: "app",
  data() {
    return {
      name: "朋友",
      main: Main,
    };
  },
  beforeCreate() {
    console.log("创建前");
    this.axios
      .get("/api/product/category/treeList")
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  },
  components: {},
  methods: {},
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
