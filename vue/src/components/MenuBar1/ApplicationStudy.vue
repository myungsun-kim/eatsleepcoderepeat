<template>
  <div class="bg">
    <el-row :gutter="0">
      <el-col :span="6" :offset="0"> </el-col>
      <el-col :span="12" :offset="0">
        <div id="h1">지원자 정보</div>
        <hr />
        <div id="box3">
          <div id="box2">
            <div id="box1">
              <label id="h2">닉네임</label>

              <div class="input1">{{ application.nickname }}</div>
            </div>
            <div id="box1">
              <label id="h2">지역</label>
              <div class="input1">{{ application.city }}</div>
            </div>
            <div id="box1">
              <label id="h2">깃</label>
              <div class="input1">{{ application.git }}</div>
            </div>
            <div id="box1">
              <label id="h2">트위터</label>
              <div class="input1">{{ application.twitter }}</div>
            </div>
          </div>
          <div id="box4">
            <div id="thumbnail">
              <img class="previewImg" src="../../assets/Item/basic.png" />
            </div>
          </div>
        </div>
        <div id="box1">
          <label id="h2">페이스북</label>
          <div class="input1">{{ application.facebook }}</div>
        </div>
        <div id="box1">
          <label id="h2">백준</label>
          <div class="input1">{{ application.backjoon }}</div>
        </div>
        <div id="box1">
          <label id="h2">Experienced</label>
          <div class="input1">
            <div v-for="(item, index) in application.strong" :key="index">
              {{ item }}&nbsp;
            </div>
          </div>
        </div>
        <div id="box1">
          <label id="h2">Beginner</label>
          <div class="input1">
            <div
              v-for="(item, index) in application.knowledgeable"
              :key="index"
            >
              {{ item }}&nbsp;
            </div>
          </div>
        </div>
        <div id="box1">
          <label id="h2">자기소개</label>
          <div class="input1">{{ application.bio }}</div>
        </div>
        <div id="btn">
          <el-button class="btn-create" @click="accept">수락</el-button>
          <el-button class="btn-cancel" @click="reject">거절</el-button>
          <el-button class="btn-cancel" @click="goback">취소</el-button>
        </div>
      </el-col>
      <el-col :span="6" :offset="0">
        <div class="height100"></div>
      </el-col>
    </el-row>
  </div>
  <div class="height10"></div>
