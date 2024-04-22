// 상품 공통 함수
import http from "@/utils/http-common";

class ProductService {
    // 카테고리 상품 전체 조회
    // 조회(select) => GET 방식
    getAll(title, page, size) {
        return http.get(`/shop/simple-product?title=${title}&page=${page}&size=${size}`);
    }
    // 카테고리 상품 상세 조회 
    // 조회(select) => GET 방식
    get(spno) {
        return http.get(`/shop/simple-product/${spno}`);
    }
}
export default new ProductService();