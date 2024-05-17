<template>
  <div class="cancel-page-container">
    <div class="cancel_container mt-5">
      <h2>주문 환불 신청</h2>
      <div class="card p-3 mb-3">
        <p class="h5">환불사유를 선택해주세요</p>
        <div class="form-check" v-for="(reason, index) in reasons" :key="index">
          <input
            class="form-check-input"
            type="radio"
            :id="`reason${index}`"
            v-model="refundReason"
            :value="reason"
          />
          <label class="form-check-label" :for="`reason${index}`">
            {{ reason }}
          </label>
        </div>
      </div>
      <div class="d-flex justify-content-between">
        <button class="btn btn-secondary" @click="goBack">이전 단계</button>
        <button
          class="btn btn-primary"
          :disabled="!refundReason"
          @click="confirmRefundlation"
        >
          다음 단계
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import MyOrderCancelService from '@/services/mypage/MyOrderCancelService';

export default {
  data() {
    return {
      orderId: this.$route.params.orderId,
      opId: [], // 주문 상세 옵션 ID 목록
      orderDetailPrice: [],
      refundPrice: 0, // 취소 금액, 필요에 따라 설정
      refundReason: "", // 선택된 취소 사유
      reasons: [
        "상품이 마음에 들지 않음 (단순변심)",
        "상품 파손",
        "그냥",
      ],
      // orderCancel: []
    };
  },
  methods: {
    async retriveOrder(orderId) {
      // 주문 상세 정보 조회
      try {
        let response = await MyOrderCancelService.get(orderId);
        this.opId = response.data.map(detail => detail.opId); // opId 목록 추출
        this.orderDetailPrice = response.data.map(detail => detail.orderDetailPrice);
      } catch (e) {
        console.error("주문 상세 조회 실패:", e);
      }
    },
    goBack() {
      this.$router.go(-1);
    },
    async confirmRefundlation() {
      if (this.refundReason) {
        if (confirm("정말로 주문을 환불하시겠습니까?")) {
          await this.confirmRefund();
        }
      } else {
        alert("취소 사유를 선택해 주세요.");
      }
    },
    async confirmRefund() {
      try {
        let data = {
          orderId: this.orderId,
          opId: this.opId, // 전체 opId 목록
          refundPrice: this.orderDetailPrice, // 취소 금액
          refundCode: "OD0301", // 주문 상태 코드
          refundReason: this.refundReason // 사용자가 선택한 취소 사유
        };
        let response = await MyOrderCancelService.createRefund(data);
        // this.orderCancel = response.data;
        console.log("Order Cancel Response:", response);
        alert("주문이 환불되었습니다.");
        this.$router.push(`/mypage/order/${this.orderId}`);
      } catch (e) {
        console.error("주문 환불 실패:", e);
        alert("환불 처리 중 문제가 발생했습니다.");
      }
    }
  },
  mounted() {
    this.retriveOrder(this.$route.params.orderId);
  },
};
</script>

<style scoped>
.cancel-page-container {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  padding-top: 50px; /* 상단 여백 조정 */
  min-height: 100vh; /* Viewport height */
  background-color: #f8f9fa; /* Optional: Background color for the page */
}

.cancel_container {
  max-width: 500px;
  width: 100%;
  padding: 20px;
  background-color: #ffffff; /* Background color for the form */
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Optional: Add some shadow */
  border-radius: 8px; /* Optional: Rounded corners */
}
</style>
