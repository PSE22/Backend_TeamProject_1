package org.example.backendproject.model.dto.shop;

/**
 * packageName : org.example.backendproject.model.dto.shop
 * fileName : ICouponDto
 * author : sjuk2
 * date : 2024-05-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-01         sjuk2          최초 생성
 */
public interface ICouponDto {
    Integer getCpId();
    Integer getPdId();
    String getCpName();
    Integer getCpDcPrice();
    Integer getCpDcRate();
    Integer getCpMinPrice();
    Integer getCpMaxDcPrice();
    String getCpExpireDate();
}
