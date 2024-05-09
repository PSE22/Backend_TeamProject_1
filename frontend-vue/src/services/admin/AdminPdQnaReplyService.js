import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class AdminPdQnaReplyService {
    // 전체조회
    getAll(pdQnaReplyContent, page, size) {
        return http.get(`/admin/admin-pdqna-reply?pdQnaReplyContent=${pdQnaReplyContent}&page=${page}&size=${size}`,{
            headers: LoginHeader()
        });
    }
    // 상세조회
    get(pdQnaReplyId) {
        return http.get(`/admin/admin-pdqna-reply/${pdQnaReplyId}`,{
            headers: LoginHeader()
        });
    }
    // 상품문의등록함수
    create(data) {
        return http.post("/admin/admin-pdqna-reply", data,{
            headers: LoginHeader()
        });
    }
    // 상품문의수정함수
    update(pdQnaReplyId, data) {
        return http.put(`/admin/admin-pdqna-reply/${pdQnaReplyId}`, data,{
            headers: LoginHeader()
        });
    }
    // 상품문의삭제함수
    delete(pdQnaReplyId) {
        return http.delete(`/admin/admin-pdqna-reply/deletion/${pdQnaReplyId}`,{
            headers: LoginHeader()
        });
    }
}

export default new AdminPdQnaReplyService();