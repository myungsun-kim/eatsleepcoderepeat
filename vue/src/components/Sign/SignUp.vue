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
          <el-button id="login">로그인</el-button>
        </div>
        <div class="height30">30</div>
        <div class="height5">5</div>
      </div>
    </el-col>

    <!-- Step1 기본정보-->
    <el-col :span="15" :offset="0" id="step1" v-if="state.form.step == 1">
      <div class="height100">
        <div class="height20">20</div>
        <div class="height5" id="H1">
          <p id="h1">회원가입</p>
        </div>
        <div class="height10" id="H2">
          <p id="h2">Step1-기본정보</p>
        </div>
        <div class="height40" id="input1">
          <input
            type="text"
            placeholder="이메일"
            id="email"
            onfocus="this.placeholder=''"
            onblur="this.placeholder='이메일'"
          />
          <div id="H3">
            <input
              type="text"
              placeholder="이름"
              id="name"
              onfocus="this.placeholder=''"
              onblur="this.placeholder='이름'"
            />
            <input
              type="text"
              placeholder="닉네임"
              id="nickname"
              onfocus="this.placeholder=''"
              onblur="this.placeholder='닉네임'"
            />
          </div>
          <input
            type="text"
            placeholder="비밀번호"
            id="password"
            onfocus="this.placeholder=''"
            onblur="this.placeholder='비밀번호'"
          />
          <input
            type="text"
            placeholder="비밀번호 확인"
            id="checkpassword"
            onfocus="this.placeholder=''"
            onblur="this.placeholder='비밀번호 확인'"
          />
          <input
            type="text"
            placeholder="분야 선택"
            id="field"
            onfocus="this.placeholder=''"
            onblur="this.placeholder='분야 선택'"
          />
          <input
            type="text"
            placeholder="지역 선택"
            id="region"
            onfocus="this.placeholder=''"
            onblur="this.placeholder='지역 선택'"
          />
        </div>
        <div class="height10" id="button0">
          <el-row :gutter="0">
            <el-col :span="12" :offset="0"></el-col>
            <el-col :span="12" :offset="0"></el-col>
          </el-row>

          <el-button id="next0">다음</el-button>
        </div>
        <div class="height10">10</div>
        <div class="height5">5</div>
      </div>
    </el-col>

    <!-- Step2 포트폴리오 -->
    <el-col :span="15" :offset="0" id="step2" v-else-if="state.form.step == 2">
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

          <el-button id="previous">이전</el-button>
          <el-button id="skip">건너뛰기</el-button>
          <el-button id="next">다음</el-button>
        </div>
      </div>
    </el-col>

    <!-- Step3 개발수준 -->
    <el-col :span="15" :offset="0" id="step3" v-else-if="state.form.step == 3">
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
              Strong
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
              Knowledgeable
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

          <el-button id="previous">이전</el-button>
          <el-button id="skip">건너뛰기</el-button>
          <el-button id="next">다음</el-button>
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
          <p id="h8">희망포지션 (최대 5개)</p>
          <div id="box2">box</div>
        </div>
        <div class="height10" id="button">
          <el-row :gutter="0">
            <el-col :span="12" :offset="0"></el-col>
            <el-col :span="12" :offset="0"></el-col>
          </el-row>

          <el-button id="previous">이전</el-button>
          <el-button id="skip">건너뛰기</el-button>
          <el-button id="next">다음</el-button>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import { reactive, ref } from 'vue';
import { useStore } from 'vuex';
export default {
  name: 'SignUp',

  setup() {
    const store = useStore();
    // 독립적인 반응형 값 생성 ref()
    // const signUp = ref(null);
    const state = reactive({
      form: {
        step: '1',
      },
    });
    return { store, state };
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
#region {
  width: 400px;
  height: 48px;

  background: #e8e8e8;
  border-radius: 10px;
  border: 0px;
  padding-left: 10px;
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
