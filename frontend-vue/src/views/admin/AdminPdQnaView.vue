<template>
  <div>
    <!-- TODO: 여기 -->
    <body id="page-top">
      <!-- Page Wrapper -->
      <div id="wrapper">
        <!-- TODO: 왼쪽 메뉴 -->
        <ul
          class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
          id="accordionSidebar"
        >
          <!-- Sidebar - Brand -->
          <a
            class="sidebar-brand d-flex align-items-center justify-content-center"
            href="/admin-product"
          >
            <div class="sidebar-brand-icon rotate-n-15"></div>
            <div class="sidebar-brand-text mx-3 logo">서울쥐</div>
          </a>

          <!-- Divider -->
          <hr class="sidebar-divider my-0" />

          <!-- Nav Item - Dashboard -->
          <li class="nav-item active">
            <a class="nav-link" href="/admin-product">
              <i class="fas fa-fw fa-tachometer-alt"></i>
              <span>처음으로</span></a
            >
          </li>

          <!-- Divider -->
          <hr class="sidebar-divider" />

          <!-- Nav Item - Pages Collapse Menu -->
          <li class="nav-item">
            <a class="nav-link" href="/admin-order">
              <i class="fas fa-fw fa-table"></i>
              <span>주문 관리</span></a
            >
          </li>

          <!-- Nav Item - Utilities Collapse Menu -->
          <li class="nav-item">
            <a class="nav-link" href="/admin-product">
              <i class="fas fa-fw fa-table"></i>
              <span>상품 관리</span></a
            >
          </li>

          <!-- Divider -->
          <hr class="sidebar-divider" />

          <!-- Nav Item - Pages Collapse Menu -->
          <li class="nav-item">
            <a class="nav-link" href="/admin-coupon">
              <i class="fas fa-fw fa-table"></i>
              <span>쿠폰관리</span></a
            >
          </li>

          <!-- Nav Item - Charts -->
          <li class="nav-item">
            <a class="nav-link" href="/admin-chart">
              <i class="fas fa-fw fa-chart-area"></i>
              <span>통계</span></a
            >
          </li>

          <!-- Nav Item - Tables -->
          <li class="nav-item">
            <a class="nav-link" href="/admin-pdqna">
              <i class="fas fa-fw fa-table"></i>
              <span>문의관리</span></a
            >
          </li>
        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
          <!-- Main Content -->
          <div id="content">
            <!-- TODO: 상단 메뉴 시작 -->
            <nav
              class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow"
            >

              <!-- 좌측상단 검색 -->
              <form
                class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"
              >
                <div class="input-group">
                  <input
                    type="text"
                    class="form-control bg-light border-0 small"
                    placeholder="상품문의 검색"
                    aria-label="Search"
                    aria-describedby="basic-addon2"
                    v-model="searchPdQnaTitle"
                  />
                  <div class="input-group-append">
                    <button class="btn btn-primary" type="button" @click="retrieveAdminPdQna">
                      <i class="fas fa-search fa-sm"></i>
                    </button>
                  </div>
                </div>
              </form>
            </nav>
            <!-- 상단 메뉴 끝 -->

            <!-- TODO: 문의관리 시작 -->
            <div class="container-fluid">
              <!-- Page Heading -->
              <h1 class="h3 mb-2 text-gray-800">상품문의</h1>
              <br />

              <!-- DataTales Example -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">상품문의</h6>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <div v-if="!submitted">
                      <div class="row">
                        <div class="col-sm-12">
                          <table
                            class="table table-bordered dataTable"
                            width="100%"
                            cellspacing="0"
                            role="grid"
                            aria-describedby="dataTable_info"
                            style="width: 100%"
                          >
                            <thead>
                              <tr role="row">
                                <th>액션</th>
                                <th>상품 문의번호</th>
                                <th>회원ID</th>
                                <th>상품번호</th>
                                <th>제목</th>
                                <th>내용</th>
                                <th>비밀글 여부</th>
                                <th>게시판 분류코드</th>
                                <th>저장일</th>
                                <th>수정일</th>
                                <th>삭제일</th>
                                <th>상태</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr
                                v-for="(data, index) in adminPdQna"
                                :key="index"
                              >
                                <td>
                                  <router-link
                                    :to="'/admin-pdqna-edit/' + data.pdQnaId"
                                  >
                                    <span class="badge text-bg-secondary"
                                      >게시글 수정</span
                                    >
                                  </router-link>
                                  <router-link
                                    :to="'/admin-pdqna-reply/' + data.pdQnaId"
                                  >
                                    <span class="badge text-bg-primary"
                                      >답변 등록</span>
                                  </router-link>
                                </td>
                                <td>{{ data.pdQnaId }}</td>
                                <td>{{ data.userId }}</td>
                                <td>{{ data.pdId }}</td>
                                <td>{{ data.pdQnaTitle }}</td>
                                <td>{{ data.pdQnaContent }}</td>
                                <td>{{ data.pdQnaSecret }}</td>
                                <td>{{ data.pdQnaCode }}</td>
                                <td>{{ data.addDate }}</td>
                                <td>{{ data.modDate }}</td>
                                <td>{{ data.delDate }}</td>
                                <td>{{ data.status }}</td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                    <!-- TODO: 페이지 시작 -->
                    <div class="row">
                      <div class="col-sm-12 col-md-5">
                        <div
                          class="dataTables_info"
                          role="status"
                          aria-live="polite"
                        >
                            검색결과 총 {{ pdQnaCount }} 건
                        </div>
                      </div>
                      <div class="col-sm-12 col-md-7">
                        <div class="dataTables_paginate paging_Simple_numbers">
                          <b-pagination
                            v-model="pdQnaPage"
                            :total-rows="pdQnaCount"
                            :per-page="pdQnaPageSize"
                            @click="retrieveAdminPdQna"
                          ></b-pagination>
                        </div>
                      </div>
                    </div>
                    <!-- TODO: 페이지 끝 -->
                  </div>
                </div>
              </div>
              <!-- /.container-fluid -->
            </div>
          <!-- TODO: 문의관리 끝 -->

          <!-- TODO: 답변관리 시작 -->
          <!-- 좌측중단 검색 -->
              <form
                class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"
              >
                <div class="input-group">
                  <input
                    type="text"
                    class="form-control bg-light border-0 small"
                    placeholder="답변관리 검색"
                    aria-label="Search"
                    aria-describedby="basic-addon2"
                    v-model="searchPdQnaReplyContent"
                  />
                  <div class="input-group-append">
                    <button
                      class="btn btn-primary"
                      type="button"
                      @click="retrieveAdminPdQnaReply"
                    >
                      <i class="fas fa-search fa-sm"></i>
                    </button>
                  </div>
                </div>
              </form>
          <div class="container-fluid">
              <!-- Page Heading -->
              <h1 class="h3 mt-3 mb-2 text-gray-800">답변관리</h1>
              <br />

              <!-- DataTales Example -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">답변관리</h6>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <div v-if="!submitted">
                      <div class="row">
                        <div class="col-sm-12">
                          <table
                            class="table table-bordered dataTable"
                            width="100%"
                            cellspacing="0"
                            role="grid"
                            aria-describedby="dataTable_info"
                            style="width: 100%"
                          >
                            <thead>
                              <tr role="row">
                                <th>액션</th>
                                <th>답변 ID</th>
                                <th>상품 문의번호</th>
                                <th>답변 내용</th>
                                <th>저장일</th>
                                <th>수정일</th>
                                <th>삭제일</th>
                                <th>상태</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr
                                v-for="(data, index) in adminPdQnaReply"
                                :key="index"
                              >
                                <td>
                                  <router-link
                                    :to="'/admin-pdqna-reply-edit/' + data.pdQnaReplyId"
                                  >
                                    <span class="badge text-bg-secondary"
                                      >답변 수정</span
                                    >
                                  </router-link>
                                </td>
                                <td>{{ data.pdQnaReplyId }}</td>
                                <td>{{ data.pdQnaId }}</td>
                                <td>{{ data.pdQnaReplyContent }}</td>
                                <td>{{ data.addDate }}</td>
                                <td>{{ data.modDate }}</td>
                                <td>{{ data.delDate }}</td>
                                <td>{{ data.status }}</td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                    <!-- TODO: 페이지 시작 -->
                    <div class="row">
                      <div class="col-sm-12 col-md-5">
                        <div
                          class="dataTables_info"
                          role="status"
                          aria-live="polite"
                        >
                            검색결과 총 {{ pdQnaReplyCount }} 건
                        </div>
                      </div>
                      <div class="col-sm-12 col-md-7">
                        <div class="dataTables_paginate paging_Simple_numbers">
                          <b-pagination
                            v-model="pdQnaReplyPage"
                            :total-rows="pdQnaReplyCount"
                            :per-page="pdQnaReplyPageSize"
                            @click="retrieveAdminPdQnaReply"
                          ></b-pagination>
                        </div>
                      </div>
                    </div>
                    <!-- TODO: 페이지 끝 -->
                  </div>
                </div>
              </div>
              <!-- /.container-fluid -->
            </div>
          <!-- TODO: 답변관리 끝 -->

          <!-- TODO: 상품리뷰 관리 시작 -->
          <!-- 좌측중단 검색 -->
              <form
                class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"
              >
                <div class="input-group">
                  <input
                    type="text"
                    class="form-control bg-light border-0 small"
                    placeholder="상품리뷰 검색"
                    aria-label="Search"
                    aria-describedby="basic-addon2"
                    v-model="searchReviewTitle"
                  />
                  <div class="input-group-append">
                    <button
                      class="btn btn-primary"
                      type="button"
                      @click="retrieveAdminPdReview"
                    >
                      <i class="fas fa-search fa-sm"></i>
                    </button>
                  </div>
                </div>
              </form>
          <div class="container-fluid">
              <!-- Page Heading -->
              <h1 class="h3 mt-3 mb-2 text-gray-800">상품리뷰</h1>
              <br />

              <!-- DataTales Example -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">상품리뷰</h6>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <div v-if="!submitted">
                      <div class="row">
                        <div class="col-sm-12">
                          <table
                            class="table table-bordered dataTable"
                            width="100%"
                            cellspacing="0"
                            role="grid"
                            aria-describedby="dataTable_info"
                            style="width: 100%"
                          >
                            <thead>
                              <tr role="row">
                                <th>액션</th>
                                <th>리뷰번호</th>
                                <th>회원ID</th>
                                <th>상품번호</th>
                                <th>제목</th>
                                <th>내용</th>
                                <th>별점</th>
                                <th>게시판 분류코드</th>
                                <th>저장일</th>
                                <th>수정일</th>
                                <th>삭제일</th>
                                <th>상태</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr
                                v-for="(data, index) in adminPdReview"
                                :key="index"
                              >
                                <td>
                                  <router-link
                                    :to="'/admin-pdqna-review-edit/' + data.reviewId"
                                  >
                                    <span class="badge text-bg-secondary"
                                      >리뷰 수정</span
                                    >
                                  </router-link>
                                </td>
                                <td>{{ data.reviewId }}</td>
                                <td>{{ data.userId }}</td>
                                <td>{{ data.pdId }}</td>
                                <td>{{ data.reviewTitle }}</td>
                                <td>{{ data.reviewContent }}</td>
                                <td>{{ data.reviewContent }}</td>
                                <td>{{ data.reviewCode }}</td>
                                <td>{{ data.addDate }}</td>
                                <td>{{ data.modDate }}</td>
                                <td>{{ data.delDate }}</td>
                                <td>{{ data.status }}</td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                    <!-- TODO: 페이지 시작 -->
                    <div class="row">
                      <div class="col-sm-12 col-md-5">
                        <div
                          class="dataTables_info"
                          role="status"
                          aria-live="polite"
                        >
                            검색결과 총 {{ pdReviewCount }} 건
                        </div>
                      </div>
                      <div class="col-sm-12 col-md-7">
                        <div class="dataTables_paginate paging_Simple_numbers">
                          <b-pagination
                            v-model="pdReviewPage"
                            :total-rows="pdReviewCount"
                            :per-page="pdReviewPageSize"
                            @click="retrieveAdminPdReview"
                          ></b-pagination>
                        </div>
                      </div>
                    </div>
                    <!-- TODO: 페이지 끝 -->
                  </div>
                </div>
              </div>
              <!-- /.container-fluid -->
            </div>
          <!-- TODO: 상품리뷰 관리 끝 -->
          </div>

          <!-- TODO: Footer 부분   -->
          <footer class="sticky-footer bg-white">
            <div class="container my-auto">
              <div class="logo text-center my-auto">
                <span>서울쥐</span>
              </div>
            </div>
          </footer>
          <!-- End of Footer -->
        </div>
        <!-- End of Content Wrapper -->
      </div>
      <!-- End of Page Wrapper -->

      <!-- TODO: 맨 위 스크롤 버튼-->
      <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
      </a>
    </body>
  </div>
