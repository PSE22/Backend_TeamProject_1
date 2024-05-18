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
    
    getCmCd() {
        return http.get('/admin/orders/cmcd',
        {headers: LoginHeader()}
        )
    }

    codeChange(orderId, cmCode, opId) {
        return http.post(`/orders/${orderId}/${cmCode}`, null, {
            params: { orderId, opId },
            headers: LoginHeader()
        });
    }
}

export default new AdminOrderService();