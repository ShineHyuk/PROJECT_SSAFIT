import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import LoginForm from "@/components/LoginForm.vue";
import User from "../views/User.vue";
import UserList from "@/components/user/UserList.vue";
import UserRegist from "@/components/user/UserRegist.vue";
import UserDetail from "@/components/user/UserDetail.vue";
import UserSearch from "@/components/user/UserSearch.vue";

// board
import Board from "../views/Board.vue";
import BoardList from "@/components/board/BoardList.vue";
import BoardDetail from "@/components/board/BoardDetail.vue";
import BoardWrite from "@/components/board/BoardWrite.vue";
import BoardUpdate from "@/components/board/BoardUpdate.vue";
import SearchMap from "@/components/board/SearchMap.vue";

// notice
import Notice from "../views/Notice.vue";
import NoticeList from "@/components/notice/NoticeList.vue";
import NoticeDetail from "@/components/notice/NoticeDetail.vue";
import NoticeWrite from "@/components/notice/NoticeWrite.vue";
import NoticeUpdate from "@/components/notice/NoticeUpdate.vue";

// video
import Video from "../views/Video.vue";
import VideoList from "@/components/video/VideoList.vue";
import VideoDetail from "@/components/video/VideoDetail.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/login",
    name: "Login",
    component: LoginForm,
  },
  {
    path: "/user",
    component: User,
    children: [
      {
        path: "regist",
        name: "Regist",
        component: UserRegist,
      },
      {
        path: "",
        name: "List",
        component: UserList,
      },
      {
        path: "search",
        name: "Search",
        component: UserSearch,
      },
      {
        path: ":id",
        name: "Detail",
        component: UserDetail,
      },
    ],
  },
  // board
  {
    path: "/board",
    component: Board,
    children: [
      {
        path: "",
        name: "BoardList",
        component: BoardList,
      },
      {
        path: "write",
        name: "BoardWrite",
        component: BoardWrite,
      },
      {
        path: ":boardId",
        name: "BoardDetail",
        component: BoardDetail,
      },

      {
        path: "update",
        name: "BoardUpdate",
        component: BoardUpdate,
      },
    ],
  },

  // notice
  {
    path: "/notice",
    component: Notice,
    children: [
      {
        path: "",
        name: "NoticeList",
        component: NoticeList,
      },
      {
        path: "write",
        name: "NoticeWrite",
        component: NoticeWrite,
      },
      {
        path: ":noticeId",
        name: "NoticeDetail",
        component: NoticeDetail,
      },

      {
        path: "update",
        name: "NoticeUpdate",
        component: NoticeUpdate,
      },
    ],
  },

  {
    path: "/searchMap",
    name: "SearchMap",
    component: SearchMap,
  },

  // video
  {
    path: "/video",
    component: Video,
    children: [
      {
        path: "",
        name: "VideoList",
        component: VideoList,
      },
      {
        path: ":videoId",
        name: "VideoDetail",
        component: VideoDetail,
        props: true,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
