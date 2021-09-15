<template>
  <p>SignUp</p>
  <el-row :gutter="20">
    <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
    <el-col :span="6" :offset="6"
      ><div class="grid-content bg-purple"></div
    ></el-col>
  </el-row>
  <el-row :gutter="20">
    <el-col :span="6" :offset="6"
      ><div class="grid-content bg-purple"></div
    ></el-col>
    <el-col :span="6" :offset="6"
      ><div class="grid-content bg-purple"></div
    ></el-col>
  </el-row>
  <el-row :gutter="20">
    <el-col :span="12" :offset="6">
      <p>hello</p>
      <div class="grid-content bg-purple">
        <p></p></div
    ></el-col>
  </el-row>
</template>

<script></script>

<style>
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: red;
}
.bg-purple {
  background: green;
}
.bg-purple-light {
  background: blue;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
