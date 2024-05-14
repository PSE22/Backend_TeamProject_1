import http from "@/utils/http-common";

class LoginService {
    // TODO: 로그인 공통함수
    login(user) {
        let data = {
            userId: user.userId,
            userPw: user.userPw,
        };
        return http.post("/auth/login", data);
    }

    // TODO: 로그아웃 공통함수
    logout() {
        localStorage.removeItem("user");
    }

    // TODO: 회원가입 공통함수
    signup(user) {
        let data = {
            userId: user.userId,
            userPw: user.userPw,
            rePw: user.userPw,
            userName: user.userName,
            gender: user.gender,
            address: user.address,
            userEmail: user.userEmail,
            userPhone: user.userPhone,
            userPromo: user.userPromo,
            userCode: user.userCode,
        }
        return http.post("/auth/signup", data);
    }

    // TODO: 아이디 중복확인 함수
    reId(userId) {
        let data = {
            userId: userId
        };
        return http.get(`/auth/signup/${userId}?userId=${userId}`, data);
    }
}

export default new LoginService();