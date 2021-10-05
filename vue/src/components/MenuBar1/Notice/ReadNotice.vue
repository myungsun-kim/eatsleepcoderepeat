<template>
  <div class="all">
    <el-row style="maxheight: 85%">
      <el-col :span="3">
        element-plus tablem 일정 개수를 넘어가면 다음 페이지로 넘어가는 알고리즘
        필요 <br />
        보드 아이디: {{ boardId }}
      </el-col>
      <el-col :span="18">
        <!-- :data="articleList" -->
        <el-table
          :data="tableData"
          stripe
          style="width: 100%"
          highlight-current-row
          @cell-click="goArticle"
          ><el-table-column prop="articleId" label="No" width="" align="center">
          </el-table-column>
          <el-table-column prop="title" label="Title" width="" align="center">
          </el-table-column>
          <el-table-column
            prop="createdMember"
            label="Author"
            width="100"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="createdDate"
            label="Date"
            width="300"
            align="center"
          >
          </el-table-column>
        </el-table>
      </el-col>
      <el-col :span="3"></el-col>
    </el-row>

    <!-- 하단 부분: pagination + 겸색 등 -->
    <el-row style="height: 15%">
      <el-col :span="3"></el-col>
      <el-col :span="18">
        <el-row style="height: 20%"></el-row>
        <el-row style="height: 25%">
          <el-col :span="5"></el-col>
          <el-col :span="14">
            <el-pagination background layout="prev, pager, next" :total="1000">
            </el-pagination>
          </el-col>
          <el-col :span="3"> </el-col>
          <el-col :span="2">
            <el-button
              class="btn-1747C9 font-noto-bold"
              @click="goCreateNotice"
            >
              글 쓰기
            </el-button>
          </el-col>
        </el-row>

        <el-row class="height20"></el-row>
        <!-- dropdown -->
        <el-row style="height: 35%" class="footer">
          <el-col :span="2" :offset="4" id="footer1">
            <select id="select" v-model="state.select">
              <option value="" class="option1">제목</option>
              <option value="작성자" class="option1">작성자</option>
              <option value="작성일" class="option1">작성일</option>
            </select>
          </el-col>
          <el-col :span="12" id="footer2">
            <input
              class="input"
              placeholder="검색어를 입력하세요"
              v-model="temp"
              size="middle"
            />
          </el-col>
          <el-col :span="2" id="footer3">
            <button id="button1">검색</button>
          </el-col>
          <el-col :span="4"></el-col>
        </el-row>
      </el-col>
      <el-col :span="3"></el-col>
      <el-col :span="3"></el-col>
    </el-row>
  </div>
</template>

