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
              <!-- 라디오 버튼 선택시 selectedAddr에 value 값이 들어감 -->
              <input class="form-check-input mt-1" type="radio" id="addrRadio1" value="option1" v-model="selectedAddr"
                @change="handleRadioChange" />
              <label class="form-check-label" for="addrRadio1">직접 입력</label>
            </div>
            <div class="form-check form-check-inline">
              <input class="form-check-input mt-1" type="radio" id="addrRadio2" value="option2" v-model="selectedAddr"
                @change="handleRadioChange" />
              <label class="form-check-label" for="addrRadio2">주문자 정보와 동일</label>
            </div>
          </div>
        </div>

        <!-- TODO: "직접 입력" 라디오 버튼 선택 시 보임 -->
        <div v-if="selectedAddr === 'option1'">
          <!-- 배송 정보 : 내용 : 주소 -->
          <div class="row order-content-row">
            <div class="col-3 row-title">주소</div>
            <div class="col-9 row-content">
              <div class="row">
                <div class="col-3">
                  <input type="test" id="postcode" class="form-control" placeholder="우편번호"
                    v-model="orderPostcode" />
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
                    v-model="orderShipAddr1" />
                </div>
                <div class="col-6">
                  <input type="text" id="shipAddr2" class="form-control" placeholder="상세주소 입력"
                    v-model="orderShipAddr2" />
                </div>
              </div>
            </div>
          </div>
          <!-- 배송 정보 : 내용 : 받으시는 분 -->
          <div class="row order-content-row">
            <div class="col-3 row-title pt-3">받으시는 분</div>
            <div class="col-9 row-content">
              <div class="col-4 ps-0">
                <input type="text" class="form-control" placeholder="이름" v-model="orderName" />
              </div>
            </div>
          </div>
          <!-- 배송 정보 : 내용 : 휴대전화 -->
          <div class="row order-content-row">
            <div class="col-3 row-title pt-3">휴대전화</div>
            <div class="col-9 row-content">
              <div class="col-6 ps-0">
                <input type="text" class="form-control" placeholder="휴대전화번호" v-model="orderPhone" />
              </div>
            </div>
          </div>
        </div>
        <!-- TODO : 여기까지 "직접 입력"-->

        <!-- TODO: "주문자 정보와 동일" 라디오 버튼 선택 시 보임 -->
        <div v-else-if="selectedAddr === 'option2'">
          <!-- 배송 정보 : 내용 : 주소 -->
          <div class="row order-content-row">
            <div class="col-3 row-title">주소</div>
            <div class="col-9 row-content">
              <div class="row">
                <div class="col-3">
                  <input type="test" id="postcode" class="form-control" placeholder="우편번호" readonly
                    v-model="orderPostcode" />
                </div>
                <div class="col-3">
                  <button @click="execDaumPostcode()" type="submit" class="btn btn-primary mb-3" disabled>
                    우편번호찾기
                  </button>
                </div>
              </div>
              <div class="row">
                <div class="col-6">
                  <input type="text" id="shipAddr" class="form-control" placeholder="주소 입력" readonly
                    v-model="orderShipAddr1" />
                </div>
                <div class="col-6">
                  <input type="text" id="shipAddr2" class="form-control" placeholder="상세주소 입력" readonly
                    v-model="orderShipAddr2" />
                </div>
              </div>
            </div>
          </div>
          <!-- 배송 정보 : 내용 : 받으시는 분 -->
          <div class="row order-content-row">
            <div class="col-3 row-title pt-3">받으시는 분</div>
            <div class="col-9 row-content">
              <div class="col-4 ps-0">
                <input type="text" class="form-control" placeholder="이름" v-model="orderName" />
              </div>
            </div>
          </div>
          <!-- 배송 정보 : 내용 : 휴대전화 -->
          <div class="row order-content-row">
            <div class="col-3 row-title pt-3">휴대전화</div>
            <div class="col-9 row-content">
              <div class="col-6 ps-0">
                <input type="text" class="form-control" placeholder="휴대전화번호" v-model="orderPhone" />
              </div>
            </div>
          </div>
        </div>
        <!-- TODO : 여기까지 "주문자 정보와 동일" -->

        <!-- 배송 정보 : 내용 : 배송 요청사항 -->
        <div class="row order-content-row">
          <div class="col-3 row-title pt-3">배송 요청사항</div>
          <div class="col-9 row-content">
            <div class="col-8 ps-0">
              <textarea class="form-control" rows="4" placeholder="요청사항을 입력하세요." v-model="orderShipMemo"></textarea>
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
                <select class="form-select" v-model="selectCoupon">
                  <!-- 회원 보유 쿠폰 반복문 돌리기 -->
                  <option v-for="(data, index) in coupon" :key="index" :value="index">
                    {{ data?.cpName }}
                  </option>
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
    <!-- 적립금 사용 -->
    <div class="order-sheet-container">
      <!-- 적립금 사용 : 제목 -->
      <div class="order-sheet-title">적립금 사용</div>
      <!-- 적립금 사용 : 내용 -->
      <div class="order-sheet-content">
        <div class="row order-content-row">
          <div class="col-3 row-title pt-3">적립금 사용</div>
          <div class="col-9 row-content">
            <div class="row row-cols-auto">
              <div class="col">
                <input type="test" class="form-control" v-model="tmpPoint" />
              </div>
              <div class="col ps-0">
                <p style="margin: 10px 0 0 0">원</p>
              </div>
              <div class="col">
                <button type="submit" class="btn btn-primary" @click="usePoint()">사용</button>
              </div>
              <div class="col">
                <button type="submit" class="btn btn-secondary" @click="usePointAll()">모두 사용</button>
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
            <div class="btn-group">
              <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" @change="handlePayment(1)" />
              <label class="btn mb-0 btn-outline-primary rounded-start" for="btnradio1">카카오페이</label>
              <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off" @change="handlePayment(2)" />
              <label class="btn mb-0 btn-outline-primary" for="btnradio2">신용카드</label>
              <input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off" @change="handlePayment(3)" />
              <label class="btn mb-0 btn-outline-primary" for="btnradio3">간편결제</label>
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
      <button class="btn btn-warning btn-lg me-md-2 col-3 mt-5 mb-5" type="button" @click="cancelOrder()">
        주문취소
      </button>
      <button class="btn btn-danger btn-lg me-md-2 col-3 mt-5 mb-5" type="button">
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
      // 주문 테이블에 보낼 데이터를 담을 변수
      orderPostcode: "",
      orderShipAddr1: "",
      orderShipAddr2: "",
      orderName: "",
      orderPhone: "",
      orderShipMemo: "",
      orderPrice: 0,
      orderPayment: "",     // 결제수단

      // 서버에서 받아온 값을 저장
      cart: [],             // 장바구니 객체 배열
      user: {},             // user 객체
      shipAddress: {},      // 배송지 객체
      coupon: [],           // 보유 쿠폰 배열
      point: {},            // 포인트 잔액

      tmpPoint: 0,          // 사용할 포인트 값을 담을 변수       
      selectCoupon: "",         // 사용할 쿠폰의 값을 담을 변수
      selectedAddr: 'option1',  // 배송지 선택 라디오 버튼 값 초기 설정
      userId: this.$store.state.userId,
    };
  },
  methods: {
    // 주문 함수 (주문 테이블 + 주문 상세 테이블 insert) : 결제 페이지로 이동
    // async goApproval() {
    //   // 1) 주문 날짜 (현재 날짜)
    //   // let now = new Date();   // js 날짜 객체
    //   // let formatNow = `${now.getFullYear()}-${now.getMonth()}-${now.getDate()} ${now.getHours()}:${now.getMinutes()}:${now.getSeconds()}`;

    //   // 2) 장바구니의 물품별 금액(단가*개수)을 구해서 모든 상품별 총금액 구하기
    //   // let totalPrice = this.cart
    //   //   .map((data) => data.unitPrice * data.cartCount)       // 상품별 금액 배열
    //   //   .reduce((acc, cur) => acc + cur)     // reduce(누적변수, 배열값) => 누적변수 + 배열값); => 총금액

    //   // 3) 주문 상세 객체 정의
    //   let orderDetail = {
    //     orderId: null,         // 주문번호 (PK) (최초 주문 번호는 null)
    //     opId: 0,               // 옵션번호 (PK)
    //     orderDetailCnt: 0,     // 수량
    //     orderDetailPrice: 0,   // 금액
    //     orderDetailCode: 0,    // 주문상태코드
    //   };

    //   // 4) 주문 상세 배열
    //   let orderDetailList = [];     // 주문상세 객체배열 정의
    //   // 배열 반복문 (for of 사용)
    //   for (const data of this.cart) {
    //     orderDetail.spno = data.spno;                     // 상품(옵션) 번호
    //     orderDetail.productCount = data.productCount;     // 상품 개수 (장바구니 수량)
    //     // 주문 상세 배열에 위의 주문 상세 객체를 넣기
    //     orderDetailList.push(orderDetail);          // 객체 배열
    //   }

    //   // 4) 백엔드(spring) insert 요청 (비동기 코딩)
    //   try {
    //     // 임시 주문 객체 : 주문 상세 객체배열 속성이 있음
    //     let data = {
    //       // orderDetailList: orderDetailList,   // 주문 상세 객체 배열(주문 상세 테이블 insert)
    //       // orderDate: formatNow,                           // 주문 날짜
    //       // orderStatus: 50001,                             // 주문 상태
    //       // productAmount: totalPrice,                      // 상품 총금액
    //       // deliveryAmount: this.deliveryAmount,            // 배달비(3000: 하드코딩)
    //       // orderAmount: totalPrice + this.deliveryAmount,  // 주문금액 = 총금액 + 배달비
    //       // deliveryAddr: this.deliveryAddr,                // 배달 주소
    //       // deliveryMsg: this.deliveryMsg,                  // 배달 메세지

    //       orderDetailList: orderDetailList,      // 주문 상세 List
    //       userId: this.user.userId,              // 회원 ID (FK)
    //       orderName,                             // 수령인
    //       orderAddr: `${this.shipAddress.shipAddr} ${this.shipAddress.shipAddr2}`,     // 배송지 주소
    //       orderPhone,                            // 수령인 연락처
    //       orderMemo,                             // 주문메모
    //       shipMemo,                              // 배송메모
    //       orderPrice,                            // 결제금액
    //       orderPayment,                          // 결제수단
    //       orderCode: "OD01",                     // 주문상태코드
    //     }
    //     // 공통 주문 추가(create) 서비스 함수 실행 
    //     let response = await OrderService.create(data);
    //     // 로깅
    //     console.log(response.data);
    //     // 결제 페이지로 이동 (✅결제 Api 사용은 여기서!!!!)
    //     // 주문 번호(response.data.sono)도 전송
    //     this.$router.push("/approval/" + response.data.sono);
    //   } catch (e) {
    //     console.log(e);     // 에러 출력
    //   }
    // },

    // 주문 취소 함수 : 장바구니 전체 페이지로 이동
    cancelOrder() {
      this.$router.push("/cart");
    },

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

    // 회원 정보 가져오기
    async getUser(userId) {
      try {
        let response = await OrderService.getUser(userId);
        this.user = response.data;
        console.log("로그인된 회원의 정보 : ", response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // 회원의 배송지 정보 가져오기
    async getShipAddress(userId) {
      try {
        let response = await OrderService.getShipAddress(userId);
        this.shipAddress = response.data;
        console.log("회원의 배송지 정보 : ", response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // 배송지 라디오 버튼 함수
    handleRadioChange() {
      if (this.selectedAddr === "option1") {
        this.orderPostcode = "";
        this.orderShipAddr1 = "";
        this.orderShipAddr2 = "";
        this.orderName = "";
        this.orderPhone = "";
        console.log("직접입력이 선택됨");

      } else if (this.selectedAddr === "option2") {
        this.orderPostcode = this.shipAddress.postcode;
        this.orderShipAddr1 = this.shipAddress.shipAddr;
        this.orderShipAddr2 = this.shipAddress.shipAddr2;
        this.orderName = this.user.userName;
        this.orderPhone = this.user.userPhone;
        console.log("주문자 정보와 동일이 선택됨");
      }
    },

    // 카카오 주소 api 연동 부분
    execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: function (data) {
          var addr = ''; // 주소 변수

          //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
          if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
            addr = data.roadAddress;
          } else { // 사용자가 지번 주소를 선택했을 경우
            addr = data.jibunAddress;
          }
          
          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          document.getElementById('postcode').value = data.zonecode;
          document.getElementById("shipAddr").value = addr;
          // 커서를 상세주소 필드로 이동한다.
          document.getElementById("shipAddr2").focus();
        }
      }).open();
    },

    // 회원의 쿠폰 정보 가져오기
    async getUserCoupon(userId) {
      try {
        let response = await OrderService.getUserCoupon(userId);
        this.coupon = response.data;
        console.log("회원의 보유 쿠폰 정보 : ", response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // 회원의 포인트 잔액 정보 가져오기
    async getResultPoint(userId) {
      try {
        let response = await OrderService.getResultPoint(userId);
        this.point = response.data;
        console.log("회원의 포인트 잔액 정보 : ", this.point.resultPoint);
      } catch (e) {
        console.log(e);
      }
    },

    // 포인트 입력한만큼 사용
    usePoint() {
      if (this.tmpPoint <= this.point.resultPoint) {
        console.log("사용가능합니데이");
      } else {
        console.log("사용못합니데이");
        alert('보유하신 적립금액이 부족합니다.');
        this.tmpPoint = 0;
      }
    },

    // 포인트 전액 사용
    usePointAll() {
      console.log("포인트 잔액 (" + this.point.resultPoint + ") 전부 사용");
      this.tmpPoint = this.point.resultPoint;
    },

    // 결제수단 버튼 선택
    handlePayment(option) {
      if (option === 1) {
        this.orderPayment = "카카오페이"
      }
      else if (option === 2) {
        this.orderPayment = "신용카드"
      }
      else if (option === 3) {
        this.orderPayment = "간편결제"
      }
      console.log("결제수단: " + this.orderPayment);
    },

    // 주문금액 구하기
    calculateTotalPrice() {
      // (장바구니 담은 상품 계산) - (쿠폰할인금액) - (적립금할인금액)
    }
  },
  mounted() {
    this.getUser(this.userId);
    this.getUserCoupon(this.userId);
    this.getResultPoint(this.userId);
  },
};
</script>
<style>
@import "@/assets/css/orderview.css";
</style>
