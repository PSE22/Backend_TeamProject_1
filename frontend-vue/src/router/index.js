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
  // 회원가입
  {
    path: "/signup",
    component: () => import("../views/SignUpView.vue"),
  },
  // 관리자 주문 관리
  {
    path: "/admin-order",
    component: () => import("../views/admin/AdminOrderView.vue"),
    beforeEnter: [routeGuard],
  },
  {
    path: '/admin-order/:orderId',
    component: () => import('../views/admin/AdminOrderDetailView.vue'),
    beforeEnter: [routeGuard],
  },
  // 관리자 메인 / 상품 관리
  {
    path: "/admin-product",
    component: () => import("../views/admin/AdminProductView.vue"),
    beforeEnter: [routeGuard],
  },
  // 관리자 상품옵션 관리
  {
    path: "/admin-option",
    component: () => import("../views/admin/AdminProductView.vue"),
    beforeEnter: [routeGuard],
  },
  // 관리자 상품 수정/삭제
  {
    path: "/admin-product-edit/:pdId",
    component: () => import("../views/admin/AdminProductEdit.vue"),
    beforeEnter: [routeGuard],
  },
  // 관리자 상품옵션 관리
  {
    path: "/admin-option",
    component: () => import("../views/admin/AdminProductView.vue"),
    beforeEnter: [routeGuard],
  },
  // 관리자 상품옵션 수정/삭제
  {
    path: "/admin-option-edit/:opId",
    component: () => import("../views/admin/AdminOptionEdit.vue"),
    beforeEnter: [routeGuard],
  },
  // 관리자 상품이미지 관리
  {
    path: "/admin-product-image",
    component: () => import("../views/admin/AdminProductView.vue"),
    beforeEnter: [routeGuard],
  },
  // 관리자 상품이미지 수정/삭제
  {
    path: "/admin-product-image-edit/:pdImgId",
    component: () => import("../views/admin/AdminProductImageEdit.vue"),
    beforeEnter: [routeGuard],
  },
  // 관리자 쿠폰 관리
  {
    path: "/admin-coupon",
    component: () => import("../views/admin/AdminCouponView.vue"),
    beforeEnter: [routeGuard],
  },
  // 관리자 쿠폰 수정/삭제
  {
    path: "/admin-coupon-edit/:cpId",
    component: () => import("../views/admin/AdminCouponEdit.vue"),
    beforeEnter: [routeGuard],
  },
  // 관리자 통계
  {
    path: "/admin-chart",
    component: () => import("../views/admin/AdminChartView.vue"),
    beforeEnter: [routeGuard],
  },
  // 관리자 상품문의 관리
  {
    path: "/admin-pdqna",
    component: () => import("../views/admin/AdminPdQnaView.vue"),
    beforeEnter: [routeGuard],
  },
  // 관리자 상품문의 관리 수정/삭제
  {
    path: "/admin-pdqna-edit/:pdQnaId",
    component: () => import("../views/admin/AdminPdQnaEdit.vue"),
    beforeEnter: [routeGuard],
  },
  // 관리자 상품문의 답변 등록/취소
  {
    path: "/admin-pdqna-reply/:pdQnaId",
    component: () => import("../views/admin/AdminPdQnaReply.vue"),
    beforeEnter: [routeGuard],
  },
  // 관리자 상품문의 답변 수정/삭제
  {
    path: "/admin-pdqna-reply-edit/:pdQnaReplyId",
    component: () => import("../views/admin/AdminPdQnaReplyEdit.vue"),
    beforeEnter: [routeGuard],
  },
  // 관리자 상품리뷰 수정/삭제
  {
    path: "/admin-pdqna-review-edit/:reviewId",
    component: () => import("../views/admin/AdminPdReviewEdit.vue"),
    beforeEnter: [routeGuard],
  },
  // 장바구니
  {
    path: "/cart",
    component: () => import("../views/CartList.vue"),
  },
  // 주문
  {
    path: "/order",
    component: () => import("../views/OrderView.vue"),
  },
  // 카테고리 상세
  {
    path: "/category/:categoryCode",
    component: () => import("../views/CategoryDetail.vue"),
  },
  // 상품 검색 페이지
  {
    path: "/product-search",
    component: () => import("../views/SearchPage.vue"),
  },
  // 상품 상세
  {
    path: "/product/:pdId",
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
  // 이벤트 상세 페이지
  {
    path: "/event/:eventId",
    component: () => import("../views/EventDetailView.vue"),
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
  // 마이페이지 -> 주문상세
  // 주소 앞에 mypage 추가해야함
  {
    path: "/mypage/order/:orderId",
    component: () => import("../views/mypage/MyPageOrderDetail.vue"),
  },
  // 주문 취소
  {
    path: "/mypage/order/cancel/:orderId",
    component: () => import("../views/mypage/MyPageOrderCancel.vue"),
  },
  {
    path: "/mypage/order/refund/:orderId",
    component: () => import("../views/mypage/MyPageOrderRefund.vue"),
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
    path: "/mypage/editProfile",
    component: () => import("../views/mypage/MyPageInfo.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

// 데이터 가드()
// TODO: 메뉴 클릭시 인증된 유저가 아니면 다른 페이지로 강제 이동시킴, 인증된 유저이면 정상적으로 페이지이동됨
// TODO: routeGuard((이동후경로객체(to), 이동전경로객체(from), 이동시키는함수(next))
function routeGuard(to, from, next) {
  const user = JSON.parse(localStorage.getItem("user")); // 로컬스토리지에 값이 있으면

  // 이 경로에 user(로컬스토리지의 값) 가 없는 사람이 들어오면 로그인 페이지로 강제이동
  if (user === null) {
    next("/login"); // 이동불가 및 로그인페이지로 강제이동(라우터가드에서 사용가능) == push 비슷
  } else if (user !== null && user.userCode === "AT02") {
    next("/");
  } else {
    next(); // 이동 허가 및 정상적으로 다음 경로이 이동됨
  }
}

export default router;
