<template>
  <el-row class="height5">
    <el-col :span="24" class="test-border"> General Search 여백 </el-col>
  </el-row>

  <el-row class="test-border height95">
    <el-col :span="2" class="test-border"></el-col>
    <el-col :span="19" class="test-border">
      <el-row class="test-border height5">
        <el-col :span="20" class="test-border">
          <el-input
            placeholder="검색어를 입력하세요"
            v-model="temp"
            size="mini"
          ></el-input>
        </el-col>
        <el-col :span="2" class="test-border"> 상세 </el-col>
        <el-col :span="2" class="test-border"> 검색 </el-col>
      </el-row>

      <el-row class="height5">
        <el-col :span="24" class="test-border"> 스터디 검색 결과 </el-col>
      </el-row>

      <el-row class="height30">
        <el-col :span="2" class="test-border"></el-col>
        <el-col :span="4" class="test-border">백준알고스터디</el-col>
        <el-col :span="1" class="test-border"></el-col>
        <el-col :span="4" class="test-border">단기간면접</el-col>
        <el-col :span="1" class="test-border"></el-col>
        <el-col :span="4" class="test-border">정처기</el-col>
        <el-col :span="1" class="test-border"></el-col>
        <el-col :span="4" class="test-border">웹스터디</el-col>
        <el-col :span="3" class="test-border"></el-col>
      </el-row>

      <el-row class="height5">
        <el-col :span="24" class="test-border"> 더보기, 접기 </el-col>
      </el-row>

      <el-row class="test-border height5"></el-row>

      <el-row class="height5">
        <el-col :span="24" class="test-border"> 프로젝트 검색 결과 </el-col>
      </el-row>

      <el-row class="height30">
        <el-col :span="2" class="test-border"></el-col>
        <el-col :span="4" class="test-border">자바라기</el-col>
        <el-col :span="1" class="test-border"></el-col>
        <el-col :span="4" class="test-border">정처기</el-col>
        <el-col :span="1" class="test-border"></el-col>
        <el-col :span="4" class="test-border">면접</el-col>
        <el-col :span="1" class="test-border"></el-col>
        <el-col :span="4" class="test-border">웹스터디</el-col>
        <el-col :span="3" class="test-border"></el-col>
      </el-row>

      <el-row class="height5">
        <el-col :span="24" class="test-border"> 더보기, 접기 </el-col>
      </el-row>

      <el-row class="test-border height5"></el-row>

      <el-row class="height5">
        <el-col :span="24" class="test-border"> 클럽 검색 결과 </el-col>
      </el-row>

      <el-row class="height30">
        <el-col :span="2" class="test-border"></el-col>
        <el-col :span="4" class="test-border">자바라기</el-col>
        <el-col :span="1" class="test-border"></el-col>
        <el-col :span="4" class="test-border">정처기</el-col>
        <el-col :span="1" class="test-border"></el-col>
        <el-col :span="4" class="test-border">면접</el-col>
        <el-col :span="1" class="test-border"></el-col>
        <el-col :span="4" class="test-border">웹스터디</el-col>
        <el-col :span="3" class="test-border"></el-col>
      </el-row>

      <el-row class="height5">
        <el-col :span="24" class="test-border"> 더보기, 접기 </el-col>
      </el-row>
    </el-col>
    <el-col :span="3" class="test-border"></el-col>
  </el-row>
</template>

<script>
export default {
  components: {},
};
</script>
