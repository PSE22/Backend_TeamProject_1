import http from "@/utils/http-common";

class QnaService {
    // 문의 전체 조회
    getAll(pdId, page, size) {
        return http.get(`/shop/product3?pdId=${pdId}&page=${page}&size=${size}`);
    }

    // 문의 상세 조회 
    get(pdQnaId) {
        return http.get(`/shop/product3/${pdQnaId}`);
    }
}
export default new QnaService();