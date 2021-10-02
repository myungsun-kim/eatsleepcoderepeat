import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import { auth } from '@/store/modules/auth';
import { member } from '@/store/modules/member';
import { chat } from '@/store/modules/chat'

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
  },

  plugins: [createPersistedState({
    paths: ['auth', 'member']
  })],
});
