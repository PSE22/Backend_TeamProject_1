<template>
  <div class="cancel-page-container">
    <div class="cancel_container mt-5">
      <h2>주문 취소 신청</h2>
      <div class="card p-3 mb-3">
        <p class="h5">취소사유를 선택해주세요</p>
        <div class="form-check" v-for="(reason, index) in reasons" :key="index">
          <input 
            class="form-check-input" 
            type="radio" 
            :id="`reason${index}`" 
            v-model="selectedReason" 
            :value="reason">
          <label class="form-check-label" :for="`reason${index}`">
            {{ reason }}
          </label>
        </div>
      </div>
      <div class="d-flex justify-content-between">
        <button class="btn btn-secondary" @click="goBack">이전 단계</button>
        <button class="btn btn-primary" :disabled="!selectedReason" @click="confirmCancellation">다음 단계</button>
      </div>
    </div>
  </div>
</template>
  
  <script>
  import MyOrderCheckService from "@/services/mypage/MyOrderCheckService";

  export default {
    data() {
      return {
        selectedReason: '',
        // 이 배열에 원하는 이유 넣어주면됨
        reasons: [
          '상품이 마음에 들지 않음 (단순변심)',
          '다른 상품 추가 후 재주문 예정',
          '그냥'
        ]
      };
    },
    methods: {
      goBack() {
        this.$router.go(-1);
      },
      confirmCancellation() {
        if (this.selectedReason) {
          if (confirm('정말로 주문을 취소하시겠습니까?')) {
            // 취소 요청을 처리하는 API 호출이나 로직
            alert('주문이 취소되었습니다.');
            // 주문 상세 페이지로 리다이렉션
            this.$router.push(`/mypage/order/${this.$route.params.orderId}`);
          }
        } else {
          alert('취소 사유를 선택해 주세요.');
        }
      }
    },

    // 취소,환불 상태 저장함수
    async confirmCancel() {
      try {
        let data = {
          orderId: this.$route.params.orderId,
          opId: this.order.opId,
          refundPrice: this.getOrderPrice(),
          refundCode: "",
          refundReason: "",
          refundDenyReason: "",
          orderCode: "",
        };
        let response = await MyOrderCheckService.create(data);
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
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
  