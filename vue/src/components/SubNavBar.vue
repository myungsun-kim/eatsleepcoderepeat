<template>
  <el-row class="height100 font-noto-md">
    <el-col :span="3"></el-col>
    <el-col :span="11">
      {{ store.state.category }} 확인용 나중에 category 알고리즘 다 확인해야함
      <el-row class="height100 sub-nav-bar">
        <el-col :span="6"
          ><el-button
            v-if="store.state.category == 1"
            class="sub-nav-btn font-noto-md"
            type="text"
            @click="clickIntroduceStudy"
            >스터디 소개</el-button
          ><el-button
            v-else-if="store.state.category == 2"
            class="sub-nav-btn font-noto-md"
            type="text"
            @click="clickIntroduceProject"
            >프로젝트 소개</el-button
          ><el-button
            v-else-if="store.state.category == 3"
            class="sub-nav-btn font-noto-md"
            type="text"
            @click="clickIntroduceClub"
            >클럽 소개</el-button
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
            v-if="store.state.category == 1"
            class="sub-nav-btn font-noto-md"
            type="text"
            @click="clickManageStudy"
            >스터디 관리 </el-button
          ><el-button
            v-else-if="store.state.category == 2"
            class="sub-nav-btn font-noto-md"
            type="text"
            @click="clickManageProject"
            >프로젝트 관리 </el-button
          ><el-button
            v-if="store.state.category == 3"
            class="sub-nav-btn font-noto-md"
            type="text"
            @click="clickManageClub"
            >클럽 관리
          </el-button></el-col
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
import { useStore, computed, watch } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const store = useStore();
    const router = useRouter();
    // store.getters['categoryGetter'];
    // const category = computed(() => store.getters['categoryGetter']);
    // const category = 1;
    // console.log('카테고리!@#!@');
    // console.log(category);
    // watch(category, () => {
    //   store.dispatch('study/introduce', studyId.value);
    // });

    const clickIntroduceStudy = function () {
      router.push({ path: '/subheader/study/introduce' });
    };

    const clickIntroduceProject = function () {
      router.push({ path: '/subheader/project/introduce' });
    };

    const clickIntroduceClub = function () {
      router.push({ path: '/subheader/club/introduce' });
    };

    const clickNotice = function () {
      router.push({ path: '/subheader/notice' });
    };

    const clickBoard = function () {
      router.push({ path: '/subheader/board' });
    };

    const clickManageStudy = function () {
      router.push({ path: '/subheader/manage' });
    };

    const clickManageProject = function () {
      router.push({ path: '/subheader/manage' });
    };
    const clickManageClub = function () {
      router.push({ path: '/subheader/manage' });
    };

    return {
      store,
      router,
      // category,
      clickIntroduceStudy,
      clickIntroduceProject,
      clickIntroduceClub,
      clickNotice,
      clickBoard,
      clickManageStudy,
      clickManageProject,
      clickManageClub,
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
