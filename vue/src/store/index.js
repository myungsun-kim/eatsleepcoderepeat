import { createStore } from 'vuex';
import axios from 'axios';
import createPersistedState from 'vuex-persistedstate';
import { auth } from '@/store/modules/auth';
import { member } from '@/store/modules/member';

export default createStore({
  state: {
    category: null,
    user: {},
    scrollModal: 'false',
    // category 1:스터디 2:프로젝트 3:클럽
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
  },
  plugins: [createPersistedState()],
});
