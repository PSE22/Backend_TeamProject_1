import http from "@/utils/http-common";

class ReviewService {
    // 리뷰 전체 조회
    getAll(page, size) {
        return http.get(`/shop/productReview?page=${page}&size=${size}`);
    }

    // 리뷰 저장
    create(data) {
        return http.post("/shop/product/review", data);
    }
}
export default new ReviewService();