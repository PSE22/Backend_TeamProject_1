// 이벤트 공통 함수
import http from "@/utils/http-common";

class EventService {
    // 이벤트 전체 조회
    getAll() {
        return http.get(`/shop/event`);
    }
}
export default new EventService();