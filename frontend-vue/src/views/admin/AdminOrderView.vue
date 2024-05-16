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
                    placeholder="검색"
                    aria-label="Search"
                    aria-describedby="basic-addon2"
                    v-model="searchOrderId"
                  />
                  <div class="input-group-append">
                    <button
                      class="btn btn-primary"
                      type="button"
                      @click="retrieveAdminOrders"
                    >
                      <i class="fas fa-search fa-sm"></i>
                    </button>
                  </div>
                </div>
              </form>
            </nav>
            <!-- 상단 메뉴 끝 -->

            <!-- TODO: 쿠폰 관리 시작 -->
            <div class="container-fluid">
              <!-- Page Heading -->
              <h1 class="h3 mb-2 text-gray-800">주문관리</h1>
              <br />

              <!-- DataTales Example -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">주문관리</h6>
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
                                <th>주문번호</th>
                                <th>회원 ID</th>
                                <th>결제금액</th>
                                <th>주문상태코드</th>
                                <th>주문일자</th>
                                <th>주문상태 변경일자</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr v-for="(order, index) in orders" :key="index">
                                <!-- <td> -->
                                  <!-- TODO: 링크 : a 태그 (전체 새로고침(성능저하) -> 페이지전환) -->
                                  <!-- TODO: 뷰에서제공 링크 : router-link (부분 새로고침: 성능향상) -->
                                <!-- </td> -->
                                <td>{{ order.orderId }}</td>
                                <td>{{ order.userId }}</td>
                                <td>{{ order.orderPrice }}</td>
                                <td>{{ order.orderCode }}</td>
                                <td>{{ order.addDate }}</td>
                                <td>{{ order.modDate }}</td>
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
                            @click="retrieveAdminOrders"
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
import AdminOrderService from "@/services/admin/AdminOrderService";

export default {
  data() {
    return {
      orders: [], // 주문 목록을 저장할 배열
      searchOrderId: "",

      page: 1,
      count: 0,
      pageSize: 10,

      pageSizes: [10, 25, 50],
    };
  },
  methods: {
    pageNoChange(value) {
      this.page = value;
      this.retrieveAdminOrders();
    },
    pageSizeChange() {
      this.page = 1;
      this.retrieveAdminOrders();
    },
    async retrieveAdminOrders() {
      try {
        let response = await AdminOrderService.getAll(
          this.searchOrderId, 
          this.page - 1,
          this.pageSize 
        );
        const { orders, totalItems } = response.data;
        this.orders = orders;
        this.count = totalItems;
        console.log("전체조회", response.data);
      } catch (error) {
        console.error("Error fetching orders: ", error);
      }
    },
  },
  mounted() {
    this.retrieveAdminOrders();
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
