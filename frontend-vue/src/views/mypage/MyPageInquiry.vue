<template>
  <div>
    <MyPageMain />
    <h1 style="margin-top: 100px; margin-bottom: 20px">1:1 문의내역</h1>
    <div class="row">
      <table class="table">
        <thead class="table-light text-center">
          <tr>
            <th scope="col">제목</th>
            <th scope="col">비밀글</th>
            <th scope="col">등록일</th>
            <th scope="col">답변일</th>
            <th scope="col">상태</th>
          </tr>
        </thead>
        <tbody class="table-group-divider align-middle">
          <tr v-for="(data, index) in inquiry" :key="index">
            <td class="col-4">
              <!-- 문의내역 제목 -->
              <div
                type="button"
                class="ms-3 qna-link"
                data-bs-toggle="modal"
                :data-bs-target="'#inquiryModal-' + index"
              >
                {{ data.pdQnaTitle }}
              </div>
              <!-- 문의내역 제목 모달창 -->
              <div
                class="modal fade"
                :id="'inquiryModal-' + index"
                tabindex="-1"
                aria-labelledby="exampleModalLabel"
                aria-hidden="true"
              >
                <div class="modal-dialog modal-dialog-centered">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h1 class="modal-title fs-5" id="exampleModalLabel">
                        {{ data.pdQnaTitle }}
                      </h1>
                      <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                      ></button>
                    </div>
                    <div class="modal-body">
                      <!-- 모달창 질문자 -->
                      <h2 class="fs-5">질문자</h2>
                      <p class="text-muted">상품명 : {{data.pdName}}</p>
                      <p>
                        {{ data.pdQnaContent }}
                      </p>
                      <!-- 문의 이미지 -->
                      <img
                        :src="data.pdQnaImgUrl"
                        v-if="pdQnaImgUrl"
                        class="img-thumbnail me-3"
                        style="width: 100px; height: 100px"
                      />
                      <hr />
                      <!-- 모달창 답변자 -->
                      <h2 class="fs-5">답변자</h2>
                      <p>
                        {{ data.pdQnaReplyContent }}
                      </p>
                    </div>
                    <div class="modal-footer">
                      <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal"
                      >
                        닫기
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </td>
            <td class="col-1 text-center" v-if="data.pdQnaSecret==='Y'">🔒</td>
            <td class="col-1 text-center" v-else>🔓</td>
            <td class="col-2 text-center">{{ data.pqAddDate }}</td>
            <td class="col-2 text-center">{{ data.pqrAddDate }}</td>
            <td class="col-2 text-center" v-if="data.pdQnaReplyId">답변 완료</td>
            <td class="col-2 text-center" v-else>답변 대기</td>
          </tr>
        </tbody>
      </table>
    </div>
    <!-- 페이징 번호 -->
    <div class="row">
      <b-pagination
        class="col-12 mb-3 d-flex justify-content-center"
        v-model="page"
        :total-rows="count"
        :per-page="pageSize"
        @click="allInquiry"
      ></b-pagination>
    </div>
  </div>
</template>
<script>
import MyPageMain from "@/components/mypage/MyPageMainMenu.vue";
import MyInquiryService from "@/services/mypage/MyInquiryService.js";
export default {
  components: {
    MyPageMain,
  },
  data() {
      return {
        inquiry: [], // 문의내역 불러오기
        userId: this.$store.state.userId,
        // 공통 페이징 속성
        page: 1, // 현재 페이지 번호
        count: 0, // 전체 데이터 개수
        pageSize: 10, // 화면에 보여질 개수
      };
    },
    methods: {
      // 전체조회
      async allInquiry(userId) {
        try {
          // TODO: 공통 전체조회 서비스 함수 실행
          // TODO: spring 통신 : 비동기 코딩 : async ~ await
          let response = await MyInquiryService.getAll(userId, this.page - 1, this.pageSize);
          const { inquiry, totalItems } = response.data;
          this.inquiry = inquiry; // spring 전달 객체 배열
          this.count = totalItems; // 전체 페이지 개수
          // 로깅
          console.log(response.data); // 웹브라우저 콘솔탭에 spring 전달 객체 배열 표시됨
        } catch (e) {
          console.log(e);
        }
      },
  },

  mounted() {
    this.allInquiry(this.$store.state.userId);
  },
};
</script>

<style>
@import "@/assets/css/mypage.css";
.qna-link {
  height: 50px;
  line-height: 50px;
  font-weight: 600;
  display: block;
}
</style>
