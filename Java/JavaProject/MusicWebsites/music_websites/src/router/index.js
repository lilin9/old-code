import Vue from "vue";
import VueRouter from "vue-router";
import Index from '../pages/index'
import Music from '../pages/Music'
import MV from '../pages/MV'
import Radio from '../pages/Radio'
import Cartoon from '../pages/Cartoon'
import MusicPlay from '../pages/MusicPlay'

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Index",
    component: Index,
    redirect: '/music',
    children: [
      {
        path: "music",
        component: Music
      },
      {
        path: "mv",
        component: MV
      },
      {
        path: "radio",
        component: Radio
      },
      {
        path: "cartoon",
        component: Cartoon
      },
      {
        path: "music",
        component: Music
      },
      {
        path: "musicPlay",
        component: MusicPlay
      }
    ]
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
