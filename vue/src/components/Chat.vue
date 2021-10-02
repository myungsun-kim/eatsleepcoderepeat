<template>
  <el-row class="height5">
    <el-col :span="24" class="test-border"> Chat여백: </el-col>
  </el-row>
  <input v-model="toId" @keyup.enter="changeMyId">
  <el-row class="height90">
    <!-- 채팅방 목록 -->
    <el-col :span="5" :offset="1" class="test-border">
      <el-row v-for="session in chatList" 
              :key="session[0]" 
              class="height10"
              @click = "changeSession(session[1])">
        {{session[1].content}}
        {{session[1].senderId}}
        {{session[1].receiverId}}
        {{session[1].read_time}}
        
      </el-row>
      <!-- <el-row class="height10">ROOM 2</el-row>
      <el-row class="height10">ROOM 3</el-row>
      <el-row class="height10">ROOM 4</el-row>
      <el-row class="height10">ROOM 5</el-row>
      <el-row class="height10">ROOM 6</el-row>
      <el-row class="height10">ROOM 7</el-row>
      <el-row class="height10">ROOM 8</el-row>
      <el-row class="height10">ROOM 9</el-row>
      <el-row class="height10">ROOM 10</el-row> -->
    </el-col>
      <el-tabs type="card" v-model="status" @tab-click="handleClick" style="margin-top: 10px;">
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
      <el-scrollbar style="max-height: 87vh;">
        <div v-for="room in listStatus" :key="room.session.session_id" class="noborder">
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
    <el-col :span="17" class="test-border">
      <el-row v-for="msg in chatMessages" :key="msg.id" class="height10">
        <el-col v-if="msg.receiverId == currentId && msg.type==1">
          {{msg.content}}
          {{msg.sent_time}}
          {{msg.read_time}}
          right
        </el-col>
        <el-col v-if="msg.receiverId != currentId && msg.type==1">
          left
          {{msg.content}}
          {{msg.sent_time}}
          {{msg.read_time}}
          <!-- {{msg.read_time.getTime()}} -->
          <p v-if="msg.read_time == 1000">읽지않음</p>
        </el-col>
      </el-row>
      
      
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
    </el-col>
  </el-row>
</template>

<script>
import { useStore } from 'vuex';
import {created, onMounted, ref, computed} from 'vue';



export default {
  components: {},
  created(){
    console.log("!!!!!!!!!!!!!!!!!!!!!!!")
    console.log("!!!!!!!!!!!!!!!!!!!!!!!")
    console.log("!!!!!!!!!!!!!!!!!!!!!!!")
    console.log("!!!!!!!!!!!!!!!!!!!!!!!")
    console.log("!!!!!!!!!!!!!!!!!!!!!!!")
  },
  setup(){
    // created(() => {
    //   console.log(this);

    //   }),
    onMounted(() => {
      window.addEventListener('beforeunload', (e)=>{
        e.preventDefault();
        store.dispatch('chat/cleanup');
        e.returnValue = '!';
        localStorage.clear();
      });
      console.log(chatList);
      store.dispatch('chat/connectSocket');
      store.dispatch('chat/initSession', currentId);
      // initSession();
      // connect();
      // 현재 보고 있다는 신호
    });
    // onUnmounted(() => {
    //   // 현재 보고 있지 않다는 신호
    // });
    const currentId = computed(() => store.getters['chat/getCurrentUserId']);
    const currentCounterpart = computed(() => store.getters['chat/getCurrentCounterpart']);
    const message = ref('');
    const stompClient = ref('');
    const currentCounterPart = ref('');
    const chatList = computed(() => store.getters['chat/getChatList']);
    const chatMessages = computed(() => store.getters['chat/getMessages']);
    
    const toId = ref('');

    const cleanup = (e) => {
      e.preventDefault();
      return "asdf";
    };

    const changeMyId = () => {
      console.log(toId.value);
      store.commit("chat/setCurrentId", toId.value);
    };

    // const chatList = [{content:"123"}, {content:"456"}];
    const store = useStore();


    const sendMessage = () => {
      store.dispatch('chat/sendMessage', {
        type : 1,
        senderId : currentId.value,
        receiverId : currentCounterpart.value,
        sent_time : 1000,
        read_time : 1000,
        content : message.value,
      });
      message.value = "";
    };
    const sendReadSignal = () => {

    };

    const loadMessages = () => {
      // store.dispatch('chat/loadMessages');
    };

    const connect = () => {

    };
    const changeSession = (msg) => {
      console.log(msg);
      let counter = getCounterPart(msg);
      store.dispatch('chat/changeSession', counter)
      .then(()=>{
        console.log("chatmessage")
        console.log(chatMessages.value);
        if(!chatMessages.value){
          store.dispatch('chat/loadMessages')
          .then(()=>{
            console.log(counter);
            store.dispatch('chat/sendMessage', {
              type : 2,
              senderId : currentId.value,
              receiverId : currentCounterpart.value,
              sent_time : 1000,
              read_time : 1000,
              content : "",
            });
          });
        }
        else{
            store.dispatch('chat/sendMessage', {
              type : 2,
              senderId : currentId.value,
              receiverId : currentCounterpart.value,
              sent_time : 1000,
              read_time : 1000,
              content : "",
            });
        }
      });
      // load session
    };
    const getCounterPart = (msg) => {
      return msg.senderId == currentId.value ?
                  msg.receiverId :
                  msg.senderId;
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
      toId, 
      changeMyId,
      
      /*
        Functions
      */
      connect,
      sendMessage,
      sendReadSignal,
      loadMessages,
      changeSession,
      getCounterPart,
      cleanup,
      

    };
  }
};
</script>
