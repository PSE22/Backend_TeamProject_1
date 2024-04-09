package org.example.jpaexam.model.common;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * packageName : org.example.jpaexam.model.common
 * fileName : BaseTimeEntity2
 * author : GGG
 * date : 2024-03-19
 * description : 공통 클래스 (공통 속성(컬럼)이 있음) : 소프트 딜리트용
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-19         GGG          최초 생성
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity2 {

    private String insertTime;

    private String updateTime;

    //   TODO:  삭제여부 필드 : Y,N (soft delete 용 == update 문 실행) N인걸 Y로 바꿈 -> Y인건 화면에 안보임
//    select * from tb_dept where delete_yn = 'N'; -> deleteYn 이 'N' (삭제안된) 것들만 출력
    private String deleteYn;
    //   TODO: 삭제시 시간 필드 (soft delete 등)
    private String deleteTime;

    @PrePersist
    void OnPrePersist() {
        this.insertTime = LocalDateTime.now()
                .format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.deleteYn = "N";
    }

    //    TODO: JPA 에서 update 가 실행되기전에 실행되는 함수
//     예) OnPreUpdate() -> update 실행
    @PreUpdate
    void OnPreUpdate() {
        this.updateTime = LocalDateTime.now()
                .format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.insertTime = this.updateTime;
        this.deleteYn = "N";
    }
}