import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class AdminPdQnaService {
    // 전체조회
    getAll(pdQnaTitle, page, size) {
        return http.get(`/admin/admin-pdqna?pdQnaTitle=${pdQnaTitle}&page=${page}&size=${size}`,{
            headers: LoginHeader()
        });
    }
    // 상세조회
    get(pdQnaId) {
        return http.get(`/admin/admin-pdqna/${pdQnaId}`,{
            headers: LoginHeader()
        });
    }
    // 상품문의등록함수
    create(data) {
        return http.post("/admin/admin-pdqna", data,{
            headers: LoginHeader()
        });
    }
    // 상품문의수정함수
    update(pdQnaId, data) {
        return http.put(`/admin/admin-pdqna/${pdQnaId}`, data,{
            headers: LoginHeader()
        });
    }
    // 상품문의삭제함수
    delete(pdQnaId) {
        return http.delete(`/admin/admin-pdqna/deletion/${pdQnaId}`,{
            headers: LoginHeader()
        });
    }
}

export default new AdminPdQnaService();