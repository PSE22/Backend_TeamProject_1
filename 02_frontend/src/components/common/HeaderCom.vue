<template>
  <!-- nav 바 -->
  <div class="container text-center">
    <div class="main-nav row">
      <!-- nav 로고 -->
      <div class="main-nav-logo col">LOGO</div>
      <!-- nav 검색창 -->
      <div class="main-nav-search col-6">
        <div class="input-group mb-3">
          <input type="text" class="form-control" placeholder="검색" />
          <button
            class="btn btn-outline-secondary"
            type="button"
            id="button-addon2"
          >
            검색
          </button>
        </div>
      </div>
      <!-- nav 마이페이지 -->
      <div class="main-nav-list col">
        <div id="myPage">
            <router-link to="#" class="router-link">로그인</router-link> |
            <router-link to="#" class="router-link">회원가입</router-link> |
            <router-link to="#" class="router-link">마이페이지</router-link> |
            <router-link to="#" class="router-link">장바구니</router-link>
        </div>
      </div>
    </div>
  </div>

  <!-- 카테고리/메뉴 탭 -->
  <!-- nav-pills : 여백 채우기 -->
  <!-- nav-justified : 동일한 가로폭 -->
  <ul class="nav nav-pills nav-justified">
    <li class="menu-tab nav-item dropdown">
      <a href="/category">카테고리</a>
    </li>
    <li class="menu-tab nav-item">
      <a href="/best">베스트</a>
    </li>
    <li class="menu-tab nav-item">
      <a href="/event">이벤트</a>
    </li>
    <li class="menu-tab nav-item">
      <a href="/coupon">쿠폰</a>
    </li>
  </ul>
</template>
<script>
export default {};
</script>
<style></style>
