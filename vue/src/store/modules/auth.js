import axios from 'axios';
import { ref } from 'vue-demi';
const BASE_URL = '';
const header = { headers: { 'Content-Type': 'application/json' } };

export const auth = {
  // 모듈별로 구분이 가능하게 하기 위해(독립적이기 위해) vuex namespaced: true
  namespaced: true,
  state: {
    // category 1:스터디 2:프로젝트 3:클럽
    category: null,
    form: {
      email: '',
      name: '',
      nickname: '',
      password: '',
      affirmPassword: '',
      position: '',
      city: '',
      exp: '',
      beg: '',
      expTechList: [],
      beginTechList: [],
      dpositionList: [],
    },
    isLogin: false,
  },
  mutations: {
    setCategory(state, value) {
      state.category = value;
    },
  },
  actions: {
    // 첫번째 인자: context를 조작하기 위한 명령어 종류
    // 두번째 인자: 넘길 값들(payload)
    // 여기서의 form은 SignIn.vue의 signIn 함수에서 dispatch로 보내는 state.form임.
    signIn({ commit }, form) {
      // swagger url경로
      const res = axios.post(
        BASE_URL + '/api/auth/login',
        JSON.stringify(form),
        header
      );
      console.log('바로 밑이 res!');
      console.log(res);
      console.log(JSON.stringify(form));
      console.log(form, '나... 로그인 된 걸지도?');
      return res;
    },
    // 여기서의 form은 SignUp.vue의 signUp 함수에서 dispatch로 보내는 state.form임.
    signUp({ commit }, form) {
      // swagger url경로
      const res = axios.post(
        BASE_URL + '/api/auth/signup',
        JSON.stringify(form),
        header
      );
      console.log(form, '나... 회원가입 한 걸지도?');
      return res;
    },
    // 여기서의 form은 SignUp.vue의 checkEmail 함수에서 dispatch로 보내는 state.form.email임.
    checkEmail({ commit }, form) {
      // swagger url경로
      const res = axios.get(BASE_URL + `/api/auth/check/email/${form}`);
      console.log(form, '나... 이메일 중복체크 한 걸지도?');
      return res;
    },
    // 여기서의 form은 SignUp.vue의 checkNickName 함수에서 dispatch로 보내는 state.form.nickname임.
    checkNickName({ commit }, form) {
      // swagger url경로
      const res = axios.get(BASE_URL + `/api/auth/check/nickname/${form}`);
      console.log(form, '나... 닉네임 중복체크 한 걸지도?');
      return res;
    },
  },
  modules: {},
};
