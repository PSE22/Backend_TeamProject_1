<template>
  <div align="center" class="mt-5">
    <h2>회원정보수정</h2>
  </div>
  <div class="container mt-5 signup-box">
    <div align="center">
      <div class="logo">
        <h2>서울쥐</h2>
      </div>
    </div>
    <!-- 회원정보수정 폼 -->
    <div v-if="user">
      <!-- 아이디 -->
      <div align="center">
        <div class="row">
          <div class="col">
            <label for="userId"></label>
            <input
              id="inputId"
              type="text"
              class="form-control"
              placeholder="아이디"
              name="userId"
              disabled
              v-model="user.userId"
            />
          </div>
        </div>
      </div>
      <!-- 비밀번호 변경 -->
      <div align="center">
        <div class="row">
          <div class="form-group col">
            <label for="userPw"></label>
            <input
              type="password"
              class="form-control"
              placeholder="기존 비밀번호 확인"
              name="userPw"
              v-model="userPw"
            />
            <label for="newPassword"></label>
            <input
              type="password"
              class="form-control"
              placeholder="새로운 비밀번호"
              name="newPassword"
              v-model="newPassword"
            />
            <label for="newPasswordConfirm"></label>
            <input
              type="password"
              class="form-control"
              placeholder="비밀번호 재확인"
              name="newPasswordConfirm"
              v-model="newPasswordConfirm"
            />
          </div>
        </div>
      </div>

      <!-- 비밀번호 일치 여부를 표시하는 메시지 -->
      <div v-if="!isNewPasswordMatch">비밀번호가 일치하지 않습니다.</div>

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
            <label for="userEmail" class="mb-3">기본배송지</label>
            <div class="input-group">
              <input
                type="text"
                name="zipcode"
                id="zipcode"
                class="form-control"
                value=""
                placeholder="우편번호"
                v-model="orderPostcode"
                readonly
              />
              <button
                class="btn btn-primary btn-sm"
                type="button"
                @click="execDaumPostcode"
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
          <div class="col">
            <label for="userPhone"></label>
            <input
              type="text"
              class="form-control"
              placeholder="휴대폰 번호"
              name="userPhone"
              v-model="user.userPhone"
            />
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

      <!-- 권한 -->
      <div align="center">
        <div class="row">
          <div class="col">
            <label for="userCode"></label>
            <input
              type="text"
              class="form-control"
              placeholder="선택 없음"
              name="userCode"
              disabled
              v-model="user.userCode"
            />
          </div>
        </div>
      </div>

      <!-- 회원가입 -->
      <br />
      <div align="center">
        <div class="row g-3 mt-2 mb-3">
          <button
            type="submit"
            class="btn btn-outline-primary ms-2 col"
            @click="updateProfile"
          >
            회원정보수정
          </button>

          <!-- TODO: 탈퇴 시작 -->
          <button
            type="button"
            class="btn btn-outline-danger ms-3 col"
            data-bs-toggle="modal"
            :data-bs-target="'#exampleModal-1'"
          >
            회원탈퇴
          </button>

          <!-- 탈퇴 모달 -->
          <div class="modal fade" id="exampleModal-1" tabindex="-1">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h1 class="modal-title fs-5" id="exampleModalLabel">
                    회원 탈퇴
                  </h1>
                  <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="modal"
                  ></button>
                </div>
                <!-- 탈퇴 모달 내 목록 -->
                <div class="modal-body">
                  <!-- 회원아이디 -->
                  <h3 class="fs-5 mt-1">회원아이디</h3>
                  <label for="text" class="form-label"></label>
                  <input
                    disabled
                    type="text"
                    class="form-control mb-4"
                    name="text"
                    v-model="user.userId"
                  />
                  <!-- 비밀번호 -->
                  <h3 class="fs-5 mt-1">비밀번호</h3>
                  <label for="text" class="form-label"></label>
                  <input
                    type="text"
                    class="form-control mb-4"
                    placeholder="비밀번호를 입력하세요"
                    name="text"
                    v-model="userPwData.userPw"
                  />
                </div>
                <div class="modal-footer">
                  <button
                    type="submit"
                    class="btn btn-primary"
                    @click="withdrawUser"
                  >
                    탈퇴
                  </button>
                  <button
                    type="button"
                    class="btn btn-secondary"
                    data-bs-dismiss="modal"
                  >
                    취소
                  </button>
                </div>
              </div>
            </div>
          </div>
          <!-- 탈퇴 모달 끝 -->

          <!-- TODO: 탈퇴 끝 -->
        </div>
      </div>
    </div>
    <router-view />
    <p v-if="message" class="alert alert-success" role="alert">
      {{ message }}
    </p>
  </div>
