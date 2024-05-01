<template>
    <div>
        sddsd
    </div>
</template>
<script>
import AdminCouponService from '@/services/admin/AdminCouponService';
export default {
    data() {
        return {
            // TODO: 수정
      adminCouponedit: null, //초기값
      message: "", // 수정성공시 화면 성공메세지 출력하는 변수
        };
    },
    methods: {
        // TODO: 수정/삭제 시작
    // 함수정의
    async getAdminCoupon(cpId) {
      try {
        let response = await AdminCouponService.get(cpId);
        this.adminCouponedit = response.data; // spring 결과를 바인딩 속성변수 emp 저장
        // 로깅
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 수정요청 함수
    async updateAdminCoupon() {
      try {
        let response = await AdminCouponService.update(
          this.adminCouponedit.cpId,
          this.adminCouponedit
        );
        // 로깅
        console.log(response.data);
        // 화면에 성공메세지 출력 : message
        this.message = "수정이 성공했습니다.";
      } catch (e) {
        console.log(e);
      }
    },
    // 삭제요청 함수
    async deleteAdminCoupon() {
      let response = await AdminCouponService.delete(this.adminCouponedit.cpId);
      console.log(response.data);
      this.$router.push("/admin-coupon");
    },
    },
    // TODO: 수정/삭제 끝
    mounted() {
        // TODO: 수정/삭제 시작
    this.message = ""; // 변수 초기화
    this.getAdminCoupon(this.$route.params.cpId); // 쿠폰메뉴를 클릭하면 cpid 옴
    // TODO: 수정/삭제 끝
    },
}
</script>
<style>
    
</style>