<template>
  <div class="container">
    <div class="height10">1짜리</div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.container {
  /* display: grid; */
  display: flex;
  width: 100%;
  height: 100%;
}
.container .left {
  /* transform: translate(50%, 50%); */
  float: left;
  width: 100%;
  height: 100vh;
  box-sizing: border-box;
  border: 1px solid red;
}
#h1 {
  font-size: 48px;
}
#login {
}
#id {
  background-color: #eef5f3;
  width: 396px;
  height: 46px;
  border: 0;
  border-radius: 10px;
  overflow: hidden;
}
#password {
  background-color: #eef5f3;
  width: 396px;
  height: 46px;
  border: 0;
  border-radius: 10px;
  margin-top: 30px;
}
</style>
