// 리뷰활동
import http from "@/utils/http-common";

class MyReviewService {
  // 전체조회 공통함수
  getAll(page, size) {
    return http.get(`/mypage/review?page=${page}&size=${size}`);
  }
}

export default new MyReviewService();