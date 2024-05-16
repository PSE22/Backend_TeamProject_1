import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class OrderCheck {
  // TODO: 전체 조회
  // 조회(select) -> get 방식 -> @GetMapping
  getAllByDateRange(userId, startDate, endDate) {
    return http.get(
      `/mypage/ordercheck/${userId}?startDate=${startDate}&endDate=${endDate}`,
      {
        headers: LoginHeader(),
      }
    );
  }

  getOrder(orderId) {
    return http.get(`/mypage/order-list/${orderId}`, {
      headers: LoginHeader(),
    });
  }
  getOrderDetail(orderId) {
    return http.get(`/mypage/order-detail/${orderId}`, {
      headers: LoginHeader(),
    });
  }

  getOrderPrice(orderId) {
    return http.get(`/mypage/order-price/${orderId}`, {
      headers: LoginHeader(),
    });
  }

  getOrderCode(orderId) {
    return http.get(`/mypage/order-code/${orderId}`, {
      headers: LoginHeader(),
    });
  }
  // TODO: 취소 저장함수(insert)
  create(data) {
    return http.post("/mypage/order-cancel", data);
  }

  // TODO: 오더코드 수정함수
  updateCode(orderId, data) {
    return http.put(`/mypage/order-update/${orderId}`, data);
  }
}

export default new OrderCheck();
