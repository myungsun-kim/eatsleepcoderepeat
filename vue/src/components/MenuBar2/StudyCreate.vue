<template>
  <div class="bg">
    <el-row :gutter="0">
      <el-col :span="6" :offset="0">
        <div class="height100"></div>
      </el-col>
      <el-col :span="12" :offset="0">
        <div>
          <div id="h1">스터디 생성</div>
          <hr />
          <div id="box1">
            <label id="h2">스터디 이름</label>
            <input
              id="name"
              class="input"
              autocomplete="off"
              type="text"
              v-model="state.form.name"
              placeholder="이름을 입력하세요."
              onfocus="this.placeholder=''"
              onblur="this.placeholder='이름을 입력하세요'"
            />
          </div>
          <div id="box1">
            <label id="h2">기술스택 (1개만 입력 가능합니다)</label>
            <div id="box5">
              <!-- v-model="state.form.techList" -->
              <div class="input"></div>
              <input
                id="techList"
                class="input"
                v-model="state.tech"
                autocomplete="off"
                @input="stackAutoComplete()"
                type="text"
                placeholder="사용하는 기술 스택을 입력하세요."
                onfocus="this.placeholder=''"
                onblur="this.placeholder='사용하는 기술 스택을 입력하세요'"
              />
              <div id="autocomplete">
                <div
                  @click="addStack1(techStack1)"
                  v-for="techStack1 in state.result"
                  class="autocomplete1"
                  style="cursor: pointer"
                  :key="techStack1"
                >
                  <span>
                    {{ techStack1 }}
                  </span>
                </div>
              </div>
            </div>
          </div>
          <div id="box3">
            <div id="box2">
              <div id="box1">
                <label id="h2">일정</label>
                <input
                  id="schedule"
                  class="input1"
                  autocomplete="off"
                  type="text"
                  v-model="state.form.schedule"
                  placeholder="스터디 일정을 입력하세요"
                  onfocus="this.placeholder=''"
                  onblur="this.placeholder='스터디 일정을 입력하세요'"
                />
              </div>
              <div id="box1">
                <label id="h2">스터디 기간(단위: 주)</label>
                <input
                  id="period"
                  class="input1"
                  autocomplete="off"
                  type="text"
                  v-model="state.form.period"
                  placeholder="숫자를 입력하세요"
                  onfocus="this.placeholder=''"
                  onblur="this.placeholder='숫자를 입력하세요'"
                />
              </div>
              <div id="box1">
                <label id="h2">인원</label>
                <input
                  id="maxCount"
                  class="input1"
                  autocomplete="off"
                  type="text"
                  v-model="state.form.maxCount"
                  placeholder="숫자를 입력하세요"
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
              <div id="thumbnail">
                <img class="previewImg" />
              </div>
              <el-upload :before-upload="beforeUpload">
                <button>사진 업로드</button>
              </el-upload>
            </div>
          </div>
          <div id="box1">
            <label id="h2">지역</label>
            <select id="city" v-model="state.form.city" class="input3">
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
            <select id="clubId" v-model="state.form.clubId" class="input3">
              <!-- 내가 속한 각 클럽 목록을 받아서 뿌려줘야 함 -->
              <option
                :value="clubId[index]"
                v-for="(item, index) in clubList"
                :key="index"
              >
                {{ item }}
              </option>
            </select>
          </div>
          <div id="box1">
            <label id="h2">소개</label>
            <textarea
              id="bio"
              class="input4"
              autocomplete="off"
              type="textarea"
              v-model="state.form.bio"
              placeholder="해당 스터디에 대해 소개해주세요."
              onfocus="this.placeholder=''"
              onblur="this.placeholder='해당 스터디에 대해 소개해주세요.'"
              maxlength="300"
            />
          </div>
          <div id="btn">
            <el-button class="btn-create" @click="goIntroduce">생성</el-button>
            <el-button class="btn-cancel" @click="goHome">취소</el-button>
          </div>
        </div>
      </el-col>
      <el-col :span="6" :offset="0">
        <div class="height100"></div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { useRouter } from 'vue-router';
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import techstacks from '@/autocomplete/techstack.js';

