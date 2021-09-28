import axios from 'axios';
const BASE_URL = '';

export const member = {
  // 모듈별로 구분이 가능하게 하기 위해(독립적이기 위해) vuex namespaced: true
  namespaced: true,
  actions: {
    readMyPage({ commit }, form) {
      console.log('?');
      const token = `eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0NiIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2MzQ1OTA4OTV9.Ahg3zPGMPIdB8qHERI8W3_fOi3P6aq6df0hGhmeFTZOW-caVv980V2Jc8buS2V0_WBjlgXtsEt4eFzXeJq7ZjQ`;
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
