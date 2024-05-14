<template>
    <div class="cancel-page-container">
      <div class="cancel_container mt-5">
        <h2>취소 신청</h2>
        <div class="card p-3 mb-3">
          <p class="h5">취소사유를 선택해주세요</p>
          <div class="form-check">
            <input 
              class="form-check-input" 
              type="radio" 
              name="cancelReason" 
              id="reason1" 
              value="상품이 마음에 들지 않음 (단순변심)" 
              v-model="selectedReason">
            <label class="form-check-label" for="reason1">
              상품이 마음에 들지 않음 (단순변심)
            </label>
          </div>
          <div class="form-check">
            <input 
              class="form-check-input" 
              type="radio" 
              name="cancelReason" 
              id="reason2" 
              value="다른 상품 추가 후 재주문 예정" 
              v-model="selectedReason">
            <label class="form-check-label" for="reason2">
              다른 상품 추가 후 재주문 예정
            </label>
          </div>
        </div>
        <div class="d-flex justify-content-between">
          <button class="btn btn-secondary" @click="goBack">이전 단계</button>
          <button class="btn btn-primary" :disabled="!selectedReason" @click="nextStep">다음 단계</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        selectedReason: '',
      };
    },
    methods: {
      goBack() {
        this.$router.go(-1);
      },
      nextStep() {
        if (this.selectedReason) {
          // 다음 단계로 이동하는 로직 추가
          console.log('취소 사유:', this.selectedReason);
          // 예를 들어, 다음 단계 페이지로 라우팅
          this.$router.push({ name: 'NextCancelStep', params: { reason: this.selectedReason } });
        }
      },
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
  