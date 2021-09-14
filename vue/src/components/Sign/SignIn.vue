<template>
  <div>
    <div class="left">좌</div>
    <div class="right">우</div>
  </div>
</template>

<script>
export default {};
</script>

<style>
div {
  width: 100%;
  height: 100%;

  border: 1px solid red;
}
div.left {
  width: 50%;
  height: 100%;
  float: left;
  box-sizing: border-box;
}
div.right {
  width: 50%;
  height: 100%;
  float: right;
  box-sizing: border-box;
}
</style>
