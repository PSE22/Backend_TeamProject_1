import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class AdminPdReviewService {
    // 전체조회
    getAll(reviewTitle, page, size) {
        return http.get(`/admin/admin-pdqna-review?reviewTitle=${reviewTitle}&page=${page}&size=${size}`,{
            headers: LoginHeader()
        });
    }
    // 상세조회
    get(reviewId) {
        return http.get(`/admin/admin-pdqna-review/${reviewId}`,{
            headers: LoginHeader()
        });
    }
    // 상품리뷰등록함수
    create(data) {
        return http.post("/admin/admin-pdqna-review", data,{
            headers: LoginHeader()
        });
    }
    // 상품리뷰수정함수
    update(reviewId, data) {
        return http.put(`/admin/admin-pdqna-review/${reviewId}`, data,{
            headers: LoginHeader()
        });
    }
    // 상품리뷰삭제함수
    delete(reviewId) {
        return http.delete(`/admin/admin-pdqna-review/deletion/${reviewId}`,{
            headers: LoginHeader()
        });
    }
}

export default new AdminPdReviewService();