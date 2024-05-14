<template>
  <div align="center" class="mt-5">
    <h2>회원가입</h2>
  </div>
  <div class="container mt-5 signup-box">
    <div align="center">
      <div class="logo">
        <h2>서울쥐</h2>
      </div>
    </div>
    <!-- 회원가입 폼 -->
    <form class="user" @submit.prevent="handleSignUp">
      <!-- 아이디 -->
      <div align="center">
        <div class="row">
          <div class="col-9">
            <label for="userId"></label>
            <input
              id="inputId"
              type="text"
              class="form-control"
              placeholder="아이디"
              name="userId"
              v-model="user.userId"
            />
          </div>

          <div class="col align-self-end">
            <div align="right">
              <button
                type="button"
                id="idcheck"
                class="btn btn-secondary"
                @click="heckUserId"
              >
                중복확인
              </button>
            </div>
          </div>
        </div>
      </div>
      <!-- 비밀번호 -->
      <div align="center">
        <div class="row">
          <div class="form-group col">
            <label for="userPw"></label>
            <input
              type="password"
              class="form-control"
              placeholder="비밀번호"
              name="userPw"
              v-model="user.userPw"
            />
          </div>
        </div>
      </div>

      <div align="center">
        <div class="row">
          <div class="form-group col">
            <label for="userPw"></label>
            <input
              type="password"
              class="form-control"
              placeholder="비밀번호 재확인"
              name="userPw"
              v-model="user.rePw"
            />
          </div>
        </div>
      </div>
      <!-- 비밀번호 일치 여부를 표시하는 메시지 -->
      <div v-if="!isPasswordMatch">비밀번호가 일치하지 않습니다.</div>

      <!-- 이름 -->
      <div align="center">
        <div class="row">
          <div class="form-group col">
            <label for="userName"></label>
            <input
              type="text"
              class="form-control"
              placeholder="이름"
              name="userName"
              v-model="user.userName"
            />
          </div>
        </div>
      </div>

      <!-- 성별 -->
      <div class="form-check form-check-inline mt-3">
        <input
          class="form-check-input"
          type="radio"
          name="gender"
          value="M"
          v-model="user.gender"
        />
        <label class="form-check-label" for="inlineRadio1">남</label>
      </div>
      <div class="form-check form-check-inline">
        <input
          class="form-check-input"
          type="radio"
          name="gender"
          value="F"
          v-model="user.gender"
        />
        <label class="form-check-label" for="inlineRadio2">여</label>
      </div>
      <!-- 이메일 -->
      <div align="center">
        <div class="row">
          <div class="col">
            <label for="userEmail"></label>
            <input
              type="text"
              class="form-control"
              placeholder="이메일"
              name="userEmail"
              v-model="user.userEmail"
            />
          </div>
        </div>
      </div>
      <br />
      <!-- 주소 -->
      <div align="center">
        <div class="row">
          <div class="col">
            <label for="userEmail"></label>
            <div class="input-group">
              <input
                type="text"
                name="zipcode"
                id="zipcode"
                class="form-control"
                value=""
                placeholder="우편번호"
                readonly
              />
              <button
                class="btn btn-primary btn-sm"
                type="button"
                @click="showApi"
              >
                주소검색
              </button>
            </div>
          </div>
        </div>
        <div class="row mt-2">
          <div class="col">
            <input
              type="text"
              name="roadaddress"
              id="roadaddress"
              class="form-control"
              value=""
              placeholder="도로명주소"
              readonly
            />
          </div>
        </div>
        <div class="row mt-2">
          <div class="col">
            <input
              type="text"
              name="detailaddress"
              id="detailaddress"
              class="form-control"
              value=""
              placeholder="상세주소"
            />
          </div>
        </div>
      </div>

      <!-- 휴대폰 번호 -->
      <div align="center">
        <div class="row">
          <div class="col-10">
            <label for="userPhone"></label>
            <input
              type="text"
              class="form-control"
              placeholder="휴대폰 번호"
              name="userPhone"
              v-model="user.userPhone"
            />
          </div>

          <div class="col align-self-end">
            <div align="right">
              <button type="button" id="phonecheck" class="btn btn-secondary">
                인증
              </button>
            </div>
          </div>
        </div>
      </div>
      <!-- 광고수신동의 -->
      광고 수신 동의 :
      <div class="form-check form-check-inline mt-3">
        <input
          class="form-check-input"
          type="radio"
          name="userPromo"
          value="Y"
          v-model="user.userPromo"
        />
        <label class="form-check-label" for="promoYes">동의</label>
      </div>
      <div class="form-check form-check-inline">
        <input
          class="form-check-input"
          type="radio"
          name="userPromo"
          value="N"
          v-model="user.userPromo"
        />
        <label class="form-check-label" for="promoNo">미동의</label>
      </div>

      <!-- 계정분류 라디오버튼 삭제 - 김태완 -->

      <!-- 회원가입 -->
      <br />
      <div align="center">
        <button
          type="button"
          class="btn btn-primary btn-user mt-3 col-12"
          @click="handleSignUp"
        >
          회원가입
        </button>
      </div>
      <router-view />
    </form>
    <p v-if="message" class="alert alert-success" role="alert">
      {{ message }}
    </p>
  </div>
