<template>
  <el-row class="height5">
    <el-col :span="24" class="test-border"> Chat여백: </el-col>
  </el-row>
  <el-row class="height90">
    <!-- 채팅방 목록 -->
    <el-col :span="5" :offset="1" class="test-border chat-list">
      <el-row class="height5"> </el-row>

      <el-row
        v-for="(session, index) in chatList"
        :key="index"
        class="height10 chat-list-item"
        @click="changeSession(session[1])"
      >
        <!-- 보내는사람: {{ session[1].senderId }} <br /> -->
        <el-col :span="4" class="flex-items">사진</el-col>
        <el-col :span="16">
          <el-row class="height50 flex-items chat-list-item-title">
            받는 사람: {{ session[1].receiverId }}
          </el-row>
          <el-row class="height50 flex-items chat-list-item-msg">
            내용: {{ session[1].content }}
          </el-row>
        </el-col>
        <el-col :span="4" class="chat-list-item-msg"
          >읽은 시간: {{ session[1].read_time }}</el-col
        >
      </el-row>
    </el-col>
    <el-tabs
      type="card"
      v-model="status"
      @tab-click="handleClick"
      style="margin-top: 10px"
    >
      <!-- <el-tab-pane label="진행" name="LIVE">
          <template #label>진행<el-badge :value="count[0]" class="badge"/></template>
        </el-tab-pane> -->
      <!-- <el-tab-pane label="대기" name="OPEN">
          <template #label>대기<el-badge :value="count[1]" class="badge"/></template>
        </el-tab-pane>
        <el-tab-pane label="종료" name="END">
          <template #label>종료<el-badge :value="count[2]" class="badge"/></template>
        </el-tab-pane> -->
    </el-tabs>
    <el-scrollbar style="max-height: 87vh">
      <div
        v-for="room in listStatus"
        :key="room.session.session_id"
        class="noborder"
      >
        <div
          @click="pickRoom(room.session.session_id)"
          class="list-item box-card"
          :class="{ selected: room.session.session_id == selectedSession }"
        >
          <ChatItem :room="room" />
        </div>
      </div>
    </el-scrollbar>
    <!-- 채팅창 -->
    <el-col :span="17" class="test-border chat-detail">
      <div class="chat-log">
        <!-- 채팅 내역 -->
        <el-row v-for="msg in chatMessages" :key="msg.id" class="height10">
          <el-col
            v-if="msg.receiverId == currentId && msg.type == 1"
            class="other-msg"
          >
            {{ msg.content }}
            {{ msg.sent_time }}
            {{ msg.read_time }}
          </el-col>
          <el-col
            v-if="msg.receiverId != currentId && msg.type == 1"
            class="my-msg"
          >
            {{ msg.content }} @ {{ new Date(msg.sent_time) }} 이건 substr(숫자,
            숫자)으로 해결 가능@
            {{ msg.read_time }}
            <!-- {{msg.read_time.getTime()}} -->
            <p v-if="msg.read_time == 1000">읽지않음</p>
          </el-col>
        </el-row>
      </div>
      <div>
        <!-- 검색창 -->
        <el-row>
          <el-col :span="20">
            <div>
              <el-input
                type="text"
                @keyup.enter="sendMessage"
                v-model="message"
                placeholder="메시지를 입력해주세요:)"
                clearable
              >
              </el-input>
            </div>
          </el-col>
          <el-col :span="2">
            <el-button
              @click="sendMessage"
              icon="el-icon-s-promotion"
              class="icon-m-p green-color-btn"
            ></el-button>
          </el-col>
        </el-row>
      </div>
      <!-- 검색창 끝 -->
    </el-col>
  </el-row>
</template>

<script>
import { useStore } from 'vuex';
import { onMounted, ref, computed } from 'vue';

export default {
  components: {},

  setup() {
    // created(() => {
    //   console.log(this);
    //   }),
    onMounted(() => {
      // initSession();
      // connect();
      // 현재 보고 있다는 신호
    });
    // onUnmounted(() => {
    //   // 현재 보고 있지 않다는 신호
    // });
    const currentId = computed(() => store.getters['chat/getCurrentUserId']);
    const currentCounterpart = computed(
      () => store.getters['chat/getCurrentCounterpart']
    );
    const message = ref('');
    const stompClient = ref('');
    const currentCounterPart = ref('');
    const chatList = computed(() => store.getters['chat/getChatList']);
    const chatMessages = computed(() => store.getters['chat/getMessages']);

    // const chatList = [{content:"123"}, {content:"456"}];
    const store = useStore();
    console.log('currentId: ' + currentId.value);

    const sendMessage = () => {
      store.dispatch('chat/sendMessage', {
        type: 1,
        senderId: currentId.value,
        receiverId: 47,
        // receiverId: currentCounterpart.value,
        sent_time: 1000,
        read_time: 1000,
        content: message.value,
      });
      message.value = '';
    };
    const sendReadSignal = () => {};

    const loadMessages = () => {
      // store.dispatch('chat/loadMessages');
    };

    const connect = () => {};
    const changeSession = (msg) => {
      console.log(msg);
      let counter = getCounterPart(msg);
      store.dispatch('chat/changeSession', counter).then(() => {
        console.log('chatmessage');
        console.log(chatMessages.value);
        if (!chatMessages.value) {
          store.dispatch('chat/loadMessages').then(() => {
            console.log(counter);
            store.dispatch('chat/sendMessage', {
              type: 2,
              senderId: currentId.value,
              receiverId: currentCounterpart.value,
              sent_time: 1000,
              read_time: 1000,
              content: '',
            });
          });
        } else {
          store.dispatch('chat/sendMessage', {
            type: 2,
            senderId: currentId.value,
            receiverId: currentCounterpart.value,
            sent_time: 1000,
            read_time: 1000,
            content: '',
          });
        }
      });
      // load session
    };
    const getCounterPart = (msg) => {
      return msg.senderId == currentId.value ? msg.receiverId : msg.senderId;
    };

    return {
      /*
        Variables
      */
      message,
      store,
      stompClient,
      currentId,
      chatList,
      chatMessages,
      currentCounterPart,

      /*
        Functions
      */
      connect,
      sendMessage,
      sendReadSignal,
      loadMessages,
      changeSession,
      getCounterPart,
    };
  },
};
</script>
<style scoped>
.chat-list-item {
  background-color: #ffffff;
}
.chat-list-item-title {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
}
.chat-list-item-msg {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 12px;
  color: rgba(0, 0, 0, 0.5);
}

.chat-list {
  background-color: #ffffff;
  border-radius: 15px 0px 0px 15px;
}

.chat-detail {
  background: #f6f5f8;
  border-radius: 0px 15px 15px 0px;
  display: flex;
  flex-flow: column;
  justify-content: space-between;
}

.chat-log {
  display: flex;
  flex-flow: column;
  justify-content: space-between;
}

.other-msg {
  border-radius: 15px 15px 0px 15px;
  text-align: left;
  background: #ffffff;
  color: #000000;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 20px;
}
.my-msg {
  border-radius: 15px 0px 15px 15px;
  text-align: right;
  background: #7b9dff;
  color: #ffffff;

  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 20px;
}
</style>
