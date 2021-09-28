import axios from 'axios';
const BASE_URL = 'http://localhost:8080';

export const auth = {
  // 모듈별로 구분이 가능하게 하기 위해(독립적이기 위해) vuex namespaced: true
  namespaced: true,
  state: {
    category: null,
    // category 1:스터디 2:프로젝트 3:클럽
    form: {
      email: '',
      name: '',
      nickname: '',
      password: '',
      affirmPassword: '',
      position: '',
      city: '',
    },
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
      axios.post(BASE_URL + '/api/auth/login');
      console.log(form, '나... 로그인 된 걸지도?');
    },
    // 여기서의 form은 SignUp.vue의 signUp 함수에서 dispatch로 보내는 state.form임.
    signUp({ commit }, form) {
      // swagger url경로
      axios.post(BASE_URL + '/api/auth/signup');
      console.log(form, '나... 회원가입 한 걸지도?');
    },
  },
  modules: {},
};
