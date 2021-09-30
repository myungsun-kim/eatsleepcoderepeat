import axios from 'axios';
const BASE_URL = '';
const header = {
  headers: {
    Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
    'Content-Type': 'application/json',
  },
};

export const study = {
  // 모듈별로 구분이 가능하게 하기 위해(독립적이기 위해) vuex namespaced: true
  namespaced: true,
  state: {
    totalStudyList: [],
  },
  mutations: {
    updateTotalStudyList(state, payload) {
      state.totalStudyList = payload;
    },
  },
  actions: {
    // 전체 스터디 목록
    getTotalStudyList({ commit }) {
      const res = axios.get(BASE_URL + '/api/study', header);
      res.then((res) => {
        console.log('스터디 목록 조회 결과');
        console.log(res);
        commit('updateTotalStudyList', res.data);
      });
      return res;
    },

    // 스터디 생성
    // JSON 형태로 보내야 하고, null인건 null로 보내야함 ""말고
    createStudy({ commit }, form) {
      const res = axios.post(
        BASE_URL + '/api/study',
        JSON.stringify(form),
        header
      );
      res.then((res) => {
        console.log('스터디 생성 결과');
        console.log(res);
      });
    },
  },
  getters: {
    totalStudyGetter: (state) => {
      console.log('GETTERS 들어옴');
      return state.totalStudyList;
    },
  },
  modules: {},
};
