<template>
  <!-- navbar #1 -->
  <div class="container text-center main-nav1">
    <div class="row">
      <!-- navbar #1 : 로고 -->
      <div class="main-nav-logo col align-self-center">
        <router-link to="/" class="router-link logo-name">서울쥐</router-link>
      </div>
      <!-- navbar #1 : 검색창 -->
      <div class="main-nav-search col-6">
        <div class="input-group">
          <input v-model="search" type="text" class="form-control" placeholder="검색" />
          <a :href="`/product-search?search=${search}`"><button class="btn btn-outline-secondary" type="button">검색</button></a>
        </div>
      </div>
      <!-- navbar #1 : 우측 리스트 -->
      <div class="main-nav-list col align-self-center">
        <!-- 일반 사용자일 때 보여지는 항목 -->
        <template v-if="this.$store.state.loggedIn && !showAdminBoard">
          <router-link to="/mypage" class="router-link">마이페이지 | </router-link>
          <router-link to="/cart" class="router-link">장바구니 | </router-link>
        </template>

        <router-link v-if="showAdminBoard" to="/admin-product" class="router-link">관리페이지 | </router-link>
        
        <template v-if="!this.$store.state.loggedIn">
          <router-link to="/login" class="router-link">로그인 | </router-link>
          <router-link to="/signup" class="router-link">회원가입</router-link>
        </template>

        <router-link v-if="this.$store.state.loggedIn" to="#" class="router-link" @click.prevent="handleLogout">로그아웃</router-link>
      </div>
    </div>
  </div>

  <!-- navbar #2 -->
  <div class="container text-center main-nav2">
    <!-- navbar #2 : 메뉴 -->
    <div class="row">
      <!-- navbar #2 : 카테고리 탭 -->
      <div class="col p-0">
        <div class="category-menu" id="category-tab" @click="toggleCategory">
          카테고리
          <ul v-if="categoryTab">
            <a :href="`/category/CG01`"  class="router-link"
              ><li>필기류/필통</li></a
            >
            <a :href="`/category/CG02`" class="router-link"
              ><li>다이어리/플래너</li></a
            >
            <a :href="`/category/CG03`" class="router-link"
              ><li>노트/메모지</li></a
            >
            <a :href="`/category/CG04`" class="router-link"
              ><li>사무용품</li></a
            >
          </ul>
        </div>
      </div>
      <div class="col p-0">
        <router-link to="/best-item" class="router-link category-menu"
          >베스트</router-link
        >
      </div>
      <div class="col p-0">
        <router-link to="/new-item" class="router-link category-menu"
          >신상품</router-link
        >
      </div>
      <div class="col p-0">
        <router-link to="/event" class="router-link category-menu"
          >이벤트</router-link
        >
      </div>
    </div>
  </div>
</template>
<script>
import LoginService from "@/services/login/LoginService";

export default {
  data() {
    return {
      categoryTab: false,
      categoryCode: "",
      search: ""
    };
  },
  methods: {
    toggleCategory() {
      this.categoryTab = !this.categoryTab;
    },
    closeCategoryTab(event) {
      if (!event.target.closest("#category-tab")) {
        this.categoryTab = false;
      }
    },
    handleLogout() {
      LoginService.logout();
      this.$store.commit("logout");
      this.$router.push("/login");
    },
  },
  computed: {
    showAdminBoard() {
      if (this.$store.state.user != null) {
        return this.$store.state.user.userCode == "AT01";
      }
      return false;
    },
  },
  mounted() {
    document.addEventListener("click", this.closeCategoryTab);
  },
  beforeUnmount() {
    document.removeEventListener("click", this.closeCategoryTab);
  },
};
</script>

<style>
@import "@/assets/css/home.css";
</style>
