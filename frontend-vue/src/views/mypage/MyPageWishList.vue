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
          <div class="row row-cols-1 row-cols-md-3 g-4" id="main-products"
              >
            <div class="col"
            v-for="(data, index) in wishlist" :key="index">
              <div class="card h-100" 
                >
                <img
                  :src="data.pdThumnail"
                  class="card-img-top"
                  alt="..."
                />
                <div class="card-body">
                  <h5 class="card-title">상품명 : {{ data.pdName }}</h5>
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
  <!-- 페이징 번호 -->
  <div class="row">
    <!-- TODO: 1페이지당 화면에 보일 개수 조정(select태그) -->
    <b-pagination
      class="col-12 mb-3"
      v-model="page"
      :total-rows="count"
      :per-page="pageSize"
      @click="retrieveWishList"
    ></b-pagination>
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

      page: 1, // 현재페이지번호
      count: 0, // 전체데이터개수
      pageSize: 3, // 1페이지당개수(select태그)

      pageSizes: [3, 6, 9], //1페이지당개수 배열(select태그-option)
    };
  },

  methods: {
    async retrieveWishList() {
      let userId = this.$store.state.user.userId;
      try {
        let response = await MyWishListService.getAll(userId, this.page - 1, this.pageSize);
        const { wishlist, totalItems } = response.data;
        this.wishlist = wishlist;
        this.count = totalItems;
      } catch (e) {
        console.error("위시리스트 조회 실패:", e);
      }
    },
  },



  pageNoChange(value) {
      // this.속성 => data() 안에 속성들 접근
      this.page = value; // 1) 현재페이지 변경
      this.retrieveWishList(); // 2) 재조회 요청
    },

    // 위시리스트 아이템을 삭제하는 함수
    async deleteProduct() {
      let response = await MyWishListService.delete(this.MyWishListService.pdId);
      // 로깅
      console.log(response.data);
      // TODO: 전체조회 페이지로 강제 이동
      this.$router.push("/mypage/wishlist");
    },
  
    // 페이지 크기 변경 처리 함수
    pageSizeChange(newSize) {
      this.pageSize = newSize;  // 새로운 페이지 크기 설정
      this.retrieveWishList();  // 페이지 크기 변경 후 위시리스트 다시 불러오기
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
