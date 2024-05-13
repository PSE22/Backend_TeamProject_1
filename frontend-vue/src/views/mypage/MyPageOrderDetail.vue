<template>
  <div class="container">
    <!-- 장바구니 타이틀 -->
    <h1 style="margin-top: 80px">주문 상세 내역</h1>
    <h5 style="margin-bottom: 20px">{{ order.orAddDate }}</h5>
    <div>
      <!-- 장바구니 리스트 -->
      <table class="table text-center">
        <!-- 장바구니 리스트명 -->
        <thead class="table-light">
          <tr>
            <th scope="col">상품</th>
            <th scope="col">옵션</th>
            <th scope="col">판매가</th>
            <th scope="col">수량</th>
            <th scope="col">구매가</th>
          </tr>
        </thead>
        <tbody class="table-group-divider align-middle">
          <tr v-for="(data, index) in order" :key="index">
            <td class="col-4">
              <div class="d-flex align-items-center text-start">
                <div class="flex-shrink-0">
                  <img
                    type="button"
                    :src="data.pdThumbnail"
                    class="img-thumbnail me-3"
                    style="width: 100px; height: 100px"
                    @click="goProduct(data.pdId)"
                  />
                </div>
                <div
                  type="button"
                  class="flex-grow-1 ms-3"
                  @click="goProduct(data.pdId)"
                >
                  {{ data.pdName }}
                </div>
              </div>
            </td>
            <td class="col-1">{{ data.opName }}</td>

            <td class="col-2">
              <div>
                <!-- 장바구니 수량 -->
                <div class="btn-group" role="group" aria-label="Basic example">
                  <!-- 장바구니 개수 표시 : 버튼 제목 -->
                  {{ data.pdPrice }}
                </div>
              </div>
            </td>
            <td class="col-2">
              {{ data.orderDetailCnt }}
            </td>
            <td class="col-2">
              {{ data.orderDetailPrice }}
            </td>
          </tr>
        </tbody>
      </table>

      <!-- 주문자 정보 : 제목 -->
      <div class="order-sheet-container">
        <div class="order-sheet-title">배송 정보</div>
        <!-- 주문자 정보 : 내용 -->
        <div class="order-sheet-content">
          <div class="row order-content-row">
            <div class="col-3 row-title">받는분</div>
            <div class="col-9 row-content">{{ order.orderName }}</div>
          </div>
          <div class="row order-content-row">
            <div class="col-3 row-title">휴대전화</div>
            <div class="col-9 row-content">{{ order.orderPhone }}</div>
          </div>
          <div class="row order-content-row">
            <div class="col-3 row-title">주소</div>
            <div class="col-9 row-content">{{order.orderAddr}}</div>
          </div>
          <div class="row order-content-row">
            <div class="col-3 row-title">배송 요청사항</div>
            <div class="col-9 row-content">{{order.shipMemo}}</div>
          </div>
          <div class="row order-content-row">
            <div class="col-3 row-title">결제수단</div>
            <div class="col-9 row-content">{{ order.orderPayment }}</div>
          </div>
        </div>
      </div>
      <!-- 취소신청만 보임 -->
      <div class="order-sheet-container" v-if="order.orderCode==='OD0201'||'OD0202'">
        <!-- 적립금 사용 : 제목 -->
        <div class="order-sheet-title" >취소/환불</div>
        <!-- 적립금 사용 : 내용 -->
        <div class="order-sheet-content">
          <div class="row order-content-row">
            <div class="col-3 row-title pt-3">취소사유</div>
            <div class="col-9 row-content">
              <div class="row row-cols-auto">
                <div class="col ps-0">
                  <p style="margin: 5px 0 0 0">{{ order.ocReason }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 환불신청만 보임 -->
      <div class="order-sheet-container" v-else-if="order.orderCode==='OD0301'||'OD0302'">
        <!-- 적립금 사용 : 제목 -->
        <div class="order-sheet-title" >취소/환불</div>
        <!-- 적립금 사용 : 내용 -->
        <div class="order-sheet-content">
          <div class="row order-content-row">
            <div class="col-3 row-title pt-3">환불사유</div>
            <div class="col-9 row-content">
              <div class="row row-cols-auto">
                <div class="col ps-0">
                  <p style="margin: 5px 0 0 0">{{ order.refundReason }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 결제금액 정보 -->
      <div class="card-group justify-content-center">
        <div class="card text-center mb-3" style="max-width: 18rem">
          <div class="card-header">주문금액</div>
          <div class="card-body">
            <h3 class="card-title"> 1000 원</h3>
          </div>
        </div>
        <div class="card text-center mb-3" style="max-width: 18rem">
          <div class="card-header">할인금액</div>
          <div class="card-body">
            <h3 class="card-title">{{ shipPrice }} 원</h3>
          </div>
        </div>
        <div class="card text-center mb-3" style="max-width: 18rem">
          <div class="card-header">포인트 결제</div>
          <div class="card-body">
            <h3 class="card-title">{{ totalPrice + shipPrice }} 원</h3>
          </div>
        </div>
        <div class="card text-center mb-3" style="max-width: 18rem">
          <div class="card-header">총 결제금액</div>
          <div class="card-body">
            <h3 class="card-title">{{ totalPrice + shipPrice }} 원</h3>
          </div>
        </div>
      </div>
      <div class="d-grid gap-2 d-md-block text-center">
        <button
          class="btn btn-outline-dark btn-lg me-md-2 col-2"
          type="button"
          @click="sendOrderList"
        >
          목록
        </button>
        <button
          class="btn btn-outline-dark btn-lg col-2"
          type="button"
          @click="goOrder"
        >
          주문취소
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import MyOrderCheckService from "@/services/mypage/MyOrderCheckService";
export default {
  data() {
    return {
      order: [],
      orderId: this.$route.params.orderId,
    };
  },
  methods: {
    async getOrderId(orderId) {
      try {
        let response = await MyOrderCheckService.getOrder(orderId);
        this.order = response.data; // 데이터를 order에 저장
        console.log(response.data); // 로그 출력
      } catch (e) {
        console.error(e); // 콘솔에 에러 출력
      }
    },
    async getOrderList(orderId) {
      try {
        let response = await MyOrderCheckService.getOrderList(orderId);
        this.order = response.data; // 데이터를 order에 저장
        console.log(response.data); // 로그 출력
      } catch (e) {
        console.error(e); // 콘솔에 에러 출력
      }
    },
    
  },
  mounted() {
    this.getOrderId(this.orderId); // 함수 호출 시 this.getOrderId.bind(this) 대신 this.getOrderId 사용
    this.getOrderList(this.orderId); // 함수 호출 시 this.getOrderId.bind(this) 대신 this.getOrderId 사용
  },
};
</script>

<style>
@import "@/assets/css/orderview.css";
</style>
