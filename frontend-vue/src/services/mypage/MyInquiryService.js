// 문의내역
import http from "@/utils/http-common";

class MyInquiryService {
  // 전체조회 공통함수
  getAll( page, size) {
    return http.get(`/mypage/inquiry?page=${page}&size=${size}`);
  }
}

export default new MyInquiryService();