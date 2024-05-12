package org.example.backend.model.dto.shop;

/**
 * packageName : org.example.backend.model.dto.shop
 * fileName : ICouponDto
 * author : gumiji
 * date : 5/7/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/7/24         gumiji          최초 생성
 */
public interface ICouponDto {
    Integer getCpId();
    Integer getPdId();
    String getCpName();
    Integer getCpDcPrice();
    Double getCpDcRate();
    Integer getCpMinPrice();
    Integer getCpMaxDcPrice();
    String getCpExpireDate();
    String getAddDate();
}
