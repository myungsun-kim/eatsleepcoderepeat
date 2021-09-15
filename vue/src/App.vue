<template>
  <!-- <div id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/about">Main</router-link>
  </div> -->

  <!-- <TopNavigationBar /> -->
  <div class="font-noto-bold">font-noto-bold 안녕하세요 700</div>
  <div class="font-noto-md">font-noto-md 안녕하세요 500</div>
  <div class="font-s-md">font-s-md 안녕하세요</div>

  <el-row :gutter="20">
    <el-col :span="12" :offset="0" class="simple-bg">test1</el-col>
    <el-col :span="12" :offset="0" class="simple-bg">test2</el-col>
  </el-row>

  <!-- Main(프로젝트명) 역할 -->

  <router-view />
</template>
<script>
export default {};
</script>
<style>
#app {
  font-family: 'Noto Sans KR', 'S-CoreDream-5Medium', '맑은 고딕', Avenir,
    Helvetica, Arial, sans-serif;
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

/* font cdn:s-core dream */
@font-face {
  font-family: 'S-CoreDream-5Medium';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-5Medium.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}

.font-noto-bold {
  font-family: 'noto-sans-kr';
  font-weight: 700;
  font-size: 60pt;
}
.font-noto-md {
  font-family: 'noto-sans-kr';
  font-weight: 500;
  font-size: 60pt;
}

.font-s-md {
  font-family: 'S-CoreDream-5Medium';
  font-size: 60pt;
}

.simple-bg {
  background-color: red;
  border: 1px solid;
  border-color: blue;
}
</style>
