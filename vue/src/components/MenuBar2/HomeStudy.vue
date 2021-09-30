<template>
  <el-row class="height5">
    <el-col :span="21" class="test-border">
      home - study, project, club 5% 여백
    </el-col>
    <el-col :span="3" class="test-border">
      <el-button class="btn-1747C9 font-noto-bold" @click="goCreate"
        >스터디 생성</el-button
      >
    </el-col>
  </el-row>

  <el-row class="height5">
    <el-col :span="22" :offset="2" class="test-border">
      <p class="itemlist-title-left">내 스터디 목록</p>
    </el-col>
  </el-row>

  <el-row class="height25">
    <el-col
      :span="4"
      :offset="1"
      class="test-border"
      v-for="(item, index) in tempArray"
      :key="index"
      >{{ item }}</el-col
    >
    {{ totalStudyList }}@@
    <el-col
      :span="4"
      :offset="1"
      class="test-border"
      v-for="(item, index) in totalStudyList"
      :key="index"
    >
      @@{{ item[index] }}@@
    </el-col>
    <el-col :span="1" class="test-border"></el-col>
    <el-col :span="4" class="test-border">정처기</el-col>
    <el-col :span="1" class="test-border"></el-col>
    <el-col :span="4" class="test-border">웹스터디</el-col>
    <el-col :span="3" class="test-border"></el-col>
  </el-row>

  <el-row class="height5">
    <el-col :span="24" class="test-border">
      <a href="">더보기</a> <a href="">접기</a>
    </el-col>
  </el-row>

  <el-row class="height5">
    <el-col :span="22" :offset="2" class="test-border">
      <p class="itemlist-title-left">추천 스터디 목록</p>
    </el-col>
  </el-row>

  <!-- 아이템 목록 시작 -->
  <el-row class="height25">
    <el-col :span="2" class="test-border"></el-col>

    <!-- item 한개 -->
    <el-col :span="4" class="item item-border" @click="goIntroduce">
      <el-row class="height40 item-img"></el-row>
      <el-row class="height10 item-head-title">백준 알고 스터디 하실 분</el-row>
      <el-row class="height10 item-content">기술 스택: Java, Kotlin</el-row>
      <el-row class="height10"></el-row>
      <el-row class="height10 item-content">
        <el-col :span="12" class="test-border left-content">지역: 무관</el-col>
        <el-col :span="12" class="test-border right-content">자물쇠</el-col>
      </el-row>
      <el-row class="height10 item-content">
        <el-col :span="12" class="test-border left-content">기간: 14주</el-col>
        <el-col :span="12" class="test-border right-content">인원: 4/10</el-col>
      </el-row>
      <el-row class="height10">
        <el-col :span="12" class="test-border item-small-content left-content"
          >작성일: 21.09.01</el-col
        >
        <el-col :span="12" class="test-border right-content">
          <div class="item-state-badge-blue item-small-content">모집 중</div>
        </el-col>
      </el-row>
    </el-col>
    <el-col :span="1" class="test-border"></el-col>
    <el-col :span="4" class="test-border" @click="goIntroduce">
      <el-row class="height40 item-img"></el-row>
      <el-row class="height10 item-head-title">단기간 면접 대비 CS 공부</el-row>
      <el-row class="height10 item-content">기술 스택: Java, Kotlin</el-row>
      <el-row class="height10"></el-row>
      <el-row class="height10 item-content">
        <el-col :span="12" class="test-border left-content">지역: 무관</el-col>
        <el-col :span="12" class="test-border right-content">자물쇠</el-col>
      </el-row>
      <el-row class="height10 item-content">
        <el-col :span="12" class="test-border left-content">기간: 14주</el-col>
        <el-col :span="12" class="test-border right-content">인원: 4/10</el-col>
      </el-row>
      <el-row class="height10">
        <el-col :span="12" class="test-border item-small-content left-content"
          >작성일: 21.09.01</el-col
        >
        <el-col :span="12" class="test-border right-content">
          <div class="item-state-badge-green item-small-content">참여 가능</div>
          <div class="item-state-badge-green item-small-content">진행 중</div>
        </el-col>
      </el-row>
    </el-col>
    <el-col :span="1" class="test-border"></el-col>
    <el-col :span="4" class="test-border">정처기</el-col>
    <el-col :span="1" class="test-border"></el-col>
    <el-col :span="4" class="test-border">웹스터디</el-col>
    <el-col :span="3" class="test-border"></el-col>
  </el-row>
  <!-- 아이템 목록 끝 -->

  <el-row class="height5">
    <el-col :span="24" class="test-border">
      <a href="">더보기</a> <a href="">접기</a>
    </el-col>
  </el-row>

  <el-row class="height5">
    <el-col :span="22" :offset="2" class="test-border">
      <p class="itemlist-title-left">전체 스터디 목록</p>
    </el-col>
  </el-row>

  <!-- 아이템 목록 시작 -->
  <el-row class="height25">
    <el-col :span="2" class="test-border"></el-col>

    <!-- item 한개 -->
    <el-col :span="4" class="test-border">
      <el-row class="height40 item-img"></el-row>
      <el-row class="height10 item-head-title">백준 알고 스터디 하실 분</el-row>
      <el-row class="height10 item-content">기술 스택: Java, Kotlin</el-row>
      <el-row class="height10"></el-row>
      <el-row class="height10 item-content">
        <el-col :span="12" class="test-border left-content">지역: 무관</el-col>
        <el-col :span="12" class="test-border right-content">
          <i class="el-icon-lock icon-size"></i>
        </el-col>
      </el-row>
      <el-row class="height10 item-content">
        <el-col :span="12" class="test-border left-content">기간: 14주</el-col>
        <el-col :span="12" class="test-border right-content">인원: 4/10</el-col>
      </el-row>
      <el-row class="height10">
        <el-col :span="12" class="test-border item-small-content left-content"
          >작성일: 21.09.01</el-col
        >
        <el-col :span="12" class="test-border right-content">
          <div class="item-state-badge-blue item-small-content">모집 중</div>
        </el-col>
      </el-row>
    </el-col>
    <el-col :span="1" class="test-border"></el-col>
    <el-col :span="4" class="test-border">
      <el-row class="height40 item-img"></el-row>
      <el-row class="height10 item-head-title">단기간 면접 대비 CS 공부</el-row>
      <el-row class="height10 item-content">기술 스택: Java, Kotlin</el-row>
      <el-row class="height10"></el-row>
      <el-row class="height10 item-content">
        <el-col :span="12" class="test-border left-content">지역: 무관</el-col>
        <el-col :span="12" class="test-border right-content">자물쇠</el-col>
      </el-row>
      <el-row class="height10 item-content">
        <el-col :span="12" class="test-border left-content">기간: 14주</el-col>
        <el-col :span="12" class="test-border right-content">인원: 4/10</el-col>
      </el-row>
      <el-row class="height10">
        <el-col :span="12" class="test-border item-small-content left-content"
          >작성일: 21.09.01</el-col
        >
        <el-col :span="12" class="test-border right-content">
          <div class="item-state-badge-green item-small-content">참여 가능</div>
          <div class="item-state-badge-green item-small-content">진행 중</div>
        </el-col>
      </el-row>
    </el-col>
    <el-col :span="1" class="test-border"></el-col>
    <el-col :span="4" class="test-border">정처기</el-col>
    <el-col :span="1" class="test-border"></el-col>
    <el-col :span="4" class="test-border">웹스터디</el-col>
    <el-col :span="3" class="test-border"></el-col>
  </el-row>
  <!-- 아이템 목록 끝 -->

  <el-row class="height5">
    <el-col :span="24" class="test-border">
      <a href="">더보기</a> <a href="">접기</a>
    </el-col>
  </el-row>
