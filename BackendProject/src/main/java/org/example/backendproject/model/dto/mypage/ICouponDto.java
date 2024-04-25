package org.example.backendproject.model.dto.mypage;

import org.apache.kafka.common.protocol.types.Field;

/**
 * packageName : org.example.backendproject.model.dto.mypage
 * fileName : ICouponDto
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
public interface ICouponDto {
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
