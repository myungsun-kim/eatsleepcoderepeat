<template>
  <div class="container">
    <div class="left">
      <div class="content1">
        <div id="h1">
          <p>환영합니다!</p>
        </div>
        <form id="login">
          <div>
            <input
              id="id"
              type="text"
              onfocus="this.value='';"
              value="이름을 입력하세요"
            />
          </div>
          <div>
            <input type="text" id="password" />
          </div>
        </form>
      </div>
    </div>

    <div class="right">
      <div class="content2">
        <h1>아직 회원이 아니신가요?</h1>
      </div>
    </div>
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
  width: 70%;
  height: 100vh;
  box-sizing: border-box;
  border: 1px solid red;
}
.container .right {
  float: right;
  width: 30%;
  height: 100%;
  box-sizing: border-box;
  border: 1px solid blue;
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
