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
    studyBoardIdList: [],
    studyArticleId: '', // 스터디 게시글 아이디
    studyArticleList: [], // 스터디 (공지/일반) 게시글 목록
    studyNoticeBoardId: '', //스터디 공지사항 보드 ID
    studyNormalBoardId: '', // 스터디 게시판 보드 ID
    article: {}, //게시글 내용
    currentPage: '1', //현재 페이지네이션 할 페이지 위치
  },
  mutations: {
    updateTotalStudyList(state, payload) {
      state.totalStudyList = payload;
      console.log('저장된 모든 스터디 목록');
      console.log(state.totalStudyList);
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
      console.log('넘겨준 STUDY INTRODUCE 값');
      console.log(payload);
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
    updateBoardIdList(state, payload) {
      // console.log('넘겨준 updateBoardIdList 값');
      // console.log(payload);
      state.studyBoardIdList = payload;
    },
    updateArticleId(state, payload) {
      // console.log('넘겨준 updateArticleId 값');
      // console.log(payload);
      state.studyArticleId = payload;
    },
    updateNoticeArticleList(state, payload) {
      // console.log('넘겨준 updateArticleId 값');
      // console.log(payload);
      state.studyArticleList = payload;
    },
    updateStudyNoticeBoardId(state, payload) {
      //스터디 공지사항 보드 ID
      // console.log('넘겨준 공지사항 게시판 ID 값');
      // console.log(payload);
      state.studyNoticeBoardId = payload;
    },
    updateStudyNormalBoardId(state, payload) {
      // 스터디 게시판 보드 ID1
      // console.log('넘겨준 일반 게시판 ID 값');
      // console.log(payload);
      state.studyNormalBoardId = payload;
    },
    updateArticle(state, payload) {
      // console.log('넘겨준 게시판 내용값');
      // console.log(payload);
      state.article = payload;
    },
    updateCurrentPage(state, payload) {
      // console.log('넘겨준 게시판 내용값');
      // console.log(payload);
      state.currentPage = payload;
    },
  },
  actions: {
    // 전체 스터디 목록
    getTotalStudyList({ commit }) {
      const res = axios.get(BASE_URL + '/api/study', header);
      res.then((res) => {
        // console.log('스터디 목록 조회 결과');
        // console.log(res);
        // console.log(res.data.content);
        commit('updateTotalStudyList', res.data.content);
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
    callUpdateArticleId({ commit }, data) {
      // console.log('넘겨줄 Article ID 값' + data);
      commit('updateArticleId', data);
    },
    introduce({ commit }, data) {
      console.log('###############');
      const res = axios.get(BASE_URL + '/api/study/' + data, header);
      res.then((res) => {
        // console.log('스터디 introduce 조회 결과');
        // console.log(res);
        console.log(res.data);
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
    applicationOne({ commit }, form) {
      const res = axios.get(
        BASE_URL +
          `/api/studyapplication/all/${form.studyId}/${form.memberNickname}`,
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
    getBoardId({ commit }, data) {
      const res = axios.get(BASE_URL + `/api/study/${data}/boards`, header);
      res.then((res) => {
        console.log('getBoardId 조회 결과');
        console.log(res.data);
        commit('updateBoardIdList', res.data);
      });
      // return res.data;
    },
    createArticle({ commit }, param) {
      // console.log('보드 ID: ' + param.boardId);
      // console.log('글 내용: ' + param.content);

      const articleContent = {
        content: param.content,
        title: param.title,
      };
      // console.log('글 내용 JSON: ' + JSON.stringify(articleContent));

      const res = axios
        .post(
          BASE_URL + `/api/studyboards/${param.boardId}/articles`,
          JSON.stringify(articleContent),
          header
        )
        .then((res) => {
          console.log('createArticle 조회 결과');
          console.log(res.data);
          commit('updateArticleId', res.data);
        });
      // return res.data;
    },

    // 게시판 무관: 글 목록 가져오기
    getArticleList({ commit }, boardid) {
      const res = axios
        .get(BASE_URL + `/api/studyboards/${boardid}/articles`, header)
        .then((res) => {
          // console.log('getArticleList 조회 결과');
          // console.log(res.data);
          commit('updateNoticeArticleList', res.data);
        });
    },

    // 페이지별 게시글 요청
    getArticleListPage({ commit }, param) {
      // console.log('상세조회, 글 id, 보드 id');
      // console.log(param);
      // console.log(param.boardid);
      // console.log(param.pageNumber);
      // console.log(
      //   `/api/studyboards/${param.boardid}/articles?page=${param.pageNumber}`
      // );

      const res = axios
        .get(
          BASE_URL +
            `/api/studyboards/${param.boardid}/articles?page=${param.pageNumber}`,
          header
        )
        .then((res) => {
          console.log('getArticleListPage 조회 결과');
          console.log(res.data);
          commit('updateNoticeArticleList', res.data);
        });
    },
    // 스터디 신청
    applicateStudy({ commit }, form) {
      const res = axios.post(
        BASE_URL + `/api/studyapplication/${form.studyId}`,
        JSON.stringify(form),
        header
      );
      res.then((res) => {
        console.log('스터디 신청');
        console.log(res);
        console.log(res.data);
      });
      return res.data;
    },
    // 스터디 신청 거절
    rejectStudy({ commit }, form) {
      const res = axios.post(
        BASE_URL + `/api/studyapplication/${form.studyId}/${form.memberId}`,
        header
      );
      res.then((res) => {
        console.log('스터디 신청 거절');
        console.log(res);
        console.log(res.data);
      });
      return res.data;
    },
    // 스터디 신청 수락
    approvalStudy({ commit }, form) {
      const res = axios.post(
        BASE_URL +
          `/api/studyapplication/approval/${form.studyId}/${form.memberId}`,
        header
      );
      res.then((res) => {
        console.log('스터디 신청 수락');
        console.log(res);
        console.log(res.data);
      });
      return res.data;
    },
    // 스터디 신청 거절
    rejectStudy({ commit }, form) {
      const res = axios.post(
        BASE_URL + `/api/studyapplication/${form.studyId}/${form.memberId}`,
        header
      );
      res.then((res) => {
        console.log('스터디 신청 거절');
        console.log(res);
        console.log(res.data);
      });
      return res.data;
    },
    // 특정 게시글 하나 조회
    getArticleDetail({ commit }, form) {
      // console.log(form);
      console.log('상세조회, 글 id, 보드 id');
      console.log(form.articleid);
      console.log(form.boardid);

      const res = axios.get(
        BASE_URL +
          // `/api/studyboards/${form.articleid}/articles/${form.boardid}`,
          `/api/studyboards/${form.boardid}/articles/${form.articleid}`,
        header
      );
      res.then((res) => {
        console.log('게시글 조회');
        // console.log(res);
        console.log(res.data);
        commit('updateArticle', res.data);
      });
      return res.data;
    },
    deleteStudy({ commit }, form) {
      // console.log(form);

      const res = axios
        .delete(BASE_URL + `/api/study/${form}`, header)
        .then(() => {
          // console.log('스터디 삭제');
          // console.log(res);
          // console.log(res.data);
          commit('updateStudyId', '');
        });
    },
    quitStudy({ commit }, form) {
      // console.log(form);

      const res = axios
        .delete(BASE_URL + `/api/study/${form}/member`, header)
        .then(() => {
          console.log('스터디 탈퇴@@@@@@@@');
          console.log(res);
          console.log(res.data);
          // commit('updateStudyId', '');
        })
        .catch((err) => {
          alert('스터디장은 탈퇴할 수 없습니다.');
        });
    },
  },
  getters: {
    totalStudyGetter: (state) => {
      // console.log('###########################');
      // console.log(state.totalStudyList);
      return state.totalStudyList;
    },
    studyIdGetter: (state) => {
      return state.studyId;
      // return state.filter((studyId) => studyId.done);
      // return state.studyId.filter((todo) => todo.done);
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
      // console.log('APPLICATION ALL GETTER');
      // console.log(state.studyApplications);
      return state.studyApplications;
    },
    studyApplicationGetter: (state) => {
      // console.log('APPLICATION ALL GETTER');
      // console.log(state.studyApplication);
      return state.studyApplication;
    },
    studyBoardIdListGetter: (state) => {
      // console.log('studyBoardIdList GETTER');
      // console.log(state.studyBoardIdList);
      return state.studyBoardIdList;
    },
    studyNoticeArticleListGetter: (state) => {
      // console.log('studyArticleList GETTER');
      // console.log(state.studyArticleList);
      return state.studyArticleList;
    },
    studyNoticeBoardIdGetter: (state) => {
      // console.log('studyNoticeBoardId GETTER');
      // console.log(state.studyNoticeBoardId);
      return state.studyNoticeBoardId;
    },
    studyNormalBoardIdGetter: (state) => {
      // console.log('studyNormalBoardIdGetter GETTER');
      // console.log(state.studyNormalBoardId);
      return state.studyNormalBoardId;
    },
    studyArticleIdGetter: (state) => {
      // console.log('studyArticleId GETTER');
      // console.log(state.studyArticleId);
      return state.studyArticleId;
    },
    articleGetter: (state) => {
      // console.log('article GETTER');
      // console.log(state.article);
      return state.article;
    },
    currentPageGetter: (state) => {
      // console.log('article GETTER');
      // console.log(state.article);
      return state.currentPage;
    },
  },
  modules: {},
};
