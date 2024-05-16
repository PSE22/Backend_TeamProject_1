<template>
  <!-- 신상품 페이지 : 배너 -->
  <div class="row">
    <div class="container-fluid banner-container-new">
      <h1 class="banner-title">New Item</h1>
    </div>
  </div>

  <!-- 신상품 페이지 : 정렬 버튼 -->
  <div class="main-nav-list col" id="sorting">
    <button id="button" @click="retrieveProduct">신상품순</button> |
    <button id="button" @click="retrieveProductLow">낮은 가격순</button> |
    <button id="button" @click="retrieveProductHigh">높은 가격순</button>
  </div>

  <!-- 신상품 페이지 : card  -->
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
      product: [],
    };
  },
  methods: {
    async retrieveProduct() {
      try {
        let response = await ProductService.getAllNewProduct();
        this.product = response.data;
        console.log( response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async retrieveProductHigh() {
      try {
        let response = await ProductService.getAllNewProductHigh();
        this.product = response.data;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async retrieveProductLow() {
      try {
        let response = await ProductService.getAllNewProductLow();
        this.product = response.data;
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
    this.retrieveProduct();
  },
};
</script>
<style>
@import "@/assets/css/category.css";
</style>
