<template>
  <div class="row row-cols-1 row-cols-md-2 g-4" id="product-top">
    <div class="col" v-if="productImage.length > 0">
      <img :src="productImage[0].pdImgUrl" class="card-img-top" alt="..." />
    </div>
    <div class="col">
      <ul class="product-select">
        <li>
          <div id="title">
            <h2>{{ product?.pdName }}</h2>
          </div>
        </li>
        <li><div id="option-text">옵션 선택</div></li>
        <select name="product-option" class="select-box">
          <option value="1" selected>상품 상세 옵션 선택 1</option>
          <option value="2">상품 상세 옵션 선택 2</option>
          <option value="3">상품 상세 옵션 선택 3</option>
          <option value="4">상품 상세 옵션 선택 4</option>
        </select>
        <li><div id="count-text">수량</div></li>
        <li>
          <div class="btn-group" role="group" id="count-box">
            <button
              type="button"
              class="btn btn-outline-secondary"
              @click="decreaseClickCount"
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
              {{ this.click }}
            </button>
            <button
              type="button"
              class="btn btn-outline-secondary"
              @click="increaseClickCount"
            >
              +
            </button>
          </div>
        </li>
        <li>
          <div id="price">
            <h2>가격 {{ product?.pdPrice }}원</h2>
          </div>
        </li>
        <li><div id="coupon-text">쿠폰 선택</div></li>
        <select name="coupon-option" class="select-box">
          <option value="1" selected>쿠폰명 1</option>
          <option value="2">쿠폰명 2</option>
          <option value="3">쿠폰명 3</option>
          <option value="4">쿠폰명 4</option>
        </select>

        <li>
          <div id="price"><h3>배송비 3000원</h3></div>
        </li>
        <br /><br /><br /><br /><br />
        <li>
          <div class="btn-group" role="group">
            <img
              @click="toggleShow"
              v-if="show"
              src="@/assets/img/free-icon-font-circle-heart-9272486.png"
            />
            <img
              @click="toggleShow"
              v-else
              src="@/assets/img/free-icon-font-circle-heart-9270879.png"
            />
            <button type="button" class="btn" id="basket-button">
              장바구니
            </button>
            <button type="button" class="btn" id="buy-button">구매하기</button>
          </div>
        </li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col" id="bottom-tap">
        <a class="router-link link-design" href="#product-bottom">상품 정보</a>
      </div>
      <div class="col" id="bottom-tap">
        <a class="router-link link-design" href="#product-review">상품 후기</a>
      </div>
      <div class="col" id="bottom-tap">
        <a class="router-link link-design" href="#product-qna">1:1 문의</a>
      </div>
    </div>
  </div>
  <!-- 상품 정보 탭 누르면 보이는 태그 -->
  <div class="row row-cols-1 g-4" id="product-bottom">
    <div class="col">
      <div class="card h-100" v-if="productImage.length > 0">
        <img :src="productImage[1].pdImgUrl" class="card-img-top" alt="..." />
      </div>
    </div>
  </div>
  <!-- 상품 후기 탭 누르면 보이는 태그 -->
  <div class="row" id="product-review">
    <table class="table">
      <thead class="table-light text-center">
        <tr>
          <th scope="col">작성자</th>
          <th scope="col">제목</th>
          <th scope="col">상품옵션</th>
          <th scope="col">평점</th>
          <th scope="col">내용</th>
          <th scope="col">등록일</th>
        </tr>
      </thead>
      <tbody class="table-group-divider align-middle">
        <tr v-for="(data, index) in review" :key="index">
          <td class="col-1 text-center">{{ data.userId }}</td>
          <td class="col-1 text-center">{{ data.reviewTitle }}</td>
          <td class="col-2 text-center">
            <div class="flex-grow-1">상품 옵션명</div>
          </td>
          <td class="col-1 text-center">{{ data.reviewRate }}</td>
          <td class="col-4">
            <div class="align-items-center text-start">
              <div class="flex-grow-1">
                {{ data.reviewContent }}
              </div>
              <img
                src="https://via.placeholder.com/100x100?text=Image"
                class="img-thumbnail me-3"
              />
              <img
                src="https://via.placeholder.com/100x100?text=Image"
                class="img-thumbnail me-3"
              />
              <img
                src="https://via.placeholder.com/100x100?text=Image"
                class="img-thumbnail me-3"
              />
            </div>
          </td>
          <td class="col-1 text-center">{{ data.addDate }}</td>
        </tr>
      </tbody>
    </table>
  </div>
  <div class="row" id="review-button">
    <form>
      <!-- Button trigger modal -->
      <button
        type="button"
        class="btn btn-primary"
        data-bs-toggle="modal"
        data-bs-target="#exampleModal"
      >
        후기 작성
      </button>

      <!-- Modal -->
      <div class="modal fade" id="exampleModal" tabindex="-1">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5" id="exampleModalLabel">
                상품 후기 작성
              </h1>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
              ></button>
            </div>
            <div class="modal-body">
              <h3 class="fs-5 mb-2">상품 옵션</h3>
              <select class="form-select mb-3">
                <option value="1" selected>상품 상세 옵션 선택 1</option>
                <option value="2">상품 상세 옵션 선택 2</option>
                <option value="3">상품 상세 옵션 선택 3</option>
                <option value="4">상품 상세 옵션 선택 4</option>
              </select>
              <h3 class="fs-5 mb-2">평점</h3>
              <select class="form-select mb-3">
                <option value="1">1 (별로에요)</option>
                <option value="2">2 (조금 아쉬워요)</option>
                <option value="3">3 (괜찮아요)</option>
                <option value="4">4 (좋아요)</option>
                <option value="5" selected>5 (아주 맘에 들어요)</option>
              </select>
              <textarea
                rows="10"
                class="form-control mb-3"
                placeholder="상품 후기를 입력하세요"
              ></textarea>
              <input
                class="mb-2"
                type="file"
                id="image1"
                accept="image/png, image/jpeg"
              />
              <input
                class="mb-2"
                type="file"
                id="image2"
                accept="image/png, image/jpeg"
              />
              <input
                class="mb-2"
                type="file"
                id="image3"
                accept="image/png, image/jpeg"
              />
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                닫기
              </button>
              <button type="submit" class="btn btn-primary">등록</button>
            </div>
          </div>
        </div>
      </div>
    </form>
  </div>
  <!-- 1:1 문의 탭 누르면 보이는 태그 -->
  <div class="row" id="product-qna">
    <table class="table">
      <thead class="table-light text-center">
        <tr>
          <th scope="col">작성자</th>
          <th scope="col">제목</th>
          <th scope="col">비밀글</th>
          <th scope="col">등록일</th>
          <th scope="col">답변일</th>
          <th scope="col">상태</th>
        </tr>
      </thead>
      <tbody class="table-group-divider align-middle">
        <tr v-for="(data, index) in qna" :key="index">
          <td class="col-1 text-center">{{ data.userId }}</td>
          <td class="col-4">
            <div
              type="button"
              class="ms-3 qna-link"
              data-bs-toggle="modal"
              :data-bs-target="'#exampleModal-' + index"
            >
              {{ data.pdQnaTitle }}
            </div>
            <!-- Modal -->
            <div
              class="modal fade"
              :id="'exampleModal-' + index"
              tabindex="-1"
              aria-labelledby="exampleModalLabel"
              aria-hidden="true"
            >
              <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">
                      {{ data.pdQnaTitle }} / {{ data.pdQnaSecret }}
                    </h1>
                    <button
                      type="button"
                      class="btn-close"
                      data-bs-dismiss="modal"
                      aria-label="Close"
                    ></button>
                  </div>
                  <div class="modal-body">
                    <h2 class="fs-5">작성자 : {{ data.userId }}</h2>
                    <p class="text-muted">
                      상품명 : 필통&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;옵션 : 파란색
                    </p>
                    <p>{{ data.pdQnaContent }}</p>
                    <img
                      src="https://via.placeholder.com/100x100?text=Image"
                      class="img-thumbnail me-3"
                    />
                    <hr />
                    <h2 class="fs-5">답변 내용</h2>
                    <p>
                      Lorem ipsum dolor sit amet consectetur adipisicing elit.
                      Impedit debitis, aperiam incidunt dolor odio magnam eum
                      quod ratione veniam quo minima id suscipit quisquam
                      tenetur facere eligendi placeat corrupti. Vitae.
                    </p>
                  </div>
                  <div class="modal-footer">
                    <button
                      type="button"
                      class="btn btn-secondary"
                      data-bs-dismiss="modal"
                    >
                      닫기
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </td>
          <td class="col-1 text-center">{{ data.pdQnaSecret }}</td>
          <td class="col-2 text-center">24/01/01</td>
          <td class="col-2 text-center">24/01/01</td>
          <td class="col-2 text-center">완료</td>
        </tr>
      </tbody>
    </table>
  </div>
  <div class="row" id="qna-button">
    <form>
      <!-- Button trigger modal -->
      <button
        type="button"
        class="btn btn-primary"
        data-bs-toggle="modal"
        data-bs-target="#exampleModal-3"
      >
        문의 작성
      </button>
      <div
        class="modal fade"
        id="exampleModal-3"
        tabindex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5" id="exampleModalLabel">
                1:1 문의 작성
              </h1>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <p class="text-muted">
                상품명 : 필통&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;옵션 : 파란색
              </p>
              <!-- <div class="input-group"> -->
              <textarea
                rows="10"
                class="form-control mb-3"
                placeholder="상품 후기를 입력하세요"
              ></textarea>
              <!-- </div> -->
              <input
                class="mb-2"
                type="file"
                id="image1"
                accept="image/png, image/jpeg"
              />
              <input
                class="mb-2"
                type="file"
                id="image1"
                accept="image/png, image/jpeg"
              />
              <input
                class="mb-2"
                type="file"
                id="image1"
                accept="image/png, image/jpeg"
              />
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                닫기
              </button>
              <button
                type="button"
                class="btn btn-primary"
                data-bs-dismiss="modal"
              >
                등록
              </button>
            </div>
          </div>
        </div>
      </div>
    </form>
  </div>
