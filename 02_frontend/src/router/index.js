import { createRouter, createWebHistory } from "vue-router";

const routes = [
  // Home
  {
    path: "/",
    component: () => import("../views/HomeView.vue"),
  },
  // 로그인
  {
    path: "/login",
    component: function () {
      return import("../views/LoginView.vue");
    },
  },
  // 아이디 찾기
  {
    path: "/find-id",
    component: function () {
      return import("../views/IdView.vue");
    },
  },
  // 비밀번호 찾기
  {
    path: "/find-password",
    component: function () {
      return import("../views/PasswordView.vue");
    },
  },
  // 회원가입
  {
    path: "/register",
    component: function () {
      return import("../views/RegisterView.vue");
    },
  },
  // 마이페이지
  {
    path: "/mypage",
    component: () => import("../views/MyPageMain.vue"),
  },
  // 카테고리 상세
  {
    path: "/category",
    component: () => import("../views/CategoryDetail.vue"),
  },
  
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
