<template>
  <div class="container order-container">
    <!-- 주문서 페이지 타이틀 -->
    <h2>주문서</h2>
    <!-- 주문 상품 -->
    <div class="order-sheet-container">
      <!-- 주문 상품 제목 -->
      <div class="order-sheet-title">주문 상품</div>
      <!-- 주문 상품 테이블 -->
      <table class="table text-center">
        <!-- 주문 상품 테이블 : 헤더 -->
        <thead class="table-light">
          <tr>
            <th scope="col">상품</th>
            <th scope="col">옵션</th>
            <th scope="col">수량</th>
            <th scope="col">가격</th>
          </tr>
        </thead>
        <!-- 주문 상품 테이블 : 내용 -->
        <tbody class="table-group-divider align-middle">
          <tr v-for="(data, index) in cart" :key="index">
            <!-- 상품명 -->
            <td class="col-6">
              <div class="d-flex align-items-center text-start">
                <!-- 상품 이미지 -->
                <div class="flex-shrink-0">
                  <img
                    :src="data.pdImgUrl"
                    class="img-thumbnail me-3"
                    style="width: 100px; height: 100px"
                  />
                </div>
                <!-- 상품명 -->
                <div class="flex-grow-1 ms-3">
                  {{ data.pdName }}
                </div>
              </div>
            </td>
            <!-- 상품 옵션 -->
            <td class="col-1">{{ data.opName }}</td>
            <!-- 상품 수량 (cartCount)-->
            <td class="col-2">
              <div class="btn-group" role="group" aria-label="Basic example">
                <button
                  type="button"
                  class="btn btn-outline-secondary"
                  @click="decreaseCount"
                >
                  -
                </button>
                <!-- 장바구니 개수 표시 : 버튼 제목 -->
                <button
                  type="button"
                  class="btn btn-outline-dark"
                  style="width: 60px"
                  disabled
                >
                  {{ cartCount }}
                </button>
                <button
                  type="button"
                  class="btn btn-outline-secondary"
                  @click="increaseCount"
                >
                  +
                </button>
              </div>
            </td>
            <!-- 상품가격 -->
            <td class="col-3">{{ data.pdPrice + data.opPrice }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <!-- 주문자 정보 -->
    <div class="order-sheet-container">
      <!-- 주문자 정보 : 제목 -->
      <div class="order-sheet-title">주문자 정보</div>
      <!-- 주문자 정보 : 내용 -->
      <div class="order-sheet-content">
        <div class="row order-content-row">
          <div class="col-3 row-title">보내는 분</div>
          <div class="col-9 row-content">(보내는 분)</div>
        </div>
        <div class="row order-content-row">
          <div class="col-3 row-title">휴대전화</div>
          <div class="col-9 row-content">(휴대전화)</div>
        </div>
        <div class="row order-content-row">
          <div class="col-3 row-title">이메일</div>
          <div class="col-9 row-content">(이메일)</div>
        </div>
      </div>
    </div>
    <!-- 배송 정보 -->
    <div class="order-sheet-container">
      <!-- 배송 정보 : 제목 -->
      <div class="order-sheet-title">배송 정보</div>
      <!-- 배송 정보 : 내용 -->
      <div class="order-sheet-content">

        <div class="row order-content-row">
          <div class="col-3 row-title">배송지 선택</div>
          <div class="col-9 row-content">
            <div class="form-check form-check-inline">
              <input
                class="form-check-input"
                type="radio"
                name="inlineRadioOptions"
                id="inlineRadio1"
                value="option1"
              />
              <label class="form-check-label" for="inlineRadio1"
                >주문자 정보와 동일</label
              >
            </div>
            <div class="form-check form-check-inline">
              <input
                class="form-check-input"
                type="radio"
                name="inlineRadioOptions"
                id="inlineRadio2"
                value="option2"
              />
              <label class="form-check-label" for="inlineRadio2"
                >신규 배송지</label
              >
            </div>
            <div class="form-check form-check-inline">
              <input
                class="form-check-input"
                type="radio"
                name="inlineRadioOptions"
                id="inlineRadio3"
                value="option3"
              />
              <label class="form-check-label" for="inlineRadio3"
                >배송지 선택</label
              >
            </div>
          </div>
        </div>
        <div class="row order-content-row">
          <div class="col-3 row-title">주소</div>
          <div class="col-9 row-content">
            <div class="col-3">
              <input
                type="password"
                class="form-control"
                id="inputPassword2"
              />
            </div>
            <div class="col-3">
              <button type="submit" class="btn btn-primary mb-3">
                우편번호찾기
              </button>
            </div>
          </div>
        </div>
        <div class="row order-content-row">
          <div class="col-3 row-title">받으시는 분</div>
          <div class="col-9 row-content">(input 박스로 수정)</div>
        </div>
        <div class="row order-content-row">
          <div class="col-3 row-title">휴대전화</div>
          <div class="col-9 row-content">(input 박스로 수정)</div>
        </div>
        <div class="row order-content-row">
          <div class="col-3 row-title">배송 요청사항</div>
          <div class="col-9 row-content">
            <span>(textarea로 수정)</span>
          </div>
        </div>
      </div>
    </div>
    <!-- 쿠폰 -->
    <div class="order-sheet-container">
      <!-- 쿠폰 : 제목 -->
      <div class="order-sheet-title">쿠폰</div>
      <!-- 쿠폰 : 내용 -->
      <div class="order-sheet-content">
        <div class="row order-content-row">
          <div class="col-3 row-title">쿠폰 적용</div>
          <div class="col-9 row-content">
            (select 박스로 수정, 보유 쿠폰 목록 선택 기능 구현)
          </div>
        </div>
      </div>
    </div>
    <!-- 적립금 -->
    <div class="order-sheet-container">
      <!-- 적립금 : 제목 -->
      <div class="order-sheet-title">적립금</div>
      <!-- 적립금 : 내용 -->
      <div class="order-sheet-content">
        <div class="row order-content-row">
          <div class="col-3 row-title">적립금</div>
          <div class="col-9 row-content">
            <span class="col-9 row-content">사용가능 잔액 (input 태그) 원</span>
            <button class="">모두 사용</button>
          </div>
        </div>
      </div>
    </div>
    <!-- 결제 수단 -->
    <div class="order-sheet-container">
      <!-- 결제 수단 : 제목 -->
      <div class="order-sheet-title">결제 수단</div>
      <!-- 결제 수단 : 내용 -->
      <div class="order-sheet-content">
        <div class="row order-content-row">
          <div class="col-3 row-title">결제 수단 선택</div>
          <div class="col-9 row-content">
            <button class="">카카오페이</button>
            <button class="">신용카드</button>
            <button class="">간편결제</button>
          </div>
        </div>
      </div>
    </div>
    <!-- 최종 결제 금액 -->
    <div class="card-group justify-content-center">
      <div class="card text-center mb-3" style="max-width: 18rem">
        <div class="card-header">총 주문 금액</div>
        <div class="card-body">
          <h3 class="card-title">원</h3>
        </div>
      </div>
      <div class="card text-center mb-3" style="max-width: 18rem">
        <div class="card-header">할인 금액</div>
        <div class="card-body">
          <h3 class="card-title">원</h3>
        </div>
      </div>
      <div class="card text-center mb-3" style="max-width: 18rem">
        <div class="card-header">최종 결제 금액</div>
        <div class="card-body">
          <h3 class="card-title">원</h3>
        </div>
      </div>
    </div>
    <!-- 결제 버튼 -->
    <div class="text-center">
      <button class="btn btn-outline-dark btn-lg me-md-2 col-2" type="button">
        결제하기
      </button>
    </div>
  </div>
</template>
<script>
export default {};
</script>
<style>
@import "@/assets/css/orderview.css";
</style>
