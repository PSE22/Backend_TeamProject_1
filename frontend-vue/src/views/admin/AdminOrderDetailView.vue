<template>
  <div class="container">
    <!-- 장바구니 타이틀 -->
    <h1 style="margin-top: 80px; margin-bottom: 20px">주문 상세 내역</h1>
    <h5 style="margin-bottom: 20px">
      {{ orderDetail.orAddDate }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ orderName }}
    </h5>
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
                  {{ data.orderDetailPrice }}
                </div>
              </div>
            </td>
            <td class="col-2">
              {{ data.orderDetailCnt }}
            </td>
            <td class="col-2">
              {{ data.orderDetailPrice * data.orderDetailCnt }}
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
            <div class="col-9 row-content">{{ orderDetail.orderName }}</div>
          </div>
          <div class="row order-content-row">
            <div class="col-3 row-title">휴대전화</div>
            <div class="col-9 row-content">{{ orderDetail.orderPhone }}</div>
          </div>
          <div class="row order-content-row">
            <div class="col-3 row-title">주소</div>
            <div class="col-9 row-content">{{ orderDetail.orderAddr }}</div>
          </div>
          <div class="row order-content-row">
            <div class="col-3 row-title">배송 요청사항</div>
            <div class="col-9 row-content">{{ orderDetail.shipMemo }}</div>
          </div>
          <div class="row order-content-row">
            <div class="col-3 row-title">결제수단</div>
            <div class="col-9 row-content">{{ orderDetail.orderPayment }}</div>
          </div>
        </div>
      </div>
      <div class="order-sheet-container">
        <div class="order-sheet-title">주문상태 변경</div>
        <div class="order-sheet-content">
          <div class="row order-content-row">
            <div class="col-3 row-title pt-3">변경사유</div>
            <div class="col-9 row-content">
              <div class="row row-cols-auto">
                <div class="col"></div>
                <div class="col ps-0">
                  <input type="text" class="form-control" v-model="a" />
                  <select class="form-select" v-model="reasonForStatusChange">
                    <option value="">변경할 주문상태</option>
                    <option
                      v-for="data in cmCode"
                      :key="data.cmCode"
                      :value="data.cmCode"
                    >
                      {{ data.cmCdName }}
                    </option>
                  </select>
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
            <h3 class="card-title">{{ totalPrice }} 원</h3>
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
          @click="a"
        >
          목록
        </button>
        <button
          class="btn btn-outline-dark btn-lg col-2"
          type="button"
          @click="changeOrderStatus"
        >
          주문상태 변경
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import AdminOrderService from "@/services/admin/AdminOrderService";
import AdminOrderDetailService from "@/services/admin/AdminOrderDetailService";

export default {
  data() {
    return {
      order: [],
      orderDetail: {},
      orderPrice: {},
      orderId: this.$route.params.orderId,
      totalPrice: 0,
      useCoupon: 0,
      orderName: "",
      orderTotalPrice: 0,

      newOrderCode: "",
      newOcCode: "",
      newRefundCode: "",
      reasonForStatusChange: "",
      cmCode: [], // cmCodes를 저장할 배열 추가
    };
  },
  methods: {
    async getOrder(orderId) {
      try {
        let response = await AdminOrderDetailService.getOrder(orderId);
        this.order = response.data; // 데이터를 order에 저장
        this.getTotalPrice();
        this.getUseCoupon();

        console.log(response.data); // 로그 출력
      } catch (e) {
        console.error(e); // 콘솔에 에러 출력
      }
    },
    // 배송지
    async getOrderDetail(orderId) {
      try {
        let response = await AdminOrderDetailService.getOrderDetail(orderId);
        this.orderDetail = response.data; // 데이터를 order에 저장
        console.log(response.data); // 로그 출력
      } catch (e) {
        console.error(e); // 콘솔에 에러 출력
      }
    },

    // 주문, 결제금액
    async getOrderPrice(orderId) {
      try {
        let response = await AdminOrderDetailService.getOrderPrice(orderId);
        this.orderPrice = response.data; // 데이터를 order에 저장
        console.log(response.data); // 로그 출력
        this.getUseCoupon();
      } catch (e) {
        console.error(e); // 콘솔에 에러 출력
      }
    },
    // 주문금액
    async getTotalPrice() {
      console.log("Order Array:", this.order);
      try {
        if (this.order.length > 0) {
          //   누적합 : totalPrice = totalPrice + 값
          for (const data of this.order) {
            this.totalPrice =
              this.totalPrice + data.orderDetailPrice * data.orderDetailCnt;
          }
          console.log("총금액", this.totalPrice);
        } else {
          this.totalPrice = 0;
        }
      } catch (error) {
        console.error("Error in getTotalPrice:", error);
        this.totalPrice = 0;
      }
    },
    // 주문 코드
    async getOrderCode(orderId) {
      try {
        let response = await AdminOrderDetailService.getOrderCode(orderId);
        this.orderName = response.data; // 데이터를 order에 저장
        console.log(response.data); // 로그 출력
      } catch (e) {
        console.error(e); // 콘솔에 에러 출력
      }
    },
    //   사용포인트
    getUsePoint() {
      if (this.orderPrice.usePointPrice) {
        return -this.orderPrice.usePointPrice;
      } else {
        return 0;
      }
    },
    //   사용쿠폰
    async getUseCoupon() {
      if (this.orderPrice.cpDcPrice) {
        this.useCoupon = -this.orderPrice.cpDcPrice;
        console.log("쿠폰", this.useCoupon);
      } else if (this.orderPrice.cpDcRate) {
        this.useCoupon = -this.totalPrice * this.orderPrice.cpDcRate;
      } else {
        this.useCoupon = 0;
        console.log("쿠폰", this.useCoupon);
      }
    },
    // getCmCd 메서드를 호출하여 cmCodes에 데이터를 저장하는 함수
    fetchCmCode() {
      AdminOrderService.getCmCd()
        .then((response) => {
          this.cmCode = response.data; // 데이터 저장
        })
        .catch((error) => {
          console.error("Error while fetching cmCodes: ", error);
        });
    },
    async changeOrderStatus() {
    try {
      if (!this.reasonForStatusChange) {
        // If no status is selected, do nothing or show an error message
        return;
      }
      
      const response = await AdminOrderService.codeChange(
        this.orderId,
        this.reasonForStatusChange,
        this.orderDetail.opId // or appropriate id from your data
      );
      console.log('Order status changed:', response.data);
    } catch (error) {
      console.error('Error changing order status:', error.response ? error.response.data : error.message);
    }
  }
  },
  created() {
    // 컴포넌트가 생성될 때 getCmCd 메서드 호출하여 데이터 받아오기
    this.fetchCmCode();
  },
  mounted() {
    this.getOrder(this.orderId);
    this.getOrderDetail(this.orderId);
    this.getOrderPrice(this.orderId);
    this.getOrderCode(this.orderId);
  },
};
</script>

<style>
@import "@/assets/css/orderview.css";
.link-custom {
  text-decoration: none; /* 밑줄 제거 */
  color: black; /* 텍스트 색상 지정 */
}
</style>
