package org.example.backend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.backend.model.entity
 * fileName : OrderStats
 * author : kimtaewan
 * date : 2024-04-29
 * description :
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
    private Long statId;

    private String statDate;

    private Integer dailyOrderCount;

    private Integer dailySales;

    private Integer weeklyOrderCount;

    private Integer weeklySales;

    private Integer monthlyOrderCount;

    private Integer monthlySales;

    private Integer yearlyOrderCount;

    private Integer yearlySales;
}
