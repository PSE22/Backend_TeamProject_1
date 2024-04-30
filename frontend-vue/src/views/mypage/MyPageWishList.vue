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
                  :src="data.pdImgUrl"
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
      
      currentPage: 1,  // 사용자가 현재 보고 있는 페이지 번호
      page:0,
      pageSize: 5,  // 한 페이지에 표시할 아이템 수
      totalItems: 0,  // 전체 아이템 수
    };
  },

  methods: {
    async retrieveWishList() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await MyWishListService.getAll(
          this.page - 1, // 현재페이지번호-1
          this.pageSize // 1페이지당개수(size)
        );
        const { wishlist, totalItems } = response.data; // 사원배열(벡엔드 전송)
        this.wishlist = wishlist;          // 사원배열(벡엔드 전송)
        this.count = totalItems; // 전체페이지수(벡엔드 전송)
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
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
    this.retrieveWishList();  // 전체 조회 함수 실행
  }
};
</script>
<style>
@import "@/assets/css/mypage.css";
@import "@/assets/css/mypagewish.css";
</style>
