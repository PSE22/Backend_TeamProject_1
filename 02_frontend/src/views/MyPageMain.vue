<template>
  <MyPageMain />
  <div class="myInfoWrap">
    <div class="mypageTitle">
      <h4 class="title">My 주문정보</h4>
      <p>최근 15일기준</p>
    </div>
    <div class="orderInfoBox">
        <li class="type1">
          <strong>0</strong>
          <span>결제완료</span></li>
        <li class="type2">
          <strong>0</strong>
          <span>상품준비중</span></li>
        <li class="type3">
          <strong>0</strong>
          <span>배송중</span></li>
        <li class="type4">
          <strong>0</strong>
          <span>배송완료</span></li>
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
