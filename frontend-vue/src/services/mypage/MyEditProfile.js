// 상품 공통 함수
import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class MyEditProfileService {
  // 회원정보 조회 함수
  showUpdateProfileForm(userId) {
    return http.get(`/mypage/editProfile${userId}`, {
      headers: LoginHeader()
    });
  }

  // 회원정보 수정 함수
  updateProfile(userId, userData) {
    console.log(userId, userData)
    // return http.put(`/mypage/editProfile/${userId}`, userData, {
    //   headers: LoginHeader()
    // });
  }

  // 회원탈퇴 함수
  withdrawUser(userId) {
    return http.delete(`/mypage/editProfile/deletion/${userId}`, {
      headers: LoginHeader()
    });
  }

  // 회원탈퇴 함수
  // withdrawUser(userId, userPw) {
  //   return http.delete(`/mypage/editProfile?userId=${userId}userPw=${userPw}`, {
  //     headers: LoginHeader()
  //   });
  // }
}

export default new MyEditProfileService();