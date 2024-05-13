<template>
  <div class="container">
    <!-- 장바구니 타이틀 -->
    <h1 style="margin-top: 80px">주문 상세 내역</h1>
    <h5 style="margin-bottom: 20px">2024-05-10</h5>
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
          <tr v-for="(data, index) in cart" :key="index">
            <td class="col-1">
              <input
                class="form-check-input"
                type="checkbox"
                :value="data"
                v-model="orderList"
                @change="selectProduct"
              />
            </td>
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
                  <button
                    type="button"
                    class="btn btn-outline-dark"
                    style="width: 60px"
                    disabled
                  >
                    {{ data.cartCount }}
                  </button>
                </div>
              </div>
            </td>
            <td class="col-2">
              {{ data.pdPrice + data.opPrice }}
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
            <div class="col-9 row-content">유저</div>
          </div>
          <div class="row order-content-row">
            <div class="col-3 row-title">휴대전화</div>
            <div class="col-9 row-content">0000</div>
          </div>
          <div class="row order-content-row">
            <div class="col-3 row-title">주소</div>
            <div class="col-9 row-content">0000</div>
          </div>
          <div class="row order-content-row">
            <div class="col-3 row-title">배송 요청사항</div>
            <div class="col-9 row-content">0000</div>
          </div>
          <div class="row order-content-row">
            <div class="col-3 row-title">결제수단</div>
            <div class="col-9 row-content">0000</div>
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
                      v-for="code in cmCode"
                      :key="code.cmCode"
                      :value="code.cmCode"
                    >
                      {{ code.cmCdName }}
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
          @click="a"
        >
          주문상태 변경
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import AdminOrderService from "@/services/admin/AdminOrderService";

export default {
  data() {
    return {
      newOrderCode: "",
      newOcCode: "",
      newRefundCode: "",
      reasonForStatusChange: "",
      cmCode: [], // cmCodes를 저장할 배열 추가
    };
  },
  methods: {
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
  },
  created() {
    // 컴포넌트가 생성될 때 getCmCd 메서드 호출하여 데이터 받아오기
    this.fetchCmCode();
  },
};
</script>

<style>
@import "@/assets/css/orderview.css";
</style>
