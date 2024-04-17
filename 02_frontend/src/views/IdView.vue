<template>
  <div align="center" class="mt-5">
    <h2>아이디 찾기</h2>
  </div>
  <div class="container mt-5 id-box">
    <div align="center" class="mb-3">
      <div class="logo">
        <h2>서울쥐</h2>
      </div>
    </div>
    <form>
      <div class="mb-1">
        <!-- 이름 박스 -->
        <label for="text" class="form-label"></label>
        <input type="text" class="form-control" placeholder="이름" />
        <!-- 핸드폰번호 박스 -->
      </div>
      <div class="mb-3">
        <label for="text" class="form-label"></label>
        <input type="tel" class="form-control" placeholder="휴대폰 번호" />
      </div>
      <!-- 아이디 찾기 -->
      <div align="center">
        <button id="register" class="btn btn-primary mt-3 col-12">
          아이디 찾기
        </button>
      </div>

      <br />
      <!-- 비밀번호 찾기 -->
      <div align="center">
        <router-link align="center" to="find-password">비밀번호 찾기</router-link>
      </div>

      <router-view />
    </form>
  </div>
</template>
<script>
export default {};
</script>
<style>
.id-box {
  margin: 150px auto;
  background-color: rgba(255, 255, 255, 1);
  padding: 40px 30px;
  border: 3px solid #505050;
  width: 500px;
  height: 400px;
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
