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
                  <img :src="data.pdImgUrl" class="img-thumbnail me-3" style="width: 100px; height: 100px" />
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
                <button type="button" class="btn btn-outline-secondary" @click="decreaseCount">
                  -
                </button>
                <!-- 장바구니 개수 표시 : 버튼 제목 -->
                <button type="button" class="btn btn-outline-dark" style="width: 60px" disabled>
                  {{ cartCount }}
                </button>
                <button type="button" class="btn btn-outline-secondary" @click="increaseCount">
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
          <div class="col-9 row-content">{{ user.userName }}</div>
        </div>
        <div class="row order-content-row">
          <div class="col-3 row-title">휴대전화</div>
          <div class="col-9 row-content">{{ user.userPhone }}</div>
        </div>
        <div class="row order-content-row">
          <div class="col-3 row-title">이메일</div>
          <div class="col-9 row-content">{{ user.userEmail }}</div>
        </div>
      </div>
    </div>
    <!-- 배송 정보 -->
    <div class="order-sheet-container">
      <!-- 배송 정보 : 제목 -->
      <div class="order-sheet-title">배송 정보</div>
      <!-- 배송 정보 : 내용 -->
      <div class="order-sheet-content">
        <!-- 배송 정보 : 내용 : 배송지 선택 -->
        <div class="row order-content-row">
          <div class="col-3 row-title">배송지 선택</div>
          <div class="col-9 row-content">
            <div class="form-check form-check-inline">
              <input class="form-check-input mt-1" type="radio" id="addrRadio1" name="addrRadioOptions" value="option1" v-model="selectedAddr"/>
              <label class="form-check-label" for="addrRadio1">직접 입력</label>
            </div>
            <div class="form-check form-check-inline">
              <input class="form-check-input mt-1" type="radio" id="addrRadio2" name="addrRadioOptions" value="option2" v-model="selectedAddr"/>
              <label class="form-check-label" for="addrRadio2">주문자 정보와 동일</label>
            </div>
          </div>
        </div>
        <!-- 배송 정보 : 내용 : 주소 -->
        <div class="row order-content-row">
          <div class="col-3 row-title">주소</div>
          <div class="col-9 row-content">
            <div class="row">
              <div class="col-3">
                <input type="test" id="postcode" class="form-control" placeholder="우편번호"
                  :value="shipAddress.postcode" />
              </div>
              <div class="col-3">
                <button @click="execDaumPostcode()" type="submit" class="btn btn-primary mb-3">
                  우편번호찾기
                </button>
              </div>
            </div>
            <div class="row">
              <div class="col-6">
                <input type="text" id="shipAddr" class="form-control" placeholder="주소 입력"
                  :value="shipAddress.shipAddr" />
              </div>
              <div class="col-6">
                <input type="text" id="shipAddr2" class="form-control" placeholder="상세주소 입력"
                  :value="shipAddress.shipAddr2" />
              </div>
            </div>
          </div>
        </div>
        <!-- 배송 정보 : 내용 : 받으시는 분 -->
        <div class="row order-content-row">
          <div class="col-3 row-title pt-3">받으시는 분</div>
          <div class="col-9 row-content">
            <div class="col-4 ps-0">
              <input type="text" class="form-control" placeholder="이름" :value="user.userName" />
            </div>
          </div>
        </div>
        <!-- 배송 정보 : 내용 : 휴대전화 -->
        <div class="row order-content-row">
          <div class="col-3 row-title pt-3">휴대전화</div>
          <div class="col-9 row-content">
            <div class="col-6 ps-0">
              <input type="text" class="form-control" placeholder="휴대전화번호" :value="user.userPhone" />
            </div>
          </div>
        </div>
        <!-- 배송 정보 : 내용 : 배송 요청사항 -->
        <div class="row order-content-row">
          <div class="col-3 row-title pt-3">배송 요청사항</div>
          <div class="col-9 row-content">
            <div class="col-8 ps-0">
              <textarea class="form-control" rows="4" placeholder="요청사항을 입력하세요."></textarea>
            </div>
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
          <div class="col-3 row-title pt-3">쿠폰 적용</div>
          <div class="col-9 row-content">
            <div class="row">
              <div class="col-6">
                <select class="form-select">
                  <!-- 회원 보유 쿠폰 반복문 돌리기 -->
                  <option selected>쿠폰선택</option>
                  <option value="1">One</option>
                </select>
              </div>
              <div class="col-3">
                <button type="submit" class="btn btn-primary">쿠폰선택</button>
              </div>
            </div>
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
          <div class="col-3 row-title pt-3">적립금</div>
          <div class="col-9 row-content">
            <div class="row">
              <div class="col-3">
                <input type="test" class="form-control" />
              </div>
              <div class="col-1 ps-0">
                <p style="margin: 10px 0 0 0">원</p>
              </div>
              <div class="col-3">
                <button type="submit" class="btn btn-primary">모두 사용</button>
              </div>
            </div>
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
          <div class="col-3 row-title pt-3">결제 수단 선택</div>
          <div class="col-9 row-content">
            <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
              <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked />
              <label class="btn mb-0" for="btnradio1">카카오페이</label>

              <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off" />
              <label class="btn mb-0" for="btnradio2">신용카드</label>

              <input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off" />
              <label class="btn mb-0" for="btnradio3">간편결제</label>
            </div>
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
      <button class="btn btn-warning btn-lg me-md-2 col-2 mt-5 mb-5" type="button">
        결제하기
      </button>
    </div>
  </div>
