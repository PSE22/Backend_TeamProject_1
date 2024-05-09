import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class CartService {
  // 전체조회
  getAll(userId) {
    return http.get(`/shop/cart/${userId}`, {
      headers: LoginHeader(),
    });
  }

  // 장바구니 수정함수
  updated(data) {
    console.log("에러 :" ,data)
    return http.put(`/shop/cart/${data.cartId}`, data, {
      headers: LoginHeader(),
    });
  }
  // 장바구니 삭제 함수 : 장바구니번호
  // remove(cartId) {
  //   return http.delete(`/shop/cart/deletion/${cartId}`, {
  //     headers: LoginHeader(),
  //   });
  // }

  // 장바구니 여러 개 삭제
remove(cartIds) {
  return http.delete(`/shop/cart/deletion`, {
      headers: LoginHeader(),
      data: cartIds  // Axios는 DELETE 메소드에서도 'data'를 사용하여 바디에 데이터를 포함시킬 수 있습니다.
  });
}
}

export default new CartService();
