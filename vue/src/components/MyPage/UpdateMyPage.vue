<template>
  <div class="bg">
    <el-row class="height100">
      <el-col :span="7" :offset="0">
        <div class="height100"></div>
      </el-col>
      <el-col :span="10" :offset="0">
        <div>
          <div id="h1">마이페이지 수정</div>
          <hr />
          <div class="box1">
            <div class="box2">
              <div class="profile">
                <!-- <img class="previewImg" /> -->
                <img class="previewImg" src="../../assets/Item/basic.png" />
              </div>
              <el-upload :before-upload="beforeProfileUpload" class="upload">
                업로드
              </el-upload>
            </div>
            <div class="box3">
              <div class="box4">
                <div class="label1">이메일</div>
                <input
                  type="text"
                  id="email"
                  class="input1"
                  v-model="state.form.email"
                  readonly
                />
              </div>
              <div class="box4">
                <div class="label1">이름</div>
                <input type="text" class="input1" v-model="state.form.name" />
              </div>
              <div class="box4">
                <div class="label1">닉네임</div>
                <input
                  type="text"
                  class="input1"
                  v-model="state.form.nickname"
                />
              </div>
              <div class="box4">
                <div class="label1">역할</div>
                <input
                  type="text"
                  class="input1"
                  v-model="state.form.position"
                />
              </div>
              <div class="box4">
                <div class="label1">지역</div>
                <input type="text" class="input1" v-model="state.form.city" />
              </div>
            </div>
            <!-- <el-upload
            ref="upload"
            class="upload-demo"
            drag
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :file-list="fileList"
            :before-upload="beforeUpload">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              파일을 드래그 하거나
              <br />
              <em>클릭해서 업로드 하세요</em>
            </div>
            </el-upload> -->
          </div>
          <div class="box5">
            <div class="box4">
              <div class="label2">새 비밀번호</div>
              <input
                type="password"
                class="input2"
                v-model="state.form.password"
              />
            </div>
            <div class="box4">
              <div class="label2">새 비밀번호 확인</div>
              <input
                type="password"
                class="input2"
                v-model="state.affirmPassword"
              />
            </div>
            <div class="box4">
              <div class="label2">github</div>
              <input
                type="text"
                class="input2"
                v-model="state.form.snsHashMap.github"
              />
            </div>
            <div class="box4">
              <div class="label2">twitter</div>
              <input
                type="text"
                class="input2"
                v-model="state.form.snsHashMap.twitter"
              />
            </div>
            <div class="box4">
              <div class="label2">facebook</div>
              <input
                type="text"
                class="input2"
                v-model="state.form.snsHashMap.facebook"
              />
            </div>
            <div class="box4">
              <div class="label2">baekjoon</div>
              <input
                type="text"
                class="input2"
                v-model="state.form.snsHashMap.backjoon"
              />
            </div>
            <div class="box4">
              <div class="label2">포트폴리오</div>
              <div class="link0">
                <a :href="`${state.form.portfolio}`" class="link1">
                  포트폴리오
                  <!-- {{ state.form.portfolio }} -->
                </a>
                <el-upload :before-upload="beforePortUpload" class="link2">
                  업로드
                </el-upload>
              </div>
            </div>
            <div class="box4">
              <div class="label2">url</div>
              <input
                type="text"
                class="input2"
                v-model="state.form.portfolio_uri"
              />
            </div>
            <div class="box4">
              <div class="label2">Experienced</div>
              <div class="box8">
                <div
                  v-for="techStack in state.form.expTechList"
                  :key="techStack"
                  class="addValue1"
                  @click="deleteStack1(techStack)"
                >
                  {{ techStack }}
                </div>
              </div>
            </div>
            <div class="box4">
              <div class="box6">
                <label class="label3">(최대5개)(영어로 입력)</label>
                <input
                  type="text"
                  id="stack1"
                  v-model="state.exp"
                  autocomplete="off"
                  @input="stackAutoComplete()"
                  placeholder="기술스택을 입력하세요."
                  onfocus="this.placeholder=''"
                  onblur="this.placeholder='기술스택을 입력하세요.'"
                />
                <div id="autocomplete">
                  <div
                    class="autocomplete1"
                    v-for="techStack1 in state.result"
                    @click="addStack1(techStack1)"
                    style="cursor: pointer"
                    :key="techStack1"
                  >
                    <span>
                      {{ techStack1 }}
                    </span>
                  </div>
                </div>
              </div>
              <div class="box7">
                <div id="warning1" style="display: none">
                  값을 입력해주세요.
                </div>
                <div id="warning2" style="display: none">
                  유효하지 않은 기술스택입니다.
                </div>
                <div id="warning3" style="display: none">
                  이미 포함되어 있습니다.
                </div>
                <div id="warning4" style="display: none">
                  최대 5개까지 가능합니다.
                </div>
              </div>
            </div>
            <div class="box4">
              <div class="label2">Beginner</div>
              <div class="box8">
                <div
                  v-for="techStack in state.form.beginTechList"
                  :key="techStack"
                  class="addValue2"
                  @click="deleteStack2(techStack)"
                >
                  {{ techStack }}
                </div>
              </div>
            </div>
            <div class="box4">
              <div class="box6">
                <label class="label3">(최대5개)(영어로 입력)</label>
                <input
                  type="text"
                  id="stack2"
                  v-model="state.beg"
                  autocomplete="off"
                  @input="stackAutoComplete1()"
                  placeholder="기술스택을 입력하세요."
                  onfocus="this.placeholder=''"
                  onblur="this.placeholder='기술스택을 입력하세요.'"
                />
                <div id="autocomplete1">
                  <div
                    @click="addStack2(techStack2)"
                    v-for="techStack2 in state.result1"
                    class="autocomplete1"
                    style="cursor: pointer"
                    :key="techStack2"
                  >
                    <span>
                      {{ techStack2 }}
                    </span>
                  </div>
                </div>
              </div>
              <div id="box7">
                <div id="warning5" style="display: none">
                  값을 입력해주세요.
                </div>
                <div id="warning6" style="display: none">
                  유효하지 않은 기술스택입니다.
                </div>
                <div id="warning7" style="display: none">
                  이미 포함되어 있습니다.
                </div>
                <div id="warning8" style="display: none">
                  최대 5개까지 가능합니다.
                </div>
              </div>
            </div>
            <div class="box4">
              <div class="label2">세부 포지션</div>
              <div class="box8">
                <div
                  v-for="detailPosition in state.form.dpositionList"
                  :key="detailPosition"
                  class="addValue1"
                  @click="deletePosition1(detailPosition)"
                >
                  {{ detailPosition }}
                </div>
              </div>
            </div>
            <div class="box4">
              <div class="box6">
                <label class="label3">(최대5개)</label>
                <input
                  v-model="state.dp"
                  type="text"
                  placeholder="세부 포지션을 입력하세요."
                  id="stack3"
                  onfocus="this.placeholder=''"
                  onblur="this.placeholder='세부 포지션을 입력하세요'"
                  @keyup.enter="addPosition()"
                />
              </div>
              <div id="box5">
                <div id="warning9" style="display: none">
                  값을 입력해주세요.
                </div>
                <div id="warning10" style="display: none">
                  이미 포함되어 있습니다.
                </div>
                <div id="warning11" style="display: none">
                  최대 5개까지 가능합니다.
                </div>
              </div>
            </div>
            <div class="box4">
              <div class="label2">자기소개</div>
              <textarea
                type="textarea"
                class="input3"
                v-model="state.form.bio"
                maxlength="300"
              />
            </div>
          </div>
          <div>
            <el-button class="btn-ghost-round-blue" @click="updateMember"
              >수정
            </el-button>
            <el-button class="btn-ghost-round" @click="goReadMyPage"
              >취소
            </el-button>
          </div>
        </div>
      </el-col>
      <el-col :span="7" :offset="0">
        <div class="height100"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { computed, watch, reactive, onBeforeMount } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import techstacks from '@/autocomplete/techstack.js';

