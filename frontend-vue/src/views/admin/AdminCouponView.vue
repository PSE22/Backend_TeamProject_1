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
                    v-model="searchCpName"
                  />
                  <div class="input-group-append">
                    <button class="btn btn-primary" type="button" @click="retrieveAdminCoupon">
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
              <h1 class="h3 mb-2 text-gray-800">쿠폰관리</h1>
              <br />

              <!-- DataTales Example -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">쿠폰관리</h6>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <div v-if="!submitted">
                      <!-- TODO: 등록 시작 -->
                      <button
                        type="button"
                        class="btn btn-primary mr-3 mb-3"
                        data-bs-toggle="modal"
                        :data-bs-target="'#exampleModal-1'"
                      >
                        등록
                      </button>

                      <!-- 등록 모달 -->
                      <div class="modal fade" id="exampleModal-1" tabindex="-1">
                        <div class="modal-dialog">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h1
                                class="modal-title fs-5"
                                id="exampleModalLabel"
                              >
                                쿠폰 등록
                              </h1>
                              <button
                                type="button"
                                class="btn-close"
                                data-bs-dismiss="modal"
                              ></button>
                            </div>
                            <!-- 등록 모달 내 목록 -->
                            <div class="modal-body">
                              <!-- 상품번호 -->
                              <h3 class="fs-5 mt-1">상품번호</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="상품번호을 입력하세요"
                                name="text"
                                v-model="adminCouponData.pdId"
                              />
                              <!-- 쿠폰명 -->
                              <h3 class="fs-5 mt-1">쿠폰명</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="쿠폰명을 입력하세요"
                                name="text"
                                v-model="adminCouponData.cpName"
                              />
                              <!-- 할인금액 -->
                              <h3 class="fs-5 mt-3">할인금액</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="할인금액을 입력하세요"
                                name="text"
                                v-model="adminCouponData.cpDcPrice"
                              />
                              <!-- 할인율 -->
                              <h3 class="fs-5 mt-3">할인율</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="할인율을 입력하세요"
                                name="text"
                                v-model="adminCouponData.cpDcRate"
                              />
                              <!-- 최소사용금액 -->
                              <h3 class="fs-5 mt-3">최소사용금액</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="최소사용금액을 입력하세요"
                                name="text"
                                v-model="adminCouponData.cpMinPrice"
                              />
                              <!-- 최대할인금액 -->
                              <h3 class="fs-5 mt-3">최대할인금액</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="최대할인금액을 입력하세요"
                                name="text"
                                v-model="adminCouponData.cpMaxDcPrice"
                              />
                              <!-- 만료일 -->
                              <h3 class="fs-5 mt-3">만료일</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="만료일을 입력하세요"
                                name="text"
                                v-model="adminCouponData.cpExpireDate"
                              />
                              <!-- 상태 확인 시작 -->
                              <h3 class="fs-5 mt-3">상태</h3>
                              <div class="form-check form-check-inline mt-3">
                                <label
                                  for="form-check-label"
                                  class="inlineRadio1"
                                ></label>
                                <input
                                  type="radio"
                                  class="form-check-input mb-4"
                                  name="Y"
                                  value="활성"
                                  v-model="adminCouponData.status"
                                />
                                <label
                                  class="form-check-label"
                                  for="inlineRadio1"
                                  >활성</label
                                >
                              </div>
                              <div class="form-check form-check-inline">
                                <label
                                  for="form-check-label"
                                  class="inlineRadio2"
                                ></label>
                                <input
                                  type="radio"
                                  class="form-check-input mb-4"
                                  name="N"
                                  value="비활성"
                                  v-model="adminCouponData.status"
                                />
                                <label
                                  class="form-check-label"
                                  for="inlineRadio2"
                                  >비활성</label
                                >
                              </div>
                              <!-- 상태 확인 끝 -->
                            </div>
                            <div class="modal-footer">
                              <button
                                type="submit"
                                class="btn btn-primary"
                                @click="saveAdminCouponData"
                              >
                                등록
                              </button>
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
                      <!-- 등록 모달 끝 -->

                      <!-- TODO: 등록 끝 -->
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
                                <th>쿠폰번호</th>
                                <th>상품번호</th>
                                <th>쿠폰명</th>
                                <th>할인금액</th>
                                <th>할인율</th>
                                <th>최소사용금액</th>
                                <th>최대할인금액</th>
                                <th>만료일</th>
                                <th>저장일</th>
                                <th>수정일</th>
                                <th>삭제일</th>
                                <th>상태</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr
                                v-for="(data, index) in adminCoupon"
                                :key="index"
                              >
                                <td>
                                  <router-link
                                    :to="'/admin-coupon-edit/' + data.cpId"
                                  >
                                    <span class="badge text-bg-secondary"
                                      >수정</span>
                                  </router-link>
                                </td>
                                <td>{{ data.cpId }}</td>
                                <td>{{ data.pdId }}</td>
                                <td>{{ data.cpName }}</td>
                                <td>{{ data.cpDcPrice }}</td>
                                <td>{{ data.cpDcRate }}</td>
                                <td>{{ data.cpMinPrice }}</td>
                                <td>{{ data.cpMaxDcPrice }}</td>
                                <td>{{ data.cpExpireDate }}</td>
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
                            검색결과 총 {{ count }} 건
                        </div>
                      </div>
                      <div class="col-sm-12 col-md-7">
                        <div class="dataTables_paginate paging_Simple_numbers">
                          <b-pagination
                            v-model="page"
                            :total-rows="count"
                            :per-page="pageSize"
                            @click="retrieveAdminCoupon"
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
import AdminCouponService from "@/services/admin/AdminCouponService";
export default {
  data() {
    return {
      // TODO: 등록
      adminCouponData: {},
      submitted: false,

      // TODO: 백엔드 연결
      adminCoupon: [],
      searchCpName: "",
      page: 1,
      count: 0,
      pageSize: 10,

      pageSizes: [10, 25, 50],
    };
  },
  methods: {
    // TODO: 등록 시작
    async saveAdminCouponData() {
      try {
        let data = {
          cpId: this.adminCouponData.cpId,
          pdId: this.adminCouponData.pdId,
          cpName: this.adminCouponData.cpName,
          cpDcPrice: this.adminCouponData.cpDcPrice,
          cpDcRate: this.adminCouponData.cpDcRate,
          cpMinPrice: this.adminCouponData.cpMinPrice,
          cpMaxDcPrice: this.adminCouponData.cpMaxDcPrice,
          cpExpireDate: this.adminCouponData.cpExpireDate,
          addDate: this.adminCouponData.addDate,
          modDate: this.adminCouponData.modDate,
          delDate: this.adminCouponData.delDate,
          status: this.adminCouponData.status,
        };
        console.log(data);
        // TODO: 벡엔드로 객체 추가 요청
        let response = await AdminCouponService.create(data);
        // TODO: 콘솔에 결과 출력
        console.log(response);
        this.submitted = true;
      } catch (e) {
        console.log(e);
      }
    },
    newAdminCouponData() {
      this.submitted = false;
      this.adminCouponData = {};
    },
    // TODO: 등록 끝
    // TODO: 백엔드 연결
    pageNoChange(value) {
      this.page = value;
      this.retrieveAdminCoupon();
    },
    pageSizeChange() {
      this.page = 1;
      this.retrieveAdminCoupon();
    },
    async retrieveAdminCoupon() {
      try {
        let response = await AdminCouponService.getAll(
          this.searchCpName,
          this.page - 1,
          this.pageSize
        );
        const { adminCoupon, totalItems } = response.data;
        this.adminCoupon = adminCoupon;
        this.count = totalItems;
        console.log("전체조회", response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // TODO: 백엔드 끝
    // TODO: 백엔드 시작
  },
  mounted() {
    this.retrieveAdminCoupon();
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
