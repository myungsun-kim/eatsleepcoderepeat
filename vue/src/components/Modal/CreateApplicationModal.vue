<template>
  <el-button @click="changemodalOpen" class="btn-ghost-blue font-noto-bold">
    신청
  </el-button>
  <teleport to="body">
    <div v-if="!modalOpen" class="modal">
      <div class="height40 flex-parent" style="width: 50%">
        <el-row class="height10"></el-row>
        <el-row class="height10">
          <el-col :span="24" class="font-noto-bold font-20">
            (스터디 이름) 스터디 신청서
          </el-col>
        </el-row>
        <!-- 세 줄 -->
        <el-row class="height30">
          <!-- 좌 우 -->
          <el-col :span="8" :offset="2" class="test-border flex-parent">
            <el-row class="test-border">
              <el-col :span="8" class="test-border"> 닉네임 </el-col>
              <el-col :span="16" class="test-border"> SSAFY </el-col>
            </el-row>
            <el-row class="test-border">
              <el-col :span="8" class="test-border"> 지역 </el-col>
              <el-col :span="16" class="test-border"> SSAFY </el-col>
            </el-row>
            <el-row class="test-border">
              <el-col :span="8" class="test-border"> 깃 </el-col>
              <el-col :span="16" class="test-border"> Git Twit Fb BJ </el-col>
            </el-row>
            <el-row class="test-border">
              <el-col :span="8" class="test-border"> 트위터 </el-col>
              <el-col :span="16" class="test-border"> Git Twit Fb BJ </el-col>
            </el-row>
          </el-col>
          <el-col :span="12" class="test-border">
            <el-upload
              class="upload-demo"
              drag
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :file-list="fileList"
              multiple
            >
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">
                파일을 드래그 하거나
                <br />
                <em>클릭해서 업로드 하세요</em>
              </div>
            </el-upload>
          </el-col>
        </el-row>

        <!-- 한 줄 -->
        <el-row class="height10">
          <el-col :span="4" :offset="2" class="test-border">페이스북 </el-col>
          <el-col :span="16" class="test-border">C++</el-col>
        </el-row>
        <!-- 한 줄 -->
        <el-row class="height10">
          <el-col :span="4" :offset="2" class="test-border">백준 </el-col>
          <el-col :span="16" class="test-border">C++</el-col>
        </el-row>
        <!-- 한 줄 -->
        <el-row class="height10">
          <el-col :span="4" :offset="2" class="test-border">Strong </el-col>
          <el-col :span="16" class="test-border">C++</el-col>
        </el-row>
        <!-- 한 줄 -->
        <el-row class="height10">
          <el-col :span="4" :offset="2" class="test-border"
            >Knowledgeable</el-col
          >
          <el-col :span="16" class="test-border">Python</el-col>
        </el-row>
        <!-- 한 줄 -->
        <el-row class="height30">
          <el-col :span="4" :offset="2" class="test-border">자기소개</el-col>
          <el-col :span="16" class="test-border">내용을 입력해 주세요</el-col>
        </el-row>
        <!-- 한 줄 -->
        <el-row class="height10">
          <!-- 여백 -->
        </el-row>
        <!-- 한 줄 -->
        <el-row class="height10">
          <el-button class="btn-ghost-round-blue-filled">수락</el-button>
          <el-button class="btn-ghost-round-red">거절</el-button>
          <el-button class="btn-ghost-round">닫기</el-button>
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
    const goManage = function () {
      store.dispatch('study/approvalStudy', state.form); //신청서 수락
      store.dispatch('changeApplicationModal', !modalOpen.value);
      router.push({ path: '/subheader/study/manage' });
    };

    return {
      store,
      router,
      state,
      modalOpen,
      goManage,
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
