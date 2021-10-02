import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import { auth } from '@/store/modules/auth';
import { member } from '@/store/modules/member';
import { chat } from '@/store/modules/chat'
import { study } from '@/store/modules/study';

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
    setScrollModal(state, value) {
      state.scrollModal = value;
    },
    setMember(state, value) {
      state.user = value;
    },
  },
  actions: {
    changeScrollModal({ commit }, payload) {
      commit('setScrollModal', payload);
    },
    uploadFile({ commit }, formData) {
      const BASE_URL = '';
      const header = {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
          'Content-Type': 'multipart/form-data',
        },
      };
      const res = axios.post(
        BASE_URL + '/api/file/uploadFile',
        formData,
        header
      );
      return res;
    },
  },
  getters: {
    scrollGetter: (state) => {
      return state.scrollModal;
    },
    getUserInfo: (state) => {
      return state.user;
    },
  },
  modules: {
    auth,
    member,
    chat,
    /* 만약 모듈을 추가하실 필요가 있다면 
    아래 path 프로퍼티에 작성해주시기 바랍니다. */
    study,
  },

  plugins: [createPersistedState({
    paths: ['auth', 'member'] // <<<<<<여기!
  })],
});
