import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import Profile from "@/views/Profile.vue";
import Afisha from "@/views/Afisha.vue";
import Main from "@/views/Main.vue";
import PlaceInfo from "@/views/PlaceInfo.vue";
import ActivityInfo from "@/views/ActivityInfo.vue";

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
    path: "/place/:id",
    name: "PlaceInfo",
    component: PlaceInfo,
    props: true,
  },
  {
    path: "/activity/:id",
    name: "ActivityInfo",
    component: ActivityInfo,
    props: true,
  },


  {
    path: '/:pathMatch(.*)*',
    redirect: '/',
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
