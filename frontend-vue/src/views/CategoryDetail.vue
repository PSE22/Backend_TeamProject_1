<template>
  <!-- 카테고리 제목 -->
  <div class="row">
    <div class="col-md-6 text-center offset-md-3" id="category-title">
      {{ categoryCode }}
    </div>
  </div>

  <!-- 카테고리 상품 페이지 : 정렬 버튼 -->
  <div class="main-nav-list col" id="sorting">
    <button id="button">신상품순</button> |
    <button id="button">판매량순</button> |
    <button id="button">낮은 가격순</button> |
    <button id="button">높은 가격순</button>
  </div>

  <!-- 해당 카테고리 상품 : card  -->
  <div class="row row-cols-1 row-cols-md-4" id="main-products">
    <div class="col mt-4 mb-4 product-card-container" v-for="(data, index) in product" :key="index">
      <div class="card h-100" type="button" @click="goProductDetail(data.pdId)">
        <img :src="data.pdThumbnail" class="card-img-top img-fluid" id="main-product-image" alt="..." />
        <div class="card-body">
          <p class="card-product-title">{{ data.pdName }}</p>
          <p class="card-product-price">{{ data.pdPrice }}원</p>
        </div>
      </div>
    </div>
  </div>

  <b-pagination class="col-12 mb-3 justify-content-center" v-model="page" :total-rows="count" :per-page="pageSize"
    @click="retrieveProduct"></b-pagination>

</template>
<script>
import ProductService from "@/services/shop/ProductService";

export default {
  data() {
    return {
      product: [],
      categoryCode: this.$route.params.categoryCode,

      page: 1,      // 현재 페이지 번호
      count: 0,     // 전체 데이터 개수
      pageSize: 12,  // 화면에 보여질 데이터 개수

      pageSizes: [3, 6, 9], // 한번에 보여질 데이터 개수 배열

      show: true,
    };
  },
  methods: {
    // 카테고리 상품 전체 가져오기
    async retrieveProduct(categoryCode) {
      try {
        console.log("categoryCode: ", categoryCode);
        let response = await ProductService.getAll(
          categoryCode,
          this.page - 1,
          this.pageSize
        );
        console.log("response: ", response);
        const { product, totalItems } = response.data;
        this.product = product;
        this.count = totalItems;
        console.log("product", this.product);
      } catch (e) {
        console.log("에러", e);
      }
    },

    // 상품 상세 페이지로 이동
    goProductDetail(pdId) {
      this.$router.push(`/product/${pdId}`);
    },

    // 각 카테고리 코드에 카테고리명 저장
    codeToName() {
      switch (this.categoryCode) {
        case "CG01":
          this.categoryCode = "필기류/필통";
          break;
        case "CG02":
          this.categoryCode = "다이어리/플래너";
          break;
        case "CG03":
          this.categoryCode = "노트/메모지";
          break;
        case "CG04":
          this.categoryCode = "사무용품";
          break;
      }
    },

    // 공통 페이징 함수 : select 태그에 바인딩
    pageSizeChange() {
      this.page = 1;            // 현재 페이지 번호
      this.retrieveProduct();   // 재조회
    },
  },

  //화면이 뜰 때 자동 실행 함수
  mounted() {
    this.retrieveProduct(this.$route.params.categoryCode);
    this.codeToName();
  },
};
</script>
<style>
@import "@/assets/css/category.css";
</style>
