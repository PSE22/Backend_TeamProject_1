// 상품 공통 함수
import http from "@/utils/http-common";

class CategoryService {
    get(cmCd) {
        return http.get(`/shop/category?cmCd=${cmCd}`);
    }
}
export default new CategoryService();