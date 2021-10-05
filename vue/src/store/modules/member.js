import axios from 'axios';
const BASE_URL = '';

export const member = {
  // 모듈별로 구분이 가능하게 하기 위해(독립적이기 위해) vuex namespaced: true
  namespaced: true,
  state: {
    mypage: {}, // 내 정보.
    userEmail: '', //어떤 유저의 정보를 보고 싶은지
    userInfo: {}, //보고자하는 유저의 정보
  },
  mutations: {
    updateMypage(state, payload) {
      // console.log('SAVE MY PAGE');
      // console.log('2-4');
      // console.log(payload);
      state.mypage = payload;
      // console.log('2-5');
    },
    updateUserEmail(state, payload) {
      console.log('updateUserEmail');
      console.log(payload);
      state.userEmail = payload;
    },
    updateUserInfo(state, payload) {
      console.log('update User Info');
      console.log(payload);
      state.userInfo = payload;
    },
  },
  actions: {
    // 마이페이지 정보
    readMyPage({ commit }) {
      // console.log('2-2');
      const res = axios
        .get(BASE_URL + `/api/member/mypage`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
          },
        })
        .then((res) => {
          // console.log('READ MY PAGE');
          // console.log(res);
          // console.log(res.data);
          commit('updateMypage', res.data);
        });
      return res;
    },
    // 다른 회원 정보 가져오기
    readInfoPage({ commit }, form) {
      console.log('readIn');
      // form = encodeURIComponent(form);
      const res = axios
        .get(BASE_URL + `/api/member/mypage/${encodeURI(form)}`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
          },
        })
        .then((res) => {
          console.log('READ MY PAGE');
          console.log(res);
          console.log(res.data);
          // console.log('READ MY PAGE');
          // console.log('2-3');
          // console.log(res);
          // console.log(res.data);
          commit('updateMypage', res.data);
          // console.log('2-6');
        });
      return res;
    },
    // 프로필 다운로드
    readProfile({ commit }, form) {
      console.log(localStorage.getItem('accessToken'));
      console.log(form);
      const res = axios.get(form, {
        headers: {
          // Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
          // 'Content-Type': 'image/png',
        },
      });
      return res;
    },
    // 비밀번호 체크
    checkPassword({ commit }, form) {
      // console.log('데이터');
      // console.log(JSON.stringify(form));
      // console.log('form');
      // console.log(form);
      console.log(localStorage.getItem('accessToken'));
      const res = axios.post(BASE_URL + `/api/member/check/password`, form, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
        },
      });
      return res;
    },
    // 회원 정보 수정
    updateMember({ commit }, form) {
      console.log(JSON.stringify(form));
      const res = axios
        .put(BASE_URL + `/api/member`, form, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
          },
        })
        .then((res) => {
          commit('updateMypage', res.data);
        });
      return res;
    },
  },
  getters: {
    mypageGetter: (state) => {
      console.log('getter');
      console.log(state.mypage);
      return state.mypage;
    },
    myStudyListGetter: (state) => {
      // console.log('myStudyListGetter 값 조회');
      // console.log(state.mypage);
      // console.log(state.mypage.myStudyList);
      return state.mypage.myStudyList;
    },
    userEmailGetter: (state) => {
      console.log('userEmailGetter');
      console.log(state.userEmail);
      return state.userEmail;
    },
    userInfoGetter: (state) => {
      console.log('userInfoGetter');
      console.log(state.userInfo);
      return state.userInfo;
    },
  },
  modules: {},
};
