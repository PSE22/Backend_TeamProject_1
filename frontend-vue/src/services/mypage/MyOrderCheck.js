import http from "@/utils/http-common";

class OrderCheck {
  // TODO: 전체 조회
  // 조회(select) -> get 방식 -> @GetMapping
  getAll(opId, page, size) {
    return http.get(
      `/mypage/orderList?opId=${opId}&page=${page}&size=${size}`
    );
  }
  // TODO: 장바구니는 상품 상세조회도 함 : 기본키로 조회(상품번호(spno))
  get(orderId) {
    return http.get(`/mypage/orderList/${orderId}`);
  }
}


export default new OrderCheck();
