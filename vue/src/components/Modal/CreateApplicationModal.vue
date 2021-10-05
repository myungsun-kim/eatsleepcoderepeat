<template>
  <el-button @click="changemodalOpen" class="btn-ghost-blue font-noto-bold">
    신청
  </el-button>
  <teleport to="body">
    <div v-if="!modalOpen" class="modal">
      <div class="height50 flex-parent" style="width: 50%">
        <el-row class="height10"></el-row>
        <el-row class="height10">
          <el-col :span="18" :offset="6" class="font-noto-bold font-20">
            (닉네임)의 (스터디 이름) 스터디 신청서
          </el-col>
        </el-row>
        <el-row class="height10"></el-row>
        <!-- 세 줄 -->
        <el-row class="height40">
          <!-- 좌 우 -->
          <el-col :span="12" :offset="3" class="test-border font-14 height100">
            <el-row class="test-border height25">
              <el-col :span="3"></el-col>
              <el-col :span="6" class="test-border"> 닉네임 </el-col>
              <el-col :span="2"></el-col>
              <el-col :span="13" class="test-border"> SSAFY </el-col>
            </el-row>
            <el-row class="test-border height25">
              <el-col :span="3"></el-col>
              <el-col :span="6" class="test-border"> 지역 </el-col>
              <el-col :span="2"></el-col>
              <el-col :span="13" class="test-border"> SSAFY </el-col>
            </el-row>
            <el-row class="test-border height25">
              <el-col :span="3"></el-col>
              <el-col :span="6" class="test-border"> 깃 </el-col>
              <el-col :span="2"></el-col>
              <el-col :span="13" class="test-border">
                <!-- git icon이 현재 동작 안 함
                <i class="el-icon-github"></i>
                <i class="el-icon-github-circled"></i>
                <i class="el-icon-lock lock font-noto-md"></i> -->
                github
              </el-col>
            </el-row>
            <el-row class="test-border height25">
              <el-col :span="3"></el-col>
              <el-col :span="6" class="test-border"> twitter </el-col>
              <el-col :span="2"></el-col>
              <el-col :span="13" class="test-border"> twitter </el-col>
            </el-row>
          </el-col>
          <!-- 사진 -->
          <el-col
            :span="6"
            class="test-border flex-parent"
            style="width: 100%; height: 100%"
          >
            <el-row class="height100">
              <div class="profile">
                <img class="previewImg" />
                <!-- <img
                  v-if="user.cover_pic"
                  :src="`${user.cover_pic}`"
                  class="previewImg"
                /> -->
              </div>
            </el-row>
          </el-col>
          <el-col :span="3"></el-col>
        </el-row>

        <!-- 한 줄 -->
        <el-row class="height10 font-14">
          <el-col :span="4" :offset="2" class="test-border"
            >&nbsp;facebook
          </el-col>
          <el-col :span="16" class="test-border">facebook</el-col>
        </el-row>
        <el-row class="height10 font-14">
          <el-col :span="4" :offset="2" class="test-border"
            >&nbsp;baekjoon
          </el-col>
          <el-col :span="16" class="test-border">baekjoon</el-col>
        </el-row>
        <!-- 한 줄 -->
        <el-row class="height10 font-14">
          <el-col :span="4" :offset="2" class="test-border">
            &nbsp;Experienced</el-col
          >
          <el-col :span="16" class="test-border">Python</el-col>
        </el-row>
        <el-row class="height10 font-14">
          <el-col :span="4" :offset="2" class="test-border"
            >&nbsp;Beginner</el-col
          >
          <el-col :span="16" class="test-border">Python</el-col>
        </el-row>
        <!-- 한 줄 -->
        <el-row class="height10 font-14">
          <el-col :span="4" :offset="2" class="test-border"
            >&nbsp;자기소개</el-col
          >
          <el-col :span="16" class="test-border">내용을 입력해 주세요</el-col>
        </el-row>
        <!-- 한 줄 -->
        <el-row class="height10">
          <!-- 여백 -->
        </el-row>
        <!-- 한 줄 -->
        <el-row class="height10">
          <el-button class="btn-ghost-round-blue-filled" @click="goApplicate"
            >신청</el-button
          >
          <el-button class="btn-ghost-round-red" @click="changemodalOpen"
            >취소</el-button
          >
        </el-row>
        <el-row class="height20">
          <!-- 여백 -->
        </el-row>
      </div>
    </div>
  </teleport>
</template>
<script>
import { useRouter } from 'vue-router';
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';

export default {
  setup() {
    const router = useRouter();
    const store = useStore();
    const modalOpen = computed(() => store.getters['applicationModalGetter']);
    console.log('모달' + modalOpen.value);
    const state = reactive({
      form: {
        studyId: 3,
        backjoon: 'qjawlsqjacks',
        bio: '설명ㅇㅇㅇㅇ',
        city: '서울',
        facebook: 'facebook.com',
        git: 'BEOMKING',
        nickname: 'BJP',
        twitter: 'twitter.com',
        uuid: '3fads23-fdfd13-23d2',
      },
    });

    // 스터디 ID 가져오기
    const studyId = computed(() => store.getters['study/studyIdGetter']);

    // 해당 참가자 정보 가져오기
    store.dispatch('member/readMyPage');
    const user = computed(() => store.getters['member/mypageGetter']);

    store.dispatch('study/applicationOne', state.form);

    const changemodalOpen = function () {
      store.dispatch('changeApplicationModal', !modalOpen.value);
    };

    // 수락 누를 시
    const goApplicate = function () {
      store.dispatch('study/applicateStudy', state.form); //신청서 수락
      store.dispatch('changeApplicationModal', !modalOpen.value);
      router.push({ path: '/subheader/study/introduce' });
    };

    return {
      store,
      router,
      state,
      modalOpen,
      goApplicate,
      changemodalOpen,
      user,

      studyId,
    };
  },
};
</script>
<style scoped>
.modal {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  /* flex-direction: column; */
  align-items: center;
  justify-content: center;
}

.modal div {
  /* display: flex; */
  /* flex-direction: column; */
  align-items: center;
  justify-content: center;
  background-color: white;
  width: 100%;
}

.btn-ghost-round-blue-filled {
  background: #1747c9;
  border: 1px solid #1747c9;
  box-sizing: border-box;
  border-radius: 30px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  line-height: 16px;
  text-align: center;
  color: #ffffff;
}
</style>