export default {
  name: 'studyCreate',
  methods: {},
  setup() {
    const router = useRouter();
    const store = useStore();

    store.dispatch('member/readMyPage');
    const user = computed(() => store.getters['member/mypageGetter']);
    let clubList = [];
    let clubId = [];
    if (user.value.myClubList) {
      if (user.value.myClubList.length > 0) {
        for (let index = 0; index < user.value.myClubList.length; index++) {
          clubList[index] = user.value.myClubList[index].name;
          clubId[index] = user.value.myClubList[index].id;
        }
      } else {
        clubList[0] = '없음';
        clubId[0] = null;
      }
    } else {
      clubList[0] = '없음';
      clubId[0] = null;
    }

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

      store
        .dispatch('uploadFile', formData)
        .then((res) => {
          // console.log(res.data.id);
          // console.log(res.data.fileDownloadUri);
          state.form.coverpic_uuid = res.data.id;
        })
        .catch(() => {
          alert('이미지를 불러오지 못했습니다.');
        });
    };

    const state = reactive({
      form: {
        bio: '', //소개
        city: '', // 지역
        clubId: null, //소속 클럽 id
        coverpic_uuid: null, //사진 uuid
        isPublic: true, //공개 여부
        maxCount: 0, //최대 인원수
        name: '', //스터디 이름
        techList: [], //기술 목록
        schedule: '', //일정 String
        period: '', //기간
      },
      tech: '',
      techStack: '',
      result: null,
    });

    const stackAutoComplete = function () {
      const autocomplete = document.getElementById('autocomplete');
      // state.tech에 값을 입력했을 시 자동완성목록 보여주기
      if (state.tech) {
        autocomplete.style = '';
        // techstack.js 에서 해당 글자로 시작하는 키워드들을 필터링해 state.result에 담는다.
        state.result = techstacks.filter((stack) => {
          // 해당 글자로 시작하는 키워드를 뽑아내기 위해 '^' 를 사용
          // 대소문자 구분 없이 찾아내기 위해 'i' 옵션을 사용
          return stack.match(new RegExp('^' + state.tech, 'i'));
        });
        // state.form.techList에 입력한 값이 없다면 즉시 닫히도록
        if (state.result.length <= 0) {
          autocomplete.style = 'display:none';
        }
      } else {
        autocomplete.style = 'display:none';
      }
    };
    // 자동완성 목록중에서 내가 클릭한 것을 박스에 추가하는 함수
    // 내가 클릭한 것: clickedTechStack
    const addStack1 = function (clickedTechStack) {
      const autocomplete = document.getElementById('autocomplete');
      // 회원가입할때 보낼 data값
      // 기존에 techList에 값이 없었다면 바로 넣는다.
      state.tech = clickedTechStack;
      if (!state.form.techList) {
        state.form.techList.push(clickedTechStack);
      }
      // 기존에 techList에 값이 들어있었다면 빼고 넣는다(임시로 하나만 받아야 하기 때문)
      else {
        state.form.techList.pop();
        state.form.techList.push(clickedTechStack);
      }
      autocomplete.style = 'display:none';
      console.log(state.form.techList);
    };

    const goIntroduce = function () {
      console.log(state.form, '스터디 생성 테스트!!!');
      // 값이 일치하는지 확인하고 잘못되었으면(생성이 안되면 다시 돌려보낸다?)
      store.dispatch('study/createStudy', state.form);
      router.push({ path: '/subheader/study/introduce' });
    };
    const goHome = function () {
      router.push({ path: '/nosubheader/study/home' });
    };

    return {
      store,
      state,
      user,
      beforeUpload,
      clubList,
      clubId,
      goIntroduce,
      goHome,
      stackAutoComplete,
      addStack1,
    };
  },
};
</script>
<style scoped>
.bg {
  background: #f2f2f2;
}
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
.input {
  width: 782px;
  height: 50px;

  /* background: #e8e8e8; */
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  padding-left: 10px;
  margin-left: 2px;

  /* 비밀번호확인 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  /* identical to box height, or 114% */
  text-align: left;

  color: #919191;
}
.input1 {
  width: 363px;
  height: 50px;

  /* background: #e8e8e8; */
  /* background: white; */
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  padding-left: 10px;
  margin-left: 2px;

  /*  텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  /* identical to box height, or 114% */
  text-align: left;

  color: #919191;
}
.input2 {
  width: 782px;
  height: 200px;

  /* background: #e8e8e8; */
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
  font-size: 18px;
  /* identical to box height, or 114% */
  text-align: left;
  align-content: flex-start;
  resize: none;

  color: #919191;
}
.input3 {
  cursor: pointer;
  width: 794px;
  height: 52px;

  /* background: #e8e8e8; */
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  padding-left: 10px;
  margin-left: 2px;

  /* 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  /* identical to box height, or 114% */
  text-align: left;

  color: #919191;
}
.input4 {
  width: 782px;
  height: 200px;

  /* background: #e8e8e8; */
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
  font-size: 18px;
  /* identical to box height, or 114% */
  text-align: left;
  align-content: flex-start;
  resize: none;

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
#box5 {
  display: flex;
  flex-flow: column;
}
#btn {
  margin-top: 50px;
}
.btn-cancel {
  margin-left: 10px;
}
#autocomplete {
  position: absolute;
  display: none;
  display: flex;
  flex-flow: column;

  width: 400px;
  max-height: 150px;
  margin-top: 54px;

  overflow: auto;
  margin-left: 0px;
  background: #919191;
  border-radius: 4px;
}

.autocomplete1 {
  background: white;
  /* background: #e8e8e8; */
}
.autocomplete1:hover {
  background: #3f8bfc;
  /* background: #307ff5; */
  color: white;
}
.autocomplete1:active {
  background: black;
}
#thumbnail {
  width: 100%;
  height: 60%;

  border: 0.5px dashed black;
  border-radius: 3%;
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
