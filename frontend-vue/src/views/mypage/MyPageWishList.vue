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
import MyWishListService from "@/services/mypage/MyWishListService.js";

export default {
  components: {
    MyPageMainMenu, // 메인 메뉴 컴포넌트 포함
  },

  data() {
    return {
      wishlist: [],  // 위시리스트 아이템을 저장할 배열
      loggedInUserId: null,  // 현재 로그인된 사용자의 ID
      loading: false,  // 데이터 로딩 상태 표시
      error: '',  // 에러 메시지를 저장할 변수
      currentPage: 1,  // 사용자가 현재 보고 있는 페이지 번호
      pageSize: 5,  // 한 페이지에 표시할 아이템 수
      totalItems: 0,  // 전체 아이템 수
    };
  },

  methods: {
    // 위시리스트 데이터를 서버로부터 불러오는 함수
    async retrieveWishList() {
      this.loading = true;  // 데이터 로딩 시작
      this.error = '';  // 에러 메시지 초기화
      try {
        const response = await MyWishListService.getAll(
          this.loggedInUserId, this.currentPage - 1, this.pageSize
        );

        this.wishlist = response.data.wishlist;  // 서버로부터 받은 위시리스트 데이터
        this.totalItems = response.data.totalItems;  // 서버로부터 받은 전체 아이템 수
        this.loading = false;  // 데이터 로딩 완료
      } catch (e) {
        console.error("위시리스트 전체조회 실패", e);
        this.error = '조회를 실패했습니다.';  // 에러 발생 시 메시지 설정
        this.loading = false;  // 에러 발생 시 로딩 상태 해제
      }
    },

    // 위시리스트 아이템을 삭제하는 함수
    async deleteProduct(pdId) {
      try {
        await MyWishListService.delete(
          this.loggedInUserId, pdId  // 특정 아이템 삭제 요청
        );  
        this.retrieveWishList();  // 삭제 후 위시리스트 다시 불러오기
      } catch (e) {
        console.error("삭제 실패:", e);
        this.error = '상품을 삭제하지 못했습니다';  // 삭제 실패 시 에러 메시지 설정
      }
    },

    // 페이지 크기 변경 처리 함수
    pageSizeChange(newSize) {
      this.pageSize = newSize;  // 새로운 페이지 크기 설정
      this.retrieveWishList();  // 페이지 크기 변경 후 위시리스트 다시 불러오기
    },
  },

  mounted() {
    this.loggedInUserId = localStorage.getItem('userId');  // 컴포넌트 마운트 시 로컬 스토리지에서 사용자 ID 불러오기
    this.retrieveWishList();  // 컴포넌트 마운트 완료 후 위시리스트 로딩
  },
};
</script>
<style>
@import "@/assets/css/mypage.css";
@import "@/assets/css/mypagewish.css";
</style>
