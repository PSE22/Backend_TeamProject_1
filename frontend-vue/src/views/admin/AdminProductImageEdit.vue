<template>
  <div>
    <div class="container mt-5 edit-box">
      <div align="center">
        <div class="logo">
          <h2>서울쥐</h2>
        </div>
      </div>
      <!-- null -> 거짓(false) -->
      <div v-if="adminProductImageEdit">
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
                  v-model="adminProductImageEdit.pdId"
                />
              </div>
            </div>
            <!-- 상품번호 끝 -->

            <!-- 이미지 URL 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="pdImgUrl" class="col-form-label">
                  이미지 URL
                </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="pdImgUrl"
                  required
                  class="form-control"
                  name="pdImgUrl"
                  v-model="adminProductImageEdit.pdImgUrl"
                />
              </div>
            </div>
            <!-- 이미지 URL 끝 -->

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
                    v-model="adminProductImageEdit.status"
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
                    v-model="adminProductImageEdit.status"
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
              @click="updateAdminProductImage"
            >
              수정
            </button>

            <button
              class="btn btn-outline-danger ms-3 col"
              @click="deleteAdminProductImage"
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
import AdminProductImageService from "@/services/admin/AdminProductImageService";
export default {
  data() {
    return {
      // TODO: 수정
      adminProductImageEdit: null, //초기값
      message: "", // 수정성공시 화면 성공메세지 출력하는 변수
    };
  },
  methods: {
    // TODO: 수정/삭제 시작
    async getAdminProductImage(pdImgId) {
      try {
        let response = await AdminProductImageService.get(pdImgId);
        this.adminProductImageEdit = response.data;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 수정요청 함수
    async updateAdminProductImage() {
      try {
        let response = await AdminProductImageService.update(
          this.adminProductImageEdit.pdImgId,
          this.adminProductImageEdit
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
    async deleteAdminProductImage() {
      let response = await AdminProductImageService.delete(this.adminProductImageEdit.pdImgId);
      console.log(response.data);
      this.$router.push("/admin-product");
    },
  },
  // TODO: 수정/삭제 끝
  mounted() {
    // TODO: 수정/삭제 시작
    this.message = ""; // 변수 초기화
    this.getAdminProductImage(this.$route.params.pdImgId); // 상품메뉴를 클릭하면 pdId 옴
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
  height: 400px;
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
