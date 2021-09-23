<template>
  <el-row class="height100 top-nav-bar">
    <el-col :span="2"></el-col>
    <el-col :span="5"
      ><el-row>
        <el-col :span="1"></el-col>
        <el-col :span="4">스터디</el-col><el-col :span="4"></el-col
        ><el-col :span="5">프로젝트</el-col><el-col :span="4"></el-col
        ><el-col :span="3">클럽</el-col><el-col :span="3"></el-col>
      </el-row>
    </el-col>
    <el-col :span="2"></el-col>
    <el-col :span="5"></el-col><el-col :span="3"></el-col
    ><el-col :span="6"
      ><el-row>
        <el-col :span="2"> <i class="el-icon-search"></i></el-col>
        <el-col :span="3"></el-col><el-col :span="3">채팅</el-col
        ><el-col :span="3"></el-col><el-col :span="6">마이페이지</el-col
        ><el-col :span="2"></el-col><el-col :span="5">로그아웃</el-col>
      </el-row> </el-col
    ><el-col :span="1"></el-col>
  </el-row>
</template>
<style>
.top-nav-bar {
  background-color: #24305e;
  color: white;
}
</style>
