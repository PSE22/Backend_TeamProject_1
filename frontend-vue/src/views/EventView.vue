<template>
  <!-- 이벤트 페이지 배너 -->
  <div class="row">
    <div class="container-fluid banner-container-event">
      <h1 class="banner-title">Event</h1>
    </div>
  </div>

  <!-- 이벤트 관리 버튼 -->
  <div class="row">
    <button v-if="isAdmin" type="button" class="btn btn-primary col-1 event-save-button" data-bs-toggle="modal"
      data-bs-target="#eventModal">등록</button>
    <div class="row" id="event-button">
      <form>
        <!-- Button trigger modal -->
        <div class="modal fade" id="eventModal" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
              <div class="modal-header">
                <h1 class="modal-title fs-5" id="modalLabel">
                  이벤트 등록
                </h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <label>이벤트 제목</label>
                <input class="form-control mb-3" v-model="eventTitle" placeholder="이벤트 제목을 입력하세요" />
                <label>이벤트 내용</label>
                <textarea rows="10" class="form-control mb-3" v-model="eventContent"
                  placeholder="이벤트 내용을 입력하세요"></textarea>
                <label>썸네일 이미지</label>
                <input class="form-control mb-3" v-model="eventThumbnail" placeholder="썸네일 이미지 URL" />
                <label>이미지 추가</label>
                <input class="form-control mb-3" v-model="eventImgUrl" placeholder="이미지 URL" />
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                  닫기
                </button>
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal" @click="saveEvent">
                  등록
                </button>
              </div>
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>


  <!-- 개별 이벤트 이미지 -->
  <div class="event-img" v-for="(data, index) in event" :key="index">
    <img :src="data.eventThumbnail" class="img-fluid" alt="이벤트이미지" type="button" @click="goEventDetail(data.eventId)" />
  </div>
</template>
<script>
import EventService from "@/services/shop/EventService";

export default {
  data() {
    return {
      isAdmin: this.$store.state.user != null && this.$store.state.user.userCode === "AT01",

      eventTitle: "",
      eventContent: "",
      eventThumbnail: "",
      eventImgUrl: "",

      event: [],
      user: {},
    }
  },
  methods: {
    // 전체 이벤트 가져오기
    async retrieveEvent() {
      try {
        let response = await EventService.getAll();
        this.event = response.data;
        console.log("전체 이벤트 : ", response.data);
        console.log("관리자 : ", this.isAdmin);
      } catch (e) {
        console.log(e);
      }
    },

    // 이벤트 등록
    async saveEvent() {
      try {
        // 이벤트 객체
        let data = {
          userId: this.this.$store.state.user.userId,
          eventTitle: this.eventTitle,
          eventContent: this.eventContent,
          eventThumbnail: this.eventThumbnail,
          eventCode: "BO0101",

          eventImgUrl: this.eventImgUrl   // 이벤트 이미지
        };

        let response = await EventService.create(data);
        console.log("이벤트 등록 성공: ", response.data);
        this.retrieveEvent(); // 재조회
      } catch (e) {
        console.log(e);
      }
    },
    // 상세 이벤트 페이지로 이동
    goEventDetail(eventId) {
      this.$router.push(`/event/${eventId}`);
    },
  },
  mounted() {
    this.retrieveEvent();
  },
};
</script>
<style>
@import "@/assets/css/category.css";

.event-save-button {
  margin-bottom: 30px;
}

.event-img {
  margin-bottom: 40px;
  text-align: center;
}
</style>
