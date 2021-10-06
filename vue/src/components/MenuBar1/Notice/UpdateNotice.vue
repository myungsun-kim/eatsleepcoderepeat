<template>
  <el-row class="height100"
    ><el-col :span="3"></el-col>
    <el-col :span="19"
      ><el-row class="height5"
        ><el-col :span="2">제목:</el-col
        ><el-col :span="22" style="margin: auto 0"
          ><input type="text" id="title" placeholder="제목을 입력하세요"
        /></el-col> </el-row
      ><el-row class="height5"> </el-row>
      <el-row style="height: 65%">
        <el-col :span="24"><QuillEditor theme="snow" /></el-col> </el-row
      ><el-row class="height5"> </el-row>
      <el-row class="height5">
        <el-col :span="9"></el-col>
        <el-col :span="2">
          <el-button
            class="btn-1747C9 font-noto-bold"
            @click="goReadDetailNotice"
            >수정</el-button
          >
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="2">
          <el-button
            class="btn-ghost-red"
            style="font-size: 14px"
            @click="goBack"
          >
            취소
          </el-button>
        </el-col>
        <el-col :span="10"></el-col>
      </el-row>
      <el-row style="height: 20%"></el-row>
    </el-col>
    <el-col :span="2"></el-col>
  </el-row>
</template>
<script>
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { QuillEditor } from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css';

export default {
  components: {
    QuillEditor,
  },
  setup() {
    const store = useStore();
    const router = useRouter();

    const goReadDetailNotice = function () {
      router.push({ path: '/subheader/notice/detail' });
    };
    const goBack = function () {
      router.push({ path: '/subheader/notice/detail' });
    };

    return {
      store,
      router,
      goBack,
      goReadDetailNotice,
    };
  },
};
</script>
<style>
#title {
  width: 98%;
  height: 100%;
  /* margin: 0 auto; */
  background-color: #f2f2f2;
  border: 0px solid black;
}
</style>
