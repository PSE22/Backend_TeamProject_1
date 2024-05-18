<template>
  <div class="row row-cols-1 row-cols-md-2 g-4" id="product-top">
    <div class="col">
      <img :src="product?.pdThumbnail" class="card-img-top" alt="..." />
    </div>
    <div class="col">
      <ul class="product-select">
        <li>
          <div id="title">
            <h2>{{ product?.pdName }}</h2>
          </div>
        </li>
        <li><div id="option-text">옵션 선택</div></li>
        <select name="product-option" class="select-box" v-model="selectOption">
          <option v-for="(data, index) in option" :key="index" :value="data">
            {{ data.opName }} + {{ data.opPrice }}원 (남은 수량 :
            {{ data.opStock }})
          </option>
        </select>
        <li><div id="count-text">수량</div></li>
        <li>
          <div class="btn-group" role="group" id="count-box">
            <button
              type="button"
              class="btn btn-outline-secondary"
              @click="decreaseCartCount"
            >
              -
            </button>
            <!-- 장바구니 개수 표시 : 버튼 제목 -->
            <input
              type="button"
              class="btn btn-outline-dark"
              style="width: 60px"
              disabled
              v-model="cartCount"
            />
            <button
              type="button"
              class="btn btn-outline-secondary"
              @click="increaseCartCount"
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
        <li><div id="coupon-text">사용 가능 쿠폰</div></li>
        <select name="coupon-option" class="select-box" v-model="selectCoupon">
          <option v-for="(data, index) in coupon" :key="index" :value="data.cpId">
            {{ data.cpName }}
          </option>
        </select>
        <li id="downloadList">
        <button type="button" class="btn" id="download-button" @click="saveCoupon">
          쿠폰받기
        </button>
        </li>
        <li>
          <div id="price"><h3>배송비 3000원</h3></div>
        </li>

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
              <button
                type="button"
                class="btn"
                id="basket-button"
                @click="sendCart"
              >
                장바구니
              </button>
            <button type="button" class="btn" id="buy-button" @click="goOrder">
              구매하기
            </button>
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
      <div v-for="(data, index) in productImage" :key="index">
        <img :src="data.pdImgUrl" class="card-img-top" alt="..." />
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
          <th scope="col">평점</th>
          <th scope="col">내용</th>
          <th scope="col">등록일</th>
        </tr>
      </thead>
      <tbody class="table-group-divider align-middle">
        <tr v-for="(data, index) in review" :key="index">
          <td class="col-1 text-center">{{ data.userId }}</td>
          <td class="col-2 text-center">{{ data.reviewTitle }}</td>
          <td class="col-1 text-center">{{ data.reviewRate }}</td>
          <td class="col-4">
            <div class="align-items-center text-start">
              <div class="flex-grow-1">
                {{ data.reviewContent }}
              </div>
            </div>
          </td>
          <td class="col-1 text-center">{{ data.addDate }}</td>
        </tr>
      </tbody>
    </table>
  </div>

  <!-- {/* paging 시작 */} -->
  <b-pagination
    class="col-12 mb-3 justify-content-center"
    v-model="reviewPage"
    :total-rows="reviewCount"
    :per-page="reviewPageSize"
    @click="retrieveReview"
  ></b-pagination>
  <!-- {/* paging 끝 */} -->

  <div class="row" id="review-button">
    <form>
      <!-- Button trigger modal -->
      <button
        type="button"
        class="btn btn-primary"
        data-bs-toggle="modal"
        data-bs-target="#reviewModal"
      >
        후기 작성
      </button>

      <!-- Modal -->
      <div class="modal fade" id="reviewModal" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5" id="modalLabel">
                상품 후기 작성
              </h1>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
              ></button>
            </div>
            <div class="modal-body">
              <h3 class="fs-5 mb-2">평점</h3>
              <select class="form-select mb-3" v-model="reviewRate">
                <option value="1">1 (별로에요)</option>
                <option value="2">2 (조금 아쉬워요)</option>
                <option value="3">3 (괜찮아요)</option>
                <option value="4">4 (좋아요)</option>
                <option value="5">5 (아주 맘에 들어요)</option>
              </select>
              <input
                class="form-control mb-3"
                v-model="reviewTitle"
                placeholder="제목을 입력하세요"
              />
              <textarea
                rows="10"
                class="form-control mb-3"
                v-model="reviewContent"
                placeholder="상품 후기를 입력하세요"
              ></textarea>
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                닫기
              </button>
              <button type="submit" class="btn btn-primary" @click="saveReview">
                등록
              </button>
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
            <div v-if="data.pdQnaSecret !== 'Y'">
              <div
                type="button"
                class="ms-3 qna-link"
                data-bs-toggle="modal"
                :data-bs-target="'#qnaModal-' + index"
              >
                {{ data.pdQnaTitle }}
              </div>
            </div>
            <div
              v-else-if="
                data.pdQnaSecret == 'Y' &&
                this.$store.state.user?.userId == data.userId
              "
            >
              <div
                type="button"
                class="ms-3 qna-link"
                data-bs-toggle="modal"
                :data-bs-target="'#qnaModal-' + index"
              >
                {{ data.pdQnaTitle }}
              </div>
            </div>
            <div v-else>
              {{ data.pdQnaTitle }}
            </div>
            <!-- Modal -->
            <div
              class="modal fade"
              :id="'qnaModal-' + index"
              tabindex="-1"
              aria-labelledby="modalLabel"
            >
              <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5" id="modalLabel">
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
                      {{ product?.pdName }}
                    </p>
                    <p>{{ data.pdQnaContent }}</p>
                    <hr />
                    <h2 class="fs-5">답변 내용</h2>
                    <p>
                      {{ data.pdQnaReplyContent }}
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
          <td class="col-2 text-center">{{ data.pqAddDate }}</td>
          <td class="col-2 text-center">{{ data.qrAddDate }}</td>
          <td class="col-2 text-center" v-if="data.pdQnaReplyContent">
            답변 완료
          </td>
          <td class="col-2 text-center" v-else>답변 대기</td>
        </tr>
      </tbody>
    </table>
  </div>
  <!-- {/* paging 시작 */} -->
  <b-pagination
    class="col-12 mb-3 justify-content-center"
    v-model="qnaPage"
    :total-rows="qnaCount"
    :per-page="qnaPageSize"
    @click="retrieveQna"
  ></b-pagination>
  <!-- {/* paging 끝 */} -->
  <div class="row" id="qna-button">
    <form>
      <!-- Button trigger modal -->
      <button
        type="button"
        class="btn btn-primary"
        data-bs-toggle="modal"
        data-bs-target="#qnaModal"
      >
        문의 작성
      </button>
      <div
        class="modal fade"
        id="qnaModal"
        tabindex="-1"
        aria-labelledby="modalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5" id="modalLabel">
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
                {{ product?.pdName }}
              </p>
              <p>비밀글 : <input type="checkbox" v-model="pdQnaSecret" /></p>
              <!-- <div class="input-group"> -->
              <input
                class="form-control mb-3"
                v-model="pdQnaTitle"
                placeholder="제목을 입력하세요"
              />
              <textarea
                rows="10"
                class="form-control mb-3"
                v-model="pdQnaContent"
                placeholder="문의 내용을 입력하세요"
              ></textarea>
              <!-- </div> -->
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
                @click="saveQna"
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
import OptionService from "@/services/shop/OptionService";
import CouponService from "@/services/shop/CouponService";
export default {
  data() {
    return {
      show: true,

      selectOption: {},
      cartCount: 1,

      orderList: [],

      selectCoupon: 0,

      wishListNum: 0,

      product: {},
      productImage: [],

      review: [],
      qna: [],
      option: [],
      coupon: [],

      reviewTitle: "",
      reviewContent: "",
      reviewRate: 0,

      pdQnaTitle: "",
      pdQnaContent: "",
      pdQnaSecret: false,

      reviewPage: 1,
      reviewCount: 0,
      reviewPageSize: 5,

      qnaPage: 1,
      qnaCount: 0,
      qnaPageSize: 5,
    };
  },
  methods: {
    toggleShow() {
      this.show = !this.show;
      if (this.show == false) {
        this.saveWishList();
      } else {
        this.deleteWishList();
      }
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
    async getProductOption(pdId) {
      try {
        let response = await OptionService.get(pdId);
        this.option = response.data;
      } catch (e) {
        console.log(e);
      }
    },
    async getCoupon(pdId) {
      try {
        let response = await CouponService.get(pdId);
        this.coupon = response.data;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async saveCoupon() {
      try {
        let data = {
          cpId: this.selectCoupon,
          userId: this.$store.state.user?.userId,
        };
        if (this.selectCoupon == 0) {
          alert("쿠폰을 선택해주세요")
        } else {
        let response = await ProductService.AddCoupon(data);
        alert(response.data);
        console.log(response.data);
        }
      } catch (e) {
        console.log(e);
      }
    },
    async retrieveWishList(pdId, userId) {
      try {
        // let response = await ProductService.getWishList(this.$route.params.pdId, this.$store.state.user?.userId);
        let response = await ProductService.getWishList(pdId, userId);
        this.wishListNum = response.data;
        if (response.data > 0) {
          this.show = false;
        } else {
          this.show = true;
        }
      } catch (e) {
        console.log(e);
      }
    },
    async saveWishList() {
      try {
        let data = {
          pdId: this.$route.params.pdId,
          userId: this.$store.state.user?.userId,
        };

        let response = await ProductService.create(data);
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async deleteWishList() {
      try {
        let response = await ProductService.remove(
          this.$route.params.pdId,
          this.$store.state.user?.userId
        );
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    goOrder() {
      try {
        console.log(this.selectOption);
        let tempCart = {
          cartCount: this.cartCount,
          opId: this.selectOption.opId,
          opName: this.selectOption.opName,
          opPrice: this.selectOption.opPrice,
          pdId: this.$route.params.pdId,
          pdName: this.product.pdName,
          pdPrice: this.product.pdPrice,
          pdThumbnail: this.product.pdThumbnail,
          userId: this.$store.state.user?.userId,
        };
        if (Object.keys(this.selectOption).length === 0) {
          alert("옵션을 선택해주세요");
          } else {
        this.orderList = [tempCart];
        this.$store.commit("setOrderList", this.orderList);
        console.log(this.orderList);
        this.$router.push("/order");
          }
      } catch (e) {
        console.log(e);
        this.orderList = [];
      }
    },
    async sendCart() {
      try {
        let temp = {
          opId: this.selectOption.opId,
          cartCount: this.cartCount,
          userId: this.$store.state.user?.userId,
        };
        if (Object.keys(this.selectOption).length === 0) {
          alert("옵션을 선택해주세요");
        } else {
        let response = await ProductService.AddCart(temp);
        alert("장바구니에 담았습니다");
        console.log(response.data);
        }
      } catch (e) {
        console.log(e);
      }
    },
    async retrieveReview() {
      try {
        let response = await ReviewService.getAll(
          this.$route.params.pdId,
          this.reviewPage - 1,
          this.reviewPageSize
        );
        const { review, totalItems } = response.data;
        this.review = review;
        this.reviewCount = totalItems;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async saveReview() {
      try {
        let temp = {
          pdId: this.$route.params.pdId,
          userId: this.$store.state.user?.userId,
          reviewTitle: this.reviewTitle,
          reviewContent: this.reviewContent,
          reviewRate: this.reviewRate,
          reviewCode: "BO0201",
        };
        // alert(JSON.stringify(temp));
        let response = await ReviewService.create(temp);
        console.log(response.data);
        this.retrieveReview(); // 재조회
      } catch (e) {
        console.log(e);
      }
    },
    async retrieveQna() {
      try {
        let response = await QnaService.getAll(
          this.$route.params.pdId,
          this.qnaPage - 1,
          this.qnaPageSize
        );
        const { qna, totalItems } = response.data;
        this.qna = qna;
        this.qnaCount = totalItems;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async saveQna() {
      try {
        let temp = {
          pdId: this.$route.params.pdId,
          userId: this.$store.state.user?.userId,
          pdQnaTitle: this.pdQnaTitle,
          pdQnaContent: this.pdQnaContent,
          pdQnaSecret: this.pdQnaSecret ? "Y" : "N",
          pdQnaCode: "BO0202",
        };

        let response = await QnaService.create(temp);
        console.log(response.data);
        this.pdQnaSecret = false;
        this.retrieveQna(); // 재조회
      } catch (e) {
        console.log(e);
      }
    },
    increaseCartCount() {
      this.cartCount = this.cartCount + 1;
    },
    decreaseCartCount() {
      if (this.cartCount > 1) {
        this.cartCount = this.cartCount - 1;
      }
    },
  },
  mounted() {
    this.getProduct(this.$route.params.pdId);
    this.getProductImage(this.$route.params.pdId);
    this.getProductOption(this.$route.params.pdId);
    this.getCoupon(this.$route.params.pdId);
    this.retrieveWishList(
      this.$route.params.pdId,
      this.$store.state.user?.userId
    );
    this.retrieveReview();
    this.retrieveQna();
  },
};
</script>

<style>
@import "@/assets/css/productDetail.css";
</style>
