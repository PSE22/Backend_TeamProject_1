// 마이페이지 메인
<template>
    <div class="my-page">
      <div class="my-page-title">마이페이지</div>
        <li class="my-page-menu"><router-link to="/mypage/order" class="router-link">주문/배송조회</router-link></li>
        <li class="my-page-menu2"><router-link to="/mypage/coupon" class="router-link">쿠폰함</router-link></li>
        <li class="my-page-menu2"><router-link to="/mypage/point" class="router-link">적립금</router-link></li>
        <li class="my-page-menu2"><router-link to="/mypage/wish" class="router-link">위시리스트</router-link></li>
        <li class="my-page-menu2"><router-link to="/mypage/inquiry" class="router-link">1:1 문의내역</router-link></li>
        <li class="my-page-menu2"><router-link to="/mypage/review" class="router-link">나의 리뷰활동</router-link></li>
        <li class="my-page-menu2"><router-link to="/mypage/info" class="router-link">회원정보 관리</router-link></li>
    </div>
</template>

<script>
export default {};
</script>

<style>
</style>
