package org.example.backendproject.model.dto.mypage;

/**
 * packageName : org.example.backendproject.model.dto.mypage
 * fileName : IMyCouponDto
 * author : gumiji
 * date : 4/25/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 4/25/24         gumiji          최초 생성
 */
public interface IMyCouponDto {
    Integer getCpId();  // PK 복합키
    String getUserId();    // PK 복합키
    String getAddDate();    // 발급일
    String getCpName();
    Integer getCpDcPrice();
    Integer getCpDcRate();
    Integer getCpMinPrice();
    Integer getCpMaxDcPrice();
    String getCpExpireDate();
}
