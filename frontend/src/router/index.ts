import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

const Prod = () => import(/* webpackChunkName: "prod", */ '@/views/Prod.vue');
const Home = () => import(/* webpackChunkName: "home", */ '@/views/Home.vue');
const Detail = () => import(/* webpackChunkName: "detail", */ '@/views/Detail.vue');
const Quote = () => import(/* webpackChunkName: "quote" */ '@/views/Quote.vue');

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/prod',
    name: 'Prod',
    component: Prod,
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
