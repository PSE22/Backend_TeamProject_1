<template>
  <MyPageMain />
  <div class="container">
    <div class="my-page-title-box">
      <div>
        <ul>
          <li>주문내역</li>
          <li>|</li>
          <li>쿠폰</li>
          <li>|</li>
          <li>적립금</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import MyPageMain from "@/components/common/MyPageMainMenu.vue";
export default {
  components: {
    MyPageMain,
  },
};
</script>

<style></style>
