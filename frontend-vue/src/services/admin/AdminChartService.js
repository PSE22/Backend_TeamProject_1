import http from "@/utils/http-common";
import LoginHeader from "../login/LoginHeader";

class AdminChartService {
    getDailyOrderStats(selectedMonth) {
        return http.get(`/admin/dailyOrderStats?odStatDate=${selectedMonth}`, {
            headers: LoginHeader()
        });
    }

    getMonthlyOrderStats(selectedYear) {
        return http.get(`/admin/monthlyOrderStats?odStatDate=${selectedYear}`, {
            headers: LoginHeader()
        });
    }

    getYearlyOrderStats() {
        return http.get(`/admin/yearlyOrderStats`, {
            headers: LoginHeader()
        });
    }
}

export default new AdminChartService();