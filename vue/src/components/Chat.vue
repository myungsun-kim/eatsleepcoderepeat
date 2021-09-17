<template>
  <el-row class="height5">
    <el-col :span="24" class="test-border"> Chat여백: </el-col>
  </el-row>

  <el-row class="height90">
    <!-- 채팅방 목록 -->
    <el-col :span="5" :offset="1" class="test-border">
      <el-row class="height10">ROOM 1</el-row>
      <el-row class="height10">ROOM 2</el-row>
      <el-row class="height10">ROOM 3</el-row>
      <el-row class="height10">ROOM 4</el-row>
      <el-row class="height10">ROOM 5</el-row>
      <el-row class="height10">ROOM 6</el-row>
      <el-row class="height10">ROOM 7</el-row>
      <el-row class="height10">ROOM 8</el-row>
      <el-row class="height10">ROOM 9</el-row>
      <el-row class="height10">ROOM 10</el-row>
    </el-col>
    <!-- 채팅창 -->
    <el-col :span="17" class="test-border">
      <el-row class="height10">Chat Title</el-row>
      <el-row class="height10">Chat msg 1</el-row>
      <el-row class="height10">Chat msg 2</el-row>
      <el-row class="height10">Chat msg 3</el-row>
      <el-row class="height10">Chat msg 4</el-row>
      <el-row class="height10">Chat msg 5</el-row>
      <el-row class="height10">Chat msg 6</el-row>
      <el-row class="height10">Chat msg 7</el-row>
      <el-row class="height10">Chat msg 8</el-row>
      <el-row class="height10">Chat INPUT</el-row>
    </el-col>
  </el-row>
</template>

<script>
export default {
  components: {},
};
</script>
