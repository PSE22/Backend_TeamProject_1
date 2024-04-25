// SimpleProductService.js
// axios CRUD 공통 함수
// 상품 공통 함수
import http from "@/utils/http-common";

class wishListService {
  // TODO: 전체 조회
  // 조회(select) -> get 방식 -> @GetMapping
  getAll(pdId, page, size) {
    return http.get(
      `/mypage/wishlist?pdId=${pdId}&page=${page}&size=${size}`
    );
  }


      // TODO: 삭제함수 : 부서번호(pdId)
  delete(pdId) { 
    return http/delete(`/mypage/wishlist/deletion/${pdId}`)
  }
}


export default new wishListService();
