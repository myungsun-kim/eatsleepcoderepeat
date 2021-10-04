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
    memberNickname: '', //어떤 회원의 정보를 조회할지
    studyIntroduce: {},
    studyApplications: [],
    studyApplication: {},
  },
  mutations: {
    updateTotalStudyList(state, payload) {
      state.totalStudyList = payload;
    },
    updateStudyId(state, payload) {
      console.log('넘겨준 STUDY ID 값' + payload);
      state.studyId = payload;
    },
    updateStudyMemberNickname(state, payload) {
      console.log('넘겨준 회원의 닉네임' + payload);
      state.memberNickname = payload;
    },
    updateStudyIntroduce(state, payload) {
      // console.log('넘겨준 STUDY INTRODUCE 값');
      // console.log(payload);
      state.studyIntroduce = payload;
    },
    updateStudyApplications(state, payload) {
      console.log('넘겨준 STUDY APPLICATION 값');
      console.log(payload);
      state.studyApplications = payload;
    },
    updateStudyApplication(state, payload) {
      console.log('해당 회원의 STUDY APPLICATION');
      console.log(payload);
      state.studyApplication = payload;
    },
  },
  actions: {
    // 전체 스터디 목록
    getTotalStudyList({ commit }) {
      const res = axios.get(BASE_URL + '/api/study', header);
      res.then((res) => {
        // console.log('스터디 목록 조회 결과');
        // console.log(res);
        // console.log(res.data);
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
        console.log(res.data);
        commit('updateStudyId', res.data);
      });
    },
    callUpdateStudyId({ commit }, data) {
      // console.log('넘겨줄 STUDY ID 값' + data);
      commit('updateStudyId', data);
    },
    introduce({ commit }, data) {
      const res = axios.get(BASE_URL + '/api/study/one/' + data, header);
      res.then((res) => {
        // console.log('스터디 introduice 조회 결과');
        // console.log(res);
        // console.log(res.data);
        commit('updateStudyIntroduce', res.data);
      });
      return res;
    },
    checkHost({ commit }, data) {
      const res = axios.get(
        BASE_URL + '/api/auth/check/nickname/' + data,
        header
      );
      return res.data;
    },
    // 해당 스터디 전체 신청서 조회
    applicationAll({ commit }, data) {
      const res = axios.get(
        BASE_URL + '/api/studyapplication/all/' + data,
        header
      );
      res.then((res) => {
        console.log('스터디 application ALL 조회 결과');
        console.log(res);
        console.log(res.data);
        commit('updateStudyApplications', res.data);
      });
      return res.data;
    },
    // 해당 스터디 특정 회원 신청서 조회
    applicationOne({ commit }, data1, data2) {
      const res = axios.get(
        BASE_URL + `/api/studyapplication/all/${data1}/${data2}`,
        header
      );
      res.then((res) => {
        console.log('스터디 application 조회');
        console.log(res);
        console.log(res.data);
        commit('updateStudyApplication', res.data);
      });
      return res.data;
    },
  },
  getters: {
    totalStudyGetter: (state) => {
      return state.totalStudyList;
    },
    studyIdGetter: (state) => {
      return state.studyId;
    },
    studyMemberNicknameGetter: (state) => {
      return state.memberNickname;
    },
    studyIntroduceGetter: (state) => {
      // console.log('Introduce GETTER');
      // console.log(state.studyIntroduce);
      return state.studyIntroduce;
    },
    studyApplicationsGetter: (state) => {
      console.log('APPLICATION ALL GETTER');
      console.log(state.studyApplications);
      return state.studyApplications;
    },
    studyApplicationGetter: (state) => {
      console.log('APPLICATION ALL GETTER');
      console.log(state.studyApplication);
      return state.studyApplication;
    },
  },
  modules: {},
};