export default {
  setup() {
    const store = useStore();
    const router = useRouter();
    // const res = store.dispatch('member/readMyPage');

    // res.then((res) => {
    //   store.state.user = res.data;
    // });
    // const user = computed(() => store.getters['member/mypageGetter']);

    const user = computed(() => store.getters['member/mypageGetter']);

    watch(user, () => {
      store.dispatch('member/readMyPage');
    });

    console.log('유저정보');
    console.log(user);
    console.log(user.value);
    // user.value.dpositionList의 형태가 {0, {"id"= 1, "name"="프론트앤드"}}처럼 들어오기때문에
    // 해당 객체를 반복문으로 돌면서 name의 value값만 모아서 dpList1에 저장
    let dpList = Object.values(user.value.dpositionList);
    let dpList1 = [];
    dpList.forEach(function (item) {
      dpList1.push(item.name);
    });

    const state = reactive({
      form: {
        beginAddTechList: [], //추가할 beginner 기술 리스트
        beginDelTechList: [], //삭제할 beginner 기술 리스트
        expAddTechList: [], //추가할 Experienced 기술 리스트
        expDelTechList: [], //삭제할 Experienced 기술 리스트
        dpositionAddList: [], //추가할 세부 포지션
        dpositionDelList: [], //삭제할 세부 포지션
        // cover_pic: user.value.cover_pic, //프로필 사진 url
        email: user.value.email, //아이디=메일
        name: user.value.name, //이름
        nickname: user.value.nickname, //별명
        position: user.value.position, //역할
        password: '', //비밀번호
        city: user.value.city, //거주지
        snsHashMap: {
          //sns 아이디
          github: '',
          twitter: '',
          facebook: '',
          backjoon: '',
        },
        // portfolio: user.value.portfolio, //포트폴리오 다운로드 주소
        // portfolio_uuid: '', //포트폴리오 id
        portfolio_uri: user.value.portfolio_uri, //포트폴리오 주소
        expTechList: user.value.expTechList, //Experinced
        beginTechList: user.value.beginTechList, //beginner
        dpositionList: dpList1, //세부 포지션
        bio: user.value.bio, // 자기소개
        tel: '', //연락처
      },
      exp: '',
      beg: '',
      dp: '',
      result: '',
      result1: '',
      affirmPassword: '',
    });

    onBeforeMount(() => {
      console.log(user.value.portfolio);
      console.log(user.value.portfolio_uuid);
      // console.log(user.value.cover_pic);
      console.log(user.value.name);
      console.log(state.form.dpositionList);

      // 유저 snsList에서 snsName에 따라 snsAccount 계정 설정
      console.log(user.value.snsList.length);
      for (var i = 0; i < user.value.snsList.length; i++) {
        if (user.value.snsList[i].snsName == 'github') {
          state.form.snsHashMap.github = user.value.snsList[i].snsAccount;
        } else if (user.value.snsList[i].snsName == 'twitter') {
          state.form.snsHashMap.twitter = user.value.snsList[i].snsAccount;
        } else if (user.value.snsList[i].snsName == 'facebook') {
          state.form.snsHashMap.facebook = user.value.snsList[i].snsAccount;
        } else if (user.value.snsList[i].snsName == 'backjoon') {
          state.form.snsHashMap.backjoon = user.value.snsList[i].snsAccount;
        }
      }
      console.log(user.value, '@@@@@@@@@@@@@@@@@@@');
    });

    // 프로필 사진 업로드
    const beforeProfileUpload = (file) => {
      let formData = new FormData();
      formData.append('file', file);

      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function (e) {
        // var image = document.createElement('img');
        console.log('파일리더');
        console.log(e);
        var image = document.querySelector('.previewImg');
        console.log('이미지');
        console.log(image);
        console.log('blob');
        console.log(e.target.result);
        image.src = e.target.result; //blob 매핑
        image.width = 250;
        image.height = 200;
        image.alt = 'here should be some image';
        // document.body.appendChild(image);
      };

      console.log('ddd');
      console.log(typeof formData);
      const res = store.dispatch('uploadFile', formData);

      res.then((res) => {
        console.log('then');
        console.log(res.data);
        console.log(res.data.fileDownloadUri);
        // readURL(this.uploadImageFile);
        console.log('reader');
        state.form.cover_pic = res.data.id;
      });
      console.log('onfile');
    };

    // 포트폴리오 파일 업로드
    const beforePortUpload = (file) => {
      let formData = new FormData();
      formData.append('file', file);

      const res = store.dispatch('uploadFile', formData);

      res.then((res) => {
        console.log('vv');
        console.log(res.data.fileDownloadUri);
        console.log(res.data.id);
        state.form.portfolio = res.data.fileDownloadUri;
        // state.form.portfolio_uuid = res.data.fileDownloadUri;
        state.form.portfolio_uuid = res.data.id;
      });
    };

    // 박스에 요소 추가(Experience)
    const addEx = function () {
      var warning1 = document.getElementById('warning1');
      var warning2 = document.getElementById('warning2');
      var warning3 = document.getElementById('warning3');
      var warning4 = document.getElementById('warning4');

      // 입력한 값이 유효한 기술스택 목록에 있을 경우
      if (techstacks.includes(state.exp)) {
        // 값을 입력하지 않았고 추가되어있는 techStack이 5개 미만일떄
        if (!state.exp && state.form.expTechList.length < 5) {
          warning1.style = '';
          warning3.style = 'display:none';
          warning4.style = 'display:none';
        }
        // 값을 입력하지 않았고 추가되어있는 techStack이 5개 이상일떄
        else if (!state.exp && state.form.expTechList.length >= 5) {
          warning1.style = '';
          warning3.style = 'display:none';
          warning4.style = '';
        }
        // 기술스택 개수가 5개미만이고 추가되어있는 techStack일때
        else if (
          state.form.expTechList.length < 5 &&
          state.form.expTechList.includes(state.exp)
        ) {
          warning1.style = 'display:none';
          warning3.style = '';
          warning4.style = 'display:none';
        }
        // 기술스택 개수가 5개이상이고 추가되어있지 않은 techStack일때
        else if (
          state.form.expTechList.length >= 5 &&
          !state.form.expTechList.includes(state.exp)
        ) {
          warning1.style = 'display:none';
          warning3.style = 'display:none';
          warning4.style = '';
        }
        // 기술스택 개수가 5개 이상이고 추가되어있는 techStack일때
        else if (
          state.form.expTechList.length >= 5 &&
          state.form.expTechList.includes(state.exp)
        ) {
          warning1.style = 'display:none';
          warning3.style = '';
          warning4.style = '';
        }
        // 기술스택 개수가 5개 미만이고 추가되어있지 않은 techStack일때
        else {
          // 회원가입할때 보낼 data값
          state.form.expTechList.push(state.exp);
          console.log(state.form, 'exp찍을거임!');
          warning1.style = 'display:none';
          warning2.style = 'display:none';
          warning3.style = 'display:none';
          warning4.style = 'display:none';
          state.exp = '';
        }
      }
      // 입력한 값이 유효한 기술스택 목록에 없을 경우
      else {
        warning2.style = '';
      }
    };

    // exp자동완성 목록을 보여주는 함수
    const stackAutoComplete = function () {
      // const autocomplete = document.querySelector('.autocomplete');
      const autocomplete = document.getElementById('autocomplete');
      if (state.exp) {
        // autocomplete.classList.remove('disabled');
        autocomplete.style = '';
        // techstack.js 에서 해당 글자로 시작하는 키워드들을 필터링해 state.result에 담는다.
        state.result = techstacks.filter((stack) => {
          // 해당 글자로 시작하는 키워드를 뽑아내기 위해 '^' 를 사용
          // 대소문자 구분 없이 찾아내기 위해 'i' 옵션을 사용
          return stack.match(new RegExp('^' + state.exp, 'i'));
        });
      } else {
        // autocomplete.classList.add('disabled');
        autocomplete.style = 'display:none';
      }
    };

    // exp 자동완성 목록중에서 내가 클릭한 것을 input에 추가하는 함수
    // 내가 클릭한 것: clickedTechStack
    const addStack1 = function (clickedTechStack) {
      const autocomplete = document.getElementById('autocomplete');
      const warning2 = document.getElementById('warning2');
      const warning3 = document.getElementById('warning3');
      const warning4 = document.getElementById('warning4');

      // 기술스택 개수가 5개미만이고 추가되어있는 techStack일때
      if (
        state.form.expTechList.length < 5 &&
        state.form.expTechList.includes(clickedTechStack)
      ) {
        warning2.style = 'display:none';
        warning3.style = '';
        warning4.style = 'display:none';
      }
      // 기술스택 개수가 5개이상이고 추가되어있지 않은 techStack일때
      else if (
        state.form.expTechList.length >= 5 &&
        !state.form.expTechList.includes(clickedTechStack)
      ) {
        warning2.style = 'display:none';
        warning3.style = 'display:none';
        warning4.style = '';
      }
      // 기술스택 개수가 5개 이상이고 추가되어있는 techStack일때
      else if (
        state.form.expTechList.length >= 5 &&
        state.form.expTechList.includes(clickedTechStack)
      ) {
        warning2.style = 'display:none';
        warning3.style = '';
        warning4.style = '';
      }
      // 기술스택 개수가 5개 미만이고 추가되어있지 않은 techStack일때
      else {
        // 내가 추가할 값이 state.expDelTechList에 있다면,
        if (state.form.expDelTechList.includes(clickedTechStack)) {
          // 해당 리스트에서 뺀 후
          state.form.expDelTechList = state.form.expDelTechList.filter(
            (techStack) => techStack !== clickedTechStack
          );
          // state.form.expAddTechList에 추가한다.
          state.form.expAddTechList.push(clickedTechStack);
        }
        // 내가 추가할 값이 state.form.expDelTechList에 없다면
        else {
          // state.form.expAddTechList에 해당 값을 추가한다.
          state.form.expAddTechList.push(clickedTechStack);
        }
        // box에 보여줄 요소 Data 처리
        // 회원가입할때 보낼 data값
        state.form.expTechList.push(clickedTechStack);
        console.log(`${state.form.expTechList}이 추가되었다!`);

        warning2.style = 'display:none';
        warning3.style = 'display:none';
        warning4.style = 'display:none';
        autocomplete.style = 'display:none';
        state.exp = '';
        console.log(state.form.expAddTechList, 'exp추가할 목록');
        console.log(state.form.expDelTechList, 'exp삭제할 목록');
      }
    };

    const deleteStack1 = (clickedTechStack) => {
      // 내가 삭제할 값이 expAddTechList에 있다면
      if (state.form.expAddTechList.includes(clickedTechStack)) {
        // 해당 리스트에서 빼고
        state.form.expAddTechList = state.form.expAddTechList.filter(
          (techStack) => techStack !== clickedTechStack
        );
        // expDelTechList에 추가한다.
        state.form.expDelTechList.push(clickedTechStack);
      }
      // 내가 삭제할 값이 expAddTechList에 없다면 expDelTechList에 추가한다.
      else {
        state.form.expDelTechList.push(clickedTechStack);
      }
      // box에 보여줄 요소 Data처리
      state.form.expTechList = state.form.expTechList.filter(
        (techStack) => techStack !== clickedTechStack
      );
      console.log(state.form.expAddTechList, 'exp추가할 목록');
      console.log(state.form.expDelTechList, 'exp삭제할 목록');
    };

    // Step3 박스에 요소 추가(Beginner)
    const addBe = function () {
      var warning5 = document.getElementById('warning5');
      var warning6 = document.getElementById('warning6');
      var warning7 = document.getElementById('warning7');
      var warning8 = document.getElementById('warning8');

      // 입력한 값이 유효한 기술스택 목록에 있을 경우
      if (techstacks.includes(state.beg)) {
        // 값을 입력하지 않았고 추가되어있는 techStack이 5개 미만일떄
        if (!state.beg && state.form.beginTechList.length < 5) {
          warning5.style = '';
          warning7.style = 'display:none';
          warning8.style = 'display:none';
        }
        // 값을 입력하지 않았고 추가되어있는 techStack이 5개 이상일떄
        else if (!state.beg && state.form.beginTechList.length >= 5) {
          warning5.style = '';
          warning7.style = 'display:none';
          warning8.style = '';
        }
        // 기술스택 개수가 5개미만이고 추가되어있는 techStack일때
        else if (
          state.form.beginTechList.length < 5 &&
          state.form.beginTechList.includes(state.beg)
        ) {
          warning5.style = 'display:none';
          warning7.style = '';
          warning8.style = 'display:none';
        }
        // 기술스택 개수가 5개이상이고 추가되어있지 않은 techStack일때
        else if (
          state.form.beginTechList.length >= 5 &&
          !state.form.beginTechList.includes(state.beg)
        ) {
          warning5.style = 'display:none';
          warning7.style = 'display:none';
          warning8.style = '';
        }
        // 기술스택 개수가 5개 이상이고 추가되어있는 techStack일때
        else if (
          state.form.beginTechList.length >= 5 &&
          state.form.beginTechList.includes(state.beg)
        ) {
          warning5.style = 'display:none';
          warning7.style = '';
          warning8.style = '';
        }
        // 기술스택 개수가 5개 미만이고 추가되어있지 않은 techStack일때
        else {
          // 회원가입할때 보낼 data값
          state.form.beginTechList.push(state.beg);
          console.log(state.form, 'exp찍을거임!');
          warning5.style = 'display:none';
          warning6.style = 'display:none';
          warning7.style = 'display:none';
          warning8.style = 'display:none';
          state.beg = '';
        }
      }
      // 입력한 값이 유효한 기술스택 목록에 없을 경우
      else {
        warning6.style = '';
      }
    };

    // Beg자동완성 목록을 보여주는 함수
    const stackAutoComplete1 = function () {
      // const autocomplete = document.querySelector('.autocomplete');
      const autocomplete = document.getElementById('autocomplete1');
      if (state.beg) {
        // autocomplete.classList.remove('disabled');
        autocomplete.style = '';
        // techstack.js 에서 해당 글자로 시작하는 키워드들을 필터링해 state.result에 담는다.
        state.result1 = techstacks.filter((stack) => {
          // 해당 글자로 시작하는 키워드를 뽑아내기 위해 '^' 를 사용
          // 대소문자 구분 없이 찾아내기 위해 'i' 옵션을 사용
          return stack.match(new RegExp('^' + state.beg, 'i'));
        });
      } else {
        // autocomplete.classList.add('disabled');
        autocomplete.style = 'display:none';
      }
    };

    // beg자동완성 목록중에서 내가 클릭한 것을 박스에 추가하는 함수
    // 내가 클릭한 것: clickedTechStack
    const addStack2 = function (clickedTechStack) {
      const autocomplete = document.getElementById('autocomplete1');
      const warning6 = document.getElementById('warning6');
      const warning7 = document.getElementById('warning7');
      const warning8 = document.getElementById('warning8');

      // 기술스택 개수가 5개미만이고 추가되어있는 techStack일때
      if (
        state.form.beginTechList.length < 5 &&
        state.form.beginTechList.includes(clickedTechStack)
      ) {
        warning6.style = 'display:none';
        warning7.style = '';
        warning8.style = 'display:none';
      }
      // 기술스택 개수가 5개이상이고 추가되어있지 않은 techStack일때
      else if (
        state.form.beginTechList.length >= 5 &&
        !state.form.beginTechList.includes(clickedTechStack)
      ) {
        warning6.style = 'display:none';
        warning7.style = 'display:none';
        warning8.style = '';
      }
      // 기술스택 개수가 5개 이상이고 추가되어있는 techStack일때
      else if (
        state.form.beginTechList.length >= 5 &&
        state.form.beginTechList.includes(clickedTechStack)
      ) {
        warning6.style = 'display:none';
        warning7.style = '';
        warning8.style = '';
      }
      // 기술스택 개수가 5개 미만이고 추가되어있지 않은 techStack일때
      else {
        // 내가 추가할 값이 state.expDelTechList에 있다면,
        if (state.form.beginDelTechList.includes(clickedTechStack)) {
          // 해당 리스트에서 뺀 후
          state.form.beginDelTechList = state.form.beginDelTechList.filter(
            (techStack) => techStack !== clickedTechStack
          );
          // state.form.expAddTechList에 추가한다.
          state.form.beginAddTechList.push(clickedTechStack);
        }
        // 내가 추가할 값이 state.form.expDelTechList에 없다면
        else {
          // state.form.expAddTechList에 해당 값을 추가한다.
          state.form.beginAddTechList.push(clickedTechStack);
        }
        // box에 보여줄 요소 Data 처리
        // 회원가입할때 보낼 data값
        state.form.beginTechList.push(clickedTechStack);
        console.log(`${state.form.beginTechList}이 추가되었다!`);

        warning6.style = 'display:none';
        warning7.style = 'display:none';
        warning8.style = 'display:none';
        autocomplete.style = 'display:none';
        state.beg = '';
        console.log(state.form.beginAddTechList, 'begin추가할 목록');
        console.log(state.form.beginDelTechList, 'begin삭제할 목록');
      }
    };

    const deleteStack2 = (clickedTechStack) => {
      // 내가 삭제할 값이 beginAddTechList에 있다면
      if (state.form.beginAddTechList.includes(clickedTechStack)) {
        // 해당 리스트에서 빼고
        state.form.beginAddTechList = state.form.beginAddTechList.filter(
          (techStack) => techStack !== clickedTechStack
        );
        // beginDelTechList에 추가한다.
        state.form.beginDelTechList.push(clickedTechStack);
      }
      // 내가 삭제할 값이 beginAddTechList에 없다면 beginDelTechList에 추가한다.
      else {
        state.form.beginDelTechList.push(clickedTechStack);
      }
      state.form.beginTechList = state.form.beginTechList.filter(
        (techStack) => techStack !== clickedTechStack
      );
      console.log(state.form.beginAddTechList, 'begin추가할 목록');
      console.log(state.form.beginDelTechList, 'begin삭제할 목록');
    };

    const addPosition = function () {
      var warning9 = document.getElementById('warning9');
      var warning10 = document.getElementById('warning10');
      var warning11 = document.getElementById('warning11');
      // 값을 입력하지 않았고 추가되어있는 detailPosition이 5개 미만일떄
      if (!state.dp && state.form.dpositionList.length < 5) {
        warning9.style = '';
        warning10.style = 'display:none';
        warning11.style = 'display:none';
      }
      // 값을 입력하지 않았고 추가되어있는 detailPosition이 5개 이상일떄
      else if (!state.dp && state.form.dpositionList.length >= 5) {
        warning9.style = '';
        warning10.style = 'display:none';
        warning11.style = '';
      }
      // 기술스택 개수가 5개이하이고 추가되어있는 techStack일때
      else if (
        state.form.dpositionList.length < 5 &&
        state.form.dpositionList.includes(state.dp)
      ) {
        warning9.style = 'display:none';
        warning10.style = '';
        warning11.style = 'display:none';
      }
      // 기술스택 개수가 5개이상이고 추가되어있지 않은 techStack일때
      else if (
        state.form.dpositionList.length >= 5 &&
        !state.form.dpositionList.includes(state.dp)
      ) {
        warning9.style = 'display:none';
        warning10.style = 'display:none';
        warning11.style = '';
      }
      // 기술스택 개수가 5개 이상이고 추가되어있는 techStack일때
      else if (
        state.form.dpositionList.length >= 5 &&
        state.form.dpositionList.includes(state.dp)
      ) {
        warning9.style = 'display:none';
        warning10.style = '';
        warning11.style = '';
      }
      // 기술스택 개수가 5개 이하이고 추가되어있지 않은 techStack일때
      else {
        // 내가 추가할 값이 dpositionDelList에 있다면,
        if (state.form.dpositionDelList.includes(state.dp)) {
          // 해당 리스트에서 뺀 후
          state.form.dpositionDelList = state.form.dpositionDelList.filter(
            (techStack) => techStack !== state.dp
          );
          // dpositionAddTechList에 추가한다.
          state.form.dpositionAddList.push(state.dp);
        }
        // 내가 추가할 값이 state.form.expDelTechList에 없다면
        else {
          // state.form.expAddTechList에 해당 값을 추가한다.
          state.form.dpositionAddList.push(state.dp);
        }
        // box에 보여줄 요소 Data처리
        // 회원가입할때 보낼 Data값
        state.form.dpositionList.push(state.dp);
        console.log(state.form, '세부포지션을 찍을거임!');
        warning9.style = 'display:none';
        warning10.style = 'display:none';
        warning11.style = 'display:none';
        state.dp = '';
        console.log(state.form.dpositionAddList, 'exp추가할 목록');
        console.log(state.form.dpositionDelList, 'exp삭제할 목록');
      }
    };

    const deletePosition1 = (clickedDetailPosition) => {
      // 내가 삭제할 값이 dpositionAddList에 있다면
      if (state.form.dpositionAddList.includes(clickedDetailPosition)) {
        // 해당 리스트에서 빼고
        state.form.dpositionAddList = state.form.dpositionAddList.filter(
          (techStack) => techStack !== clickedDetailPosition
        );
        // dpositionDelList에 추가한다.
        state.form.dpositionDelList.push(clickedDetailPosition);
      }
      // 내가 삭제할 값이 dpositionDelList에 없다면 dpositionDelList에 추가한다.
      else {
        state.form.dpositionDelList.push(clickedDetailPosition);
      }
      // box에 보여줄 요소 Data처리
      state.form.dpositionList = state.form.dpositionList.filter(
        (detailPosition) => detailPosition !== clickedDetailPosition
      );
      console.log(state.form.dpositionAddList, 'dposition추가할 목록');
      console.log(state.form.dpositionDelList, 'dposition삭제할 목록');
    };

    const goReadMyPage = function () {
      router.push({ path: '/nosubheader/readmypage' });
    };

    const updateMember = function () {
      store.dispatch('member/updateMember', state.form).then((res) => {
        store.dispatch('member/readMyPage');
        console.log(res);
      });
    };

    return {
      store,
      state,
      router,
      goReadMyPage,
      beforeProfileUpload,
      beforePortUpload,
      stackAutoComplete,
      stackAutoComplete1,
      addEx,
      addBe,
      addPosition,
      addStack1,
      addStack2,
      deleteStack1,
      deleteStack2,
      deletePosition1,
      updateMember,
      user,
    };
  },

  methods: {
    // readURL: function (event) {
    //   console.log('selected');
    //   var reader = new FileReader();
    //   reader.onload = (e) => {
    //     console.log('onload');
    //     console.log(e.target.result);
    //     this.uploadImageFile = e.target.result;
    //   };
    //   console.log(this.uploadImageFile);
    // },
  },
};
</script>

