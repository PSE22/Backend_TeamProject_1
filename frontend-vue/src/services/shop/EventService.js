// 이벤트 공통 함수
import http from "@/utils/http-common";

class EventService {
    // 이벤트 전체 조회
    getAll() {
        return http.get(`/shop/event`);
    }
    
    // 이벤트 상세 조회
    getEventDetail(eventId) {
        return http.get(`/shop/event/${eventId}`);
    }

    // 이벤트 이미지 조회
    getEventImage(eventId) {
        return http.get(`/shop/eventImg/${eventId}`);
    }
}
export default new EventService();