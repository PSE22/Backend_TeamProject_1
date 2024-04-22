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
            <th scope="col">상태</th>
          </tr>
        </thead>
        <tbody class="table-group-divider align-middle">
          <tr>
            <td class="col-4">
              <router-link to="/" class="router-link ms-3 qna-link"
                >1:1 문의 제목</router-link
              >
            </td>
            <td class="col-1 text-center">Y/N</td>
            <td class="col-2 text-center">24/01/01</td>
            <td class="col-2 text-center">완료</td>
          </tr>
        </tbody>
      </table>
    </div>
    <!-- 페이징 번호 -->
      <div class="row ">
        <b-pagination
          class="col-12 mb-3 d-flex justify-content-center"
          v-model="page"
          :total-rows="count"
          :per-page="pageSize"
          @click="retrieveDept"
        ></b-pagination>
      </div>
  </div>
</template>
<script>
import MyPageMain from "@/components/mypage/MyPageMainMenu.vue";
export default {
  components: {
    MyPageMain,
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