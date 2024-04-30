<template>
  <!-- Custom fonts for this template -->
  <link
    href="../../../public/vendor/fontawesome-free/css/all.min.css"
    rel="stylesheet"
    type="text/css"
  />
  <link
    href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
    rel="stylesheet"
  />

  <!-- Custom styles for this template -->
  <link href="../../../public/css/sb-admin-2.min.css" rel="stylesheet" />

  <!-- Custom styles for this page -->
  <link
    href="../../../public/vendor/datatables/dataTables.bootstrap4.min.css"
    rel="stylesheet"
  />
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
            <a class="nav-link" href="/adorder">
              <i class="fas fa-fw fa-table"></i>
              <span>주문 관리</span></a
            >
          </li>

          <!-- Nav Item - Utilities Collapse Menu -->
          <li class="nav-item">
            <a class="nav-link" href="/adproduct">
              <i class="fas fa-fw fa-table"></i>
              <span>상품 관리</span></a
            >
          </li>

          <!-- Divider -->
          <hr class="sidebar-divider" />

          <!-- Nav Item - Pages Collapse Menu -->
          <li class="nav-item">
            <a class="nav-link" href="/adcoupon">
              <i class="fas fa-fw fa-table"></i>
              <span>쿠폰관리</span></a
            >
          </li>

          <!-- Nav Item - Charts -->
          <li class="nav-item">
            <a class="nav-link" href="/adchart">
              <i class="fas fa-fw fa-chart-area"></i>
              <span>통계</span></a
            >
          </li>

          <!-- Nav Item - Tables -->
          <li class="nav-item">
            <a class="nav-link" href="/adtable">
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
                  />
                  <div class="input-group-append">
                    <button class="btn btn-primary" type="button">
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

            <!-- TODO: 쿠폰 관리 시작 -->
            <div class="container-fluid">
              <!-- Page Heading -->
              <h1 class="h3 mb-2 text-gray-800">쿠폰관리</h1>
              <br />

              <!-- DataTales Example -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">쿠폰관리</h6>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <!-- TODO: 등록/수정/삭제 시작 백에서 연결 해야함 -->
                    <button type="button" class="btn btn-primary mr-3 mb-3">등록</button>
                    <button type="button" class="btn btn-secondary mr-3 mb-3">수정</button>
                    <button type="button" class="btn btn-danger mr-3 mb-3">삭제</button>
                    <!-- 등록/수정/삭제 끝 -->
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
                              <th>Name</th>
                              <th>Position</th>
                              <th>Office</th>
                              <th>Age</th>
                              <th>Start date</th>
                              <th>Salary</th>
                            </tr>
                          </thead>
                          <tfoot>
                            <tr>
                              <th>Name</th>
                              <th>Position</th>
                              <th>Office</th>
                              <th>Age</th>
                              <th>Start date</th>
                              <th>Salary</th>
                            </tr>
                          </tfoot>
                          <tbody>
                            <tr>
                              <td>Tiger Nixon</td>
                              <td>System Architect</td>
                              <td>Edinburgh</td>
                              <td>61</td>
                              <td>2011/04/25</td>
                              <td>$320,800</td>
                            </tr>
                            <tr>
                              <td>Donna Snider</td>
                              <td>Customer Support</td>
                              <td>New York</td>
                              <td>27</td>
                              <td>2011/01/25</td>
                              <td>$112,000</td>
                            </tr>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                  <!-- TODO: 페이지 시작 / 페이지 네이션 백에서 해야함 -->
                  <div class="row">
                    <div class="col-sm-12 col-md-5">
                      <div
                        class="dataTables_info"
                        role="status"
                        aria-live="polite"
                      >
                        검색결과 총 ? 건 중 ? 건
                      </div>
                    </div>
                    <div class="col-sm-12 col-md-7">
                      <div class="dataTables_paginate paging_Simple_numbers">
                        <ul class="pagination justify-content-end">
                          <li
                            class="paginate_button page-item previous disabled"
                          >
                            <a
                              href="#"
                              aria-controls="dataTable"
                              data-dt-idx="0"
                              tabindex="0"
                              class="page-link"
                              >이전</a
                            >
                          </li>
                          <li class="page-item">
                            <a
                              href="#"
                              aria-controls="dataTable"
                              data-dt-idx="1"
                              tabindex="0"
                              class="page-link"
                              >1</a
                            >
                          </li>
                          <li class="page-item">
                            <a
                              href="#"
                              aria-controls="dataTable"
                              data-dt-idx="2"
                              tabindex="0"
                              class="page-link"
                              >2</a
                            >
                          </li>
                          <li class="page-item">
                            <a
                              href="#"
                              aria-controls="dataTable"
                              data-dt-idx="3"
                              tabindex="0"
                              class="page-link"
                              >3</a
                            >
                          </li>
                          <li class="page-item">
                            <a
                              href="#"
                              aria-controls="dataTable"
                              data-dt-idx="4"
                              tabindex="0"
                              class="page-link"
                              >다음</a
                            >
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                  <!-- TODO: 페이지 끝 -->
                </div>
              </div>
            </div>
            <!-- /.container-fluid -->
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

    <!-- TODO: 로그아웃 모달 화면 -->
    <div
      class="modal fade"
      id="logoutModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button
              class="close"
              type="button"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">
            Select "Logout" below if you are ready to end your current session.
          </div>
          <div class="modal-footer">
            <button
              class="btn btn-secondary"
              type="button"
              data-dismiss="modal"
            >
              Cancel
            </button>
            <a class="btn btn-primary" href="login.html">Logout</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {};
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
</style>
