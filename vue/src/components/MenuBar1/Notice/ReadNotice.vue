<template>
  <div class="all">
    <el-row style="maxheight: 85%">
      <el-col :span="3">
        element-plus tablem 일정 개수를 넘어가면 다음 페이지로 넘어가는 알고리즘
        필요
      </el-col>
      <el-col :span="18">
        <el-table
          :data="tableData"
          stripe
          style="width: 100%"
          highlight-current-row
          @current-change="handleCurrentChange"
        >
          <el-table-column
            prop="tag"
            label="Tag"
            width="100"
            align="center"
          ></el-table-column>
          <el-table-column prop="title" label="Title" width="" align="center">
          </el-table-column>
          <el-table-column
            prop="author"
            label="Author"
            width="100"
            align="center"
            @click="event"
          >
          </el-table-column>
          <el-table-column prop="date" label="Date" width="100" align="center">
          </el-table-column>
        </el-table>
      </el-col>
      <el-col :span="3"></el-col>
    </el-row>

    <!-- pagination -->
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
          <el-col :span="2" :offset="4">
            <select id="select" v-model="state.select">
              <option value="" class="option1">제목</option>
              <option value="작성자" class="option1">작성자</option>
              <option value="작성일" class="option1">작성일</option>
            </select>
          </el-col>
          <el-col :span="12">
            <el-input
              placeholder="검색어를 입력하세요"
              v-model="temp"
              size="middle"
            ></el-input>
          </el-col>
          <el-col :span="2">
            <el-button class="btn-ghost font-noto-bold" id="button1"
              >검색</el-button
            >
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
// import { useStore } from 'vuex';
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';

export default {
  setup() {
    // const store = useStore();
    const router = useRouter();
    const currentRow = ref('1');
    const state = reactive({
      form: {},
      select: '',
    });
    const goCreateNotice = function () {
      router.push({ path: '/subheader/notice/create' });
    };

    const handleCurrentChange = function (val) {
      this.currentRow.value = val;
      console.log('click one item@');
    };
    const event = function () {
      // 게시글 클릭 이벤트 하려고 했으나 실패함.
      console.log('click one item@');
    };

    const tableData = [
      {
        tag: '공지사항',
        title: '안녕하세요',
        author: '민수',
        date: '2016-05-03',
      },
      {
        tag: '공지사항',
        title: '안녕하세요',
        author: '민수',
        date: '2016-05-03',
      },
      {
        tag: '공지사항',
        title: '안녕하세요',
        author: '민수',
        date: '2016-05-03',
      },
      {
        tag: '공지사항',
        title: '안녕하세요',
        author: '민수',
        date: '2016-05-03',
      },
    ];
    return {
      router,
      state,
      goCreateNotice,
      handleCurrentChange,
      tableData,
      currentRow,
      event,
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
#button1 {
  font-size: 15px;
  width: 70px;
  height: 30px;
}
.footer {
  align-items: center;
}
#select {
  cursor: pointer;
  width: 70px;
  height: 36px;
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
  background: #409eff;
}
</style>
