import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class MyPageOrderCancel {
  // TODO: 취소 저장함수(insert)
  create(data) {
    console.log("통신", data);
    return http.post("/mypage/order-cancel", data, {
      headers: LoginHeader(),
    });
  }
  // 상세조회
  get(orderId) {
    console.log("orderId", orderId);
    return http.get(`/mypage/order-orderId/${orderId}`, {
      headers: LoginHeader(),
    });
  }
  // TODO: 환불 저장함수(insert)
  createRefund(data) {
    console.log("통신", data);
    return http.post("/mypage/order-refund", data, {
      headers: LoginHeader(),
    });
  }
}

export default new MyPageOrderCancel();
