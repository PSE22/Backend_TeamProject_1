package org.example.backend.service;

import jakarta.servlet.http.HttpSession;
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
 * author : kimtaewan
 * date : 2024-04-25
 * description : 회원정보수정
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-25         kimtaewan          최초 생성
 */

@Service
public class EditProfileService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isPasswordCorrect(String userPw) {
        String userId = (String) httpSession.getAttribute("userId");
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return passwordEncoder.matches(userPw, user.getUserPw());
        }
        return false;
    }

    public User getCurrentUser() {
        String userId = (String) httpSession.getAttribute("userId");
        if (userId != null) {
            Optional<User> optionalUser = userRepository.findById(userId);
            return optionalUser.orElse(null);
        }
        return null;
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void withdrawUser(String userPw) {
        String userId = (String) httpSession.getAttribute("userId");
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(userPw, user.getUserPw())) {
                userRepository.delete(user);
                httpSession.invalidate();
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
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
