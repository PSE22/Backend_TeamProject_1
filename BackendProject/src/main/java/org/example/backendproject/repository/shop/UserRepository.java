package org.example.backendproject.repository.shop;

import org.example.backendproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : UserRepository
 * author : SAMSUNG
 * date : 2024-04-30
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-30         SAMSUNG          최초 생성
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
