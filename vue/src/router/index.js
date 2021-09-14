import { createRouter, createWebHistory } from 'vue-router';
import Main from '../views/Main.vue';
import Home from '../components/Essence/Home.vue';
import Create from '../components/Essence/Create.vue';
import Update from '../components/Essence/Update.vue';
import Introduce from '../components/Essence/Introduce.vue';
import Manage from '../components/Essence/Manage.vue';
import ReadNotice from '../components/Essence/Notice/ReadNotice.vue';
import CreateNotice from '../components/Essence/Notice/CreateNotice.vue';

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
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