</template>
<script>
import ProductService from "@/services/shop/ProductService";
import ReviewService from "@/services/shop/ReviewService";
import QnaService from "@/services/shop/QnaService";
export default {
  data() {
    return {
      show: true,
      product: {},
      productImage: [],
      review: [],
      qna: [],
      click: 0,

      page: 1,
      count: 0,
      pageSize: 3,

      pageSizes: [3, 6, 9],
    };
  },
  methods: {
    toggleShow() {
      this.show = !this.show;
    },
    async getProduct(pdId) {
      try {
        let response = await ProductService.get(pdId);
        this.product = response.data;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async getProductImage(pdId) {
      try {
        let response = await ProductService.getImage(pdId);
        this.productImage = response.data;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async retrieveReview() {
      try {
        let response = await ReviewService.getAll(this.page - 1, this.pageSize);
        const { review, totalItems } = response.data;
        this.review = review;
        this.count = totalItems;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async retrieveQna() {
      try { 
        let response = await QnaService.getAll(this.page - 1, this.pageSize);
        const { qna, totalItems } = response.data;
        this.qna = qna;
        this.count = totalItems;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    increaseClickCount() {
      this.click = this.click + 1;
    },
    decreaseClickCount() {
      if (this.click > 0) {
        this.click = this.click - 1;
      }
    },
  },
  mounted() {
    this.getProduct(this.$route.params.pdId);
    this.getProductImage(this.$route.params.pdId);
    this.retrieveReview();
    this.retrieveQna();
  },
};
</script>

<style>
@import "@/assets/css/productDetail.css";
</style>
