<template>
  <div class="searchContent"><h3>검색 : {{ searchPdName }}</h3></div>
  <div class="row row-cols-1 row-cols-md-4 g-4" id="main-products">
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

  <div>
    <b-pagination
      class="col-12 mb-3 justify-content-center"
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
      searchPdName: this.$route.query.search,

      page: 1,
      count: 0,
      pageSize: 8,
    };
  },
  methods: {
    async retrieveProduct() {
      try {
        let response = await ProductService.getAllProductSearch(
          this.searchPdName,
          this.page - 1,
          this.pageSize
        );
        const { product, totalItems } = response.data;
        this.product = product;
        this.count = totalItems;
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
