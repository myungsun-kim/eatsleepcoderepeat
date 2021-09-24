import { createRouter, createWebHistory } from 'vue-router';
import Header from '../views/Header.vue';

// sub header 시작
import SubHeader from '../components/SubHeader.vue';
import Introduce from '../components/MenuBar1/Introduce.vue';
import CreateNotice from '../components/MenuBar1/Notice/CreateNotice.vue';
import ReadNotice from '../components/MenuBar1/Notice/ReadNotice.vue';
import ReadDetailNotice from '../components/MenuBar1/Notice/ReadDetailNotice.vue';
import UpdateNotice from '../components/MenuBar1/Notice/UpdateNotice.vue';
import CreateBoard from '../components/MenuBar1/Board/CreateBoard.vue';
import ReadBoard from '../components/MenuBar1/Board/ReadBoard.vue';
import ReadDetailBoard from '../components/MenuBar1/Board/ReadDetailBoard.vue';
import UpdateBoard from '../components/MenuBar1/Board/UpdateBoard.vue';
import Manage from '../components/MenuBar1/Manage.vue';
// sub header 끝

// no sub header 시작
import NoSubHeader from '../components/NoSubHeader.vue';
import Main from '../components/Main.vue';
import Home from '../components/MenuBar2/Home.vue';
import Create from '../components/MenuBar2/Create.vue';
import Update from '../components/MenuBar2/Update.vue';
import GeneralSearch from '../components/Search/GeneralSearch.vue';
import AdvancedSearch from '../components/Search/AdvancedSearch.vue';
import Chat from '../components/Chat.vue';
import ReadMyPage from '../components/MyPage/ReadMyPage.vue';
import UpdateMyPage from '../components/MyPage/UpdateMyPage.vue';
import PasswordCheck from '../components/MyPage/PasswordCheck.vue';
// no sub header 끝

import NoHeader from '../views/NoHeader.vue';
import SignIn from '../components/Sign/SignIn.vue';
import SignUp from '../components/Sign/SignUp.vue';

import NotFound from '../views/NotFound.vue';
const routes = [
  // 1-1. Top Nav Bar가 있는 경우: Header
  {
    path: '/',
    component: Header,
    children: [
      {
        path: '',
        component: Main,
      },
      {
        path: '/subheader',
        component: SubHeader,
        // 2-1. sub header가 있는 경우: NoSubHeader
        children: [
          {
            path: '',
            component: Main,
          },
          {
            path: '/subheader/introduce',
            component: Introduce,
          },
          {
            path: '/subheader/notice',
            component: ReadNotice,
          },
          {
            path: '/subheader/notice/create',
            component: CreateNotice,
          },
          {
            path: '/subheader/notice/read',
            component: ReadNotice,
          },
          {
            path: '/subheader/notice/detail',
            component: ReadDetailNotice,
          },
          {
            path: '/subheader/notice/update',
            component: UpdateNotice,
          },
          {
            path: '/subheader/board',
            component: ReadBoard,
          },
          {
            path: '/subheader/board/detail',
            component: ReadDetailBoard,
          },
          {
            path: '/subheader/board/create',
            component: CreateBoard,
          },
          {
            path: '/subheader/board/read',
            component: ReadBoard,
          },
          {
            path: '/subheader/notice/detail',
            component: ReadDetailNotice,
          },
          {
            path: '/subheader/board/update',
            component: UpdateBoard,
          },
          {
            path: '/subheader/manage',
            component: Manage,
          },
        ],
      },
      {
        path: '/nosubheader',
        component: NoSubHeader,
        // 2-2. sub header가 없는 경우: NoSubHeader
        children: [
          {
            path: '',
            component: Main,
          },
          {
            path: '/nosubheader/main',
            component: Main,
          },
          {
            path: '/nosubheader/home',
            component: Home,
          },
          {
            path: '/nosubheader/create',
            component: Create,
          },
          {
            path: '/nosubheader/update',
            component: Update,
          },

          {
            path: '/nosubheader/generalsearch',
            component: GeneralSearch,
          },
          {
            path: '/nosubheader/advancedsearch',
            component: AdvancedSearch,
          },
          {
            path: '/nosubheader/chat',
            component: Chat,
          },
          {
            path: '/nosubheader/readmypage',
            component: ReadMyPage,
          },
          {
            path: '/nosubheader/updatemypage',
            component: UpdateMyPage,
          },
          {
            path: '/nosubheader/passwordcheck',
            component: PasswordCheck,
          },
        ],
      },
    ],
  },

  //1-2. Top Nav Bar가 없는 경우: NoHeader
  {
    path: '/noheader',
    component: NoHeader,
    children: [
      {
        path: '',
        component: SignIn,
      },
      {
        path: '/noheader/signin',
        component: SignIn,
      },
      {
        path: '/noheader/signup',
        component: SignUp,
      },
    ],
  },
  {
    path: '/:catchAll(.*)',
    component: NotFound,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

// const router = createRouter({
//   history: createWebHistory(process.env.PATH),
//   // base: 'configure-admin', <-- this does not work in vue 3
//   routes: routes,
//   linkActiveClass: 'active',
// });

export default router;
