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
    <el-col :span="2" class="test-border"></el-col>
    <!-- Item 리스트 1개씩 v-for로 -->
    <el-col
      :span="4"
      :offset="1"
      class="test-border item-border"
      v-for="(item, index) in myStudyList"
      :key="index"
    >
      <el-row class="height40 item-img"></el-row>
      <el-row class="height10 item-head-title">{{ item.name }}</el-row>
      <el-row class="height10 item-content"
        >기술 스택:&nbsp;
        <div v-for="(tech, index) in item.techList" :key="index">
          {{ tech }} &nbsp;
        </div>
      </el-row>
      <el-row class="height10"></el-row>
      <el-row class="height10 item-content">
        <el-col :span="12" class="test-border left-content"
          >지역: {{ item.city }}</el-col
        >
        <el-col :span="12" class="test-border right-content">
          <i v-if="item.isPublic == false" class="el-icon-lock icon-size"></i>
        </el-col>
      </el-row>
      <el-row class="height10 item-content">
        <el-col :span="12" class="test-border left-content"
          >기간: {{ item.period }}주</el-col
        >
        <el-col :span="12" class="test-border right-content"
          >인원: {{ item.memberCount }}/{{ item.maxCount }}</el-col
        >
      </el-row>
      <el-row class="height10">
        <el-col :span="12" class="test-border item-small-content left-content"
          >작성일: {{ item.modifyDate.substr(2, 8) }}</el-col
        >
        <el-col :span="12" class="test-border right-content">
          <div
            v-if="item.status == '종료'"
            class="item-state-badge-blue item-small-content"
          >
            {{ item.status }}
          </div>
          <div v-else class="item-state-badge-blue item-small-content">
            {{ item.status }}중
          </div>
          <div
            v-if="item.isParticipate == true && item.status == '진행'"
            class="item-state-badge-blue item-small-content"
          >
            참여 가능
          </div>
        </el-col>
      </el-row>
    </el-col>
    <el-col :span="2" class="test-border"></el-col>
  </el-row>

  <el-row class="height5">
    <el-col :span="24" class="test-border">
      <a href="">더보기</a>
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
    <!-- Item 리스트 1개씩 v-for로 -->
    <el-col
      :span="4"
      :offset="1"
      class="test-border item-border"
      v-for="(item, index) in totalStudyList"
      :key="index"
    >
      <el-row class="height40 item-img"></el-row>
      <el-row class="height10 item-head-title">{{ item.name }}</el-row>
      <el-row class="height10 item-content"
        >기술 스택: {{ item.techList[0] }}, {{ item.techList[1] }}</el-row
      >
      <el-row class="height10"></el-row>
      <el-row class="height10 item-content">
        <el-col :span="12" class="test-border left-content"
          >지역: {{ item.city }}</el-col
        >
        <el-col :span="12" class="test-border right-content">
          <i v-if="item.isPublic == false" class="el-icon-lock icon-size"></i>
        </el-col>
      </el-row>
      <el-row class="height10 item-content">
        <el-col :span="12" class="test-border left-content"
          >기간: {{ item.period }}주</el-col
        >
        <el-col :span="12" class="test-border right-content"
          >인원: {{ item.memberCount }}/{{ item.maxCount }}</el-col
        >
      </el-row>
      <el-row class="height10">
        <el-col :span="12" class="test-border item-small-content left-content"
          >작성일: {{ item.modifyDate.substr(2, 8) }}</el-col
        >
        <el-col :span="12" class="test-border right-content">
          <div
            v-if="item.status == '종료'"
            class="item-state-badge-blue item-small-content"
          >
            {{ item.status }}
          </div>
          <div v-else class="item-state-badge-blue item-small-content">
            {{ item.status }}중
          </div>
          <div
            v-if="item.isParticipate == true && item.status == '진행'"
            class="item-state-badge-blue item-small-content"
          >
            참여 가능
          </div>
        </el-col>
      </el-row>
    </el-col>
    <el-col :span="2" class="test-border"></el-col>
  </el-row>
  <!-- 아이템 목록 끝 -->

  <el-row class="height5">
    <el-col :span="24" class="test-border">
      <a href="">더보기</a>
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
    <!-- Item 리스트 1개씩 v-for로 -->
    <el-col
      :span="4"
      :offset="1"
      class="test-border item-border"
      v-for="(item, index) in totalStudyList"
      :key="index"
    >
      <el-row class="height40 item-img"></el-row>
      <el-row class="height10 item-head-title">{{ item.name }}</el-row>
      <el-row class="height10 item-content"
        >기술 스택:&nbsp;
        <div v-for="(tech, index) in item.techList" :key="index">
          {{ tech }} &nbsp;
        </div>
      </el-row>
      <el-row class="height10"></el-row>
      <el-row class="height10 item-content">
        <el-col :span="12" class="test-border left-content"
          >지역: {{ item.city }}</el-col
        >
        <el-col :span="12" class="test-border right-content">
          <i v-if="item.isPublic == false" class="el-icon-lock icon-size"></i>
        </el-col>
      </el-row>
      <el-row class="height10 item-content">
        <el-col :span="12" class="test-border left-content"
          >기간: {{ item.period }}주</el-col
        >
        <el-col :span="12" class="test-border right-content"
          >인원: {{ item.memberCount }}/{{ item.maxCount }}</el-col
        >
      </el-row>
      <el-row class="height10">
        <el-col :span="12" class="test-border item-small-content left-content"
          >작성일: {{ item.modifyDate.substr(2, 8) }}</el-col
        >
        <el-col :span="12" class="test-border right-content">
          <div
            v-if="item.status == '종료'"
            class="item-state-badge-blue item-small-content"
          >
            {{ item.status }}
          </div>
          <div v-else class="item-state-badge-blue item-small-content">
            {{ item.status }}중
          </div>
          <div
            v-if="item.isParticipate == true && item.status == '진행'"
            class="item-state-badge-blue item-small-content"
          >
            참여 가능
          </div>
        </el-col>
      </el-row>
    </el-col>
    <el-col :span="2" class="test-border"></el-col>
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

    const res = store.dispatch('study/getTotalStudyList');
    res.then((res) => {
      // store.state.totalStudyList = res.data;
    });
    const totalStudyList = computed(
      () => store.getters['study/totalStudyGetter']
    );

    // 내가 속한 스터디 목록 받을 것임
    const myRes = store.dispatch('member/readMyPage');
    myRes.then((myRes) => {
      // store.state.myStudyList = myRes.data;
    });
    const myStudyList = computed(
      () => store.getters['member/myStudyListGetter']
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
      myStudyList,
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
