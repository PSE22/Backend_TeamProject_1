import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class AdminProductImageService {
    // 전체조회
    getAll(pdImgId, page, size) {
        return http.get(`/admin/admin-product-image?pdImgId=${pdImgId}&page=${page}&size=${size}`,{
            headers: LoginHeader()
        });
    }
    // 상세조회
    get(pdImgId) {
        return http.get(`/admin/admin-product-image/${pdImgId}`,{
            headers: LoginHeader()
        });
    }
    // 상품이미지등록함수
    create(data) {
        return http.post("/admin/admin-product-image", data,{
            headers: LoginHeader()
        });
    }
    // 상품이미지수정함수
    update(pdImgId, data) {
        return http.put(`/admin/admin-product-image/${pdImgId}`, data,{
            headers: LoginHeader()
        });
    }
    // 상품이미지삭제함수
    delete(pdImgId) {
        return http.delete(`/admin/admin-product-image/deletion/${pdImgId}`,{
            headers: LoginHeader()
        });
    }
}

export default new AdminProductImageService();