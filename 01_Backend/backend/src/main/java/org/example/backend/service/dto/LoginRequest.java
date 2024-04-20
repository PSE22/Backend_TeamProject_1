package org.example.backend.service.dto;

import lombok.*;

/**
 * packageName : org.example.backend.service.dto
 * fileName : LoginRequest
 * author : kimtaewan
 * date : 2024-04-19
 * description : 로그인 정보 수신
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19         kimtaewan          최초 생성
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String userId;
    private String userPw;
}
