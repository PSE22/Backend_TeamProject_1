package org.example.backendproject.model.dto.shop;

import org.springframework.data.jpa.repository.Query;

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
    String getCpId();
    String getCpName();
    Integer getCpDcPrice();
    Integer getCpDcRate();
    Integer getCpMinPrice();
    Integer getCpMaxDcPrice();
}
