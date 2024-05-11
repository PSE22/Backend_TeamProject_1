<template>
    <div class="container">
        <div class="event-detail-container">
            <div class="row event-detail-table justify-content-md-center">
                <div class="col-2 text-center event-detail-title-th">제목</div>
                <div class="col-8 event-detail-title-td">{{ event.eventTitle }}</div>
            </div>
            <div class="row event-detail-table justify-content-md-center">
                <div class="col-2 text-center event-detail-title-th">내용</div>
                <div class="col-8 event-detail-title-td">
                    <!-- {{ event.eventContent }} -->
                    Lorem ipsum, dolor sit amet consectetur adipisicing elit. Provident mollitia necessitatibus, cum ex rem delectus corporis quas voluptates, corrupti eligendi voluptatum, ab reprehenderit quisquam? Ad veniam voluptates quasi vero quia!
                </div>
            </div>
            <div class="row text-center">
                <div class="col-12" v-for="(data, index) in eventImg" :key="index">
                    <img class="img-fluid" :src="data.eventImgUrl" />
                </div>
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
    margin: 30px;
}

.event-detail-title-th {
    height: 50px;
    line-height: 50px;
    padding-left: 20px;
    font-weight: bold;
    background-color: lightgray;
    border: solid 1px black;
    border-radius: 5px;  
}

.event-detail-title-td {
    height: 50px;
    line-height: 50px;
    padding-left: 20px;
    border: solid 1px black;
    border-radius: 5px;
}

.event-detail-table {
    margin: 3px;
    display: flex;
}
</style>