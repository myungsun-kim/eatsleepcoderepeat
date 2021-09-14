import { createRouter, createWebHistory } from 'vue-router';
import Main from '../views/Main.vue';
import Home from '../components/Essence/Home.vue';
import Create from '../components/Essence/Create.vue';
import Update from '../components/Essence/Update.vue';
import Introduce from '../components/Essence/Introduce.vue';
import Manage from '../components/Essence/Manage.vue';
import GeneralSearch from '../components/Search/GeneralSearch.vue';
import AdvancedSearch from '../components/Search/AdvancedSearch.vue';

import ReadNotice from '../components/Essence/Notice/ReadNotice.vue';
import CreateNotice from '../components/Essence/Notice/CreateNotice.vue';

import Chat from '../components/Chat/Chat.vue';

import ReadMyPage from '../components/MyPage/ReadMyPage.vue';
import UpdateMyPage from '../components/MyPage/UpdateMyPage.vue';
import PasswordCheck from '../components/MyPage/PasswordCheck.vue';

import SignIn from '../components/Sign/SignIn.vue';
import SignUp from '../components/Sign/SignUp.vue';

const routes = [
  {
    path: '/',
    name: 'Main',
    component: Main,
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
  },
  {
    path: '/create',
    name: 'Create',
    component: Create,
  },
  {
    path: '/update',
    name: 'Update',
    component: Update,
  },
  {
    path: '/introduce',
    name: 'Introduce',
    component: Introduce,
  },
  {
    path: '/manage',
    name: 'Manage',
    component: Manage,
  },
  {
    path: '/notice',
    name: 'ReadNotice',
    component: ReadNotice,
    children: [
      {
        path: '/read',
        name: 'ReadNotice',
        component: ReadNotice,
      },
      {
        path: '/create',
        name: 'CreateNotice',
        component: CreateNotice,
      },
    ],
  },
  //search도 children 문제 해결하면 reroute할 것
  {
    path: '/generalsearch',
    name: 'GeneralSearch',
    component: GeneralSearch,
  },
  {
    path: '/advancedsearch',
    name: 'AdvancedSearch',
    component: AdvancedSearch,
  },
  {
    path: '/chat',
    name: 'Chat',
    component: Chat,
  },
  {
    path: '/readmypage',
    name: 'ReadMyPage',
    component: ReadMyPage,
  },
  {
    path: '/updatemypage',
    name: 'UpdateMyPage',
    component: UpdateMyPage,
  },
  {
    path: '/passwordcheck',
    name: 'PasswordCheck',
    component: PasswordCheck,
  },
  {
    path: '/signin',
    name: 'SignIn',
    component: SignIn,
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
