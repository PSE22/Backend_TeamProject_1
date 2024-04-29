<template>
  <!-- 카테고리 제목 -->
  <div class="row">
    <div class="col-md-6 text-center offset-md-3" id="category-title">
      카테고리명
    </div>
  </div>

  <!-- 카테고리 상품 페이지 : 정렬 버튼 -->
  <div class="main-nav-list col align-self-end" id="sorting">
    <button id="button">신상품순</button> |
    <button id="button">판매량순</button> |
    <button id="button">낮은 가격순</button> |
    <button id="button">높은 가격순</button>
  </div>

  <!-- 해당 카테고리 상품 : card  -->
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

  <!-- {/* paging 시작 */} -->
  <!-- 1페이지당 화면에 보일 개수 조정 -->
  <div class="col-12 w-25 mb-3">
    1페이지당 화면에 보일 개수
    <select
      class="form-select form-select-sm"
      v-model="pageSize"
      @change="pageSizeChange"
    >
      <!-- TODO: vue 반복문 실행 -->
      <option v-for="(data, index) in pageSizes" :key="index" :value="data">
        {{ data }}
      </option>
    </select>
  </div>

  <b-pagination
    class="col-12 mb-3"
    v-model="page"
    :total-rows="count"
    :per-page="pageSize"
    @click="retrieveProduct"
  ></b-pagination>
  <!-- {/* paging 끝 */} -->

</template>
<script>
import ProductService from "@/services/shop/ProductService";

export default {
  data() {
    return {
      product: [],
      categoryCode: "",

      page: 1,      // 현재 페이지 번호
      count: 0,     // 전체 데이터 개수
      pageSize: 4,  // 화면에 보여질 데이터 개수

      pageSizes: [3, 6, 9], // 한번에 보여질 데이터 개수 배열

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
        console.log(response.data);
      } catch (e) {
        console.log(e);
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
  },
};
</script>
<style>
@import "@/assets/css/category.css";
</style>
