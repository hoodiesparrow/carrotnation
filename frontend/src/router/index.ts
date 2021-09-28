import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

const Prod = () => import(/* webpackChunkName: "prod" */ '@/views/Prod.vue');
const Home = () => import(/* webpackChunkName: "home" */ '@/views/Home.vue');
const Test = () => import(/* webpackChunkName: "test" */ '@/views/Test.vue');
const Detail = () => import(/* webpackChunkName: "detail" */ '@/views/Detail.vue');
// const Test2 = () => import(/* webpackChunkName: "test2" */ '@/views/Test2.vue');

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  // {
  //   path: '/chart',
  //   name: 'Chart',
  //   component: Chart,
  // },
  {
    path: '/prod',
    name: 'Prod',
    component: Prod,
  },
  {
    path: '/test',
    name: 'Test',
    component: Test,
  },
  {
    path: '/detail',
    name: 'Detail',
    component: Detail,
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
