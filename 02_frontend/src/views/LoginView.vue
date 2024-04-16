<template>
  <div align="center" class="mt-5" to="login">
    <h2>로그인</h2>
  </div>
  <div class="container mt-5 login-box">
    <div align="center" class="mb-3">
      <h2>로고</h2>
    </div>
    <form>
      <div class="mb-1">
        <!-- 아이디 박스 -->
        <label for="text" class="form-label"></label>
        <input 
          type="text" 
          class="form-control" 
          id="exampleInputEmail1" 
          placeholder="아이디"
        />
        <!-- 비밀번호 박스 -->
      </div>
      <div class="mb-3">
        <label for="password" class="form-label"></label>
        <input
          type="password"
          class="form-control"
          id="exampleInputPassword1"
          placeholder="비밀번호"
        />
      </div>
      <!-- 아이디 저장 체크박스 -->
      <div class="mb-3 form-check">
        <input type="checkbox" class="form-check-input" id="exampleCheck1" />
        <label class="form-check-label" for="exampleCheck1">아이디 저장</label>
      </div>
      <!-- 로그인 박스 -->
      <div align="center">
        <button type="submit" class="btn btn-primary me-auto col-12">로그인</button>
      </div>

      <!-- 찾기 및 회원 가입 -->
      <nav class="mt-3 mb-3" align="center">
        <router-link align="left" to="/find-id">아이디 찾기</router-link> |
        <router-link align="center" to="/find-password">비밀번호 찾기</router-link> |
        <router-link align="right" to="/register">회원가입</router-link>
      </nav>

      <!-- 소셜 페이지 로그인 기능 연동 -->
      <div class="social-container" align="center">
        <router-link href="#" class="social" to="facebook"
          >facebook</router-link
        >
        <router-link href="#" class="social" to="google">google</router-link>
        <router-link href="#" class="social" to="kakao">kakao</router-link>
      </div>
      <router-view />
    </form>
  </div>
</template>
<script>
export default {
  data() {
    return {};
  },
};
</script>
<style>
.login-box {
  margin: 150px auto;
  background-color: rgba(255, 255, 255, 1);
  padding: 40px 30px;
  border: 3px solid #505050;
  width: 500px;
  height: 500px;
}

.social-container {
  margin: 30px 0;
}
.social-container a {
  background-color: rgb(206, 206, 206);
  border-radius: 50%;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  margin: 0 20px;
  height: 60px;
  width: 60px;
}
</style>
