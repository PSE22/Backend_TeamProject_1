// 상품 공통 함수
import http from "@/utils/http-common";

class ProductService {
    // 카테고리 상품 전체 조회
    // 조회(select) => GET 방식
    getAll(categoryCode, page, size) {
        return http.get(`/shop/category?&categoryCode=${categoryCode}&page=${page}&size=${size}`);
    }

    // 상품 상세 조회 
    // 조회(select) => GET 방식
    get(pdId) {
        return http.get(`/shop/product/${pdId}`);
    }
}
export default new ProductService();