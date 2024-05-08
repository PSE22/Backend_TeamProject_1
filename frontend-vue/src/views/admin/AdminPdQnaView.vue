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
            href="/admin"
          >
            <div class="sidebar-brand-icon rotate-n-15"></div>
            <div class="sidebar-brand-text mx-3 logo">서울쥐</div>
          </a>

          <!-- Divider -->
          <hr class="sidebar-divider my-0" />

          <!-- Nav Item - Dashboard -->
          <li class="nav-item active">
            <a class="nav-link" href="/admin">
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
                    placeholder="검색"
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

              <!-- Topbar Navbar -->
              <ul class="navbar-nav ml-auto">
                <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                <li class="nav-item dropdown no-arrow d-sm-none">
                  <a
                    class="nav-link dropdown-toggle"
                    href="#"
                    id="searchDropdown"
                    role="button"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    <i class="fas fa-search fa-fw"></i>
                  </a>
                  <!-- Dropdown - Messages -->
                  <div
                    class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                    aria-labelledby="searchDropdown"
                  >
                    <form class="form-inline mr-auto w-100 navbar-search">
                      <div class="input-group">
                        <input
                          type="text"
                          class="form-control bg-light border-0 small"
                          placeholder="Search for..."
                          aria-label="Search"
                          aria-describedby="basic-addon2"
                        />
                        <div class="input-group-append">
                          <button class="btn btn-primary" type="button">
                            <i class="fas fa-search fa-sm"></i>
                          </button>
                        </div>
                      </div>
                    </form>
                  </div>
                </li>

                <div class="topbar-divider d-none d-sm-block"></div>

                <!-- 우측 상단 유저 -->
                <li class="nav-item dropdown no-arrow">
                  <a
                    class="nav-link dropdown-toggle"
                    href="#"
                    id="userDropdown"
                    role="button"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    <span class="mr-2 d-none d-lg-inline text-gray-600 small"
                      >관리자 1</span
                    >
                    <img
                      class="img-profile rounded-circle"
                      src="img/undraw_profile.svg"
                    />
                  </a>
                  <!-- 드롭다운 우측 상단 유저 -->
                  <div
                    class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                    aria-labelledby="userDropdown"
                  >
                    <a class="dropdown-item" href="#">
                      <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                      Profile
                    </a>
                    <a class="dropdown-item" href="#">
                      <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                      Settings
                    </a>
                    <a class="dropdown-item" href="#">
                      <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                      Activity Log
                    </a>
                    <div class="dropdown-divider"></div>
                    <a
                      class="dropdown-item"
                      href="#"
                      data-toggle="modal"
                      data-target="#logoutModal"
                    >
                      <i
                        class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"
                      ></i>
                      로그아웃
                    </a>
                  </div>
                </li>
              </ul>
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
                                  <!-- TODO: 링크 : a 태그 (전체 새로고침(성능저하) -> 페이지전환) -->
                                  <!-- TODO: 뷰에서제공 링크 : router-link (부분 새로고침: 성능향상) -->
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
                                  <router-link
                                    :to="'/admin-pdqna-reply-edit/' + data.pdQnaReplyId"
                                  >
                                    <span class="badge text-bg-secondary"
                                      >답변 수정</span>
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
                    <!-- TODO: 페이지 시작 / 페이지 네이션 백에서 해놓음 -->
                    <div class="row">
                      <div class="col-sm-12 col-md-5">
                        <div
                          class="dataTables_info"
                          role="status"
                          aria-live="polite"
                        >
                            검색결과 총 {{ count }} 건
                        </div>
                      </div>
                      <div class="col-sm-12 col-md-7">
                        <div class="dataTables_paginate paging_Simple_numbers">
                          <b-pagination
                            v-model="page"
                            :total-rows="count"
                            :per-page="pageSize"
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
          </div>
          <!-- TODO: 게시판 끝 -->

          <!-- TODO: Footer 부분   -->
          <footer class="sticky-footer bg-white">
            <div class="container my-auto">
              <div class="copyright text-center my-auto">
                <span>Copyright &copy; Your Website 2021</span>
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
export default {
  data() {
    return {
      // TODO: 등록
      adminPdQnaData: {
        userId: this.$store.state.user.userId,
      }, // 관리자 ID
      submitted: false, // 저장버튼 클릭하면 true 바뀜

      // TODO: 백엔드 연결
      adminPdQna: [], // spring 에서 전송
      searchPdQnaTitle: "",

      // 공통 속성(현재페이지, 전체데이터개수,1페이지당개수)
      page: 1, // 현재페이지번호
      count: 0, // 전체데이터개수
      pageSize: 10, // 1페이지당개수(select태그)

      pageSizes: [10, 25, 50], //1페이지당개수 배열(select태그-option)
    };
  },
  methods: {
    // TODO: 등록 시작
    async saveAdminPdQnaData() {
      try {
        // 임시 객체 변수
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
          pdQnaReplyId: this.adminPdQnaData.pdQnaReplyId,
        };
        console.log(data);
        // TODO: 벡엔드로 객체 추가 요청
        let response = await AdminPdQnaService.create(data);
        // TODO: 콘솔에 결과 출력
        console.log(response);
        this.submitted = true; // 저장유무변수=true 변경
      } catch (e) {
        console.log(e);
      }
    },
    // 저장페이지 열기 함수 : 화면 초기화
    newAdminPdQnaData() {
      // 뷰/리액트 : 변수값을 조작하면 화면이 자동 갱신됨
      this.submitted = false;
      this.adminPdQnaData = {};
    },
    // TODO: 등록 끝
    // TODO: 백엔드 연결
    pageNoChange(value) {
      this.page = value; // 1) 현재페이지 변경
      this.retrieveAdminPdQna(); // 2) 재조회 요청
    },
    pageSizeChange() {
      this.page = 1; // 2) 현재 페이지번호 초기화(1)
      this.retrieveAdminPdQna(); // 3) 재조회 요청
    },
    async retrieveAdminPdQna() {
      try {
        let response = await AdminPdQnaService.getAll(
          this.searchPdQnaTitle, // 검색어
          this.page - 1, // 현재페이지번호-1
          this.pageSize // 1페이지당개수(size)
        );
        const { adminPdQna, totalItems } = response.data; // 쿠폰배열(벡엔드 전송)
        this.adminPdQna = adminPdQna; // 쿠폰배열(벡엔드 전송)
        this.count = totalItems; // 전체페이지수(벡엔드 전송)
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
