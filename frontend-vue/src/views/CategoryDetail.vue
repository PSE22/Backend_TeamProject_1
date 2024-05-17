<template>
  <!-- 카테고리 제목 -->
  <div class="row">
    <div class="col-md-6 text-center offset-md-3" id="category-title">
      {{ categoryCode }}
    </div>
  </div>

  <!-- 카테고리 상품 페이지 : 정렬 버튼 -->
  <div class="main-nav-list col" id="sorting">
    <button id="button" @click="retrieveNewItem">신상품순</button> |
    <button id="button" @click="retrieveBestItem">인기순</button> |
    <button id="button" @click="retrieveLowPrice">낮은 가격순</button> |
    <button id="button" @click="retrieveHighPrice">높은 가격순</button>
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

  <b-pagination v-if="flag === 0"
    class="col-12 mb-3 justify-content-center" 
    v-model="page" 
    :total-rows="count" 
    :per-page="pageSize"
    @click="retrieveProduct">
  </b-pagination>

  <b-pagination v-if="flag === 1"
    class="col-12 mb-3 justify-content-center" 
    v-model="page" 
    :total-rows="count" 
    :per-page="pageSize"
    @click="retrieveNewItem">
  </b-pagination>

  <b-pagination v-if="flag === 2"
    class="col-12 mb-3 justify-content-center" 
    v-model="page" 
    :total-rows="count" 
    :per-page="pageSize"
    @click="retrieveLowPrice">
  </b-pagination>

  <b-pagination v-if="flag === 3"
    class="col-12 mb-3 justify-content-center" 
    v-model="page" 
    :total-rows="count" 
    :per-page="pageSize"
    @click="retrieveProduct">
  </b-pagination>

  <b-pagination v-if="flag === 4"
    class="col-12 mb-3 justify-content-center" 
    v-model="page" 
    :total-rows="count" 
    :per-page="pageSize"
    @click="retrieveHighPrice">
  </b-pagination>


</template>
<script>
import ProductService from "@/services/shop/ProductService";

export default {
  data() {
    return {
      flag: 0,      // 정렬 기준값
      product: [],
      categoryCode: this.$route.params.categoryCode,

      page: 1,      // 현재 페이지 번호
      count: 0,     // 전체 데이터 개수
      pageSize: 4,  // 화면에 보여질 데이터 개수
    };
  },
  methods: {
    // 카테고리 상품 전체 가져오기
    async retrieveProduct() {
      try {
        this.flag = 0;
        let response = await ProductService.getAll(
          this.$route.params.categoryCode,
          this.page - 1,
          this.pageSize
        );
        const { product, totalItems } = response.data;
        this.product = product;
        this.count = totalItems;
      } catch (e) {
        console.log("에러", e);
      }
    },

    // 카테고리별 신상품순 정렬
    async retrieveNewItem() {
      try {
        this.flag = 1;
        let response = await ProductService.getAllCategoryNew(
          this.$route.params.categoryCode,
          this.page - 1,
          this.pageSize
        );
        const { product, totalItems } = response.data;
        this.product = product;
        this.count = totalItems;
        console.log("신상품순 되었나요?");
      } catch (e) {
        console.log("에러", e);
      }
    },

    // 카테고리별 인기순 정렬
    async retrieveBestItem() {
      try {
        this.flag = 2;
        let response = await ProductService.getAllCategoryBest(
          this.$route.params.categoryCode,
          this.page - 1,
          this.pageSize
        );
        const { product, totalItems } = response.data;
        this.product = product;
        this.count = totalItems;
        console.log("인기순 되었나요?");
      } catch (e) {
        console.log("에러", e);
      }
    },

    // // 카테고리별 낮은 가격순 정렬
    async retrieveLowPrice() {
      try {
        this.flag = 3;
        let response = await ProductService.getAllCategoryLowPrice(
          this.$route.params.categoryCode,
          this.page - 1,
          this.pageSize
        );
        const { product, totalItems } = response.data;
        this.product = product;
        this.count = totalItems;
        console.log("낮은 가격순 되었나요?");
      } catch (e) {
        console.log("에러", e);
      }
    },

    // // 카테고리별 높은 가격순 정렬
    async retrieveHighPrice() {
      try {
        this.flag = 4;
        let response = await ProductService.getAllCategoryHighPrice(
          this.$route.params.categoryCode,
          this.page - 1,
          this.pageSize
        );
        const { product, totalItems } = response.data;
        this.product = product;
        this.count = totalItems;
        console.log("높은 가격순 되었나요?");
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
    this.retrieveProduct();
    this.codeToName();
  },
};
</script>
<style>
@import "@/assets/css/category.css";
</style>
