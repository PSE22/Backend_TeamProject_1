// User 공통 함수
import http from "@/utils/http-common";

class UserService {
    // User 상세 조회
    getUser(userId) {
        return http.get(`/shop/user/${userId}`);
    }
}
export default new UserService();