</template>
<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const store = useStore();
    const router = useRouter();

    console.log('SET UP');

    const res = store.dispatch('study/getTotalStudyList');
    res.then((res) => {
      store.state.totalStudyList = res.data;
    });
    const totalStudyList = computed(
      () => store.getters['study/totalStudyGetter']
    );

    const goCreate = function () {
      router.push({ path: '/nosubheader/study/create' });
    };

    const goIntroduce = function () {
      router.push({ path: '/subheader/study/introduce' });
    };

    const tempArray = ['a', 'BBB'];
    return {
      store,
      router,
      totalStudyList,
      goCreate,
      goIntroduce,
      tempArray,
    };
  },
};
</script>
<style>
.item-img {
  background-image: url('../../assets/Item/Cat.png');
  background-repeat: no-repeat;
  background-size: cover;
  box-sizing: border-box;
  border-radius: 5px 5px 0px 0px;
  left: 0px;
  top: 0px;

  padding: 0px;
}

.item-head-title {
  font-size: 16pt;
  font-weight: bold;
  padding: 0 3px 0 3px;
}
.item-content {
  font-size: 12pt;
  font-weight: normal;
  padding: 0 3px 0 3px;
}
.item-small-content {
  font-size: 10pt;
  font-weight: normal;
  padding: 0 3px 0 3px;
}

.left-content {
  text-align: left;
  padding: 0 3px 0 3px;
}
.right-content {
  text-align: right;
  padding: 0 3px 0 3px;
}

.item-state-badge-blue {
  display: inline-block;
  padding: 0 3px 0 3px;
  margin: 0 2px 0 2px;
  color: white;
  background: #4361ff;
  border-radius: 3px;
  /* vertical-align: middle; */
}
.item-state-badge-green {
  display: inline-block;
  padding: 0 3px 0 3px;
  margin: 0 2px 0 2px;
  color: white;
  background: #00bf00;
  border-radius: 3px;
  /* vertical-align: middle; */
}
.item-state-badge-red {
  display: inline-block;
  padding: 0 3px 0 3px;
  margin: 0 2px 0 2px;
  color: white;
  background: #bf0000;
  border-radius: 3px;
  /* vertical-align: middle; */
}
.item-state-badge-black {
  display: inline-block;
  padding: 0 3px 0 3px;
  margin: 0 2px 0 2px;
  color: white;
  background: #000000;
  border-radius: 3px;
  /* vertical-align: middle; */
}

/* icon 크기는 font-size로 작성함 */
.icon-size {
  font-size: 20px;
  font-weight: bold;
}

.item {
  cursor: pointer;
}
</style>
