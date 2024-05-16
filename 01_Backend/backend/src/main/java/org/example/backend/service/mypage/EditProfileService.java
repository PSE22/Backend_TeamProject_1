package org.example.backend.service.mypage;

import org.example.backend.model.entity.User;
import org.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.backend.service
 * fileName : EditProfileService
 * author :
 * date : 2024-04-25
 * description : 회원정보수정
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-25                   최초 생성
 */

@Service
public class EditProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isPasswordCorrect(String userId, String userPw) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return passwordEncoder.matches(userPw, user.getUserPw());
        }
        return false;
    }

    public String getCurrentUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    public User updateUser(User user) {
        userRepository.save(user);
        return user;
    }

    public String withdrawUser(String userId, String userPw) {
        // 사용자 ID로 사용자 정보 조회
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // 입력한 비밀번호와 사용자의 저장된 비밀번호를 비교하여 일치하는지 확인
            if (passwordEncoder.matches(userPw, user.getUserPw())) {
                // 일치할 경우 회원 탈퇴 수행
                userRepository.deleteById(userId);
                return "회원 탈퇴가 완료되었습니다.";
            } else {
                // 비밀번호가 일치하지 않을 경우 실패 메시지 반환
                return "비밀번호가 일치하지 않습니다.";
            }
        } else {
            // 사용자가 존재하지 않을 경우 실패 메시지 반환
            return "사용자가 존재하지 않습니다.";
        }
    }



    @Scheduled(cron = "0 0 0 * * *")
    public void deleteInactiveUsers() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime threeYearsAgo = LocalDateTime.now().minusYears(3);
        String threeYearsAgoStr = threeYearsAgo.format(formatter);

        List<User> inactiveUsers = userRepository.findByStatusAndDelDate("N", threeYearsAgoStr);
        userRepository.deleteAll(inactiveUsers);
    }
}