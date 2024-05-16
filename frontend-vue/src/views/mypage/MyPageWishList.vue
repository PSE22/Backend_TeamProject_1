<template>
  <!-- MyPageWishList.vue -->
  <div class="mypage-layout">
    <MyPageMainMenu />
    <div class="mypage-contents">
      <div class="mypage-backcolor">
        <div class="mypage-wish-maintitle">나의 위시리스트</div>
        <div class="tabList"><p class="mypage-wish-subtitle">찜 목록</p></div>
        <div class="mypage-product-box">
          <div class="form-check">
            <div class="check-all"> <!-- 수정된 부분 -->
              <input
                class="form-check-input"
                type="checkbox"
                v-model="selectAll"
                @change="toggleAll"
              />
              <label class="form-check-label">전체선택</label> <!-- 수정된 부분 -->
            </div>
            <button type="button" @click="deleteSelectedItems">선택삭제</button>
          </div>
          <div class="row row-cols-1 row-cols-md-4 g-4">
            <div class="col" v-for="item in wishlist" :key="item.pdId">
              <div class="card h-100">
                <input
                  class="form-check-input check-item"
                  type="checkbox"
                  :value="item.pdId"
                  v-model="selectedItems"
                />
                <router-link :to="`/product/${item.pdId}`"  class="link-custom" >
                <img :src="item.pdThumbnail" class="card-img-top" alt="...">
              </router-link>
                <div class="card-body">
                  <router-link :to="`/product/${item.pdId}`"  class="link-custom">
                  <h5 class="card-title">{{ item.pdName }}</h5>
                </router-link>
                  <p class="card-text">{{ item.pdPrice }} 원</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import MyPageMainMenu from "@/components/mypage/MyPageMainMenu.vue";
import MyWishListService from "@/services/mypage/MyWishListService";

export default {
  components: {
    MyPageMainMenu,
  },
  data() {
    return {
      wishlist: [],
      selectedItems: [],
      selectAll: false,
    };
  },
  methods: {
    async retrieveWishList() {
      try {
        const response = await MyWishListService.getAll(this.$store.state.user.userId);
        this.wishlist = response.data;
      } catch (e) {
        console.error("위시리스트 조회 실패:", e);
      }
    },
    async deleteSelectedItems() {
      const deletions = this.selectedItems.map(pdId =>
        MyWishListService.deleteWishlistItem(this.$store.state.user.userId, pdId)
      );
      try {
        await Promise.all(deletions);
        this.retrieveWishList(); // 삭제 후 위시리스트를 다시 가져옵니다.
        this.selectedItems = []; // 선택된 아이템을 초기화합니다.
      } catch (e) {
        console.error("위시리스트 아이템 삭제 실패:", e);
      }
    },
    toggleAll() {
      this.selectAll ? this.selectedItems = this.wishlist.map(item => item.pdId) : this.selectedItems = [];
    }
  },
  mounted() {
    this.retrieveWishList();
  }
};
</script>

<style>

.link-custom {
  text-decoration: none; /* 밑줄 제거 */
  color: black; /* 텍스트 색상 지정 */
}

@import "@/assets/css/mypage.css";
@import "@/assets/css/mypagewish.css";
</style>