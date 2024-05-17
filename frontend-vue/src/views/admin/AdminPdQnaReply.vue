<template>
  <div>
    <div class="container mt-5 edit-box">
      <div align="center">
        <div class="logo">
          <h2>서울쥐</h2>
        </div>
      </div>
      <!-- null -> 거짓(false) -->
      <div v-if="adminPdQnaData">
        <div class="col-8 mx-auto">
          <div>
            <!-- 상품문의번호 시작 -->
            <div class="row g-3 align-items-center mt-3 mb-3">
              <div class="col-4">
                <label htmlFor="pdQnaId" class="col-form-label">
                  상품문의번호
                </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="pdQnaId"
                  disabled
                  required
                  class="form-control"
                  name="pdQnaId"
                  v-model="adminPdQnaData.pdQnaId"
                />
              </div>
            </div>
            <!-- 상품문의번호 끝 -->

            <!-- 고객 문의 내용 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="pdQnaContent" class="col-form-label">
                  고객 문의 내용
                </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="pdQnaContent"
                  disabled
                  required
                  class="form-control"
                  name="pdQnaContent"
                  v-model="adminPdQnaData.pdQnaContent"
                />
              </div>
            </div>
            <!-- 고객 문의 내용 끝 -->

            <!-- 관리자 답변 내용 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="pdQnaReplyContent" class="col-form-label">
                  관리자 답변 내용
                </label>
              </div>

              <div class="col-8">
                <textarea
                  id="pdQnaReplyContent"
                  required
                  class="form-control"
                  name="pdQnaReplyContent"
                  v-model="adminPdQnaReplyData.pdQnaReplyContent"
                ></textarea>
              </div>
            </div>
            <!-- 관리자 답변 내용 끝 -->

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
                    v-model="adminPdQnaReplyData.status"
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
                    v-model="adminPdQnaReplyData.status"
                  />
                  <label class="form-check-label" for="inlineRadio2"
                    >비활성</label
                  >
                </div>
              </div>
            </div>
            <!-- 상태 끝 -->
          </div>

          <!-- 등록과 취소를 한 행에 나타내기 -->
          <div class="row g-3 mt-3 mb-3">
            <div class="col">
              <button
                type="submit"
                class="btn btn-outline-secondary w-100"
                @click="saveAdminPdQnaReplyData"
              >
                등록
              </button>
            </div>
            <!-- 등록 끝 -->

            <!-- 취소 시작 -->
            <div class="col">
              <router-link to="/admin-pdqna">
                <button class="btn btn-outline-danger w-100">취소</button>
              </router-link>
            </div>
          </div>
          <!-- 취소 끝 -->

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
import AdminPdQnaReplyService from "@/services/admin/AdminPdQnaReplyService";
import AdminPdQnaService from "@/services/admin/AdminPdQnaService";
export default {
  data() {
    return {
      // TODO: 등록
      adminPdQnaData: null,
      adminPdQnaReplyData: {
        pdQnaId: "",
        pdQnaReplyContent: "",
        status: "",
      },
      message: "",
    };
  },
  methods: {
    // TODO: 등록 시작
    // 함수정의
    async getAdminPdQna(pdQnaId) {
      try {
        let response = await AdminPdQnaService.get(pdQnaId);
        this.adminPdQnaData = response.data;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async saveAdminPdQnaReplyData() {
      try {
        let data = {
          pdQnaId: this.adminPdQnaData.pdQnaId,
          pdQnaReplyContent: this.adminPdQnaReplyData.pdQnaReplyContent,
          status: this.adminPdQnaReplyData.status,
        };
        console.log(data);
        // TODO: 벡엔드로 객체 추가 요청
        let response = await AdminPdQnaReplyService.create(data);
        // TODO: 콘솔에 결과 출력
        console.log(response);
        this.submitted = true;
      } catch (e) {
        console.log(e);
      }
    },
  },
  // TODO: 등록 끝
  mounted() {
    // TODO: 등록 시작
    this.message = "";
    this.getAdminPdQna(this.$route.params.pdQnaId);
    // TODO: 등록 끝
  },
};
</script>
<style>
.edit-box {
  margin: 100px auto;
  background-color: rgba(255, 255, 255, 1);
  padding: 40px 30px;
  border: 3px solid #505050;
  width: 750px;
  height: 500px;
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
