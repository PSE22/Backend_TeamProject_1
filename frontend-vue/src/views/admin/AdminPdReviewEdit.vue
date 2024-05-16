<template>
  <div>
    <div class="container mt-5 edit-box">
      <div align="center">
        <div class="logo">
          <h2>서울쥐</h2>
        </div>
      </div>
      <!-- null -> 거짓(false) -->
      <div v-if="adminPdReviewEdit">
        <div class="col-8 mx-auto">
          <div>
            <!-- 회원ID 시작 -->
            <div class="row g-3 align-items-center mt-3 mb-3">
              <div class="col-4">
                <label htmlFor="userId" class="col-form-label"> 회원ID </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="userId"
                  required
                  class="form-control"
                  name="userId"
                  v-model="adminPdReviewEdit.userId"
                />
              </div>
            </div>
            <!-- 회원ID 끝 -->
            
            <!-- 상품번호 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="pdId" class="col-form-label"> 상품번호 </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="pdId"
                  required
                  class="form-control"
                  name="pdId"
                  v-model="adminPdReviewEdit.pdId"
                />
              </div>
            </div>
            <!-- 상품번호 끝 -->

            <!-- 제목 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="reviewTitle" class="col-form-label"> 제목 </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="reviewTitle"
                  required
                  class="form-control"
                  name="reviewTitle"
                  v-model="adminPdReviewEdit.reviewTitle"
                />
              </div>
            </div>
            <!-- 제목 끝 -->

            <!-- 내용 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="reviewContent" class="col-form-label"> 내용 </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="reviewContent"
                  required
                  class="form-control"
                  name="reviewContent"
                  v-model="adminPdReviewEdit.reviewContent"
                />
              </div>
            </div>
            <!-- 내용 끝 -->

            <!-- 별점 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="reviewRate" class="col-form-label">
                  별점
                </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="reviewRate"
                  required
                  class="form-control"
                  name="reviewRate"
                  v-model="adminPdReviewEdit.reviewRate"
                />
              </div>
            </div>
            <!-- 별점 끝 -->

            <!-- 게시판 분류코드 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="reviewCode" class="col-form-label">
                  게시판 분류코드
                </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="reviewCode"
                  required
                  class="form-control"
                  name="reviewCode"
                  v-model="adminPdReviewEdit.reviewCode"
                />
              </div>
            </div>
            <!-- 게시판 분류코드 끝 -->

            <!-- 상태 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <h6 class="col-auto">상태</h6>
              <div class="col">
                <div class="form-check form-check-inline mt-3">
                  <input
                    type="radio"
                    class="form-check-input mb-4"
                    id="inlineRadio1"
                    name="status"
                    value="Y"
                    v-model="adminPdReviewEdit.status"
                  />
                  <label class="form-check-label" for="inlineRadio1"
                    >활성</label
                  >
                </div>
                <div class="form-check form-check-inline">
                  <input
                    type="radio"
                    class="form-check-input mb-4"
                    id="inlineRadio2"
                    name="status"
                    value="N"
                    v-model="adminPdReviewEdit.status"
                  />
                  <label class="form-check-label" for="inlineRadio2"
                    >비활성</label
                  >
                </div>
              </div>
            </div>
            <!-- 상태 끝 -->
          </div>

          <div class="row g-3 mb-3">
            <button
              type="submit"
              class="btn btn-outline-secondary ms-2 col"
              @click="updateAdminPdReview"
            >
              수정
            </button>

            <button
              class="btn btn-outline-danger ms-3 col"
              @click="deleteAdminPdReview"
            >
              삭제
            </button>
          </div>

          <!-- TODO: message 있으면 true, "" 이면 false -->
          <p v-if="message != ''" class="alert alert-success mt-3 text-center">
            {{ message }}
          </p>
        </div>
      </div>

      <div v-else>
        <br />
        <p>준비중...</p>
      </div>
    </div>
  </div>
</template>
<script>
import AdminPdReviewService from '@/services/admin/AdminPdReviewService';
export default {
  data() {
    return {
      // TODO: 수정
      adminPdReviewEdit: null, //초기값
      message: "", // 수정성공시 화면 성공메세지 출력하는 변수
    };
  },
  methods: {
    // TODO: 수정/삭제 시작
    // 함수정의
    async getAdminPdReview(reviewId) {
      try {
        let response = await AdminPdReviewService.get(reviewId);
        this.adminPdReviewEdit = response.data; // spring 결과를 바인딩 속성변수 저장
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 수정요청 함수
    async updateAdminPdReview() {
      try {
        let response = await AdminPdReviewService.update(
          this.adminPdReviewEdit.reviewId,
          this.adminPdReviewEdit
        );
        console.log(response.data);
        // 화면에 성공메세지 출력 : message
        alert("수정이 성공했습니다.");
        this.$router.push("/admin-pdqna");
      } catch (e) {
        console.log(e);
      }
    },
    // 삭제요청 함수
    async deleteAdminPdReview() {
      let response = await AdminPdReviewService.delete(this.adminPdReviewEdit.reviewId);
      console.log(response.data);
      this.$router.push("/admin-pdqna");
    },
  },
  // TODO: 수정/삭제 끝
  mounted() {
    // TODO: 수정/삭제 시작
    this.message = ""; // 변수 초기화
    this.getAdminPdReview(this.$route.params.reviewId); // 리뷰메뉴를 클릭하면 reviewId 옴
    // TODO: 수정/삭제 끝
  },
};
</script>
<style>
.edit-box {
  margin: 100px auto;
  background-color: rgba(255, 255, 255, 1);
  padding: 40px 30px;
  border: 3px solid #505050;
  width: 700px;
  height: 600px;
}
.a1 {
  position: relative;
}
.a {
  position: absolute;
  top: 50%;
}

@font-face {
  font-family: "YClover-Bold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_231029@1.1/YClover-Bold.woff2")
    format("woff2");
  font-weight: 700;
  font-style: normal;
}

.logo {
  /* background-color: rgb(115, 235, 67); */
  font-size: 30px;
  font-family: "YClover-Bold";
}
</style>
