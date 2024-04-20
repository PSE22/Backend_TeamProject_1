<template>
  <div class="mypage-layout">
    <MyPageMainMenu />
    <div class="mypage-contents">
      <div class="mypage-backcolor">
        <div class="mypage-subtitle">나의 위시리스트</div>
        <div class="tabList"><p class="mypage-wish-subtitle">찜 목록</p></div>
        <div class="mypage-product-box">
          <div class="form-check">
            <label class="check-all">
              <input
                class="form-check-input"
                type="checkbox"
                value="selectAll"
                name="cartList"
              />
              <p class="form-check-label" for="flexCheckDefault">전체선택</p>
            </label>
            <div class="btngroup">
              <button type="button">선택삭제</button>
              <button type="button">선택담기</button>
            </div>
          </div>
          <div class="row row-cols-1 row-cols-md-3 g-4" id="main-products">
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
                <label class="check-item">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    value="selectAll"
                    name="cartList"
                  />
                </label>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MyPageMainMenu from "@/components/mypage/MyPageMainMenu.vue";
export default {
  components: {
    MyPageMainMenu,
  },
};
</script>
<style>
@import "@/assets/css/mypage.css";
@import "@/assets/css/mypagewish.css";
</style>
