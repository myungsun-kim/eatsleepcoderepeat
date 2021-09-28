import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';

import { auth } from '@/store/modules/auth';

export default createStore({
  state: {
    category: null,
    user: {},
    scrollModal: 'false',
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
    readMyPage(state, payload) {
      console.log('얘는조회');
      console.log('muto' + payload);
      state.user = payload;
      console.log(state.user + ' m');
    },
    setScrollModal(state, value) {
      state.scrollModal = value;
    },
  },
  actions: {
    async readMyPage({ commit }, payload) {
      console.log('?');
      try {
        // const token = localStorage.getItem('jwt');
        const token = `eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0NCIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2MzQ1MTIzMjh9.OMcJ-UHnaO8MU2nrJUYX48xhzy4_WzYz4WjEN6YlxO2BqvJkkZY5LL_3nFwB4SS2mIeGrSZ2phXlKufAsFsh9g`;
        console.log(token);
        // https://cors-anywhere.herokuapp.com/ 사용시 cors 에러 해결 가능
        const res = await axios.get('/api/member/mypage', {
          headers: {
            Authorization: 'Bearer ' + token,
          },
        });
        console.log('마이페이지');
        console.log(res);
        console.log(res.data);
        console.log(res.data.email);
        commit('readMyPage', payload);
      } catch (err) {
        console.log(err);
      }
    },
    changeScrollModal({ commit }, payload) {
      commit('setScrollModal', payload);
    },
  },
  getters: {
    scrollGetter: (state) => {
      return state.scrollModal;
    },
  },
  modules: { auth },
  plugins: [createPersistedState()],
});
