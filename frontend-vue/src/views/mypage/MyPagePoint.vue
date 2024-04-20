<template>
  <div class="mypage-layout">
    <MyPageMainMenu />
    <div class="mypage-contents">
      <div class="mypage-backcolor">
        <div class="mypage-subtitle">적립금</div>
        <div class="pointIntro">
          <dl class="usablePoint">
            <dt>사용가능 적립금</dt>
            <dd>
              <strong>0</strong>
              P
            </dd>
          </dl>
        </div>
        <div class="mypage-filter" role="group" aria-label="Basic example">
          <div class="dateSelect">
            <button type="button" class="btn btn-primary">1 개월</button>
            <button type="button" class="btn btn-primary">3 개월</button>
            <button type="button" class="btn btn-primary">6 개월</button>
          </div>
          <p class="pointDate">24.03.19 ~ 24.04.19</p>
        </div>

        <div class="pointWrap">
          <ul class="pointList">
            <li class="pointItem">
              <p class="date">24.03.24</p>
              <dl class="pointInfo">
                <dt>소멸</dt>
                <dd>유효기간만료</dd>
              </dl>
              <div class="state minus"><strong>-10</strong>P</div>
            </li>
          </ul>
          <div class="mypageNotice">
            <h3 class="title" style="margin-left: 20px;"  >적립/사용 유의사항</h3>
            <ul class="content">
              <li>
                마일리지는 홈플러스 회원만 적립 및 사용이 가능합니다. (비회원
                제외)
              </li>
              <li>
                마일리지는 주문/결제 시 할인 받을 금액을 10원단위로 기입하여
                사용 가능한 쿠폰입니다.
              </li>
              <li>
                주문/결제 시 다른 쿠폰 및 결제수단과 함께 이용할 수 있습니다.
              </li>
              <li>
                마일리지를 사용 하실 때, 남은 최종 실 결제금액이 1,000원 이상
                되어야 사용 가능합니다.
              </li>
              <li>
                마일리지는 전체주문취소/전체반품 시 자동 환원되어 재사용
                가능합니다.
              </li>
              <li>
                마일리지의 유효기간은 쿠폰별 상이할 수 있으며, 만료시 자동
                소멸됩니다. (유효기간은 연장되지 않습니다.)
              </li>
              <li>마일리지는 회원탈퇴 시 모두 소멸됩니다.</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import MyPageMainMenu from "@/components/mypage/MyPageMainMenu.vue";
export default {
  components: {
    MyPageMainMenu,
  },
};
</script>
<style>
@import "@/assets/css/mypage.css";
@import "@/assets/css/mypagepoint.css";
</style>
