import http from "@/utils/http-common";

class OptionService {
    // 상품 옵션 전체 조회
    get(pdId) {
        return http.get(`/shop/productOption/${pdId}`)
    }
}

export default new OptionService();