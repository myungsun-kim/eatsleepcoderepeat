<template>
  <el-row class="height5">
    <el-col :span="24" class="test-border"> UPDATE MY PAGE 여백: </el-col>
  </el-row>
  <el-row class="height5">
    <el-col :span="22" :offset="2" class="test-border">
      마이페이지 수정
    </el-col>
  </el-row>
  <el-row class="height5"> </el-row>
  <el-row class="height85">
    <el-col :span="10" :offset="7" class="test-border">
      <el-row class="test-border">
        <el-col :span="9" class="test-border"> File Upload </el-col>
        <el-col :span="13" :offset="2" class="test-border">
          <el-row class="test-border">
            <el-col :span="5" class="test-border"> ID </el-col>
            <el-col :span="15" :offset="4" class="test-border">
              ssafy@ssafy</el-col
            >
          </el-row>
          <el-row class="test-border">
            <el-col :span="5" class="test-border"> 이름 </el-col>
            <el-col :span="15" :offset="4" class="test-border"> 김싸피</el-col>
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
          다양한 프로젝트를 하고 싶습니다. 다양한 프로젝트를 하고 싶습니다.
          다양한 프로젝트를 하고 싶습니다.
        </el-col>
      </el-row>
      <el-row class="test-border"> 수정페이지로 이동 </el-row>
    </el-col>
  </el-row>
</template>

<script>
export default {
  components: {},
};
</script>

<style scoped>
.test-border {
  margin-bottom: 3%;
}
</style>
