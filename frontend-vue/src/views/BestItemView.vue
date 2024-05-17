<template>
  <!-- 베스트 상품 페이지 : 배너 -->
  <div class="row">
    <div class="container-fluid banner-container-best">
      <h1 class="banner-title">Best Item</h1>
    </div>
  </div>

  <!-- 베스트 상품 페이지 : 정렬 버튼 -->
  <div class="main-nav-list col" id="sorting">
    <button id="button" @click="retrieveProductDay">일간 베스트</button> |
    <button id="button" @click="retrieveProductMonth">월간 베스트</button> |
    <button id="button" @click="retrieveProductYear">연간 베스트</button>
  </div>

  <!-- 베스트 3 -->
  <div class="row row-cols-1 row-cols-md-3 g-4" id="category-products">
    <div v-for="(data, index) in product3" :key="index" class="col">
      <div class="text-center">
      <img src="@/assets/img/free-icon-crown-1141782.png"> Best {{ index + 1 }}
      </div>
      <div class="card h-100" type="button" @click="goProductDetail(data.pdId)">
        <div class="product-image">
          <img :src="data.pdThumbnail" class="card-img-top" />
        </div>
        <div class="card-body">
          <h5 class="card-title">{{ data.pdName }}</h5>
          <span>{{ data.pdPrice }}</span>
        </div>
      </div>
    </div>
  </div>
  <!-- 베스트 상품 페이지 : card  -->
  <div class="row row-cols-1 row-cols-md-4 g-4" id="category-products">
    <div v-for="(data, index) in product" :key="index" class="col">
      <div class="card h-100" type="button" @click="goProductDetail(data.pdId)">
        <div class="product-image">
          <img :src="data.pdThumbnail" class="card-img-top" />
        </div>
        <div class="card-body">
          <h5 class="card-title">{{ data.pdName }}</h5>
          <span>{{ data.pdPrice }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProductService from "@/services/shop/ProductService";

export default {
  data() {
    return {
      product3: [],
      product: [],
    };
  },
  methods: {
    async retrieveProduct3() {
      try {
        let response = await ProductService.getThreeBestProduct();
        this.product3 = response.data;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async retrieveProductDay() {
      try {
        let response = await ProductService.getAllBestProductDay();
        this.product = response.data;
        this.product.shift(); // 0번 인덱스 자르기
        this.product.shift(); // 1번 인덱스 자르기
        this.product.shift(); // 2번 인덱스 자르기
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async retrieveProductMonth() {
      try {
        let response = await ProductService.getAllBestProductMonth();
        this.product = response.data;
        this.product.shift(); // 0번 인덱스 자르기
        this.product.shift(); // 1번 인덱스 자르기
        this.product.shift(); // 2번 인덱스 자르기
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async retrieveProductYear() {
      try {
        let response = await ProductService.getAllBestProductYear();
        this.product = response.data;
        this.product.shift(); // 0번 인덱스 자르기
        this.product.shift(); // 1번 인덱스 자르기
        this.product.shift(); // 2번 인덱스 자르기
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    goProductDetail(pdId) {
      this.$router.push(`/product/${pdId}`);
    },
  },
  mounted() {
    this.retrieveProduct3();
    this.retrieveProductDay();
  },
};
</script>
<style>
@import "@/assets/css/category.css";
</style>
