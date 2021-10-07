<template>
  <el-button @click="changemodalOpen" class="btn-ghost-red font-noto-bold"
    >거절</el-button
  >
  <teleport to="body">
    <div v-if="!modalOpen" class="modal">
      <div class="height40">
        <!-- 참고:
        https://v3.ko.vuejs.org/guide/teleport.html#vue-%E1%84%8F%E1%85%A5%E1%86%B7%E1%84%91%E1%85%A9%E1%84%82%E1%85%A5%E1%86%AB%E1%84%90%E1%85%B3%E1%84%8B%E1%85%AA-%E1%84%92%E1%85%A1%E1%86%B7%E1%84%81%E1%85%A6-%E1%84%89%E1%85%A1%E1%84%8B%E1%85%AD%E1%86%BC
        I'm a teleported modal! (My parent is "body") -->
        <el-row class="height10"></el-row>
        <el-row class="height20">
          <el-col :span="24" class="font-noto-bold font-20"
            >(지원자닉네임)님의 (스터디명) 가입을 거절하시겠습니까?
          </el-col>
        </el-row>
        <el-row class="height10">
          <el-col :span="6" :offset="6">
            <el-button
              class="btn-ghost-red font-noto-bold"
              @click="reject"
              style="font-size: 14px"
              >거절</el-button
            >
          </el-col>
          <el-col :span="6">
            <el-button
              class="btn-ghost-blue font-noto-bold"
              @click="changemodalOpen"
              style="font-size: 14px"
              >취소</el-button
            >
          </el-col>
          <el-col :span="6"></el-col>
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
    const modalOpen = computed(() => store.getters['rejectModalGetter']);
    // 스터디 ID 가져오기
    const studyId = computed(() => store.getters['study/studyIdGetter']);

    // 해당 멤버 닉네임 가져오기
    const memberNickname = computed(
      () => store.getters['study/studyMemberNicknameGetter']
    );

    // 해당 멤버 지원서 정보 가져오기
    const studyApplication = computed(
      () => store.getters['study/studyApplicationGetter']
    );
    const state = reactive({
      form: {
        studyId: studyId.value,
        memberNickname: memberNickname.value,
        memberId: studyApplication.value.memberId,
      },
    });
    // 해당 멤버 정보 가져오기

    const reject = function () {
      store.dispatch('study/rejectStudy', state.form);
      store.dispatch('changeRejectModal', !modalOpen.value);
    };

    const changemodalOpen = function () {
      store.dispatch('changeRejectModal', !modalOpen.value);
    };

    return {
      router,
      store,
      state,
      modalOpen,
      reject,
      changemodalOpen,
      memberNickname,
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
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.modal div {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: white;
  width: 50%;
}
</style>
