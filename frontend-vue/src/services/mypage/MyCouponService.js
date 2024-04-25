import http from "@/utils/http-common";

class CouponService {
  // 전체조회 공통함수
  getAll(cpId, page, size) {
    return http.get(`/mypage?cpid=${cpId}&page=${page}&size=${size}`);
  }
}

export default new CouponService();
