// 마이페이지
<template>
  <div>
    <MyPageMain />
    <div class="mp-page-top">
      <!-- 회원정보 -->
      <div class="row" id="mp-box-name">
        <h3>{{ userName }} 님</h3>
      </div>
      <!-- 첫번째 타이틀 -->
      <div
        class="mp-title-box justify-content-md-center align-items-center row"
      >
        <div class="col-3 text-center">
          <button
            type="button"
            class="mp-btn"
            @click="this.$router.push('/mypage/order')"
          >
            <p>주문내역</p>
            <h1>{{ orderCount }}</h1>
          </button>
        </div>
        <div class="col-3 text-center">
          <button
            class="mp-btn"
            type="button"
            @click="this.$router.push('/mypage/coupon')"
          >
            <p>쿠폰</p>
            <h1>{{ couponCount }}</h1>
          </button>
        </div>
        <div
          class="col-3 text-center"
          type="button"
          @click="this.$router.push('/mypage/point')"
        >
          <button class="mp-btn">
            <p>적립금</p>
            <h1>{{ userPoint.resultPoint || 0 }}</h1>
          </button>
        </div>
      </div>
    </div>
    <!-- My 주문정보 (결제진행) -->
    <div class="row">
      <div class="myInfoWrap col">
        <div class="mypageTitle">
          <h4 class="title">My 주문정보</h4>
          <p>최근 15일기준</p>
        </div>
        <div class="orderInfoBox">
          <li class="type1">
            <strong>{{ getCountByOrderCode("PO03") }}</strong>
            <span>결제완료</span>
          </li>
          <li class="type2">
            <strong>{{ getCountByOrderCode("OD0101") }}</strong>
            <span>상품준비중</span>
          </li>
          <li class="type3">
            <strong>{{ getCountByOrderCode("OD0103") }}</strong>
            <span>배송중</span>
          </li>
          <li class="type4">
            <strong>{{ getCountByOrderCode("OD0104") }}</strong>
            <span>배송완료</span>
          </li>
        </div>
      </div>
    </div>
    <!-- My 리뷰활동 -->
    <div class="row">
      <div class="myInfoWrap col">
        <div class="mypageTitle">
          <h2 class="title">My 활동</h2>
          <p>최근 1개월 기준</p>
        </div>
        <div
          class="mp-title-box justify-content-md-center align-items-center row"
        >
          <div class="col-5 text-center">
            <button
              type="button"
              class="mp-btn"
              @click="this.$router.push('/mypage/review')"
            >
              <p>작성 리뷰</p>
              <h1>{{ reviewCount }}</h1>
            </button>
          </div>
          <div class="col-5 text-center">
            <button
              class="mp-btn"
              type="button"
              @click="this.$router.push('/mypage/inquiry')"
            >
              <p>문의 내역</p>
              <h1>{{ inquiryCount }}</h1>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MyPageMain from "@/components/mypage/MyPageMainMenu.vue";
import MyPageService from "@/services/mypage/MyPageService";
export default {
  components: {
    MyPageMain,
  },
  data() {
    return {
      userId: this.$store.state.userId,
      orderCount: 0,
      couponCount: 0,
      reviewCount: 0,
      inquiryCount: 0,
      userPoint: 0,
      userName: "",
      orderCode: [],
    };
  },
  // created() {
  //       if(this.$store.state.loggedIn == false) {
  //           this.$router.push("/login"); // home 강제 이동
  //       }
  //   },
  methods: {
    getCountByOrderCode(code) {
      let count = 0;
      for (let i = 0; i < this.orderCode.length; i++) {
        if (this.orderCode[i].orderCode === code) {
          count = this.orderCode[i].count;
          break;
        }
      }
      return count;
    },
  },
  async mounted() {
    try {
      let response = await MyPageService.getOrderCnt(
        this.$store.state.user.userId
      );
      let response2 = await MyPageService.getCouponCnt(
        this.$store.state.user.userId
      );
      let response3 = await MyPageService.getReviewCnt(
        this.$store.state.user.userId
      );
      let response4 = await MyPageService.getInquiryCnt(
        this.$store.state.user.userId
      );
      let response5 = await MyPageService.getUserPoint(
        this.$store.state.user.userId
      );
      let response6 = await MyPageService.getUserName(
        this.$store.state.user.userId
      );
      let response7 = await MyPageService.getOrderCode(
        this.$store.state.user.userId
      );
      this.orderCount = response.data;
      this.couponCount = response2.data;
      this.reviewCount = response3.data;
      this.inquiryCount = response4.data;
      this.userPoint = response5.data;
      this.userName = response6.data;
      this.orderCode = response7.data;
      console.log(response7.data); // 웹브라우저 콘솔탬에 백앤드 데이터 표시
    } catch (e) {
      console.log(e);
    }
  },
};
</script>

<style>
@import "@/assets/css/mypage.css";
</style>
