import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class MyPageService {
  // 주문 카운트
  getOrderCnt(userId) {
    return http.get(`/mypage/ordercnt/${userId}`, {
      headers: LoginHeader(),
    });
  }
  // 쿠폰 카운트
  getCouponCnt(userId) {
    return http.get(`/mypage/couponcnt/${userId}`, {
      headers: LoginHeader(),
    });
  }
  // 리뷰 카운트
  getReviewCnt(userId) {
    return http.get(`/mypage/reviewcnt/${userId}`, {
      headers: LoginHeader(),
    });
  }
  // 문의 카운트
  getInquiryCnt(userId) {
    return http.get(`/mypage/inquirycnt/${userId}`, {
      headers: LoginHeader(),
    });
  }
  // 회원명
  getUserName(userId) {
    return http.get(`/mypage/username/${userId}`, {
      headers: LoginHeader(),
    });
  }
  // 적립금
  getUserPoint(userId) {
    return http.get(`/shop/order/point/${userId}`, {
      headers: LoginHeader(),
    });
  }
  // 오더코드
  getOrderCode(userId) {
    return http.get(`/mypage/ordercode/${userId}`, {
      headers: LoginHeader(),
    });
  }
}

export default new MyPageService();
