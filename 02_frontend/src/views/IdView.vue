<template>
  <div align="center" class="mt-5">
    <h2>아이디 찾기</h2>
  </div>
  <div class="container mt-5 id-box">
    <div align="center" class="mb-3">
      <h2>로고</h2>
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
</style>