<style scoped>
.test-border {
  margin-bottom: 3%;
}
.flex-parent {
  display: flex;
  flex-direction: column;
  text-align: left;
  justify-content: space-evenly;
}
.title {
  font-size: 24px;
}
.info-size {
  height: 32px;
}
.previewImg {
  /* min-width: 275px;
  min-height: 275px; */
  /* width: 70%;
  height: 70%; */
  max-width: 100%;
  max-height: 100%;

  object-fit: cover;
  border: 1px dotted black;
  border-radius: 10px;
}
.upload {
}
.bg {
  background: #f2f2f2;
}
.box1 {
  height: 360px;
  display: flex;
  margin-top: 30px;
  /* border: 1px solid red; */
}
.box2 {
  display: flex;
  flex-flow: column;
}
.box3 {
  margin-left: 50px;
  width: 300px;
  height: 50px;
}
.box4 {
  width: 650px;
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  overflow: visible;
}
.box5 {
  display: flex;
  flex-flow: column;
  flex-wrap: nowrap;
  /* border: 1px solid lightseagreen; */
}
.box6 {
  display: flex;
  flex-flow: column;
}
.box8 {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  min-width: 527px;
  height: 50px;
  border: none;
  border-radius: 4px;
  padding-left: 10px;
  background: white;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
}
.label1 {
  display: flex;
  align-items: center;
  width: 100px;
  height: 52px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
}
.label2 {
  display: flex;
  text-align: left;
  align-items: center;
  min-width: 200px;
  height: 52px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
}
.label3 {
  min-width: 200px;
  display: flex;
  text-align: left;
  align-items: center;
  margin-top: -10px;
  margin-left: 200px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 16px;
}
.input1 {
  width: 300px;
  height: 50px;
  border: none;
  border-radius: 4px;
  padding-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
}
.input2 {
  min-width: 527px;
  height: 50px;
  border: none;
  border-radius: 4px;
  padding-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
}
.input3 {
  min-width: 527px;
  height: 200px;
  border: none;
  resize: none;
  border-radius: 4px;
  margin-top: 10px;
  padding-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
}
.link0 {
  display: flex;
  align-items: center;
}
.link1 {
  width: 464px;
  height: 50px;
  border: none;
  border-radius: 4px;
  padding-left: 10px;
  display: flex;
  justify-content: center;
  align-items: center;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
}
.link2 {
  width: 50px;
  height: 30px;
  border: none;
  border-radius: 4px;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid black;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
}
#h1 {
  width: 300px;
  height: 52px;
  margin-top: 50px;
  margin-bottom: 0px;
  margin-left: 1px;
  text-align: left;
  color: #000000;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 36px;
  line-height: 52px;
}
#email {
  background: lightgrey;
}
#stack1 {
  width: 230px;
  height: 40px;

  border-radius: 10px;
  border: 0px;

  display: flex;
  margin-left: 200px;
  margin-bottom: 5px;

  /* 기술스택 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  /* identical to box height, or 114% */
  padding-left: 10px;
  text-align: left;
}
#stack2 {
  width: 230px;
  height: 40px;

  border-radius: 10px;
  border: 0px;

  display: flex;
  margin-left: 200px;
  margin-bottom: 5px;

  /* 기술스택 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  /* identical to box height, or 114% */
  padding-left: 10px;
  text-align: left;
}
#stack3 {
  width: 230px;
  height: 40px;

  border-radius: 10px;
  border: 0px;

  display: flex;
  margin-left: 200px;
  margin-bottom: 5px;

  /* 기술스택 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 18px;
  /* identical to box height, or 114% */
  padding-left: 10px;
  text-align: left;
}
#autocomplete {
  position: absolute;
  display: none;
  display: flex;
  flex-flow: column;
  max-height: 150px;
  overflow: auto;
  margin-top: 60px;
  margin-left: 200px;
  border-radius: 4px;
}
#autocomplete1 {
  position: absolute;
  display: none;
  display: flex;
  flex-flow: column;
  max-height: 150px;
  overflow: auto;
  margin-top: 60px;
  margin-left: 200px;
  border-radius: 4px;
}
.autocomplete1 {
  width: 200px;
  background: white;
  /* background: #e8e8e8; */
}
.autocomplete1:hover {
  width: 200px;
  background: #3f8bfc;
  /* background: #307ff5; */
  color: white;
}
.autocomplete1:active {
  width: 200px;
  background: black;
}
.autocomplete2 {
  width: 200px;
  background: white;
  /* background: #e8e8e8; */
}
.autocomplete2:hover {
  width: 200px;
  background: #3f8bfc;
  /* background: #307ff5; */
  color: white;
}
.autocomplete2:active {
  width: 200px;
  background: black;
}
.addValue1 {
  height: 30px;
  display: flex;
  /* text-align: center; */
  vertical-align: middle;

  margin-right: 5px;
  padding-top: 3px;
  padding-left: 5px;
  padding-right: 5px;
  border: 1px solid #307ff5;
  border-radius: 10px;
  color: #307ff5;

  font-family: Noto Sans KR;
  font-style: normal;
}
.addValue1:hover {
  height: 30px;
  display: flex;
  /* text-align: center; */
  vertical-align: middle;

  margin-right: 5px;
  padding-top: 3px;
  padding-left: 5px;
  padding-right: 5px;
  border: 1px solid #ff5757;
  border-radius: 10px;
  color: #ff5757;

  font-family: Noto Sans KR;
  font-style: normal;

  cursor: pointer;
}
.addValue2 {
  height: 30px;
  display: flex;
  /* text-align: center; */
  vertical-align: middle;

  margin-right: 5px;
  padding-top: 3px;
  padding-left: 5px;
  padding-right: 5px;
  border: 1px solid #307ff5;
  border-radius: 10px;
  color: #307ff5;

  font-family: Noto Sans KR;
  font-style: normal;
}
.addValue2:hover {
  height: 30px;
  display: flex;
  /* text-align: center; */
  vertical-align: middle;

  margin-right: 5px;
  padding-top: 3px;
  padding-left: 5px;
  padding-right: 5px;
  border: 1px solid #ff5757;
  border-radius: 10px;
  color: #ff5757;

  font-family: Noto Sans KR;
  font-style: normal;

  cursor: pointer;
}
#warning1 {
  display: flex;
  align-items: center;
  margin-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
  color: #ff5757;
}
#warning2 {
  display: flex;
  align-items: center;
  margin-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
  color: #ff5757;
}
#warning3 {
  display: flex;
  align-items: center;
  margin-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
  color: #ff5757;
}
#warning4 {
  display: flex;
  align-items: center;
  margin-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
  color: #ff5757;
}
#warning5 {
  display: flex;
  align-items: center;
  margin-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
  color: #ff5757;
}
#warning6 {
  display: flex;
  align-items: center;
  margin-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
  color: #ff5757;
}
#warning7 {
  display: flex;
  align-items: center;
  margin-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
  color: #ff5757;
}
#warning8 {
  display: flex;
  align-items: center;
  margin-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
  color: #ff5757;
}
#warning9 {
  width: 150px;
  display: flex;
  align-items: center;
  margin-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
  color: #ff5757;
}
#warning10 {
  width: 200px;
  display: flex;
  align-items: center;
  margin-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
  color: #ff5757;
}
#warning11 {
  display: flex;
  align-items: center;
  margin-left: 10px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
  color: #ff5757;
}
.profile {
  width: 100%;
  height: 100%;

  overflow: hidden;
  /* border: 0.5px dashed black; */
  /* border-radius: 3%; */
}
</style>
