// SimpleProductService.js
// axios CRUD 공통 함수
// 상품 공통 함수
import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class MyPointService {
  // TODO: 전체 조회
  getAll(userId) {
    return http.get(`/mypage/point/${userId}`  ,{
      headers: LoginHeader()
  });
  }

    // 특정 기간의 적립금 내역 조회
    getPointsForPeriod(userId, startDate, endDate) {
      return http.get(`/mypage/point/period/${userId}`, {
        params: {
          startDate,
          endDate
        },
        headers: LoginHeader()
      });
    }
}



export default new MyPointService();
