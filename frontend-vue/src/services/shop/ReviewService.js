import http from "@/utils/http-common";

class ReviewService {
    // 리뷰 전체 조회
    getAll(pdId, page, size) {
        return http.get(`/shop/productReview?pdId=${pdId}&page=${page}&size=${size}`);
    }

    // 리뷰 저장
    create(data) {
        return http.post("/shop/product/review", data);
    }
}
export default new ReviewService();