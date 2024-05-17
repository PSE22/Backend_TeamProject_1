<template>
  <div>
    <body id="page-top">
      <div id="wrapper">
        <!-- Sidebar -->
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
            <a class="nav-link" href="/admin-pdqna
            ">
              <i class="fas fa-fw fa-table"></i>
              <span>문의관리</span></a
            >
          </li>
        </ul>
        <div id="content-wrapper" class="d-flex flex-column">
          <div id="content">
            <!-- Topbar -->
            <nav
              class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow"
            >
              <!-- Topbar Search -->
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
            </nav>
            <!-- End of Topbar -->
            <div class="container-fluid">
              <h1 class="h3 mb-2 text-gray-800">통계</h1>
              <div class="row">
                <div class="col-xl-8 col-lg-7">
                  <!-- Daily Sales Chart -->
                  <div class="col-xl-12 col-lg-12">
                    <div class="card shadow mb-4">
                      <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">
                          일간 통계
                        </h6>
                        <input
                          type="month"
                          v-model="selectedMonth"
                          @change="fetchStats"
                        />
                      </div>
                      <div class="card-body">
                        <canvas
                          id="dailyChart"
                          width="100%"
                          height="50"
                        ></canvas>
                      </div>
                    </div>
                  </div>
                  <!-- Monthly Sales Chart -->
                  <div class="col-xl-12 col-lg-12">
                    <div class="card shadow mb-4">
                      <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">
                          월간 통계
                        </h6>
                        <select
                          v-model="selectedYear"
                          @change="fetchStats"
                          class="form-control"
                        >
                          <option
                            v-for="year in availableYears"
                            :value="year"
                            :key="year"
                          >
                            {{ year }}년
                          </option>
                        </select>
                      </div>
                      <div class="card-body">
                        <canvas
                          id="monthlyChart"
                          width="100%"
                          height="50"
                        ></canvas>
                      </div>
                    </div>
                  </div>

                  <!-- Yearly Sales Chart -->
                  <div class="col-xl-12 col-lg-12">
                    <div class="card shadow mb-4">
                      <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">
                          연간 통계
                        </h6>
                      </div>
                      <div class="card-body">
                        <canvas
                          id="yearlyChart"
                          width="1000%"
                          height="600px"
                        ></canvas>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <footer class="sticky-footer bg-white">
            <div class="container my-auto">
              <div class="logo text-center my-auto">
                <span>서울쥐</span>
              </div>
            </div>
          </footer>
        </div>
      </div>
      <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
      </a>
    </body>
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
import AdminChartService from "@/services/admin/AdminChartService";
import Chart from "chart.js/auto";

export default {
  data() {
    return {
      dailyStats: {
        date: [],
        orders: [],
        sales: [],
      },
      monthlyStats: {
        date: [],
        orders: [],
        sales: [],
      },
      yearlyStats: {
        date: [],
        orders: [],
        sales: [],
      },
      selectedMonth: new Date().toISOString().substr(0, 7), // YYYY-MM
      selectedYear: new Date().getFullYear(), // YYYY
      availableYears: [],
    };
  },
  methods: {
    generateAvailableYears() {
      const currentYear = new Date().getFullYear();
      const years = [];
      for (let year = currentYear; year >= currentYear - 10; year--) {
        years.push(year);
      }
      this.availableYears = years;
    },
    fetchStats() {
      // 일간 통계 가져오기
      AdminChartService.getDailyOrderStats(this.selectedMonth)
        .then((response) => {
          this.dailyStats.date = response.data.map((stat) => stat.odStatDate);
          this.dailyStats.orders = response.data.map(
            (stat) => stat.dailyOrderCnt
          );
          this.dailyStats.sales = response.data.map((stat) => stat.dailySales);
          this.drawDualChart(
            "dailyChart",
            this.dailyStats.date,
            this.dailyStats.orders,
            this.dailyStats.sales
          );
        })
        .catch((error) => {
          console.error("Error fetching daily stats:", error);
        });

      // 월간 통계 가져오기
      AdminChartService.getMonthlyOrderStats(this.selectedYear)
        .then((response) => {
          this.monthlyStats.date = response.data.map((stat) => stat.odStatDate);
          this.monthlyStats.orders = response.data.map(
            (stat) => stat.monthlyOrderCnt
          );
          this.monthlyStats.sales = response.data.map(
            (stat) => stat.monthlySales
          );
          this.drawDualChart(
            "monthlyChart",
            this.monthlyStats.date,
            this.monthlyStats.orders,
            this.monthlyStats.sales
          );
        })
        .catch((error) => {
          console.error("Error fetching monthly stats:", error);
        });

      // 연간 통계 가져오기
      AdminChartService.getYearlyOrderStats()
        .then((response) => {
          this.yearlyStats.date = response.data.map((stat) => stat.odStatDate);
          this.yearlyStats.orders = response.data.map(
            (stat) => stat.yearlyOrderCnt
          );
          this.yearlyStats.sales = response.data.map(
            (stat) => stat.yearlySales
          );
          this.drawDualChart(
            "yearlyChart",
            this.yearlyStats.date,
            this.yearlyStats.orders,
            this.yearlyStats.sales
          );
        })
        .catch((error) => {
          console.error("Error fetching yearly stats:", error);
        });
    },
    drawDualChart(chartId, statDate, orderData, salesData) {
      const existingChart = Chart.getChart(chartId);
      if (existingChart) {
        existingChart.destroy();
      }

      new Chart(chartId, {
        type: "bar",
        data: {
          labels: this.generateLabels(statDate),
          datasets: [
            {
              label: "주문량",
              data: orderData,
              backgroundColor: "rgb(54, 162, 235)",
              yAxisID: "order-y-axis",
            },
            {
              label: "매출",
              data: salesData,
              borderColor: "rgb(255, 99, 132)",
              type: "line",
              yAxisID: "sales-y-axis",
            },
          ],
        },
        options: {
          scales: {
            x: {
              display: true,
              scaleLabel: {
                display: true,
                labelString: "일",
              },
            },
            "order-y-axis": {
              type: "linear",
              display: true,
              position: "left",
              id: "order-y-axis",
              scaleLabel: {
                display: true,
                labelString: "주문량",
              },
            },
            "sales-y-axis": {
              type: "linear",
              display: true,
              position: "right",
              id: "sales-y-axis",
              grid: {
                drawOnChartArea: false,
              },
              scaleLabel: {
                display: true,
                labelString: "매출",
              },
            },
          },
        },
      });
    },
    generateLabels(statDate) {
      const labels = [];
      for (const key in statDate) {
        labels.push(statDate[key]);
      }
      return labels;
    },
  },
  mounted() {
    this.generateAvailableYears();
    this.fetchStats();
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
</style>
