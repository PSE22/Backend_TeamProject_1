import http from "@/utils/http-common";

class CouponService {
    // 쿠폰 전체 조회
    get(pdId) {
        return http.get(`/shop/coupon/${pdId}`)
    }
}

export default new CouponService();