<template>
  <div>
    <MyPageMainMenu />
    <div class="row">
      <div class="col">
        <div class="mypageOrderMain">
          <div class="mypageSubTitle">주문/배송 조회</div>
          <div class="dateSelect" role="group" aria-label="Basic example">
            <button type="button" class="btn btn-primary">1 개월</button>
            <button type="button" class="btn btn-primary">3 개월</button>
            <button type="button" class="btn btn-primary">6 개월</button>
          </div>
          <div class="orderContainer">
            <div class="headTitle">
              <!-- {{ data.orderDate }} -->
              <strong>2024-04-17</strong>
              <div class="link">
                <!-- <router-link v-vind:to="/order-detail" class="router-link">| 상세보기 ></router-link>  -> v-vind:to 사용해야 링크에 변수 사용가능-->
                <router-link to="/order-detail" class="router-link"
                  >| 상세보기 ></router-link
                >
              </div>
            </div>

            <article class="productRow">
              <div class="orderStepBox">배송완료</div>
              <div class="productItems">
                <div class="orderItemBoxWrap">
                  <div class="orderItemBox">
                    <router-link to="/" class="thumb">
                      <span class="thumbImage">
                        <img alt="샘플" src="@/assets/logo.png" />
                      </span>
                    </router-link>
                    <div class="orderItemInfo">
                      <div class="titleWrap">
                        <span class="title"> 테스트 </span>
                      </div>
                      <div class="priceWrap">
                        <span class="price"><strong> 0000 </strong>원</span>
                        <span class="count">수량 <strong>0개</strong></span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </article>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import OrderCheck from "@/services/OrderCheck.js";
import MyPageMainMenu from "@/components/mypage/MyPageMainMenu.vue";
export default {
  components: {
    MyPageMainMenu,
  },
  data() {
    return {
      simpleProduct: [], // spring 보내줄 배열변수

      // 공통 페이징 속성
      page: 1, // 현재페이지번호
      count: 0, // 전체 데이터개수
      pageSize: 4, // 화면에 보여질 개수

      pageSizes: [3, 6, 9], // 화면에 보여질 개수배열
    };
  },

  methods: {
    // TODO: 전체조회 함수    : 검색어 버튼 태그
    async retrieveOrderList() {
      try {
        // TODO: 공통 전체조회 서비스 함수 실행
        // TODO: spring 통신 : 비동기 코딩 : async ~ await
        let response = await OrderCheck.getAll(
                                                    this.page - 1,
                                                    this.pageSize
                                                    );
        const { simpleProduct, totalItems } = response.data;
        this.simpleProduct = simpleProduct; // spring 전달 객체배열
        this.count         = totalItems;    // 전체페이지개수
        // 로깅(디버깅)
        console.log(response.data);         // 웹브라우저 콘솔탭에 spring 전달 객체배열이 표시됨
      } catch (e) {
        console.log(e); // 웹브라우저 콘솔탭에 에러메세지가 표시됨
      }
    },
    // TODO: 공통 페이징 함수 : select 태그
    pageSizeChange() {
      this.page = 1; // 현재페이지번호 : 1
      this.retrieveOrderList(); // 재조회
    },
  },
  //   TODO: 화면이 뜰때 자동 실행 함수
  mounted() {
    // TODO: 전체 조회 자동 실행
    this.retrieveOrderList();
  },
};

</script>
<style>
@import "@/assets/css/mypageorder.css";
@import "@/assets/css/mypage.css";
</style>
