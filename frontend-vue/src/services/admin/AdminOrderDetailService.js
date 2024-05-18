import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class OrderCheck {

  getOrder(orderId) {
    return http.get(`/admin/order-list/${orderId}`, {
      headers: LoginHeader(),
    });
  }
  getOrderDetail(orderId) {
    return http.get(`/admin/order-detail/${orderId}`, {
      headers: LoginHeader(),
    });
  }

  getOrderPrice(orderId) {
    return http.get(`/admin/order-price/${orderId}`, {
      headers: LoginHeader(),
    });
  }

  getOrderCode(orderId) {
    return http.get(`/admin/order-code/${orderId}`, {
      headers: LoginHeader(),
    });
  }
  // TODO: 취소 저장함수(insert)
  create(data) {
    console.log("통신",data)
    return http.post("/mypage/order-cancel", data);
  }

  // 상세조회
  get(orderId) {
    console.log("orderId", orderId)
    return http.get(`/mypage/order-orderId/${orderId}`);
  }
}

export default new OrderCheck();
