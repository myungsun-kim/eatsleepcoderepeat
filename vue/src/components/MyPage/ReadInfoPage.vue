<template>
  <el-row class="height5">
    <el-col :span="1"></el-col>
    <el-col :span="21" class="test-border title">
      마이페이지
      <hr style="border: 1px color= black" width="100%"
    /></el-col>
    <el-col :span="2"></el-col>
  </el-row>
  <el-row class="height5"> </el-row>
  <!-- 좌측 | 우측 갈리는 부분 1:9:1: 12: 1-->
  <el-row class="height90">
    <!-- 좌측 -->
    <el-col :span="1"></el-col>

    <el-col :span="8" :offset="1" class="test-border font-14 flex-parent">
      <el-row class="test-border height30">
        <el-col :span="8" class="test-border">
          <el-row class="height100">
            <div class="profile">
              <img
                v-if="user.cover_pic"
                :src="`${user.cover_pic}`"
                class="previewImg"
              />
            </div>
          </el-row>
        </el-col>
        <el-col :span="14" :offset="2" class="test-border flex-parent">
          <el-row class="test-border info-size">
            <el-col :span="5" class="test-border"> email </el-col>
            <el-col :span="15" :offset="4" class="info test-border">
              {{ user.email }}</el-col
            >
          </el-row>
          <el-row class="test-border info-size">
            <el-col :span="5" class="test-border"> 이름 </el-col>
            <el-col :span="15" :offset="4" class="info test-border">
              {{ user.name }}</el-col
            >
          </el-row>
          <el-row class="test-border info-size">
            <el-col :span="5" class="test-border"> 닉네임 </el-col>
            <el-col :span="15" :offset="4" class="info test-border">
              {{ user.nickname }}</el-col
            >
          </el-row>
          <el-row class="test-border info-size">
            <el-col :span="5" class="test-border"> 역할 </el-col>
            <el-col :span="15" :offset="4" class="info test-border">
              {{ user.position }}</el-col
            >
          </el-row>
          <el-row class="test-border info-size">
            <el-col :span="5" class="test-border"> 지역 </el-col>
            <el-col :span="15" :offset="4" class="info">
              {{ user.city }}
            </el-col>
          </el-row>
        </el-col>
      </el-row>

      <!-- 마이페이지 정보 깃~ -->
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> github </el-col>
        <el-col :span="16" :offset="1" class="info">
          <!-- <span
            v-if="
              user.snsList.length > 0 && user.snsList[0].snsName == 'github'
            "
            >{{ user.snsList[0].snsAccount }}</span
          >
          <span
            v-else-if="
              user.snsList.length > 1 && user.snsList[1].snsName == 'github'
            "
            >{{ user.snsList[1].snsAccount }}</span
          >
          <span
            v-else-if="
              user.snsList.length > 2 && user.snsList[2].snsName == 'github'
            "
            >{{ user.snsList[2].snsAccount }}</span
          >
          <span
            v-else-if="
              user.snsList.length > 3 && user.snsList[3].snsName == 'github'
            "
            >{{ user.snsList[3].snsAccount }}</span
          > -->
        </el-col>
      </el-row>
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> 트위터 </el-col>
        <el-col :span="16" :offset="1" class="info">
          <!-- <span
            v-if="
              user.snsList.length > 0 && user.snsList[0].snsName == 'twitter'
            "
            >{{ user.snsList[0].snsAccount }}</span
          >
          <span
            v-else-if="
              user.snsList.length > 1 && user.snsList[1].snsName == 'twitter'
            "
            >{{ user.snsList[1].snsAccount }}</span
          >
          <span
            v-else-if="
              user.snsList.length > 2 && user.snsList[2].snsName == 'twitter'
            "
            >{{ user.snsList[2].snsAccount }}</span
          >
          <span
            v-else-if="
              user.snsList.length > 3 && user.snsList[3].snsName == 'twitter'
            "
            >{{ user.snsList[3].snsAccount }}</span
          > -->
        </el-col>
      </el-row>

      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> 페이스북 </el-col>
        <el-col :span="16" :offset="1" class="info">
          <!-- <span
            v-if="
              user.snsList.length > 0 && user.snsList[0].snsName == 'facebook'
            "
            >{{ user.snsList[0].snsAccount }}</span
          >
          <span
            v-else-if="
              user.snsList.length > 1 && user.snsList[1].snsName == 'facebook'
            "
            >{{ user.snsList[1].snsAccount }}</span
          >
          <span
            v-else-if="
              user.snsList.length > 2 && user.snsList[2].snsName == 'facebook'
            "
            >{{ user.snsList[2].snsAccount }}</span
          >
          <span
            v-else-if="
              user.snsList.length > 3 && user.snsList[3].snsName == 'facebook'
            "
            >{{ user.snsList[3].snsAccount }}</span
          > -->
        </el-col>
      </el-row>
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> 백준 </el-col>
        <el-col :span="16" :offset="1" class="info">
          <!-- <span
            v-if="
              user.snsList.length > 0 && user.snsList[0].snsName == 'backjoon'
            "
            >{{ user.snsList[0].snsAccount }}</span
          >
          <span
            v-else-if="
              user.snsList.length > 1 && user.snsList[1].snsName == 'backjoon'
            "
            >{{ user.snsList[1].snsAccount }}</span
          >
          <span
            v-else-if="
              user.snsList.length > 2 && user.snsList[2].snsName == 'backjoon'
            "
            >{{ user.snsList[2].snsAccount }}</span
          >
          <span
            v-else-if="
              user.snsList.length > 3 && user.snsList[3].snsName == 'backjoon'
            "
            >{{ user.snsList[3].snsAccount }}</span
          > -->
        </el-col>
      </el-row>
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> 포트폴리오 </el-col>
        <el-col :span="16" :offset="1" class="info">
          <a v-if="user.portfolio" :href="`${user.portfolio}`">포트폴리오</a>
        </el-col>
      </el-row>
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> 포트폴리오 URI </el-col>
        <el-col :span="16" :offset="1" class="info">
          {{ user.portfolio_uri }}
        </el-col>
      </el-row>
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> Experienced </el-col>
        <el-col :span="16" :offset="1" class="info">
          <span v-for="item in user.expTechList" :key="item"
            >{{ item }}&nbsp;</span
          >
        </el-col>
      </el-row>
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> Beginner</el-col>
        <el-col :span="16" :offset="1" class="info">
          <span v-for="item in user.beginTechList" :key="item"
            >{{ item }}&nbsp;</span
          >
        </el-col>
      </el-row>
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> 세부 포지션 </el-col>
        <el-col :span="16" :offset="1" class="info">
          <span v-for="item in user.dpositionList" :key="item"
            >{{ item.name }}&nbsp;</span
          >
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border">
          자기소개
          <div id="myImg" style="width: 100px; height: 100px"></div
        ></el-col>
        <el-col :span="16" :offset="1" class="info">
          <span v-if="user.bio">{{ user.bio }}</span>
        </el-col>
      </el-row>

      <el-row class="test-border align-center"></el-row>
    </el-col>

    <!-- 우측 -->
    <el-col :span="12" :offset="1" class="test-border">
      <el-row class="test-border title">내가 속한 클럽</el-row>
      <el-row class="height5"></el-row>
      <el-row class="test-border height20">
        <el-col :span="6" class="test-border"> Item1 </el-col>
        <el-col :span="6" class="test-border"> Item2 </el-col>
        <el-col :span="6" class="test-border"> Item3 </el-col>
        <el-col :span="6" class="test-border"> Item4 </el-col>
      </el-row>
      <el-row class="height5"></el-row>
      <el-row class="test-border title">내가 속한 스터디</el-row>
      <el-row class="height5"></el-row>
      <el-row class="test-border height20">
        <el-col :span="6" class="test-border"> Item1 </el-col>
        <el-col :span="6" class="test-border"> Item2 </el-col>
        <el-col :span="6" class="test-border"> Item3 </el-col>
        <el-col :span="6" class="test-border"> Item4 </el-col>
      </el-row>
      <el-row class="height5"></el-row>
      <el-row class="test-border title">내가 속한 프로젝트</el-row>
      <el-row class="height5"></el-row>
      <el-row class="test-border height20">
        <el-col :span="6" class="test-border"> Item1 </el-col>
        <el-col :span="6" class="test-border"> Item2 </el-col>
        <el-col :span="6" class="test-border"> Item3 </el-col>
        <el-col :span="6" class="test-border"> Item4 </el-col>
      </el-row>
      <el-row class="height5"
        ><el-col :span="22"></el-col>
        <el-col :span="2"><ServiceQuitModal /></el-col
      ></el-row>
    </el-col>
  </el-row>
</template>

<script>
import { useStore } from 'vuex';
import { reactive, computed } from 'vue';
import { useRouter } from 'vue-router';

export default {
  name: 'ReadMyPage',
  components: {},
  setup() {
    const store = useStore();
    const router = useRouter();

    console.log('2-1');
    console.log('2-7');
    const user = computed(() => store.getters['member/mypageGetter']);
    console.log('2-8');

    console.log('유저정보');
    console.log(user);
    console.log(user.value);
    console.log(user.value.email);

    return { store, router, user };
  },
};
</script>
<style scoped>
.title {
  font-size: 24px;
}
.info {
  border-radius: 4px;
  border: 0px;
  background-color: white;
  color: black;
}
.info-size {
  height: 32px;
}
.profile {
  width: 100%;
  height: 100%;
}
.previewImg {
  max-width: 100%;
  max-height: 100%;
}
.title {
  padding-top: 50px;
  text-align: left;
  font-size: 36px;
}
</style>
