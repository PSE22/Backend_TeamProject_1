import http from "@/utils/http-common";

class MyCouponService {
  // 전체조회 공통함수
  getAll(page, size) {
    return http.get(`/mypage/coupon?page=${page}&size=${size}`);
  }

}

export default new MyCouponService();
