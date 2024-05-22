// User 공통 함수
import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class OrderService {
  // 주문 데이터 저장
  create(data) {
    return http.post(`/shop/order`, data, {
      headers: LoginHeader(),
    });
  }

  // User 상세 조회
  getUser(userId) {
    return http.get(`/shop/order/${userId}`, {
      headers: LoginHeader(),
    });
  }

  // 배송지 정보 상세 조회
  getShipAddress(userId) {
    return http.get(`/shop/order/ship-address/${userId}`, {
      headers: LoginHeader(),
    });
  }

  // 회원 쿠폰 정보 조회
  getUserCoupon(userId) {
    return http.get(`/shop/order/user-coupon/${userId}`, {
      headers: LoginHeader(),
    });
  }

  // 사용 쿠폰 상태 'N' 으로 수정
  updateUserCoupon(cpId, userId) {
    return http.put(`/shop/order/user-coupon/${cpId}/${userId}`, {
      headers: LoginHeader(),
    });
  }


  // 회원의 포인트 잔액 조회
  getResultPoint(userId) {
    return http.get(`/shop/order/point/${userId}`, {
      headers: LoginHeader(),
    });
  }

  // 사용 포인트 저장
  createUsePoint(data) {
    return http.post("/shop/order/point", data, {
      headers: LoginHeader(),
    });
  }

  // 배송지 저장
  createShipAddress(data) {
    return http.post("/shop/order/ship-address", data, {
      headers: LoginHeader(),
    });
  }

  // 장바구니 조회
  getCart(userId) {
    return http.get(`/shop/order/cart/${userId}`, {
        headers: LoginHeader(),
      });
  }

  // 장바구니 삭제
  removeCart(cartId) {
    return http.delete(`/shop/order/deletion/cart/${cartId}`, {
      headers: LoginHeader(),
    });
  }
}
export default new OrderService();
