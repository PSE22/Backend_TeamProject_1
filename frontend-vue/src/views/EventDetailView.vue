<template>
    <div class="event-detail-container">
        <div class="row event-detail-table">
            <div class="col-3 event-detail-title-th">제목</div>
            <div class="col-9 event-detail-title-td">{{ event.eventTitle }}</div>
        </div>
        <div class="row event-detail-table">
            <div class="col-3 event-detail-title-th">내용</div>
            <div class="col-9 event-detail-title-td">{{ event.eventContent }} </div>
        </div>
        <div class="row text-center event-detail-table">
            <div class="col-12" v-for="(data, index) in eventImg" :key="index">
                <img class="img-fluid" :src="data.eventImgUrl" />
            </div>
        </div>
    </div>
</template>
<script>
import EventService from "@/services/shop/EventService";

export default {
    data() {
        return {
            event: {},
            eventImg: []
        }
    },
    methods: {
        // 상세 이벤트 가져오기
        async retrieveEventDetail(eventId) {
            try {
                let response = await EventService.getEventDetail(eventId);
                this.event = response.data;
                console.log("상세 이벤트 : ", response.data);
            } catch (e) {
                console.log(e);
            }
        },
        // 이벤트 이미지 가져오기
        async retrieveEventImg(eventId) {
            try {
                let response = await EventService.getEventImage(eventId);
                this.eventImg = response.data;
                console.log("이벤트 이미지: ", response.data);
            } catch (e) {
                console.log(e);
            }
        },
    },
    mounted() {
        this.retrieveEventDetail(this.$route.params.eventId);
        this.retrieveEventImg(this.$route.params.eventId);
    },
}
</script>
<style>
.event-detail-container {
    border: solid 3px grey;
    border-radius: 10px;
    margin: 30px;
}
.event-detail-title-th, .event-detail-title-td {
    height: 70px;
    line-height: 70px;
    width: auto;

}
</style>