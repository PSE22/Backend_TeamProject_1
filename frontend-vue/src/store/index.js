import { createStore } from 'vuex'

const user = JSON.parse(localStorage.getItem("user"));

export default createStore({
  state: {
    loggedIn: user ? true : false, // 로그인 여부
    user: user ? user : null, // 로그인 정보(웹토큰속성 있음)
    orderList: [],
    userId: "user1",
  },
  getters: {},
  mutations: {
    setOrderList(state, value) {
      state.orderList = [...value];
    },
    // TODO: 로그인 성공함수
    //  => loggedIn(로그인여부 속성) = true , user(유저 객체) = user 객체 저장
    loginSuccess(state, user) {
      state.loggedIn = true;
      state.user = user;
    },
    // TODO: 로그인 실패함수
    loginFailure(state) {
      state.loggedIn = false;
      state.user = null;
    },
    // TODO: 로그아웃함수
    logout(state) {
      state.loggedIn = false;
      state.user = null;
    },
    // TODO: 회원가입성공함수
    signupSuccess(state) {
      state.loggedIn = false;
    },
    // TODO: 회원가입실패함수
    signupFailure(state) {
      state.loggedIn = false;
    },
  },
  // // TODO: 비동기 함수 (async ~ await)
  // actions: {
  //   // 선택된 상품 목록을 전달하는 액션
  //   setOrderList({ commit }, orderList) {
  //     // 다른 액션을 호출하거나 외부 서비스와 통신하는 등의 작업을 수행할 수 있음
  //     // 여기서는 단순히 커밋(commit)을 사용하여 상태를 변경하지 않고 전달만 함
  //     commit("setOrderList", orderList);
  //   },
  // },
  modules: {},
});
