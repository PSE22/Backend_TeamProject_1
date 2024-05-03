import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class AdminCouponService {
    // 전체조회
    getAll(cpName, page, size) {
        return http.get(`/admin/admin-coupon?cpName=${cpName}&page=${page}&size=${size}`,{
            headers: LoginHeader()
        });
    }
    // 상세조회
    get(cpId) {
        return http.get(`/admin/admin-coupon/${cpId}`,{
            headers: LoginHeader()
        });
    }
    // 쿠폰등록함수
    create(data) {
        return http.post("/admin/admin-coupon", data,{
            headers: LoginHeader()
        });
    }
    // 쿠폰수정함수
    update(cpId, data) {
        return http.put(`/admin/admin-coupon/${cpId}`, data,{
            headers: LoginHeader()
        });
    }
    // 쿠폰삭제함수
    delete(cpId) {
        return http.delete(`/admin/admin-coupon/deletion/${cpId}`,{
            headers: LoginHeader()
        });
    }
}

export default new AdminCouponService();