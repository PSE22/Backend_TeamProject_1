<template>
  <div style="margin-bottom: 100px;">
    <MyPageMain />
    <h1 style="margin-top: 100px; margin-bottom: 20px">쿠폰함</h1>
    <div class="row">
      <table class="table text-center">
        <!-- 쿠폰 목록 타이틀 -->
        <thead class="table-light">
          <tr>
            <th scope="col">쿠폰명</th>
            <th scope="col">혜택</th>
            <th scope="col">사용조건</th>
            <th scope="col">사용기간</th>
          </tr>
        </thead>
        <!-- 쿠폰 목록 -->
        <tbody class="table-group-divider">
          <tr v-for="(data, index) in coupon" :key="index">
            <!-- 쿠폰명 -->
            <td class="text-start col-3">{{ data.cpName }}</td>
            <!-- 혜택 -->
            <td v-if="data.cpDcPrice===null" class="col-2">{{ data.cpDcRate * 100 }}% 할인</td>
            <td v-else class="col-2">{{ data.cpDcPrice }}원 할인</td>
            <!-- 조건 -->
            <td class="col-3">{{ data.cpMinPrice }}원 이상 사용가능 (최대 {{ data.cpMaxDcPrice }}원)</td>
            <!-- 사용기간 -->
            <td class="col-3">{{data.addDate }} ~ {{ data.cpExpireDate }}</td>  
          </tr>
        </tbody>
      </table>
    </div>
    <!-- 페이징 -->
    <div class="row">
      <b-pagination
        class="col-12 mb-3 d-flex justify-content-center"
        v-model="page"
        :total-rows="count"
        :per-page="pageSize"
        @click="allCoupon"
      ></b-pagination>
    </div>
    <!-- 쿠폰 안내 -->
    <div>
      <p>
        • 발급받으신 쿠폰을 사용하시는 경우 다운로드 후 주문/결제 시 적용하실 수
        있습니다.
      </p>
      <p>
        • 사용기간이 만료되거나 사용한 쿠폰은 보유 목록에서 자동으로 삭제됩니다.
      </p>
      <p>• 주문/취소 시 이용기간이 남아 있는 쿠폰인 경우 재발급됩니다.</p>
    </div>
  </div>
</template>
<script>
import MyPageMain from "@/components/mypage/MyPageMainMenu.vue";
import MyCouponService from "@/services/mypage/MyCouponService";
export default {
  components: {
    MyPageMain,
  },
  data() {
    return {
      coupon: [], // 쿠폰 불러오기
      userId: this.$store.state.user.userId,

      // 공통 페이징 속성
      page: 1, // 현재 페이지 번호
      count: 0, // 전체 데이터 개수
      pageSize: 10, // 화면에 보여질 개수
    };
  },
  methods: {
    // 전체조회
    async allCoupon(userId) {
      try {
        // TODO: 공통 전체조회 서비스 함수 실행
        // TODO: spring 통신 : 비동기 코딩 : async ~ await
        let response = await MyCouponService.getAll(userId, this.page - 1, this.pageSize);
        
        const { coupon, totalItems } = response.data;
        this.coupon = coupon; // spring 전달 객체 배열 (쿠폰배열)
        this.count = totalItems; // 전체 페이지 개수
        // 로깅
        console.log(response.data); // 웹브라우저 콘솔탭에 spring 전달 객체 배열 표시됨
      } catch (e) {
        console.log(e);
      }
    },
  },
  mounted() {
    this.allCoupon(this.$store.state.user.userId,);
  },
};
</script>

<style>
@import "@/assets/css/mypage.css";
</style>