<template>
  <div align="center" class="mt-5">
    <h2>회원가입</h2>
  </div>
  <div class="container mt-5 register-box">
    <div align="center">
      <h2>로고</h2>
    </div>
    <!-- 회원가입 폼 -->
    <form>
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
          <label for="pwd"></label>
          <input
            type="password"
            class="form-control"
            id="pwd"
            placeholder="비밀번호"
            name="pwd"
          />
        </div>
      </div>

      <div align="center">
        <div class="form-group col">
          <label for="pwd_check"></label>

          <input
            type="password"
            class="form-control"
            id="pwd_check"
            placeholder="비밀번호 재확인"
            name="pwd_check"
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
            id="name"
            placeholder="이름"
            name="name"
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
              id="email"
              placeholder="이메일"
              name="email"
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
            <button type="button" id="phonecheck" class="btn btn-secondary">
              중복확인
            </button>
          </div>
        </div>
      </div>
      </div>

      <!-- 휴대폰 번호 -->
      <div align="center">
        <div class="row">
          <div class="col-10">
            <label for="phone"></label>

            <input
              type="text"
              class="form-control"
              id="phone"
              placeholder="휴대폰 번호"
              name="phone"
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
        <button id="register" class="btn btn-primary mt-3 col-12">
          회원가입
        </button>
      </div>
      <router-view />
    </form>
  </div>
</template>
<script>
export default {};
</script>
<style>
.register-box {
  margin: 150px auto;
  background-color: rgba(255, 255, 255, 1);
  padding: 40px 30px;
  border: 3px solid #505050;
  width: 600px;
  height: 600px;
}
.a1 {
  position: relative;
}
.a {
  position: absolute;
  top: 50%;
}
</style>
