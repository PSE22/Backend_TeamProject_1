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
        <!-- thead -->
        <thead class="table-light">
          <tr>
            <th scope="col">상품</th>
            <th scope="col">옵션</th>
            <th scope="col">수량</th>
            <th scope="col">가격</th>
          </tr>
        </thead>
        <!-- tbody -->
        <tbody class="table-group-divider align-middle">
          <tr v-for="(data, index) in orderList" :key="index">
            <!-- 상품 이미지 & 상품명 칸 -->
            <td class="col-4">
              <div class="d-flex align-items-center text-start">
                <div class="flex-shrink-0">
                  <img :src="data.pdThumbnail" class="img-thumbnail me-3" style="width: 100px; height: 100px" />
                </div>
                <div class="flex-grow-1 ms-3">
                  {{ data.pdName }}
                </div>
              </div>
            </td>
            <!-- 옵션명 칸 -->
            <td class="col-2">{{ data.opName }}</td>
            <!-- 수량 칸 -->
            <td class="col-2">{{ data.cartCount }}</td>
            <!-- 가격 -->
            <td class="col-2">
              {{ (data.pdPrice + data.opPrice) }}
            </td>
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
                @change="handleRadioChange"/>
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
                  <input type="test" id="postcode" class="form-control" placeholder="우편번호" v-model="orderPostcode" />
                </div>
                <div class="col-3">
                  <button @click="execDaumPostcode()" class="btn btn-primary mb-3">
                    우편번호찾기
                  </button>
                </div>
              </div>
              <div class="row">
                <div class="col-6">
                  <input type="text" id="shipAddr" class="form-control" placeholder="주소 입력" v-model="orderShipAddr1" />
                </div>
                <div class="col-6">
                  <input  type="text" id="shipAddr2" class="form-control" placeholder="상세주소 입력"
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
                  <input type="test" class="form-control" placeholder="우편번호" readonly
                    v-model="orderPostcode" />
                </div>
                <div class="col-3">
                  <button type="submit" class="btn btn-primary mb-3" disabled>
                    우편번호찾기
                  </button>
                </div>
              </div>
              <div class="row">
                <div class="col-6">
                  <input type="text" class="form-control" placeholder="주소 입력" readonly
                    v-model="orderShipAddr1" />
                </div>
                <div class="col-6">
                  <input type="text" class="form-control" placeholder="상세주소 입력" readonly
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
          <div class="col-3 row-title pt-3">쿠폰 선택</div>
          <div class="col-9 row-content">
            <div class="row">
              <div class="col-6">
                <select class="form-select" v-model="selectCoupon">
                  <!-- 회원 보유 쿠폰 반복문 돌리기 -->
                  <option v-for="(data, index) in coupon" :key="index" :value="data" selec>
                    {{ data?.cpName }}
                  </option>
                </select>
              </div>
              <div class="col-3">
                <button type="submit" class="btn btn-primary" @click="clickCoupon()">쿠폰적용</button>
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
              <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off"
                @change="handlePayment(1)" />
              <label class="btn mb-0 btn-outline-primary rounded-start" for="btnradio1">카카오페이</label>
              <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off"
                @change="handlePayment(2)" />
              <label class="btn mb-0 btn-outline-primary" for="btnradio2">신용카드</label>
              <input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off"
                @change="handlePayment(3)" />
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
          <h3 class="card-title"> {{ totalPrice }}원</h3>
        </div>
      </div>
      <div class="card text-center mb-3" style="max-width: 18rem">
        <div class="card-header">할인 금액</div>
        <div class="card-body">
          <h3 class="card-title">{{ parseInt(discount) + parseInt(tmpPoint) }}원</h3>
        </div>
      </div>
      <div class="card text-center mb-3" style="max-width: 18rem">
        <div class="card-header">최종 결제 금액</div>
        <div class="card-body">
          <h3 class="card-title">{{ totalPrice - (parseInt(discount) + parseInt(tmpPoint)) }}원</h3>
        </div>
      </div>
    </div>
    <!-- 결제 버튼 -->
    <div class="text-center">
      <button class="btn btn-warning btn-lg me-md-2 col-3 mt-5 mb-5" type="button" @click="cancelOrder()">
        주문취소
      </button>
      <button class="btn btn-danger btn-lg me-md-2 col-3 mt-5 mb-5" type="button" @click="goApproval()">
        주문하기
      </button>
    </div>
  </div>
</template>
<script>
import OrderService from "@/services/shop/OrderService";

