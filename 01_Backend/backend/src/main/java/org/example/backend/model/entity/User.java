package org.example.backend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.example.backend.model.common.BaseTimeEntity2;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.security.model.entity
 * fileName : User
 * author : kimtaewan
 * date : 2024-04-18
 * description : 회원
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-18         kimtaewan          최초 생성
 */
@Entity
@Table(name = "TB_USER")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_USER SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE USER_ID = ?")
public class User extends BaseTimeEntity2 {

    @Id
    private String userId;
    private String userPw;
    private String userName;
    private String userBirth;
    private String gender;
    private String userEmail;
    private String userPhone;
    private String userPromo;
    private String userCode;
}