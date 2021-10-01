<template>
  <el-row class="height100 top-nav-bar">
    <el-col :span="2"></el-col>
    <el-col :span="5"
      ><el-row>
        <el-col :span="1"></el-col>
        <el-col :span="4"
          ><el-button
            class="top-nav-btn font-s-md"
            type="text"
            @click="clickStudy"
            >스터디</el-button
          ></el-col
        ><el-col :span="4"></el-col
        ><el-col :span="5"
          ><el-button
            class="top-nav-btn font-s-md"
            type="text"
            @click="clickProject"
            >프로젝트</el-button
          ></el-col
        ><el-col :span="4"></el-col
        ><el-col :span="3"
          ><el-button
            class="top-nav-btn font-s-md"
            type="text"
            @click="clickClub"
            >클럽</el-button
          ></el-col
        ><el-col :span="3"></el-col>
      </el-row>
    </el-col>

    <el-col :span="2"></el-col>
    <el-col :span="5"
      ><el-row class="height100 logo-div"
        ><img
          src="../assets/Item/logo.png"
          class="logo"
          @click="clickMain" /></el-row></el-col
    ><el-col :span="3"></el-col
    ><el-col :span="6"
      ><el-row class="height100">
        <el-col :span="2"> <i class="el-icon-search"></i></el-col>
        <el-col :span="3"></el-col
        ><el-col :span="3"
          ><el-button
            class="top-nav-btn font-s-md"
            type="text"
            @click="clickChat"
            >채팅</el-button
          ></el-col
        ><el-col :span="3"></el-col
        ><el-col :span="6"
          ><el-button
            class="top-nav-btn font-s-md"
            type="text"
            @click="clickMyPage"
            >마이페이지</el-button
          ></el-col
        ><el-col :span="2"></el-col
        ><el-col :span="5">
          <el-button
            v-if="!token"
            class="top-nav-btn font-s-md"
            type="text"
            @click="clickLogIn"
            >로그인</el-button
          >
          <el-button
            v-if="token"
            class="top-nav-btn font-s-md"
            type="text"
            @click="clickLogOut"
            >로그아웃</el-button
          >
        </el-col>
      </el-row> </el-col
    ><el-col :span="1"></el-col>
  </el-row>
</template>

<script>
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const store = useStore();
    const router = useRouter();

    const clickStudy = function () {
      store.commit('setCategory', 1);
      router.push({ path: '/nosubheader/study/home' });
    };

    const clickProject = function () {
      store.commit('setCategory', 2);
      router.push({ path: '/nosubheader/project/home' });
    };

    const clickClub = function () {
      store.commit('setCategory', 3);
      router.push({ path: '/nosubheader/club/home' });
      // console.log(store.state.category);
    };

    const clickMain = function () {
      router.push({ path: '/' });
    };

    const clickChat = function () {
      router.push({ path: '/nosubheader/chat' });
    };

    const clickMyPage = function () {
      router.push({ path: '/nosubheader/readmypage' });
    };
    const clickLogIn = function () {
      router.push({ path: '/noheader/signin' });
    };
    const clickLogOut = function () {
      localStorage.removeItem('accessToken');
      window.location = '/';
    };
    const token = localStorage.getItem('accessToken');
    return {
      store,
      router,
      clickStudy,
      clickProject,
      clickClub,
      clickMain,
      clickChat,
      clickMyPage,
      clickLogIn,
      clickLogOut,
      token,
    };
  },
};
</script>

<style>
.top-nav-bar {
  background-color: #24305e;
  /* vertical-align: middle; */
  display: flex;
  align-items: center;
}
.logo-div {
  display: table-cell;
  vertical-align: middle;
}
.logo {
  width: 80%;
  object-fit: cover;
  vertical-align: middle;
}
.top-nav-btn {
  font-size: 18px;
  text-align: center;
  color: white;
}
</style>
