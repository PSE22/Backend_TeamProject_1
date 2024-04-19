<template>
  <div align="center" class="mt-5">
    <h2>회원가입</h2>
  </div>
  <div class="container mt-5 register-box">
    <div align="center">
      <div class="logo">
        <h2>서울쥐</h2>
      </div>
    </div>
    <!-- 회원가입 폼 -->
    <form class="user" @submit.prevent="handleRegister">
      <!-- 아이디 -->
      <div align="center">
        <div class="row">
          <div class="col-9">
            <label for="userid"></label>
            <input
              type="text"
              class="form-control"
              id="userid"
              placeholder="아이디"
              name="userid"
              v-model="user.id"
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
          <label for="password"></label>
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호"
            name="password"
            v-model="user.password"
          />
        </div>
      </div>

      <div align="center">
        <div class="form-group col">
          <label for="password_check"></label>
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호 재확인"
            name="password_check"
            v-model="user.repassword"
          />
        </div>
      </div>

      <!-- 이름 -->
      <div align="center">
        <div class="form-group col">
          <label for="name"></label>
          <input
            type="text"
            class="form-control"
            placeholder="이름"
            name="name"
            v-model="user.name"
          />
        </div>
      </div>

      <!-- 이메일 -->
      <div align="center">
        <div class="row">
          <div class="col-5">
            <label for="email"></label>
            <input
              type="text"
              class="form-control"
              placeholder="이메일"
              name="email"
              v-model="user.email"
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
            <button type="button" class="btn btn-secondary">
              중복확인
            </button>
          </div>
        </div>
      </div>
      </div>

      <div align="center">
        <button type="submit" class="btn btn-outline-secondary btn-user mt-3 col-12">
          주소 검색
        </button>
      </div>

      <!-- 휴대폰 번호 -->
      <div align="center">
        <div class="row">
          <div class="col-10">
            <label for="phone"></label>
            <input
              type="text"
              class="form-control"
              placeholder="휴대폰 번호"
              name="phone"
              v-model="user.phone"
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

      <!-- 회원가입 -->
      <br />
      <div align="center">
        <button type="submit" class="btn btn-primary btn-user mt-3 col-12">
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
import LoginService from '@/services/login/LoginService';

export default {
  // 데이터 바인딩
  data() {
    return {
      user: {
                id: "",
                password: "",
                repassword: "",
                name: "",
                email: "",
                phone: "",
                address: "",
            },
            message: "",      // 성공메세지 화면 출력속성
    }
  },
  // TODO: 함수 정의
  methods: {
    async handleRegister() {
      this.message = "";
      try {
        // TODO: 공통 사용자등록 서비스 함수 실행
        let response = await LoginService.register(this.user);
        // 공유저장소의 register 성공함수 실행
        this.$store.commit("registerSuccess");
        this.message = "사용자가 등록되었습니다.";
        // 로깅 
        console.log(response.data);
      } catch(e) {
        // 공유저장소의 register 실패함수 실행
        this.$store.commit("registerFailure");
        this.message = "에러 : " + e;
        console.log(e);
      }
    }
  },
};
</script>
<style>
.register-box {
  margin: 100px auto;
  background-color: rgba(255, 255, 255, 1);
  padding: 40px 30px;
  border: 3px solid #505050;
  width: 600px;
  height: 650px;
}
.a1 {
  position: relative;
}
.a {
  position: absolute;
  top: 50%;
}

@font-face {
    font-family: 'YClover-Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_231029@1.1/YClover-Bold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}

.logo {
    /* background-color: rgb(115, 235, 67); */
    font-size: 30px;
    font-family: 'YClover-Bold';
}
</style>
