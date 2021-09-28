<template>
  <el-row :gutter="0" class="height100" id="signup">
    <el-col :span="9" :offset="0" id="left">
      <div class="height100">
        <div class="height30">30</div>
        <div class="height5">5</div>
        <div class="height10" id="P1">
          <p id="p1">회원이신가요?</p>
        </div>
        <div class="height10" id="P2">
          <p id="p2">
            로그인 하고 여러분이 원하는 스터디와 프로젝트를 찾아보세요!
          </p>
        </div>
        <div class="height5"></div>
        <div class="height5">
          <el-button id="login" @click="goSignIn">로그인</el-button>
        </div>
        <div class="height30">30</div>
        <div class="height5">5</div>
      </div>
    </el-col>

    <!-- Step1 기본정보-->
    <el-col :span="15" :offset="0" id="step1" v-if="state.step == 1">
      <form class="height100">
        <div class="height20">20</div>
        <div class="height5" id="H1">
          <p id="h1">회원가입</p>
        </div>
        <div class="height10" id="H2">
          <p id="h2">Step1-기본정보</p>
        </div>
        <div class="height40" id="input1" :rules="state.rules">
          <input
            v-model="state.form.email"
            type="text"
            placeholder="이메일"
            id="email"
            onfocus="this.placeholder=''"
            onblur="this.placeholder='이메일'"
            @blur="checkEmail()"
            autocomplete="off"
            maxlength="30"
          />
          <div id="H3">
            <input
              v-model="state.form.name"
              type="text"
              placeholder="이름"
              id="name"
              onfocus="this.placeholder=''"
              onblur="this.placeholder='이름'"
              @blur="checkName()"
              autocomplete="off"
            />
            <input
              v-model="state.form.nickname"
              type="text"
              placeholder="닉네임"
              id="nickname"
              onfocus="this.placeholder=''"
              onblur="this.placeholder='닉네임'"
              autocomplete="off"
            />
          </div>
          <input
            v-model="state.form.password"
            type="password"
            placeholder="비밀번호"
            id="password"
            onfocus="this.placeholder=''"
            onblur="this.placeholder='비밀번호'"
            @blur="checkPassword()"
            autocomplete="off"
          />
          <input
            v-model="state.form.affirmPassword"
            type="password"
            placeholder="비밀번호 확인"
            id="checkpassword"
            onfocus="this.placeholder=''"
            onblur="this.placeholder='비밀번호 확인'"
            @blur="checkAffirmPassword()"
            autocomplete="off"
          />
          <select id="position" v-model="state.form.position">
            <option value="">포지션을 선택하세요</option>
            <option value="p1">기획자</option>
            <option value="p2">개발자</option>
            <option value="p3">디자이너</option>
          </select>

          <select id="city" v-model="state.form.city">
            <option value="">지역을 선택하세요</option>
            <option value="r1">서울</option>
            <option value="r2">부산</option>
            <option value="r3">대구</option>
            <option value="r4">인천</option>
            <option value="r5">광주</option>
            <option value="r6">대전</option>
            <option value="r7">울산</option>
            <option value="r8">세종</option>
            <option value="r9">경기</option>
            <option value="r10">강원</option>
            <option value="r11">충북</option>
            <option value="r12">충남</option>
            <option value="r13">전북</option>
            <option value="r14">전남</option>
            <option value="r15">경북</option>
            <option value="r16">경남</option>
            <option value="r17">제주</option>
          </select>
        </div>
        <div class="height10" id="button0">
          <el-row :gutter="0">
            <el-col :span="12" :offset="0"></el-col>
            <el-col :span="12" :offset="0"></el-col>
          </el-row>

          <el-button id="next0" @click="nextStep">다음</el-button>
        </div>
        <div class="height10">10</div>
        <div class="height5">5</div>
      </form>
    </el-col>

    <!-- Step2 포트폴리오 -->
    <el-col :span="15" :offset="0" id="step2" v-else-if="state.step == 2">
      <div class="height100">
        <div class="height20">20</div>
        <div class="height5" id="H1">
          <p id="h1">회원가입</p>
        </div>
        <div class="height10" id="H2">
          <p id="h2">Step2-포트폴리오</p>
        </div>
        <div class="height5">5</div>
        <div class="height30">
          <el-row :gutter="0" class="height100">
            <el-col :span="11" :offset="0">
              <div class="height100">
                <div id="H4">
                  <p id="h4">포트폴리오 파일 등록</p>
                </div>
                <div id="upload">
                  <el-upload
                    class="upload-demo"
                    drag
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
                    :file-list="fileList"
                    multiple
                  >
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text">
                      파일을 드래그 하거나
                      <br />
                      <em>클릭해서 업로드 하세요</em>
                    </div>
                    <template #tip>
                      <div class="el-upload__tip">(최대 100MB)</div>
                    </template>
                  </el-upload>
                </div>
              </div>
            </el-col>
            <el-col :span="1" :offset="0">
              <div class="height100" id="H5">
                <p id="h5">또는</p>
              </div>
            </el-col>
            <el-col :span="12" :offset="0">
              <div class="height100" id="H6">
                <p>포트폴리오 url 등록</p>
                <input
                  type="text"
                  placeholder="url경로를 입력하세요."
                  id="portfolio"
                  onfocus="this.placeholder=''"
                  onblur="this.placeholder='url경로를 입력하세요.'"
                />
              </div>
            </el-col>
          </el-row>
        </div>
        <div class="height5">5</div>
        <div class="height10" id="button">
          <el-row :gutter="0">
            <el-col :span="12" :offset="0"></el-col>
            <el-col :span="12" :offset="0"></el-col>
          </el-row>

          <el-button id="previous" @click="previousStep">이전</el-button>
          <el-button id="skip" @click="skipStep">건너뛰기</el-button>
          <el-button id="next" @click="nextStep">다음</el-button>
        </div>
      </div>
    </el-col>

    <!-- Step3 개발수준 -->
    <el-col :span="15" :offset="0" id="step3" v-else-if="state.step == 3">
      <div class="height100">
        <div class="height20">20</div>
        <div class="height5" id="H1">
          <p id="h1">회원가입</p>
        </div>
        <div class="height10" id="H2">
          <p id="h2">Step3-개발수준</p>
        </div>
        <div class="height40" id="circle">
          <div id="H7">
            <div id="circle1">
              Experienced
              <br />
              (이해가 깊음)
            </div>
            <div id="H8">
              <div id="box1"></div>
              <input
                type="text"
                placeholder="기술스택을 입력하세요."
                id="stack"
                onfocus="this.placeholder=''"
                onblur="this.placeholder='기술스택을 입력하세요'"
              />
            </div>
          </div>
          <hr style="border: dotted 1px; margin-left: 180px" />
          <div id="H7">
            <div id="circle1">
              Beginner
              <br />
              (경험해본적 있음)
            </div>
            <div>
              <div id="box1"></div>
              <input
                type="text"
                placeholder="기술스택을 입력하세요."
                id="stack"
                onfocus="this.placeholder=''"
                onblur="this.placeholder='기술스택을 입력하세요'"
              />
            </div>
          </div>
        </div>
        <div class="height10" id="button">
          <el-row :gutter="0">
            <el-col :span="12" :offset="0"></el-col>
            <el-col :span="12" :offset="0"></el-col>
          </el-row>

          <el-button id="previous" @click="previousStep">이전</el-button>
          <el-button id="skip" @click="skipStep">건너뛰기</el-button>
          <el-button id="next" @click="nextStep">다음</el-button>
        </div>
      </div>
    </el-col>

    <!-- Step4 세부정보 -->
    <el-col :span="15" :offset="0" id="step4" v-else>
      <div class="height100">
        <div class="height20">20</div>
        <div class="height5" id="H1">
          <p id="h1">회원가입</p>
        </div>
        <div class="height10" id="H2">
          <p id="h2">Step4-세부정보</p>
        </div>
        <div class="height40" id="H8">
          <p id="h8">세부 포지션 (최대 5개)</p>
          <div id="box2">box</div>
        </div>
        <div class="height10" id="button">
          <el-row :gutter="0">
            <el-col :span="12" :offset="0"></el-col>
            <el-col :span="12" :offset="0"></el-col>
          </el-row>

          <el-button id="previous" @click="previousStep">이전</el-button>
          <el-button id="skip" @click="signUp">건너뛰기(완료)</el-button>
          <el-button id="next" @click="signUp">완료</el-button>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import { onBeforeMount, onUnmounted, reactive } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
