<template>
  <el-row class="height5">
    <el-col :span="24" class="test-border"> UPDATE MY PAGE 여백: </el-col>
  </el-row>
  <el-row class="height5">
    <el-col :span="7"></el-col>

    <el-col :span="17" class="test-border itemlist-title-left">
      마이페이지 수정
    </el-col>
  </el-row>
  <el-row class="height5"> </el-row>
  <el-row class="height85">
    <el-col :span="10" :offset="7" class="test-border flex-parent">
      <el-row class="test-border">
        <el-col :span="12" class="test-border">
          <el-upload
            ref="upload"
            class="upload-demo"
            drag
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :file-list="fileList"
            :before-upload="beforeUpload"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              파일을 드래그 하거나
              <br />
              <em>클릭해서 업로드 하세요</em>
            </div>
          </el-upload>
        </el-col>
        <el-col :span="10" :offset="2" class="test-border">
          <el-row class="test-border">
            <el-col :span="5" class="test-border"> ID </el-col>
            <el-col :span="15" :offset="4" class="test-border">
              ssafy@ssafy.com
            </el-col>
          </el-row>
          <el-row class="test-border">
            <el-col :span="5" class="test-border"> 이름 </el-col>
            <el-col :span="15" :offset="4" class="test-border">
              <el-input
                type="text"
                v-model="inputName"
                placeholder="김싸피"
              ></el-input>
            </el-col>
          </el-row>
          <el-row class="test-border">
            <el-col :span="5" class="test-border"> 닉넴 </el-col>
            <el-col :span="15" :offset="4" class="test-border"> ssAfy</el-col>
          </el-row>
          <el-row class="test-border">
            <el-col :span="5" class="test-border"> 역할 </el-col>
            <el-col :span="15" :offset="4" class="test-border"> 개발자</el-col>
          </el-row>
          <el-row class="test-border">
            <el-col :span="5" class="test-border"> 지역 </el-col>
            <el-col :span="15" :offset="4" class="test-border"> 경북</el-col>
          </el-row>
        </el-col>
      </el-row>
      <!-- 마이페이지 정보 깃~ -->
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> 깃 </el-col>
        <el-col :span="16" :offset="1" class="test-border"> Github </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> 트위터 </el-col>
        <el-col :span="16" :offset="1" class="test-border"> Twitter </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> 페이스북 </el-col>
        <el-col :span="16" :offset="1" class="test-border"> Facebook </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> 백준 </el-col>
        <el-col :span="16" :offset="1" class="test-border"> Baekjoon </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> 포트폴리오 </el-col>
        <el-col :span="12" :offset="1" class="test-border"> port </el-col>
        <el-col :span="4" class="test-border"
          ><el-upload>업로드</el-upload>
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> 포트폴리오 url </el-col>
        <el-col :span="16" :offset="1" class="test-border"> url </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> Strong </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          C++, Python
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> Knowledgeable </el-col>
        <el-col :span="16" :offset="1" class="test-border"> Java </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> 희망 포지션 </el-col>
        <el-col :span="16" :offset="1" class="test-border"> FE </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> 자기소개 </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          v-model로 값을 받으려고 생각중. 자세한 것은 최준성 팀원의 저번
          프로젝트 관해서 '입력, v-model, input' 키워드로 물어볼 것
        </el-col>
      </el-row>
      <el-row class="test-border align-center">
        <el-button class="btn-ghost-round-blue" @click="goReadMyPage"
          >수정
        </el-button>
        <el-button class="btn-ghost-round" @click="goReadMyPage"
          >취소
        </el-button>
      </el-row>
    </el-col>
  </el-row>
</template>

<script>
import { ref } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import axios from 'axios';

export default {
  methods: {
    beforeUpload: function (file) {
      let formData = new FormData();
      formData.append('file', file);
      const res = axios.post('/api/file/uploadFile', formData, {
        headers: {
          // auth가 제대로 안넘어가면 401 error 발생
          // 이해가 안되면 최민수에게 문의
          Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
          'Content-Type': 'multipart/form-data',
        },
      });
      // console.log(res);
    },
  },

  setup() {
    const store = useStore();
    const router = useRouter();

    const goReadMyPage = function () {
      router.push({ path: '/nosubheader/readmypage' });
    };

    return {
      store,
      router,
      goReadMyPage,
      inputName: ref(''),
    };
  },
};
</script>

<style scoped>
.test-border {
  margin-bottom: 3%;
}
.flex-parent {
  display: flex;
  flex-direction: column;
  text-align: left;
  justify-content: space-evenly;
}
</style>
