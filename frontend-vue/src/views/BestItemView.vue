<template>
  <!-- 베스트 상품 페이지 : 배너 -->
  <div class="row">
    <div class="container-fluid banner-container">
      <h1 class="banner-title">Best Item</h1>
    </div>
  </div>

  <!-- 베스트 상품 페이지 : 정렬 버튼 -->
  <div class="main-nav-list col align-self-end" id="sorting">
    <button id="button">신상품순</button> |
    <button id="button">판매량순</button> |
    <button id="button">낮은 가격순</button> |
    <button id="button">높은 가격순</button>
  </div>

  <!-- 베스트 상품 페이지 : card  -->
  <div class="row row-cols-1 row-cols-md-4 g-4" id="category-products">
    <div v-for="(data, index) in product" :key="index" class="col">
      <div class="card h-100">
        <div class="product-image">
          <img :src="data.pdImgUrl" class="card-img-top" />
        </div>
        <!-- 하트 이미지 : 위시 리스트 등록 -->
        <div class="heart-icon-white" @click="toggleShow">
          <!-- 빈 하트 이미지 : 위시 리스트 등록 전 -->
          <img
            v-if="show"
            src="@/assets/img/free-icon-font-circle-heart-9272486.png"
          />
          <!-- 검정 하트 이미지 : 위시 리스트 등록 후 -->
          <img
            v-else
            src="@/assets/img/free-icon-font-circle-heart-9270879.png"
          />
        </div>
        <div class="card-body">
          <h5 class="card-title">{{ data.pdName }}</h5>
          <span>{{ data.pdPrice }}</span>
        </div>
      </div>
    </div>
  </div>

  <!-- b-pagination 페이징 -->
  <div class="product-paging">
    <b-pagination
      class="col-12 mb-3"
      v-model="page"
      :total-rows="count"
      :per-page="pageSize"    
      @click="retrieveProduct"
    ></b-pagination>
  </div>

</template>
<script>
import ProductService from "@/services/shop/ProductService";

export default {
  data() {
    return {
      product: [],
      categoryCode: "",

      page: 1, // 현재 페이지 번호
      count: 0, // 전체 데이터 개수
      pageSize: 4, // 화면에 보여질 데이터 개수

      show: true,
    };
  },
  methods: {
    toggleShow() {
      this.show = !this.show;
    },

    async retrieveProduct() {
      try {
        // 공통 전체조회 서비스 함수
        let response = await ProductService.getAll(
          this.categoryCode,
          this.page - 1,
          this.pageSize
        );
        const { product, totalItems } = response.data;
        this.product = product;
        this.count = totalItems;
        console.log(response.data); // 웹 브라우저 콘솔 탭에 spring 전달 객체 배열이 표시됨
      } catch (e) {
        console.log(e); // 웹 브라우저 콘솔 탭에 에러메세지 표시됨
      }
    },
  },
  mounted() {
    this.retrieveProduct();
  },
};
</script>
<style>
@import "@/assets/css/category.css";
</style>
