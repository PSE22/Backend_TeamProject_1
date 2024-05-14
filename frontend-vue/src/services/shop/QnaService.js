import http from "@/utils/http-common";

class QnaService {
    // 문의 전체 조회
    getAll(page, size) {
        return http.get(`/shop/productQna?page=${page}&size=${size}`);
    }

    // 문의 저장
    create(data) {
        console.log(data);
        return http.post("/shop/product/qna", data);
    }
}
export default new QnaService();