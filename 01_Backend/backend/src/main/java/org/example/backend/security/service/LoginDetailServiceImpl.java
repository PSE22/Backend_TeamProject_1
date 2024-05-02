package org.example.backend.security.service;

import org.example.backend.model.entity.User;
import org.example.backend.repository.UserRepository;
import org.example.backend.security.service.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * packageName : org.example.backend.security.service
 * fileName : LoginDetailService
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
@Service
public class LoginDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("ID 없음:" + userId));
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getUserCode());
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);

        return new LoginDto(
                user.getUserId(),
                user.getUserPw(),
                authorities
        );
    }
}
