//package org.example.backend.service.admin;
//
//import jakarta.annotation.PostConstruct;
//import org.example.backend.model.entity.User;
//import org.example.backend.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class AdminCreater {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public AdminCreater(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @PostConstruct
//    public void creater() {
//        // 테스트용 계정 생성
//        User admin = new User();
//        admin.setUserId("seoulmouse1");
//        admin.setUserPw(passwordEncoder.encode("password123!"));
//        admin.setUserName("김태완");
//        admin.setUserEmail("nick594740@gmail.com");
//        admin.setUserBirth("");
//        admin.setGender("Y");
//        admin.setUserPhone("01082981131");
//        admin.setUserPromo("Y");
//        admin.setUserCode("AT01");
//
//        userRepository.save(admin);
//    }
//}