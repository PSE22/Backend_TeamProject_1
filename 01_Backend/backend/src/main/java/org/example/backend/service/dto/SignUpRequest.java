package org.example.backend.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.backend.model.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * packageName : org.example.backend.model.dto
 * fileName : SignUpRequest
 * author : kimtaewan
 * date : 2024-04-18
 * description : 회원 정보 수신
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-18         kimtaewan          최초 생성
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "ID는 필수 입력 값입니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String userPw;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String userName;

    private String userBirth;

    @NotBlank(message = "성별은 필수 입력 값입니다.")
    private String gender;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "올바른 이메일 주소를 입력하세요.")
    private String userEmail;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    private String userPhone;

    @NotBlank
    private String userPromo;

    @NotBlank
    private String userCode;


    public User toUser(PasswordEncoder passwordEncoder) {
        String encodedPassword = passwordEncoder.encode(userPw);
        String birth = userBirth != null ? userBirth : "";

        User user = new User();
        user.setUserId(userId);
        user.setUserPw(encodedPassword);
        user.setUserName(userName);
        user.setUserBirth(birth);
        user.setGender(gender);
        user.setUserEmail(userEmail);
        user.setUserPhone(userPhone);
        user.setUserPromo(userPromo);
        user.setUserCode(userCode);

        return user;
    }
}