</template>
<script>
// import CartService from "@/services/shop/CartService";
import OrderService from "@/services/shop/OrderService";

export default {
  data() {
    return {
      cart: [], // 장바구니 객체 배열
      user: {}, // user 객체
      shipAddress: {}, // 배송지 객체
      selectedAddr: 'option1',
    };
  },
  methods: {
    // 장바구니 전체조회
    // async allCart(userId) {
    //   try {
    //     let response = await CartService.getAll(
    //       userId,
    //       this.page - 1,
    //       this.pageSize
    //     );
    //     const { cart, totalItems } = response.data;
    //     this.cart = cart;
    //     this.count = totalItems;
    //     // 로깅
    //     console.log(response.data); // 웹브라우저 콘솔탬에 백앤드 데이터 표시
    //   } catch (e) {
    //     console.log(e);
    //   }
    // },
    async getUser(userId) {
      try {
        let response = await OrderService.getUser(userId);
        this.user = response.data;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async getShipAddress(userId) {
      try {
        let response = await OrderService.getShipAddress(userId);
        this.shipAddress = response.data;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 주소에 
    // 배송지 선택 라디오 버튼 선택하면 실행
    addressRadio() {
      document.querySelectorAll('input[type=radio][name=addrRadioOptions]').forEach(function (radio) {
        radio.addEventListener('change', function () {
          if (this.value === 'option1') {
            // "신규 배송지" 라디오 버튼이 선택
            // 여기에 해당 라디오 버튼을 선택했을 때 수행할 동작 추가
          } else if (this.value === 'option2') {
            // "주문자 정보와 동일" 라디오 버튼이 선택
            // 여기에 해당 라디오 버튼을 선택했을 때 수행할 동작 추가
          }
        });
      });
    },

    // 카카오 주소 api 연동 부분
    execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: function (data) {
          // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

          // 각 주소의 노출 규칙에 따라 주소를 조합한다.
          // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
          var addr = ''; // 주소 변수

          //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
          if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
            addr = data.roadAddress;
          } else { // 사용자가 지번 주소를 선택했을 경우(J)
            addr = data.jibunAddress;
          }

          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          document.getElementById('postcode').value = data.zonecode;
          document.getElementById("shipAddr").value = addr;
          // 커서를 상세주소 필드로 이동한다.
          document.getElementById("shipAddr2").focus();
        }
      }).open();
    }
  },
  mounted() {
    this.getUser(this.$store.state.userId);
    this.getShipAddress(this.$store.state.userId);
  },
};
</script>
<style>
@import "@/assets/css/orderview.css";
</style>
