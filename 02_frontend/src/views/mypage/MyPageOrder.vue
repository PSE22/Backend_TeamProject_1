<template>
  <div>
    <MyPageMainMenu />
    <div class="row">
      <div class="col">
        <div class="mypageOrderMain">
          <div class="mypageSubTitle">주문/배송 조회</div>
          <div class="dateSelect" role="group" aria-label="Basic example">
            <button type="button" class="btn btn-primary">1 개월</button>
            <button type="button" class="btn btn-primary">3 개월</button>
            <button type="button" class="btn btn-primary">6 개월</button>
          </div>
          <div class="orderContainer">
            <div class="headTitle">
              <!-- {{ data.orderDate }} -->
              <strong>2024-04-17</strong>
              <div class="link">
                <!-- <router-link v-vind:to="/order-detail" class="router-link">| 상세보기 ></router-link>  -> v-vind:to 사용해야 링크에 변수 사용가능-->
                <router-link to="/order-detail" class="router-link"
                  >| 상세보기 ></router-link
                >
              </div>
            </div>

            <article class="productRow">
              <div class="orderStepBox">배송완료</div>
              <div class="productItems">
                <div class="orderItemBoxWrap">
                  <div class="orderItemBox">
                    <router-link to="/" class="thumb">
                      <span class="thumbImage">
                        <img alt="샘플" src="@/assets/logo.png" />
                      </span>
                    </router-link>
                    <div class="orderItemInfo">
                      <div class="titleWrap">
                        <span class="title"> 테스트 </span>
                      </div>
                      <div class="priceWrap">
                        <span class="price"><strong> 0000 </strong>원</span>
                        <span class="count">수량 <strong>0개</strong></span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </article>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import MyPageMainMenu from "@/components/mypage/MyPageMainMenu.vue";
export default {
  components: {
    MyPageMainMenu,
  },
};
</script>
<style>
@import "@/assets/css/mypageorder.css";
@import "@/assets/css/mypage.css";
</style>
