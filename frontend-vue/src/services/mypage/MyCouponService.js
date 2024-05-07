import http from "@/utils/http-common";

class MyCouponService {
  // 전체조회 공통함수
  getAll(userId, page, size) {
    return http.get(`/mypage/coupon/${userId}?page=${page}&size=${size}`);
  }

}

export default new MyCouponService();
