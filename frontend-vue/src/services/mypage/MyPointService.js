// SimpleProductService.js
// axios CRUD 공통 함수
// 상품 공통 함수
import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class MyPointService {


    // 특정 기간의 적립금 내역 조회
    getPointsForPeriod(userId, startDate, endDate) {
      return http.get(`/mypage/point/${userId}?startDate=${startDate}&endDate=${endDate}`, {
        headers: LoginHeader()
      });
    }

    // 잔액 조회
    getPointBalance(userId) {
      return http.get(`/shop/order/point/${userId}`, {
        headers: LoginHeader(),
      });
    }
}



export default new MyPointService();
