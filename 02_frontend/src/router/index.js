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
    component: () => import("../views/LoginView.vue"),
  },
  // 아이디 찾기
  {
    path: "/find-id",
    component: () => import("../views/IdView.vue"),
  },
  // 비밀번호 찾기
  {
    path: "/find-password",
    component: () => import("../views/PasswordView.vue"),
  },
  // 회원가입
  {
    path: "/register",
    component: () => import("../views/RegisterView.vue"),
  },
  // 관리자 메인
  {
    path: "/admin-page",
    component: () => import("../views/AdminView.vue"),
  },
  // 장바구니
  {
    path: "/cart",
    component: () => import("../views/CartList.vue"),
  }, 
  // 카테고리 상세
  {
    path: "/category",
    component: () => import("../views/CategoryDetail.vue"),
  },
  // 상품 상세
  {
    path: "/product-detail",
    component: () => import("../views/ProductDetail.vue"),
  },
  // 베스트 상품 페이지
  {
    path: "/best-item",
    component: () => import("../views/BestItemView.vue"),
  },
  // 신상품 페이지
  {
    path: "/new-item",
    component: () => import("../views/NewItemView.vue"),
  },
  // 이벤트 페이지
  {
    path: "/event",
    component: () => import("../views/EventView.vue"),
  },

  // TODO: 마이페이지 
  // 마이페이지
  {
    path: "/mypage",
    component: () => import("../views/mypage/MyPageMain.vue"),
  },

  // 마이페이지 -> 주문조회
  {
    path: "/mypage/order",
    component: () => import("../views/mypage/MyPageOrder.vue"),
  },
  // 마이페이지 -> 쿠폰함
  {
    path: "/mypage/coupon",
    component: () => import("../views/mypage/MyPageCoupon.vue"),
  },
  // 마이페이지 -> 적립금
  {
    path: "/mypage/point",
    component: () => import("../views/mypage/MyPagePoint.vue"),
  },
  // 마이페이지 -> 위시리스트
  {
    path: "/mypage/wish",
    component: () => import("../views/mypage/MyPageWishList.vue"),
  },
  // 마이페이지 -> 1:1문의
  {
    path: "/mypage/inquiry",
    component: () => import("../views/mypage/MyPageInquiry.vue"),
  },
  // 마이페이지 -> 나의 리뷰 활동
  {
    path: "/mypage/review",
    component: () => import("../views/mypage/MyPageReview.vue"),
  },
  // 마이페이지 -> 회원정보 관리
  {
    path: "/mypage/info",
    component: () => import("../views/mypage/MyPageInfo.vue"),
  },

];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
