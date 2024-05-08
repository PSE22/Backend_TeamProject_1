// 문의내역
import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";


class MyInquiryService {
  // 전체조회 공통함수
  getAll(userId, page, size) {
    console.log(userId, page, size)

    return http.get(`/mypage/inquiry/${userId}?page=${page}&size=${size}`, {
      headers: LoginHeader(),
    });
  }
}

export default new MyInquiryService();
