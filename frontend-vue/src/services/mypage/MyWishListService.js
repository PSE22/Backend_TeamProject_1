// SimpleProductService.js
// axios CRUD 공통 함수
// 상품 공통 함수
import http from "@/utils/http-common";

class wishListService {
  // TODO: 전체 조회
  // 조회(select) -> get 방식 -> @GetMapping
  getAll(pdName, page, size) {
    return http.get(
      `/mypage/wishList?pdName=${pdName}&page=${page}&size=${size}`
    );
  }

  // 위시리스트 상세조회
  get(pdId) {
    return http.get(`/mypage/wishList/${pdId}`);
  }
}


export default new wishListService();
