// User 공통 함수
import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class OrderService {
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
          })
    }

    // 회원 쿠폰 정보 조회
    getUserCoupon(userId) {
        return http.get(`/shop/order/user-coupon/${userId}`, {
            headers: LoginHeader(),
          })
    }

    // 회원의 포인트 잔액 조회
    getResultPoint(userId) {
        return http.get(`/shop/order/point/${userId}`, {
            headers: LoginHeader(),
          })
    }

    // 주문 데이터 저장
    create(data){
        return http.post(`/shop/order`, data, {
            headers: LoginHeader(),
          });
    }
}
export default new OrderService();