</template>
<script>
import AdminPdQnaService from "@/services/admin/AdminPdQnaService";
import AdminPdQnaReplyService from '@/services/admin/AdminPdQnaReplyService';
import AdminPdReviewService from '@/services/admin/AdminPdReviewService';
export default {
  data() {
    return {
      // TODO: 등록
      adminPdQnaData: {
        userId: this.$store.state.user.userId,
      }, // 상품문의
      adminPdQnaReplyData: {
        userId: this.$store.state.user.userId,
      }, // 답변관리
      adminPdReviewData: {
        userId: this.$store.state.user.userId,
      }, // 리뷰관리
      submitted: false,

      // TODO: 백엔드 연결
      adminPdQna: [], // 상품문의
      adminPdQnaReply: [], // 답변관리
      adminPdReview: [], // 리뷰관리
      searchPdQnaTitle: "", // 문의 내용
      searchPdQnaReplyContent: "", // 답변 내용
      searchReviewTitle: "", // 리뷰 제목

      pdQnaPage: 1, // 현재페이지번호
      pdQnaReplyPage: 1,
      pdReviewPage: 1,
      pdQnaCount: 0, // 전체데이터개수
      pdQnaReplyCount: 0,
      pdReviewCount: 0,
      pdQnaPageSize: 10, // 1페이지당개수(select태그)
      pdQnaReplyPageSize: 10,
      pdReviewPageSize: 10,

      pageSizes: [10, 25, 50], //1페이지당개수 배열(select태그-option)
    };
  },
  methods: {
    // TODO: 상품문의 등록 시작
    async saveAdminPdQnaData() {
      try {
        let data = {
          pdQnaId: this.adminPdQnaData.pdQnaId,
          userId: this.adminPdQnaData.userId,
          pdId: this.adminPdQnaData.pdId,
          pdQnaTitle: this.adminPdQnaData.pdQnaTitle,
          pdQnaContent: this.adminPdQnaData.pdQnaContent,
          pdQnaSecret: this.adminPdQnaData.pdQnaSecret,
          pdQnaCode: this.adminPdQnaData.pdQnaCode,
          addDate: this.adminPdQnaData.addDate,
          modDate: this.adminPdQnaData.modDate,
          delDate: this.adminPdQnaData.delDate,
          status: this.adminPdQnaData.status,
        };
        console.log(data);
        // TODO: 벡엔드로 객체 추가 요청
        let response = await AdminPdQnaService.create(data);
        // TODO: 콘솔에 결과 출력
        console.log(response);
        this.submitted = true;
      } catch (e) {
        console.log(e);
      }
    },
    newAdminPdQnaData() {
      this.submitted = false;
      this.adminPdQnaData = {};
    },
    // TODO: 상품문의 등록 끝
    // TODO: 답변 등록 시작
    async saveAdminPdQnaReplyData() {
      try {
        let replyData = {
          pdQnaReplyId: this.adminPdQnaReplyData.pdQnaReplyId,
          pdQnaId: this.adminPdQnaReplyData.pdQnaId, // 상품문의번호 ID
          pdQnaReplyContent: this.adminPdQnaReplyData.pdQnaReplyContent,
        };
        console.log("답변저장",replyData);
        let response = await AdminPdQnaReplyService.create(replyData);
        console.log(response);
      } catch (e) {
        console.log(e);
      }
    },
    newAdminPdQnaReplyData() {
      this.submitted = false;
      this.adminPdQnaReplyData = {};
    },
    // TODO: 답변 등록 끝
    // TODO: 리뷰 등록 시작
    async saveAdminPdReviewData() {
      try {
        let reviewData = {
          reviewId: this.adminPdReViewData.reviewId,
          userId: this.adminPdReViewData.userId, // 회원 ID
          pdId: this.adminPdReViewData.pdId, // 상품번호
          reviewTitle: this.adminPdReViewData.reviewTitle,
          reviewContent: this.adminPdReViewData.reviewContent,
          reviewRate: this.adminPdReViewData.reviewRate,
          reviewCode: this.adminPdReViewData.reviewCode,
        };
        console.log("리뷰저장",reviewData);
        let response = await AdminPdReviewService.create(reviewData);
        console.log(response);
      } catch (e) {
        console.log(e);
      }
    },
    newAdminPdReViewData() {
      this.submitted = false;
      this.adminPdReViewData = {};
    },
    // TODO: 리뷰 등록 끝
    // TODO: 백엔드 연결
    pageNoChange(value) {
      this.pdQnaPage = value; // 1) 현재페이지 변경
      this.pdQnaReplyPage = value;
      this.pdReviewPage = value;
      this.retrieveAdminPdQna(); // 2) 재조회 요청
      this.retrieveAdminPdQnaReply();
      this.retrieveAdminPdReview();
    },
    pageSizeChange() {
      this.pdQnaPage = 1; // 1) 현재 페이지번호 초기화(1)
      this.pdQnaReplyPage = 1;
      this.pdReviewPage = 1;
      this.retrieveAdminPdQna(); // 2) 재조회 요청
      this.retrieveAdminPdQnaReply();
      this.retrieveAdminPdReview();
    },
    async retrieveAdminPdQna() {
      try {
        let response = await AdminPdQnaService.getAll(
          this.searchPdQnaTitle,
          this.pdQnaPage - 1,
          this.pdQnaPageSize
        );
        const { adminPdQna, totalItems } = response.data;
        this.adminPdQna = adminPdQna;
        this.pdQnaCount = totalItems;
        console.log("전체조회", response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async retrieveAdminPdQnaReply() {
      try {
        let response = await AdminPdQnaReplyService.getAll(
          this.searchPdQnaReplyContent,
          this.pdQnaReplyPage - 1,
          this.pdQnaReplyPageSize
        );
        const { adminPdQnaReply, totalItems } = response.data;
        this.adminPdQnaReply = adminPdQnaReply;
        this.pdQnaReplyCount = totalItems;
        console.log("전체조회", response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async retrieveAdminPdReview() {
      try {
        let response = await AdminPdReviewService.getAll(
          this.searchReviewTitle,
          this.pdReviewPage - 1,
          this.pdReviewPageSize
        );
        const { adminPdReview, totalItems } = response.data;
        this.adminPdReview = adminPdReview;
        this.pdReviewCount = totalItems;
        console.log("전체조회", response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // TODO: 백엔드 끝
    // TODO: 백엔드 시작
  },
  mounted() {
    this.retrieveAdminPdQna(); // 전체 조회 함수 실행
    this.retrieveAdminPdQnaReply();
    this.retrieveAdminPdReview();
    // TODO: 백엔드 끝
  },
};
</script>
<style>
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
.table th {
  white-space: nowrap; /* 줄바꿈 방지 */
  text-overflow: ellipsis; /* 텍스트 생략 */
  padding: 0 30px; /* 좌우 여백 추가 */
}
.table td {
  text-align: center; /* 가운데 정렬 */
}
</style>
