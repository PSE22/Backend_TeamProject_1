package org.example.backend.model.dto.mypage;

/**
 * packageName : org.example.backend.model.dto.mypage
 * fileName : IMyOrderDetailDto
 * author : gumiji
 * date : 5/10/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/10/24         gumiji          최초 생성
 */
public interface IMyOrderDetailDto {
    Long getOrderId();  // 주문번호
    String getOrderAddr();  // 주소
    String getOrderName();  // 수령인
    String getPostcode();   // 우편번호
    String getOrderPhone(); // 연락처
    String getShipMemo();   // 배송메모
    Long getOrderPrice();   // 결제금액
    String getOrderPayment();   // 결제수단
    String getOrAddDate();    // 주문일자
    String getOrderCode();  // 주문상태코드
    String getOrderDetailCnt();  // 주문수량
    Integer getOrderDetailPrice();  // 상품 금액
    String getOcAddDate();  // 취소 신청일
    String getOcReason();  // 취소사유
    String getOcDenyReason();  // 취소 취소사유
    String getReAddDate();  // 환불 신청일
    String getRefundReason();  // 환불사유
    String getRefundDenyReason();  // 취소 취소사유
    String getPdThumbnail();  // 상품 썸네일
    String getPdName();  // 상품명
    String getOpName();  // 옵션명
    Integer getPdPrice();  // 상품가격
    Integer getCpDcPrice(); // 할인금액
    Double getCpDcRate();   // 할인율
    Integer getUsePointPrice(); // 사용포인트

}
