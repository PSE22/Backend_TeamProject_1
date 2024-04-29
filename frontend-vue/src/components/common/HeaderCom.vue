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
          <input type="text" class="form-control" placeholder="검색" />
          <button class="btn btn-outline-secondary" type="button">검색</button>
        </div>
      </div>
      <!-- navbar #1 : 우측 리스트 -->
      <div class="main-nav-list col align-self-center">
        <router-link to="/mypage" class="router-link">마이페이지</router-link> |
        <router-link to="/cart" class="router-link">장바구니</router-link> |
        <!-- 로그인 / 회원가입 -->
        <router-link
          v-if="this.$store.state.loggedIn == false"
          to="/login"
          class="router-link"
          >로그인 |</router-link
        >
        <router-link
          v-if="this.$store.state.loggedIn == false"
          to="/signup"
          class="router-link"
        >
          회원가입</router-link
        >

        <!-- 로그아웃 -->
        <router-link
          to="#"
          v-if="this.$store.state.loggedIn == true"
          class="router-link"
          @click.prevent="handleLogout"
        >
          로그아웃
        </router-link>
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
            <router-link to="/category/CG01" class="router-link"
              ><li>필기류/필통</li></router-link
            >
            <router-link to="/category/CG02" class="router-link"
              ><li>다이어리/플래너</li></router-link
            >
            <router-link to="/category/CG03" class="router-link"
              ><li>노트/메모지</li></router-link
            >
            <router-link to="/category/CG04" class="router-link"
              ><li>사무용품</li></router-link
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
    };
  },
  methods: {
    toggleCategory() {
      this.categoryTab = !this.categoryTab;
    },
    // categoryOver: function () {
    //       this.categoryTab = !this.categoryTab;
    //     }
    handleLogout() {
      LoginService.logout();
      this.$store.commit("logout");
      this.$router.push("/login");
    },
  },
  computed: {
    showAdminBoard() {
      if (this.$store.state.user != null) {
        return this.$store.state.user.codeName == "ROLE_ADMIN";
      }
      return false;
    },
  },
};
</script>

<style>
@import "@/assets/css/home.css";
</style>
