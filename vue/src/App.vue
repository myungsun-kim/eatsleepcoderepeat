<template>
  <!-- <div id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/about">Main</router-link>
  </div> -->
  <el-form enctype="multipart/form-data">
    <el-upload
      ref="upload"
      class="upload-demo"
      action="/api/file/uploadFile"
      :before-upload="beforeUpload"
      :auto-upload="false"
    >
      <template #trigger>
        <el-button size="small" type="primary">select file</el-button>
      </template>
      <el-button
        style="margin-left: 10px"
        size="small"
        type="success"
        @click="submitUpload"
        >upload to server</el-button
      >
      <template #tip>
        <div class="el-upload__tip">
          jpg/png files with a size less than 500kb
        </div>
      </template>
    </el-upload>
  </el-form>

  <div class="bg-color">
    <router-view />
  </div>
</template>
<script lang="ts">
import axios from 'axios';
export default {
  methods: {
    submitUpload() {
      console.log('7');
      this.$refs.upload.submit();
    },
    beforeUpload: function (file) {
      console.log('1');
      console.log(file);
      const token =
        'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0OSIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2MzQ1ODYzNzR9.LHUNEgslWjvUmRdwW59vCnMwEJ64euA9k7-QZ1ffgP70F7XTiU2ilQhcqhfYxw4WL7v3BaTKPZuonG5nR_KI1w';
      let formData = new FormData();
      formData.append('file', file);
      console.log('2');

      const res = axios.post(
        'http://localhost:8080/api/file/uploadFile',
        formData,
        {
          headers: {
            Authorization: 'Bearer ' + token,
          },
        }
      );
      console.log(res);
      console.log('3');
    },
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
  /* border: 1px solid black; */
  box-sizing: border-box;
}
.test-router-border {
  /* border: 5px solid springgreen; */
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
  /* border: 3px solid rgb(0, 183, 255); */
  box-sizing: border-box;
  height: 100%;
}
.height95 {
  /* border: 3px solid rgb(255, 0, 0); */
  box-sizing: border-box;
  height: 95%;
}
.height92 {
  /* border: 3px solid rgb(255, 123, 0); */
  box-sizing: border-box;
  height: 92%;
}
.height90 {
  /* border: 3px solid rgb(166, 255, 0); */
  box-sizing: border-box;
  height: 90%;
}
.height85 {
  /* border: 3px solid rgb(255, 238, 0); */
  box-sizing: border-box;
  height: 85%;
}
.height50 {
  /* border: 3px solid rgb(139, 0, 139); */
  box-sizing: border-box;
  height: 50%;
}
.height40 {
  /* border: 3px solid rgb(255, 0, 0); */
  box-sizing: border-box;
  height: 40%;
}
.height30 {
  /* border: 3px solid rgb(255, 0, 212); */
  box-sizing: border-box;
  height: 30%;
}
.height25 {
  /* border: 3px solid rgb(229, 255, 0); */
  box-sizing: border-box;
  height: 25%;
}
.height20 {
  /* border: 3px solid rgb(61, 143, 65); */
  box-sizing: border-box;
  height: 20%;
}
.height10 {
  /* border: 3px solid rgb(0, 25, 139); */
  box-sizing: border-box;
  height: 10%;
}
.height8 {
  /* border: 3px solid rgb(113, 139, 255); */
  box-sizing: border-box;
  height: 8%;
}
.height5 {
  /* border: 3px solid rgb(0, 255, 234); */
  box-sizing: border-box;
  height: 5%;
}
.height1 {
  /* border: 3px solid rgb(0, 255, 111); */
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

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 15px;
  text-align: center;
  color: #000000;
}
.btn-ghost-round {
  background: #ffffff;
  border: 1px solid #000000;
  box-sizing: border-box;
  border-radius: 30px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  line-height: 16px;
  text-align: center;
  color: #000000;
}

.btn-ghost-round-blue {
  background: #ffffff;
  border: 1px solid #1747c9;
  box-sizing: border-box;
  border-radius: 30px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  line-height: 16px;
  text-align: center;
  color: #1747c9;
}
.btn-ghost-round-red {
  background: #ffffff;
  border: 1px solid #f53030;
  box-sizing: border-box;
  border-radius: 30px;
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  line-height: 16px;
  text-align: center;
  color: #f53030;
}
.btn-ghost-red {
  font-size: 14pt;
  color: #f53030;
  border: 1px solid #f53030;
}
.btn-ghost-red:hover {
  font-size: 14pt;
  color: #ffffff;
  background-color: #f53030;
  border: 1px solid #f53030;
}
.btn-ghost-blue {
  font-size: 14pt;
  color: #1747c9;
  border: 1px solid #1747c9;
}
.btn-ghost-blue:hover {
  font-size: 14pt;
  color: #ffffff;
  background-color: #1747c9;
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
/* 정렬 시작 */
/* 정리하고자 하는 것의 부모에 넣어주면 된다. */
.align-center {
  display: flex;
  justify-content: center;
}
/* 자식들을 세로 간격 동등하게 정렬함 */
.flex-parent {
  display: flex;
  flex-direction: column;
  text-align: left;
  justify-content: space-evenly;
}
/* 자식들이 가로 축 기준으로 동일한 높이에 오도록 */
.flex-container {
  display: flex;
  align-content: center;
}
/* 정렬 끝 */
</style>
