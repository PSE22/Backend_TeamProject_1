package org.example.backend.model.dto.mypage;

import lombok.*;
import org.example.backend.model.entity.OrderDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName : org.example.backend.model.dto.mypage
 * fileName : OrderCancelDto
 * author : gumiji
 * date : 5/16/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/16/24         gumiji          최초 생성
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderCancelDto {
    private Long orderId;
    private List<Long> opId = new ArrayList<>();
    private List<Integer> ocPrice = new ArrayList<>();
    private String ocCode;
    private String ocReason;
}
