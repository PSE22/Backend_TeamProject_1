<template>
  <div align="center" class="mt-5">
    <h2>회원정보수정</h2>
  </div>
  <div class="container mt-5 signup-box">
    <div align="center">
      <div class="logo">
        <h2>서울쥐</h2>
      </div>
    </div>
    <!-- 회원정보수정 폼 -->
    <div v-if="user">
      <!-- 아이디 -->
      <div align="center">
        <div class="row">
          <div class="col">
            <label for="userId"></label>
            <input
              id="inputId"
              type="text"
              class="form-control"
              placeholder="아이디"
              name="userId"
              disabled
              v-model="user.userId"
            />
          </div>
        </div>
      </div>
      <!-- 비밀번호 변경 -->
      <div align="center">
        <div class="row">
          <div class="form-group col">
            <label for="userPw"></label>
            <input
              type="password"
              class="form-control"
              placeholder="기존 비밀번호 확인"
              name="userPw"
              v-model="userPw"
            />
            <label for="newPassword"></label>
            <input
              type="password"
              class="form-control"
              placeholder="새로운 비밀번호"
              name="newPassword"
              v-model="newPassword"
            />
            <label for="newPasswordConfirm"></label>
            <input
              type="password"
              class="form-control"
              placeholder="비밀번호 재확인"
              name="newPasswordConfirm"
              v-model="newPasswordConfirm"
            />
          </div>
        </div>
      </div>

      <!-- 비밀번호 일치 여부를 표시하는 메시지 -->
      <div v-if="!isNewPasswordMatch">비밀번호가 일치하지 않습니다.</div>

      <!-- 이름 -->
      <div align="center">
        <div class="row">
          <div class="form-group col">
            <label for="userName"></label>
            <input
              type="text"
              class="form-control"
              placeholder="이름"
              name="userName"
              v-model="user.userName"
            />
          </div>
        </div>
      </div>

      <!-- 성별 -->
      <div class="form-check form-check-inline mt-3">
        <input
          class="form-check-input"
          type="radio"
          name="gender"
          value="M"
          v-model="user.gender"
        />
        <label class="form-check-label" for="inlineRadio1">남</label>
      </div>
      <div class="form-check form-check-inline">
        <input
          class="form-check-input"
          type="radio"
          name="gender"
          value="F"
          v-model="user.gender"
        />
        <label class="form-check-label" for="inlineRadio2">여</label>
      </div>
      <!-- 이메일 -->
      <div align="center">
        <div class="row">
          <div class="col">
            <label for="userEmail"></label>
            <input
              type="text"
              class="form-control"
              placeholder="이메일"
              name="userEmail"
              v-model="user.userEmail"
            />
          </div>
        </div>
      </div>
      <br />

      <!-- 휴대폰 번호 -->
      <div align="center">
        <div class="row">
          <div class="col">
            <label for="userPhone"></label>
            <input
              type="text"
              class="form-control"
              placeholder="휴대폰 번호"
              name="userPhone"
              v-model="user.userPhone"
            />
          </div>
        </div>
      </div>
      <!-- 광고수신동의 -->
      광고 수신 동의 :
      <div class="form-check form-check-inline mt-3">
        <input
          class="form-check-input"
          type="radio"
          name="userPromo"
          value="Y"
          v-model="user.userPromo"
        />
        <label class="form-check-label" for="promoYes">동의</label>
      </div>
      <div class="form-check form-check-inline">
        <input
          class="form-check-input"
          type="radio"
          name="userPromo"
          value="N"
          v-model="user.userPromo"
        />
        <label class="form-check-label" for="promoNo">미동의</label>
      </div>

      <!-- 권한 -->
      <div align="center">
        <div class="row">
          <div class="col">
            <label for="userCode"></label>
            <input
              type="text"
              class="form-control"
              placeholder="선택 없음"
              name="userCode"
              disabled
              v-model="user.userCode"
            />
          </div>
        </div>
      </div>

      <!-- 회원가입 -->
      <br />
      <div align="center">
        <div class="row g-3 mt-2 mb-3">
          <button
            type="submit"
            class="btn btn-outline-primary ms-2 col"
            @click="updateProfile"
          >
            회원정보수정
          </button>

          <!-- TODO: 탈퇴 시작 -->
          <button
            type="button"
            class="btn btn-outline-danger ms-3 col"
            data-bs-toggle="modal"
            :data-bs-target="'#exampleModal-1'"
          >
            회원탈퇴
          </button>

          <!-- 탈퇴 모달 -->
          <div class="modal fade" id="exampleModal-1" tabindex="-1">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h1 class="modal-title fs-5" id="exampleModalLabel">
                    회원 탈퇴
                  </h1>
                  <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="modal"
                  ></button>
                </div>
                <!-- 탈퇴 모달 내 목록 -->
                <div class="modal-body">
                  <!-- 회원아이디 -->
                  <h3 class="fs-5 mt-1">회원아이디</h3>
                  <label for="text" class="form-label"></label>
                  <input
                    disabled
                    type="text"
                    class="form-control mb-4"
                    name="text"
                    v-model="user.userId"
                  />
                  <!-- 비밀번호 -->
                  <h3 class="fs-5 mt-1">비밀번호</h3>
                  <label for="text" class="form-label"></label>
                  <input
                    type="text"
                    class="form-control mb-4"
                    placeholder="비밀번호를 입력하세요"
                    name="text"
                    v-model="userPwData.userPw"
                  />
                </div>
                <div class="modal-footer">
                  <button
                    type="submit"
                    class="btn btn-primary"
                    @click="withdrawUser"
                  >
                    탈퇴
                  </button>
                  <button
                    type="button"
                    class="btn btn-secondary"
                    data-bs-dismiss="modal"
                  >
                    취소
                  </button>
                </div>
              </div>
            </div>
          </div>
          <!-- 탈퇴 모달 끝 -->

          <!-- TODO: 탈퇴 끝 -->
        </div>
      </div>
    </div>
    <router-view />
    <p v-if="message" class="alert alert-success" role="alert">
      {{ message }}
    </p>
  </div>
