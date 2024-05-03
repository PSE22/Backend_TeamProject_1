// User 공통 함수
import http from "@/utils/http-common";

class OrderService {
    // User 상세 조회
    getUser(userId) {
        return http.get(`/shop/order/${userId}`);
    }

    // 배송지 정보 상세 조회
    getShipAddress(userId) {
        return http.get(`/shop/order/ship-address/${userId}`)
    }

    // 회원 쿠폰 정보 조회
    getUserCoupon(userId) {
        return http.get(`/shop/order/user-coupon/${userId}`)
    }
}
export default new OrderService();