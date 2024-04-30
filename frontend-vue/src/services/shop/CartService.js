import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class CartService {
  // 전체조회
  getAll(userId) {
    return http.get(`/shop/cart/${userId}`, {
      headers: LoginHeader(),
    });
  }

  // 장바구니 저장함수
  create(data) {
    return http.post(`/shop/cart`, data, {
      headers: LoginHeader(),
    });
  }
  // 장바구니 삭제 함수 : 장바구니번호
  remove(cartId) {
    return http.delete(`/shop/cart/deletion/${cartId}`, {
      headers: LoginHeader(),
    });
  }
}

export default new CartService();
