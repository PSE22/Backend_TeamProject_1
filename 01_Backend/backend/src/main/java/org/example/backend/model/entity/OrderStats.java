package org.example.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.backend.model.entity
 * fileName : OrderStats
 * author : kimtaewan
 * date : 2024-04-29
 * description : 주문통계
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-29         kimtaewan          최초 생성
 */
@Entity
@Table(name="TB_ORDER_STATS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class OrderStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long odStatId;
    private String odStatDate;
    private Integer dailyOrderCnt;
    private Integer dailySales;
    private Integer monthlyOrderCnt;
    private Integer monthlySales;
    private Integer yearlyOrderCnt;
    private Integer yearlySales;
}
