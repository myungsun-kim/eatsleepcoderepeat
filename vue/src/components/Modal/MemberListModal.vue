<template>
  <div @click="modalOpen = true" class="oneLine">
    {{ studyIntroduce.memberCount }}/{{ studyIntroduce.maxCount }} 명(

    <div v-for="(member, index) in studyIntroduce.memberDtos" :key="index">
      &nbsp;{{ member.nickname }}&nbsp;
    </div>

    )
  </div>
  <teleport to="body">
    <div v-if="modalOpen" class="modal">
      <div class="height80" style="width: 30%">
        <el-row class="height10"></el-row>
        {{ studyIntroduce.memberDtos }}
        <el-row
          class="height20"
          v-for="(item, index) in studyIntroduce.memberDtos"
          :key="index"
        >
          <el-col :span="1"></el-col>
          <el-col :span="4">사진</el-col>
          <el-col :span="1"></el-col>
          <el-col :span="8">{{ item.nickname }}</el-col>
          <el-col :span="5"></el-col>
          <el-col :span="4">
            <el-button class="btn-1747C9 font-noto-bold" @click="goInfoPage">
              마이페이지
            </el-button>
          </el-col>
          <el-col :span="1"></el-col>
        </el-row>

        <el-row class="height20"></el-row>

        <el-row class="height20">
          <el-col :span="5"></el-col>
          <el-col :span="14">
            <el-button
              class="btn-1747C9 font-noto-bold"
              @click="modalOpen = false"
              >확인</el-button
            >
          </el-col>
          <el-col :span="5"></el-col>
        </el-row>
      </div>
    </div>
  </teleport>
</template>
<script>
import { computed, watch } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  data() {
    return {
      modalOpen: false,
    };
  },
  setup() {
    const store = useStore();

    // 1. 스터디 ID를 받아옴
    const studyId = computed(() => store.getters['study/studyIdGetter']);
    // console.log(studyId);
    // 2. 모달창에 스터디 정보를 일부 띄워야하기 때문에 스터디 정보를 받음
    const studyIntroduce = computed(
      () => store.getters['study/studyIntroduceGetter']
    );
    // 혹시나 스터디 정보가 바뀌면 다시 정보 가져오기
    watch(studyId, () => {
      store.dispatch('study/introduce', studyId.value);
    });

    // 아무튼 이메일을 알았다고 가정(백엔드 작업 후)
    // 해당 회원의 정보 페이지로 이동
    const goInfoPage = function (val) {
      // console.log('goInfoPage');
      // 선택한 회원의 이메일 정보 저장
      // store.dispatch('member/updateUserEmail', val.email);
      // router.push({ path: '/nosubheader/readinfopage' });
    };

    return {
      studyIntroduce,
      goInfoPage,
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
  width: 100%;
}

.height20 {
  margin-bottom: 5px;
}

.oneLine {
  display: flex;
}
</style>
