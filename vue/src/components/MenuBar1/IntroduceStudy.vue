<template>
  <el-row class="font-20">
    <el-col :span="3"></el-col>
    <el-col :span="3"
      ><el-row class="height1"> </el-row>
      <el-row class="height8" v-if="store.state.category == 1">
        <i class="el-icon-postcard flex-items"></i>&nbsp;스터디 이름
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-setting flex-items"></i>&nbsp;주요 기술 스택
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-date flex-items"></i>&nbsp;프로젝트 일정
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-time flex-items"></i>&nbsp;프로젝트 기간
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-star-off flex-items"></i>&nbsp;팀장
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-user flex-items"></i>&nbsp;인원
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-lock flex-items"></i>&nbsp;공개여부
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-position flex-items"></i>&nbsp;지역
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-video-play flex-items"></i>&nbsp;스터디 상태
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-folder flex-items"></i>&nbsp;소속 클럽
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <i class="el-icon-edit flex-items"></i>&nbsp;마지막 수정일
      </el-row>
    </el-col>
    <el-col :span="1"></el-col>
    <el-col :span="5"
      ><el-row class="height1"> </el-row>
      <el-row class="height8"> {{ studyIntroduce.name }}</el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <div v-for="(tech, index) in studyIntroduce.techList" :key="index">
          {{ tech }}&nbsp;
        </div>
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8"> {{ studyIntroduce.schedule }} </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8"> {{ studyIntroduce.period }}주 </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8"> {{ studyIntroduce.host.nickname }} </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <MemberListModal />
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <div v-if="studyIntroduce.isPublic">공개</div>
        <div v-else>비공개</div>
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8"> {{ studyIntroduce.city }} </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <div v-if="studyIntroduce.status == '종료'">종료</div>
        <div v-else-if="studyIntroduce.status == '모집'">모집 중</div>
        <div v-else>
          진행 중 - 참여&nbsp;
          <div v-if="studyIntroduce.isParticipate">가능</div>
          <div v-else>불가</div>
        </div>
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        <div v-if="studyIntroduce.club">{{ studyIntroduce.club }}</div>
        <div v-else>없음</div>
      </el-row>
      <el-row class="height1"> </el-row>
      <el-row class="height8">
        {{ studyIntroduce.modifyDate.substr(2, 8) }}&nbsp;
        {{ studyIntroduce.modifyDate.substr(11, 8) }}
        <!-- ???? 왜 역으로 가야되는거지 -->
      </el-row>
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
        <i class="el-icon-chat-dot-round font-20 flex-items"></i>&nbsp;소개
      </el-row>
      <el-row class="height92 font-20">
        {{ studyIntroduce.bio }}
      </el-row>
      <el-row>
        <el-col :span="7"></el-col>
        <el-col :span="2">
          <el-button class="btn-1747C9 font-14" @click="goUpdate">
            수정
          </el-button>
        </el-col>
        <el-col :span="2">
          <StudyDeleteModal />
        </el-col>
        <el-col :span="2">
          <StudyQuitModal />
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
      </el-row>
    </el-col>
    <el-col :span="3"></el-col>
  </el-row>
</template>
<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import MemberListModal from '../Modal/MemberListModal.vue';
import StudyDeleteModal from '../Modal/StudyDeleteModal.vue';
import StudyQuitModal from '../Modal/StudyQuitModal.vue';

export default {
  setup() {
    const store = useStore();
    const router = useRouter();
    const studyId = computed(() => store.getters['study/studyIdGetter']);
    console.log('studyId: ' + studyId.value);
    store.dispatch('study/introduce', studyId.value);
    const studyIntroduce = computed(
      () => store.getters['study/studyIntroduceGetter']
    );
    console.log('studyIntroduce: ' + studyIntroduce.value);

    // const state = reactive({
    //   form: {
    //     name: studyIntroduce.value.name, //스터디 이름
    //     techList: ['java', 'python'], //기술 목록
    //     schedule: '', //일정 String
    //     period: 7, //기간
    //     host: {}, //팀장
    //     memberCount: 0, //현재 인원수
    //     maxCount: 0, //최대 인원수
    //     memberDtos: [], //인원 정보
    //     isPublic: false, //공개 여부
    //     city: '', //지역
    //     isParticipate: '', //스터디 상태(참여 가능/불가)
    //     club: null, //소속 클럽 id
    //     modifyDate: '', //마지막 수정일
    //     bio: '', //소개
    //     dbFile: null, //사진 file
    //   },
    // });

    const goUpdate = function () {
      router.push({ path: '/nosubheader/study/update' });
    };
    return {
      store,
      router,
      studyIntroduce,
      goUpdate,
    };
  },
  components: {
    MemberListModal,
    StudyQuitModal,
    StudyDeleteModal,
  },
};
</script>
