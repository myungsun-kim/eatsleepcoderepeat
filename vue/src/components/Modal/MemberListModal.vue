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
        <el-row class="height20">
          <el-col :span="1"></el-col>
          <el-col :span="4">사진{{ studyIntroduce.memberDtos }} </el-col>
          <el-col :span="1"></el-col>
          <el-col :span="8">유저명</el-col>
          <el-col :span="5"></el-col>
          <el-col :span="4">
            <el-button class="btn-1747C9 font-noto-bold">
              마이페이지
            </el-button>
          </el-col>
          <el-col :span="1"></el-col>
        </el-row>

        <el-row class="height20">
          <el-col :span="1"></el-col>
          <el-col :span="4">사진</el-col>
          <el-col :span="1"></el-col>
          <el-col :span="8">유저명</el-col>
          <el-col :span="5"></el-col>
          <el-col :span="4">
            <el-button
              class="btn-1747C9 font-noto-bold"
              @click="modalOpen = false"
            >
              마이페이지
            </el-button>
          </el-col>
          <el-col :span="1"></el-col>
        </el-row>

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
import { computed } from 'vue';
import { useStore } from 'vuex';
export default {
  data() {
    return {
      modalOpen: false,
    };
  },
  setup() {
    const store = useStore();
    const studyId = computed(() => store.getters['study/studyIdGetter']);
    console.log('studyId: ' + studyId.value);
    store.dispatch('study/introduce', studyId.value);
    const studyIntroduce = computed(
      () => store.getters['study/studyIntroduceGetter']
    );
    console.log('studyIntroduce: ' + studyIntroduce.value);
    return {
      store,
      studyIntroduce,
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

.oneLine {
  display: flex;
}
</style>
