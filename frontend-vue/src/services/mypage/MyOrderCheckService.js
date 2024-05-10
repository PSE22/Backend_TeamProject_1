import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class OrderCheck {
  // TODO: 전체 조회
  // 조회(select) -> get 방식 -> @GetMapping
  getAll(userId) {
    return http.get(`/mypage/ordercheck/${userId}`, {
      headers: LoginHeader(),
    });
  }

  getOrder(orderId) {
    return http.get(`/mypage/orderdetail/${orderId}`, {
      headers: LoginHeader(),
    });
  }
  getOrderList(orderId) {
    return http.get(`/mypage/orderlist/${orderId}`, {
      headers: LoginHeader(),
    });
  }
}

export default new OrderCheck();
