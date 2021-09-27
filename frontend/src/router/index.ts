import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
function importComp(view: string) {
  return () => import(/* webpackChunkName: "view-[request]" */ `@/views/${view}.vue`)
}
const Prod = () => import(/* webpackChunkName: "prod" */ '@/views/Prod.vue');
const Home = () => import(/* webpackChunkName: "home" */ '@/views/Home.vue');
const Test = () => import(/* webpackChunkName: "test" */ '@/views/Test.vue');
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
  // {
  //   path: '/test2',
  //   name: 'Test2',
  //   component: Test2,
  // },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
