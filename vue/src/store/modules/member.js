import axios from 'axios';
const BASE_URL = '';

export const member = {
  // 모듈별로 구분이 가능하게 하기 위해(독립적이기 위해) vuex namespaced: true
  namespaced: true,
  actions: {
    readMyPage({ commit }, form) {
      console.log('?');
      const token = `eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0NCIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2MzQ1NTc2NTZ9.cRRoL_a4JSkEeUS3DsPUsq6R2XyeOlYpPW9yYzW83Y_gKyajhrdYcIC5sZ3pLWXoYg1sTiSx1pYJ8l8ppG_ZzA`;
      console.log(token);
      const res = axios.get(BASE_URL + '/api/member/mypage', {
        headers: {
          Authorization: 'Bearer ' + token,
        },
      });
      return res;
    },
  },
  modules: {},
};
