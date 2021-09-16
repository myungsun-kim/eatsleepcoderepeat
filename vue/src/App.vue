<template>
  <div class="red-color">
    <!-- <div id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/about">Main</router-link>
  </div> -->

    <!-- <TopNavigationBar /> -->
    <!-- <div class="font-noto-bold">font-noto-bold 안녕하세요 700</div>
    <div class="font-noto-md">font-noto-md 안녕하세요 500</div>
    <div class="font-s-md">font-s-md 안녕하세요</div> -->

    <!-- Main(프로젝트명) 역할 -->

    <router-view />
  </div>
</template>
<script>
export default {};
</script>
<style>
body {
  margin: 0;
}

#app {
  font-family: 'Noto Sans KR', 'S-CoreDream-5Medium', '맑은 고딕', Avenir,
    Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  width: 100vw;
  height: 100vh;
  margin: 0px;
  padding: 0px;
  text-align: center;
  color: #2c3e50;
}

body {
  margin: 0;
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
}
.font-noto-md {
  font-family: 'noto-sans-kr';
  font-weight: 500;
}

.font-s-md {
  font-family: 'S-CoreDream-5Medium';
}

.red-color {
  /* background-color: salmon; */
  width: 100vw;
  height: 100vh;
}
</style>
