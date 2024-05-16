package org.example.backend.model.dto.shop;

/**
 * packageName : org.example.backendproject.model.dto.shop
 * fileName : IUserCouponDto
 * author : 5
 * date : 24. 5. 2.
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.         5          최초 생성
 */
public interface IUserCouponDto {
    String getUserId();
    Long getCpId();
    Long getPdId();
    String getCpName();
    Integer getCpDcPrice();
    Double getCpDcRate();
    Integer getCpMinPrice();
    Integer getCpMaxDcPrice();
}
