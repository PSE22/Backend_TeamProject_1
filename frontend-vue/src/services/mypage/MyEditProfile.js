import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class MyEditProfileService {
  // TODO: 회원정보 상세조회
  findById(userId) {
    return http.get(`/mypage/editProfile/${userId}`, {
        headers: LoginHeader()
      });
  }

  // 회원정보 수정 함수
  updateProfile(userId, userData) {
    console.log(userId, userData)
    return http.put(`/mypage/editProfile/${userId}`, userData, {
      headers: LoginHeader()
    });
  }

  // 회원탈퇴 함수
  withdrawUser(userId, userPw) {
    return http.delete(`/mypage/editProfile/deletion/${userId}/${userPw}`, {
      headers: LoginHeader()
    });
  }
}

export default new MyEditProfileService();