</template>
<script>
import LoginService from "@/services/login/LoginService";

export default {
  // 데이터 바인딩
  data() {
    return {
      openDaumAddrApi: {
        addressName: "기본배송지",
        zipcode: "", // 우편번호
        address: "", // 기본주소
        roadAddress: "", // 도로명 주소
        roadAddressEnglish: "", //영문 도로명 주소
        jibunAddress: "", // 지번 주소
        jibunAddressEnglish: "", // 영문 지번 주소
      },
      user: {
        userId: "",
        userPw: "",
        rePw: "",
        userName: "",
        userBirth: "",
        gender: "",
        userEmail: "",
        userPhone: "",
        userPromo: "",
        userCode: "AT02", // 계정분류코드 고정 - 김태완
      },
      message: "", // 성공메세지 화면 출력속성
    };
  },
  // TODO: 함수 정의
  methods: {
    showApi() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          let fullRoadAddr = data.roadAddress; // 도로명 주소 변수
          let extraRoadAddr = ""; // 도로명 조합형 주소 변수

          // 법정동명이 있을 경우 추가한다. (법정리는 제외)
          // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
          if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
            extraRoadAddr += data.bname;
          }
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if (data.buildingName !== "" && data.apartment === "Y") {
            extraRoadAddr +=
              extraRoadAddr !== ""
                ? ", " + data.buildingName
                : data.buildingName;
          }
          // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
          if (extraRoadAddr !== "") {
            extraRoadAddr = " (" + extraRoadAddr + ")";
          }
          // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
          if (fullRoadAddr !== "") {
            fullRoadAddr += extraRoadAddr;
          }
          document.getElementById("zipcode").value = data.zonecode;
          document.getElementById("roadaddress").value = data.roadAddress;
        },
      }).open({
      popupName: 'postcodePopup'
    });
    },
    async heckUserId() {
      var userId = this.user.userId.trim();
      if (userId.length < 8 || !/^[a-zA-Z0-9]+$/.test(userId)) {
        alert("아이디는 영문자와 숫자 조합의 8자리 이상이어야 합니다.");
        return;
      }
      this.failMessage = "";
      this.successMessage = "";
      try {
        this.successMessage = ""; // 성공 메시지 초기화
        let response = await LoginService.reId(this.user.userId); // user.userId만 전달
        alert("사용 가능한 아이디입니다."); // 성공 메시지 알림창 표시
        return response.data;
      } catch (e) {
        this.failMessage = ""; // 실패 메시지 초기화
        alert("중복된 아이디 입니다."); // 실패 메시지 알림창 표시
        console.log(e);
      }
    },
    async handleSignUp() {
      this.message = "";
      try {
        // TODO: 공통 사용자등록 서비스 함수 실행
        let response = await LoginService.signup(this.user);
        // 공유저장소의 signUp 성공함수 실행
        this.$store.commit("signUpSuccess");
        this.message = "사용자가 등록되었습니다.";
        this.$router.push("/api/login"); // 회원가입 성공시 로그인 페이지로 이동 - 김태완
        // 로깅
        console.log(response.data);
      } catch (e) {
        // 공유저장소의 signUp 실패함수 실행
        this.$store.commit("signupFailure");
        this.message = "에러 : " + e;
        console.log(e);
      }
    },
  },
  computed: {
    isPasswordMatch() {
      return this.user.userPw === this.user.rePw;
    },
  },
};
</script>
<style>
.signup-box {
  margin: 100px auto;
  background-color: rgba(255, 255, 255, 1);
  padding: 40px 30px;
  border: 3px solid #505050;
  width: 600px;
  height: 900px;
}
.a1 {
  position: relative;
}
.a {
  position: absolute;
  top: 50%;
}

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
</style>
