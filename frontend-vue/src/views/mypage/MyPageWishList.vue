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
              <button type="button" @click="deleteProduct">선택삭제</button>
              <button type="button">선택담기</button>
            </div>
          </div>
          <div class="row row-cols-1 row-cols-md-3 g-4" id="main-products">
            <div class="col" v-for="(data, index) in wishlist" :key="index">
              <div class="card h-100">
                <img :src="data.pdThumbnail" 
                class="card-img-top" 
                alt="..." />
                <div class="card-body">
                  <h5 class="card-title">{{ data.pdName }}</h5>
                  <p class="card-text">가격 : {{ data.pdPrice }}</p>
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
    <!-- 페이징 컨트롤 -->
    <b-pagination
      v-model="page"
      :total-rows="count"
      :per-page="pageSize"
      @change="pageNoChange"
    >
    </b-pagination>
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
      wishlist: [], // 위시리스트 아이템을 저장할 배열
      userId: this.$store.state.user.userId,

      page: 1, // 현재페이지번호
      count: 0, // 전체데이터개수
      pageSize: 3, // 1페이지당개수(select태그)

      pageSizes: [3, 6, 9], //1페이지당개수 배열(select태그-option)
    };
  },

  methods: {
    // TODO: 전체조회 함수
    async retrieveWishList(userId) {
      try {
        let response = await MyWishListService.getAll(
          userId,
          this.page - 1,
          this.pageSize
        );
        const { wishlist, totalItems } = response.data; // 사원배열(벡엔드 전송)
        this.wishlist = wishlist; // 사원배열(벡엔드 전송)
        this.count = totalItems; // 전체페이지수(벡엔드 전송)

        console.log("응답 데이터:", response.data);
      } catch (e) {
        console.error("에러 발생:", e);
        console.error("상세 에러 내용:", JSON.stringify(e, null, 2));
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

  // 페이지 변경 처리
  pageNoChange(value) {
    this.page = value; // 현재 페이지 번호 업데이트
    this.retrieveWishList(this.userId); // 데이터 다시 불러오기
  },

  mounted() {
    this.retrieveWishList(this.userId);
  },
};
</script>
<style>
@import "@/assets/css/mypage.css";
@import "@/assets/css/mypagewish.css";
</style>
