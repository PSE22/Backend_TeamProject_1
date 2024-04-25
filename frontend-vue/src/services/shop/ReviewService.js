import http from "@/utils/http-common";

class ReviewService {
    // 리뷰 전체 조회
    getAll(pdId, page, size) {
        return http.get(`/shop/product2?pdId=${pdId}&page=${page}&size=${size}`);
    }

    // 리뷰 상세 조회 
    get(reviewId) {
        return http.get(`/shop/product2/${reviewId}`);
    }
}
export default new ReviewService();