</template>
<script>
import { useRouter } from 'vue-router';
import { reactive, computed, watch } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'ApplicationStudy',
  methods: {},
  setup() {
    const router = useRouter();
    const store = useStore();

    const application = computed(
      () => store.getters['study/studyApplicationGetter']
    );

    const state = reactive({
      form: {
        studyId: application.value.studyId,
        memberId: application.value.memberId,
      },
    });
    // 수락
    const accept = function () {
      store.dispatch('study/approvalStudy', state.form);
      router.push({ path: '/subheader/study/manage' });
    };
    // 거절
    const reject = function () {
      store.dispatch('study/rejectStudy', state.form);
      router.push({ path: '/subheader/study/manage' });
    };

    const goback = function () {
      router.push({ path: '/subheader/study/manage' });
    };

    return {
      store,
      router,
      state,
      application,
      accept,
      reject,
      goback,
    };
  },
};
</script>
<style scoped>
.bg {
  background: #f2f2f2;
}
#h1 {
  width: 184px;
  height: 52px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 36px;
  line-height: 52px;
  margin-top: 50px;
  margin-bottom: 0px;
  margin-left: 1px;
  text-align: left;
  color: #000000;
}
#h2 {
  width: 300px;
  height: 35px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 20px;
  line-height: 29px;
  margin-top: 10px;
  margin-left: 2px;
  text-align: left;
  color: #000000;
}
.box0 {
  display: flex;
  align-items: center;
  width: 782px;
  height: 50px;

  /* background: #e8e8e8; */
  background: white;
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  padding-left: 10px;
  margin-left: 2px;
  /* border: 1px solid blue; */

  /*  텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  /* identical to box height, or 114% */
  text-align: left;

  color: #919191;
}
.box6 {
  display: flex;
}
.input {
  width: 782px;
  height: 50px;

  /* background: #e8e8e8; */
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  padding-left: 10px;
  margin-left: 2px;

  /* 비밀번호확인 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  /* identical to box height, or 114% */
  text-align: left;

  color: #919191;
}
.input1 {
  width: 363px;
  height: 50px;

  /* background: #e8e8e8; */
  /* background: white; */
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  padding-left: 10px;
  margin-left: 2px;

  /*  텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  /* identical to box height, or 114% */
  text-align: left;

  color: #919191;
}
.input2 {
  width: 782px;
  height: 200px;

  /* background: #e8e8e8; */
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  margin-left: 2px;
  padding-top: 15px;
  padding-left: 10px;

  /* 비밀번호확인 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  /* identical to box height, or 114% */
  text-align: left;
  align-content: flex-start;
  resize: none;

  color: #919191;
}
.input3 {
  cursor: pointer;
  width: 794px;
  height: 52px;

  /* background: #e8e8e8; */
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  padding-left: 10px;
  margin-left: 2px;

  /* 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  /* identical to box height, or 114% */
  text-align: left;

  color: #919191;
}
.input4 {
  width: 782px;
  height: 200px;

  /* background: #e8e8e8; */
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  margin-left: 2px;
  padding-top: 15px;
  padding-left: 10px;

  /* 비밀번호확인 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  /* identical to box height, or 114% */
  text-align: left;
  align-content: flex-start;
  resize: none;

  color: #919191;
}
#box1 {
  display: flex;
  flex-flow: column;
}
#box2 {
  display: flex;
  flex-flow: column;
}
#box3 {
  display: flex;
}
#box4 {
  margin-left: 60px;
  display: flex;
  flex-flow: column;
}
#box5 {
  display: flex;
  flex-flow: column;
}
#box6 {
  display: flex;
  flex-flow: column;
  justify-content: center;
}
#btn {
  margin-top: 50px;
}
.btn-cancel {
  margin-left: 10px;
}
#autocomplete {
  position: absolute;
  display: none;
  display: flex;
  flex-flow: column;

  width: 400px;
  max-height: 150px;
  margin-top: 140px;

  overflow: auto;
  margin-left: 0px;
  background: #919191;
  border-radius: 4px;
}
#warning1 {
  display: flex;
  align-items: center;
  margin-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
  color: #ff5757;
}
#warning2 {
  display: flex;
  align-items: center;
  margin-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
  color: #ff5757;
}
#warning3 {
  display: flex;
  align-items: center;
  margin-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
  color: #ff5757;
}
.autocomplete1 {
  background: white;
  /* background: #e8e8e8; */
}
.autocomplete1:hover {
  background: #3f8bfc;
  /* background: #307ff5; */
  color: white;
}
.autocomplete1:active {
  background: black;
}
#thumbnail {
  width: 70%;
  height: 70%;

  overflow: hidden;

  border: 0.5px dashed black;
  border-radius: 3%;
}
#radio {
  height: 40px;
  display: flex;
  vertical-align: middle;
}
.previewImg {
  max-width: 100%;
  max-height: 100%;
  object-fit: cover;
  overflow: hidden;
}
.label3 {
  min-width: 200px;
  display: flex;
  text-align: left;
  align-items: center;
  margin-top: -5px;
  margin-left: 5px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 16px;
}
.addValue1 {
  height: 30px;
  display: flex;
  /* text-align: center; */
  vertical-align: middle;

  margin-right: 5px;
  padding-top: 3px;
  padding-left: 5px;
  padding-right: 5px;
  border: 1px solid #307ff5;
  border-radius: 10px;
  color: #307ff5;

  font-family: Noto Sans KR;
  font-style: normal;
}
.addValue1:hover {
  height: 30px;
  display: flex;
  /* text-align: center; */
  vertical-align: middle;

  margin-right: 5px;
  padding-top: 3px;
  padding-left: 5px;
  padding-right: 5px;
  border: 1px solid #ff5757;
  border-radius: 10px;
  color: #ff5757;

  font-family: Noto Sans KR;
  font-style: normal;

  cursor: pointer;
}
</style>
