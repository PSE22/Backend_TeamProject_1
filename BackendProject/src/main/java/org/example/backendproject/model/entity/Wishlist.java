package org.example.backendproject.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.backendproject.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

/**
 * packageName : org.example.backendproject.model.entity
 * fileName : Wishlist
 * author : SAMSUNG
 * date : 2024-04-19
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19         SAMSUNG          최초 생성
 */
@Entity
@Table(name = "TB_WISHLIST")
@SequenceGenerator(
        name = "SQ_DEPT_GENERATOR"
        , sequenceName = "SQ_WISHLIST"
        , initialValue = 1
        , allocationSize = 1
)
@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Wishlist extends BaseTimeEntity {
//    pd_id	number
//    user_id	varchar2(100 byte)
//    add_date	date
//    status	char(1 byte)
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE
        , generator = "SQ_WISHLIST_GENERATOR"
)
    private Integer pd_id;
    private String user_id;
}
