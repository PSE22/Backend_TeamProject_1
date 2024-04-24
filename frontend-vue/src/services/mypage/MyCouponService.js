import http from "@/utils/http-common";

class CouponService {
  // 전체조회 공통함수
  getAll(cpId, page, size) {
    return http.get(`/mypage?cpid=${cpId}&page=${page}&size=${size}`);
  }
  // 유저 상세 조회 : 기본키(상품번호(spno))
  get(userId) {
    // 조회(select) -> get 방식 -> @GetMapping
    return http.get(`/mypage/${userId}`);
  }
}

export default new CouponService();
