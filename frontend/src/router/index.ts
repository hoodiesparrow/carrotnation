import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

const Prod = () => import(/* webpackChunkName: "prod", webpackPrefetch: false */ '@/views/Prod.vue');
const Home = () => import(/* webpackChunkName: "home", webpackPrefetch: false */ '@/views/Home.vue');
const Test = () => import(/* webpackChunkName: "test", webpackPrefetch: false */ '@/views/Test.vue');
const Test2 = () => import(/* webpackChunkName: "test", webpackPrefetch: false */ '@/views/Test2.vue');
const Test3 = () => import(/* webpackChunkName: "test", webpackPrefetch: false */ '@/views/Test3.vue');
const Detail = () => import(/* webpackChunkName: "detail", webpackPrefetch: false */ '@/views/Detail.vue');
// const Test2 = () => import(/* webpackChunkName: "test2", webpackPrefetch: false */ '@/views/Test2.vue');

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
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
