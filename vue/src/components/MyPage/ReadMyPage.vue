<template>
  <el-row class="height5">
    <el-col :span="24" class="test-border"> READ MY PAGE 여백: </el-col>
  </el-row>
  <el-row class="height5">
    <el-col :span="24" class="test-border"> 마이페이지 </el-col>
  </el-row>
  <el-row class="height5"> </el-row>
  <!-- 좌측 | 우측 갈리는 부분 1:9:1: 12: 1-->
  <el-row class="height85">
    <!-- 좌측 -->
    <el-col :span="1"></el-col>

    <el-col :span="8" :offset="1" class="test-border font-14 flex-parent">
      <el-row class="test-border">
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
        <el-col :span="10" :offset="2" class="test-border flex-parent">
          <el-row class="test-border info-size">
            <el-col :span="5" class="test-border"> ID </el-col>
            <el-col :span="15" :offset="4" class="info">
              {{ user.email }}</el-col
            >
          </el-row>
          <el-row class="test-border info-size">
            <el-col :span="5" class="test-border"> 이름 </el-col>
            <el-col :span="15" :offset="4" class="info">
              {{ user.name }}</el-col
            >
          </el-row>
          <el-row class="test-border info-size">
            <el-col :span="5" class="test-border"> 닉넴 </el-col>
            <el-col :span="15" :offset="4" class="info">
              {{ user.nickname }}</el-col
            >
          </el-row>
          <el-row class="test-border info-size">
            <el-col :span="5" class="test-border"> 역할 </el-col>
            <el-col :span="15" :offset="4" class="info">
              {{ user.position }}</el-col
            >
          </el-row>
        </el-col>
      </el-row>
      <!-- 마이페이지 정보 깃~ -->
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> 지역 </el-col>
        <el-col :span="16" :offset="1" class="info"> {{ user.city }} </el-col>
      </el-row>
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> 깃 </el-col>
        <el-col :span="16" :offset="1" class="info"> 깃 </el-col>
      </el-row>
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> 트위터 </el-col>
        <el-col :span="16" :offset="1" class="info"> 트윗 </el-col>
      </el-row>

      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> 페이스북 </el-col>
        <el-col :span="16" :offset="1" class="info"> Facebook </el-col>
      </el-row>
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> 백준 </el-col>
        <el-col :span="16" :offset="1" class="info"> Baekjoon </el-col>
      </el-row>
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> 포트폴리오 </el-col>
        <el-col :span="16" :offset="1" class="info"> port </el-col>
      </el-row>
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> 포트폴리오 url </el-col>
        <el-col :span="16" :offset="1" class="info">
          {{ user.portfolio_uri }}
        </el-col>
      </el-row>
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> Strong </el-col>
        <el-col :span="16" :offset="1" class="info">
          {{ user.expTechList }}
        </el-col>
      </el-row>
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> Knowledgeable </el-col>
        <el-col :span="16" :offset="1" class="info">
          {{ user.beginTechList }}
        </el-col>
      </el-row>
      <el-row class="test-border info-size">
        <el-col :span="7" class="test-border"> 희망 포지션 </el-col>
        <el-col :span="16" :offset="1" class="info">
          <span v-for="item in user.dpositionList" :key="item"
            >{{ item.name }}&nbsp;</span
          >
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> 자기소개 </el-col>
        <el-col :span="16" :offset="1" class="info">
          {{ user.bio }}
        </el-col>
      </el-row>
      <el-row class="test-border align-center"> <PasswordCheckModal /> </el-row>
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
import PasswordCheckModal from '../Modal/PasswordCheckModal.vue';
import ServiceQuitModal from '../Modal/ServiceQuitModal.vue';
import { reactive } from 'vue';
import { useStore } from 'vuex';
import { computed } from 'vue';

export default {
  name: 'ReadMyPage',
  components: {
    PasswordCheckModal,
    ServiceQuitModal,
  },
  setup() {
    const store = useStore();
    const state = { token: {} };
    const res = store.dispatch('member/readMyPage', state.token);
    res.then((res) => {
      store.state.user = res.data;
    });
    const user = computed(() => store.state.user);
    console.log(user);
    return { user };
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
</style>
