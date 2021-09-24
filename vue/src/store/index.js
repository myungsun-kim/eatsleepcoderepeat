import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';

export default createStore({
  state: {
    category: null,
    // category 1:스터디 2:프로젝트 3:클럽
  },
  mutations: {
    setCategory(state, value) {
      state.category = value;
    },
  },
  actions: {},
  modules: {},
  plugins: [createPersistedState()],
});
