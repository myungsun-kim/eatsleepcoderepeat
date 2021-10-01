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
    studyId: '',
  },
  mutations: {
    updateTotalStudyList(state, payload) {
      state.totalStudyList = payload;
    },
    updateStudyId(state, payload) {
      // console.log('넘겨준 STUDY ID 값' + payload);
      state.studyId = payload;
    },
  },
  actions: {
    // 전체 스터디 목록
    getTotalStudyList({ commit }) {
      const res = axios.get(BASE_URL + '/api/study', header);
      res.then((res) => {
        console.log('스터디 목록 조회 결과');
        console.log(res);
        console.log(res.data);
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
    callUpdateStudyId({ commit }, data) {
      // console.log('넘겨줄 STUDY ID 값' + data);
      commit('updateStudyId', data);
    },
  },
  getters: {
    totalStudyGetter: (state) => {
      return state.totalStudyList;
    },
    studyIdGetter: (state) => {
      return state.studyId;
    },
  },
  modules: {},
};
