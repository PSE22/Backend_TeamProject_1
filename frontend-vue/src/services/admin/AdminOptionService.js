import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class AdminOptionService {
    // 전체조회
    getAll(opName, page, size) {
        return http.get(`/admin/admin-option?opName=${opName}&page=${page}&size=${size}`,{
            headers: LoginHeader()
        });
    }
    // 상세조회
    get(opId) {
        return http.get(`/admin/admin-option/${opId}`,{
            headers: LoginHeader()
        });
    }
    // 상품등록함수
    create(data) {
        return http.post("/admin/admin-option", data,{
            headers: LoginHeader()
        });
    }
    // 상품수정함수
    update(opId, data) {
        return http.put(`/admin/admin-option/${opId}`, data,{
            headers: LoginHeader()
        });
    }
    // 상품삭제함수
    delete(opId) {
        return http.delete(`/admin/admin-option/deletion/${opId}`,{
            headers: LoginHeader()
        });
    }
}

export default new AdminOptionService();