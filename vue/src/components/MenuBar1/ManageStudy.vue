<template>
  <el-row style="maxheight: 85%">
    <el-col :span="3"> </el-col>
    <el-col :span="18">
      <el-table
        :data="studyApplications"
        stripe
        style="width: 100%"
        highlight-current-row
        @cell-click="cellClickEvent"
        @cell-dblclick="goInfoPage"
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
          <el-button @click="goApplicate" class=""> 지원서 보기 </el-button>
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
</template>

<script>
// import CheckApplicationModal from '../Modal/CheckApplicationModal.vue';
import MemberAcceptModal from '../Modal/MemberAcceptModal.vue';
import MemberRejectModal from '../Modal/MemberRejectModal.vue';

import { ref, computed, reactive, watch } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  components: {
    // CheckApplicationModal,
    MemberAcceptModal,
    MemberRejectModal,
  },
  setup() {
    const store = useStore();
    const router = useRouter();
    // 스터디 ID 가져오기
    const studyId = computed(() => store.getters['study/studyIdGetter']);
    console.log('studyId: ' + studyId.value);

    // 해당 스터디의 모든 신청서 가져오기
    store.dispatch('study/applicationAll', studyId.value);

    const studyApplications = computed(
      () => store.getters['study/studyApplicationsGetter']
    );

    const goCreateNotice = function () {
      router.push({ path: '/subheader/notice/create' });
    };

    const state = reactive({
      form: {
        studyId: studyId.value,
        memberId: '',
        email: '',
        nickname: '',
      },
    });

    const cellClickEvent = function (val) {
      console.log('셀 클릭');
      console.log(val);
      // 선택한 회원의 memberId 정보 저장
      store.commit('study/updateStudyMemberId', val.memberId);
      //선택한 회원의 이메일 정보 저장
      store.commit('member/updateUserEmail', val.email);
      //선택한 회원의 닉네임 정보 저장
      store.dispatch('study/updateStudyMemberNickname', val.nickname);
      state.form.email = val.email;
      state.form.nickname = val.nickname;
      state.form.memberId = val.memberId;

      //선택한 회원의 지원서 저장하기
      store.dispatch('study/getStudyApplicationOne', state.form);
      // router.push({ path: '/subheader/notice/detail' });
      // const tmp = computed(
      //   () => store.getters['member/studyApplicationGetter']
      // );
    };



    // //해당 지원자 신청서 가져오기
    // const application = computed(
    //   () => store.getters['member/studyApplicationGetter']
    // );
    // console.log(application.value);


    // 해당 회원의 정보 페이지로 이동
    const goInfoPage = function (val) {
      console.log('GO INFO PAGE');
      // 선택한 회원의 memberId 정보 저장
      store.commit('study/updateStudyMemberId', val.memberId);
      state.form.memberId = val.memberId;
      // 선택한 회원의 이메일 정보 저장
      store.commit('member/updateUserEmail', val.email);
      console.log('이메일정보');
      console.log(val.email);
      // 선택한 회원의 닉네임 정보 저장
      store.dispatch('study/updateStudyMemberNickname', val.nickname);
      state.form.nickname = val.nickname;

      // 선택한 회원의 정보 저장
      store.dispatch('member/readInfoPage', val.email);
      router.push({ path: '/nosubheader/readinfopage' });
    };

    // 해당 회원의 지원서 조회
    const goApplicate = function () {
      console.log('이동부터하니 ...');

      setTimeout(function () {
        router.push({ path: '/subheader/study/application' });
      }, 100);
      // router.push({ path: '/subheader/study/application' });
    };
    const event = function () {
      // 게시글 클릭 이벤트 하려고 했으나 실패함.
      console.log('click one item@');
    };
    const clcikEvent = function () {
      console.log('CLICK EVENT');
    };
    return {
      store,
      router,
      state,
      studyApplications,
      goCreateNotice,
      cellClickEvent,
      // tableData,
      goApplicate,
      event,
      clcikEvent,
      goInfoPage,
    };
  },
};
</script>