export default {
  data() {
    return {
      userId: this.$store.state.user.userId,
      orderList: [],            // 임시 장바구니 배열(로컬 저장소)
      selectCoupon: {},         // 사용할 쿠폰의 값을 담을 객체
      selectedAddr: "option1",  // 배송지 선택 라디오 버튼 값 초기 설정
      discount: 0,              // 할인금액
      tmpPoint: 0,              // 사용할 적립금
      totalPrice: 0,            // 상품 총액

      // 주문 테이블에 보낼 데이터를 담을 변수
      orderPostcode: "",    // 우편번호
      orderShipAddr1: "",   // 주소
      orderShipAddr2: "",   // 상세주소
      orderName: "",        // 수령인
      orderPhone: "",       // 수령인번호
      orderShipMemo: "",    // 배송요청사항
      orderPayment: "",     // 결제수단

      // 서버에서 받아온 값을 저장
      cart: [],             // 장바구니 객체 배열
      user: {},             // user 객체
      shipAddress: {},      // 배송지 객체
      coupon: [],           // 보유 쿠폰 배열
      point: {},            // 포인트 잔액
    };
  },
  methods: {
    // 주문 정보 저장 (주문 테이블 + 주문 상세 테이블 insert)
    async goApproval() {
      // 주문 상세 배열에 객체 넣기 (반복문)
      let orderDetailList = [];                 // 주문상세 객체배열 정의
      for (const data of this.orderList) {
        let orderDetail = {}                    // 주문 상세 객체 정의
        orderDetail.opId = data.opId;                                   // 상품(옵션) 번호
        orderDetail.orderDetailCnt = data.cartCount;                    // 상품 개수 (장바구니 수량)
        orderDetail.orderDetailPrice = data.opPrice + data.pdPrice;     // 주문상세가격
        orderDetail.orderDetailCode = "OD0101";                           // 주문상태코드
        orderDetailList.push(orderDetail);    // 주문 상세 배열에 값 넣기
      }

      // 3) spring insert 요청 (비동기)
      try {
        // 임시 주문 객체 : 주문 상세 객체 배열 (List) 포함
        let data = {
          orderDetailList: orderDetailList,      // 주문 상세 List
          userId: this.user.userId,              // 회원 ID (FK)
          orderName: this.orderName,             // 수령인
          orderAddr: `${this.shipAddress.shipAddr} ${this.shipAddress.shipAddr2}`,     // 배송지 주소
          orderPhone: this.orderPhone,           // 수령인 연락처
          shipMemo: this.orderShipMemo,          // 배송메모
          orderPrice: this.totalPrice - (parseInt(this.discount) + parseInt(this.tmpPoint)),           // 결제금액
          orderPayment: this.orderPayment,       // 결제수단
          orderCode: "OD0101",                   // 주문상태코드
          postcode: this.orderPostcode,          // 우편번호
        }

        let response = await OrderService.create(data);       // 주문 추가(create) 서비스 함수 실행 
        console.log(response.data);
        // 장바구니 삭제 반복문
        for (const data of this.orderList) {
          this.deleteCart(data.cartId);
        }
        // 쿠폰 삭제함수 실행
        await this.updateUserCoupon();
        // 사용포인트 저장함수 실행
        await this.saveUsePoint();
        // 배송지 저장함수 실행
        await this.createShipAddress();

        alert("주문이 완료되었습니다.");
        // 주문 상세 페이지로 이동
        this.$router.push("/mypage/order");

      } catch (e) {
        console.log(e);
      }
    },

    // 사용 포인트 테이블에 데이터 저장
    async saveUsePoint() {
      try {
        let data = {
          usePointId: null,                 // 사용 포인트 ID
          orderId: null,                    // 주문 ID
          usePointPrice: this.tmpPoint      // 차감금액
        } 
        let response = await OrderService.createUsePoint(data);
        console.log("사용 포인트 저장 : ", response.data);

      } catch (e) {
        console.log("사용 포인트 없음 : ", e);
      }
    },

    // 사용한 쿠폰의 상태 'N(비활성화)' 으로 수정
    async updateUserCoupon() {
      try {
        let response = await OrderService.updateUserCoupon(this.selectCoupon.cpId, this.$store.state.user.userId);
        console.log("사용 쿠폰 삭제 성공: ", response.data);
      } catch (e) {
        console.log("사용 쿠폰 없음", e);
      }
    },

    // 장바구니 정보 가져오기
    async getCart(userId) {
      try {
        let response = await OrderService.getCart(userId);
        this.cart = response.data;
        console.log("회원의 장바구니 정보 : ", response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // 장바구니 삭제
    async deleteCart(cartId) {
      try {
        let response = await OrderService.removeCart(cartId);
        console.log("장바구니 삭제 완료", response.data);
        // 삭제 후 재조회
        this.getCart();
      } catch (e) {
        console.log("장바구니 삭제 실패 : ", e);
      }
    },

    // 주문 취소 함수 : 장바구니 전체 페이지로 이동
    cancelOrder() {
      try {
        this.$router.push("/cart");
      }
      catch (e) {
        console.log(e);
      }
    },

    // 장바구니 상품 총 금액 구하기 (최종 결제금액 아님)
    sumOrderAmount() {
      try {
        this.orderList = this.$store.getters.getOrderList;
        // 장바구니의 물품별 금액(단가*개수)을 구해서 모든 상품별 총금액 구하기
        this.totalPrice = this.orderList
          .map((data) => (data.pdPrice + data.opPrice) * data.cartCount)   // 상품별 금액 배열
          .reduce((acc, cur) => acc + cur, 0);          // acc 는 반환값을 누적, cur 는 배열의 현재 요소를 가리킴
        if (this.totalPrice < 60000) {
          this.totalPrice += 3000;   // 상품 총 금액이 6만원 미만이면 배송비(3000원) 추가
        }
        console.log("상품 금액 + 배송비 : ", this.totalPrice);
      } catch (e) {
        console.log(e);
      }
    },

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

    // 입력한 배송지 저장 
    async createShipAddress() {
      try {
        let data = {
          userId: this.userId,                // 회원 ID
          shipAddr: this.orderShipAddr1,      // 배송지 주소
          shipAddrName: "배송지명1",           // 배송지명
          shipAddr2: this.orderShipAddr2,     // 배송지 상세주소
          postcode: this.orderPostcode,       // 우편번호
        }
        let response = await OrderService.createShipAddress(data);
        console.log("신규 배송지 저장 : ", response.data);
      } catch (e) {
        console.log(" 배송지 저장 안됨?: ", e);
      }
    },

    // 배송지 라디오 버튼 함수
    handleRadioChange() {
      try {
        if (this.selectedAddr === "option1") {
          this.orderPostcode = "";
          this.orderShipAddr1 = "";
          this.orderShipAddr2 = "";
          this.orderName = "";
          this.orderPhone = "";
          console.log("직접 입력이 선택됨");

        } else if (this.selectedAddr === "option2") {
          this.orderPostcode = this.shipAddress.postcode;
          this.orderShipAddr1 = this.shipAddress.shipAddr;
          this.orderShipAddr2 = this.shipAddress.shipAddr2;
          this.orderName = this.user.userName;
          this.orderPhone = this.user.userPhone;
          console.log("주문자 정보와 동일이 선택됨");
        }
      } catch (e) {
        console.log(e);
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
        console.log("회원의 보유 쿠폰 정보 : ", response.data);
        // 주문한 상품에 사용가능한 쿠폰만 가져오기
        for (const data of response.data) {
          for (const data2 of this.orderList) {
            if (data.pdId === data2.pdId) {
              console.log("pdId 같음: ", data.pdId);
              this.coupon.push(data);
            } else {
              console.log("pdId 다름(사용불가): ", data.pdId);
            }
          }
        }
        console.log("최종 사용 가능 쿠폰 정보 : ", this.coupon);

      } catch (e) {
        console.log(e);
      }
    },

    // 쿠폰선택 버튼 눌렀을 때 실행
    clickCoupon() {
      try {
        alert("[" + this.selectCoupon.cpName + "] 쿠폰 적용 완료!");
        console.log(this.selectCoupon);
        if (this.selectCoupon.cpDcPrice == null) {
          // 쿠폰의 할인금액(cpDcPrice)이 null 이면, "할인율"만큼 할인하기
          this.discount = this.selectCoupon.cpDcRate * this.totalPrice;
        } else if (this.selectCoupon.cpDcRate == null) {
          // 쿠폰의 할인율(cpDcRate)이 null 이면, "할인금액"만큼 할인하기
          this.discount = this.selectCoupon.cpDcPrice;
        }
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
      try {
        if (this.tmpPoint <= this.point.resultPoint && this.tmpPoint <= this.totalPrice) {
          console.log("사용가능합니데이");
        } else {
          if (this.tmpPoint > this.point.resultPoint) {
            alert('보유하신 적립금액이 부족합니다.');
            this.tmpPoint = 0;
          } else if (this.tmpPoint > this.totalPrice) {
            alert('사용하실 적립금액이 초과되었습니다.');
            this.tmpPoint = 0;
          }
        }
      } catch (e) {
        console.log(e);
      }
    },

    // 포인트 전액 사용
    usePointAll() {
      try {
        console.log("포인트 잔액 (" + this.point.resultPoint + ") 전부 사용");
        this.tmpPoint = this.point.resultPoint;
      }
      catch (e) {
        console.log(e);
      }
    },

    // 결제수단 버튼 선택
    handlePayment(option) {
      try {
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
      }
      catch (e) {
        console.log(e);
      }

    },

  },
  mounted() {
    this.sumOrderAmount();
    this.getCart(this.$store.state.user.userId);
    this.getUser(this.$store.state.user.userId);
    this.getShipAddress(this.$store.state.user.userId);
    this.getUserCoupon(this.$store.state.user.userId);
    this.getResultPoint(this.$store.state.user.userId);
  },
};
</script>
<style>
@import "@/assets/css/orderview.css";
</style>
