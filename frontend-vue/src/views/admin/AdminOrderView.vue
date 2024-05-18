<template>
  <div>
    <body id="page-top">
      <div id="wrapper">
        <ul
          class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
          id="accordionSidebar"
        >
          <a
            class="sidebar-brand d-flex align-items-center justify-content-center"
            href="/admin-product"
          >
            <div class="sidebar-brand-icon rotate-n-15"></div>
            <div class="sidebar-brand-text mx-3 logo">서울쥐</div>
          </a>
          <hr class="sidebar-divider my-0" />
          <li class="nav-item active">
            <a class="nav-link" href="/admin-product">
              <i class="fas fa-fw fa-tachometer-alt"></i>
              <span>처음으로</span></a
            >
          </li>
          <hr class="sidebar-divider" />
          <li class="nav-item">
            <a class="nav-link" href="/admin-order">
              <i class="fas fa-fw fa-table"></i>
              <span>주문 관리</span></a
            >
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/admin-product">
              <i class="fas fa-fw fa-table"></i>
              <span>상품 관리</span></a
            >
          </li>
          <hr class="sidebar-divider" />
          <li class="nav-item">
            <a class="nav-link" href="/admin-coupon">
              <i class="fas fa-fw fa-table"></i>
              <span>쿠폰관리</span></a
            >
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/admin-chart">
              <i class="fas fa-fw fa-chart-area"></i>
              <span>통계</span></a
            >
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/admin-pdqna">
              <i class="fas fa-fw fa-table"></i>
              <span>문의관리</span></a
            >
          </li>
        </ul>

        <div id="content-wrapper" class="d-flex flex-column">
          <div id="content">
            <nav
              class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow"
            >
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

            <div class="container-fluid">
              <h1 class="h3 mb-2 text-gray-800">주문관리</h1>
              <br />
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
                                <td>
                                  <router-link
                                    :to="`/admin-order/${order.orderId}`"
                                  >
                                    {{ order.orderId }}
                                  </router-link>
                                </td>
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
                  </div>
                </div>
              </div>
              <!-- /.container-fluid -->
            </div>
          </div>
          <!-- TODO: 게시판 끝 -->

          <footer class="sticky-footer bg-white">
            <div class="container my-auto">
              <div class="logo text-center my-auto">
                <span>서울쥐</span>
              </div>
            </div>
          </footer>
        </div>
        <!-- End of Content Wrapper -->
      </div>
      <!-- End of Page Wrapper -->

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
