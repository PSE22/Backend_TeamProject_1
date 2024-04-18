<template>
  <div>
    <MyPageMain />
    <h1 style="margin-top: 100px; margin-bottom: 20px">쿠폰함</h1>
    <div class="row">
      <table class="table">
        <thead class="table-light">
          <tr>
            <th scope="col">쿠폰명</th>
            <th scope="col">혜택</th>
            <th scope="col">사용조건</th>
            <th scope="col">사용기간</th>
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
    <!-- 쿠폰 안내 -->
    <div>
      <p>• 발급받으신 쿠폰을 사용하시는 경우 다운로드 후 주문/결제 시 적용하실 수 있습니다.</p>
      <p>• 사용기간이 만료되거나 사용한 쿠폰은 보유 목록에서 자동으로 삭제됩니다.</p>
      <p>• 주문/취소 시 이용기간이 남아 있는 쿠폰인 경우 재발급됩니다.</p>
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
