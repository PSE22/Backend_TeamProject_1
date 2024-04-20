// 장바구니 // 기능 : 전체선택, 선택삭제, 전제삭제, 주문하기
<template>
  <div class="container">
    <!-- 장바구니 타이틀 -->
    <h1 style="margin-top: 80px; margin-bottom: 20px">장바구니</h1>
    <div>
      <!-- 장바구니 리스트 -->
      <table class="table text-center">
        <!-- 장바구니 리스트명 -->
        <thead class="table-light">
          <tr>
            <th scope="col ">
              <!-- 전체선택 체크박스 -->
              <input
                class="form-check-input"
                type="checkbox"
                value="selectAll"
                name="cartList"
              />
              <label class="form-check-label" for="flexCheckDefault"></label>
            </th>
            <th scope="col">상품</th>
            <th scope="col">수량</th>
            <th scope="col">가격</th>
            <th scope="col">배송비</th>
          </tr>
        </thead>
        <tbody class="table-group-divider">
          <tr>
            <td>
              <input
                class="form-check-input"
                type="checkbox"
                value="selectAll"
                name="cartList"
              />
            </td>
            <td>
              <div>
                <img
                  src="https://via.placeholder.com/100x100?text=Image"
                  class="img-thumbnail"
                  alt="..."
                />
                상품이름
              </div>
            </td>
            <td>
              <!-- 장바구니 수량 -->
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
                  10{{ cartCount }}
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
            <td>30,000</td>
            <td>@mdo</td>
          </tr>
        </tbody>
      </table>
      <button
        type="button"
        class="btn btn-dark"
        style="
          --bs-btn-padding-y: 0.25rem;
          --bs-btn-padding-x: 0.5rem;
          --bs-btn-font-size: 0.75rem;
        "
      >
        선택삭제
      </button>
      <div class="d-grid gap-2 col-6 mx-auto d-md-flex justify-content">
        <button class="btn btn-outline-dark btn-lg col-5" type="button">
          선택주문
        </button>
        <button class="btn btn-outline-dark btn-lg col-5" type="button">
          전체주문
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      cart: [], // 장바구니 객체배열
      check: false, // 체크박스 전체선택
    };
  },
  methods: {
    // 장바구니 전체조회
    async retrieveCart() {},
    // 장바구니 삭제 함수 : delete 버튼 태그
    async deleteSimpleCart() {}, // cartId
    // 주문페이지 이동 함수
    goOrder() {},
    // 체크박스 전체선택
    selectAll() {},
  },
  mounted() {
    this.retrieveCart();
  },
};
</script>

<style></style>
