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
          <div v-for="(orderItems, orderId) in groupedOrders" :key="orderId" class="orderContainer">
            <div class="headTitle">
              <strong>{{ orderItems[0].addDate }}</strong>
              <div class="link">
                <router-link :to="`/order/${orderId}`"> 상세보기 </router-link>
              </div>
            </div>
            <div class="orderStepBox">{{ orderItems[0].orderCode }}</div> <!-- 주문 상태 한 번만 출력 -->
            <div class="productRow">
              <div v-for="item in orderItems" :key="item.id" class="orderItemBoxWrap">
                <router-link to="/" class="thumb">
                  <img :src="item.pdThumbnail" class="thumbImage" />
                </router-link>
                <div class="orderItemInfo">
                  <div class="titleWrap"><span class="title">{{ item.pdName }}</span></div>
                  <div class="priceWrap">
                    <span class="price"><strong>{{ item.orderDetailPrice }}</strong>원</span>
                    <span class="count">수량 <strong>{{ item.orderDetailCnt }}</strong></span>
                  </div>
                </div>
              </div>
            </div>
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
      orders: [], // 원본 데이터 배열
      groupedOrders: {}, // 그룹화된 주문 데이터
      userId: this.$store.state.userId,
    };
  },
  methods: {
    async retrieveOrderCheck(userId) {
      try {
        const response = await OrderCheck.getAll(userId);
        this.orders = response.data;
        this.groupOrders();
      } catch (e) {
        console.error("Error fetching orders:", e);
      }
    },
    groupOrders() {
      this.groupedOrders = this.orders.reduce((acc, order) => {
        if (!acc[order.orderId]) {
          acc[order.orderId] = [];
        }
        acc[order.orderId].push(order);
        return acc;
      }, {});
    }
  },
  mounted() {
    this.retrieveOrderCheck(this.userId);
  },
};

</script>
<style>
@import "@/assets/css/mypageorder.css";
@import "@/assets/css/mypage.css";
</style>
