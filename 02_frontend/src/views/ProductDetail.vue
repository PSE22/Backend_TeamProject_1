<template>
  <div class="row row-cols-1 row-cols-md-2 g-4" id="main-products">
    <div class="col">
      <div class="card h-100">
        <img
          src="https://via.placeholder.com/400x400?text=Image"
          class="card-img-top"
          alt="..."
        />
        <div class="card-body">
          <h5 class="card-title">Card title</h5>
          <p class="card-text">상품 설명</p>
        </div>
      </div>
    </div>
    <div class="col">
      <ul class="product-select">
        <li><h2>상품명</h2></li>
        <br>
        <li>상품 설명 Lorem ipsum dolor sit amet consectetur adipisicing elit. Consectetur quisquam, numquam porro necessitatibus sit fugit</li>
        <br>
        <li><h2>가격 (원)</h2></li>
        <br><br>
        <li>옵션 선택</li>
        <br>
        <select name="option" class="select">
          <option value="1">상품 상세 옵션 선택 1</option>
          <option value="2">상품 상세 옵션 선택 2</option>
          <option value="3">상품 상세 옵션 선택 3</option>
          <option value="4">상품 상세 옵션 선택 4</option>
        </select>
        <br><br>
        <li>수량</li>
      </ul>
    </div>
  </div>
</template>
<script>
export default {};
</script>
<style>
@import "@/assets/css/productDetail.css";
</style>
