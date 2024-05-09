<template>
  <!-- 이벤트 페이지 배너 -->
  <div class="row">
    <div class="container-fluid banner-container">
      <h1 class="banner-title">Event</h1>
    </div>
  </div>

  <!-- 개별 이벤트 이미지 -->
  <div class="event-img" v-for="(data, index) in event" :key="index" type="button" @click="goEventDetail(data.eventId)">
    <img :src="data.eventThumbnail" class="img-fluid" alt="이벤트이미지" />
  </div>
</template>
<script>
import EventService from "@/services/shop/EventService";

export default {
  data() {
    return {
      event: [],
    }
  },
  methods: {
    // 전체 이벤트 가져오기
    async retrieveEvent() {
      try {
        let response = await EventService.getAll();
        this.event = response.data;
        console.log("전체 이벤트 : ", response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 상세 이벤트 페이지로 이동
    goEventDetail(eventId) {
      this.$router.push(`/event/${eventId}`);
    }
  },
  mounted() {
    this.retrieveEvent();
  },
};
</script>
<style>
@import "@/assets/css/category.css";

.event-img {
  margin-bottom: 40px;
  text-align: center;
}
</style>
