<template>
  <div class="mypage-layout">
    <MyPageMainMenu />
    <div class="mypage-contents">
      <div class="mypage-backcolor">
        <div class="mypage-subtitle">적립금</div>
        <div class="pointIntro">
          <dl class="usablePoint">
            <dt>사용가능 적립금</dt>
            <dd>
              <strong>{{ pointBalance }}</strong> P
            </dd>
          </dl>
        </div>
        <div class="mypage-filter" role="group" aria-label="Basic example">
          <div class="dateSelect">
            <button type="button" class="btn btn-primary">1 개월</button>
            <button type="button" class="btn btn-primary">3 개월</button>
            <button type="button" class="btn btn-primary">6 개월</button>
          </div>
          <p class="pointDate">24.03.19 ~ 24.04.19</p>
        </div>

        <div class="pointWrap">
          <ul class="pointList">
            <li class="pointItem" v-for="(item, index) in point" :key="index">
              <p class="date">{{ item.actionType === '만료' ? item.delDate : item.addDate }}</p>
              <dl class="pointInfo">
                <dt>{{ item.actionType === '만료' ? '만료' : (item.pointAdd ? '적립 (' + item.pointCode + ')' : '사용') }}</dt>
                <dd>{{ item.actionType === '만료' ? '만료금액' : (item.pointAdd ? '적립금액' : '사용금액') }}</dd>
              </dl>
              <div :class="{ 'plus': item.pointAdd, 'minus': item.usePointPrice }">
                <strong>
          {{ item.actionType === '적립' ? `+${item.pointAdd}` : 
            (item.actionType === '사용' ? `-${item.usePointPrice}` : 
            (item.actionType === '만료' ? `-${item.pointAdd}` : '')) }}P
        </strong>
              </div>
            </li>
          </ul>
          <div class="mypageNotice">
            <h3 class="title" style="margin-left: 20px;">적립/사용 유의사항</h3>
            <ul class="content">
              <li>적립금 내용은 서비스 정책에 따라 달라질 수 있습니다.</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
// import dayjs from "dayjs";
import MyPageMainMenu from "@/components/mypage/MyPageMainMenu.vue";
import MyPointService from "@/services/mypage/MyPointService";
export default {
  components: {
    MyPageMainMenu,
  },



  data() {
    return {
      point: [],      // 적립금 상세 목록
      pointBalance: 0, // 사용가능한 적립금 잔액
    };
  },

  methods: {
    // 적립금 정보 조회
    async retrievePoint() {
      try {
        const userId = this.$store.state.user.userId; // Vuex store에서 userId 가져오기
        const response = await MyPointService.getAll(userId);
        this.pointBalance = response.data.pointBalance; // 서버로부터 받은 적립금 잔액 정보 저장
        if (response && response.data) {
        this.point = response.data; // 데이터 할당 확인
      } else {
        console.error("적립금 정보가 비어 있습니다.");
      }
      console.log("받은 데이터:", response.data); // 데이터 로깅
    } catch (e) {
      console.error("적립금 정보 로딩 실패:", e);
    }
  }
  },

  mounted() {
    this.retrievePoint(this.userId);
  }
};
</script>
<style>
@import "@/assets/css/mypage.css";
@import "@/assets/css/mypagepoint.css";
</style>
