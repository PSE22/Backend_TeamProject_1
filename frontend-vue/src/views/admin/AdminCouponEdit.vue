<template>
  <div>
    <div class="container mt-5 edit-box">
      <div align="center">
        <div class="logo">
          <h2>서울쥐</h2>
        </div>
      </div>
      <!-- null -> 거짓(false) -->
      <div v-if="adminCouponEdit">
        <div class="col-8 mx-auto">
          <div>
            <!-- 상품번호 시작 -->
            <div class="row g-3 align-items-center mt-3 mb-3">
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
                  v-model="adminCouponEdit.pdId"
                />
              </div>
            </div>
            <!-- 상품번호 끝 -->

            <!-- 쿠폰명 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="cpName" class="col-form-label"> 쿠폰명 </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="cpName"
                  required
                  class="form-control"
                  name="cpName"
                  v-model="adminCouponEdit.cpName"
                />
              </div>
            </div>
            <!-- 쿠폰명 끝 -->

            <!-- 할인금액 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="cpDcPrice" class="col-form-label">
                  할인금액
                </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="cpDcPrice"
                  required
                  class="form-control"
                  name="cpDcPrice"
                  v-model="adminCouponEdit.cpDcPrice"
                />
              </div>
            </div>
            <!-- 할인금액 끝 -->

            <!-- 할인율 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="cpDcRate" class="col-form-label">
                  할인율
                </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="cpDcRate"
                  required
                  class="form-control"
                  name="cpDcRate"
                  v-model="adminCouponEdit.cpDcRate"
                />
              </div>
            </div>
            <!-- 할인율 끝 -->

            <!-- 최소사용금액 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="cpMinPrice" class="col-form-label">
                  최소사용금액
                </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="cpMinPrice"
                  required
                  class="form-control"
                  name="cpMinPrice"
                  v-model="adminCouponEdit.cpMinPrice"
                />
              </div>
            </div>
            <!-- 최소사용금액 끝 -->

            <!-- 최대할인금액 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="cpMaxDcPrice" class="col-form-label">
                  최대할인금액
                </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="cpMaxDcPrice"
                  required
                  class="form-control"
                  name="cpMaxDcPrice"
                  v-model="adminCouponEdit.cpMaxDcPrice"
                />
              </div>
            </div>
            <!-- 최대할인금액 끝 -->

            <!-- 만료일 시작 -->
            <div class="row g-3 align-items-center mb-3">
              <div class="col-4">
                <label htmlFor="cpExpireDate" class="col-form-label">
                  만료일
                </label>
              </div>

              <div class="col-8">
                <input
                  type="text"
                  id="cpExpireDate"
                  required
                  class="form-control"
                  name="cpExpireDate"
                  v-model="adminCouponEdit.cpExpireDate"
                />
              </div>
            </div>
            <!-- 만료일 끝 -->

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
                    v-model="adminCouponEdit.status"
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
                    v-model="adminCouponEdit.status"
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
              @click="updateAdminCoupon"
            >
              수정
            </button>

            <button
              class="btn btn-outline-danger ms-3 col"
              @click="deleteAdminCoupon"
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
import AdminCouponService from "@/services/admin/AdminCouponService";
export default {
  data() {
    return {
      // TODO: 수정
      adminCouponEdit: null,
      message: "",
    };
  },
  methods: {
    // TODO: 수정/삭제 시작
    // 함수정의
    async getAdminCoupon(cpId) {
      try {
        let response = await AdminCouponService.get(cpId);
        this.adminCouponEdit = response.data;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 수정요청 함수
    async updateAdminCoupon() {
      try {
        let response = await AdminCouponService.update(
          this.adminCouponEdit.cpId,
          this.adminCouponEdit
        );
        console.log(response.data);
        // 화면에 성공메세지 출력 : message
        alert("수정이 성공했습니다.");
        this.$router.push("/admin-coupon");
      } catch (e) {
        console.log(e);
      }
    },
    // 삭제요청 함수
    async deleteAdminCoupon() {
      let response = await AdminCouponService.delete(this.adminCouponEdit.cpId);
      console.log(response.data);
      this.$router.push("/admin-coupon");
    },
  },
  // TODO: 수정/삭제 끝
  mounted() {
    // TODO: 수정/삭제 시작
    this.message = "";
    this.getAdminCoupon(this.$route.params.cpId);
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
  width: 600px;
  height: 650px;
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
