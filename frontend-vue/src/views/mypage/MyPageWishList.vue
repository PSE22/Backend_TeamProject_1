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
              <button type="button"
              @click="saveWishList">선택담기</button>
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
import WishListService from "@/services/mypage/MyWishListService";
export default {
  components: {
    MyPageMainMenu,
  },

  data() {
    return {
      wishList: [], // 장바구니 객체배열
      // 공통 페이징 속성 정의
      page: 1, // 현재페이지번호
      count: 0, // 전체 데이터개수
      pageSize: 3, // 화면에 보여질 개수

      pageSizes: [3, 6, 9], // 화면에 보여질 개수배열
    };
  },

  methods: {
    // TODO: 전체조회(장바구니) 함수 : 검색어 버튼, 화면이뜰때 자동 실행
    async retrieveWishList() {
      try {
        // TODO: 공통 장바구니 전체 조회 서비스 함수 실행
        // TODO: 비동기 코딩 : async ~ await
        let response = await WishListService.getAll(
          this.page - 1,
          this.pageSize
        );
        const { wishList, totalItems } = response.data;
        this.wishList = wishList;
        this.count = totalItems;
        // 로깅
        console.log(response.data); // 웹브라우저 콘솔탭에 벡엔드 데이터 표시
      } catch (e) {
        console.log(e); // 웹브라우저 콘솔탭에 에러 표시
      }
    },

    // 저장함수
    async saveWishList(){
          try {
            // 임시 객체 변수
            let data = {
                pdId: this.wishList.pdId,
                pdName: this.wishList.pdName,
                pdPrice: this.wishList.pdPrice,

            }

            // 백엔드로 객체 추가 요청
            let response = await WishListService.creat(data);  // JSP에있는 DeptService 사용할때는 import 해줘야함
            // 콘솔에 결과 출력
            console.log(response);
            this.submitted = true; // 저장유무변수=true 변경

            } catch(e) {
              console.log(e);
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

  pageNoChange(value) {
      // this.속성 => data() 안에 속성들 접근
      this.page = value; // 1) 현재페이지 변경
      this.retrieveDept(); // 2) 재조회 요청
    },
};
</script>
<style>
@import "@/assets/css/mypage.css";
@import "@/assets/css/mypagewish.css";
</style>
