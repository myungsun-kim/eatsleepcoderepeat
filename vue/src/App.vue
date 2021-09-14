<template>
  <!-- <div id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/about">Main</router-link>
  </div> -->

  <!-- <TopNavigationBar /> -->

  <!-- Main(프로젝트명) 역할 -->
  <router-view />
</template>
<script>
// import TopNavigationBar from './views/TopNavigationBar.vue';

export default {
  // components: {
  //   TopNavigationBar,
  // },
};
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
