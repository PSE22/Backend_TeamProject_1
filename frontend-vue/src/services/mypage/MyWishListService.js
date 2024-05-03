// SimpleProductService.js
// axios CRUD 공통 함수
// 상품 공통 함수
import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class MyWishListService {
  // TODO: 전체 조회
  // 조회(select) -> get 방식 -> @GetMapping
  // 로그인한 사용자의 위시리스트 조회
  getAll(userId, page, pageSize) {
    return http.get(`/mypage/wishlist/${userId}&page=${page}&size=${pageSize}` ,{
      headers: LoginHeader()
    });
  }


      // TODO: 삭제함수 : 부서번호(pdId)
  delete(pdId) { 
    return http/delete(`/mypage/wishlist/deletion/${pdId}`,{
      headers: LoginHeader()
    });
  }
}



export default new MyWishListService();
