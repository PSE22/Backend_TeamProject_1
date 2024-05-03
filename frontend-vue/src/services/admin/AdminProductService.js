import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class AdminProductService {
    // 전체조회
    getAll(pdName, page, size) {
        return http.get(`/admin/admin-product?pdName=${pdName}&page=${page}&size=${size}`,{
            headers: LoginHeader()
        });
    }
    // 상세조회
    get(pdId) {
        return http.get(`/admin/admin-product/${pdId}`,{
            headers: LoginHeader()
        });
    }
    // 상품등록함수
    create(data) {
        return http.post("/admin/admin-product", data,{
            headers: LoginHeader()
        });
    }
    // 상품수정함수
    update(pdId, data) {
        return http.put(`/admin/admin-product/${pdId}`, data,{
            headers: LoginHeader()
        });
    }
    // 상품삭제함수
    delete(pdId) {
        return http.delete(`/admin/admin-product/deletion/${pdId}`,{
            headers: LoginHeader()
        });
    }
}

export default new AdminProductService();