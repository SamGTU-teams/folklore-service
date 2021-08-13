import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import Profile from "@/views/Profile.vue";
import Afisha from "@/views/Afisha.vue";
import Main from "@/views/Main.vue";
import BuildingInfo from "@/views/BuildingInfo.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "Main",
    component: Main,
  },
  {
    path: "/afisha",
    name: "Afisha",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    component: Afisha,
  },
  {
    path: "/profile",
    name: "Profile",
    component: Profile,
  },
  {
    path: "/object/:id",
    name: "BuildingInfo",
    component: BuildingInfo,
    props: true,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
