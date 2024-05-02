<template>
  <div align="center" class="mt-5">
    <h2>회원가입</h2>
  </div>
  <div class="container mt-5 signup-box">
    <div align="center">
      <div class="logo">
        <h2>서울쥐</h2>
      </div>
    </div>
    <!-- 회원가입 폼 -->
    <form class="user" @submit.prevent="handleSignUp">
      <!-- 아이디 -->
      <div align="center">
        <div class="row">
          <div class="col-9">
            <label for="userId"></label>
            <input
              type="text"
              class="form-control"
              placeholder="아이디"
              name="userId"
              v-model="user.userId"
            />
          </div>

          <div class="col align-self-end">
            <div align="right">
              <button type="button" id="idcheck" class="btn btn-secondary">
                중복확인
              </button>
            </div>
          </div>
        </div>
      </div>
      <!-- 비밀번호 -->
      <div align="center">
        <div class="form-group col">
          <label for="userPw"></label>
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호"
            name="userPw"
            v-model="user.userPw"
          />
        </div>
      </div>

      <div align="center">
        <div class="form-group col">
          <label for="userPw"></label>
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호 재확인"
            name="userPw"
            v-model="user.rePw"
          />
        </div>
      </div>
      <!-- 비밀번호 일치 여부를 표시하는 메시지 -->
      <div v-if="!isPasswordMatch">비밀번호가 일치하지 않습니다.</div>

      <!-- 이름 -->
      <div align="center">
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
          <div class="col-5">
            <label for="userEmail"></label>
            <input
              type="text"
              class="form-control"
              placeholder="이메일"
              name="userEmail"
              v-model="user.userEmail"
            />
          </div>

          <div class="col-1 a1">
            <span class="a">@</span>
          </div>

          <div class="col-3">
            <label for="email"></label>
            <select class="form-select" aria-label="Default select example">
              <option selected>naver.com</option>
              <option value="1">google.com</option>
              <option value="2">daum.net</option>
              <option value="3">nate.com</option>
            </select>
          </div>

          <div class="col align-self-end">
            <div align="right">
              <button type="button" class="btn btn-secondary">중복확인</button>
            </div>
          </div>
        </div>
      </div>

      <!-- <div align="center">
        <button type="submit" 
        class="btn btn-outline-secondary btn-user mt-3 col-12"
        @click="address"
        >
          주소 검색
        </button>
      </div> -->

      <!-- 휴대폰 번호 -->
      <div align="center">
        <div class="row">
          <div class="col-10">
            <label for="userPhone"></label>
            <input
              type="text"
              class="form-control"
              placeholder="휴대폰 번호"
              name="userPhone"
              v-model="user.userPhone"
            />
          </div>

          <div class="col align-self-end">
            <div align="right">
              <button type="button" id="phonecheck" class="btn btn-secondary">
                인증
              </button>
            </div>
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

      <!-- 계정분류 라디오버튼 삭제 - 김태완 -->

      <!-- 회원가입 -->
      <br />
      <div align="center">
        <button
          type="button"
          class="btn btn-primary btn-user mt-3 col-12"
          @click="handleSignUp"
        >
          회원가입
        </button>
      </div>
      <router-view />
    </form>
    <p v-if="message" class="alert alert-success" role="alert">
      {{ message }}
    </p>
  </div>
</template>
<script>
import LoginService from "@/services/login/LoginService";

export default {
  // 데이터 바인딩
  data() {
    return {
      user: {
        userId: "",
        userPw: "",
        rePw: "",
        userName: "",
        gender: "",
        address: "",
        userEmail: "",
        userPhone: "",
        userPromo: "",
        userCode: "AT02", // 계정분류코드 고정 - 김태완
      },
      message: "", // 성공메세지 화면 출력속성
    };
  },
  // TODO: 함수 정의
  methods: {
    async handleSignUp() {
      this.message = "";
      try {
        // TODO: 공통 사용자등록 서비스 함수 실행
        let response = await LoginService.signup(this.user);
        // 공유저장소의 signUp 성공함수 실행
        this.$store.commit("signUpSuccess");
        this.message = "사용자가 등록되었습니다.";
        this.$router.push("/api/login"); // 회원가입 성공시 로그인 페이지로 이동 - 김태완
        // 로깅
        console.log(response.data);
      } catch (e) {
        // 공유저장소의 signUp 실패함수 실행
        this.$store.commit("signupFailure");
        this.message = "에러 : " + e;
        console.log(e);
      }
    },
  },
  computed: {
    isPasswordMatch() {
      return this.user.userPw === this.user.rePw;
    },
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
  height: 700px;
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