export default {
  name: 'SignUp',
  setup() {
    const router = useRouter();
    // store/index.js를 쓰겠다는 이야기
    const store = useStore();
    // 독립적인 반응형 값 생성 ref()
    // const signUp = ref(null);
    const state = reactive({
      step: 1,
      form: {
        email: '',
        name: '',
        nickname: '',
        password: '',
        affirmPassword: '',
        position: '',
        city: '',
      },
    });
    // Step1~4 간의 이동시 이동하는 페이지에 기존에 입력해놨던 값이 하나라도 있었다면 모조리 불러온다.(=값을 입력했으나 unMounted 된 적이 없는 경우)
    // router.push로 해당 페이지로 이동했을 때 store.auth.state.form에 저장되어 있는 내용이 있다면 해당 내용을 불러온다.
    onBeforeMount(() => {
      // 'state 중 auth 모둘의 값을 쓸 것이다.' 라는 문법
      if (store.state.auth.form) {
        console.log(store.state.form);
        state.form.email = store.state.auth.form.email;
        state.form.name = store.state.auth.form.name;
        state.form.nickname = store.state.auth.form.nickname;
        state.form.password = store.state.auth.form.password;
        state.form.affirmPassword = store.state.auth.form.affirmPassword;
        state.form.position = store.state.auth.form.position;
        state.form.city = store.state.auth.form.city;
      }
    });

    // 회원가입 페이지를 벗어나면 자동으로 store.state.form에 임시로 저장해놨던 내용들을 초기화 시킨다.
    onUnmounted(() => {
      store.state.auth.form.email = '';
      store.state.auth.form.name = '';
      store.state.auth.form.nickname = '';
      store.state.auth.form.password = '';
      store.state.auth.form.affirmPassword = '';
      store.state.auth.form.position = '';
      store.state.auth.form.city = '';
    });

    // 로그인 페이지로 이동
    const goSignIn = function () {
      router.push({ path: '/noheader/signin' });
    };

    // 현제 페이지에서 입력했던 내용들을 vuex-persistedstate가 적용되는 store에 저장시켜놓고
    // 이전 회원가입 Step으로 이동
    const previousStep = function () {
      console.log('이전 Step으로 이동!');
      console.log(state.form);
      store.state.auth.form.email = state.form.email;
      store.state.auth.form.name = state.form.name;
      store.state.auth.form.nickname = state.form.nickname;
      store.state.auth.form.password = state.form.password;
      store.state.auth.form.affirmPassword = state.form.affirmPassword;
      store.state.auth.form.position = state.form.position;
      store.state.auth.form.city = state.form.city;
      state.step = state.step - 1;
      router.push({ path: '/noheader/signup' });
    };
    // 현제 페이지에서 입력했던 내용들을 vuex-persistedstate가 적용되는 store에 저장시켜놓고
    // 이전 회원가입 Step으로 이동
    const nextStep = function () {
      console.log('다음 Step으로 이동!');
      console.log(state.form);
      store.state.auth.form.email = state.form.email;
      store.state.auth.form.name = state.form.name;
      store.state.auth.form.nickname = state.form.nickname;
      store.state.auth.form.password = state.form.password;
      store.state.auth.form.affirmPassword = state.form.affirmPassword;
      store.state.auth.form.position = state.form.position;
      store.state.auth.form.city = state.form.city;
      state.step = state.step + 1;
      // window.location.reload();
      router.push({ path: '/noheader/signup' });
    };
    const skipStep = function () {
      store.state.auth.form.email = state.form.email;
      store.state.auth.form.name = state.form.name;
      store.state.auth.form.nickname = state.form.nickname;
      store.state.auth.form.password = state.form.password;
      store.state.auth.form.affirmPassword = state.form.affirmPassword;
      store.state.auth.form.position = state.form.position;
      store.state.auth.form.city = state.form.city;
      state.step = state.step + 1;
      router.push({ path: '/noheader/signup' });
    };
    const signUp = function () {
      // modules의 auth.js에서 signUp 액션을 dispatch함
      store.dispatch('auth/signUp', state.form);
      alert('회원가입이 성공적으로 완료되었습니다!');
      window.location = '/noheader/signIn';
    };
    // 이메일 유효성 검사
    const checkEmail = function () {
      console.log('이메일 포커싱 벗어남!!!');
      let emailVal = state.form.email;
      let reg =
        /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      if (emailVal.match(reg) == null) {
        alert('올바른 이메일 형식이 아닙니다.');
      }
      state.form.email = '';
    };
    const checkName = function () {
      console.log('이름 포커싱 벗어남!!!');
      let nameVal = state.form.name;
      let reg = /^[가-힣]{1,7}|[a-zA-Z]{2,10}$/;

      if (nameVal.match(reg) == null) {
        alert(
          '올바른 형식이 아닙니다.\n한글 이름은 1~7자 이내, \n영문 이름은 2~10자 이내로 작성해주세요.\n(혼용 불가)'
        );
        state.form.name = '';
      }
    };
    const checkNickname = function () {
      console.log('닉네임 포커싱 벗어남!!!');
    };
    const checkPassword = function () {
      console.log('비밀번호 포커싱 벗어남!!!');
      let passwordVal = state.form.password;
      let reg =
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

      if (passwordVal.match(reg) == null) {
        alert(
          '올바른 비밀번호 형식이 아닙니다.\n비밀번호는 최소 8자 이상이어야 합니다.\n비밀번호는 영문(소문자), 영문(대문자), 숫자, 특수문자가 반드시 조합되어야 합니다.'
        );
        state.form.password = '';
      }
    };
    const checkAffirmPassword = function () {
      console.log('비밀번호확인 포커싱 벗어남!!!');
      let affirmPasswordVal = state.form.affirmPassword;

      if (affirmPasswordVal != state.form.password) {
        alert('두 비밀번호가 일치하지 않습니다.');
        state.form.affirmPassword = '';
      }
    };
    return {
      store,
      state,
      router,
      goSignIn,
      previousStep,
      nextStep,
      skipStep,
      signUp,
      checkEmail,
      checkName,
      checkNickname,
      checkPassword,
      checkAffirmPassword,
    };
  },
};
</script>

