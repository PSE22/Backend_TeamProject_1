<template>
  <div>
    <div class="container mt-5 edit-box">
      <div align="center">
        <div class="logo">
          <h2>서울쥐</h2>
        </div>
      </div>
      <!-- null -> 거짓(false) -->
      <div v-if="adminProductEdit">
        <div class="col-10 mx-auto">
          <div>

            <!-- 상품명 시작 -->
            <div class="row g-3 align-items-center mb-3 mt-3">
              <div class="col-4">
                <label htmlFor="pdName" class="col-form-label"> 상품명 </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="pdName"
                  required
                  class="form-control"
                  name="pdName"
                  v-model="adminProductEdit.pdName"
                />
              </div>
            </div>
            <!-- 상품명 끝 -->

            <!-- 가격 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="cpName" class="col-form-label"> 가격 </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="pdPrice"
                  required
                  class="form-control"
                  name="pdPrice"
                  v-model="adminProductEdit.pdPrice"
                />
              </div>
            </div>
            <!-- 가격 끝 -->

            <!-- 재고 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="pdStock" class="col-form-label">
                  재고
                </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="pdStock"
                  required
                  class="form-control"
                  name="pdStock"
                  v-model="adminProductEdit.pdStock"
                />
              </div>
            </div>
            <!-- 재고 끝 -->

            <!-- 카테고리분류코드 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="categoryCode" class="col-form-label">
                  카테고리분류코드
                </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="categoryCode"
                  required
                  class="form-control"
                  name="categoryCode"
                  v-model="adminProductEdit.categoryCode"
                />
              </div>
            </div>
            <!-- 카테고리분류코드 끝 -->

            <!-- 썸네일URL 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="pdThumbnail" class="col-form-label">
                  썸네일URL
                </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="pdThumbnail"
                  required
                  class="form-control"
                  name="pdThumbnail"
                  v-model="adminProductEdit.pdThumbnail"
                />
              </div>
            </div>
            <!-- 썸네일URL 끝 -->

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
                    v-model="adminProductEdit.status"
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
                    v-model="adminProductEdit.status"
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
              @click="updateAdminProduct"
            >
              수정
            </button>

            <button
              class="btn btn-outline-danger ms-3 col"
              @click="deleteAdminProduct"
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
import AdminProductService from "@/services/admin/AdminProductService";
export default {
  data() {
    return {
      // TODO: 수정
      adminProductEdit: null, //초기값
      message: "", // 수정성공시 화면 성공메세지 출력하는 변수
    };
  },
  methods: {
    // TODO: 수정/삭제 시작
    // 함수정의
    async getAdminProduct(pdId) {
      try {
        let response = await AdminProductService.get(pdId);
        this.adminProductEdit = response.data; // spring 결과를 바인딩 속성변수 저장
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 수정요청 함수
    async updateAdminProduct() {
      try {
        let response = await AdminProductService.update(
          this.adminProductEdit.pdId,
          this.adminProductEdit
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
    async deleteAdminProduct() {
      let response = await AdminProductService.delete(this.adminProductEdit.pdId);
      console.log(response.data);
      this.$router.push("/admin-product");
    },
  },
  // TODO: 수정/삭제 끝
  mounted() {
    // TODO: 수정/삭제 시작
    this.message = ""; // 변수 초기화
    this.getAdminProduct(this.$route.params.pdId); // 상품메뉴를 클릭하면 pdId 옴
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
