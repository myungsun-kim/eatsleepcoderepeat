import { createRouter, createWebHashHistory } from 'vue-router';
import Main from '../views/Main.vue';
import Home from '../components/Essence/Home.vue';

const routes = [
  {
    path: '/',
    name: 'Main',
    component: Main,
  },
  {
    path: '/Home',
    name: 'Home',
    component: Home,
  },
  // {
  //   path: "/about",
  //   name: "About",

  //   component: function () {
  //     return import(/* webpackChunkName: "about" */ "../views/About.vue");
  //   },
  // },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
