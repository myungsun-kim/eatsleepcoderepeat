<template>
  <el-row class="font-20">
    <el-col :span="3"></el-col>
    <el-col :span="3"
      ><el-row class="height1"> </el-row>
      <el-row class="height8" v-if="store.state.category == 1">
        <i class="el-icon-postcard"></i>&nbsp;스터디 이름
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-setting"></i>&nbsp;주요 기술 스택
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-date"></i>&nbsp;프로젝트 일정
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-time"></i>&nbsp;프로젝트 기간
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-star-off"></i>&nbsp;팀장
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8"> <i class="el-icon-user"></i>&nbsp;인원 </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-lock"></i>&nbsp;공개여부
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-position"></i>&nbsp;지역
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-video-play"></i>&nbsp;스터디 상태
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-folder"></i>&nbsp;소속 클럽
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-edit"></i>&nbsp;마지막 수정일
      </el-row>
    </el-col>
    <el-col :span="1"></el-col>
    <el-col :span="5"
      ><el-row class="height1"> </el-row>
      <el-row class="height8"> 백준 알고리즘 스터디 </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8"> Java, Kotlin </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8"> 주 5일 9-6시 </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8"> 7주 </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8"> MinMin </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <el-button type="text" @click="open">
          5/6명(Minsu, Sun, Jun, Kim, Min) <MemberListModal /> </el-button
      ></el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8"> 비공개 </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8"> 무관 </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8"> 진행중- 참여불가 </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <el-button type="text">SSAFY</el-button>
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8"> 21.09.07 15:38 </el-row>
      <el-row class="height1"> </el-row>
    </el-col>
    <el-col :span="1"></el-col>
    <el-col :span="8">
      <el-row class="height5"></el-row>
      <el-row style="height: 80%; background-color: red"> 사진 </el-row>
    </el-col>
    <el-col :span="3"></el-col>
  </el-row>
  <el-row class="height5"> </el-row>
  <el-row class="height50 font-20">
    <el-col :span="3"></el-col>
    <el-col :span="18">
      <el-row class="height8">
        <i class="el-icon-chat-dot-round font-20"></i>&nbsp;소개
      </el-row>
      <el-row class="height92 font-20"
        >안녕하세요! 🔥성공적인 취업 및 이직🔥를 목표로 기초부터 알고리즘 스터디
        시작하실 분들 모집합니다. 언어: 기술스택에 명시된 언어 중 편한 언어로
        진행하시면 됩니다. 알고리즘 문제 선정 방식 🤔 알고리즘은 진행하는 팀원의
        수준에 맞게 백준, 프로그래머스를 기준으로 같이 협의하여 선정합니다.
        백준기준 실버 3-4이신 분들 환영합니다. 같이 실력 키워나가요!! BOJ 매 주
        5문제씩 선정합니다. Programmers 기업별 코딩 테스트 진행 및 여러가지 특수
        상황이 있을 경우 선정하여 풀어봅니다. 진행 방식 매 주 선정한 알고리즘
        문제를 모임 전에 풀어옵니다. 주 1회 온라인 모임을 가지고, 한 명씩 코드
        설명을 합니다. 모임을 가질 요일은 스터디원이 다 모집된 후에 정하도록
        하겠습니다
      </el-row>
      <el-row>
        <el-col :span="7"></el-col>
        <el-col :span="2">
          <el-button class="btn-1747C9 font-14" @click="goUpdate">
            수정
          </el-button>
        </el-col>
        <el-col :span="2">
          <el-button class="btn-ghost-red" style="font-size: 14px">
            <StudyDeleteModal />
          </el-button>
        </el-col>
        <el-col :span="2">
          <el-button class="btn-ghost-red" style="font-size: 14px">
            <StudyQuitModal />
          </el-button>
        </el-col>
        <el-col :span="10"></el-col>
        <el-col :span="3"></el-col>
        <!-- <el-col :span="9"></el-col
        ><el-col :span="2"><el-button>신청</el-button></el-col
        ><el-col :span="1"></el-col
        ><el-col :span="2"><el-button>취소</el-button></el-col
        ><el-col :span="10"></el-col> <el-col :span="3"></el-col
      > -->
        <!-- <el-col :span="11"></el-col
        ><el-col :span="2"><el-button>탈퇴</el-button></el-col
        ><el-col :span="10"></el-col>  -->
      </el-row> </el-col
    ><el-col :span="3"></el-col>
  </el-row>
</template>
<script>
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import MemberListModal from '../Modal/MemberListModal.vue';
import StudyDeleteModal from '../Modal/StudyDeleteModal.vue';
import StudyQuitModal from '../Modal/StudyQuitModal.vue';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const store = useStore();
    const router = useRouter();

    return {
      store,
      router,
    };
  },
  components: {
    MemberListModal,
    StudyQuitModal,
    StudyDeleteModal,
  },
  setup() {
    // const store = useStore();
    const router = useRouter();

    const goUpdate = function () {
      router.push({ path: '/nosubheader/update' });
    };

    return {
      goUpdate,
    };
  },
};
</script>
