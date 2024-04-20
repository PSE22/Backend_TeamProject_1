import http from "@/utils/http-common";

class LoginService {
    // TODO: 로그인 공통함수
    login(user) {
        let data = {
            id: user.id,
            password: user.password,
        };
        return http.post("/login", data);
    }

    // TODO: 로그아웃 공통함수
    logout() {
        localStorage.removeItem("user");
    }

    // TODO: 회원가입 공통함수
    register(user) {
        let data = {
            id: user.id,
            password: user.password,
            repassword: user.password,
            name: user.name,
            address: user.address,
            email: user.email,
            phone: user.phone,
        }
        return http.post("/register", data);
    }
}

export default new LoginService();