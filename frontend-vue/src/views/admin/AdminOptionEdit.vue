<template>
  <div>
    <div class="container mt-5 edit-box">
      <div align="center">
        <div class="logo">
          <h2>서울쥐</h2>
        </div>
      </div>
      <!-- null -> 거짓(false) -->
      <div v-if="adminOptionEdit">
        <div class="col-10 mx-auto">
          <div>
            <!-- 상품번호 시작 -->
            <div class="row g-3 align-items-center mb-3 mt-3">
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
                  v-model="adminOptionEdit.pdId"
                />
              </div>
            </div>
            <!-- 상품번호 끝 -->

            <!-- 옵션명 시작 -->
            <div class="row g-3 align-items-center mb-3 mt-3">
              <div class="col-4">
                <label htmlFor="opName" class="col-form-label"> 옵션명 </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="opName"
                  required
                  class="form-control"
                  name="opName"
                  v-model="adminOptionEdit.opName"
                />
              </div>
            </div>
            <!-- 옵션명 끝 -->

            <!-- 옵션추가가격 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="opPrice" class="col-form-label"> 옵션추가가격 </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="opPrice"
                  required
                  class="form-control"
                  name="opPrice"
                  v-model="adminOptionEdit.opPrice"
                />
              </div>
            </div>
            <!-- 옵션추가가격 끝 -->

            <!-- 옵션재고 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="opStock" class="col-form-label">
                  옵션재고
                </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="opStock"
                  required
                  class="form-control"
                  name="opStock"
                  v-model="adminOptionEdit.opStock"
                />
              </div>
            </div>
            <!-- 옵션재고 끝 -->

            <!-- 옵션분류코드 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="opCode" class="col-form-label">
                  옵션분류코드
                </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="opCode"
                  required
                  class="form-control"
                  name="opCode"
                  v-model="adminOptionEdit.opCode"
                />
              </div>
            </div>
            <!-- 옵션분류코드 끝 -->

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
                    value="활성"
                    v-model="adminOptionEdit.status"
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
                    value="비활성"
                    v-model="adminOptionEdit.status"
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
              @click="updateAdminOption"
            >
              수정
            </button>

            <button
              class="btn btn-outline-danger ms-3 col"
              @click="deleteAdminOption"
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
import AdminOptionService from "@/services/admin/AdminOptionService";
export default {
  data() {
    return {
      // TODO: 수정
      adminOptionEdit: null,
      message: "",
    };
  },
  methods: {
    // TODO: 수정/삭제 시작
    // 함수정의
    async getAdminOption(opId) {
      try {
        let response = await AdminOptionService.get(opId);
        this.adminOptionEdit = response.data;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 수정요청 함수
    async updateAdminOption() {
      try {
        let response = await AdminOptionService.update(
          this.adminOptionEdit.opId,
          this.adminOptionEdit
        );
        console.log(response.data);
        // 화면에 성공메세지 출력 : message
        alert("수정이 성공했습니다.");
        this.$router.push("/admin-product");
      } catch (e) {
        console.log(e);
      }
    },
    // 삭제요청 함수
    async deleteAdminOption() {
      let response = await AdminOptionService.delete(this.adminOptionEdit.opId);
      console.log(response.data);
      this.$router.push("/admin-product");
    },
  },
  // TODO: 수정/삭제 끝
  mounted() {
    // TODO: 수정/삭제 시작
    this.message = "";
    this.getAdminOption(this.$route.params.opId);
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
  width: 650px;
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
