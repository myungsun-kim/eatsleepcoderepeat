<template>
  <el-row style="height: 15%">
    <el-col :span="3"></el-col>
    <el-col :span="18">
      회원목록테이블
      <el-row>
        <el-col :span="4"></el-col>
        <el-col :span="2">리스트</el-col>
        <el-col :span="10"></el-col>
        <el-col :span="2"></el-col>
        <el-col :span="2"></el-col>
        <el-col :span="4"></el-col>
      </el-row>
    </el-col>
    <el-col :span="3"></el-col>
  </el-row>

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
        @cell-click="handleCurrentChange2"
      >
        <el-table-column
          prop="nickname"
          label="닉네임"
          width="100"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="application"
          label="신청서"
          width=""
          align="center"
        >
          <CheckApplicationModal />
        </el-table-column>
        <el-table-column
          prop="manage"
          label="수락 여부"
          width="300"
          align="center"
          @click="event"
        >
          <MemberAcceptModal />
          <MemberRejectModal />
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
        <el-col :span="5"></el-col><el-col :span="14">페이지네이션</el-col
        ><el-col :span="3"></el-col><el-col :span="2"></el-col>
      </el-row>
      <el-row class="height10"> </el-row>
      <el-row style="height: 35%">
        <el-col :span="4"></el-col>
        <el-col :span="2">리스트</el-col>
        <el-col :span="10"></el-col>
        <el-col :span="2"></el-col>
        <el-col :span="2"></el-col>
        <el-col :span="4"></el-col>
      </el-row>
      <el-row class="height10"> </el-row>
    </el-col>
    <el-col :span="3"></el-col><el-col :span="3"></el-col
  ></el-row>
</template>

<script>
import CheckApplicationModal from '../Modal/CheckApplicationModal.vue';
import MemberAcceptModal from '../Modal/MemberAcceptModal.vue';
import MemberRejectModal from '../Modal/MemberRejectModal.vue';

import { ref, computed } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  components: {
    CheckApplicationModal,
    MemberAcceptModal,
    MemberRejectModal,
  },
  setup() {
    const store = useStore();
    const router = useRouter();
    // 스터디 ID 가져오기
    const studyId = computed(() => store.getters['study/studyIdGetter']);
    console.log('studyId: ' + studyId.value);

    store.dispatch('study/applicationAll', studyId.value);
    const studyApplications = computed(
      () => store.getters['study/studyApplicationsGetter']
    );
    console.log('studyApplications: ' + studyApplications.value);

    const currentRow = ref('1');

    const goCreateNotice = function () {
      router.push({ path: '/subheader/notice/create' });
    };

    const handleCurrentChange = function (val) {
      // this.currentRow.value = val;
      console.log('click one item@');
      console.log(val);
      // router.push({ path: '/subheader/notice/detail' });
    };
    const handleCurrentChange2 = function (row, col, cell, event) {
      // this.currentRow.value = val;
      console.log('2222222222222');
      console.log(row);
      console.log(col);
      console.log(cell);
      console.log(event);
      // router.push({ path: '/subheader/notice/detail' });
    };
    const event = function () {
      // 게시글 클릭 이벤트 하려고 했으나 실패함.
      console.log('click one item@');
    };
    const clcikEvent = function () {
      console.log('CLICK EVENT');
    };

    const tableData = [
      {
        nickname: '공지사항',
      },
      {
        nickname: '공지사항',
      },
    ];
    return {
      store,
      router,
      studyApplications,
      goCreateNotice,
      handleCurrentChange,
      handleCurrentChange2,
      tableData,
      currentRow,
      event,
      clcikEvent,
    };
  },
};
</script>
