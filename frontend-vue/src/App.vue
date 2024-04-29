<template>
  <!-- 헤더 -->
  <HeaderCom />
  
  <!-- 본문 -->
  <div class="container d-flex flex-column min-vh-100">
    <router-view/>
  </div>

  <!-- footer -->
  <FooterCom />
</template>

<script>
import HeaderCom from "./components/common/HeaderCom.vue";
import FooterCom from "./components/common/FooterCom.vue";

export default {
  components: {
    HeaderCom,
    FooterCom
  }
}
</script>

<style>
@import "@/assets/css/app.css";
@import "@/assets/css/sb-admin-2.css";
</style>
