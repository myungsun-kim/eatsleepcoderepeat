<template>
  <el-row class="height100"
    ><el-col :span="3"></el-col>
    <el-col :span="19">
      <el-row class="height5">
        <el-col :span="2">제목:</el-col>
        <el-col :span="22" style="margin: auto 0">
          <input
            type="text"
            id="title"
            placeholder="제목을 입력하세요"
            v-model="state.form.title"
          />
        </el-col>
      </el-row>
      <el-row class="height5"> </el-row>
      <el-row style="height: 65%">
        <el-col :span="24">
          <!-- contentType="html" 도 가능함. 원하는 걸로 선택 -->
          <quill-editor
            v-model:content="state.form.content"
            contentType="text"
            theme="snow"
          ></quill-editor>
        </el-col>
      </el-row>
      <el-row class="height5"> </el-row>
      <el-row class="height5">
        <el-col :span="9"> </el-col>
        <el-col :span="2">
          <el-button
            class="btn-1747C9 font-noto-bold"
            @click="goReadDetailNotice"
          >
            생성
          </el-button>
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="2">
          <el-button
            class="btn-ghost-red"
            style="font-size: 14px"
            @click="goReadNotice"
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
import { QuillEditor } from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css';

import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  components: {
    QuillEditor,
  },
  setup() {
    const store = useStore();
    const router = useRouter();

    // 1. 스터디 id를 가져와야 함.
    const studyId = 3;

    // 2. 현재 이 게시판 board id를 가져와야 함.
    // 1번 매커니즘 고치면 stidyId.value로 바꿔야 할 듯.
    store.dispatch('study/getBoardId', studyId);
    const boardIdList = computed(
      () => store.getters['study/studyBoardIdListGetter']
    );
    // 0: {id: 1, name: '공지사항'}
    // 1: {id: 2, name: '게시판'}
    console.log('boardIdList: ');
    console.log(boardIdList);
    const boardId = 1;
    for (let index = 0; index < boardIdList.length; index++) {
      console.log('@@@@@');
      console.log(boardIdList[index]);
      // 여기 안에 안들어가면  .value 지워보기
      if (boardIdList[index].value.name == '공지사항') {
        boardId = index;
      }
    }

    const state = reactive({
      form: {
        title: '', //게시글 제목
        content: '', //게시글 내용
      },
    });

    const goReadDetailNotice = function () {
      // 게시글 내용 확인
      console.log(state.form);
      store.dispatch('study/createArticle', boardId);

      router.push({ path: '/subheader/notice/detail' });
    };
    const goReadNotice = function () {
      router.push({ path: '/subheader/notice' });
    };

    return {
      state,
      goReadDetailNotice,
      goReadNotice,
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
