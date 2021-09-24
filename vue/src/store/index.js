import { createStore } from 'vuex';

export default createStore({
  state: {
    category: 1,
    // category 1:스터디 2:프로젝트 3:클럽
  },
  mutations: {
    setCategory(state, value) {
      state.category = value;
    },
  },
  actions: {},
  modules: {},
});
