<template>
  <div>
    <MyPageMain />
    <h1 style="margin-top: 100px; margin-bottom: 20px">나의 리뷰활동</h1>
    <div class="row">
      <table class="table">
        <thead class="table-light text-center">
          <tr>
            <th scope="col">상품정보</th>
            <th scope="col">별점</th>
            <th scope="col">내용</th>
            <th scope="col">등록일</th>
          </tr>
        </thead>
        <tbody class="table-group-divider align-middle">
          <tr v-for="(data, index) in review" :key="index">
            <td class="col-4">
              <div class="d-flex align-items-center text-start">
                <!-- 상품정보 -->
                <div class="flex-shrink-0">
                  <img
                    :src="data.pdImgUrl"
                    class="img-thumbnail me-3"
                    style="{ height: 15 + 'vh', width: 5 + 'vw' }"
                  />
                </div>
                <div class="flex-grow-1">
                  {{ data.pdName }}
                </div>
              </div>
            </td>
            <!-- 별점 -->
            <td class="col-1 text-center">⭐️⭐️⭐️⭐️⭐️</td>
            <td class="col-4">
              <!-- 리뷰 내용 -->
              <p>
                {{ data.reviewContent }}
              </p>
              <img
              :src="data.reviewImgUrl"
                class="img-thumbnail me-3"
              />
            </td>
            <!-- 등록일 -->
            <td class="col-1 text-center">{{ data.addDate }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="row">
      <b-pagination
        class="col-12 mb-3 d-flex justify-content-center"
        v-model="page"
        :total-rows="count"
        :per-page="pageSize"
        @click="allReview"
      ></b-pagination>
    </div>
  </div>
</template>
<script>
import MyPageMain from "@/components/mypage/MyPageMainMenu.vue";
import MyReviewService from "@/services/mypage/MyReviewService";
export default {
  components: {
    MyPageMain,
  },
  data() {
    return {
      review: [], // 리뷰 불러오기


      // 공통 페이징 속성
      page: 1, // 현재 페이지 번호
      count: 0, // 전체 데이터 개수
      pageSize: 3, // 화면에 보여질 개수
    };
  },
  methods: {
    // 전체조회
    async allReview() {
      try {
        // TODO: 공통 전체조회 서비스 함수 실행
        // TODO: spring 통신 : 비동기 코딩 : async ~ await
        let response = await MyReviewService.getAll(
          this.page - 1,
          this.pageSize
        );
        const { review, totalItems } = response.data;
        this.review = review; // spring 전달 객체 배열 (쿠폰배열)
        this.count = totalItems; // 전체 페이지 개수
        // 로깅
        console.log(response.data); // 웹브라우저 콘솔탭에 spring 전달 객체 배열 표시됨
      } catch (e) {
        console.log(e);
      }
    },
  },
  mounted() {
    this.allReview;
  },
};
</script>

<style>
@import "@/assets/css/mypage.css";
</style>
