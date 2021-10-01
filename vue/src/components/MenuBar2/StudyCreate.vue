<template>
  <div>
    <el-row :gutter="0">
      <el-col :span="5" :offset="0">
        <div class="height100">1</div>
      </el-col>
      <el-col :span="13" :offset="0">
        <div class="height100">
          <div id="h1">스터디 생성</div>
          <hr />
          <div id="box1">
            <label id="h2">스터디 이름</label>
            <input
              v-model="state.form.name"
              type="text"
              placeholder="이름을 입력하세요."
              id="name"
              onfocus="this.placeholder=''"
              onblur="this.placeholder='이름을 입력하세요'"
            />
          </div>
          <div id="box1">
            <label id="h2">기술스택</label>
            <!-- v-model="state.form.techList" -->
            <input
              type="text"
              placeholder="사용하는 기술 스택을 입력하세요."
              id="techList"
              onfocus="this.placeholder=''"
              onblur="this.placeholder='사용하는 기술 스택을 입력하세요'"
            />
          </div>
          <div id="box3">
            <div id="box2">
              <div id="box1">
                <label id="h2">일정</label>
                <input
                  v-model="state.form.schedule"
                  type="text"
                  placeholder="스터디 일정을 입력하세요"
                  id="schedule"
                  onfocus="this.placeholder=''"
                  onblur="this.placeholder='스터디 일정을 입력하세요'"
                />
              </div>
              <div id="box1">
                <label id="h2">스터디 기간(단위: 주)</label>
                <input
                  v-model="state.form.period"
                  type="text"
                  placeholder="숫자를 입력하세요"
                  id="period"
                  onfocus="this.placeholder=''"
                  onblur="this.placeholder='숫자를 입력하세요'"
                />
              </div>
              <div id="box1">
                <label id="h2">인원</label>
                <input
                  v-model="state.form.maxCount"
                  type="text"
                  placeholder="숫자를 입력하세요"
                  id="maxCount"
                  onfocus="this.placeholder=''"
                  onblur="this.placeholder='숫자를 입력하세요'"
                />
              </div>
              <div id="box1">
                <label id="h2">스터디 공개 여부</label>
                <div id="radio">
                  <!-- radio 타입은 name명이 같을 경우 하나만 선택된다. -->
                  <label>
                    <input
                      type="radio"
                      name="study"
                      value="true"
                      v-model="state.form.isPublic"
                    />
                    공개
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="study"
                      value="false"
                      v-model="state.form.isPublic"
                    />
                    비공개
                  </label>
                </div>
              </div>
            </div>
            <div id="box4">
              <label id="h2">프로필 사진 등록</label>
              <div style="width: 100%; height: 80%">
                <img class="previewImg" />
              </div>
              <el-upload :before-upload="beforeUpload">업로드</el-upload>
            </div>
          </div>
          <div id="box1">
            <label id="h2">지역</label>
            <select id="city" v-model="state.form.city">
              <option value="">지역을 선택하세요</option>
              <option value="서울">서울</option>
              <option value="부산">부산</option>
              <option value="대구">대구</option>
              <option value="인천">인천</option>
              <option value="광주">광주</option>
              <option value="대전">대전</option>
              <option value="울산">울산</option>
              <option value="세종">세종</option>
              <option value="경기">경기</option>
              <option value="강원">강원</option>
              <option value="충북">충북</option>
              <option value="충남">충남</option>
              <option value="전북">전북</option>
              <option value="전남">전남</option>
              <option value="경북">경북</option>
              <option value="경남">경남</option>
              <option value="제주">제주</option>
            </select>
          </div>

          <div id="box1">
            <label id="h2">소속 클럽</label>
            <select id="clubId" v-model="state.form.clubId">
              <!-- 내가 속한 각 클럽 목록을 받아서 뿌려줘야 함 -->

              <option
                :value="clubId[index]"
                v-for="(item, index) in clubList"
                :key="index"
              >
                {{ item }}
              </option>
              <!-- <option value="none">없음</option>
              <option value="B">B</option>
              <option value="C">C</option>
              <option value="D">D</option> -->
            </select>
          </div>
          <div id="box1">
            <label id="h2">소개</label>
            <textarea
              v-model="state.form.bio"
              type="textarea"
              placeholder="해당 스터디에 대해 소개해주세요"
              id="bio"
              onfocus="this.placeholder=''"
              onblur="this.placeholder='해당 스터디에 대해 소개해주세요'"
              maxlength="300"
              show-word-limit
            />
          </div>
          <div id="btn">
            <el-button class="btn-create" @click="goIntroduce">생성</el-button>
            <el-button class="btn-cancel" @click="goHome">취소</el-button>
          </div>
        </div>
      </el-col>
      <el-col :span="6" :offset="0">
        <div class="height100">3</div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { useRouter } from 'vue-router';
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'studyCreate',
  methods: {},
  setup() {
    const router = useRouter();
    const store = useStore();

    const mypage = store.dispatch('member/readMyPage');
    mypage.then((mypage) => {
      store.commit('setMember', mypage.data);
    });
    const user = computed(() => store.getters['member/mypageGetter']);
    // console.log(user.value);
    // console.log(user.value.city);
    // console.log(user.value.myClubList);
    // console.log(user.value.myClubList.length);
    // console.log(user.value.myClubList[0]);

    let clubList = [];
    let clubId = [];
    if (user.value.myClubList.length > 0) {
      for (let index = 0; index < user.value.myClubList.length; index++) {
        clubList[index] = user.value.myClubList[index].name;
        clubId[index] = user.value.myClubList[index].id;
      }
    } else {
      clubList[0] = '없음';
      clubId[0] = null;
    }
    // const clubList = ['사과', '배클럽', '우주', '공장'];
    // const clubId = [31, 32, 33, 34];

    // 사진 업로드
    const beforeUpload = (file) => {
      let formData = new FormData();
      formData.append('file', file);

      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function (e) {
        // var image = document.createElement('img');
        var image = document.querySelector('.previewImg');
        image.src = e.target.result; //blob 매핑
        image.width = 250;
        image.height = 200;
        image.alt = 'here should be some image';
      };

      const res = store.dispatch('uploadFile', formData);
      res.then((res) => {
        // console.log(res.data.id);
        // console.log(res.data.fileDownloadUri);
        state.form.uuid = res.data.id;
      });
    };

    const state = reactive({
      form: {
        bio: '', //소개
        city: '', //도시
        clubId: null, //소속 클럽 id
        isPublic: false, //공개 여부
        maxCount: 0, //최대 인원수
        name: '', //스터디 이름
        period: 7, //기간
        schedule: '', //일정 String
        techList: ['java', 'python'], //기술 목록
        uuid: null, //사진 uuid
      },
    });

    const goIntroduce = function () {
      console.log(state.form);
      // 값이 일치하는지 확인하고 잘못되었으면(생성이 안되면 다시 돌려보낸다?)

      store.dispatch('study/createStudy', state.form);
      router.push({ path: '/subheader/study/introduce' });
    };
    const goHome = function () {
      router.push({ path: '/nosubheader/home' });
    };

    return {
      goIntroduce,
      goHome,
      store,
      mypage,
      user,
      clubList,
      clubId,
      beforeUpload,
      state,
    };
  },
};
</script>
<style scoped>
#h1 {
  width: 184px;
  height: 52px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 36px;
  line-height: 52px;
  margin-top: 50px;
  margin-bottom: 0px;
  margin-left: 1px;
  text-align: left;
  color: #000000;
}
#h2 {
  width: 300px;
  height: 35px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 20px;
  line-height: 29px;
  margin-top: 10px;
  margin-left: 2px;
  text-align: left;
  color: #000000;
}
#input {
  width: 782px;
  height: 50px;

  background: #e8e8e8;
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  padding-left: 10px;
  margin-left: 2px;

  /* 비밀번호확인 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 16px;
  /* identical to box height, or 114% */
  text-align: left;

  color: #919191;
}
#input1 {
  width: 363px;
  height: 50px;

  background: #e8e8e8;
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  padding-left: 10px;
  margin-left: 2px;

  /* 비밀번호확인 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 16px;
  /* identical to box height, or 114% */
  text-align: left;

  color: #919191;
}
#input2 {
  width: 782px;
  height: 200px;

  background: #e8e8e8;
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  margin-left: 2px;
  padding-top: 15px;
  padding-left: 10px;

  /* 비밀번호확인 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 16px;
  /* identical to box height, or 114% */
  text-align: left;
  align-content: flex-start;
  resize: none;

  color: #919191;
}
#region {
  cursor: pointer;
  width: 794px;
  height: 52px;

  background: #e8e8e8;
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  padding-left: 10px;
  margin-left: 2px;

  /* 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 16px;
  /* identical to box height, or 114% */
  text-align: left;

  color: #919191;
}
#box1 {
  display: flex;
  flex-flow: column;
}
#box2 {
  display: flex;
  flex-flow: column;
}
#box3 {
  display: flex;
}
#box4 {
  margin-left: 60px;
  display: flex;
  flex-flow: column;
}
#btn {
  margin-top: 50px;
}
.btn-cancel {
  margin-left: 10px;
}
#radio {
  height: 40px;
  display: flex;
  vertical-align: middle;
}
.previewImg {
  max-width: 100%;
  max-height: 100%;
  object-fit: cover;
}
</style>
