// SimpleProductService.js
// axios CRUD 공통 함수
// 상품 공통 함수
import http from "@/utils/http-common";

class MyPointService {
  // TODO: 전체 조회
  // 조회(select) -> get 방식 -> @GetMapping
  getAll(pointId, page, size) {
    return http.get(
      `/mypage/point?pointId=${pointId}&page=${page}&size=${size}`
    );
  }


      // TODO: 상세 조회/
  }



export default new MyPointService();
