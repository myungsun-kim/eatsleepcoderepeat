<template>
  <el-row style="maxheight: 85%">
    <el-col :span="3">
      element-plus tablem 일정 개수를 넘어가면 다음 페이지로 넘어가는 알고리즘
      필요
    </el-col>
    <el-col :span="18">
      <el-table :data="tableData" stripe style="width: 100%">
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
        >
        </el-table-column>
        <el-table-column prop="date" label="Date" width="100" align="center">
        </el-table-column>
      </el-table>
    </el-col>
    <el-col :span="3"></el-col>
  </el-row>
  <el-row style="height: 15%">
    <el-col :span="3"></el-col>
    <el-col :span="18"
      ><el-row style="height: 20%"> </el-row>
      <el-row style="height: 25%">
        <el-col :span="5"></el-col>
        <el-col :span="14">
          <el-pagination background layout="prev, pager, next" :total="1000">
          </el-pagination>
        </el-col>
        <el-col :span="3"> </el-col>
        <el-col :span="2">
          <el-button class="btn-1747C9 font-noto-bold" @click="goCreateNotice">
            글 쓰기
          </el-button>
        </el-col>
      </el-row>
      <el-row class="height10">여백</el-row>
      <el-row style="height: 35%">
        <el-col :span="2" :offset="4">
          <el-dropdown size="mini" split-button type="">
            제목
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>제목</el-dropdown-item>
                <el-dropdown-item>작성자</el-dropdown-item>
                <el-dropdown-item>작성일</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </el-col>
        <el-col :span="12">
          <el-input
            placeholder="검색어를 입력하세요"
            v-model="temp"
            size="mini"
          ></el-input>
        </el-col>
        <el-col :span="2">
          <el-button class="btn-ghost font-noto-bold">검색</el-button>
        </el-col>
        <el-col :span="4"></el-col>
      </el-row>
    </el-col>
    <el-col :span="3"></el-col>
    <el-col :span="3"></el-col>
  </el-row>
</template>

<script>
// import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  data() {
    return {
      tableData: [
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
      ],
    };
  },
  setup() {
    // const store = useStore();
    const router = useRouter();

    const goCreateNotice = function () {
      console.log('@');
      router.push({ path: '/subheader/notice/create' });
    };

    return {
      goCreateNotice,
    };
  },
};
</script>