</template>
<script>
import MyEditProfile from "@/services/mypage/MyEditProfile";

export default {
  data() {
    return {
      user: null,
      message: "",
      // 비밀번호
      userPw: "", // 기존 비밀번호
      newPassword: "", // 새로운 비밀번호
      newPasswordConfirm: "", // 비밀번호 재확인
      userCode: "AT02",
      userPwData: {
        userId: this.$store.state.user.userId,
      },
    };
  },
  // TODO: 함수 정의
  methods: {
    async changePassword() {
      // 새로운 비밀번호와 비밀번호 재확인이 일치하는지 확인
      if (!this.isNewPasswordMatch) {
        alert("새로운 비밀번호가 일치하지 않습니다.");
        return;
      }

      try {
        // 비밀번호 변경 API 호출
        alert("비밀번호가 변경되었습니다.");
      } catch (error) {
        alert("비밀번호 변경에 실패했습니다.");
      }
    },
    async getUser(userId) {
      console.log("아이디 확인", userId);
      try {
        let response = await MyEditProfile.findById(userId);
        this.user = response.data; // spring 결과를 바인딩 속성변수 user 저장
        console.log("상세 조회", response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 수정요청 함수
    async updateProfile() {
      try {
        let temp = {
          userId: this.user.userId,
          userPw: this.newPassword,
          userName: this.user.userName,
          gender: this.user.gender,
          userEmail: this.user.userEmail,
          userPhone: this.user.userPhone,
          userPromo: this.user.userPromo,
          userCode: this.user.userCode,
        };
        let response = await MyEditProfile.updateProfile(
          this.user.userId,
          temp
        );
        console.log(response.data);
        // 화면에 성공메세지 출력 : message
        alert("수정이 성공했습니다.");
        this.$router.push("/mypage/editProfile");
      } catch (e) {
        console.log(e);
      }
    },
    // 삭제요청 함수
    async withdrawUser() {
      try {
        let userPwData = {
          userPW: this.userPwData.userPw,
        };
        let response = await MyEditProfile.withdrawUser(this.user.userId, userPwData.userPW);
        console.log(response.data);
        alert("회원탈퇴가 성공했습니다.");
        this.$router.push("/");
      } catch (e) {
        console.log("회원탈퇴 실패", e);
      }
    },
  },
  computed: {
    isPasswordMatch() {
      return this.user.userPw === this.user.rePw;
    },
    isNewPasswordMatch() {
      return this.newPassword === this.newPasswordConfirm;
    },
  },
  mounted() {
    this.message = "";
    this.getUser(this.$store.state.user?.userId);
  },
};
</script>
<style>
.signup-box {
  margin: 100px auto;
  background-color: rgba(255, 255, 255, 1);
  padding: 40px 30px;
  border: 3px solid #505050;
  width: 600px;
  height: 900px;
}
.a1 {
  position: relative;
}
.a {
  position: absolute;
  top: 50%;
}

@font-face {
  font-family: "YClover-Bold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_231029@1.1/YClover-Bold.woff2")
    format("woff2");
  font-weight: 700;
  font-style: normal;
}

.logo {
  /* background-color: rgb(115, 235, 67); */
  font-size: 30px;
  font-family: "YClover-Bold";
}
</style>
