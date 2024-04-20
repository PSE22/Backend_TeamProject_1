<template>
  <div>
    <MyPageMain />
    <h1 style="margin-top: 100px; margin-bottom: 20px">1:1 문의내역</h1>
    <div class="row">
      <table class="table">
        <thead class="table-light ">
          <tr>
            <th scope="col">제목</th>
            <th scope="col">내용</th>
            <th scope="col">등록일</th>
            <th scope="col">상태</th>
          </tr>
        </thead>
        <tbody class="table-group-divider">
          <tr>
            <td>Mark</td>
            <td>Otto</td>
            <td>@mdo</td>
            <td>@mdo</td>
          </tr>
        </tbody>
      </table>
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
</style>