</template>
<script>
import MyEditProfile from "@/services/mypage/MyEditProfile";
import OrderService from '@/services/shop/OrderService';

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
      user: null, // 초기값
      message: "", // 성공메세지 화면 출력속성
      // 비밀번호
      userPw: "", // 기존 비밀번호
      newPassword: "", // 새로운 비밀번호
      newPasswordConfirm: "", // 비밀번호 재확인
      userCode: "AT02",
      userPwData: {
        userId: this.$store.state.user.userId,
      },
      shipAddress: {},
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
        popupName: "postcodePopup",
      });
    },
    async changePassword() {
      // 새로운 비밀번호와 비밀번호 재확인이 일치하는지 확인
      if (!this.isNewPasswordMatch) {
        alert("새로운 비밀번호가 일치하지 않습니다.");
        return;
      }

      try {
        // 비밀번호 변경 API 호출
        alert("비밀번호가 변경되었습니다.");
      } catch (error) {
        alert("비밀번호 변경에 실패했습니다.");
      }
    },
    async getUser(userId) {
      console.log("아이디 확인", userId);
      try {
        let response = await MyEditProfile.findById(userId);
        this.user = response.data; // spring 결과를 바인딩 속성변수 user 저장
        // 로깅
        console.log("상세 조회", response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 수정요청 함수
    async updateProfile() {
      try {
        let temp = {
          // 임시 수정될 객체
          userId: this.user.userId,
          userPw: this.newPassword,
          userName: this.user.userName,
          gender: this.user.gender,
          userEmail: this.user.userEmail,
          userPhone: this.user.userPhone,
          userPromo: this.user.userPromo,
          userCode: this.user.userCode,
        };
        let response = await MyEditProfile.updateProfile(
          this.user.userId,
          temp
        );
        // 로깅
        console.log(response.data);
        // 화면에 성공메세지 출력 : message
        alert("수정이 성공했습니다.");
        this.$router.push("/mypage/editProfile");
      } catch (e) {
        console.log(e);
      }
    },
    // 삭제요청 함수
    async withdrawUser() {
      try {
        let userPwData = {
          userPW: this.userPwData.userPw,
        };
        console.log("회원탈퇴", userPwData);
        let response = await MyEditProfile.withdrawUser(this.user.userId, userPwData.userPW);
        console.log(response.data);
        alert("회원탈퇴가 성공했습니다.");
        this.$router.push("/");
      } catch (e) {
        console.log("회원탈퇴 실패", e);
      }
    },
    // 배송지 정보 가져오기
    async getShipAddress(userId) {
      try {
        let response = await OrderService.getShipAddress(userId);
        this.shipAddress = response.data;
        console.log("회원의 배송지 정보 : ", response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 카카오 주소 api 연동 부분
    execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: function (data) {
          var addr = ''; // 주소 변수

          //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
          if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
            addr = data.roadAddress;
          } else { // 사용자가 지번 주소를 선택했을 경우
            addr = data.jibunAddress;
          }

          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          document.getElementById('postcode').value = data.zonecode;
          document.getElementById("shipAddr").value = addr;
          // 커서를 상세주소 필드로 이동한다.
          document.getElementById("shipAddr2").focus();
        }
      }).open();
    },
  },
  computed: {
    isPasswordMatch() {
      return this.user.userPw === this.user.rePw;
    },
    isNewPasswordMatch() {
      return this.newPassword === this.newPasswordConfirm;
    },
  },
  mounted() {
    this.message = ""; // 변수 초기화
    this.getUser(this.$store.state.user?.userId);
    this.getShipAddress(this.$store.state.user.userId);
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
  height: 1000px;
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
