import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import Home from "../views/Home.vue";
import ItemPage from "../views/ItemPage.vue";
import Chart from "../views/Chart.vue";
import Prod from "../views/Prod.vue";
import Select from "@/views/Select.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/item",
    name: "Item",
    component: ItemPage,
  },
  {
    path: "/chart",
    name: "Chart",
    component: Chart,
  },
  {
    path: "/prod",
    name: "Prod",
    component: Prod,
  },
  {
    path: "/sel",
    name: "Select",
    component: Select,
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
