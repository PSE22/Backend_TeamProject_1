<template>
  <!-- MyPageWishList.vue -->
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
              <button type="button"
              @click="deleteProduct">선택삭제</button>
              <button type="button">선택담기</button>
            </div>
          </div>
          <div class="row row-cols-1 row-cols-md-4 g-4" id="main-products"
              >
            <div class="col"
            v-for="(data, index) in wishlist" :key="index">
              <div class="card h-100" 
                >
                <img
                  :src="data.pdThumbnail"
                  class="card-img-top"
                  alt="..."
                  @click="goProduct(data.pdId)"
                />
                <div class="card-body">
                  <h5 class="card-title" @click="goProduct(data.pdId)" >상품명 : {{ data.pdName }}</h5>
                  <p class="card-text">상품 가격 : {{ data.pdPrice }}</p>
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
import MyWishListService from "@/services/mypage/MyWishListService.js";

export default {
  components: {
    MyPageMainMenu, // 메인 메뉴 컴포넌트 포함
  },

  data() {
    return {
      wishlist: [],  // 위시리스트 아이템을 저장할 배열

    };
  },

  methods: {
    async retrieveWishList() {
      let userId = this.$store.state.user.userId; // 사용자 ID 가져오기
      try {
        let response = await MyWishListService.getAll(userId);
        this.wishlist = response.data;  // 받아온 데이터를 wishlist 배열에 할당
      } catch (e) {
        console.error("위시리스트 조회 실패:", e);
      }
    }
  },

    // 위시리스트 아이템을 삭제하는 함수
    async deleteProduct(pdId) {
      try {
        await MyWishListService.delete(pdId);  // pdId를 이용해 상품 삭제
        this.retrieveWishList();  // 삭제 후 위시리스트 재조회
      } catch (e) {
        console.error("위시리스트 아이템 삭제 실패:", e);
      }
    },

    goProduct(pdId) {
      // 클릭시 상품으로 이동하는 함수
      // URL이 정해지면 아래 코드를 업데이트
      this.$router.push(`/product/${pdId}`);
      console.log("Navigate to product details with pdId:", pdId);
    },


    mounted() {
    this.retrieveWishList(this.$store.state.user.userId);
  },

};
</script>
<style>
@import "@/assets/css/mypage.css";
@import "@/assets/css/mypagewish.css";
</style>
