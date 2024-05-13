// 상품 공통 함수
import http from "@/utils/http-common";

class ProductService {
    // 베스트 상품 전체 조회
    getAllBestProduct() {
        return http.get(`/shop/home/best/product`);
    }

    // 베스트 상품 전체 조회(높은 가격순)
    getAllBestProductHigh() {
        return http.get(`/shop/home/best/product/high`);
    }

    // 베스트 상품 전체 조회(낮은 가격순)
    getAllBestProductLow() {
        return http.get(`/shop/home/best/product/low`);
    }

    // 신상품 전체 조회
    getAllNewProduct() {
        return http.get(`/shop/home/new/product`);
    }

    // 신상품 전체 조회(높은 가격순)
    getAllNewProductHigh() {
        return http.get(`/shop/home/new/product/high`);
    }

    // 신상품 전체 조회(낮은 가격순)
    getAllNewProductLow() {
        return http.get(`/shop/home/new/product/low`);
    }

    // 카테고리 상품 전체 조회
    // 조회(select) => GET 방식
    getAll(categoryCode, page, size) {
        return http.get(`/shop/category?&categoryCode=${categoryCode}&page=${page}&size=${size}`);
    }

    // 상품 상세 조회 
    // 조회(select) => GET 방식
    get(pdId) {
        return http.get(`/shop/product/${pdId}`);
    }

    // 상품 이미지 전체 조회
    // 조회(select) => GET 방식
    getImage(pdId) {
        return http.get(`/shop/productImage/${pdId}`);
    }

    // 위시 리스트 조회
    getWishList(pdId, userId) {
        return http.get(`/shop/product/wishList/${pdId}/${userId}`);
    }

    // 위시 리스트 저장
    create(data) {
        return http.post("/shop/product/wishList", data);
    }

    // 위시 리스트 삭제
    remove(pdId, userId) {
        return http.delete(`/shop/product/deletion/${pdId}/${userId}`)
    }

    // 장바구니에 저장
    AddCart(data) {
        return http.post("/shop/product/cart", data);
    }

    // 쿠폰 저장
    AddCoupon(data) {
        return http.post("/shop/product/coupon", data);
    }
}
export default new ProductService();