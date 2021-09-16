<template>
  <!-- <div id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/about">Main</router-link>
  </div> -->

  <div class="bg-color">
    <router-view />
  </div>
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
  width: 100vw;
  height: 100vh;
  margin: 0px;
  padding: 0px;
  text-align: center;
  color: #2c3e50;
  /* overflow: hidden; */
}

body {
  margin: 0;
  /* overflow: hidden; */
  /* min-height: 100%; */
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

.test-border {
  border: 1px solid black;
  box-sizing: border-box;
}
.test-router-border {
  border: 5px solid springgreen;
  box-sizing: border-box;
  height: 90%;
}

.bg-color {
  background-color: #f2f2f2;
  width: 100%;
  height: 100%;
}
.height100 {
  border: 3px solid rgb(0, 183, 255);
  box-sizing: border-box;
  height: 100%;
}
.height90 {
  border: 3px solid rgb(166, 255, 0);
  box-sizing: border-box;
  height: 90%;
}
.height50 {
  border: 3px solid rgb(139, 0, 139);
  box-sizing: border-box;
  height: 50%;
}
.height30 {
  border: 3px solid rgb(255, 0, 212);
  box-sizing: border-box;
  height: 30%;
}
.height10 {
  border: 3px solid rgb(0, 25, 139);
  box-sizing: border-box;
  height: 10%;
}
.height92 {
  border: 3px solid rgb(255, 123, 0);
  box-sizing: border-box;
  height: 92%;
}
.height8 {
  border: 3px solid rgb(113, 139, 255);
  box-sizing: border-box;
  height: 8%;
}
.height5 {
  border: 3px solid rgb(0, 255, 234);
  box-sizing: border-box;
  height: 5%;
}
</style>
