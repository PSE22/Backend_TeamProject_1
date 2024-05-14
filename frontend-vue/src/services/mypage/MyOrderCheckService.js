import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class OrderCheck {
  // TODO: 전체 조회
  // 조회(select) -> get 방식 -> @GetMapping
  getAllByDateRange(userId, startDate, endDate) {
    return http.get(`/mypage/ordercheck/${userId}?startDate=${startDate}&endDate=${endDate}`, {
      headers: LoginHeader(),
    });
  }

  getOrder(orderId) {
    return http.get(`/mypage/orderlist/${orderId}`, {
      headers: LoginHeader(),
    });
  }
  getOrderDetail(orderId) {
    return http.get(`/mypage/orderdetail/${orderId}`, {
      headers: LoginHeader(),
    });
  }
  
  getOrderPrice(orderId) {
    return http.get(`/mypage/orderprice/${orderId}`, {
      headers: LoginHeader(),
    });
  }

  getOrderCode(orderId) {
    return http.get(`/mypage/ordercode/${orderId}`, {
      headers: LoginHeader(),
    });
  }
}

export default new OrderCheck();
