<template>
  <el-row id="main">
    <el-col :span="5"></el-col>
    <el-col :span="14">
      <el-row style="height: 20%"></el-row>
      <el-row id="main-box">
        <el-row style="height: 25%"></el-row>
        <el-row style="height: 13%; font-size: 64px">
          <el-col :span="6"></el-col>
          <el-col :span="12">Eat Sleep Code Repeat</el-col>
          <el-col :span="6"></el-col>
        </el-row>
        <el-row style="height: 7%"></el-row>
        <el-row style="height: 10%; font-size: 48px">
          <el-col :span="6"></el-col>
          <el-col :span="12">
            좋은 사람<br />
            좋은 스터디<br />
            좋은 프로젝트
          </el-col>
          <el-col :span="6"></el-col>
        </el-row>
        <el-row :gutter="0" style="height: 20%"></el-row>
        <el-row :gutter="0" style="height: 5%">
          <el-col :span="10"></el-col>
          <el-col :span="2">
            <button class="sign-btn" @click="goSignIn">로그인</button>
          </el-col>
          <el-col :span="2">
            <button class="sign-btn" @click="goSignUp">회원가입</button>
          </el-col>
          <el-col :span="10"></el-col>
        </el-row>
      </el-row>
    </el-col>
    <el-col :span="5"></el-col>
  </el-row>
</template>

<script>
// import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  components: {},
  setup() {
    // const store = useStore();
    const router = useRouter();

    const goSignIn = function () {
      router.push({ path: '/noheader/signin' });
    };
    const goSignUp = function () {
      router.push({ path: '/noheader/signup' });
    };

    return {
      goSignIn,
      goSignUp,
    };
  },
};
</script>
<style>
#main {
  height: 100%;
  background-image: url('../assets/main.jpg');
  background-repeat: no-repeat;
  background-size: cover;
  box-sizing: border-box;
}
#main-box {
  display: block;
  height: 60%;
  color: white;
  border: 3px solid #ffffff;
}
.sign-btn {
  display: block;
  border: 0;
  outline: 0;
  background-color: transparent;
  color: white;
  text-decoration: underline;
  font-weight: bold;
  font-size: 18px;
  cursor: pointer;
  margin: auto;
}
</style>
