<template>
    <!-- 카테고리 제목 -->
    <div class="row">
      <div class="col-md-6 text-center offset-md-3" id="category-title">
        카테고리명
      </div>
    </div>
    <div class="main-nav-list col align-self-end" id="sorting">
      <button id="button">신상품순</button> |
      <button id="button">판매량순</button> |
      <button id="button">낮은 가격순</button> |
      <button id="button">높은 가격순</button>
    </div>
    <!-- 해당 카테고리 상품 이미지 -->
    <!-- v-for="(data, index) in 배열" :key="index" -->
    <div class="row row-cols-1 row-cols-md-3 g-4" id="category-products">
      <div v-for="(data, index) in card" :key="index" class="col">
        <div class="card h-100">
          <div class="product-image">
            <!-- <img
              src="https://via.placeholder.com/400x400?text=Image"
              class="card-img-top"
              alt="..."
            /> -->
            <img :src="data.img" class="card-img-top">
          </div>
          <!-- 하트 이미지 : 위시 리스트 등록 -->
          <div class="heart-icon-white" @click="toggleShow">
            <!-- 빈 하트 이미지 : 위시 리스트 등록 전 -->
            <!-- <img v-if="show" src="@/assets/img/free-icon-font-circle-heart-9272486.png" /> -->
            <img v-if="show" :src="data.img1">
            <!-- 검정 하트 이미지 : 위시 리스트 등록 후 -->
            <!-- <img v-else src="@/assets/img/free-icon-font-circle-heart-9270879.png" /> -->
            <img v-else :src="data.img2">
          </div>
          <div class="card-body">
            <h5 class="card-title">Card title</h5>
            <p class="card-text">상품 설명</p>
          </div>
        </div>
      </div>
    </div>
  </template>
  <script>
  export default {
      data() {
          return {
              show: true,
              card: [
                  {
                      img: "https://via.placeholder.com/400x400?text=Image",
                      img1: require("@/assets/img/free-icon-font-circle-heart-9272486.png"),
                      img2: require("@/assets/img/free-icon-font-circle-heart-9270879.png")
                  },
                  {
                      img: "https://via.placeholder.com/400x400?text=Image",
                      img1: require("@/assets/img/free-icon-font-circle-heart-9272486.png"),
                      img2: require("@/assets/img/free-icon-font-circle-heart-9270879.png")
                  },
                  {
                      img: "https://via.placeholder.com/400x400?text=Image",
                      img1: require("@/assets/img/free-icon-font-circle-heart-9272486.png"),
                      img2: require("@/assets/img/free-icon-font-circle-heart-9270879.png")
                  },
                  {
                      img: "https://via.placeholder.com/400x400?text=Image",
                      img1: require("@/assets/img/free-icon-font-circle-heart-9272486.png"),
                      img2: require("@/assets/img/free-icon-font-circle-heart-9270879.png")
                  },
                  {
                      img: "https://via.placeholder.com/400x400?text=Image",
                      img1: require("@/assets/img/free-icon-font-circle-heart-9272486.png"),
                      img2: require("@/assets/img/free-icon-font-circle-heart-9270879.png")
                  },
                  {
                      img: "https://via.placeholder.com/400x400?text=Image",
                      img1: require("@/assets/img/free-icon-font-circle-heart-9272486.png"),
                      img2: require("@/assets/img/free-icon-font-circle-heart-9270879.png")
                  },
              ]
          }
      },
      methods: {
          toggleShow() {
              this.show = !this.show;
          },
          plusWish() {}
      },
  };
  </script>
  <style>
  @import "@/assets/css/category.css";
  </style>