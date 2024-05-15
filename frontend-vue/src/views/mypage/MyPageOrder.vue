<template>
  <div>
    <MyPageMainMenu />
    <div class="row">
      <div class="col">
        <div class="mypageOrderMain">
          <div class="mypageSubTitle">주문/배송 조회</div>
          <div class="mypage-filter" role="group" aria-label="Basic example">
            <div class="dateSelect" role="group" aria-label="Basic example">
              <button type="button" class="btn btn-primary" @click="retrieveOrdersByPeriod(1)">1 개월</button>
              <button type="button" class="btn btn-primary" @click="retrieveOrdersByPeriod(3)">3 개월</button>
              <button type="button" class="btn btn-primary" @click="retrieveOrdersByPeriod(6)">6 개월</button>
            </div>
          </div>
          <div v-for="(orderItems, orderId) in groupedOrders" :key="orderId" class="orderContainer">
            <div class="headTitle">
              <strong>{{ (orderItems[0].addDate) }}</strong>
              <div class="link">
                <router-link :to="`/mypage/order/${orderId}`"> 상세보기 </router-link>
              </div>
            </div>
            <div class="orderStepBox">{{ orderItems[0].cmCdName }}</div> <!-- 주문 상태 한 번만 출력 -->
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
import dayjs from 'dayjs'; // dayjs import

export default {
  components: {
    MyPageMainMenu,
  },
  data() {
    return {
      orders: [], // 원본 데이터 배열
      groupedOrders: [], // 그룹화된 주문 데이터 (배열 형태로 변경)
      userId: this.$store.state.user.userId,
      displayPeriod: '', // 선택한 기간 표시
    };
  },
  methods: {
    async retrieveOrdersByPeriod(months) {
      const endDate = dayjs().format('YYYY-MM-DD HH:mm:ss');
      const startDate = dayjs().subtract(months, 'month').format('YYYY-MM-DD HH:mm:ss');
      this.displayPeriod = `${startDate} ~ ${endDate}`;
      
      try {
        const response = await OrderCheck.getAllByDateRange(this.userId, startDate, endDate);
        this.orders = response.data;
        this.groupOrders();
        console.log("Loaded orders:", this.orders);
      } catch (e) {
        console.error("Error fetching orders for period:", e);
      }
    },
    formatDate(date) {
      return dayjs(date).format('YYYY-MM-DD HH:mm:ss');
    },
    groupOrders() {
      // orderId별로 그룹화
      const grouped = this.orders.reduce((acc, order) => {
        if (!acc[order.orderId]) {
          acc[order.orderId] = [];
        }
        acc[order.orderId].push(order);
        return acc;
      }, {});

      // 그룹을 addDate 기준으로 정렬
      this.groupedOrders = Object.values(grouped).sort((a, b) => {
        const dateA = new Date(a[0].addDate);
        const dateB = new Date(b[0].addDate);
        return dateB - dateA; // 최신 날짜가 먼저 오도록 내림차순 정렬
      });

      console.log('Final groupedOrders:', this.groupedOrders);
    }
  },
  mounted() {
    this.retrieveOrdersByPeriod(1); // 기본으로 1개월치 주문 데이터 로딩
  },
};
</script>

<style>
@import "@/assets/css/mypageorder.css";
@import "@/assets/css/mypage.css";
</style>