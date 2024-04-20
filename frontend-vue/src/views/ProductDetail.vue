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
          <div id="title"><h2>상품명</h2></div>
        </li>
        <li>
          <div id="content">
            상품 설명 Lorem ipsum dolor sit amet consectetur adipisicing elit.
            Consectetur quisquam, numquam porro necessitatibus sit fugit
          </div>
        </li>
        <li>
          <div id="price"><h2>가격 (원)</h2></div>
        </li>
        <li><div id="option-text">옵션 선택</div></li>
        <select name="option" class="select">
          <option value="1">상품 상세 옵션 선택 1</option>
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
      <div class="col" id="bottom-tap" @click="toggleInfo">
        <a class="router-link link-design" href="#product-bottom">상품 정보</a>
      </div>
      <div class="col" id="bottom-tap" @click="toggleReview">
        <a class="router-link link-design" href="#product-review">상품 후기</a>
      </div>
      <div class="col" id="bottom-tap" @click="toggleQna">
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
          <th scope="col">별점</th>
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

          <td class="col-1 text-center">⭐️⭐️⭐️⭐️⭐️</td>
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
  <!-- 1:1 문의 탭 누르면 보이는 태그 -->
  <div class="row" id="product-qna">
    <table class="table">
      <thead class="table-light text-center">
        <tr>
          <th scope="col">작성자</th>
          <th scope="col">제목</th>
          <th scope="col">비밀글</th>
          <th scope="col">등록일</th>
          <th scope="col">상태</th>
        </tr>
      </thead>
      <tbody class="table-group-divider align-middle">
        <tr>
          <td class="col-2 text-center">OOO</td>
          <td class="col-5">
            <router-link to="/" class="router-link qna-link ms-3"
              >1:1 문의 제목</router-link
            >
          </td>
          <td class="col-1 text-center">Y/N</td>
          <td class="col-2 text-center">24/01/01</td>
          <td class="col-2 text-center">완료</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
export default {
  data() {
    return {
      show: true,
    };
  },
  methods: {
    toggleShow() {
      this.show = !this.show;
    },
  },
};
</script>

<style>
@import "@/assets/css/productDetail.css";
</style>
