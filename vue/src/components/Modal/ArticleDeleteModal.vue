<template>
  <!-- 글 삭제  03-5-2 -->
  <button @click="modalOpen = true">
    Open full screen modal! (With teleport!)
  </button>
  <teleport to="body">
    <div v-if="modalOpen" class="modal">
      <div class="height40">
        <!-- 참고:
        https://v3.ko.vuejs.org/guide/teleport.html#vue-%E1%84%8F%E1%85%A5%E1%86%B7%E1%84%91%E1%85%A9%E1%84%82%E1%85%A5%E1%86%AB%E1%84%90%E1%85%B3%E1%84%8B%E1%85%AA-%E1%84%92%E1%85%A1%E1%86%B7%E1%84%81%E1%85%A6-%E1%84%89%E1%85%A1%E1%84%8B%E1%85%AD%E1%86%BC
        I'm a teleported modal! (My parent is "body") -->
        <el-row class="height30"></el-row>
        <el-row class="height10">
          <el-col :span="24" class="font-noto-bold font-20"
            >금주 모임 시간 변경 안내</el-col
          >
        </el-row>
        <el-row class="height10">
          <el-col :span="24" class="font-noto-md font-20"
            >정말로 삭제하시겠습니까?</el-col
          >
        </el-row>
        <el-row class="height10">
          <el-col :span="6" :offset="6">
            <el-button
              class="btn-ghost-red font-noto-bold"
              @click="modalOpen = false"
              style="font-size: 14px"
              >삭제</el-button
            >
          </el-col>
          <el-col :span="6">
            <el-button
              class="btn-ghost-blue font-noto-bold"
              @click="modalOpen = false"
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
