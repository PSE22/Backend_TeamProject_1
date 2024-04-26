<template>
  <!-- 이벤트 페이지 배너 -->
  <div class="row">
    <div class="container-fluid banner-container">
      <h1 class="banner-title">Event</h1>
    </div>
  </div>

  <!-- 개별 이벤트 이미지 -->
  <div class="event-img" v-for="(data, index) in eventImg" :key="index">
    <!-- <img
      src="https://via.placeholder.com/1200x300?text=Image"
      class="img-fluid"
      alt="이벤트이미지"
    /> -->
    <img
      :src="data.eventImgUrl"
      class="img-fluid"
      alt="이벤트이미지"
    />
  </div>
</template>
<script>
import EventService from "@/services/shop/EventService";

export default {
  data() {
    return {
      eventImg: [],
    }
  },

  methods: {
      async retrieveEvent() {
        await EventService.getAll()
          .then((response) => {
            this.eventImg = response.data;
            console.log(response.data);
          })
          .catch((e) => {
            console.log(e);
          });
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
  margin: 50px 0px;
  text-align: center;
}
</style>
