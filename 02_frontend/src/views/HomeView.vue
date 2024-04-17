<template>
  <!-- 이벤트 슬라이드 -->
  <div>
    <div
      id="carouselExampleIndicators"
      class="carousel slide"
      data-bs-ride="true"
    >
      <div class="carousel-indicators">
        <button
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide-to="0"
          class="active"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide-to="1"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide-to="2"
        ></button>
      </div>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img
            src="https://via.placeholder.com/1280x400?text=Event+Image+1"
            class="d-block w-100"
            alt=""
          />
        </div>
        <div class="carousel-item">
          <img
            src="https://via.placeholder.com/1280x400?text=Event+Image+2"
            class="d-block w-100"
            alt=""
          />
        </div>
        <div class="carousel-item">
          <img
            src="https://via.placeholder.com/1280x400?text=Event+Image+3"
            class="d-block w-100"
            alt=""
          />
        </div>
      </div>
      <button
        class="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide="prev"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button
        class="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide="next"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>

  <!-- 추천 상품 제목-->
  <div class="row ">
    <div class="col-md-6 text-center offset-md-3" id="main-product-title"> 이달의 추천 상품 </div>
  </div>

  <!-- 추천 상품 카드 -->
  <!-- row-cols-md-3 : 한줄에 보일 카드 개수(3) -->
  <div class="row row-cols-1 row-cols-md-3 g-4" id="main-products">
  <div class="col">
    <div class="card h-100">
      <img src="https://via.placeholder.com/400x400?text=Image" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">Card title</h5>
        <p class="card-text">상품 설명</p>
      </div>
    </div>
  </div>
  <div class="col">
    <div class="card h-100">
      <img src="https://via.placeholder.com/400x400?text=Image" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">Card title</h5>
        <p class="card-text">상품 설명</p>
      </div>
    </div>
  </div>
  <div class="col">
    <div class="card h-100">
      <img src="https://via.placeholder.com/400x400?text=Image" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">Card title</h5>
        <p class="card-text">상품 설명</p>
      </div>
    </div>
  </div>
  <div class="col">
    <div class="card h-100">
      <img src="https://via.placeholder.com/400x400?text=Image" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">Card title</h5>
        <p class="card-text">상품 설명</p>
      </div>
    </div>
  </div>

</div>

</template>
<script>
export default {};
</script>
<style>
</style>
