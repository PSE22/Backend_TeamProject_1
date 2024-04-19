<template>
  <div>
    <MyPageMain />
    <h1 style="margin-top: 100px; margin-bottom: 20px">나의 리뷰활동</h1>
    <div class="row">
      <table class="table">
        <thead class="table-light text-center">
          <tr>
            <th scope="col">상품정보</th>
            <th scope="col">별점</th>
            <th scope="col">내용</th>
            <th scope="col">등록일</th>
          </tr>
        </thead>
        <tbody class="table-group-divider align-middle">
          <tr>
            <td class="col-4">
              <div class="d-flex align-items-center text-start">
                <div class="flex-shrink-0">
                  <img
                    src="https://via.placeholder.com/100x100?text=Image"
                    class="img-thumbnail me-3"
                  />
                </div>
                <div class="flex-grow-1">
                  This is some content from a media component. This is some
                  content from a media component.
                </div>
              </div>
            </td>
            <td class="col-1 text-center">⭐️⭐️⭐️⭐️⭐️</td>
            <td class="col-4">
              <p>
                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Amet
                voluptate magnam officia, odio repudiandae possimus impedit
                dolor porro quis sed ex quisquam ullam qui inventore deserunt
                velit aut dolores error.
              </p>
              <img
                src="https://via.placeholder.com/70x70?text=Image"
                class="img-thumbnail me-3"
              />
              <img
                src="https://via.placeholder.com/70x70?text=Image"
                class="img-thumbnail me-3"
              />
              <img
                src="https://via.placeholder.com/70x70?text=Image"
                class="img-thumbnail me-3"
              />
            </td>
            <td class="col-1 text-center">24/01/01</td>
          </tr>
        </tbody>
      </table>
    </div>
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
</style>
