<template>
  <div>
    <div class="container mt-5 edit-box">
      <div align="center">
        <div class="logo">
          <h2>서울쥐</h2>
        </div>
      </div>
      <!-- null -> 거짓(false) -->
      <div v-if="adminPdQnaReply">
        <div class="col-8 mx-auto">
          <div>
            <!-- 상품문의번호 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="pdQnaId" class="col-form-label"> 상품문의번호 </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="pdQnaId"
                  required
                  class="form-control"
                  name="pdQnaId"
                  v-model="adminPdQnaReplyEdit.pdQnaId"
                />
              </div>
            </div>
            <!-- 상품문의번호 끝 -->

            <!-- 관리자 답변 내용 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="pdQnaReplyContent" class="col-form-label">
                  관리자 답변 내용
                </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="pdQnaReplyContent"
                  required
                  class="form-control"
                  name="pdQnaReplyContent"
                  v-model="adminPdQnaReplyEdit.pdQnaReplyContent"
                />
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
                    v-model="adminPdQnaReplyEdit.status"
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
                    v-model="adminPdQnaReplyEdit.status"
                  />
                  <label class="form-check-label" for="inlineRadio2"
                    >비활성</label
                  >
                </div>
              </div>
            </div>
            <!-- 상태 끝 -->
          </div>

          <div class="row g-3 mt-3 mb-3">
            <button
              type="submit"
              class="btn btn-outline-secondary ms-2 col"
              @click="updateAdminPdQnaReply"
            >
              수정
            </button>

            <button
              class="btn btn-outline-danger ms-3 col"
              @click="deleteAdminPdQnaReply"
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
import AdminPdQnaReplyService from '@/services/admin/AdminPdQnaReplyService';
export default {
  data() {
    return {
      // TODO: 수정
      adminPdQnaReply: null, // 초기값
      message: "", // 수정성공시 화면 성공메세지 출력하는 변수
    };
  },
  methods: {
    // TODO: 수정/삭제 시작
    // 함수정의
    async getAdminPdQnaReply(pdQnaReplyId) {
      try {
        let response = await AdminPdQnaReplyService.get(pdQnaReplyId);
        this.adminPdQnaReply = response.data; // spring 결과를 바인딩 속성변수 emp 저장
        // 로깅
        console.log("상세",response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async saveAdminPdQnaReplyEdit() {
      try {
        // 임시 객체 변수
        let data = {
          pdQnaId: this.adminPdQnaEdit.pdQnaId,
          pdQnaReplyContent: this.adminPdQnaEdit.pdQnaReplyContent, // 화면 입력
          status: this.adminPdQnaEdit.status,
        };
        console.log(data);
        // TODO: 벡엔드로 객체 추가 요청
        let response = await AdminPdQnaReplyService.update(data);
        // TODO: 콘솔에 결과 출력
        console.log(response);
        this.submitted = true; // 저장유무변수=true 변경
      } catch (e) {
        console.log(e);
      }
    },
    // 수정요청 함수
    async updateAdminPdQnaReply() {
      try {
        let response = await AdminPdQnaReplyService.update(
          this.adminPdQnaReply.pdQnaReplyId,
          this.adminPdQnaReply
        );
        // 로깅
        console.log(response.data);
        // 화면에 성공메세지 출력 : message
        alert("수정이 성공했습니다.");
        this.$router.push("/admin-pdqna");
      } catch (e) {
        console.log(e);
      }
    },
    // 삭제요청 함수
    async deleteAdminPdQnaReply() {
      let response = await AdminPdQnaReplyService.delete(this.adminPdQnaReply.pdQnaReplyId);
      console.log(response.data);
      this.$router.push("/admin-pdqna");
    },
  },
  // TODO: 수정/삭제 끝
  mounted() {
    // TODO: 수정/삭제 시작
    this.message = ""; // 변수 초기화
    this.getAdminPdQnaReply(this.$route.params.pdQnaReplyId); // 쿠폰메뉴를 클릭하면 cpid 옴
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