<template>
  <!-- 헤더 -->
  <HeaderCom />
  
  <!-- 본문 -->
  <div class="container">
    <router-view/>
  </div>
</template>

<script>
import HeaderCom from "./components/common/HeaderCom.vue";

export default {
  components: {
    HeaderCom
  }
}
</script>

<style>
@import "@/assets/css/app.css";
</style>
