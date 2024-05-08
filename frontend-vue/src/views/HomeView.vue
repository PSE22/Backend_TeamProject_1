<template>
  <!-- 이벤트 슬라이드 -->
  <div>
    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="true">
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"></button>
      </div>
      <div class="carousel-inner">
        <div class="carousel-item active" v-for="(data, index) in event" :key="index">
          <img :src="data.eventThumbnail" class="d-block mx-auto" alt="" />
        </div>
        <!-- <div class="carousel-item">
          <img src="https://via.placeholder.com/1280x400?text=Event+Image+2" class="d-block w-100" alt="" />
        </div>
        <div class="carousel-item">
          <img src="https://via.placeholder.com/1280x400?text=Event+Image+3" class="d-block w-100" alt="" />
        </div> -->
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
        data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
        data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>

  <!-- 추천 상품 제목-->
  <div class="row">
    <div class="col-md-6 text-center offset-md-3" id="main-product-title">
      🐭서울쥐 추천 상품🐭
    </div>
  </div>
  <!-- 추천 상품 카드 -->
  <!-- row-cols-md-3 : 한줄에 보일 카드 개수(3) -->
  <div class="row row-cols-1 row-cols-md-3 g-4" id="main-products">
    <div class="col" v-for="(data, index) in product" :key="index" :value="data.pdId" @click="goProductDetail">
      <div class="card h-100">
        <img :src="data.pdThumbnail" class="card-img-top img-fluid" id="main-product-image" alt="..." />
        <div class="card-body">
          <p class="card-product-title">{{ data.pdName }}</p>
          <p class="card-product-price">{{ data.pdPrice }}원</p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import EventService from '@/services/shop/EventService';
import ProductService from '@/services/shop/ProductService';

export default {
  data() {
    return {
      event: [],
      product: []
    }
  },
  methods: {
    // 전체 이벤트 가져오기
    async getEvent() {
      try {
        let response = await EventService.getAll();
        this.event = response.data;
        console.log("전체 이벤트 : ", response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 전체 상품 가져오기
    async getProduct() {
      try {
        let response = await ProductService.getAllProduct();
        this.product = response.data;
        console.log("전체 상품 : ", response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 상품 상세 페이지로 이동
    // goProductDetail(pdId) {
    //   this.$router.push(`/product/${pdId}`);
    // }
  },
  mounted() {
    this.getEvent();
    this.getProduct();
  },
};
</script>
<style>
  /* 이벤트 슬라이드 양쪽 화살표 아이콘 */
  .carousel-control-prev-icon, .carousel-control-next-icon {
    background-color: skyblue;
    border-radius: 5px;
  }
  /* 상품 목록 타이틀 */
  #main-product-title {
    font-family: 'YClover-Regular';
    font-size: 30px;
    margin-top: 100px;
  }
  /* 상품 이미지 */
  #main-product-image {
    width: 300px;
    height: 300px;
  }
  /* 상품 가격 */
  .card-product-price {
    font-size: 30px;
  }
</style>
