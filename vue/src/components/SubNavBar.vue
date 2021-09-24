<template>
  <el-row class="height100 font-noto-md">
    <el-col :span="3"></el-col>
    <el-col :span="11"
      ><el-row class="height100 sub-nav-bar">
        <el-col :span="6"
          ><el-button
            class="sub-nav-btn font-noto-md"
            type="text"
            @click="clickIntroduce"
            >스터디 소개</el-button
          ></el-col
        >
        <el-col :span="1"></el-col
        ><el-col :span="5"
          ><el-button
            class="sub-nav-btn font-noto-md"
            type="text"
            @click="clickNotice"
            >공지사항</el-button
          ></el-col
        >
        <el-col :span="1"></el-col
        ><el-col :span="4"
          ><el-button
            class="sub-nav-btn font-noto-md"
            type="text"
            @click="clickBoard"
            >게시판</el-button
          ></el-col
        >
        <el-col :span="1"></el-col
        ><el-col :span="6"
          ><el-button
            class="sub-nav-btn font-noto-md"
            type="text"
            @click="clickManage"
            >스터디 관리</el-button
          ></el-col
        >
      </el-row></el-col
    >
    <el-col :span="8"> </el-col>
    <el-col :span="1" class="lock-div"
      ><i class="el-icon-lock lock font-noto-md"></i
    ></el-col>
    <el-col :span="1"></el-col>
  </el-row>
</template>

<script>
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const store = useStore();
    const router = useRouter();

    const clickIntroduce = function () {
      router.push({ path: '/subheader/introduce' });
    };

    const clickNotice = function () {
      store.commit('setCategory', 2);
      router.push({ path: '/subheader/notice' });
    };

    const clickBoard = function () {
      store.commit('setCategory', 3);
      router.push({ path: '/subheader/board' });
    };

    const clickManage = function () {
      router.push({ path: '/subheader/manage' });
    };

    return {
      store,
      router,
      clickIntroduce,
      clickNotice,
      clickBoard,
      clickManage,
    };
  },
};
</script>

<style>
.sub-nav-bar {
  display: flex;
  align-items: flex-end;
}
.sub-nav-btn {
  font-size: 28px;
  text-align: center;
  color: black;
  text-decoration: underline;
}
.lock-div {
  display: flex;
  align-items: center;
}
.lock {
  font-size: 26px;
  text-align: center;
  color: black;
  vertical-align: middle;
}
</style>
