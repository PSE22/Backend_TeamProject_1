package org.example.backend.service.dto;

import lombok.*;

/**
 * packageName : org.example.backend.service.dto
 * fileName : LoginResponse
 * author : kimtaewan
 * date : 2024-04-19
 * description : 로그인 정보 전송
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19         kimtaewan          최초 생성
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String accessToken;

    private String tokenType = "Bearer";
    private String userId;
    private String userCode;

    public LoginResponse(String accessToken, String userId, String userCode) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.userCode = userCode;
    }
}
