<template>
  <div class="row row-cols-1 row-cols-md-2 g-4" id="product-top">
    <div class="col">
      <div class="card h-100">
        <img
          src="https://via.placeholder.com/400x400?text=Image"
          class="card-img-top"
          alt="..."
        />
        <div class="card-body">
          <h5 class="card-title">Card title</h5>
          <p class="card-text">상품 설명</p>
        </div>
      </div>
    </div>
    <div class="col">
      <ul class="product-select">
        <li>
          <div id="title"><h2>상품명 : </h2></div>
        </li>
        <li>
          <div id="content">
            상품 설명 Lorem ipsum dolor sit amet consectetur adipisicing elit.
            Consectetur quisquam, numquam porro necessitatibus sit fugit
          </div>
        </li>
        <li>
          <div id="price"><h2>가격 원</h2></div>
        </li>
        <li><div id="option-text"></div></li>
        <select name="option" class="select">
          <option value="1" selected>상품 상세 옵션 선택 1</option>
          <option value="2">상품 상세 옵션 선택 2</option>
          <option value="3">상품 상세 옵션 선택 3</option>
          <option value="4">상품 상세 옵션 선택 4</option>
        </select>
        <li><div id="count-text">수량 : </div></li>
        <li>
          <div class="btn-group" role="group" id="count-box">
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
              10{{ clickCount }}
            </button>
            <button
              type="button"
              class="btn btn-outline-secondary"
              @click="increaseCount"
            >
              +
            </button>
          </div>
        </li>
        <br /><br /><br /><br /><br /><br /><br /><br /><br />
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
      <div class="card h-100">
        <img
          src="https://via.placeholder.com/400x400?text=Image"
          class="card-img-top"
          alt="..."
        />
      </div>
    </div>
  </div>
  <!-- 상품 후기 탭 누르면 보이는 태그 -->
  <div class="row" id="product-review">
    <table class="table">
      <thead class="table-light text-center">
        <tr>
          <th scope="col">작성자</th>
          <th scope="col">상품옵션</th>
          <th scope="col">평점</th>
          <th scope="col">내용</th>
          <th scope="col">등록일</th>
        </tr>
      </thead>
      <tbody class="table-group-divider align-middle">
        <tr>
          <td class="col-1 text-center">OOO</td>
          <td class="col-2 text-center">
            <div class="flex-grow-1">
              This is some content from a media component. This is some content
              from a media component.
            </div>
          </td>
          <td class="col-1 text-center">1/5</td>
          <td class="col-4">
            <div class="align-items-center text-start">
              <div class="flex-grow-1">
                This is some content from a media component. This is some
                content from a media component.
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
          <td class="col-1 text-center">24/01/01</td>
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
        <tr>
          <td class="col-1 text-center">OOO</td>
          <td class="col-4">
            <div
              type="button"
              class="ms-3 qna-link"
              data-bs-toggle="modal"
              data-bs-target="#exampleModal-2"
            >
              1:1 문의 제목
            </div>
            <!-- Modal -->
            <div
              class="modal fade"
              id="exampleModal-2"
              tabindex="-1"
              aria-labelledby="exampleModalLabel"
              aria-hidden="true"
            >
              <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">
                      1:1 문의합니다.(여기 제목 옆에 비밀글여부 바인딩 하면
                      될거같은데)
                    </h1>
                    <button
                      type="button"
                      class="btn-close"
                      data-bs-dismiss="modal"
                      aria-label="Close"
                    ></button>
                  </div>
                  <div class="modal-body">
                    <h2 class="fs-5">작성자</h2>
                    <p class="text-muted">
                      상품명 : 필통&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;옵션 : 파란색
                    </p>
                    <p>
                      Lorem ipsum dolor sit amet consectetur adipisicing elit.
                      Impedit debitis, aperiam incidunt dolor odio magnam eum
                      quod ratione veniam quo minima id suscipit quisquam
                      tenetur facere eligendi placeat corrupti. Vitae.
                    </p>
                    <img
                      src="https://via.placeholder.com/100x100?text=Image"
                      class="img-thumbnail me-3"
                    />
                    <hr />
                    <h2 class="fs-5">답변자</h2>
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
          <td class="col-1 text-center">Y/N</td>
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
import ProductService from '@/services/shop/ProductService';
export default {
  data() {
    return {
      show: true,
      product: null
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
      } catch (e) {
        console.log(e);
      }
    }
  },
    mounted() {
    // TODO: 화면이 뜰 때 상품 상세 조회 : 상품번호(spno)
    this.getProduct(this.$route.params.pdId);
  },
};
</script>

<style>
@import "@/assets/css/productDetail.css";
</style>
