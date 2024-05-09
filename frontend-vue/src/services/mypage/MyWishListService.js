// SimpleProductService.js
// axios CRUD 공통 함수
// 상품 공통 함수
import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class MyWishListService {
  // TODO: 전체 조회
  getAll(userId) {
    return http.get(`/mypage/wishlist/${userId}`, {
      headers: LoginHeader()
    });
  }

  // 삭제함수: 특정 상품을 위시리스트에서 삭제
  // URL 수정 및 올바른 HTTP 메소드 호출
  deleteWishlistItem(userId, pdId) { 
    return http.delete(`/mypage/wishlist/${userId}/${pdId}`, {
      headers: LoginHeader()
    });
  }
}



export default new MyWishListService();
