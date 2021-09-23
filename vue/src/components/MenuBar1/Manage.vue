<template>
  <el-row style="height: 85%">
    <el-col :span="3"></el-col>
    <el-col :span="18">회원목록테이블</el-col>
    <el-col :span="3"></el-col>
  </el-row>
  <el-row style="height: 15%">
    <el-col :span="3"></el-col>
    <el-col :span="18"
      ><el-row style="height: 20%"> </el-row>
      <el-row style="height: 25%">
        <el-col :span="5"></el-col><el-col :span="14">페이지네이션</el-col
        ><el-col :span="3"></el-col
        ><el-col :span="2"><button>글 쓰기</button></el-col>
      </el-row>
      <el-row class="height10"> </el-row>
      <el-row style="height: 35%">
        <el-col :span="4"></el-col>
        <el-col :span="2">리스트</el-col>
        <el-col :span="10">검색창</el-col>
        <el-col :span="2"><MemberAcceptModal /></el-col>
        <el-col :span="2"><MemberRejectModal /></el-col>
        <el-col :span="4"></el-col>
      </el-row>
      <el-row class="height10"> </el-row>
    </el-col>
    <el-col :span="3"></el-col><el-col :span="3"></el-col
  ></el-row>
</template>

<script>
import MemberAcceptModal from '../Modal/MemberAcceptModal.vue';
import MemberRejectModal from '../Modal/MemberRejectModal.vue';
export default {
  components: {
    MemberAcceptModal,
    MemberRejectModal,
  },
};
</script>
