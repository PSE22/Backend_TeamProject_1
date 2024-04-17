<template>
  <MyPageMain />

  <!-- My 주문정보 (결제진행) -->
  <div class="myInfoWrap">
    <div class="mypageTitle">
      <h4 class="title">My 주문정보</h4>
      <p>최근 15일기준</p>
    </div>
    <div class="orderInfoBox">
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
    </div>
  </div>

  <!-- My 리뷰활동 -->
  <div class="myInfoWrap">
    <div class="mypageTitle">
      <h2 class="title">My 리뷰활동</h2>
      <p>오늘 기준</p>
    </div>
    <div class="orderInfoBox review">
        <li>
          <a title=""
             class=""
             href=""
             >작성 가능한 리뷰
            <span class="count"><strong>0</strong>건</span></a>
        </li>
        <li>
          <a
            title=""
            class=""
            href="">
            나의 베스트 &amp; 추천 리뷰<span class="count"
              ><strong>0</strong>개</span
            ></a
          >
        </li>
    </div>
  </div>
</template>

<script>
import MyPageMain from "@/components/mypage/MyPageMainMenu.vue";
export default {
  components: {
    MyPageMain,
  },
};
</script>

<style></style>
