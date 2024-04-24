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
import WishListService from "@/services/mypage/MyWishListService.js";
export default {
  components: {
    MyPageMainMenu,
  },

  // 전체조회 바인딩
  data() {
    return {
      wishlist: [], // 장바구니 객체배열
      page: 1, // 현재페이지번호
      count: 0, // 전체 데이터개수
      pageSize: 3, // 화면에 보여질 개수

    };
  },

  methods: {
    // 전체조회 함수
    async retrieveWishList() {
      try {
        let response = await WishListService.getAll(
          this.page - 1,
          this.pageSize
        );
        const { wishlist, totalItems } = response.data;
        this.wishlist = wishlist;
        this.count = totalItems;
        // 로깅
        console.log(response.data); // 웹브라우저 콘솔탭에 벡엔드 데이터 표시
      } catch (e) {
        console.log(e); // 웹브라우저 콘솔탭에 에러 표시
      }
    },


    // 삭제함수
    async deleteProduct(pdId) {
      try {
        // TODO: 공통 장바구니 삭제 서비스 함수 실행
        let response = await WishListService.remove(pdId);
        //  로깅
        console.log(response.data);
        // alert 대화상자 출력
        alert("정상적으로 삭제되었습니다");
        // 삭제 후 재조회
        this.retrieveWishList();
       
      } catch (e) {
        console.log(e);
      }
    },
    // 클릭시 그 상품페이지로 이동
    goProduct() {
      this.$router.push("/");
    },

    // TODO: 공통 페이징 함수 : select 태그
    pageSizeChange() {
      this.page = 1; // 현재페이지번호 : 1
      this.retrieveWishList(); // 재조회
    },
  },

  //   TODO: 화면이 뜰때 자동 실행 함수
  mounted() {
    // TODO: 화면이 뜰때 전체조회 실행
    this.retrieveWishList();
  },
  
};
</script>
<style>
@import "@/assets/css/mypage.css";
@import "@/assets/css/mypagewish.css";
</style>
