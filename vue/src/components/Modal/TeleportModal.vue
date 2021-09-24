<template>
  <el-button @click="modalOpen = true" class="">
    Open full screen modal! (With teleport!)
  </el-button>
  <teleport to="body">
    <div v-if="modalOpen" class="modal">
      <div class="height40">
        <el-row class="height30"></el-row>
        <el-row class="height10">
          <el-col :span="24" class="font-noto-bold font-20">
            백준 알고리즘 스터디
          </el-col>
        </el-row>
        <el-row class="height10">
          <el-col :span="24" class="font-noto-md font-20">
            정말로 탈퇴하시겠습니까
          </el-col>
        </el-row>
        <el-row class="height10">
          <el-col :span="6" :offset="6">
            <el-button
              class="btn-ghost-red font-noto-bold"
              @click="modalOpen = false"
              style="font-size: 14px"
            >
              탈퇴
            </el-button>
          </el-col>
          <el-col :span="6">
            <el-button
              class="btn-ghost-blue font-noto-bold"
              @click="modalOpen = false"
              style="font-size: 14px"
            >
              취소
            </el-button>
          </el-col>
          <el-col :span="6"></el-col>
        </el-row>
      </div>
    </div>
  </teleport>
</template>
<script>
export default {
  data() {
    return {
      modalOpen: false,
    };
  },
};
</script>
<style scope>
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
