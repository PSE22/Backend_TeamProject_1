import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class MyCouponService {
  // 전체조회 공통함수
  getAll(userId, page, size) {
    return http.get(`/mypage/coupon/${userId}?page=${page}&size=${size}`, {
      headers: LoginHeader(),
    });
  }

}

export default new MyCouponService();
