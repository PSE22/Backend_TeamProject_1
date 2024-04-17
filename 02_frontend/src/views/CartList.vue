// 장바구니
<template>
  <div class="container">
    <div>
      <h1>장바구니</h1>
    </div>
    <div>
      <table class="table">
        <thead class="table-light">
          <tr>
            <th scope="col">
              <div class="form-check">
                <input
                  class="form-check-input"
                  type="checkbox"
                  value=""
                  id="flexCheckDefault"
                />
                <label class="form-check-label" for="flexCheckDefault"></label>
              </div>
            </th>
            <th scope="col">상품정보</th>
            <th scope="col">판매가</th>
            <th scope="col">수량</th>
          </tr>
        </thead>
        <tbody class="table-group-divider">
          <tr>
            <th scope="row">1</th>
            <td>Mark</td>
            <td>Otto</td>
            <td>@mdo</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
        cart: []  // 장바구니 객체배열
    }
  },
};
</script>

<style></style>
