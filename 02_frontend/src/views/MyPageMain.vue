<template>
  <MyPageMain />
  <div class="maininfowrap">
    <div class="mypageTitle">
        <h2 class="title">My 주문정보</h2>
        <p>최근 15일기준</p>
      </div>
      <div class="orderInfoBox">
        <!-- <ul> -->
          <li class="type1">
            <strong>0</strong>
            <span>결제완료</span>
          </li>
          <li class="type2">
            <strong>0</strong>
            <span>상품준비중</span>
          </li>
          <li class="type3">
            <strong>0</strong>
            <span>배송중</span>
          </li>
          <li class="type4">
            <strong>0</strong>
            <span>배송완료</span>
          </li>
        <!-- </ul> -->
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

<style>
@import "@/assets/css/mypage.css";</style>
