import http from "@/utils/http-common";

class CartService {
  // 전체조회
  getAll(cartId, page, size) {
    return http.get(`/shop/cart?cartId=${cartId}&page${page}&size${size}`);
  }

  // 장바구니 저장함수
  create(data) {
    return http.post(`/shop/cart`, data);
  }
  // 장바구니 삭제 함수 : 장바구니번호
  remove(cartId) {
    return http.delete(`/shop/cart/deletion/${cartId}`);
  }
}

export default new CartService();