<style scoped>
/* 공통 */
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: red;
}
.bg-purple {
  background: green;
}
.bg-purple-light {
  background: blue;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
#left {
  background: linear-gradient(
    180deg,
    rgba(45, 109, 206, 0.6) 0%,
    rgba(66, 142, 255, 0.7) 55%,
    #c4e6ff 100%
  );
}
#P1 {
  display: flex;
  align-items: center;
  justify-content: center;
}
#p1 {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 40px;
  line-height: 58px;
  /* identical to box height, or 145% */

  text-align: center;
  margin-top: 0px;
  margin-bottom: 0px;
  color: #ffffff;
}
#P2 {
  display: flex;
  align-items: center;
  justify-content: center;
}
#p2 {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 20px;
  line-height: 30px;
  /* or 150% */

  text-align: center;
  margin-top: 0px;
  margin-bottom: 0px;
  color: #ffffff;
}
#login {
  width: 64px;
  height: 18px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  line-height: 16px;
  /* or 100% */

  text-align: center;

  color: #000000;

  /* 버튼 */
  width: 178px;
  height: 50px;

  border: 0px;
  background: #ffffff;
  border-radius: 30px;
}
#button0 {
  display: flex;
  align-items: center;
}
#button {
  display: flex;
  align-items: center;
}
#previous {
  /* 버튼 */
  width: 150px;
  height: 54px;

  background: #ff5757;
  border: 0px;
  border-radius: 30px;
  margin-left: 20%;

  /* 글자 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  line-height: 16px;
  /* identical to box height, or 100% */

  text-align: center;

  color: #ffffff;
}
#skip {
  /* 버튼 */
  width: 150px;
  height: 54px;

  background: #ffffff;
  border: 2px solid #307ff5;
  box-sizing: border-box;
  border-radius: 30px;
  margin-left: 30%;

  /* 글자 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  line-height: 16px;
  /* identical to box height, or 100% */

  text-align: center;
  color: #307ff5;
}
#next {
  /* 버튼 */
  width: 150px;
  height: 54px;

  background: #307ff5;
  border: 0px;
  border-radius: 30px;
  margin-left: 10px;

  /* 글자 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  line-height: 16px;
  /* identical to box height, or 100% */

  text-align: center;

  color: #ffffff;
}
#H1 {
  display: flex;
  align-items: center;
  justify-content: left;
}
#h1 {
  width: 200px;
  height: 58px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 48px;
  line-height: 58px;
  /* identical to box height, or 121% */

  display: flex;
  align-items: center;
  margin-top: 0px;
  margin-bottom: 0px;
  margin-left: 180px;

  color: #202121;
}
#H2 {
  display: flex;
  align-items: center;
  justify-content: left;
}
#h2 {
  width: 200px;
  height: 40px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 24px;
  line-height: 40px;
  /* identical to box height, or 167% */

  display: flex;
  align-items: center;
  vertical-align: middle;
  margin-top: 0px;
  margin-bottom: 0px;
  margin-left: 180px;

  color: #202121;
}
#H3 {
  display: flex;
  align-items: center;
  justify-content: left;
}
/* -----------------------------------------------step1------------------------------------- */
#next0 {
  /* 버튼 */
  width: 150px;
  height: 54px;

  background: #307ff5;
  border: 0px;
  border-radius: 30px;
  margin-left: 70%;

  /* 글자 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  line-height: 16px;
  /* identical to box height, or 100% */

  text-align: center;

  color: #ffffff;
}
#input1 {
  display: flex;
  flex-flow: column;
}
#email {
  width: 400px;
  height: 48px;

  background: #e8e8e8;
  border-radius: 10px;
  border: 0px;

  margin-bottom: 10px;

  margin-left: 180px;

  /* 이메일 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 16px;
  /* identical to box height, or 114% */
  padding-left: 10px;
  text-align: left;

  color: #919191;
}
#name {
  width: 189px;
  height: 48px;

  background: #e8e8e8;
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  margin-left: 180px;

  /* 이름 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 16px;
  /* identical to box height, or 114% */
  padding-left: 10px;
  text-align: left;

  color: #919191;
}
#nickname {
  width: 189px;
  height: 48px;

  background: #e8e8e8;
  border-radius: 10px;
  border: 0px;
  margin-left: 10px;
  margin-bottom: 10px;
  padding-left: 10px;

  /* 닉네임 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 16px;
  /* identical to box height, or 114% */
  text-align: left;

  color: #919191;
}
#password {
  width: 400px;
  height: 48px;

  background: #e8e8e8;
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  padding-left: 10px;
  margin-left: 180px;

  /* 비밀번호 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 16px;
  /* identical to box height, or 114% */
  text-align: left;

  color: #919191;
}
#checkpassword {
  width: 400px;
  height: 48px;

  background: #e8e8e8;
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  padding-left: 10px;
  margin-left: 180px;

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
#field {
  width: 400px;
  height: 48px;

  background: #e8e8e8;
  border-radius: 10px;
  border: 0px;
  margin-bottom: 10px;
  padding-left: 10px;
  margin-left: 180px;

  /* 분야 선택 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 16px;
  /* identical to box height, or 114% */
  text-align: left;

  color: #919191;
}
#position {
  width: 412px;
  height: 50px;

  background: #e8e8e8;
  border-radius: 10px;
  border: 0px;
  padding-left: 10px;
  margin-bottom: 10px;
  margin-left: 180px;

  /* 지역 선택 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 16px;
  /* identical to box height, or 114% */
  text-align: left;

  color: #919191;
}
#city {
  width: 412px;
  height: 50px;

  background: #e8e8e8;
  border-radius: 10px;
  border: 0px;
  padding-left: 10px;
  margin-bottom: 10px;
  margin-left: 180px;

  /* 지역 선택 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 16px;
  /* identical to box height, or 114% */
  text-align: left;

  color: #919191;
}

