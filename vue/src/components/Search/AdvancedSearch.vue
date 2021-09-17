<template>
  <el-row class="height5">
    <el-col :span="24" class="test-border">
      Advanced Search 여백: 입력칸이 생각보다 빡빡함.
    </el-col>
  </el-row>

  <el-row class="test-border height5">
    <el-col :span="2" :offset="1" class="test-border">
      <el-input placeholder="분류" v-model="temp" size="mini"></el-input>
    </el-col>
    <el-col :span="2" class="test-border">
      <el-input placeholder="기술스택" v-model="temp" size="mini"></el-input>
    </el-col>
    <el-col :span="2" class="test-border">
      <el-input placeholder="기한" v-model="temp" size="mini"></el-input>
    </el-col>
    <el-col :span="2" class="test-border"
      ><el-input placeholder="인원" v-model="temp" size="mini"></el-input>
    </el-col>
    <el-col :span="2" class="test-border">
      <el-input placeholder="상태" v-model="temp" size="mini"></el-input>
    </el-col>
    <el-col :span="2" class="test-border">
      <el-input placeholder="참여 여부" v-model="temp" size="mini"></el-input>
    </el-col>
    <el-col :span="2" class="test-border">
      <el-input placeholder="지역" v-model="temp" size="mini"></el-input>
    </el-col>
    <el-col :span="2" class="test-border">
      <el-input placeholder="팀장 아이디" v-model="temp" size="mini"></el-input>
    </el-col>
    <el-col :span="4" class="test-border">
      <el-input
        placeholder="검색어를 입력하세요"
        v-model="temp"
        size="mini"
      ></el-input>
    </el-col>
    <el-col :span="1" class="test-border"> 일반 </el-col>
    <el-col :span="1" class="test-border"> 검색 </el-col>
  </el-row>

  <el-row class="test-border height95">
    <el-col :span="2" class="test-border"></el-col>
    <el-col :span="19" class="test-border">
      <el-row class="height5">
        <el-col :span="24" class="test-border">
          스터디(or 프로젝트 or 클럽) 검색 결과
        </el-col>
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

      <el-row class="height5"> </el-row>

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

      <el-row class="height5"> </el-row>

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
        <el-col :span="24" class="test-border">
          더보기, 접기: 무한스크롤?
        </el-col>
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
