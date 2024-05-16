<template>
    <div class="mypage-layout">
      <MyPageMainMenu />
      <div class="mypage-contents">
        <div class="mypage-backcolor">
          <div class="mypage-subtitle">적립금</div>
          <div class="pointIntro">
            <dl class="usablePoint">
              <dt>사용가능 적립금</dt>
              <dd><strong>{{ pointBalance.resultPoint }}</strong> P</dd>
            </dl>
          </div>
          <div class="mypagepoint-filter" role="group" aria-label="Basic example">
            <div class="dateSelect">
              <button type="button" class="btn btn-primary" @click="retrievePointsForPeriod(1)">1 개월</button>
              <button type="button" class="btn btn-primary" @click="retrievePointsForPeriod(3)">3 개월</button>
              <button type="button" class="btn btn-primary" @click="retrievePointsForPeriod(6)">6 개월</button>
            </div>
            <p class="pointDate">기간: {{ displayPeriod }}</p>
          </div>
          <ul class="pointList">
            <li class="pointItem" v-for="(item, index) in points" :key="index">
              <p class="date">{{ item.actionType === '만료' ? item.delDate : item.addDate }}</p>
              <dl class="pointInfo">
                <dt>{{ item.actionType === '만료' ? '만료' : (item.pointAdd ? '적립 (' + item.pointCode + ')' : '사용') }}</dt>
                <dd>{{ item.actionType === '만료' ? '만료금액' : (item.pointAdd ? '적립금액' : '사용금액') }}</dd>
              </dl>
              <div :class="{ 'plus': item.pointAdd, 'minus': item.usePointPrice }">
                <strong>{{ item.actionType === '적립' ? `+${item.pointAdd}` : (item.actionType === '사용' ? `-${item.usePointPrice}` : (item.actionType === '만료' ? `-${item.pointAdd}` : '')) }}P</strong>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import dayjs from 'dayjs';
  import MyPageMainMenu from "@/components/mypage/MyPageMainMenu.vue";
  import MyPointService from "@/services/mypage/MyPointService";
  
  export default {
    components: {
      MyPageMainMenu
    },
    data() {
      return {
        points: [],
        displayPeriod: '',
        pointBalance: 0
      };
    },
    methods: {
      async retrievePointsForPeriod(months) {
        const userId = this.$store.state.user.userId;
        const endDate = dayjs().format('YYYY-MM-DD');
        const startDate = dayjs().subtract(months, 'month').format('YYYY-MM-DD');
        this.displayPeriod = `${startDate} ~ ${endDate}`;
        
  
        try {
          const response = await MyPointService.getPointsForPeriod(userId, startDate, endDate);
          this.points = response.data; 
          console.log("Loaded data:", this.points); 
        } catch (e) {
          console.error("적립금 정보 로딩 실패:", e);
        }
      },
      
      async retrievePointBalance() {
       let response = await MyPointService.getPointBalance(this.$store.state.user.userId);
       this.pointBalance = response.data
      }
    },
    mounted() {
      this.retrievePointsForPeriod(1); // 초기 로딩 시 기본적으로 1개월치 데이터 로드
      this.retrievePointBalance();
    }
  };
  </script>
  
  <style>
  @import "@/assets/css/mypage.css";
  @import "@/assets/css/mypagepoint.css";
  </style>