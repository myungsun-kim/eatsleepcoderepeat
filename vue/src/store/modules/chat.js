import axios from 'axios';
import store from '..';
import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';

const BASE_URL = '';
const header = { headers: { 'Content-Type': 'application/json' } };

export const chat = {
  //   모듈별로 구분이 가능하게 하기 위해(독립적이기 위해) vuex namespaced: true
  namespaced: true,
  getters: {
    getChatList: (state) => {
      // console.log(state.chatList);
      // console.log(state.currentUserId);
      return state.chatordered;
    },
    getMessages: (state) => {
      console.log(state.currentCounterpart);
      return state.chatdetail[`${state.currentCounterpart}`];
    },
    getCurrentCounterpart: (state) => {
      return state.currentCounterpart;
    },
    getCurrentUserId: (state) => {
      return state.currentUserId;
    },
    getUnreadCounts: (state) => {
      return state.unreadCounts;
    },
  },
  state: {
    chatlist: {},
    chatordered: [],
    chatdetail: {},
    currentCounterpart: 0,
    currentUserId: 0,
    stompClient: {},
    connected: false,
    socket: {},
    unreadSession: {},
    unreadCounts: 0,

    //     // category 1:스터디 2:프로젝트 3:클럽
    //     category: null,
    //     form: {
    //       email: '',
    //       name: '',
    //       nickname: '',
    //       password: '',
    //       affirmPassword: '',
    //       position: '',
    //       city: '',
    //     },
  },
  mutations: {
    cleanup(state) {
      state.chatlist = {};
      state.chatordered = [];
      state.chatdetail = {};
      state.currentCounterpart = 0;
      state.currentUserId = 0;
      state.stompClient = {};
      state.connected = false;
      state.socket = {};
      state.unreadSession = {};
      state.unreadCounts = 0;
    },
    setUnreadFlag(state, payload) {
      console.log('SETUNREADSTART');
      if (
        state.unreadSession[payload] === undefined ||
        state.unreadSession[payload] == false
      ) {
        console.log('SETUNREAD');
        console.log(payload);
        console.log(state.unreadSession);
        console.log(state.unreadSession[payload]);
        state.unreadCounts++;
        state.unreadSession[payload] = true;
      }
    },
    setReadFlag(state, payload) {
      if (state.unreadSession[payload] === true) {
        state.unreadCounts--;
        state.unreadSession[payload] = false;
      }
    },
    readMyMessage(state, payload) {
      let counterpart = payload.receiverId;
      let sessionMsgs = state.chatdetail[counterpart];
      console.log(sessionMsgs);
      for (let i = sessionMsgs.length - 1; i >= 0; i--) {
        console.log(sessionMsgs[i]);

        if (sessionMsgs[i].senderId == state.currentUserId) {
          continue;
        }
        if (sessionMsgs[i].read_time != 1000) {
          break;
        }
        sessionMsgs[i].read_time = payload.sent_time;
      }
    },
    readSignal(state, payload) {
      if (payload.senderId == state.currentUserId) {
        return;
      }
      let counterpart = payload.senderId;
      let sessionMsgs = state.chatdetail[counterpart];
      console.log(sessionMsgs);
      for (let i = sessionMsgs.length - 1; i >= 0; i--) {
        console.log(sessionMsgs[i]);

        if (sessionMsgs[i].receiverId == state.currentUserId) {
          continue;
        }
        if (sessionMsgs[i].read_time != 1000) {
          break;
        }
        sessionMsgs[i].read_time = payload.sent_time;
      }
    },
    setCurrentId(state, payload) {
      state.currentUserId = Number(payload);
      console.log(state.currentUserId);
    },
    setSocket(state, payload) {
      if (!state.connected) {
        state.socket = payload;
        state.connected = true;
      }
    },
    closeSocket(state) {
      if (state.connected) {
        state.connected = false;
        state.socket.close();
        state.socket = undefined;
      }
    },
    setStompClient(state, payload) {
      state.stompClient = payload;
    },
    addSession(state, payload) {
      //payload can be message itself or
      //{msg, counterpart}
      let counterpart = payload.counterpart;
      let data = payload.item;
      let chatlist = state.chatlist;
      console.log('addSession');
      console.log(counterpart);
      console.log(!(counterpart in chatlist));
      console.log(chatlist.value);
      if (!(counterpart in chatlist)) {
        console.log('create new session');
        chatlist[counterpart] = data;
      } else {
        console.log('add to existing session');
        if (chatlist[counterpart].id < data.id) {
          chatlist[counterpart] = data;
        }
      }
      console.log(state.chatlist);
      state.chatordered = Object.entries(state.chatlist).sort(
        (a, b) => b[1].id - a[1].id
      );
      console.log(state.chatordered);
      console.log(state.chatlist);
      console.log(state.chatlist);
      // console.log(state.chatlist);
    },
    initMessages(state, payload) {
      for (let i = 0; i < payload.length; i++) {
        console.log(payload[i]);
        payload[i].read_time = new Date(payload[i].read_time).getTime();
        payload[i].sent_time = new Date(payload[i].sent_time).getTime();
      }
      state.chatdetail[`${state.currentCounterpart}`] = payload;
    },
    addMessage(state, payload) {
      // let counterpart = Number(`${payload.counterpart}`);
      let counterpart = payload.counterpart;
      let msg = payload.item;
      console.log('addMessage');
      console.log(counterpart);
      console.log(counterpart + 3);
      console.log(payload);
      console.log(msg);
      console.log(state.chatdetail[counterpart]);
      if (!state.chatdetail[counterpart]) {
        state.chatdetail[counterpart] = [msg];
      } else {
        state.chatdetail[counterpart].push(msg);
      }
    },
    changeSession(state, payload) {
      state.currentCounterpart = payload;
    },
  },
  actions: {
    // 채팅 시작
    startChat({ commit }, body) {
      const res = axios
        .post(
          BASE_URL + '/api/chat/sessions/start',
          JSON.stringify(body),
          header
        )
        .then((res) => {
          console.log('채팅방 개설 결과');
          console.log(res);
          // console.log(res.data.content);
          // commit('updateTotalStudyList', res.data.content);
        });
      // return res;
    },
    cleanup({ commit }) {
      commit('cleanup');
    },
    startup({ dispatch, commit }, pk) {
      commit('setCurrentId', pk);
      dispatch('connectSocket');
      dispatch('initSession', pk);
      // alert("!")
    },

    sendMessage({ state }, msg) {
      if (!state.connected) {
        //예외처리
        return false;
      } else {
        state.stompClient.send(
          `/pub/${msg.receiverId}`,
          JSON.stringify(msg),
          {}
        );
        return true;
      }
    },
    getCounterpart({ state }, msg) {
      return msg.senderId != state.currentId ? msg.receiverId : msg.senderId;
    },
    connectSocket({ state, dispatch, commit }) {
      console.log('connect socket' + state.connected);
      //   const serverURL = 'http://localhost:8080/api/socket/chat'; // 서버 채팅 주소
      const serverURL = 'http://j5d105.p.ssafy.io:8080/api/socket/chat'; // 서버 채팅 주소
      let socket = new SockJS(serverURL);
      commit('setSocket', socket);

      // store.commit('stompSetter', Stomp.over(socket));
      console.log(`connecting to socket=> ${serverURL}`);
      let stompClient = Stomp.over(socket);
      commit('setStompClient', stompClient);
      stompClient.connect(
        {},
        (frame) => {
          // 구독 == 채팅방 입장.
          stompClient.subscribe('/sub/' + `${state.currentUserId}`, (res) => {
            const item = JSON.parse(res.body);
            console.log('stomp on');
            // item.read_time = new Date(item.read_time);
            // item.sent_time = new Date(item.sent_time);
            switch (item.type) {
              case 1: // 일반 메세지
                // let counterpart = dispatch('getCounterpart', item)
                // .then((res) =>{
                //     console.log(res);
                //     return res;
                // });
                let counterpart =
                  item.senderId == state.currentUserId
                    ? item.receiverId
                    : item.senderId;
                console.log(state.currentUserId);
                console.log(counterpart);
                console.log(counterpart.value);
                console.log(item);
                console.log('message done');
                // item.read_time = new Date(item.read_time);
                // item.sent_time = new Date(item.sent_time);

                commit('addSession', { item, counterpart });
                commit('addMessage', { item, counterpart });
                if (item.senderId == state.currentCounterpart) {
                  console.log('!!!!');
                  dispatch('sendMessage', {
                    type: 2,
                    senderId: state.currentUserId,
                    receiverId: state.currentCounterpart,
                    sent_time: 1000,
                    read_time: 1000,
                    content: '',
                  });
                  // 메세지 보내서 읽고있음을 알려주기
                  // unreadflag 설정 ㄴㄴ
                } else {
                  commit('setUnreadFlag', counterpart);
                }
                break;
              case 2:
                console.log('readSignal');
                if (item.receiverId == state.currentUserId) {
                  commit('readSignal', item);
                } else {
                  commit('readMyMessage', item);
                  commit('setReadFlag', item.receiverId);
                }
                break;
              default:
                break;
            }
          });
        },
        (error) => {
          console.log('stomperr');
          // 소켓 연결 실패
        }
      );
    },
    changeSession({ commit }, changeId) {
      commit('changeSession', changeId);
    },

    //     // 첫번째 인자: context를 조작하기 위한 명령어 종류
    //     // 두번째 인자: 넘길 값들(payload)
    //     // 여기서의 form은 SignIn.vue의 signIn 함수에서 dispatch로 보내는 state.form임.
    initSession({ commit, state }, currentId) {
      // currentId += 0;
      // currentId = "asdf";
      axios
        //   의도한 부분인가?? @김대연
        .get(
          // 'http://localhost:8080/api/chat/sessions/' + `${state.currentUserId}`,
          'http://j5d105.p.ssafy.io:8080/api/chat/sessions/' +
            `${state.currentUserId}`,
          // JSON.stringify(form),
          { headers: { 'Content-Type': 'application/json' } }
        )
        .then((response) => {
          console.log(response, state.currentId);
          response.data.forEach((item) => {
            let counterpart =
              item['senderId'] == state.currentUserId
                ? item['receiverId']
                : item['senderId'];
            console.log(item);
            item.read_time = new Date(item.read_time).getTime();
            item.sent_time = new Date(item.sent_time).getTime();

            if (
              item.receiverId == state.currentUserId &&
              item.read_time == 1000
            ) {
              let counterpart =
                item.senderId == state.currentUserId
                  ? item.receiverId
                  : item.senderId;
              this.commit('chat/setUnreadFlag', counterpart);
            }
            // console.log("------------");
            // console.log(state.currentUserId);
            // console.log(item['senderId']);
            // console.log(item['senderId']==state.currentUserId);
            // console.log(item['senderId']===state.currentUserId);
            // console.log(item);
            // console.log(counterpart);
            // console.log("------------");
            // console.log(item);
            commit('addSession', { counterpart, item });
          });
        });
    },
    loadMessages({ state, commit }) {
      console.log(`loadMessages`);
      const res = axios
        .get(
          // const serverURL = 'http://114.129.238.179:8080/api/socket/chat';
          // 'http://localhost:8080/api/chat/messages/' +
          'http://j5d105.p.ssafy.io:8080/api/chat/messages/' +
            `${state.currentUserId}` +
            '/' +
            `${state.currentCounterpart}`,
          // JSON.stringify(form),
          { headers: { 'Content-Type': 'application/json' } }
        )
        .then((response) => {
          console.log(response.data);
          commit('initMessages', response.data);
          console.log(response.data);
        });
    },
    loadMoreMessages({ state, commit }, pk) {
      console.log(`loadMessages`);
      const res = axios
        .get(
          // const serverURL = 'http://114.129.238.179:8080/api/socket/chat';
          // 'http://localhost:8080/api/chat/messages/' +
          'http://j5d105.p.ssafy.io:8080/api/chat/messages/' +
            `${state.currentUserId}` +
            '/' +
            `${state.currentCounterpart}` +
            '/' +
            pk,
          // JSON.stringify(form),
          { headers: { 'Content-Type': 'application/json' } }
        )
        .then((response) => {
          commit('initMessages', response.data);
          console.log(response.data);
        });
    },
    //       // swagger url경로
    //       const res = axios.post(
    //         BASE_URL + '/api/auth/login',
    //         JSON.stringify(form),
    //         header
    //       );
    //       console.log('바로 밑이 res!');
    //       console.log(res);
    //       console.log(JSON.stringify(form));
    //       console.log(form, '나... 로그인 된 걸지도?');
    //       return res;
    //     },
    //     // 여기서의 form은 SignUp.vue의 signUp 함수에서 dispatch로 보내는 state.form임.
    //     signUp({ commit }, form) {
    //       // swagger url경로
    //       const res = axios.post(
    //         BASE_URL + '/api/auth/signup',
    //         JSON.stringify(form),
    //         header
    //       );
    //       console.log(form, '나... 회원가입 한 걸지도?');
    //       return res;
    //     },
    //     // 여기서의 form은 SignUp.vue의 checkEmail 함수에서 dispatch로 보내는 state.form.email임.
    //     checkEmail({ commit }, form) {
    //       // swagger url경로
    //       const res = axios.get(BASE_URL + `/api/auth/check/email/${form}`);
    //       console.log(form, '나... 이메일 중복체크 한 걸지도?');
    //       return res;
    //     },
    //     // 여기서의 form은 SignUp.vue의 checkNickName 함수에서 dispatch로 보내는 state.form.nickname임.
    //     checkNickName({ commit }, form) {
    //       // swagger url경로
    //       const res = axios.get(BASE_URL + `/api/auth/check/nickname/${form}`);
    //       console.log(form, '나... 닉네임 중복체크 한 걸지도?');
    //       return res;
    //     },
  },
  modules: {},
};
