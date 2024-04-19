package org.example.backend.security.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * packageName : org.example.backend.security.dto
 * fileName : UserDto
 * author : kimtaewan
 * date : 2024-04-19
 * description :
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
public class LoginDto extends User {
    private String userId;

    public LoginDto(String userId, String userPw, Collection<? extends GrantedAuthority> authorities) {
        super(userId, userPw, authorities);
        this.userId = userId;
    }
}
