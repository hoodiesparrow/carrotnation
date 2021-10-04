import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

const Prod = () => import(/* webpackChunkName: "prod", */ '@/views/Prod.vue');
const Home = () => import(/* webpackChunkName: "home", */ '@/views/Home.vue');
const Test = () => import(/* webpackChunkName: "test", */ '@/views/Test.vue');
const Test2 = () => import(/* webpackChunkName: "test", */ '@/views/Test2.vue');
const Test3 = () => import(/* webpackChunkName: "test", */ '@/views/Test3.vue');
const Detail = () => import(/* webpackChunkName: "detail", */ '@/views/Detail.vue');
const Quote = () => import(/* webpackChunkName: "quote" */ '@/views/Quote.vue');

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
    path: '/test2',
    name: 'Test2',
    component: Test2,
  },
  {
    path: '/test3',
    name: 'Test3',
    component: Test3,
  },
  {
    path: '/detail',
    name: 'Detail',
    component: Detail,
  },
  {
    path: '/quote',
    name: 'Quote',
    component: Quote,
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
