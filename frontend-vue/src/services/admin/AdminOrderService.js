import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class AdminOrderService {
    // 전체조회
    getAll(orderId, page, size) {
        return http.get(`/admin/orders?
        orderId=${orderId}&page=${page}&size=${size}`,{
            headers: LoginHeader()
        });
    }
    // // 상세조회
    // get(orderId) {
    //     return http.get(`/admin//orders/${orderId}?
    //     newOrderCode=${orderCode}&
    //     newOcCode=${ocCode}&
    //     newRefundCode=${refundCode}`,{
    //         headers: LoginHeader()
    //     });
    // }
    // 주문상태코드수정
    // update(orderId, data) {
    //     return http.put(`/admin/orders/${orderId}?
    //     newOrderCode=${orderCode}&
    //     newOcCode=${ocCode}&
    //     newRefundCode=${refundCode}`, data,{
    //         headers: LoginHeader()
    //     });
    // }
    
    getCmCd() {
        return http.get('/admin/orders/cmcd',
        {headers: LoginHeader()}
        )
    }
}

export default new AdminOrderService();