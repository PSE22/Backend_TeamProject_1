<template>
  <!-- navbar #1 -->
  <div class="container text-center main-nav1">
    <div class="row">
      <!-- navbar #1 : 로고 -->
      <div class="main-nav-logo col align-self-center">
        <router-link to="/" class="router-link">서울쥐</router-link>
      </div>
      <!-- navbar #1 : 검색창 -->
      <div class="main-nav-search col-6 ">
        <div class="input-group mb-3">
          <input type="text" class="form-control" placeholder="검색" />
          <button class="btn btn-outline-secondary" type="button">
            검색
          </button>
        </div>
      </div>
      <!-- navbar #1 : 우측 리스트 -->
      <div class="main-nav-list col align-self-center">
        <router-link to="/login" class="router-link">로그인</router-link> |
        <router-link to="/register" class="router-link">회원가입</router-link> |
        <router-link to="/mypage" class="router-link">마이페이지</router-link> |
        <router-link to="/cart" class="router-link">장바구니</router-link>
      </div>
    </div>
  </div>

  <!-- navbar #2 -->
  <div class="container text-center main-nav2">
    <div class="row">
      <div class="col p-0">
        <div class="router-link category-menu" id="category-tab" @click="toggleCategory">
          카테고리
          <ul class="category-ul" v-if="categoryTab">
            <li><router-link to="/category/1" class="category-li">카테고리1</router-link></li>
            <li><router-link to="/category/2" class="category-li">카테고리2</router-link></li>
            <li><router-link to="/category/3" class="category-li">카테고리3</router-link></li>
            <li><router-link to="/category/4" class="category-li">카테고리4</router-link></li>
          </ul>
        </div>
      </div>
      <div class="col p-0">
        <router-link to="/best-item" class="router-link category-menu">베스트</router-link>
      </div>
      <div class="col p-0">
        <router-link to="/new-item" class="router-link category-menu">신상품</router-link>
      </div>
      <div class="col p-0">
        <router-link to="/event" class="router-link category-menu">이벤트</router-link>
      </div>
    </div>
  </div>

</template>
<script>
export default {
  data() {
    return {
      categoryTab: false,
    }
  },
  methods: {
    // 카테고리 탭 토글
    toggleCategory() {
      this.categoryTab = !this.categoryTab;
    }
  },
};
</script>

<style>
@import "@/assets/css/home.css";
</style>