/*---------------------------------------------------------------Step2----------------------------- */
#H4 {
  display: flex;
  align-items: center;
  justify-content: left;
}
#h4 {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 16px;
  /* identical to box height, or 114% */

  text-align: center;
  margin-left: 180px;

  color: #000000;
}
#upload {
  display: flex;
  flex-flow: column;
  justify-content: left;
  margin-left: 180px;
  margin-right: 100px;
}
.upload-demo .el-upload {
  width: 500px;
  height: 500px;
}
.el-upload__tip {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 360px;
}
#H5 {
  display: flex;
  align-items: center;
  justify-content: center;
}
#h5 {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 16px;
  /* identical to box height, or 114% */

  text-align: center;

  color: #000000;
}
#H6 {
}
#h6 {
}
#portfolio {
  width: 400px;
  height: 48px;

  background: #e8e8e8;
  border-radius: 10px;
  border: 0px;
  padding-left: 10px;

  /* 지역 선택 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 16px;
  /* identical to box height, or 114% */
  text-align: left;

  color: #919191;
}

/* -------------------------------------Step3----------------------------- */
#H7 {
  display: flex;
}
#circle {
}
#circle1 {
  margin-left: 180px;
  width: 150px;
  height: 150px;
  border: solid 1px black;
  border-radius: 100px;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 20px;
  display: flex;
  align-items: center;
  justify-content: center;

  color: #000000;
}
#box1 {
  width: 332px;
  height: 87px;

  border: 1px solid #000000;
  box-sizing: border-box;
  border-radius: 15px;
  margin-left: 65px;
}
#stack {
  width: 200px;
  height: 40px;

  background: #e8e8e8;
  border-radius: 10px;
  border: 0px;

  display: flex;
  margin-left: 65px;
  margin-top: 15px;
  margin-bottom: 10px;

  /* 기술스택 텍스트 */
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 16px;
  /* identical to box height, or 114% */
  padding-left: 10px;
  text-align: left;

  color: #919191;
}
/* --------------------------------------------Step4-------------------------------------------- */
#H8 {
  display: flex;
  flex-flow: column;
}
#h8 {
  display: flex;
  margin-left: 180px;
}
#box2 {
  width: 397px;
  height: 45px;
  margin-left: 180px;

  border: 1px solid #000000;
  box-sizing: border-box;
  border-radius: 15px;

  display: flex;
  align-items: center;
  justify-content: left;
}
</style>