<script>
import { reactive, ref, computed, watch } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
export default {
  setup() {
    const store = useStore();
    const router = useRouter();
    const currentRow = ref('1');
    const state = reactive({
      form: {},
      select: '',
    });

    // 1. 스터디 id를 가져와야 함.
    const studyId = computed(() => store.getters['study/studyIdGetter']);
    watch(studyId, () => {
      console.log('studyId가 바뀌어서 새로 가져오기');
      store.dispatch('study/getBoardId', studyId);
    });

    // 2. 현재 이 게시판 board id를 가져와야 함.
    // 1번 매커니즘 고치면 stidyId.value로 바꿔야 할 듯.
    const boardIdList = computed(
      () => store.getters['study/studyBoardIdListGetter']
    );

    watch(boardIdList.length, () => {
      console.log('boardIdList length 바뀜');
      store.dispatch('study/getNoticeArticleList', boardId);
    });
    watch(boardIdList, () => {
      console.log('boardIdList 바뀜');
      store.dispatch('study/getNoticeArticleList', boardId);
    });
    // 0: {id: 1, name: '공지사항'}
    // 1: {id: 2, name: '게시판'}

    // 3. 보드 id 가져오기
    // 일단 접근이 잘 안되서 tempList로 옮김
    let tempList = boardIdList.value;
    console.log(tempList);
    let boardId = 1;
    for (let index = 0; index < tempList.length; index++) {
      if (tempList[index].name == '공지사항') {
        boardId = tempList[index].id;
        console.log(boardId);

        store.commit('study/updateStudyNoticeBoardId', tempList[index].id);
      } else {
        store.commit('study/updateStudyNormalBoardId', tempList[index].id);
      }
    }
    //  4. 게시판 목록 가져오기
    watch(boardId, () => {
      console.log('boardId 바뀜');
      store.dispatch('study/getNoticeArticleList', boardId);
    });

    store.dispatch('study/getNoticeArticleList', boardId);
    const articleList = computed(
      () => store.getters['study/studyNoticeArticleListGetter']
    );
    // watch(articleList.value, () => {
    //   console.log('articleList 바뀜');
    //   store.dispatch('study/getNoticeArticleList', boardId);
    // });
    // watch(articleList.value.content.length, () => {
    //   console.log('articleList.value.content.length 바뀜');
    //   store.dispatch('study/getNoticeArticleList', boardId);
    // });
    console.log('articleList 출력');
    // console.log(articleList);
    // console.log(articleList.value);
    console.log(articleList.value.content);
    // console.log(articleList.value.content[0]);
    // articleId: 8
    // createdDate: "2021-10-05T16:08:11.246601"
    // createdMember: "별명"
    // modifiedDate: null
    // studyBoard: "공지사항"
    // title: "ㅁㄴㅇㅁㅇㅁ"
    // viewCount: 0
    // console.log(articleList.value.content[0].title);

    const tableData = articleList.value.content;
    // watch(tableData, () => {
    //   console.log('tableData 바뀜');
    //   // store.dispatch('study/getNoticeArticleList', boardId);
    // });

    const goCreateNotice = function () {
      router.push({ path: '/subheader/notice/create' });
    };

    const goArticle = function (val) {
      console.log(val.articleId);
      store.dispatch('study/callUpdateArticleId', val.articleId);
      router.push({ path: '/subheader/notice/detail' });
    };

    return {
      store,
      router,
      state,
      boardId,
      goCreateNotice,
      goArticle,
      tableData,
      currentRow,
    };
  },
};
</script>
<style scoped>
.all {
  height: 100%;
  display: flex;
  flex-flow: column;
  justify-content: space-between;
}
.input {
  width: 100%;
  height: 36px;
  border: 1px solid black;
  border-radius: 4px;
  padding-left: 10px;
}
.input:hover {
  border: 1px solid #409eff;
}
.input:focus {
  border: 1px solid #409eff;
}
/* 각 박스 클릭 시 나오는 테두리 없애기 */
input:focus {
  outline: none;
}
select:focus {
  outline: none;
}
button:focus {
  outline: none;
}
#button1 {
  cursor: pointer;
  font-size: 15px;
  width: 70px;
  height: 40px;
  border: 1px solid black;
  border-radius: 4px;

  text-align: center;
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
}
#button1:hover {
  cursor: pointer;
  color: #409eff;
  border: 1px solid #409eff;
  border-radius: 4px;
}
#button1:active {
  /* border-style: groove; */
  border: 3px solid #409eff;
}
.footer {
  align-items: center;
}
#footer1 {
  display: flex;
  flex-wrap: wrap;
  justify-content: end;
  margin-right: 10px;
}
#footer2 {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  margin-right: 10px;
}
#footer3 {
  display: flex;
  flex-wrap: wrap;
  justify-content: start;
  margin-right: 10px;
}
#select {
  cursor: pointer;
  width: 70px;
  height: 40px;
  border: 1px solid black;
  border-radius: 4px;
  /* 텍스트 */
  text-align: center;
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
}
#select:hover {
  border: 1px solid #409eff;
}
#select:active {
  border: 3px solid #409eff;
}
</style>
