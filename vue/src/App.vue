<template>
  <!-- <div id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/about">Main</router-link>
  </div> -->

  <div>
    <TeleportModal></TeleportModal>
  </div>

  <div class="bg-color">
    <router-view />
  </div>
</template>
<script>
import TeleportModal from '../src/components/Modal/TeleportModal.vue';

export default {
  components: {
    TeleportModal,
  },
  data() {
    return {
      modalOpen: false,
    };
  },
};
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
  /* overflow: hidden; */
}

body {
  margin: 0;
  /* 스크롤바 생김->그만큼 x가 안보임->좌우스크롤 발생 << 이를 방지 */
  overflow-x: hidden;
  overflow-y: scroll;
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
  /* 적용 안되면 이걸로 바꿔보기 -> Noto Sans KR  */
  font-family: 'noto-sans-kr';
  font-weight: 500;
}

.font-s-md {
  font-family: 'S-CoreDream-5Medium';
}

.itemlist-title-left {
  font-size: 36px;
  text-align: left;
  margin: 0;
}

.font-20 {
  font-size: 20px;
  margin: 0;
}
.font-14 {
  font-size: 14px;
  margin: 0;
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
.vh100 {
  height: 100vh;
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
.height95 {
  border: 3px solid rgb(255, 0, 0);
  box-sizing: border-box;
  height: 95%;
}
.height92 {
  border: 3px solid rgb(255, 123, 0);
  box-sizing: border-box;
  height: 92%;
}
.height90 {
  border: 3px solid rgb(166, 255, 0);
  box-sizing: border-box;
  height: 90%;
}
.height85 {
  border: 3px solid rgb(255, 238, 0);
  box-sizing: border-box;
  height: 85%;
}
.height50 {
  border: 3px solid rgb(139, 0, 139);
  box-sizing: border-box;
  height: 50%;
}
.height40 {
  border: 3px solid rgb(255, 0, 0);
  box-sizing: border-box;
  height: 40%;
}
.height30 {
  border: 3px solid rgb(255, 0, 212);
  box-sizing: border-box;
  height: 30%;
}
.height25 {
  border: 3px solid rgb(229, 255, 0);
  box-sizing: border-box;
  height: 25%;
}
.height20 {
  border: 3px solid rgb(61, 143, 65);
  box-sizing: border-box;
  height: 20%;
}
.height10 {
  border: 3px solid rgb(0, 25, 139);
  box-sizing: border-box;
  height: 10%;
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
.height1 {
  border: 3px solid rgb(0, 255, 111);
  box-sizing: border-box;
  height: 1%;
}

/* buttons */
/*
  color: 글자 색
  background-color: 버튼 배경 색
*/
.btn-1747C9 {
  color: white;
  background-color: #1747c9;
}
.btn-ghost {
  color: black;
  border: 1px solid black;
  background-color: #f2f2f2;
}
.btn-ghost-red {
  font-size: 14pt;
  color: #f53030;
  border: 1px solid #f53030;
}
.btn-ghost-blue {
  font-size: 14pt;
  color: #1747c9;
  border: 1px solid #1747c9;
}
.btn-create {
  width: 76px;
  height: 32px;
  color: white;
  background-color: #1747c9;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 16px;
}
.btn-cancel {
  width: 76px;
  height: 32px;
  color: #f53030;
  border: 1px solid #f53030;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 16px;
}

/* btns 끝 */
</style>
