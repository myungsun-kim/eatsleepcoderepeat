import { createStore } from 'vuex';
import axios from 'axios';
import createPersistedState from 'vuex-persistedstate';

export default createStore({
  state: {
    category: null,
    data: {},
    // category 1:스터디 2:프로젝트 3:클럽
  },
  mutations: {
    login(state, payload) {
      console.log(payload);
      state.data = payload;
      console.log(state.data);
    },
    setCategory(state, value) {
      state.category = value;
    },
  },
  actions: {
    async login({ commit }, payload) {
      try {
        const res = await axios.post(
          'http://localhost:8080/api/auth/login',
          payload
        );
        console.log('@@@@@@@@@@@@@@@@@');
        console.log(res);
        console.log(res.data);
        console.log(res.data.grantType);
        console.log(res.data.email);
        commit('login', res.data);
        // localStorage.setItem('jwt', state.token);
        // ElMessage({
        //   showClose: true,
        //   message: '로그인 테스트.',
        //   type: 'success',
        // });
      } catch (err) {
        console.log(err);
        // ElMessage({
        //   showClose: true,
        //   message: '로그인에 문제.',
        //   type: 'error',
        // });
      }
    },
  },
  modules: {},
  plugins: [createPersistedState()],
});
