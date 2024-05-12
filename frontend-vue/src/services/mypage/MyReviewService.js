// 리뷰활동
import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";


class MyReviewService {
  // 전체조회 공통함수
  getAll(userId, page, size) {
    console.log(userId, page, size)
    return http.get(`/mypage/review/${userId}?page=${page}&size=${size}`, {
      headers: LoginHeader(),
    });
  }
}

export default new MyReviewService();