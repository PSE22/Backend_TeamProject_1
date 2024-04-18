<template>
    <!-- 이벤트 페이지 배너 -->
  <div class="container-fluid">
    <div>
        <h1 class="display-2 h1-class">Event</h1>
    </div>
  </div>
  <!-- 개별 이벤트 이미지 -->
  <div class="event-img">
    <img src="https://via.placeholder.com/1200x300?text=Image" class="img-fluid" alt="이벤트이미지">
  </div>
</template>
<script>
export default {
    data() {
        return {
            
        }
    },
};
</script>
<style>
.container-fluid {
    background-color: grey;
    height: 350px;
    color: white;
}

.event-img {
    margin: 50px 0px;
    text-align: center;
}

</style>
