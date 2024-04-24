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

  // TODO: 위시리스트 상세조회
  get(pdId) {
    return http.get(`/mypage/wishList/${pdId}`);
  }

    // TODO: 추가(insert) 함수
    creat(data) {
      // 사용법 : http.post("컨트롤러함수 url", 생성할객체 )
      return http.post("/basic/dept", data);
    }

      // TODO: 삭제함수 : 부서번호(pdId)
  delete(pdId) { 
    return http/delete(`/mypage/wishList/deletion/${pdId}`)
  }

}


export default new wishListService();
