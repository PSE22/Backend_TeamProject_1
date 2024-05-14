import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";
import dayjs from "dayjs";

class OrderCheck {
  // TODO: 전체 조회
  // 조회(select) -> get 방식 -> @GetMapping
  getAllByDateRange(userId, startDate, endDate) {
    // 날짜 형식을 yyyy-MM-dd HH:mm:ss로 맞추어 요청
    const formattedStartDate = dayjs(startDate).format('YYYY-MM-DD HH:mm:ss');
    const formattedEndDate = dayjs(endDate).format('YYYY-MM-DD HH:mm:ss');
    
    return http.get(`/mypage/ordercheck/${userId}?startDate=${formattedStartDate}&endDate=${formattedEndDate}`, {
      headers: LoginHeader(),
    });
  }

  getOrder(orderId) {
    return http.get(`/mypage/orderlist/${orderId}`, {
      headers: LoginHeader(),
    });
  }
  getOrderDetail(orderId) {
    return http.get(`/mypage/orderdetail/${orderId}`, {
      headers: LoginHeader(),
    });
  }
  
  getOrderPrice(orderId) {
    return http.get(`/mypage/orderprice/${orderId}`, {
      headers: LoginHeader(),
    });
  }
}

export default new OrderCheck();
