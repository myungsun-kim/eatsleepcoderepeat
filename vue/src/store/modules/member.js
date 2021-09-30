import axios from 'axios';
const BASE_URL = '';

export const member = {
  // 모듈별로 구분이 가능하게 하기 위해(독립적이기 위해) vuex namespaced: true
  namespaced: true,
  state: {
    mypage: {},
  },
  mutations: {
    updateMypage(state, payload) {
      console.log('SAVE MY PAGE');
      console.log(payload);
      state.mypage = payload;
    },
  },
  actions: {
    readMyPage({ commit }) {
      const res = axios
        .get(BASE_URL + `/api/member/mypage`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
          },
        })
        .then((res) => {
          console.log('READ MY PAGE');
          console.log(res);
          console.log(res.data);
          commit('updateMypage', res.data);
        });

      return res;
    },
    checkPassword({ commit }, form) {
      console.log('데이터');
      console.log(JSON.stringify(form));
      console.log('form');
      console.log(form);
      console.log(localStorage.getItem('accessToken'));
      const res = axios.post(BASE_URL + `/api/member/check/password`, form, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
        },
      });
      return res;
    },
  },
  getters: {
    mypageGetter: (state) => {
      return state.mypage;
    },
    myStudyListGetter: (state) => {
      return state.mypage.myStudyList;
    },
  },
  modules: {},
};
