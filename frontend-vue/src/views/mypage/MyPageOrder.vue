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
          <div class="orderContainer"
          v-for="(data, index) in ordercheck" :key="index">
            <div class="headTitle">
              <!-- {{ data.orderDate }} -->
              <strong>{{data.addDate}}</strong>
              <div class="link">
                <!-- <router-link v-vind:to="/order-detail" class="router-link">| 상세보기 ></router-link>  -> v-vind:to 사용해야 링크에 변수 사용가능-->
                <router-link :to="`/order/${data.orderId}`">
                  >| 상세보기 > 
                  </router-link
                >
              </div>
            </div>

            <article class="productRow">
              <div class="orderStepBox">{{data.orderCode}}</div>
              <div class="productItems">
                <div class="orderItemBoxWrap">
                  <div class="orderItemBox">
                    <router-link to="/" class="thumb">
                      <span class="thumbImage">
                        <img 
                        :src="data.pdThumbnail" />
                      </span>
                    </router-link>
                    <div class="orderItemInfo">
                      <div class="titleWrap">
                        <span class="title"> {{data.pdName}} </span>
                      </div>
                      <div class="priceWrap">
                        <span class="price"><strong> {{data.orderDetailPrice}} </strong>원</span>
                        <span class="count">수량 <strong>{{ data.orderDetailCnt }}</strong></span>
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
import OrderCheck from "@/services/mypage/MyOrderCheckService.js";
import MyPageMainMenu from "@/components/mypage/MyPageMainMenu.vue";
export default {
  components: {
    MyPageMainMenu,
  },

  data() {
    return {
      ordercheck: [], // spring 보내줄 배열변수

      userId: this.$store.state.userId,
    };
  },

  methods: {
    // TODO: 전체조회 함수    : 검색어 버튼 태그
    async retrieveOrderCheck(userId) {
      try {
        let response = await OrderCheck.getAll(userId);
        this.ordercheck = response.data;
        console.log(response.data); // 웹브라우저 콘솔탬에 백앤드 데이터 표시
      } catch (e) {
        console.log(e);
      }
    },
  },
  //   TODO: 화면이 뜰때 자동 실행 함수
  mounted() {
    // TODO: 전체 조회 자동 실행
    this.retrieveOrderCheck(this.$store.state.user.userId);
  },
};


</script>
<style>
@import "@/assets/css/mypageorder.css";
@import "@/assets/css/mypage.css";
</style>
